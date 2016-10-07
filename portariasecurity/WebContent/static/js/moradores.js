$(document).ready(function() {
	aplicarListeners();
	aplicatListenerBtnSalvar();
	jQuery(function($) {
		$.mask.definitions['~'] = '[+-]';
		//Inicio Mascara Telefone
		$('#celular').mask("(99) 9999-9999?9").blur(
			function(event) {
				var target, phone, element;
				target = (event.currentTarget) ? event.currentTarget
						: event.srcElement;
				phone = target.value.replace(/\D/g, '');
				element = $(target);
				element.unmask();
				if (phone.length > 10) {
					element.mask("(99) 99999-999?9");
				} else {
					element.mask("(99) 9999-9999?9");
				}
			});
	});
	$('#modal-moradores').on('show.bs.modal', function () {})
});

var aplicatListenerBtnSalvar = function() {
	$('#btn-salvar').on('click', function() {
		var url = 'moradores';
		var dados = $('#form-moradores').serialize();
		$.post(url, dados).done(function(pagina) {
			$('#secao-moradores').html(pagina)
			aplicarListeners();
		}).fail(function() {
		    $.bootstrapGrowl("Erro ao cadastrar Morador!", { type:'danger' ,align:'center'});
		}).always(function() {
			$('#modal-moradores').modal('hide');
		});
	});
}

var limparModal = function() {
	$('#id').val('');
	$('#nome').val('');
	$('#cpf').val('');
	$('#documento').val('');
	$('#celular').val('');
	$('#datanascto').val('');
	$('#unidade option').attr('selected', false);
};

var aplicarListeners = function() {
	$("select.special-flexselect").flexselect({ hideDropdownOnEmptyInput: true });
	$('#modal-moradores').on('hide.bs.modal', limparModal);

	$('.btn-editar').on('click', function() {
		var id = $(this).parents('tr').data('id');
		var url = 'moradores/' + id;

		$.get(url).success(function(morador) {
			$('#id').val(morador.id);
			$('#nome').val(morador.nome);
			$('#cpf').val(morador.cpf);
			$('#documento').val(morador.documento);
			$('#celular').val(morador.celular);
			if (morador.datanascto != null) {
				var dt = morador.datanascto;
				var dtn = dt.substr(8,2)+'/'+dt.substr(5,2)+'/'+dt.substr(0,4);
				$('#datanascto').val(dtn);
			}
			$('#unidade option[value='+morador.unidade.id+']').attr('selected', true);
			$('#modal-moradores').modal('show');
		});
	});

	$('.btn-deletar').on('click', function() {
		var id = $(this).parents('tr').data('id');
		var csrf = $('#csrf').val();

		$.ajax({
			url : "moradores/" + id,
			type : 'DELETE',
			headers: {'X-CSRF-TOKEN': csrf},
			success : function(result) {
				$('tr[data-id="' + id + '"]').remove();
			    $.bootstrapGrowl("Morador excluído com sucesso!", { type:'success' ,align:'center'});
			}, 
			fail: function(result) {
			    $.bootstrapGrowl("Erro ao excluir Morador!", { type:'danger' ,align:'center'});
			}
		});
	});
  	$('#btn-procurar').on('click', function() {
		var moradorFiltro = $('#moradorFiltro').val();
		var unidadeFiltro = $('#unidadeFiltro').val();
		
		$.ajax({
			url : "moradoresFiltrar?moradorFiltro="+moradorFiltro+"&unidadeFiltro="+unidadeFiltro,
			type : 'GET',
			headers: {'X-CSRF-TOKEN': csrf},
			success : function(data) {
				$('#secao-moradores').html(data),
				aplicarListeners();
			    $.bootstrapGrowl("Morador(es) filtrados com sucesso!", { type:'success' ,align:'center'});
			}, 
			fail: function(data) {
			    $.bootstrapGrowl("Morador(es) não encontrado(s)!", { type:'danger' ,align:'center'});
			}
		});  		
  	});
  	$(function(){
  	    $("#filtrarControle").click(function(e){
  	        e.preventDefault();
  	        el = $(this).data('element');
  	        $(el).toggle();
  	    });
  	});
}
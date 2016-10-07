$(document).ready(function() {
	aplicarListeners();
	aplicatListenerBtnSalvar();
});

var aplicatListenerBtnSalvar = function() {
	$('#btn-salvar').on('click', function() {
		var url = 'modelos';
		var dados = $('#form-modelos').serialize();
		$.post(url, dados).done(function(pagina) {
			$('#secao-modelos').html(pagina)
			aplicarListeners();
		}).fail(function() {
		    $.bootstrapGrowl("Erro ao cadastrar Modelo!", { type:'danger' ,align:'center'});
		}).always(function() {
			$('#modal-modelos').modal('hide');
		});
	});
}

var limparModal = function() {
	$('#id').val('');
	$('#descricao').val('');
	$('#marca').val('');
};

var aplicarListeners = function() {
	$("select.special-flexselect").flexselect({ hideDropdownOnEmptyInput: true });
	$('#modal-modelos').on('hide.bs.modal', limparModal);

	$('.btn-editar').on('click', function() {
		var id = $(this).parents('tr').data('id');
		var url = 'modelos/' + id;

		$.get(url).success(function(modelo) {
			$('#id').val(modelo.id);
			$('#descricao').val(modelo.descricao);
			$('#marca option[value='+modelo.marca.id+']').attr('selected', true);
			$('#modal-modelos').modal('show');
		});
	});

	$('.btn-deletar').on('click', function() {
		var id = $(this).parents('tr').data('id');
		var csrf = $('#csrf').val();

		$.ajax({
			url : "modelos/" + id,
			type : 'DELETE',
			headers: {'X-CSRF-TOKEN': csrf},
			success : function(result) {
				$('tr[data-id="' + id + '"]').remove();
			    $.bootstrapGrowl("Modelo excluído com sucesso!", { type:'success' ,align:'center'});
			}, 
			fail: function(result) {
			    $.bootstrapGrowl("Erro ao excluir Modelo!", { type:'danger' ,align:'center'});
			}
		});
	});
  	$('#btn-procurar').on('click', function() {
		var marcaFiltro  = $('#marcaFiltro').val();
		var modeloFiltro = $('#modeloFiltro').val();
		
		$.ajax({
			url : "modelosFiltrar?marcaFiltro="+marcaFiltro+"&modeloFiltro="+modeloFiltro,
			type : 'GET',
			headers: {'X-CSRF-TOKEN': csrf},
			success : function(data) {
				$('#secao-modelos').html(data),
				aplicarListeners();
			    $.bootstrapGrowl("Modelo(s) filtrados com sucesso!", { type:'success' ,align:'center'});
			}, 
			fail: function(data) {
			    $.bootstrapGrowl("Modelo(s) não encontrado(s)!", { type:'danger' ,align:'center'});
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
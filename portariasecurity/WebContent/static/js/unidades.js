$(document).ready(function() {
	aplicarListeners();
	aplicatListenerBtnSalvar();
	jQuery(function($) {
		$.mask.definitions['~'] = '[+-]';
		//Inicio Mascara Telefone
		$('#telefone').mask("(99) 9999-9999?9").blur(
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
	$("#unidadeTipo").change(function() {
		$('#bloco').show();
		if ($('#unidadeTipo option:selected').val() == 1) {
			$('#bloco').hide();
		}
	});
	$('#modal-unidades').on('show.bs.modal', function () {
		$('#bloco').show();
		if ($('#unidadeTipo option:selected').val() == 1) {
			$('#andar option').attr('selected', false);
			$('#bloco option').attr('selected', false);
			$('#bloco').hide();
		}		  
	})
});

var aplicatListenerBtnSalvar = function() {
	$('#btn-salvar').on('click', function() {
		var url = 'unidades';
		var dados = $('#form-unidades').serialize();
		$.post(url, dados).done(function(pagina) {
			$('#secao-unidades').html(pagina)
			aplicarListeners();
		}).fail(function() {
		    $.bootstrapGrowl("Erro ao cadastrar Unidade!", { type:'danger' ,align:'center'});
		}).always(function() {
			$('#modal-unidades').modal('hide');
		});
	});
}

var limparModal = function() {
	$('#id').val('');
	$('#descricao').val('');
	$('#ramal').val('');
	$('#telefone').val('');
	$('#vagas').val('');
	$('#moradorTipo option').attr('selected', false);
	$('#unidadeTipo option').attr('selected', false);
	if ( '#andar'.length > 0) {
		$('#andar option').attr('selected', false);
	}
	if ( '#bloco'.length > 0) {
		$('#bloco option').attr('selected', false);
	}
};

var aplicarListeners = function() {
	$("select.special-flexselect").flexselect({ hideDropdownOnEmptyInput: true });
	$('#modal-unidades').on('hide.bs.modal', limparModal);

	$('.btn-editar').on('click', function() {
		var id = $(this).parents('tr').data('id');
		var url = 'unidades/' + id;

		$.get(url).success(function(unidade) {
			$('#id').val(unidade.id);
			$('#descricao').val(unidade.descricao);
			$('#ramal').val(unidade.ramal);
			$('#telefone').val(unidade.telefone);
			$('#vagas').val(unidade.vagas);
			$('#moradorTipo option[value='+unidade.moradorTipo.id+']').attr('selected', true);
			$('#unidadeTipo option[value='+unidade.unidadeTipo.id+']').attr('selected', true);
			if ( unidade.andar != null) {
				$('#andar option[value='+unidade.andar.id+']').attr('selected', true);
			}
			if ( unidade.bloco != null) {
				$('#bloco option[value='+unidade.bloco.id+']').attr('selected', true);
			}
			$('#modal-unidades').modal('show');
		});
	});

	$('.btn-deletar').on('click', function() {
		var id = $(this).parents('tr').data('id');
		var csrf = $('#csrf').val();

		$.ajax({
			url : "unidades/" + id,
			type : 'DELETE',
			headers: {'X-CSRF-TOKEN': csrf},
			success : function(result) {
				$('tr[data-id="' + id + '"]').remove();
			    $.bootstrapGrowl("Unidade excluída com sucesso!", { type:'success' ,align:'center'});
			}, 
			fail: function(result) {
			    $.bootstrapGrowl("Erro ao excluir Unidade!", { type:'danger' ,align:'center'});
			}
		});
	});
  	$('#btn-procurar').on('click', function() {
		var unidadeFiltro = $('#unidadeFiltro').val();
		var moradorTipoFiltro = $('#moradorTipoFiltro').val();
		var unidadeTipoFiltro = $('#unidadeTipoFiltro').val();
		var blocoFiltro = $('#blocoFiltro').val();
		var andarFiltro = $('#andarFiltro').val();
		
		$.ajax({
			url : "unidadesFiltrar?unidadeFiltro="+unidadeFiltro+"&moradorTipoFiltro="+moradorTipoFiltro+"&unidadeTipoFiltro="+unidadeTipoFiltro+"&blocoFiltro="+blocoFiltro+"&andarFiltro="+andarFiltro,
			type : 'GET',
			headers: {'X-CSRF-TOKEN': csrf},
			success : function(data) {
				$('#secao-unidades').html(data),
				aplicarListeners();
			    $.bootstrapGrowl("Unidade(s) filtradas com sucesso!", { type:'success' ,align:'center'});
			}, 
			fail: function(data) {
			    $.bootstrapGrowl("Unidade(s) não encontrado(s)!", { type:'danger' ,align:'center'});
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
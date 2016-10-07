modeloId = "";

$(document).ready(function() {
	aplicarListeners();
	aplicatListenerBtnSalvar();
	jQuery(function($) {
		$.mask.definitions['~'] = '[+-]';
		$('#placa').mask("a**-9999").blur(
				function(event) {
					var target, phone, element;
					target = (event.currentTarget) ? event.currentTarget
							: event.srcElement;
					phone = target.value.replace(/\D/g, '');
					element = $(target);
					element.unmask();
					element.mask("a**-9999");
				});
	});
	$("#marca").bind("change",function () {
        var id = $(this).val();
        if(id !== "") {
      	  var url = 'modelosSelecionar/' + id;
      		$.get(url).success(function(data) {
          		var options = "<option value=''>-- Selecione --</option>";
                  $.each(data, function(index, valor) {
                  	if(valor.descricao != undefined) {
              			options += "<option value='" + valor.id + "' "+(valor.id==modeloId ? ' selected' : '')+">" + valor.descricao + "</option>";
                  	}
                  });
                  $("#modelo").html(options).fadeIn();
              });
          }
  	});
});

var aplicatListenerBtnSalvar = function() {
	$('#btn-salvar').on('click', function() {
		var url = 'visitantes';
		var dados = $('#form-visitantes').serialize();
		$.post(url, dados).done(function(pagina) {
			$('#secao-visitantes').html(pagina)
			aplicarListeners();
		}).fail(function() {
		    $.bootstrapGrowl("Erro ao cadastrar Visitante!", { type:'danger' ,align:'center'});
		}).always(function() {
			$('#modal-visitantes').modal('hide');
		});
	});
}

var limparModal = function() {
	$('#id').val('');
	$('#unidade option').attr('selected', false);
	$('#nome').val('');
	$('#placa').val('');
	$('#entrada').val('');
	$('#saida').val('');
	$('#documento').val('');
	$('#marca option').attr('selected', false);
	$('#modelo option').attr('selected', false);
	$('#cor option').attr('selected', false);
	$('#visitanteTipo option').attr('selected', false);
};

var aplicarListeners = function() {
	$("select.special-flexselect").flexselect({ hideDropdownOnEmptyInput: true });
	$('#modal-visitantes').on('hide.bs.modal', limparModal);

	$('.btn-editar').on('click', function() {
		var id = $(this).parents('tr').data('id');
		var url = 'visitantes/' + id;

		$.get(url).success(function(visitante) {
			$('#id').val(visitante.id);
			$('#unidade option[value='+visitante.unidade.id+']').attr('selected', true);
			$('#nome').val(visitante.nome);
			$('#placa').val(visitante.placa);
			$('#documento').val(visitante.documento);
			if ( visitante.modelo != null) {
				$('#marca option[value='+visitante.modelo.marca.id+']').attr('selected', true);
				modeloId = visitante.modelo.id;
				$('#marca').trigger("change");
				$('#modelo option[value='+visitante.modelo.id+']').attr('selected', true);
			}
			if ( visitante.cor != null) {
				$('#cor option[value='+visitante.cor.id+']').attr('selected', true);
			}
			$('#visitanteTipo option[value='+visitante.visitanteTipo.id+']').attr('selected', true);
			$('#modal-visitantes').modal('show');
		});
	});

	$('.btn-deletar').on('click', function() {
		var id = $(this).parents('tr').data('id');
		var csrf = $('#csrf').val();

		$.ajax({
			url : "visitantes/" + id,
			type : 'DELETE',
			headers: {'X-CSRF-TOKEN': csrf},
			success : function(result) {
				$('tr[data-id="' + id + '"]').remove();
			    $.bootstrapGrowl("Visitante excluído com sucesso!", { type:'success' ,align:'center'});
			}, 
			fail: function(result) {
			    $.bootstrapGrowl("Erro ao excluir Visitante!", { type:'danger' ,align:'center'});
			}
		});
	});
	$('.btn-saida').on('click', function() {
		var id = $(this).parents('tr').data('id');
		var csrf = $('#csrf').val();

		$.ajax({
			url : "visitantes/" + id,
			type : 'PUT',
			headers: {'X-CSRF-TOKEN': csrf},
			success : function(result) {
				$('tr[data-id="' + id + '"]').hide();
			    $.bootstrapGrowl("Visitante alterado com sucesso!", { type:'success' ,align:'center'});
			}, 
			fail: function(result) {
			    $.bootstrapGrowl("Erro ao alterar Visitante!", { type:'danger' ,align:'center'});
			}
		});	});
}
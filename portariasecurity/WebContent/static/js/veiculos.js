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
		var url = 'veiculos';
		var dados = $('#form-veiculos').serialize();
		$.post(url, dados).done(function(pagina) {
			$('#secao-veiculos').html(pagina)
			aplicarListeners();
		}).fail(function() {
		    $.bootstrapGrowl("Erro ao cadastrar Veiculo!", { type:'danger' ,align:'center'});
		}).always(function() {
			$('#modal-veiculos').modal('hide');
		});
	});
}

var limparModal = function() {
	$('#id').val('');
	$('#placa').val('');
	$('#marca').val('');
	$('#modelo').val('');
	$('#cor').val('');
	$('#ano').val('');
	$('#morador option').attr('selected', false);
	$('#marca option').attr('selected', false);
	$('#cor option').attr('selected', false);
};

var aplicarListeners = function() {
	$("select.special-flexselect").flexselect({ hideDropdownOnEmptyInput: true });
	$('#modal-veiculos').on('hide.bs.modal', limparModal);
	$('.btn-editar').on('click', function() {
		var id = $(this).parents('tr').data('id');
		var url = 'veiculos/' + id;

		$.get(url).success(function(veiculo) {
			console.log(veiculo);
			$('#id').val(veiculo.id);
			$('#placa').val(veiculo.placa);
			$('#ano').val(veiculo.ano);			
			$('#morador option[value='+veiculo.morador.id+']').attr('selected', true);
			$('#marca option[value='+veiculo.modelo.marca.id+']').attr('selected', true);
			modeloId = veiculo.modelo.id;
			$('#marca').trigger("change");
			$('#modelo option[value='+veiculo.modelo.id+']').attr('selected', true);
			$('#cor option[value='+veiculo.cor.id+']').attr('selected', true);		
			$('#modal-veiculos').modal('show');
		});
	});

	$('.btn-deletar').on('click', function() {
		var id = $(this).parents('tr').data('id');
		var csrf = $('#csrf').val();

		$.ajax({
			url : "veiculos/" + id,
			type : 'DELETE',
			headers: {'X-CSRF-TOKEN': csrf},
			success : function(result) {
				$('tr[data-id="' + id + '"]').remove();
			    $.bootstrapGrowl("Veiculo excluído com sucesso!", { type:'success' ,align:'center'});
			}, 
			fail: function(result) {
			    $.bootstrapGrowl("Erro ao excluir Veiculo!", { type:'danger' ,align:'center'});
			}
		});
	});
  	$('#btn-procurar').on('click', function() {
		var moradorFiltro = $('#moradorFiltro').val();
		var unidadeFiltro = $('#unidadeFiltro').val();
		var placaFiltro = $('#placaFiltro').val();
		var modeloFiltro = $('#modeloFiltro').val();
		
		$.ajax({
			url : "veiculosFiltrar?moradorFiltro="+moradorFiltro+"&unidadeFiltro="+unidadeFiltro+"&placaFiltro="+placaFiltro+"&modeloFiltro="+modeloFiltro,
			type : 'GET',
			headers: {'X-CSRF-TOKEN': csrf},
			success : function(data) {
				$('#secao-veiculos').html(data),
				aplicarListeners();
			    $.bootstrapGrowl("Veiculo(s) filtrados com sucesso!", { type:'success' ,align:'center'});
			}, 
			fail: function(data) {
			    $.bootstrapGrowl("Veiculo(s) não encontrado(s)!", { type:'danger' ,align:'center'});
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
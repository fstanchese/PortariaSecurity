$(document).ready(function() {
	aplicarListeners();
	aplicatListenerBtnSalvar();
});

var aplicatListenerBtnSalvar = function() {
	$('#btn-salvar').on('click', function() {
		var url = 'marcas';
		var dados = $('#form-marcas').serialize();
		$.post(url, dados).done(function(pagina) {
			$('#secao-marcas').html(pagina)
			aplicarListeners();
		}).fail(function() {
		    $.bootstrapGrowl("Erro ao cadastrar Marca!", { type:'danger' ,align:'center'});
		}).always(function() {
			$('#modal-marcas').modal('hide');
		});
	});
}

var limparModal = function() {
	$('#id').val('');
	$('#descricao').val('');
};

var aplicarListeners = function() {
	$("select.special-flexselect").flexselect({ hideDropdownOnEmptyInput: true });
	$('#modal-marcas').on('hide.bs.modal', limparModal);

	$('.btn-editar').on('click', function() {
		var id = $(this).parents('tr').data('id');
		var url = 'marcas/' + id;

		$.get(url).success(function(bloco) {
			$('#id').val(bloco.id);
			$('#descricao').val(bloco.descricao);
			$('#modal-marcas').modal('show');
		});
	});

	$('.btn-deletar').on('click', function() {
		var id = $(this).parents('tr').data('id');
		var csrf = $('#csrf').val();

		$.ajax({
			url : "marcas/" + id,
			type : 'DELETE',
			headers: {'X-CSRF-TOKEN': csrf},
			success : function(result) {
				$('tr[data-id="' + id + '"]').remove();
			    $.bootstrapGrowl("Marca excluída com sucesso!", { type:'success' ,align:'center'});
			}, 
			fail: function(result) {
			    $.bootstrapGrowl("Erro ao excluir Marca!", { type:'danger' ,align:'center'});
			}
		});
	});
  	$('#btn-procurar').on('click', function() {
		var marcaFiltro = $('#marcaFiltro').val();
		
		$.ajax({
			url : "marcasFiltrar?marcaFiltro="+marcaFiltro,
			type : 'GET',
			headers: {'X-CSRF-TOKEN': csrf},
			success : function(data) {
				$('#secao-marcas').html(data),
				aplicarListeners();
			    $.bootstrapGrowl("Marca(s) filtradas com sucesso!", { type:'success' ,align:'center'});
			}, 
			fail: function(data) {
			    $.bootstrapGrowl("Marca(s) não encontrada(s)!", { type:'danger' ,align:'center'});
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
$(document).ready(function() {
	aplicarListeners();
	aplicatListenerBtnSalvar();
});

var aplicatListenerBtnSalvar = function() {
	$('#btn-salvar').on('click', function() {
		var url = 'unidadetipos';
		var dados = $('#form-unidadetipos').serialize();
		$.post(url, dados).done(function(pagina) {
			$('#secao-unidadetipos').html(pagina)
			aplicarListeners();
		}).fail(function() {
		    $.bootstrapGrowl("Erro ao cadastrar Tipo de Unidade!", { type:'danger' ,align:'center'});
		}).always(function() {
			$('#modal-unidadetipos').modal('hide');
		});
	});
}

var limparModal = function() {
	$('#id').val('');
	$('#descricao').val('');
};

var aplicarListeners = function() {
	$('#modal-unidadetipos').on('hide.bs.modal', limparModal);

	$('.btn-editar').on('click', function() {
		var id = $(this).parents('tr').data('id');
		var url = 'unidadetipos/' + id;

		$.get(url).success(function(tipounidade) {
			$('#id').val(tipounidade.id);
			$('#descricao').val(tipounidade.descricao);
			$('#modal-unidadetipos').modal('show');
		});
	});

	$('.btn-deletar').on('click', function() {
		var id = $(this).parents('tr').data('id');
		var csrf = $('#csrf').val();

		$.ajax({
			url : "unidadetipos/" + id,
			type : 'DELETE',
			headers: {'X-CSRF-TOKEN': csrf},
			success : function(result) {
				$('tr[data-id="' + id + '"]').remove();
			    $.bootstrapGrowl("Tipo de Unidade excluído com sucesso!", { type:'success' ,align:'center'});
			}, 
			fail: function(result) {
			    $.bootstrapGrowl("Erro ao excluir Tipo de Unidade!", { type:'danger' ,align:'center'});
			}
		});
	});
}
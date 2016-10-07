$(document).ready(function() {
	aplicarListeners();
	aplicatListenerBtnSalvar();
});

var aplicatListenerBtnSalvar = function() {
	$('#btn-salvar').on('click', function() {
		var url = 'moradortipos';
		var dados = $('#form-moradortipos').serialize();
		$.post(url, dados).done(function(pagina) {
			$('#secao-moradortipos').html(pagina)
			aplicarListeners();
		}).fail(function() {
		    $.bootstrapGrowl("Erro ao cadastrar Tipo de Morador!", { type:'danger' ,align:'center'});
		}).always(function() {
			$('#modal-moradortipos').modal('hide');
		});
	});
}

var limparModal = function() {
	$('#id').val('');
	$('#descricao').val('');
};

var aplicarListeners = function() {
	$('#modal-moradortipos').on('hide.bs.modal', limparModal);

	$('.btn-editar').on('click', function() {
		var id = $(this).parents('tr').data('id');
		var url = 'moradortipos/' + id;

		$.get(url).success(function(moradortipos) {
			$('#id').val(moradortipos.id);
			$('#descricao').val(moradortipos.descricao);
			$('#modal-moradortipos').modal('show');
		});
	});

	$('.btn-deletar').on('click', function() {
		var id = $(this).parents('tr').data('id');
		var csrf = $('#csrf').val();

		$.ajax({
			url : "moradortipos/" + id,
			type : 'DELETE',
			headers: {'X-CSRF-TOKEN': csrf},
			success : function(result) {
				$('tr[data-id="' + id + '"]').remove();
			    $.bootstrapGrowl("Tipo de Morador excluído com sucesso!", { type:'success' ,align:'center'});
			}, 
			fail: function(result) {
			    $.bootstrapGrowl("Erro ao excluir Tipo de Morador!", { type:'danger' ,align:'center'});
			}
		});
	});
}
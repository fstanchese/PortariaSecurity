$(document).ready(function() {
	aplicarListeners();
	aplicatListenerBtnSalvar();
});

var aplicatListenerBtnSalvar = function() {
	$('#btn-salvar').on('click', function() {
		var url = 'funcoes';
		var dados = $('#form-funcoes').serialize();
		$.post(url, dados).done(function(pagina) {
			$('#secao-funcoes').html(pagina)
			aplicarListeners();

		}).fail(function() {
		    $.bootstrapGrowl("Erro ao cadastrar Função!", { type:'danger' ,align:'center'});
		}).always(function() {
			$('#modal-funcoes').modal('hide');
		});
	});
}

var limparModal = function() {
	$('#id').val('');
	$('#descricao').val('');
};

var aplicarListeners = function() {
	$('#modal-funcoes').on('hide.bs.modal', limparModal);

	$('.btn-editar').on('click', function() {
		var id = $(this).parents('tr').data('id');
		var url = 'funcoes/' + id;

		$.get(url).success(function(funcao) {
			$('#id').val(funcao.id);
			$('#descricao').val(funcao.descricao);
			$('#modal-funcoes').modal('show');
		});
	});

	$('.btn-deletar').on('click', function() {
		var id = $(this).parents('tr').data('id');
		var csrf = $('#csrf').val();

		$.ajax({
			url : "funcoes/" + id,
			type : 'DELETE',
			headers: {'X-CSRF-TOKEN': csrf},
			success : function(result) {
				$('tr[data-id="' + id + '"]').remove();
			    $.bootstrapGrowl("Funcão excluída com sucesso!", { type:'success' ,align:'center'});
			}, 
			fail: function(result) {
			    $.bootstrapGrowl("Erro ao excluir Função!", { type:'danger' ,align:'center'});
			}
		});
	});
}
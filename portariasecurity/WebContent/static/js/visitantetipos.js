$(document).ready(function() {
	aplicarListeners();
	aplicatListenerBtnSalvar();
});

var aplicatListenerBtnSalvar = function() {
	$('#btn-salvar').on('click', function() {
		var url = 'visitantetipos';
		var dados = $('#form-visitantetipos').serialize();
		$.post(url, dados).done(function(pagina) {
			$('#secao-visitantetipos').html(pagina)
			aplicarListeners();
		}).fail(function() {
		    $.bootstrapGrowl("Erro ao cadastrar Tipo de Visitante!", { type:'danger' ,align:'center'});
		}).always(function() {
			$('#modal-visitantetipos').modal('hide');
		});
	});
}

var limparModal = function() {
	$('#id').val('');
	$('#descricao').val('');
};

var aplicarListeners = function() {
	$('#modal-visitantetipos').on('hide.bs.modal', limparModal);

	$('.btn-editar').on('click', function() {
		var id = $(this).parents('tr').data('id');
		var url = 'visitantetipos/' + id;

		$.get(url).success(function(visitantetipo) {
			$('#id').val(visitantetipo.id);
			$('#descricao').val(visitantetipo.descricao);
			$('#modal-visitantetipos').modal('show');
		});
	});

	$('.btn-deletar').on('click', function() {
		var id = $(this).parents('tr').data('id');
		var csrf = $('#csrf').val();

		$.ajax({
			url : "visitantetipos/" + id,
			type : 'DELETE',
			headers: {'X-CSRF-TOKEN': csrf},
			success : function(result) {
				$('tr[data-id="' + id + '"]').remove();
			    $.bootstrapGrowl("Tipo de Visitante excluído com sucesso!", { type:'success' ,align:'center'});
			}, 
			fail: function(result) {
			    $.bootstrapGrowl("Erro ao excluir Tipo de Visitante!", { type:'danger' ,align:'center'});
			}
		});
	});
}
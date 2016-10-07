$(document).ready(function() {
	aplicarListeners();
	aplicatListenerBtnSalvar();
});

var aplicatListenerBtnSalvar = function() {
	$('#btn-salvar').on('click', function() {
		var url = 'funcionarios';
		var dados = $('#form-funcionarios').serialize();
		console.log(dados);
		$.post(url, dados).done(function(pagina) {
			$('#secao-funcionarios').html(pagina)
			aplicarListeners();
		}).fail(function() {
		    $.bootstrapGrowl("Erro ao cadastrar Funcionário!", { type:'danger' ,align:'center'});
		}).always(function() {
			$('#modal-funcionarios').modal('hide');
		});
	});
}

var limparModal = function() {
	$('#id').val('');
	$('#nome').val('');
	$('#login').val('');
	$('#senha').val('');
	$('#confirmacaoSenha').val('');
	$('#permissoes option').attr('selected', false);
};

var aplicarListeners = function() {
	$('#modal-funcionarios').on('hide.bs.modal', limparModal);

	$('.btn-editar').on('click', function() {
		var id = $(this).parents('tr').data('id');
		var url = 'funcionarios/' + id;

		$.get(url).success(function(funcionario) {
			$('#id').val(funcionario.id);
			$('#nome').val(funcionario.nome);
			$('#login').val(funcionario.login);
			$('#senha').val(funcionario.senha);
			$('#funcao option[value='+funcionario.funcao.id+']').attr('selected', true);

			funcionario.permissoes.forEach(function(permissao){
				var id = permissao.id;
				console.log(id);
				$('#permissoes option[value='+id+']').attr('selected', true);
			});
			
			$('#modal-funcionarios').modal('show');
		});
	});

	$('.btn-deletar').on('click', function() {
		var id = $(this).parents('tr').data('id');
		var csrf = $('#csrf').val();

		$.ajax({
			url : "funcionarios/" + id,
			type : 'DELETE',
			headers: {'X-CSRF-TOKEN': csrf},
			success : function(result) {
				$('tr[data-id="' + id + '"]').remove();
			    $.bootstrapGrowl("Funcionário excluído com sucesso!", { type:'success' ,align:'center'});
			}, 
			fail: function(result) {
			    $.bootstrapGrowl("Erro ao excluir Funcionário!", { type:'danger' ,align:'center'});
			}
		});
	});
}
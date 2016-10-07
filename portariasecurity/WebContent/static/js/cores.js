$(document).ready(function() {
	aplicarListeners();
	aplicatListenerBtnSalvar();
});

var aplicatListenerBtnSalvar = function() {
	$('#btn-salvar').on('click', function() {
		var url = 'cores';
		var dados = $('#form-cores').serialize();
		$.post(url, dados).done(function(pagina) {
			$('#secao-cores').html(pagina)
			aplicarListeners();
		}).fail(function() {
		    $.bootstrapGrowl("Erro ao cadastrar a Cor!", { type:'danger' ,align:'center'});
		}).always(function() {
			$('#modal-cores').modal('hide');
		});
	});
}

var limparModal = function() {
	$('#id').val('');
	$('#descricao').val('');
};

var aplicarListeners = function() {
	$("select.special-flexselect").flexselect({ hideDropdownOnEmptyInput: true });
	$('#modal-cores').on('hide.bs.modal', limparModal);

	$('.btn-editar').on('click', function() {
		var id = $(this).parents('tr').data('id');
		var url = 'cores/' + id;

		$.get(url).success(function(cor) {
			$('#id').val(cor.id);
			$('#descricao').val(cor.descricao);
			$('#modal-cores').modal('show');
		});
	});

	$('.btn-deletar').on('click', function() {
		var id = $(this).parents('tr').data('id');
		var csrf = $('#csrf').val();

		$.ajax({
			url : "cores/" + id,
			type : 'DELETE',
			headers: {'X-CSRF-TOKEN': csrf},
			success : function(result) {
				$('tr[data-id="' + id + '"]').remove();
			    $.bootstrapGrowl("Cor excluída com sucesso!", { type:'success' ,align:'center'});
			}, 
			fail: function(result) {
			    $.bootstrapGrowl("Erro ao excluir a Cor!", { type:'danger' ,align:'center'});
			}
		});
	});
  	$('#btn-procurar').on('click', function() {
		var corFiltro = $('#corFiltro').val();
		
		$.ajax({
			url : "coresFiltrar?corFiltro="+corFiltro,
			type : 'GET',
			headers: {'X-CSRF-TOKEN': csrf},
			success : function(data) {
				$('#secao-cores').html(data),
				aplicarListeners();
			    $.bootstrapGrowl("Cor(es) filtradas com sucesso!", { type:'success' ,align:'center'});
			}, 
			fail: function(data) {
			    $.bootstrapGrowl("Cor(es) não encontrada(s)!", { type:'danger' ,align:'center'});
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
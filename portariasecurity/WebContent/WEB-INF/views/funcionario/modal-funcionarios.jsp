<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="modal fade" id="modal-funcionarios" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<form id="form-funcionarios" method="post">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">Informações do Usuario</h4>
				</div>
				<div class="modal-body">
					<label for="nome">Nome: </label>
					<input type="text" maxlength="100" id="nome" name="nome" class="form-control">

					<label for="login">Login: </label>
					<input type="text" maxlength="40" id="login" name="login" class="form-control">

					<label for="senha">Senha: </label>
					<input type="password" id="senha" name="senha" class="form-control">

					<label for="confirmacaoSenha">Confirmação Senha: </label>
					<input type="password" id="confirmacaoSenha" name="confirmacaoSenha" class="form-control" placeholder="Uso exclusivo para cadastrar ou alterar senha">
 
					<label for="funcao">Funcao: </label>
					<select id="funcao" name="funcao" class="form-control">
						<c:forEach items="${funcoes}" var="funcao">
							<option value="${funcao.id}">${funcao.descricao}</option>
						</c:forEach>
					</select>
					
					<label for="permissoes">Permisssões: </label>
					<select id="permissoes" name="permissoes" class="form-control" multiple="multiple">
						<c:forEach items="${permissoes}" var="permissao">
							<option value="${permissao.id}">${permissao.nome}</option>
						</c:forEach>
					</select>
															
					<input id="id" name="id" type="hidden">
					<input id="condominio" name="condominio" type="hidden" value="1">
					<input id="csrf" name="_csrf" type="hidden" value="${_csrf.token}">
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
					<button id="btn-salvar" type="button" class="btn btn-primary">Salvar Informações</button>
				</div>
			</form>
		</div>
	</div>
</div>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<c:if test="${not empty msgTxt}">
	<script type="text/javascript">
		$(document).ready(function() { 
			$(function() {
			   $.bootstrapGrowl("${msgTxt}", { type:"${msgTipo}" ,align:'center'});
			});
		});
	</script>
</c:if>
<tr>
	<td colspan="6">
		<button type="button" class="btn btn-primary" data-toggle="modal"
			data-target="#modal-funcionarios">Cadastrar Usuario</button>
	</td>
</tr>
<table class="table table-hover table-condensed table-striped table-bordered">
	<thead>
		<tr>
			<th style="width: 5%">#</th>
			<th style="width: 30%">Nome</th>
			<th style="width: 15%">Login</th>
			<th style="width: 10%">Funcao</th>
			<th style="width: 10%">Permissões</th>
			<th style="width: 10%">Ações</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${funcionarios}" var="funcionario">
			<tr data-id="${funcionario.id}">
				<td>${funcionario.id}</td>
				<td>${funcionario.nome}</td>
				<td>${funcionario.login}</td>
				<td>${funcionario.funcao.descricao}</td>
				<td>
				<c:forEach items="${funcionario.permissoes}" var="permissoes">
					${permissoes.nome}<br>
				</c:forEach>
				</td>
				<td>
					<button type="button" class="btn btn-warning btn-xs btn-editar">Editar</button>
					<button type="button" class="btn btn-danger btn-xs btn-deletar">Deletar</button>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
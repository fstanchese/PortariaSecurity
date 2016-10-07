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
	<td colspan="4">
		<button type="button" class="btn btn-primary" data-toggle="modal"
			data-target="#modal-moradortipos">Cadastrar Tipo de Morador</button>
	</td>
</tr>
<table class="table table-hover table-condensed table-striped table-bordered">
	<thead>
		<tr>
			<th style="width: 10%">#</th>
			<th style="width: 80%">Descrição</th>
			<th style="width: 10%">Ações</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${moradortipos}" var="moradortipo">
			<tr data-id="${moradortipo.id}">
				<td>${moradortipo.id}</td>
				<td>${moradortipo.descricao}</td>
				<td>
					<button type="button" class="btn btn-warning btn-xs btn-editar">Editar</button>
					<button type="button" class="btn btn-danger btn-xs btn-deletar">Deletar</button>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
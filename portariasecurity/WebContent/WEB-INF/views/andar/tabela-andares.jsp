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

<p>
<a href="#" id="filtrarControle" data-element="#filtrar">Exibir/Ocultar filtros</a>
</p>
<div id='filtrar' style='display: block'>
<form class="form-horizontal">
	<div class="col-md-12">
		<div class="row">
		<div class="form-group col-md-2">
			<label for="andarFiltro">Andares : </label> 
			<select id="andarFiltro" name="andarFiltro" tabindex="2" class="form-control special-flexselect">
				<option value=""></option>
				<c:forEach items="${andaresFiltro}" var="andarFiltro">
					<option value="${andarFiltro.descricao}">${andarFiltro.descricao}</option>
				</c:forEach>
			</select>
		</div>
		</div>
	</div>
	<div class="col-md-12">
		<div class="row">
		<div class="form-group col-md-2">
			<button id="btn-procurar" class="btn btn-primary" type="button"	>
				<span class="glyphicon glyphicon-search"> Filtrar</span>
			</button>
		</div>			
		</div>
	</div>
</form>
</div>		
<tr>
	<td colspan="4">
		<button type="button" class="btn btn-primary" data-toggle="modal"
			data-target="#modal-andares">Cadastrar Andar</button>
	</td>
</tr>
<c:if test="${not empty andares}">
<table class="table table-hover table-condensed table-striped table-bordered">
	<thead>
		<tr>
			<th style="width: 10%">#</th>
			<th style="width: 80%">Descrição</th>
			<th style="width: 10%">Ações</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${andares}" var="andar">
			<tr data-id="${andar.id}">
				<td>${andar.id}</td>
				<td>${andar.descricao}</td>
				<td>
					<button type="button" class="btn btn-warning btn-xs btn-editar">Editar</button>
					<button type="button" class="btn btn-danger btn-xs btn-deletar">Deletar</button>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
</c:if>

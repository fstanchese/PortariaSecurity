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
		<div class="form-group col-md-6">
			<label for="moradorFiltro">Moradores : </label> 
			<select id="moradorFiltro" name="moradorFiltro" tabindex="2" class="form-control special-flexselect">
				<option value=""></option>
				<c:forEach items="${moradoresFiltro}" var="moradorFiltro">
					<option value="${moradorFiltro.nome}">${moradorFiltro.nome}</option>
				</c:forEach>
			</select>
		</div>
		<div class="form-group col-md-2">
			<label for="unidadeFiltro">Unidades : </label> 
			<select id="unidadeFiltro" name="unidadeFiltro" tabindex="2" class="form-control special-flexselect">
				<option value=""></option>
				<c:forEach items="${unidadesFiltro}" var="unidadeFiltro">
					<option value="${unidadeFiltro.descricao}">${unidadeFiltro.descricao}</option>
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
	<td colspan="10">
		<button type="button" class="btn btn-primary" data-toggle="modal"
			data-target="#modal-moradores">Cadastrar Morador</button>
	</td>
</tr>
<c:if test="${not empty moradores}">
<table class="table table-hover table-condensed table-striped table-bordered">
	<thead>
		<tr>
			<th style="width: 03%">#</th>
			<th style="width: 30%; ">Morador</th>
			<th style="width: 10%; ">Unidade</th>
			<th style="width: 10%; ">CPF</th>
			<th style="width: 10%; ">Documento</th>
			<th style="width: 10%; ">Celular</th>
			<th style="width: 10%; ">Data Nascto</th>
			<th style="width: 10%; ">Ações</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${moradores}" var="morador">
			<tr data-id="${morador.id}">
				<td>${morador.id}</td>
				<td>${morador.nome }</td>
				<td>${morador.unidade.descricao}</td>
				<td>${morador.cpf}</td>
				<td>${morador.documento }</td>
				<td>${morador.celular }</td>
				<td><fmt:formatDate value="${morador.datanascto}" pattern="dd/MM/yyyy" /></td>
				<td>
					<button type="button" class="btn btn-warning btn-xs btn-editar">Editar</button>
					<button type="button" class="btn btn-danger btn-xs btn-deletar">Deletar</button>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
</c:if>

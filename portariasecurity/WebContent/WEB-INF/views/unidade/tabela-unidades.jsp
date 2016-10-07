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
		<div class="form-group col-md-3">
			<label for="unidadeFiltro">Unidades : </label> 
			<select id="unidadeFiltro" name="unidadeFiltro" tabindex="2" class="form-control special-flexselect">
				<option value=""></option>
				<c:forEach items="${unidadesFiltro}" var="unidadeFiltro">
					<option value="${unidadeFiltro.descricao}">${unidadeFiltro.descricao}</option>
				</c:forEach>
			</select>
		</div>
		<div class="form-group col-md-2">
			<label for="moradorTipoFiltro">Tipo Morador : </label> 
			<select id="moradorTipoFiltro" name="moradorTipoFiltro" class="form-control">
				<option value=""></option>
				<c:forEach items="${moradorTipos}" var="moradorTipo">
					<option value="${moradorTipo.descricao}">${moradorTipo.descricao}</option>
				</c:forEach>
			</select>
		</div>
		<div class="form-group col-md-2">
			<label for="unidadeTipoFiltro">Tipo Unidade : </label> 
			<select id="unidadeTipoFiltro" name="unidadeTipoFiltro" class="form-control">
				<option value=""></option>
				<c:forEach items="${unidadeTipos}" var="unidadeTipo">
					<option value="${unidadeTipo.descricao}">${unidadeTipo.descricao}</option>
				</c:forEach>
			</select>
		</div>
		<div class="form-group col-md-2">
			<label>Bloco: </label> 
			<select id="blocoFiltro" name="blocoFiltro" class="form-control">
				<option value="">-</option>
				<c:forEach items="${blocos}" var="bloco">
					<option value="${bloco.descricao}">${bloco.descricao}</option>
				</c:forEach>
			</select>		
		</div>
		<div class="form-group col-md-2">
			<label>Andar: </label> 
			<select id="andarFiltro" name="andarFiltro" class="form-control">
				<option value="">-</option>
				<c:forEach items="${andares}" var="andar">
					<option value="${andar.descricao}">${andar.descricao}</option>
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
			data-target="#modal-unidades">Cadastrar Unidade</button>
	</td>
</tr>

<c:if test="${not empty unidades}">
<table id="unidades" class="table table-hover table-condensed table-striped table-bordered">
	<thead>
		<tr>
			<th style="width: 3%">#</th>
			<th style="width: 20%" align=center>Descrição</th>
			<th style="width: 10%" align=center>Tipo Morador</th>
			<th style="width: 10%" align=center>Tipo Unidade</th>
			<th style="width: 8%" align=center>Bloco</th>
			<th style="width: 9%" align=center>Andar</th>
			<th style="width: 6%" align=center>Ramal</th>
			<th style="width: 12%" align=center>Telefone</th>
			<th style="width: 5%" align=center>Vagas</th>
			<th style="width: 13%">Ações</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${unidades}" var="unidade">
			<tr data-id="${unidade.id}">
				<td>${unidade.id }</td>
				<td>${unidade.descricao }</td>
				<td>${unidade.moradorTipo.descricao}</td>
				<td>${unidade.unidadeTipo.descricao}</td>
				<td>${unidade.bloco.descricao }</td>
				<td>${unidade.andar.descricao }</td>
				<td>${unidade.ramal}</td>
				<td>${unidade.telefone}</td>
				<td>${unidade.vagas}</td>
				<td>
					<button type="button" class="btn btn-warning btn-xs btn-editar">Editar</button>
					<button type="button" class="btn btn-danger btn-xs btn-deletar">Deletar</button>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
</c:if>

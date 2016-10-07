<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<c:if test="${not empty msgTxt}">
	<script type="text/javascript">
		$(document).ready(function() {
			$(function() {
				$.bootstrapGrowl("${msgTxt}", {
					type : "${msgTipo}",
					align : 'center'
				});
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
		<div class="form-group col-md-2">
			<label for="placaFiltro">Placa : </label> 
			<select id="placaFiltro" name="placaFiltro" tabindex="2" class="form-control special-flexselect">
				<option value=""></option>
				<c:forEach items="${placas}" var="placa">
					<option value="${placa.placa}">${placa.placa}</option>
				</c:forEach>
			</select>
		</div>
		<div class="form-group col-md-3">
			<label>Modelo : </label> 
			<select id="modeloFiltro" name="modeloFiltro" tabindex="2" class="form-control special-flexselect">
				<option value=""></option>
				<c:forEach items="${marcaEModelos}" var="marcaemodelos">
					<option value="${marcaemodelos}">${marcaemodelos}</option>
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
	<th colspan="10">
		<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modal-veiculos">Cadastrar Veiculo</button>
	</th>
</tr>
<c:if test="${not empty veiculos}">
<table class="table table-hover table-condensed table-striped table-bordered">
<thead>

	<tr>
		<th style="width: 5%">#</th>
		<th style="width: width=30%" align=center>Morador</th>
		<th style="width: width=15%" align=center>Unidade</th>
		<th style="width: width=10%" align=center>Placa</th>
		<th style="width: width=15%" align=center>Modelo</th>
		<th style="width: width=10%" align=center>Cor</th>
		<th style="width: width=5%" align=center>Ano</th>
		<th style="width: 10%">Ações</th>
	</tr>
</thead>
<tbody>
	<c:forEach items="${veiculos}" var="veiculo">
		<tr data-id="${veiculo.id}">
			<td>${veiculo.id }</td>
			<td>${veiculo.morador.nome }</td>
			<td>${veiculo.morador.unidade.descricao}</td>
			<td>${veiculo.placa}</td>
			<td>${veiculo.modelo.marca.descricao}
				${veiculo.modelo.descricao}</td>
			<td>${veiculo.cor.descricao}</td>
			<td>${veiculo.ano}</td>
			<td>
				<button type="button" class="btn btn-warning btn-xs btn-editar">Editar</button>
				<button type="button" class="btn btn-danger btn-xs btn-deletar">Deletar</button>
			</td>
		</tr>
	</c:forEach>
</tbody>
</table>
</c:if>

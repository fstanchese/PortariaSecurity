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
<!-- <p> -->
<!-- <a href="#" id="filtrarControle" data-element="#filtrar">Exibir/Ocultar filtros</a> -->
<!-- </p> -->
<!-- <div id='filtrar' style='display: block'> -->
<%-- <form class="form-horizontal"> --%>
<!-- 	<div class="col-md-12"> -->
<!-- 		<div class="row"> -->
<!-- 		<div class="form-group col-md-2"> -->
<!-- 			<label for="unidadeFiltro">Unidades : </label>  -->
<!-- 			<select id="unidadeFiltro" name="unidadeFiltro" tabindex="2" class="form-control special-flexselect"> -->
<!-- 				<option value=""></option> -->
<%-- 				<c:forEach items="${unidadesFiltro}" var="unidadeFiltro"> --%>
<%-- 					<option value="${unidadeFiltro.descricao}">${unidadeFiltro.descricao}</option> --%>
<%-- 				</c:forEach> --%>
<!-- 			</select> -->
<!-- 		</div> -->
<!-- 		<div class="form-group col-md-2"> -->
<!-- 			<label for="placaFiltro">Placa : </label>  -->
<!-- 			<select id="placaFiltro" name="placaFiltro" tabindex="2" class="form-control special-flexselect"> -->
<!-- 				<option value=""></option> -->
<%-- 				<c:forEach items="${placas}" var="placa"> --%>
<%-- 					<option value="${placa.placa}">${placa.placa}</option> --%>
<%-- 				</c:forEach> --%>
<!-- 			</select> -->
<!-- 		</div> -->
<!-- 		<div class="form-group col-md-3"> -->
<!-- 			<label>Modelo : </label>  -->
<!-- 			<select id="modeloFiltro" name="modeloFiltro" tabindex="2" class="form-control special-flexselect"> -->
<!-- 				<option value=""></option> -->
<%-- 				<c:forEach items="${marcaEModelos}" var="marcaemodelos"> --%>
<%-- 					<option value="${marcaemodelos}">${marcaemodelos}</option> --%>
<%-- 				</c:forEach> --%>
<!-- 			</select>		 -->
<!-- 		</div> -->
<!-- 	</div> -->
<!-- 	</div> -->
<!-- 		<div class="col-md-12"> -->
<!-- 		<div class="row"> -->
<!-- 		<div class="form-group col-md-2"> -->
<!-- 			<button id="btn-procurar" class="btn btn-primary" type="button"	> -->
<!-- 				<span class="glyphicon glyphicon-search"> Filtrar</span> -->
<!-- 			</button> -->
<!-- 		</div>			 -->
<!-- 		</div> -->
<!-- 	</div>	 -->
<%-- </form> --%>
<!-- </div> -->
<tr>
	<th colspan="10">
		<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modal-visitantes">Entrada Visitante</button>
	</th>
</tr>
<c:if test="${not empty visitantes}">
<table class="table table-hover table-condensed table-striped table-bordered">
<thead>
	<tr>
		<th style="width: 5%">#</th>
		<th style="width: width=15%" align=center>Unidade</th>
		<th style="width: width=30%" align=center>Nome Visitante</th>
		<th style="width: width=10%" align=center>Documento</th>
		<th style="width: width=10%" align=center>Placa</th>
		<th style="width: width=15%" align=center>Modelo</th>
		<th style="width: width=10%" align=center>Entrada</th>
		<th style="width: width=10%" align=center>Saida</th>
		<th style="width: 10%">Ações</th>
	</tr>
</thead>
<tbody>
	<c:forEach items="${visitantes}" var="visitante">
		<tr data-id="${visitante.id}">
			<td>${visitante.id }</td>
			<td>${visitante.unidade.descricao }</td>
			<td>${visitante.nome }</td>
			<td>${visitante.documento}</td>
			<td>${visitante.placa}</td>
			<td>${visitante.modelo.marca.descricao}
				${visitante.modelo.descricao}</td>
			<td><fmt:formatDate type="both" value="${visitante.entrada}" /></td>
			<td><fmt:formatDate type="both" value="${visitante.saida}"  /></td>
			<td>
				<button type="button" class="btn btn-danger btn-xs btn-saida">Saida</button>
				<button type="button" class="btn btn-warning btn-xs btn-editar">Editar</button>
			</td>
		</tr>
	</c:forEach>
</tbody>
</table>
</c:if>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="modal fade" id="modal-moradores" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<form id="form-moradores" method="post">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">Informações do Morador</h4>
				</div>
				<div class="modal-body">

					<label for="unidade">Unidade : </label> 
					<select id="unidade" name="unidade" class="form-control">
						<c:forEach var="unidade" items="${unidades}">
							<option value="${unidade.id}">${unidade.descricao}	</option>
						</c:forEach>
					</select>
					
					<label>Nome : </label> 
					<input id="nome" type=text size="80" maxlength="100" name="nome" class="form-control" value="${morador.nome}" />

					<label>CPF : </label> 
					<input id="cpf" type=text maxlength="11" size="11" name="cpf" class="form-control" value="${morador.cpf}" />

					<label>Documento: </label> 
					<input id="documento" type="text" maxlength="20" size="20" name="documento" class="form-control" value="${morador.documento}" />

					<label>Celular: </label> 
					<input id="celular" type="text" maxlength="15" size="12" name="celular" class="form-control" value="${morador.celular}" />

					<label>Data de Nascto: </label> 
					<input id="datanascto" type="text" size="10" name="datanascto" class="form-control" value="${morador.datanascto}"/>

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
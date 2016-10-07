<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="modal fade" id="modal-unidades" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<form id="form-unidades" method="post">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">Informações da Unidade</h4>
				</div>
				<div class="modal-body">

					<label for="moradorTipo">Tipo Morador : </label> 
					<select id="moradorTipo" name="moradorTipo" class="form-control">
					<c:forEach var="moradorTipo" items="${moradorTipos}">
						<option value="${moradorTipo.id}">${moradorTipo.descricao}</option>
					</c:forEach>
					</select>
					<label for="unidadeTipo">Tipo Unidade : </label> 
					<select id="unidadeTipo" name="unidadeTipo" class="form-control">
					<c:forEach var="unidadeTipo" items="${unidadeTipos}">
						<option value="${unidadeTipo.id}">${unidadeTipo.descricao}</option>
					</c:forEach>
					</select>
					
					<div id='bloco' style='display: block'>
						<label>Bloco: </label> 
						<select id="bloco" name="bloco" class="form-control">
							<option value="">-</option>
							<c:forEach var="bloco" items="${blocos}">
								<option value="${bloco.id}">${bloco.descricao}</option>
							</c:forEach>
						</select>
						<label>Andar: </label> 
						<select id="andar" name="andar" class="form-control">
							<option value="">-</option>
							<c:forEach var="andar" items="${andares}">
								<option value="${andar.id}">${andar.descricao}</option>
							</c:forEach>
						</select>
					</div>
					<label>Descrição : </label> 
					<input type=text id="descricao" maxlength="20" name="descricao"  class="form-control" value="${unidade.descricao}" />

					<label>Ramal : </label> 
					<input type=text id="ramal" maxlength="4" size="3" name="ramal"  class="form-control" value="${unidade.ramal}" />

					<label for="telefone">Telefone Fixo: </label> 
					<input type="tel" class="form-control" name="telefone" id="telefone" maxlength="15" value="${unidade.telefone}">

					<label>Vagas : </label> 
					<input type=text id="vagas" maxlength="1" size="1" min="0" max="9" name="vagas"  class="form-control" value="${unidade.vagas}" />

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
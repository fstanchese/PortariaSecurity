<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="modal fade" id="modal-veiculos" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<form id="form-veiculos" method="post">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">Informações do Veículo</h4>
				</div>
				<div class="modal-body">
					<label for="morador">Morador : </label> 
					<select id="morador" name="morador" class="form-control">
						<c:forEach var="morador" items="${moradores}">
							<option value="${morador.id}">${morador.nome}</option>
						</c:forEach>
					</select>
					
					<label>Placa : </label> 
					<input id="placa" style="text-transform: uppercase" type=text maxlength="10" size="10" name="placa" class="form-control" value="${veiculo.placa}" />

					<label for="marca">Marca : </label> 
					<select id="marca" name="marca" class="form-control">
						<option value="" 	>-- Selecione --</option>
						<c:forEach var="marca" items="${marcas}">
							<option value="${marca.id}">${marca.descricao}</option>
						</c:forEach>
					</select>
	
					<label for="modelo">Modelo : </label> 
					<select id="modelo"  name="modelo" class="form-control">
							<option value="" 	>-- Selecione --</option>
							<c:forEach var="modelo" items="${modelos}">
								<option value="${modelo.id}">${modelo.descricao}</option>
							</c:forEach>			
					</select>
				
					<label for="cor">Cor : </label> 
					<select id="cor" name="cor" class="form-control">
						<c:forEach var="cor" items="${cores}">
							<option value="${cor.id}">${cor.descricao}</option>
						</c:forEach>
					</select>
					
					<label>Ano : </label> 
					<input id="ano" type=text maxlength="4" size="4" name="ano" class="form-control" value="${veiculo.ano}" />

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
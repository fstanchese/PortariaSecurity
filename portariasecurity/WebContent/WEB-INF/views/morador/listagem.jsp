<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>${titulo}</title>
		<c:set var="path" value="${pageContext.request.contextPath}" scope="request"/>
		<style type="text/css">
			@IMPORT url("${path}/static/bootstrap/css/bootstrap.min.css");
			@IMPORT url("${path}/static/bootstrap/css/bootstrap-theme.min.css");
			@IMPORT url("${path}/static/bootstrap/css/style.css");
			@IMPORT url("${path}/static/bootstrap/css/flexselect.css");
		</style>
	</head>
	<body>
		<div class="container">
			<jsp:include page="../cabecalho.jsp"></jsp:include>
			<section id="secao-moradores">
				<jsp:include page="tabela-moradores.jsp"/>
			</section>
			
			<jsp:include page="modal-moradores.jsp"/>
		</div>
		<script type="text/javascript" src="${path}/static/js/jquery.min.js"></script>
		<script type="text/javascript" src="${path}/static/bootstrap/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="${path}/static/js/jquery.bootstrap-growl.min.js"></script>
		<script type="text/javascript" src="${path}/static/js/moradores.js"></script>
		<script type="text/javascript" src="${path}/static/js/jquery.maskedinput-1.3.1.min.js" ></script>
		<script type="text/javascript" src="${path}/static/js/liquidmetal.js" ></script>
		<script type="text/javascript" src="${path}/static/js/jquery.flexselect.js" ></script>	
	</body>
</html>
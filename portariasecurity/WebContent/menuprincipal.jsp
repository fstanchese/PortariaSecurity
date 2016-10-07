<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="path" value="${pageContext.request.contextPath}" scope="request" />
<html>
<head>
<style type="text/css">
@IMPORT url("${path}/static/bootstrap/css/bootstrap.min.css");
@IMPORT url("${path}/static/bootstrap/css/bootstrap-theme.min.css");
@IMPORT url("${path}/static/bootstrap/css/style.css");
</style>
<meta charset="UTF-8">
<title>Menu Principal</title>
</head>
<body>
	<c:import url="/WEB-INF/views/cabecalho.jsp" />
	<script type="text/javascript" src="${path}/static/js/jquery.min.js"></script>
	<script type="text/javascript" src="${path}/static/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>

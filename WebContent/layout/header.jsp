<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="icon"
	href="${pageContext.request.contextPath }/assets/imgs/coder.png">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/assets/css/custom.css">
</head>
<body>
	<c:set var="BASE_URL" value="http://localhost:8080/E-Commerce/" />
	<c:import url="layout/nav.jsp" />
	<c:import url="layout/sidebar.jsp" />
	<div class="container my-5">
		<c:if test="${!empty msgError}">
			<div class="alert alert-danger alert-dismissible fade show"
				role="alert">
				<strong class="text-center">${msgError }</strong>
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<%
				request.removeAttribute("msgError");
			%>
		</c:if>

		<c:if test="${!empty msgSuccess}">
			<div class="alert alert-success alert-dismissible fade show"
				role="alert">
				<strong class="text-center">${msgSuccess }</strong>
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<%
				request.removeAttribute("msgSuccess");
			%>
		</c:if>
	</div>
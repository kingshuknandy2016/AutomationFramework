<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Insert title here</title>
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
		<spring:url value="/WebContent/WEB-INF/resources/registration.js" var="registrationJS" />

		<script type="text/javascript" src="${registrationJS}"></script>
	</head>
	<body>
		<h1>Successful</h1>

		<p>If you click on me, I will disappear.</p>
		<p>Click me away!</p>
		<p>Click me too!</p>
		
		
		<p>${name}</p>

	</body>
</html>

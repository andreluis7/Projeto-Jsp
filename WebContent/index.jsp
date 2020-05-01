<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Bem vindo ao curso de jsp!</h1>
	<%= "seu sucesso garantido!" %>

	<form action="receber-nome.jsp">
	<input type="text" id="nome" name="nome" >
	<input type="submit" value="Enviar">	
	</form>
	
	<% session.setAttribute("curso", "curso de jsp"); %>
	
</body>
</html>
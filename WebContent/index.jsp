<jsp:useBean id="calcula" class="beans.BeanCursoJsp" type="beans.BeanCursoJsp" scope="page"/>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="myprefix" uri="WEB-INF/testetag.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
	<form action="LoginServlet" method="post">
	Login: 
	<input type="text" id="login" name="login">
	<br/>
	Senha:
	<input type="text" id="senha" name="senha">
	<br/>
	
	<input type="submit" value="Logar">
	</form>
	
	
</body>
</html>
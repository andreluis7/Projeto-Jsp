<jsp:useBean id="calcula" class="beans.BeanCursoJsp"
	type="beans.BeanCursoJsp" scope="page" />
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="myprefix" uri="WEB-INF/testetag.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="resources/css/estilo.css" rel="stylesheet" />
</head>
<body>
	<div class="login-page">
		<div class="form">
			<form action="LoginServlet" method="post">
				Login: <input type="text" id="login" name="login" placeholder = "Informe o login"> <br />
				Senha: <input type="password" id="senha" name="senha" placeholder = "Informe a senha"> <br />

				<button type="submit" value="Logar">Logar</button>
			</form>
		</div>
	</div>
</body>
</html>
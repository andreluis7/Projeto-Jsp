<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Cadastro de Usuários</h1>

	<form action="salvarUsuario" method="post">
		<table>
			<tr>
				<td>Codigo:</td>
				<td><input type="text" readonly="readonly" id="id" name="id" value="${user.id}"></td>
			</tr>
			<tr>
				<td>Login:</td>
				<td><input type="text" id="login" name="login"
					value="${user.login}"></td>
			</tr>
			<tr>
				<td>Senha:</td>
				<td><input type="password" id="senha" name="senha"
					value="${user.senha}"></td>
			</tr>
		</table>
		<input type="submit" value="Salvar">
	</form>

	<table>

		<c:forEach items="${usuarios}" var="user">
			<tr>
				<td style="widht: 150px"><c:out value="${user.id}"></c:out></td>
				<td style="widht: 150px"><c:out value="${user.login}"></c:out></td>
				<td><c:out value="${user.senha}"></c:out></td>

				<td><a href="salvarUsuario?acao=delete&user=${user.login}">Excluir</a></td>
				<td><a href="salvarUsuario?acao=editar&user=${user.login}">Editar</a></td>
			</tr>
		</c:forEach>

	</table>

</body>
</html>
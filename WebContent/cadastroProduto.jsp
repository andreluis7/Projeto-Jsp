<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="resources/css/cadastro.css" rel="stylesheet">
<title>Cadastro de Produtos</title>
</head>
<body>
	<h2 class="center">Cadastro de Produtos</h2>
	<h3 class="center" style="color: orange"> ${msg}</h3>
	<form action="salvarProduto" method="post" id="formProduto">
		<ul class="form-style-1">
			<li>
				<table>
					<tr>
						<td>Codigo:</td>
						<td><input type="text" readonly="readonly" id="codigo" name="codigo"
							value="${produto.codigo}"></td>
					</tr>
					<tr>
						<td>Nome:</td>
						<td><input type="text" id="nome" name="nome"
							value="${produto.nome}"></td>
					</tr>
					<tr>
						<td>Quantidade:</td>
						<td><input type="text" id="quantidade" name="quantidade"
							value="${produto.quantidade}"></td>
					</tr>
					<tr>
						<td>Valor:</td>
						<td><input type="text" id="valor" name="valor"
							value="${produto.valor}"></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value="Salvar" style="margin-right: 5px"><input
							type="submit" value="Cancelar"
							onclick="document.getElementById('formProduto').action = 'salvarProduto?acao=reset'"></td>
					</tr>
				</table>
			</li>
		</ul>
	</form>
	<div class="container">
		<table class="responsive-table">
			<caption>Produtos Cadastrados</caption>
			<tbody>
				<tr>
					<th scope="col">C�digo</th>
					<th scope="col">Nome</th>
					<th scope="col">Quantidade</th>
					<th scope="col">Valor</th>
					<th scope="col">Editar</th>
					<th scope="col">Excluir</th>
				</tr>

				<c:forEach items="${produtos}" var="produto">
					<tr>
						<td style="widht: 150px"><c:out value="${produto.codigo}"></c:out></td>
						<td style="widht: 150px"><c:out value="${produto.nome}"></c:out></td>
						<td><c:out value="${produto.quantidade}"></c:out></td>
						<td><c:out value="${produto.valor}"></c:out></td>
						
						<td><a href="salvarProduto?acao=editar&produto=${produto.codigo}"><img
								src="resources/img/editar.png" width="20px" height="20px"
								title="Editar" alt="Editar"></a></td>
						<td><a href="salvarProduto?acao=delete&produto=${produto.codigo}"><img
								src="resources/img/excluir.png" width="20px" height="20px"
								title="Excluir" alt="Excluir"></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>
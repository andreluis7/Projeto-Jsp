<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="resources/css/cadastro.css" rel="stylesheet">

<title>Cadastro de Telefone</title>
</head>
<body>
	<a href="acessoliberado.jsp"><img alt="Inicio" title="Inicio"
		src="resources/img/home.png" width="30" height="30"></a>
	<a href="index.jsp"><img alt="Sair" title="Sair"
		src="resources/img/exit.png" width="30" height="30"></a>
	<h2 class="center">Cadastro de Telefone</h2>
	<h3 class="center" style="color: orange">${msg}</h3>
	<form action="salvarTelefone" method="post" id="formTelefone"
		onsubmit="return validarCampos() ? true:false">
		<ul class="form-style-1">
			<li>
				<table>
					<tr>
						<td>User:</td>
						<td><input type="text" readonly="readonly" id="id" name="id"
							value="${userEscolhido.id}"></td>

						<td><input type="text" id="nome" name="nome"
							readonly="readonly" value="${userEscolhido.nome}"></td>
					</tr>
					<tr>
						<td>Número:</td>
						<td><input type="text" id="numero" name="numero"
							value="${telefone.numero}"
							placeholder="Informe o número do telefone"></td>

						<td><select id="tipo" name="tipo" style="width: 100%">
								<option>Casa</option>
								<option>Contato</option>
								<option>Celular</option>
						</select></td>

					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value="Salvar" style="width: 173px;" />

						</td>
						<td><input type="submit" value="Voltar" style="width: 173px;"
							onclick="document.getElementById('formTelefone').action = 'salvarTelefone?acao=voltar'">
						</td>
					</tr>
				</table>
			</li>
		</ul>
	</form>
	<div class="container">
		<table class="responsive-table">
			<caption>Telefones Cadastrados</caption>
			<tbody>
				<tr>
					<th scope="col">ID</th>
					<th scope="col">Número</th>
					<th scope="col">Tipo</th>
					<th scope="col">Excluir</th>
				</tr>

				<c:forEach items="${telefones}" var="telefone">
					<tr>
						<td><c:out value="${telefone.id}"></c:out></td>
						<td><c:out value="${telefone.numero}"></c:out></td>
						<td><c:out value="${telefone.tipo}"></c:out></td>

						<td><a
							href="salvarTelefone?acao=deleteFone&telefoneId=${telefone.id}"
							onclick="return confirm('Deseja confirmar a exclusão?');"><img
								src="resources/img/excluir.png" width="20px" height="20px"
								title="Excluir" alt="Excluir"></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<script type="text/javascript">
		function validarCampos() {
			if (document.getElementById('formUser').action != 'salvarTelefones?acao=voltar') {
				if (document.getElementById("numero").value == '') {
					alert('Informe o Número!');
					return false;
				}
				if (document.getElementById("tipo").value == '') {
					alert('Informe o Tipo!');
					return false;
				}
			}
			return true;
		}
	</script>
</body>
</html>
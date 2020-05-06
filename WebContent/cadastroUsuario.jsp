<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="resources/css/cadastro.css" rel="stylesheet">

<!-- Adicionando JQuery -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"
	integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
	crossorigin="anonymous"></script>

<title>Cadastro de Usuário</title>
</head>
<body>
	<a href="acessoliberado.jsp">Início</a>
	<a href="index.jsp">Sair</a>
	<h2 class="center">Cadastro de Usuario</h2>
	<h3 class="center" style="color: orange">${msg}</h3>
	<form action="salvarUsuario" method="post" id="formUser"
		onsubmit="return validarCampos() ? true:false">
		<ul class="form-style-1">
			<li>
				<table>
					<tr>
						<td>Codigo:</td>
						<td><input type="text" readonly="readonly" id="id" name="id"
							value="${user.id}"></td>
					</tr>
					<tr>
						<td>Login:</td>
						<td><input type="text" id="login" name="login"
							value="${user.login}"></td>

						<td>Senha:</td>
						<td><input type="password" id="senha" name="senha"
							value="${user.senha}"></td>
					</tr>
					<tr>
						<td>Nome:</td>
						<td><input type="text" id="nome" name="nome"
							value="${user.nome}"></td>

						<td>Telefone:</td>
						<td><input type="text" id="telefone" name="telefone"
							value="${user.telefone}"></td>
					</tr>
					<tr>
						<td>Cep:</td>
						<td><input type="text" id="cep" name="cep"
							value="${user.cep}"></td>

						<td>Ibge:</td>
						<td><input type="text" id="ibge" name="ibge"
							value="${user.ibge}"></td>
					</tr>
					<tr>
						<td>Rua:</td>
						<td><input type="text" id="rua" name="rua"
							value="${user.rua}"></td>

						<td>Bairro:</td>
						<td><input type="text" id="bairro" name="bairro"
							value="${user.bairro}"></td>
					</tr>
					<tr>
						<td>Cidade:</td>
						<td><input type="text" id="cidade" name="cidade"
							value="${user.cidade}"></td>

						<td>Estado:</td>
						<td><input type="text" id="estado" name="estado"
							value="${user.estado}"></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value="Salvar"
							style="margin-right: 5px"><input type="submit"
							value="Cancelar"
							onclick="document.getElementById('formUser').action = 'salvarUsuario?acao=reset'"></td>
					</tr>
				</table>
			</li>
		</ul>
	</form>
	<div class="container">
		<table class="responsive-table">
			<caption>Usuarios Cadastrados</caption>
			<tbody>
				<tr>
					<th scope="col">ID</th>
					<th scope="col">Login</th>
					<th scope="col">Nome</th>
					<th scope="col">Telefone</th>
					<th scope="col">Cep</th>
					<th scope="col">Rua</th>
					<th scope="col">Bairro</th>
					<th scope="col">Cidade</th>
					<th scope="col">Estado</th>
					<th scope="col">Ibge</th>
					<th scope="col">Telefone</th>
					<th scope="col">Editar</th>
					<th scope="col">Excluir</th>
				</tr>

				<c:forEach items="${usuarios}" var="user">
					<tr>
						<td><c:out value="${user.id}"></c:out></td>
						<td><c:out value="${user.login}"></c:out></td>
						<td><c:out value="${user.nome}"></c:out></td>
						<td><c:out value="${user.telefone}"></c:out></td>
						<td><c:out value="${user.cep}"></c:out></td>
						<td><c:out value="${user.rua}"></c:out></td>
						<td><c:out value="${user.bairro}"></c:out></td>
						<td><c:out value="${user.cidade}"></c:out></td>
						<td><c:out value="${user.estado}"></c:out></td>
						<td><c:out value="${user.ibge}"></c:out></td>

						<td><a href="salvarTelefone?acao=addFone&user=${user.id}"><img
								src="resources/img/telefone.png" width="20px" height="20px"
								title="Telefones" alt="Telefones"></a></td>
						<td><a href="salvarUsuario?acao=editar&user=${user.id}"><img
								src="resources/img/editar.png" width="20px" height="20px"
								title="Editar" alt="Editar"></a></td>
						<td><a href="salvarUsuario?acao=delete&user=${user.id}"><img
								src="resources/img/excluir.png" width="20px" height="20px"
								title="Excluir" alt="Excluir"></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<script type="text/javascript">
		function validarCampos() {
			if (document.getElementById("login").value == '') {
				alert('Informe o Login');
				return false;
			} else if (document.getElementById("nome").value == '') {
				alert('Informe o Nome');
				return false;
			} else if (document.getElementById("senha").value == '') {
				alert('Informe a Senha');
				return false;
			} else if (document.getElementById("telefone").value == '') {
				alert('Informe o Telefone');
				return false;
			}

			return true;
		}
		
		<!-- Adicionando Javascript Cep-->
			$(document).ready(
					function() {

						function limpa_formulário_cep() {
							// Limpa valores do formulário de cep.
							$("#rua").val("");
							$("#bairro").val("");
							$("#cidade").val("");
							$("#estado").val("");
							$("#ibge").val("");
						}

						//Quando o campo cep perde o foco.
						$("#cep").blur(
								function() {

									//Nova variável "cep" somente com dígitos.
									var cep = $(this).val().replace(/\D/g, '');

									//Verifica se campo cep possui valor informado.
									if (cep != "") {

										//Expressão regular para validar o CEP.
										var validacep = /^[0-9]{8}$/;

										//Valida o formato do CEP.
										if (validacep.test(cep)) {

											//Preenche os campos com "..." enquanto consulta webservice.
											$("#rua").val("...");
											$("#bairro").val("...");
											$("#cidade").val("...");
											$("#estado").val("...");
											$("#ibge").val("...");

											//Consulta o webservice viacep.com.br/
											$.getJSON("https://viacep.com.br/ws/" + cep
													+ "/json/?callback=?", function(
													dados) {

												if (!("erro" in dados)) {
													//Atualiza os campos com os valores da consulta.
													$("#rua").val(dados.logradouro);
													$("#bairro").val(dados.bairro);
													$("#cidade").val(dados.localidade);
													$("#estado").val(dados.uf);
													$("#ibge").val(dados.ibge);
												} //end if.
												else {
													//CEP pesquisado não foi encontrado.
													limpa_formulário_cep();
													alert("CEP não encontrado.");
												}
											});
										} //end if.
										else {
											//cep é inválido.
											limpa_formulário_cep();
											alert("Formato de CEP inválido.");
										}
									} //end if.
									else {
										//cep sem valor, limpa formulário.
										limpa_formulário_cep();
									}
								});
					});
		</script>
</body>
</html>
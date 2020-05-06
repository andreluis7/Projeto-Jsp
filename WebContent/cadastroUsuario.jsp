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

<title>Cadastro de Usu�rio</title>
</head>
<body>
	<a href="acessoliberado.jsp">In�cio</a>
	<a href="index.jsp">Sair</a>
	<h2 class="center">Cadastro de Usuario</h2>
	<h3 class="center" style="color: orange">${msg}</h3>
	<form action="salvarUsuario" method="post" id="formUser"
		onsubmit="return validarCampos() ? true:false;"
		enctype="multipart/form-data">
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
							value="${user.login}" placeholder="Informe o login"></td>

						<td>Senha:</td>
						<td><input type="password" id="senha" name="senha"
							value="${user.senha}" placeholder="Informe a senha"></td>
					</tr>
					<tr>
						<td>Nome:</td>
						<td><input type="text" id="nome" name="nome"
							value="${user.nome}" placeholder="Informe o nome"></td>

						<td>Telefone:</td>
						<td><input type="text" id="telefone" name="telefone"
							value="${user.telefone}" placeholder="Informe o telefone"></td>
					</tr>
					<tr>
						<td>Cep:</td>
						<td><input type="text" id="cep" name="cep"
							value="${user.cep}" placeholder="Informe o cep"></td>

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
						<td>Foto</td>
						<td><input type="file" name="foto" value="Foto"></td>
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
					<th scope="col">Foto</th>
					<th scope="col">Nome</th>
					<th scope="col">Telefone</th>
					<th scope="col">Editar</th>
					<th scope="col">Excluir</th>
				</tr>

				<c:forEach items="${usuarios}" var="user">
					<tr>
						<td><c:out value="${user.id}"></c:out></td>
						<td><c:out value="${user.login}"></c:out></td>
						<td><a href="salvarUsuario?acao=download&user=${user.id}"><img
								src='<c:out value="${user.tempFotoUser}"/>' alt="Imagem User"
								title="Imagem User" width="32px" height="32px" /> </a></td>
						<td><c:out value="${user.nome}"></c:out></td>

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

						function limpa_formul�rio_cep() {
							// Limpa valores do formul�rio de cep.
							$("#rua").val("");
							$("#bairro").val("");
							$("#cidade").val("");
							$("#estado").val("");
							$("#ibge").val("");
						}

						//Quando o campo cep perde o foco.
						$("#cep").blur(
								function() {

									//Nova vari�vel "cep" somente com d�gitos.
									var cep = $(this).val().replace(/\D/g, '');

									//Verifica se campo cep possui valor informado.
									if (cep != "") {

										//Express�o regular para validar o CEP.
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
													//CEP pesquisado n�o foi encontrado.
													limpa_formul�rio_cep();
													alert("CEP n�o encontrado.");
												}
											});
										} //end if.
										else {
											//cep � inv�lido.
											limpa_formul�rio_cep();
											alert("Formato de CEP inv�lido.");
										}
									} //end if.
									else {
										//cep sem valor, limpa formul�rio.
										limpa_formul�rio_cep();
									}
								});
					});
		</script>
</body>
</html>
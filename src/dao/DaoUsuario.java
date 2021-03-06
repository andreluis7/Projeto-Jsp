package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.BeanCursoJsp;
import connection.SingleConnection;

public class DaoUsuario {

	private Connection connection;

	public DaoUsuario() {
		connection = SingleConnection.getConnection();
	}

	public void salvar(BeanCursoJsp beanCursoJsp) {
		try {
			String sql = "insert into usuario (login, senha, nome, telefone, "
					+ "cep, rua, bairro, cidade, estado, ibge, fotobase64, "
					+ "contenttype, curriculobase64, contenttypecurriculo, "
					+ "fotobase64miniatura, ativo, sexo, perfil) "
					+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, beanCursoJsp.getLogin());
			statement.setString(2, beanCursoJsp.getSenha());
			statement.setString(3, beanCursoJsp.getNome());
			statement.setString(4, beanCursoJsp.getTelefone());
			statement.setString(5, beanCursoJsp.getCep());
			statement.setString(6, beanCursoJsp.getRua());
			statement.setString(7, beanCursoJsp.getBairro());
			statement.setString(8, beanCursoJsp.getCidade());
			statement.setString(9, beanCursoJsp.getEstado());
			statement.setString(10, beanCursoJsp.getIbge());
			statement.setString(11, beanCursoJsp.getFotoBase64());
			statement.setString(12, beanCursoJsp.getContentType());
			statement.setString(13, beanCursoJsp.getCurriculoBase64());
			statement.setString(14, beanCursoJsp.getContentTypeCurriculo());
			statement.setString(15, beanCursoJsp.getFotoBase64Miniatura());
			statement.setBoolean(16, beanCursoJsp.isAtivo());
			statement.setString(17, beanCursoJsp.getSexo());
			statement.setString(18, beanCursoJsp.getPerfil());
			statement.execute();
			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();

			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}

	public List<BeanCursoJsp> listarUsuarios(String descricaopesquisa) throws Exception {
		String sql = "select * from usuario where login <> 'admin' and nome like '%" + descricaopesquisa + "%'";
		return consultarUsuarios(sql);
	}
	
	public List<BeanCursoJsp> listarUsuarios() throws Exception {
		String sql = "select * from usuario where login <> 'admin'";
		return consultarUsuarios(sql);
	}

	private List<BeanCursoJsp> consultarUsuarios(String sql) throws SQLException {
		List<BeanCursoJsp> usuarios = new ArrayList<BeanCursoJsp>();
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			BeanCursoJsp beanCursoJsp = new BeanCursoJsp();
			beanCursoJsp.setId(resultSet.getLong("id"));
			beanCursoJsp.setLogin(resultSet.getString("login"));
			beanCursoJsp.setSenha(resultSet.getString("senha"));
			beanCursoJsp.setNome(resultSet.getString("nome"));
			beanCursoJsp.setTelefone(resultSet.getString("telefone"));
			beanCursoJsp.setCep(resultSet.getString("cep"));
			beanCursoJsp.setRua(resultSet.getString("rua"));
			beanCursoJsp.setBairro(resultSet.getString("bairro"));
			beanCursoJsp.setCidade(resultSet.getString("cidade"));
			beanCursoJsp.setEstado(resultSet.getString("estado"));
			beanCursoJsp.setIbge(resultSet.getString("ibge"));
			// beanCursoJsp.setFotoBase64(resultSet.getString("fotobase64"));
			beanCursoJsp.setFotoBase64Miniatura(resultSet.getString("fotobase64miniatura"));
			beanCursoJsp.setContentType(resultSet.getString("contenttype"));
			beanCursoJsp.setCurriculoBase64(resultSet.getString("curriculobase64"));
			beanCursoJsp.setContentTypeCurriculo(resultSet.getString("contenttypecurriculo"));
			beanCursoJsp.setAtivo(resultSet.getBoolean("ativo"));
			beanCursoJsp.setSexo(resultSet.getString("sexo"));
			beanCursoJsp.setPerfil(resultSet.getString("perfil"));
			usuarios.add(beanCursoJsp);
		}
		return usuarios;
	}

	public void delete(String id) {
		try {
			String sql = "delete from usuario where id = '" + id + "' and login <> 'admin'";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.execute();
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public BeanCursoJsp consultar(String id) throws Exception {
		String sql = "select * from usuario where id = '" + id + "' and login <> 'admin'";

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			BeanCursoJsp beanCursoJsp = new BeanCursoJsp();
			beanCursoJsp.setId(resultSet.getLong("id"));
			beanCursoJsp.setLogin(resultSet.getString("login"));
			beanCursoJsp.setSenha(resultSet.getString("senha"));
			beanCursoJsp.setNome(resultSet.getString("nome"));
			beanCursoJsp.setTelefone(resultSet.getString("telefone"));
			beanCursoJsp.setCep(resultSet.getString("cep"));
			beanCursoJsp.setRua(resultSet.getString("rua"));
			beanCursoJsp.setBairro(resultSet.getString("bairro"));
			beanCursoJsp.setCidade(resultSet.getString("cidade"));
			beanCursoJsp.setEstado(resultSet.getString("estado"));
			beanCursoJsp.setIbge(resultSet.getString("ibge"));
			beanCursoJsp.setFotoBase64(resultSet.getString("fotobase64"));
			beanCursoJsp.setFotoBase64Miniatura(resultSet.getString("fotobase64miniatura"));
			beanCursoJsp.setContentType(resultSet.getString("contenttype"));
			beanCursoJsp.setCurriculoBase64(resultSet.getString("curriculobase64"));
			beanCursoJsp.setContentTypeCurriculo(resultSet.getString("contenttypecurriculo"));
			beanCursoJsp.setAtivo(resultSet.getBoolean("ativo"));
			beanCursoJsp.setSexo(resultSet.getString("sexo"));
			beanCursoJsp.setPerfil(resultSet.getString("perfil"));

			return beanCursoJsp;
		}

		return null;
	}

	public boolean validarLogin(String login) throws Exception {
		String sql = "select count(1) as qtd from usuario where login = '" + login + "'";

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			return resultSet.getInt("qtd") <= 0;
		}

		return false;
	}

	public boolean validarSenha(String senha) throws Exception {
		String sql = "select count(1) as qtd from usuario where senha = '" + senha + "'";

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			return resultSet.getInt("qtd") <= 0;
		}

		return false;
	}

	public boolean validarLoginUpdate(String login, String id) throws Exception {
		String sql = "select count(1) as qtd from usuario where login = '" + login + "' and id <>" + id;

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			return resultSet.getInt("qtd") <= 0;
		}

		return false;
	}

	public boolean validarSenhaUpdate(String senha, String id) throws Exception {
		String sql = "select count(1) as qtd from usuario where senha = '" + senha + "' and id <>" + id;

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			return resultSet.getInt("qtd") <= 0;
		}

		return false;
	}

	public void atualizar(BeanCursoJsp beanCursoJsp) {
		try {
			String sql = "update usuario set login = ?, senha = ?, nome = ?, telefone = ? , "
					+ "cep = ?, rua = ?, bairro = ?, cidade = ?, estado = ?, ibge = ?, fotobase64 = ?, "
					+ "contenttype = ?, curriculoBase64 = ?, contentTypeCurriculo = ?, fotobase64miniatura = ?, "
					+ "ativo = ?, sexo = ?, perfil = ? "
					+ "where id = " + beanCursoJsp.getId();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, beanCursoJsp.getLogin());
			preparedStatement.setString(2, beanCursoJsp.getSenha());
			preparedStatement.setString(3, beanCursoJsp.getNome());
			preparedStatement.setString(4, beanCursoJsp.getTelefone());
			preparedStatement.setString(5, beanCursoJsp.getCep());
			preparedStatement.setString(6, beanCursoJsp.getRua());
			preparedStatement.setString(7, beanCursoJsp.getBairro());
			preparedStatement.setString(8, beanCursoJsp.getCidade());
			preparedStatement.setString(9, beanCursoJsp.getEstado());
			preparedStatement.setString(10, beanCursoJsp.getIbge());
			preparedStatement.setString(11, beanCursoJsp.getFotoBase64());
			preparedStatement.setString(12, beanCursoJsp.getContentType());
			preparedStatement.setString(13, beanCursoJsp.getCurriculoBase64());
			preparedStatement.setString(14, beanCursoJsp.getContentTypeCurriculo());
			preparedStatement.setString(15, beanCursoJsp.getFotoBase64Miniatura());
			preparedStatement.setBoolean(16, beanCursoJsp.isAtivo());
			preparedStatement.setString(17, beanCursoJsp.getSexo());
			preparedStatement.setString(18, beanCursoJsp.getPerfil());
			preparedStatement.executeUpdate();
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}

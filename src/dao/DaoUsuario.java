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
			String sql = "insert into usuario (login, senha, nome, telefone) values (?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, beanCursoJsp.getLogin());
			statement.setString(2, beanCursoJsp.getSenha());
			statement.setString(3, beanCursoJsp.getNome());
			statement.setString(4, beanCursoJsp.getTelefone());
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

	public List<BeanCursoJsp> listarUsuarios() throws Exception {
		List<BeanCursoJsp> usuarios = new ArrayList<BeanCursoJsp>();
		String sql = "select * from usuario";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			BeanCursoJsp beanCursoJsp = new BeanCursoJsp();
			beanCursoJsp.setId(resultSet.getLong("id"));
			beanCursoJsp.setLogin(resultSet.getString("login"));
			beanCursoJsp.setSenha(resultSet.getString("senha"));
			beanCursoJsp.setNome(resultSet.getString("nome"));
			beanCursoJsp.setTelefone(resultSet.getString("telefone"));
			usuarios.add(beanCursoJsp);
		}
		return usuarios;
	}

	public void delete(String id) {
		try {
			String sql = "delete from usuario where id = '" + id + "'";
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
		String sql = "select * from usuario where id = '" + id + "'";

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			BeanCursoJsp beanCursoJsp = new BeanCursoJsp();
			beanCursoJsp.setId(resultSet.getLong("id"));
			beanCursoJsp.setLogin(resultSet.getString("login"));
			beanCursoJsp.setSenha(resultSet.getString("senha"));
			beanCursoJsp.setNome(resultSet.getString("nome"));
			beanCursoJsp.setTelefone(resultSet.getString("telefone"));
			return beanCursoJsp;
		}

		return null;
	}
	
	public boolean validarLogin(String login) throws Exception {
		String sql = "select count(1) as qtd from usuario where login = '" + login + "'";

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			return resultSet.getInt("qtd") <=0;
		}

		return false;
	}

	public boolean validarLoginUpdate(String login, String id) throws Exception {
		String sql = "select count(1) as qtd from usuario where login = '" + login + "' and id <>" + id;

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			return resultSet.getInt("qtd") <=0;
		}

		return false;
	}
	
	
	public void atualizar(BeanCursoJsp beanCursoJsp) {
		try {
			String sql = "update usuario set login = ?, senha = ?, nome = ?, telefone = ? where id = " + beanCursoJsp.getId();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, beanCursoJsp.getLogin());
			preparedStatement.setString(2, beanCursoJsp.getSenha());
			preparedStatement.setString(3, beanCursoJsp.getNome());
			preparedStatement.setString(4, beanCursoJsp.getTelefone());
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

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
			String sql = "insert into usuario (login, senha) values (?,?)";
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, beanCursoJsp.getLogin());
			statement.setString(2, beanCursoJsp.getSenha());
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
			beanCursoJsp.setLogin(resultSet.getString("login"));
			beanCursoJsp.setLogin(resultSet.getString("senha"));
			usuarios.add(beanCursoJsp);
		}
		return usuarios;
	}
	
	public void delete(String login) {
		try {
			String sql = "delete from usuario where login = '" + login + "'";
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
}

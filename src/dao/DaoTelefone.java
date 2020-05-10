package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.TelefoneBean;
import connection.SingleConnection;

public class DaoTelefone {

	private Connection connection;

	public DaoTelefone() {
		connection = SingleConnection.getConnection();
	}

	public void salvar(TelefoneBean telefoneBean) {
		try {
			String sql = "INSERT INTO telefone(numero, tipo, usuario) VALUES (?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, telefoneBean.getNumero());
			statement.setString(2, telefoneBean.getTipo());
			statement.setLong(3, telefoneBean.getUsuario());
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

	public List<TelefoneBean> listarTelefones(Long user) throws Exception {
		List<TelefoneBean> telefones = new ArrayList<TelefoneBean>();
		String sql = "select * from telefone where usuario = " + user;
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			TelefoneBean telefoneBean = new TelefoneBean();
			telefoneBean.setId(resultSet.getLong("id"));
			telefoneBean.setNumero(resultSet.getString("numero"));
			telefoneBean.setTipo(resultSet.getString("tipo"));
			telefoneBean.setUsuario(resultSet.getLong("usuario"));
			telefones.add(telefoneBean);
		}
		return telefones;
	}

	public void delete(String id) {
		try {
			String sql = "delete from telefone where id = '" + id + "'";
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

	public TelefoneBean consultar(String id) throws Exception {
		String sql = "select * from telefone where id = '" + id + "'";

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			TelefoneBean telefoneBean = new TelefoneBean();
			telefoneBean.setId(resultSet.getLong("id"));
			telefoneBean.setNumero(resultSet.getString("numero"));
			telefoneBean.setTipo(resultSet.getString("tipo"));
			telefoneBean.setUsuario(resultSet.getLong("usuario"));
			return telefoneBean;
		}

		return null;
	}

}

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.CategoriaBean;
import beans.ProdutoBean;
import connection.SingleConnection;

public class DaoProduto {

	private Connection connection;

	public DaoProduto() {
		connection = SingleConnection.getConnection();
	}

	public void salvar(ProdutoBean produtoBean) {
		try {
			String sql = "INSERT INTO produto(nome, quantidade, valor) VALUES (?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, produtoBean.getNome());
			statement.setInt(2, produtoBean.getQuantidade());
			statement.setString(3, produtoBean.getValor());
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

	public boolean validarNome(String nome) throws Exception {
		String sql = "select count(1) as qtd from produto where nome = '" + nome + "'";

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			return resultSet.getInt("qtd") <= 0;
		}

		return false;
	}

	public void atualizar(ProdutoBean produtoBean) {
		try {
			String sql = "update produto set nome=?, quantidade=?, valor=? where codigo = " + produtoBean.getCodigo();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, produtoBean.getNome());
			preparedStatement.setInt(2, produtoBean.getQuantidade());
			preparedStatement.setString(3, produtoBean.getValor());
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

	public List<ProdutoBean> listarProdutos() throws Exception {
		List<ProdutoBean> produtos = new ArrayList<ProdutoBean>();
		String sql = "select * from produto";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			ProdutoBean produtoBean = new ProdutoBean();
			produtoBean.setCodigo(resultSet.getLong("codigo"));
			produtoBean.setNome(resultSet.getString("nome"));
			produtoBean.setQuantidade(resultSet.getInt("quantidade"));
			produtoBean.setValor(resultSet.getString("valor"));
			produtos.add(produtoBean);
		}
		return produtos;
	}
	
						/*Listar Categorias*/
	
	public List<CategoriaBean> listarCategorias() throws Exception {
		List<CategoriaBean> categorias = new ArrayList<CategoriaBean>();
		String sql = "select * from categoria";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			CategoriaBean categoria = new CategoriaBean();
			categoria.setId(resultSet.getLong("id"));
			categoria.setNome(resultSet.getString("nome"));
			categorias.add(categoria);
		}
		return categorias;
	}

	public void delete(String codigo) {
		try {
			String sql = "delete from produto where codigo = '" + codigo + "'";
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
	
	public ProdutoBean consultar(String codigo) throws Exception {
		String sql = "select * from produto where codigo = '" + codigo + "'";

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			ProdutoBean produtoBean = new ProdutoBean();
			produtoBean.setCodigo(resultSet.getLong("codigo"));
			produtoBean.setNome(resultSet.getString("nome"));
			produtoBean.setQuantidade(resultSet.getInt("quantidade"));
			produtoBean.setValor(resultSet.getString("valor"));
			return produtoBean;
		}

		return null;
	}

	public boolean validarNomeUpdate(String nome, String codigo) throws Exception {
		String sql = "select count(1) as qtd from produto where nome = '" + nome + "' and codigo <>" + codigo;

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			return resultSet.getInt("qtd") <=0;
		}

		return false;
	}

}

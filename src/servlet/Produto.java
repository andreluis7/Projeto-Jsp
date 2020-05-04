package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.ProdutoBean;
import dao.DaoProduto;

@WebServlet("/salvarProduto")
public class Produto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoProduto daoProduto = new DaoProduto();

	public Produto() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String acao = request.getParameter("acao");
			String produto = request.getParameter("produto");

			if (acao.equalsIgnoreCase("delete")) {
				daoProduto.delete(produto);

				RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastroProduto.jsp");
				request.setAttribute("produtos", daoProduto.listarProdutos());
				dispatcher.forward(request, response);

			} else if (acao.equalsIgnoreCase("editar")) {
				ProdutoBean produtoBean = daoProduto.consultar(produto);

				RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastroProduto.jsp");
				request.setAttribute("produto", produtoBean);
				dispatcher.forward(request, response);

			} else if (acao.equalsIgnoreCase("listarProdutos")) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastroProduto.jsp");
				request.setAttribute("produtos", daoProduto.listarProdutos());
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");

		if (acao != null && acao.equalsIgnoreCase("reset")) {
			try {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastroProduto.jsp");
				 request.setAttribute("produtos", daoProduto.listarProdutos());
				dispatcher.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {

			String codigo = request.getParameter("codigo");
			String nome = request.getParameter("nome");
			String quantidade = request.getParameter("quantidade");
			String valor = request.getParameter("valor");

			ProdutoBean produtoBean = new ProdutoBean();

			produtoBean.setCodigo(!codigo.isEmpty() ? Long.parseLong(codigo) : null);
			produtoBean.setNome(nome);
			produtoBean.setQuantidade(Integer.parseInt(quantidade));
			produtoBean.setValor(valor);

			boolean podeInserir = true;
			String msg = null;
			try {
				if (codigo == null || codigo.isEmpty() && !daoProduto.validarNome(nome)) {
					msg = "\nProduto j� existe com o mesmo nome";
					podeInserir = false;
				}

				if (msg != null) {
					request.setAttribute("msg", msg);
				}

				if (codigo == null || codigo.isEmpty() && daoProduto.validarNome(nome) && podeInserir) {
					daoProduto.salvar(produtoBean);
				}

				else if (codigo != null && !codigo.isEmpty()) {
					if (!daoProduto.validarNomeUpdate(nome, codigo)) {
						request.setAttribute("msg", "Produto j� existe com o mesmo nome");
						podeInserir = false;
					} else {
						daoProduto.atualizar(produtoBean);
					}
				}

				if (!podeInserir) {
					request.setAttribute("produto", produtoBean);
				}

				RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastroProduto.jsp");
				request.setAttribute("produtos", daoProduto.listarProdutos());
				dispatcher.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}

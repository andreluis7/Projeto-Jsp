package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanCursoJsp;
import beans.TelefoneBean;
import dao.DaoTelefone;
import dao.DaoUsuario;

@WebServlet("/salvarTelefone")
public class Telefone extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoUsuario daoUsuario = new DaoUsuario();

	private DaoTelefone daoTelefone = new DaoTelefone();

	public Telefone() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			String acao = request.getParameter("acao");

			if (acao != null) {
				if (acao.endsWith("addFone")) {
					String user = request.getParameter("user");

					BeanCursoJsp usuario = daoUsuario.consultar(user);

					request.getSession().setAttribute("userEscolhido", usuario);
					request.setAttribute("userEscolhido", usuario);

					RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastroTelefone.jsp");
					request.setAttribute("telefones", daoTelefone.listarTelefones(usuario.getId()));
					dispatcher.forward(request, response);

				} else if (acao.endsWith("deleteFone")) {
					String telefoneId = request.getParameter("telefoneId");
					daoTelefone.delete(telefoneId);

					BeanCursoJsp usuario = (BeanCursoJsp) request.getSession().getAttribute("userEscolhido");

					RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastroTelefone.jsp");
					request.setAttribute("telefones", daoTelefone.listarTelefones(usuario.getId()));
					request.setAttribute("msg", "Removido com sucesso!");
					dispatcher.forward(request, response);

				}
			} else {
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listarUsuarios());
				view.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			BeanCursoJsp beanCursoJsp = (BeanCursoJsp) request.getSession().getAttribute("userEscolhido");
			String numero = request.getParameter("numero");
			String tipo = request.getParameter("tipo");

			String acao = request.getParameter("acao");

			if (acao == null || (acao != null && !acao.equalsIgnoreCase("voltar"))) {

				if (numero == null || (numero != null && numero.isEmpty())) {

					RequestDispatcher view = request.getRequestDispatcher("/telefones.jsp");
					request.setAttribute("telefone", daoTelefone.listarTelefones(beanCursoJsp.getId()));
					request.setAttribute("msg", "Informe o numero do telefone!");
					view.forward(request, response);

				} else {

					TelefoneBean beanTelefone = new TelefoneBean();
					beanTelefone.setNumero(numero);
					beanTelefone.setTipo(tipo);
					beanTelefone.setUsuario(beanCursoJsp.getId());
					daoTelefone.salvar(beanTelefone);
					request.getSession().setAttribute("userEscolhido", beanCursoJsp);
					request.setAttribute("userEscolhido", beanCursoJsp);

					RequestDispatcher view = request.getRequestDispatcher("/cadastroTelefone.jsp");
					request.setAttribute("telefones", daoTelefone.listarTelefones(beanCursoJsp.getId()));
					request.setAttribute("msg", "Salvo Com Sucesso!");
					view.forward(request, response);

				}

			} else {
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listarUsuarios());
				view.forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

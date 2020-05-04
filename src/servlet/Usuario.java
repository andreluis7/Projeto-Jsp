package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanCursoJsp;
import dao.DaoUsuario;

@WebServlet("/salvarUsuario")
public class Usuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoUsuario daoUsuario = new DaoUsuario();

	public Usuario() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		try {
			String acao = request.getParameter("acao");
			String user = request.getParameter("user");

			if (acao.equalsIgnoreCase("delete")) {
				daoUsuario.delete(user);

				RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listarUsuarios());
				dispatcher.forward(request, response);

			} else if (acao.equalsIgnoreCase("editar")) {
				BeanCursoJsp beanCursoJsp = daoUsuario.consultar(user);

				RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("user", beanCursoJsp);
				dispatcher.forward(request, response);

			} else if (acao.equalsIgnoreCase("listartodos")) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listarUsuarios());
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listarUsuarios());
				dispatcher.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {

			String id = request.getParameter("id");
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			String nome = request.getParameter("nome");
			String telefone = request.getParameter("telefone");

			BeanCursoJsp beanCursoJsp = new BeanCursoJsp();

			beanCursoJsp.setId(!id.isEmpty() ? Long.parseLong(id) : null);
			beanCursoJsp.setLogin(login);
			beanCursoJsp.setSenha(senha);
			beanCursoJsp.setNome(nome);
			beanCursoJsp.setTelefone(telefone);

			boolean podeInserir = true;
			String msg = null;
			try {
				if (id == null || id.isEmpty() && !daoUsuario.validarLogin(login)) {
					msg = "\nUsu�rio j� existe com o mesmo login";
					podeInserir = false;
				}

				else if (id == null || id.isEmpty() && !daoUsuario.validarSenha(senha)) {
					msg = "\nUsu�rio j� existe com o mesma senha";
					podeInserir = false;
				}

				if (msg != null) {
					request.setAttribute("msg", msg);
				}

				if (id == null || id.isEmpty() && daoUsuario.validarLogin(login) && podeInserir) {
					daoUsuario.salvar(beanCursoJsp);
				}

				else if (id != null && !id.isEmpty()) {
					if (!daoUsuario.validarLoginUpdate(login, id)) {
						request.setAttribute("msg", "Usua�rio j� existe com o mesmo login");
						podeInserir = false;
					} else if (!daoUsuario.validarSenhaUpdate(senha, id)) {
						request.setAttribute("msg", "Usua�rio j� existe com o mesma senha");
						podeInserir = false;
					} else {
						daoUsuario.atualizar(beanCursoJsp);
					}
				}

				if (!podeInserir) {
					request.setAttribute("user", beanCursoJsp);
				}

				RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listarUsuarios());
				dispatcher.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}

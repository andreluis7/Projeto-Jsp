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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		
		BeanCursoJsp beanCursoJsp = new BeanCursoJsp();
		
		beanCursoJsp.setLogin(login);
		beanCursoJsp.setSenha(senha);
		
		daoUsuario.salvar(beanCursoJsp);
		
		try {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastroUsuario.jsp");
			request.setAttribute("usuarios",daoUsuario.listarUsuarios());
			dispatcher.forward(request,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}

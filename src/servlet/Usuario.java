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
		try {
		String acao = request.getParameter("acao");
		String user = request.getParameter("user");
		
		if(acao.equalsIgnoreCase("delete")) {
			daoUsuario.delete(user);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastroUsuario.jsp");
			request.setAttribute("usuarios",daoUsuario.listarUsuarios());
			dispatcher.forward(request,response);
			
		} else if(acao.equalsIgnoreCase("editar")) {
			BeanCursoJsp beanCursoJsp = daoUsuario.consultar(user);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastroUsuario.jsp");
			request.setAttribute("user",beanCursoJsp);
			dispatcher.forward(request,response);
			
			
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		
		BeanCursoJsp beanCursoJsp = new BeanCursoJsp();
		
		beanCursoJsp.setId(!id.isEmpty()? Long.parseLong(id) : 0);
		beanCursoJsp.setLogin(login);
		beanCursoJsp.setSenha(senha);
		if (id == null || id.isEmpty()) {
			daoUsuario.salvar(beanCursoJsp);			
		}else {
			daoUsuario.atualizar(beanCursoJsp);
		}
		
		try {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastroUsuario.jsp");
			request.setAttribute("usuarios",daoUsuario.listarUsuarios());
			dispatcher.forward(request,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}

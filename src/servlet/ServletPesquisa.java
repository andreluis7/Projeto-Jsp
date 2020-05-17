package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanCursoJsp;
import dao.DaoUsuario;

@WebServlet("/servletPesquisa")
public class ServletPesquisa extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DaoUsuario daoUsuario = new DaoUsuario();
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String descricaoconsulta = request.getParameter("descricaoconsulta");
		
		if (descricaoconsulta != null && !descricaoconsulta.trim().isEmpty()) {
			try {
				List<BeanCursoJsp> listaPesquisa = daoUsuario.listarUsuarios(descricaoconsulta);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", listaPesquisa);
				dispatcher.forward(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else { 
			RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastroUsuario.jsp");
			try {
				request.setAttribute("usuarios", daoUsuario.listarUsuarios());
			} catch (Exception e) {
				e.printStackTrace();
			}
			dispatcher.forward(request, response);
		}
	}

}

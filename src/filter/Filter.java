package filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import connection.SingleConnection;

public class Filter implements javax.servlet.Filter {

	private static Connection connection; 

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		try {

			chain.doFilter(request, response);
			connection.commit(); 

		} catch (Exception e) {

			try {
				connection.rollback(); 

			} catch (SQLException e1) {
				e1.printStackTrace();

			}

		}

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException { 
		SingleConnection.getConnection();
	}
}
package kr.co.comes.projectA.interceptor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class DBdownInterceptor extends HandlerInterceptorAdapter {

	/**
	 * Interceptor ó�� �޼ҵ� DB�ٿ�� �ۿ�
	 * @return /dbdown.jsp ȣ��
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Connection con = null;

		try {
			Class.forName("org.postgresql.Driver");
			 con = DriverManager.getConnection("jdbc:postgresql://192.168.0.140:5432/ResourceMonitor", "postgres",
					"comes");
		} catch (SQLException e) {
			response.sendRedirect("/dbdown");
			return false;
		} finally {
			con.close();
		}
		return true;

	}
}

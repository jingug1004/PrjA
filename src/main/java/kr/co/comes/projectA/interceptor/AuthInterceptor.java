package kr.co.comes.projectA.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

	/**
	 * �� URL ����
	 */

	private void saveDest(HttpServletRequest req) {

		String uri = req.getRequestURI();

		String query = req.getQueryString();

		if (query == null || query.equals("null")) {
			query = "";
		} else {
			query = "?" + query;
		}

		if (req.getMethod().equals("GET")) {
			logger.info("dest: " + (uri + query));
			req.getSession().setAttribute("dest", uri + query);
		}
	}

	/**
	 * Interceptor ó�� �޼ҵ� session �� null�� ��� �ۿ�
	 * @return /user/login.jsp ȣ��
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession session = request.getSession();

		if (session.getAttribute("id") == null) {

			logger.info("current user is not logined");

			System.out.println(request.getRequestURI());
			saveDest(request);

			response.sendRedirect("/");
			return false;
		}
		return true;
	}
}

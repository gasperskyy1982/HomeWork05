package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DiscountFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("Autorization");
		if (filterConfig.getInitParameter("isReg").equals("true")) {
			System.out.println("SUPER SALE");
		} else {
			System.out.println("403 Forbiden");
		}

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request2 = (HttpServletRequest) request;
		HttpSession session = request2.getSession();
		if (session.getAttribute("login") == null) {
			HttpServletResponse response2 = (HttpServletResponse) response;
			response2.sendRedirect("/.403View.jsp");
		} else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}

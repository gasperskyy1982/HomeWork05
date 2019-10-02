package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controllers.DBUtils;
import controllers.UserController;
import models.User;

/**
 * Servlet implementation class AuthServlet
 */
public class AuthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AuthServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher rd = request.getRequestDispatcher("/AuthDB.jsp");
		rd.forward(request, response);

				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		boolean isLogin = false;
		boolean showForm = true;
		boolean isError = false;
		String logOut = request.getParameter("logOut");
		String login = request.getParameter("Login");
		String password = request.getParameter("Password");
		String message = "<div style = 'color:red;'> Acess denided </div>";
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		
		
		if (logOut != null) {
			session.invalidate();
			session = request.getSession(true);
			showForm = true;
		} else {
			if (login != null && password != null) {
				if (DBUtils.getAuth(login, password)) {
					showForm = false;
					
					User user = new UserController().createUser(DBUtils.userLogin, DBUtils.userPassword, DBUtils.userName, 
																DBUtils.userRegion, DBUtils.userGender, DBUtils.userComment);
					session.setAttribute("user", user);
//					response.sendRedirect("/authed");
					RequestDispatcher rd = request.getRequestDispatcher("/AuthedDB.jsp");
					request.setAttribute("authUser", user);
					request.setAttribute("auth", "Autorized");
					rd.forward(request, response);
				} else {
					isError = true;
					request.setAttribute("isError", isError);
					RequestDispatcher rd = request.getRequestDispatcher("/AuthDB.jsp");
					rd.forward(request, response);
				}
			}

			if (showForm) {
				doGet(request, response);
			}
		}
	}

}

package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.DBUtils;
import controllers.UserController;
import models.User;

/**
 * Servlet implementation class RegServlet
 */
public class RegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		boolean showForm = true;
		RequestDispatcher rd = request.getRequestDispatcher("/RegDB.jsp");
		request.setAttribute("showForm", showForm);
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		boolean isError = false;
		boolean showForm = true;
		StringBuilder error_Text = new StringBuilder();
		String login = request.getParameter("Login");
		String password = request.getParameter("Password");
		String re_password = request.getParameter("Re_Password");
		String name = request.getParameter("Name");
		String region = request.getParameter("Region");
		String gender = request.getParameter("Gender");
		String comment = request.getParameter("Comment");
		String agree = request.getParameter("Agree");
		
		System.out.println(gender);
		
		request.setAttribute("showForm", showForm);
		
		if (login != null && login.length() == 0) {
			isError = true;
			error_Text.append("<li style = 'color:red'> Login is empty </li>");
		}

		if (login != null && login.length() != 0) {
			if (!DBUtils.isLoginCorrect(login)) {
				isError = true;
				error_Text.append("<li style = 'color:red'> Incorrect Login </li>");
			} else {
				if (!DBUtils.getUniqueLogin(login)) {
					isError = true;
					error_Text.append("<li style = 'color:red'> Duplicate Login </li>");
				} else {
					request.setAttribute("login", login);
				}
			}
		}
					
		if (password != null && password.length() == 0) {
			isError = true;
			error_Text.append("<li style = 'color:red'> Password is empty </li>");
		} else {
			if (!DBUtils.isPasswordCorrect(password)) {
				isError = true;
				error_Text.append("<li style = 'color:red'> Not safe Password </li>");
			} else {
				request.setAttribute("password", password);
			}
		}
		if (re_password != null && re_password.length() == 0) {
			isError = true;
			error_Text.append("<li style = 'color:red'> Re_Password is empty </li>");
		}

		if (password != null && password.length() != 0 && re_password != null && re_password.length() != 0) {
			if (!DBUtils.isRe_PasswordCorrect(password, re_password)) {
				isError = true;
				error_Text.append("<li style = 'color:red'> Re_type Password </li>");
			} else {
				request.setAttribute("re_password", re_password);
			}
		}

		if (name != null && name.length() == 0) {
			isError = true;
			error_Text.append("<li style = 'color:red'> Name is empty </li>");
		} else {
			request.setAttribute("name", name);
		}

		if (region != null && region.length() == 0) {
			isError = true;
			error_Text.append("<li style = 'color:red'> Region is empty </li>");
		} else {
			request.setAttribute("region", region);
		}

		if (gender == null) {
			isError = true;
			error_Text.append("<li style = 'color:red'> Gender is empty </li>");
		} else {
			request.setAttribute("gender", Boolean.parseBoolean(gender));
		}

		if (comment != null && comment.length() == 0) {
			isError = true;
			error_Text.append("<li style = 'color:red'> Comment is empty </li>");
		} else {
			request.setAttribute("comment", comment);
		}

		if (agree == null) {
			isError = true;
			error_Text.append("<li style = 'color:red'> Agree is empty </li>");
		} else {
			request.setAttribute("agree", agree);
		}

		if (!isError) {
			showForm = false;
			User user = new UserController().createUser(login, password, name, region, Boolean.parseBoolean(gender),
			comment);
			DBUtils.setUser(user.getLogin(), user.getPassword(), user.getName(), user.getRegion(), user.getGender(), user.getComment());
			response.sendRedirect("./RegDB.jsp");
		} else {
			request.setAttribute("error", error_Text);
			RequestDispatcher rd = request.getRequestDispatcher("./RegDB.jsp");
			rd.forward(request, response);
		
		}

	}

}

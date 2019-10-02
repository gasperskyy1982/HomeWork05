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
 * Servlet implementation class CorrectServlet
 */
public class CorrectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CorrectServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		session.getAttribute("user");
		boolean showCorrForm = true;
		RequestDispatcher rd = request.getRequestDispatcher("/CorrDB.jsp");
		request.setAttribute("showCorrForm", showCorrForm);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		boolean isErrorCorr = false;
		boolean showCorrForm = true;
		StringBuilder error_TextCorr = new StringBuilder();
		String loginCorr = request.getParameter("Login");
		String passwordCorr = request.getParameter("Password");
		String re_passwordCorr = request.getParameter("Re_Password");
		String nameCorr = request.getParameter("Name");
		String regionCorr = request.getParameter("Region");
		String genderCorr = request.getParameter("Gender");
		String commentCorr = request.getParameter("Comment");
		String agreeCorr = request.getParameter("Agree");

		System.out.println(loginCorr);
		System.out.println(passwordCorr);
		System.out.println(nameCorr);
		System.out.println(regionCorr);
		System.out.println(genderCorr);
		System.out.println(commentCorr);
		System.out.println(agreeCorr);
		
		request.setAttribute("showCorrForm", showCorrForm);
		request.setAttribute("login", loginCorr);
							
		if (passwordCorr != null && passwordCorr.length() == 0) {
			isErrorCorr = true;
			error_TextCorr.append("<li style = 'color:red'> Password is empty </li>");
		} else {
			if (!DBUtils.isPasswordCorrect(passwordCorr)) {
				isErrorCorr = true;
				error_TextCorr.append("<li style = 'color:red'> Not safe Password </li>");
			} else {
				request.setAttribute("password", passwordCorr);
			}
		}
		if (re_passwordCorr != null && re_passwordCorr.length() == 0) {
			isErrorCorr = true;
			error_TextCorr.append("<li style = 'color:red'> Re_Password is empty </li>");
		}

		if (passwordCorr != null && passwordCorr.length() != 0 && re_passwordCorr != null && re_passwordCorr.length() != 0) {
			if (!DBUtils.isRe_PasswordCorrect(passwordCorr, re_passwordCorr)) {
				isErrorCorr = true;
				error_TextCorr.append("<li style = 'color:red'> Re_type Password </li>");
			} else {
				request.setAttribute("re_password", re_passwordCorr);
			}
		}

		if (nameCorr != null && nameCorr.length() == 0) {
			isErrorCorr = true;
			error_TextCorr.append("<li style = 'color:red'> Name is empty </li>");
		} else {
			request.setAttribute("name", nameCorr);
		}

		if (regionCorr != null && regionCorr.length() == 0) {
			isErrorCorr = true;
			error_TextCorr.append("<li style = 'color:red'> Region is empty </li>");
		} else {
			request.setAttribute("region", regionCorr);
		}

		if (genderCorr == null) {
			isErrorCorr = true;
			error_TextCorr.append("<li style = 'color:red'> Gender is empty </li>");
		} else {
			request.setAttribute("gender", Boolean.parseBoolean(genderCorr));
		}

		if (commentCorr != null && commentCorr.length() == 0) {
			isErrorCorr = true;
			error_TextCorr.append("<li style = 'color:red'> Comment is empty </li>");
		} else {
			request.setAttribute("comment", commentCorr);
		}

		if (agreeCorr == null) {
			isErrorCorr = true;
			error_TextCorr.append("<li style = 'color:red'> Agree is empty </li>");
		} else {
			request.setAttribute("agree", agreeCorr);
		}

		System.out.println("isError = " + isErrorCorr);
		System.out.println("TextError = " + error_TextCorr);
		
		if (!isErrorCorr) {
			
			showCorrForm = false;
			User corrUser = new UserController().createUser(loginCorr, re_passwordCorr, nameCorr, regionCorr, Boolean.parseBoolean(genderCorr), commentCorr);
			
			DBUtils.CorrectUser(corrUser.getLogin(), corrUser.getPassword(), corrUser.getName(), corrUser.getRegion(),
								corrUser.getGender(), corrUser.getComment());
			
			HttpSession session = request.getSession();
			session.setAttribute("user", corrUser);
			response.sendRedirect("./CorrDB.jsp");
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/CorrDB.jsp");
			request.setAttribute("errorCorr", error_TextCorr);
			rd.forward(request, response);
		}

	}

}

package coder.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import coder.daos.UserDAO;
import coder.models.User;

@WebServlet("/UserController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DataSource ds;

	public UserController() {
		super();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		try {
			InitialContext initContext = new InitialContext();
			Context env = (Context) initContext.lookup("java:comp/env");
			ds = (DataSource) env.lookup("jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		UserDAO userDao = new UserDAO();
		HttpSession session = request.getSession();

		String action = request.getParameter("action");

		Connection con = null;

		try {
			con = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}

		if (action == null) {
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		} else {
			switch (action) {
			case "billout":
				if (session.getAttribute("user") == null) {
					request.getRequestDispatcher("/login.jsp").forward(request, response);
				} else {
					request.getRequestDispatcher("/cartout.jsp").forward(request, response);
				}
				break;
			case "login":
				request.getRequestDispatcher("/login.jsp").forward(request, response);
				break;
			case "register":
				request.getRequestDispatcher("/register.jsp").forward(request, response);
				break;
			case "loginForm":

				String email = request.getParameter("email");
				String password = request.getParameter("password");

				User user = userDao.getUserByEmail(con, email);
				if (user == null || !password.contentEquals(user.getPassword())) {
					request.setAttribute("msgError", "Login Error! Please try again!");
					request.getRequestDispatcher("/login.jsp").forward(request, response);
				} else if (user.getId() == 1 && user.getRole() == 1) {
					session.setAttribute("user", user);
					request.setAttribute("msgSuccess", "Welcom Back");
					request.getRequestDispatcher("/admin_home.jsp").forward(request, response);
				} else {

					session.setAttribute("user", user);
					request.setAttribute("msgSuccess", "Welcom Back");
					request.getRequestDispatcher("/cartout.jsp").forward(request, response);
				}

				break;
			case "logout":
				session.removeAttribute("user");
				request.getRequestDispatcher("/index.jsp").forward(request, response);
				break;
			case "registerFrom":
				registerAUser(request, response, con, userDao);
				break;
			}
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void registerAUser(HttpServletRequest request, HttpServletResponse response, Connection con,
			UserDAO userDao) throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		PrintWriter pw = response.getWriter();

		User user = userDao.getUserByEmail(con, email);

		if (user == null) {

			boolean bol = userDao.registerAUser(con, name, email, password, 1);

			if (bol) {
				request.setAttribute("msgError", "Register Success! Plese login below!");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			} else {
				request.setAttribute("msgError", "Something wrong! Please contact admin!");
				request.getRequestDispatcher("/register.jsp").forward(request, response);
			}

		} else {
			request.setAttribute("msgError", "Email is alreay in use!");
			request.getRequestDispatcher("/register.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

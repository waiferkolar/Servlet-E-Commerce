package coder.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.google.gson.Gson;

import coder.daos.OrderDao;
import coder.daos.ProductDAO;
import coder.models.CartItem;
import coder.models.Order;
import coder.models.Product;
import coder.models.User;

@WebServlet("/CartController")
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DataSource ds;

	public CartController() {
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
		ProductDAO pDao = new ProductDAO();

		Connection con = null;

		try {
			con = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String param = request.getParameter("action");

		if (param.contentEquals("billout")) {
			billOut(request, response, con, pDao, pw);
		} else if (param.contentEquals("orderout")) {
			orderOut(request, response, con, pDao, pw);
		}

		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	private void billOut(HttpServletRequest request, HttpServletResponse response, Connection con, ProductDAO pDao,
			PrintWriter pw) {
		Gson gson = new Gson();
		String items = request.getParameter("items");
		CartItem[] cartItems = gson.fromJson(items, CartItem[].class);
		List<Product> products = new ArrayList<>();
		for (CartItem item : cartItems) {
			products.add(pDao.getSingleProduct(con, item.getId()));
		}
		pw.write(gson.toJson(products));
	}

	private void orderOut(HttpServletRequest request, HttpServletResponse response, Connection con, ProductDAO pDao,
			PrintWriter pw) throws ServletException, IOException {
		String items = request.getParameter("items");
		OrderDao orderDao = new OrderDao();

		User user = (User) request.getSession().getAttribute("user");
			boolean bol = orderDao.insertNewOrder(con, user.getId(),items);
			if (bol) {
				pw.write("success");
			} else {
				pw.write("error");
			}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

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
import coder.daos.UserDAO;
import coder.models.CartItem;
import coder.models.Order;
import coder.models.Product;
import coder.models.User;

@WebServlet("/AdminUserController")
public class AdminUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DataSource ds;

	public AdminUserController() {
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
		String action = request.getParameter("action");
		PrintWriter pw = response.getWriter();

		Connection con = null;
		try {
			con = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (action != null) {
			if (action.contentEquals("orderdetail")) {
				showUserOderDetail(request, response, con, pw);
			}
		}

		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private void showUserOderDetail(HttpServletRequest request, HttpServletResponse response, Connection con,
			PrintWriter pw) throws ServletException, IOException {

		String user_id = request.getParameter("user_id");

		OrderDao oDao = new OrderDao();
		ProductDAO pDao = new ProductDAO();
		UserDAO uDao = new UserDAO();

		User user = uDao.getUserById(con, Integer.parseInt(user_id));

		Gson gson = new Gson();
		List<Order> ods = oDao.getOrderOfAUser(con, Integer.parseInt(user_id));

		List<CartItem> userItems = new ArrayList<>();
		List<Product> products = new ArrayList<>();

		for (Order order : ods) {
			CartItem[] items = gson.fromJson(order.getOrders(), CartItem[].class);
			for (CartItem item : items) {
				userItems.add(item);
			}
		}

		for (CartItem item : userItems) {
			products.add(pDao.getSingleProductWithNameAndCount(con, item.getId(), item.getCount(), user.getName()));
		}

		request.setAttribute("orders", products);
		request.getRequestDispatcher("/order_detail.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

package coder.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import coder.models.Order;

public class OrderDao {

	public boolean insertNewOrder(Connection con, int user_id, String orders) {
		boolean bol = false;

		String query = "INSERT INTO orders (user_id,orders) VALUES (?,?)";

		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement(query);
			stmt.setInt(1, user_id);
			stmt.setString(2, orders);

			int result = stmt.executeUpdate();

			if (result == 1) {
				bol = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return bol;
	}

	public List<Order> getOrderOfAUser(Connection con, int user_id) {
		List<Order> orders = new ArrayList<>();

		String query = "SELECT * FROM orders WHERE user_id=?";

		PreparedStatement stmt = null;
		ResultSet set = null;

		try {
			stmt = con.prepareStatement(query);
			stmt.setInt(1, user_id);
			set = stmt.executeQuery();

			while (set.next()) {
				orders.add(new Order(set.getInt("id"), set.getInt("user_id"), set.getString("orders")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(set != null) {
			try {
				set.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return orders;
	}
}

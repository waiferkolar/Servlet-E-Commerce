package coder.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import coder.models.Product;

public class ProductDAO {
	public List<Product> getAllProductByCat(Connection con, int catId) {
		List<Product> products = new ArrayList<>();

		String query = "SELECT * FROM products WHERE cat_id=?";
		PreparedStatement stmt = null;
		ResultSet set = null;
		try {
			stmt = con.prepareStatement(query);
			stmt.setInt(1, catId);
			set = stmt.executeQuery();

			while (set.next()) {
				products.add(new Product(set.getInt("id"), set.getInt("cat_id"), set.getInt("price"),
						set.getString("name"), set.getString("image"), set.getString("description")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (set != null) {
			try {
				set.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return products;
	}

	public Product getSingleProduct(Connection con, int id) {
		Product product = null;

		String query = "SELECT * FROM products WHERE id=?";
		PreparedStatement stmt = null;
		ResultSet set = null;
		try {
			stmt = con.prepareStatement(query);
			stmt.setInt(1, id);

			set = stmt.executeQuery();
			while (set.next()) {
				product = new Product(set.getInt("id"), set.getInt("cat_id"), set.getInt("price"),
						set.getString("name"), set.getString("image"), set.getString("description"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (set != null) {
			try {
				set.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return product;
	}

	public Product getSingleProductWithNameAndCount(Connection con, int id, int pCount, String name) {
		Product product = null;

		String query = "SELECT * FROM products WHERE id=?";
		PreparedStatement stmt = null;
		ResultSet set = null;
		try {
			stmt = con.prepareStatement(query);
			stmt.setInt(1, id);

			set = stmt.executeQuery();
			while (set.next()) {
				product = new Product(set.getInt("id"), set.getInt("cat_id"), set.getInt("price"), pCount,
						set.getString("name"), set.getString("image"), set.getString("description"), name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (set != null) {
			try {
				set.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return product;
	}
}

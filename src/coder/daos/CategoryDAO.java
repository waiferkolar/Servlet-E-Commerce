package coder.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import coder.models.Category;

public class CategoryDAO {
	public List<Category> getAllCats(Connection con) {
		List<Category> categories = new ArrayList<>();
		String query = "SELECT * FROM categories";
		PreparedStatement stmt = null;
		ResultSet set = null;
		try {
			stmt = con.prepareStatement(query);
			set = stmt.executeQuery();
			while (set.next()) {
				categories.add(new Category(set.getInt("id"), set.getString("name"), set.getString("image")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if(set != null) {
			try {
				set.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return categories;
	}
}

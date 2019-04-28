package coder.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import coder.models.User;

public class UserDAO {

	public User getUserByEmail(Connection con, String email) {
		User user = null;

		String query = "SELECT * FROM users WHERE email=?";
		PreparedStatement stmt = null;
		ResultSet set = null;
		try {
			stmt = con.prepareStatement(query);
			stmt.setString(1, email);
			set = stmt.executeQuery();

			while (set.next()) {
				user = new User(set.getInt("id"), set.getInt("role"), set.getString("name"), set.getString("email"),
						set.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (set != null) {
			try {
				set.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}
	
	public User getUserById(Connection con, int user_id) {
		User user = null;

		String query = "SELECT * FROM users WHERE id=?";
		PreparedStatement stmt = null;
		ResultSet set = null;
		try {
			stmt = con.prepareStatement(query);
			stmt.setInt(1, user_id);
			set = stmt.executeQuery();

			while (set.next()) {
				user = new User(set.getInt("id"), set.getInt("role"), set.getString("name"), set.getString("email"),
						set.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (set != null) {
			try {
				set.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}

	public boolean registerAUser(Connection con, String name, String email, String password, int role) {
		boolean bol = false;
		
		String query = "INSERT INTO users (name,email,role,password) VALUES (?,?,?,?)";

		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement(query);
			stmt.setString(1, name);
			stmt.setString(2, email);
			stmt.setInt(3, role);
			stmt.setString(4, password);

			int result = stmt.executeUpdate();

			if (result == 1) {
				bol = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return bol;
	}
}

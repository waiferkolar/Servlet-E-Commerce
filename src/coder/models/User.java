package coder.models;

public class User {
	private int id, role;
	private String name, email, password;

	public User(int id, int role, String name, String email, String password) {
		super();
		this.id = id;
		this.role = role;
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", role=" + role + ", name=" + name + ", email=" + email + ", password=" + password
				+ "]";
	}

}

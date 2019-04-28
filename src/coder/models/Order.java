package coder.models;

public class Order {
	private int id, user_id;
	private String orders;

	public Order(int id, int user_id, String orders) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.orders = orders;
	}

	public Order(int user_id, String orders) {
		super();
		this.user_id = user_id;
		this.orders = orders;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getOrders() {
		return orders;
	}

	public void setOrders(String orders) {
		this.orders = orders;
	}

}

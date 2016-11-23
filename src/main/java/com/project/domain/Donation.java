package main.java.com.project.domain;

public class Donation {
	private int id;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Donation(String _type) {
		this.type = _type;
	}

	public Donation(){

	}
}

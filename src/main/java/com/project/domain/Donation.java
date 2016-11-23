package main.java.com.project.domain;

public class Donation {
	private int id;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private String fluid_type;

	public String getFluid_type() {
		return fluid_type;
	}

	public void setFluid_type(String fluid_type) {
		this.fluid_type = fluid_type;
	}

	public Donation(String _fluid_type) {
		this.fluid_type = _fluid_type;
	}

	public Donation(){

	}
}

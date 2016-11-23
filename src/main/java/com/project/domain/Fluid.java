package main.java.com.project.domain;

public class Fluid {
	private int id;

	private String fluid_type;
	private int volume;
	private int value;
	private int donation_id;

	public Fluid(String fluid_type, int volume, int value) {
		super();
		this.fluid_type = fluid_type;
		this.volume = volume;
		this.value = value;
	}

	public Fluid(String fluid_type, int volume, int value, int donation_id) {
		super();
		this.fluid_type = fluid_type;
		this.volume = volume;
		this.value = value;
		this.donation_id = donation_id;
	}

	public Fluid(){}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDonation_id() {
		return donation_id;
	}
	public void setDonation_id(int donation_id) {
		this.donation_id = donation_id;
	}
	public String getFluid_type() {
		return fluid_type;
	}
	public void setFluid_type(String fluid_type) {
		this.fluid_type = fluid_type;
	}
	public int getVolume() {
		return volume;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
}

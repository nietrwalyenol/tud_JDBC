package com.project.domain;

public class Donation {
	private int id;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private String place;

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Donation(String _place) {
		this.place = _place;
	}

	public Donation(){

	}

}

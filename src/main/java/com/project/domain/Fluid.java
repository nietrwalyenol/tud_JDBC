package com.project.domain;


public class Fluid {

	private int id;
	
	private String data_pobrania;
	private int rodzaj;
	private int objetosc;
	private int opis;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getData_pobrania() {
		return data_pobrania;
	}
	public void setData_pobrania(String data_pobrania) {
		this.data_pobrania = data_pobrania;
	}
	public int getRodzaj() {
		return rodzaj;
	}
	public void setRodzaj(int rodzaj) {
		this.rodzaj = rodzaj;
	}
	public int getObjetosc() {
		return objetosc;
	}
	public void setObjetosc(int objetosc) {
		this.objetosc = objetosc;
	}
	public int getOpis() {
		return opis;
	}
	public void setOpis(int opis) {
		this.opis = opis;
	}
	public Fluid(int id, String data_pobrania, int rodzaj, int objetosc, int opis) {
		super();
		this.id = id;
		this.data_pobrania = data_pobrania;
		this.rodzaj = rodzaj;
		this.objetosc = objetosc;
		this.opis = opis;
	}
	public Fluid(String data_pobrania, int rodzaj, int objetosc, int opis) {
		super();
		this.data_pobrania = data_pobrania;
		this.rodzaj = rodzaj;
		this.objetosc = objetosc;
		this.opis = opis;
	}
	public Fluid(String data_pobrania, int rodzaj, int objetosc) {
		super();
		this.data_pobrania = data_pobrania;
		this.rodzaj = rodzaj;
		this.objetosc = objetosc;
	}
	public Fluid() {
	}
	
}

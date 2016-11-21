package com.project.service;

import java.util.List;

import com.project.domain.Badanie;

public interface IBadanieM {

	public boolean add_badanie(Badanie badanie);
	
	public List<Badania> get_all_badanie();
	
	public boolean update_badanie(String wynik, String nwynik);

	public boolean add_all_badanie(List<Badanie> badanie);
	
	public void clear_badanie();
	
	public int select_id_from_badanie(String wynik);
}
package com.project.service;

import java.util.List;

import com.project.domain.Fluid;

public interface IFluidM {
	
	public boolean add_fluid(Fluid fluid);	
	public boolean add_all_fluid(List<Fluid> fluid);
		
	public List<Fluid> get_all_fluid();
	
	public boolean update_fluid(Fluid sfluid, Fluid nfluid);

	public void clear_fluid();
	public boolean clear_fluid(Fluid fluid);
	
	public void set_badanie_for_fluid(Fluid fluid, String badanie_wynik);
	public List<Fluid> get_all_fluid_for_badanie(String wynik);
}

package main.java.com.project.service;

import java.util.List;

import main.java.com.project.domain.Fluid;
import main.java.com.project.domain.Donation;

public interface InterfaceFluidManager {
	public boolean add_fluid(Fluid fluid);
	public boolean add_all_fluids(List<Fluid> donations);
	public boolean update_fluid(Fluid old, Fluid new_fluid);
	public boolean clear_fluid(Fluid fluid);
	public void clear_fluids();
	public List<Fluid> get_all_fluids();
	public void set_donation_for_fluid(Fluid fluid, String donation_type);
	public List<Fluid> get_all_fluids_for_donation(String place);
}

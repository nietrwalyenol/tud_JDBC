package com.project.service;

import java.util.List;

import com.project.domain.Fluid;
import com.project.domain.Donation;

public interface InterfaceDonationManager {
	public boolean add_donation(Donation donation);

	public List<Donation> get_all_donations();

	public boolean update_donation(String place, String place_new);

	public boolean add_all_donations(List<Donation> donations);

	public void clear_donations();

	public int select_id_from_donations(String place);
}

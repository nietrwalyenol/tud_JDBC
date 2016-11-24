package com.project.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import com.project.domain.Fluid;
import com.project.domain.Donation;
import com.project.service.InterfaceFluidManager;
import com.project.service.InterfaceDonationManager;
import com.project.service.FluidManager;
import com.project.service.DonationManager;

import org.junit.Before;
import org.junit.Test;

public class DonationManagerTest {
	InterfaceDonationManager donationManager = new DonationManager();

	private final static String PLACE_1 = "Szpital";

	@Before
	public void clear_database(){
		donationManager.clear_donations();
	}

	@Test
	public void test_connection(){
		assertNotNull(((DonationManager)donationManager).getConnection());
	}

	@Test
	public void test_add_one(){
		Donation donation = new Donation(PLACE_1);
		assertEquals(true,donationManager.add_donation(donation));
		List<Donation> donations = donationManager.get_all_donations();
		Donation personRetrieved = donations.get(0);
		assertEquals(PLACE_1, personRetrieved.getPlace());
	}

	@Test
	public void test_delete_one(){
		donationManager.clear_donations();
		Donation donation = new Donation(PLACE_1);
		donationManager.add_donation(donation);

		assertEquals(1,((DonationManager)donationManager).delete_donation(donation));
	}

	@Test
	public void test_delete_all(){

		List<Donation> good = new ArrayList<Donation>();
		Donation d1 = new Donation("deleteit1");
		Donation d2 = new Donation("deleteit2");
		Donation d3 = new Donation("deleteit3");
		good.add(d1);
		good.add(d2);
		good.add(d3);

		donationManager.clear_donations();

		donationManager.add_all_donations(good);

		((DonationManager)donationManager).clear_donations();
		List<Donation> donations = donationManager.get_all_donations();
		assertEquals(0, donations.size());
	}

	@Test
	public void test_add_all(){
		List<Donation> good = new ArrayList<Donation>();
		Donation d1 = new Donation("Przychodnia");
		Donation d2 = new Donation("Punkt pobrania krwi");
		Donation d3 = new Donation("Piwnica lekarza");
		good.add(d1);
		good.add(d2);
		good.add(d3);

		donationManager.clear_donations();

		donationManager.add_all_donations(good);

		List<Donation> all = donationManager.get_all_donations();
		assertEquals(3, all.size());
	}

	@Test
	public void test_update(){
		donationManager.clear_donations();
		Donation donation = new Donation(PLACE_1);
		donationManager.add_donation(donation);
		assertEquals(true, donationManager.update_donation(donation.getPlace(), "Ambulans"));
	}
}

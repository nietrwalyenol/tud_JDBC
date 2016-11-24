package test.java.com.project.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import main.java.com.project.domain.Fluid;
import main.java.com.project.domain.Donation;
import main.java.com.project.service.InterfacFluidManager;
import main.java.com.project.service.InterfaceDonationManager;
import main.java.com.project.service.FluidManager;
import main.java.com.project.service.DonationManager;

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
		assertEquals(PLACE_1, personRetrieved.getType());
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

	@Test
	public void test_fluids_from_donation(){
		InterfaceFluidManager fm = new FluidManager();
		donationManager.add_donation(new Donation("Szpital wojewódzki"));

		int dm_id = donationManager.select_id_from_donation("Szpital wojewódzki");

		fm.add_fluid(new Fluid("Fluid1", 1,1,dm_id));
		fm.add_fluid(new Fluid("Fluid2", 1,1,dm_id));

		List<Fluid> fluids = fm.get_all_fluids_for_donation("Szpital wojewódzki");
		assertEquals(2, fluids.size());
		fm.clear_fluids();
	}
}

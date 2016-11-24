package com.project.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import com.project.domain.Fluid;
import com.project.domain.Donation;
import com.project.service.InterfaceFluidManager;
import com.project.service.FluidManager;
import com.project.service.DonationManager;

import org.junit.Before;
import org.junit.Test;

public class FluidManagerTest {
	DonationManager dm = new DonationManager();
	InterfaceFluidManager fluidManager = new FluidManager();

	@Before
	public void clear_database(){
		dm.clear_donations();
		fluidManager.clear_fluids();
	}

	private final static String FLUID_TYPE_1 = "Krew";

	@Test
	public void test_connection(){
		assertNotNull(((FluidManager)fluidManager).getConnection());
	}
	@Test
	public void test_add_one(){
		dm.add_donation(new Donation("Test Fluid"));
		Fluid fluid = new Fluid(FLUID_TYPE_1, 400, 200, dm.select_id_from_donations("Test Fluid"));
		assertEquals(true,fluidManager.add_fluid(fluid));

		List<Fluid> fluids = fluidManager.get_all_fluids();
		Fluid fluidRetrieved = fluids.get(0);
		assertEquals(FLUID_TYPE_1, fluidRetrieved.getFluid_type());
	}
	@Test
	public void test_add_all_ok(){
		dm.add_donation(new Donation("TestAddAll"));
		List<Fluid> good = new ArrayList<Fluid>();
		int donation_id = dm.select_id_from_donations("TestAddAll");
		Fluid f1 = new Fluid("Krew", 450, 225, donation_id);
		Fluid f2 = new Fluid("Limfa", 200, 150, donation_id);
		Fluid f3 = new Fluid("Osocze", 600, 800, donation_id);
		good.add(f1);
		good.add(f2);
		good.add(f3);

		fluidManager.add_all_fluids(good);

		List<Fluid> all = fluidManager.get_all_fluids();
		assertEquals(3, all.size());
	}

	@Test
	public void test_add_all_bad(){
		dm.add_donation(new Donation("TestAddAll"));
		int donation_id = dm.select_id_from_donations("TestAddAll");
		List<Fluid> bad = new ArrayList<Fluid>();
		Fluid f1 = new Fluid(FLUID_TYPE_1, 400, 200, donation_id);
		Fluid f2 = new Fluid(FLUID_TYPE_1, 400, 200, donation_id);
		Fluid f3 = new Fluid("Płyn MR", 20, 81, donation_id);
		bad.add(f1);
		bad.add(f2);
		bad.add(f3);

		fluidManager.add_all_fluids(bad);

		List<Fluid> all = fluidManager.get_all_fluids();
		assertEquals(0, all.size());
	}

	@Test
	public void test_update(){
		dm.add_donation(new Donation("Test Fluid"));
		dm.add_donation(new Donation("Test Fluid2"));
		int dm_id1 = dm.select_id_from_donations("Test Fluid");
		int dm_id2 = dm.select_id_from_donations("Test Fluid2");
		Fluid fluid = new Fluid(FLUID_TYPE_1, 200, 100, dm_id1);
		fluidManager.add_fluid(fluid);

		Fluid fluidNew = new Fluid(FLUID_TYPE_1, 100, 50, dm_id2);
		assertEquals(true,fluidManager.update_fluid(fluid, fluidNew));
	}

	@Test
	public void test_get_fluids(){
		dm.add_donation(new Donation("Test Fluid"));
		int donation_id = dm.select_id_from_donations("Test Fluid");
		for(int i = 0; i< 3; i++){
			Fluid fluid = new Fluid("Fluid "+i, 50*i, 25*i, donation_id);
			fluidManager.add_fluid(fluid);
		}
		List<Fluid> fluids =  fluidManager.get_all_fluids();
		assertEquals(3, fluids.size());
	}

	@Test
	public void test_delete_one(){
		dm.add_donation(new Donation("Test Fluid"));
		int donation_id = dm.select_id_from_donations("Test Fluid");
		Fluid fluid = new Fluid(FLUID_TYPE_1, 100, 50, donation_id);
		fluidManager.add_fluid(fluid);
		Fluid fluid2 = new Fluid("DeleteNO", 200, 100, donation_id);
		fluidManager.add_fluid(fluid2);

		fluidManager.clear_fluid(fluid);
		List<Fluid> fluids = fluidManager.get_all_fluids();
		assertEquals(1, fluids.size());
		Fluid check = fluids.get(0);
		assertEquals("DeleteNO", check.getFluid_type());
	}

	@Test
	public void test_delete(){
		dm.add_donation(new Donation("Test Fluid"));
		int donation_id = dm.select_id_from_donations("Test Fluid");
		Fluid fluid = new Fluid(FLUID_TYPE_1, 300, 150, donation_id);
		fluidManager.add_fluid(fluid);
		fluidManager.clear_fluids();
		List<Fluid> fluids = fluidManager.get_all_fluids();
		assertEquals(0, fluids.size());
	}

}

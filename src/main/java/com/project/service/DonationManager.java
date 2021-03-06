package com.project.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.project.domain.Fluid;
import com.project.domain.Donation;

public class DonationManager implements InterfaceDonationManager{
	private Connection connection;

	private String url = "jdbc:hsqldb:hsql://localhost/workdb";

	private String createTableDonation = "CREATE TABLE Donation(id bigint GENERATED BY DEFAULT AS IDENTITY, place varchar(20), PRIMARY KEY(id))";

	private PreparedStatement PS_add_donation;
	private PreparedStatement PS_delete_all;
	private PreparedStatement PS_delete_one;
	private PreparedStatement PS_get_all;
	private PreparedStatement PS_update;
	private PreparedStatement PS_select_id;
	private Statement statement;

	public DonationManager() {
		try {
			connection = DriverManager.getConnection(url);
			statement = connection.createStatement();

			ResultSet rs = connection.getMetaData().getTables(null, null, null,
					null);
			boolean tableExists = false;
			while (rs.next()) {
				if ("Donation".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
					tableExists = true;
					break;
				}
			}

			if (!tableExists)
				statement.executeUpdate(createTableDonation);

			PS_add_donation = connection.prepareStatement("INSERT INTO Donation (place) VALUES (?)");
			PS_delete_one = connection.prepareStatement("DELETE FROM Donation WHERE place=?");
			PS_delete_all = connection.prepareStatement("DELETE FROM Donation");
			PS_get_all = connection.prepareStatement("SELECT id, place FROM Donation");
			PS_update = connection.prepareStatement("UPDATE Donation SET place=? WHERE place=?");
			PS_select_id = connection.prepareStatement("SELECT id FROM Donation WHERE place=?;");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public void clear_donations() {
		try {

			PS_delete_all.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean add_donation(Donation donation) {
		int count = 0;
		try {
			PS_add_donation.setString(1, donation.getPlace());

			count = PS_add_donation.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(count == 1) return true;
		else return false;
	}

	public int delete_donation(Donation donation){
		int count = 0;
		try{
			PS_delete_one.setString(1, donation.getPlace());
			count = PS_delete_one.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public int select_id_from_donations(String place){
		int output = -1;
		try {
			PS_select_id.setString(1, place);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			ResultSet rs = PS_select_id.executeQuery();
			while (rs.next()) {
				output = rs.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return output;
	}

	public List<Donation> get_all_donations() {
		List<Donation> donations = new ArrayList<Donation>();

		try {
			ResultSet rs = PS_get_all.executeQuery();

			while (rs.next()) {
				Donation d = new Donation();
				d.setId(rs.getInt("id"));
				d.setPlace(rs.getString("place"));
				donations.add(d);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return donations;
	}

	public boolean add_all_donations(List<Donation> donations) {
		try {
			connection.setAutoCommit(false);

			for(Donation donation : donations){
				PS_add_donation.setString(1, donation.getPlace());
				PS_add_donation.executeUpdate();
			}

			connection.commit();
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			try {

				connection.rollback();
				connection.setAutoCommit(true);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

		return false;
	}

	public boolean update_donation(String place, String place_new) {
		int count = 0;
		try {
			PS_update.setString(2, place);
			PS_update.setString(1, place_new);

			count = PS_update.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(count == 1) return true;
		else return false;
	}
}

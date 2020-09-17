package com.cognixia.jumplus.dollarsbank.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cognixia.jumplus.dollarsbank.config.ConnectionManagerProperties;
import com.cognixia.jumplus.dollarsbank.dao.AddressDAO;
import com.cognixia.jumplus.dollarsbank.model.Address;

/**
 * The controller for address.
 * @author Lori White
 * @version v1 (09/17/2020)
 */
public class AddressController implements AddressDAO{
	private Connection conn = ConnectionManagerProperties.getConnection();
	/**
	 * Retrieves an address by id.
	 * @param id the id to search by
	 * @return Address - the address found by id
	 */
	@Override
	public Address getById(int id) {
		Address address = null;

		try(PreparedStatement pstmt = conn.prepareStatement("select * from dollars_bank.address where address_id = ?")) {

			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			if(rs.next()) {
				address = new Address( rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
			}
			pstmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return address;
	}
	/**
	 * Retrieves an address by a street and a zipcode.
	 * @param street the street to search by
	 * @param zip the zipcode to search by
	 * @return Address - the address found by a street and a zipcode
	 */
	@Override
	public Address getByStreetAndZipcode(String street, String zipcode) {
		Address address = null;

		try(PreparedStatement pstmt = conn.prepareStatement("select * from dollars_bank.address where street = ? and zip_code = ?")) {

			pstmt.setString(1, street);
			pstmt.setString(2, zipcode);

			ResultSet rs = pstmt.executeQuery();

			if(rs.next()) {
				address = new Address( rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
			}
			pstmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return address;
	}
	/**
	 * Finds whether an address exists based on the street and zipcode.
	 * @param street the street to search for
	 * @param zipcode the zipcode to search for
	 * @return boolean - whether an address exists based on the street and zipcode
	 */
	@Override
	public boolean existsByStreetAndZipcode(String street, String zipcode) {
		return getByStreetAndZipcode(street, zipcode) != null;
	}
	/**
	 * Adds an address.
	 * @param address the address to add
	 * @return Address - the added address
	 */
	@Override
	public Address add(Address address) {
		if(!existsByStreetAndZipcode(address.getStreet(), address.getZipcode())) {
			try {
				PreparedStatement pstmt = conn.prepareStatement("insert into dollars_bank.address values(?,?,?,?,?)");

				pstmt.setInt(1, address.getAddressId());
				pstmt.setString(2, address.getStreet());
				pstmt.setString(3, address.getCity());
				pstmt.setString(4, address.getState());
				pstmt.setString(5, address.getZipcode());

				int insert = pstmt.executeUpdate();

				if(insert > 0) {

					address = getByStreetAndZipcode(address.getStreet(), address.getZipcode());
					return address;
				}
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}

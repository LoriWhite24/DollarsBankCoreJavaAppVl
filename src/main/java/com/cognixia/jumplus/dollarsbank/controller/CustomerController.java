package com.cognixia.jumplus.dollarsbank.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cognixia.jumplus.dollarsbank.config.ConnectionManagerProperties;
import com.cognixia.jumplus.dollarsbank.dao.CustomerDAO;
import com.cognixia.jumplus.dollarsbank.model.Customer;

/**
 * The controller for customer.
 * @author Lori White
 * @version v1 (09/17/2020)
 */
public class CustomerController implements CustomerDAO{
	private Connection conn = ConnectionManagerProperties.getConnection();
	/**
	 * Retrieves a customer by id.
	 * @param id the customer id to search by
	 * @return Customer - the customer found by id
	 */
	@Override
	public Customer getById(String id) {
		Customer customer = null;

		// select * from student where student_id = ?
		try(PreparedStatement pstmt = conn.prepareStatement("select * from dollars_bank.customer where user_id = ?")) {

			pstmt.setString(1, id);

			ResultSet rs = pstmt.executeQuery();

			if(rs.next()) {
				
				customer = new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7));

			}

			pstmt.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}


		return customer;
	}
	/**
	 * Finds whether a customer exists by an id. 
	 * @param id the customer id to search by
	 * @return boolean - whether a customer exists by an id
	 */
	@Override
	public boolean existsById(String id) {
		return getById(id) != null;
	}
	/**
	 * Updates a customer.
	 * @param customer the customer to update
	 * @param column the specific column to update or if null update all
	 * @return boolean - whether the customer was updated 
	 */
	@Override
	public boolean update(Customer customer, String column) {
		try(PreparedStatement pstmt = conn.prepareStatement("update dollars_bank.customer set " + column + " = ? where user_id = ?")) {
			
			switch(column) {
				case "password":
					pstmt.setString(1, customer.getPassword());
				break;
				case "first_name":
					pstmt.setString(1, customer.getFirstName());
				break;
				case "last_name":
					pstmt.setString(1, customer.getLastName());
				break;
				case "email":
					pstmt.setString(1, customer.getEmail());
				break;
				case "phone_number":
					pstmt.setString(1, customer.getPhoneNumber());
				break;
				case "address_id":
					pstmt.setInt(1, customer.getAddressid());
				break;
			}
			
			pstmt.setString(2, customer.getUserId());

			int updated = pstmt.executeUpdate();

			if(updated > 0) {
				return true;
			}
			pstmt.close();
			
		} catch(SQLException e) {
			
			e.printStackTrace();
		}
		
		return false;
	}
	/**
	 * Adds a customer.
	 * @param customer the customer to add
	 * @return Customer - the added customer
	 */
	@Override
	public Customer add(Customer customer) {
		if(!existsById(customer.getUserId())) {
			try {
				PreparedStatement pstmt = conn.prepareStatement("insert into dollars_bank.customer values(?,?,?,?,?,?,?)");

				pstmt.setString(1, customer.getUserId());
				pstmt.setString(2, customer.getPassword());
				pstmt.setString(3, customer.getFirstName());
				pstmt.setString(4, customer.getLastName());
				pstmt.setString(5, customer.getEmail());
				pstmt.setString(6, customer.getPhoneNumber());
				pstmt.setInt(7,  customer.getAddressid());


				int insert = pstmt.executeUpdate();

				if(insert > 0) {
					return getById(customer.getUserId());
				}


				pstmt.close();

			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		
		return null;
	}
	/**
	 * Retrieves a customer by email.
	 * @param email the customer email to search by
	 * @return Customer - the customer found by email
	 */
	@Override
	public Customer getByEmail(String email) {
		Customer customer = null;

		// select * from student where student_id = ?
		try(PreparedStatement pstmt = conn.prepareStatement("select * from dollars_bank.customer where email = ?")) {

			pstmt.setString(1, email);

			ResultSet rs = pstmt.executeQuery();

			if(rs.next()) {
				
				customer = new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7));

			}

			pstmt.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}


		return customer;
	}
	/**
	 * Finds whether a customer exists by an email. 
	 * @param email the customer email to search by
	 * @return boolean - whether a customer exists by an email
	 */
	@Override
	public boolean existsByEmail(String email) {
		return getByEmail(email) != null;
	}
}

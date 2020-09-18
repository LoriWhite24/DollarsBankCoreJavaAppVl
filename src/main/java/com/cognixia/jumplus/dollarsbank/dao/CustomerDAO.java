package com.cognixia.jumplus.dollarsbank.dao;

import com.cognixia.jumplus.dollarsbank.model.Customer;

/**
 * The DAO for customer.
 * @author Lori White
 * @version v1 (09/16/2020)
 */
public interface CustomerDAO {
	/**
	 * Retrieves a customer by id.
	 * @param id the customer id to search by
	 * @return Customer - the customer found by id
	 */
	public Customer getById(String id);
	/**
	 * Finds whether a customer exists by an id. 
	 * @param id the customer id to search by
	 * @return boolean - whether a customer exists by an id
	 */
	public boolean existsById(String id);
	/**
	 * Retrieves a customer by email.
	 * @param email the customer email to search by
	 * @return Customer - the customer found by email
	 */
	public Customer getByEmail(String email);
	/**
	 * Finds whether a customer exists by an email. 
	 * @param email the customer email to search by
	 * @return boolean - whether a customer exists by an email
	 */
	public boolean existsByEmail(String email);
	/**
	 * Updates a customer.
	 * @param customer the customer to update
	 * @param column the specific column to update or if null update all
	 * @return boolean - whether the customer was updated 
	 */
	public boolean update(Customer customer, String column);
	/**
	 * Adds a customer.
	 * @param customer the customer to add
	 * @return Customer - the added customer
	 */
	public Customer add(Customer customer);
}

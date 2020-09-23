package com.cognixia.jumplus.dollarsbank.dao;

import java.util.List;

import com.cognixia.jumplus.dollarsbank.model.CustomerAccount;

/**
 * The DAO for customer_account.
 * @author Lori White
 * @version v1 (09/16/2020)
 */
public interface CustomerAccountDAO {
	/**
	 * Retrieves a list of customer-account objects by a customer id.
	 * @param customerId the customer's id to search for
	 * @return List - the list of customer-account objects by a customer id.
	 */
	public List<CustomerAccount> getByCustomer(String customerId);
	/**
	 * Retrieves a list of customer-account objects by an account id.
	 * @param accountId the account's id to search for
	 * @return List - the list of customer-account objects by an account id.
	 */
	public List<CustomerAccount> getByAccount(String accountId);
	/**
	 * Adds a customer linked to an account.
	 * @param customerAccount the customer linked to an account
	 * @return CustomerAccount - the added customer linked to an account
	 */
	public CustomerAccount add(CustomerAccount customerAccount);
	/**
	 * Removes a customer linking to an account.
	 * @param id the id of a customer and an account to remove
	 * @return boolean - whether the customer linking to an account was removed
	 */
	public boolean deleteById(int id);
}

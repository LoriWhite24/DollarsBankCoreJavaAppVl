package com.cognixia.jumplus.dollarsbank.model;

/**
 * The model for customer_account.
 * @author Lori White
 * @version v1 (09/16/2020)
 */
public class CustomerAccount {
	private Integer customerAccountId;
	private String customerId;
	private String accountId;
	/**
	 * Default constructor.
	 */
	public CustomerAccount() {
		this(-1, "N/A", "N/A");
	}
	/**
	 * Overloaded constructor.
	 * @param customerAccountId the id for a customer linked to an account
	 * @param customerId the customer's id
	 * @param accountId the account's id
	 */
	public CustomerAccount(Integer customerAccountId, String customerId, String accountId) {
		super();
		this.customerAccountId = customerAccountId;
		this.customerId = customerId;
		this.accountId = accountId;
	}
	/**
	 * Retrieves the id for a customer linked to an account.
	 * @return Integer - the id for a customer linked to an account
	 */
	public Integer getCustomerAccountId() {
		return customerAccountId;
	}
	/**
	 * Updates the id for a customer linked to an account.
	 * @param customerAccountId the id for a customer linked to an account
	 */
	public void setCustomerAccountId(Integer customerAccountId) {
		this.customerAccountId = customerAccountId;
	}
	/**
	 * Retrieves the customer's id.
	 * @return String - the customer's id
	 */
	public String getCustomerId() {
		return customerId;
	}
	/**
	 * Updates the customer's id.
	 * @param customerId the customer's id
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	/**
	 * Retrieves the account's id.
	 * @return String - the account's id
	 */
	public String getAccountId() {
		return accountId;
	}
	/**
	 * Updates the account's id.
	 * @param accountId the account's id
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	/**
	 * Generates a string representation for this model.
	 * @return String - the string representation for this model
	 */
	@Override
	public String toString() {
		return "CustomerAccount [customerAccountId=" + customerAccountId + ", customerId=" + customerId + ", accountId="
				+ accountId + "]";
	}
}

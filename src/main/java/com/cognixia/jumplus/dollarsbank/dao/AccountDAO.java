package com.cognixia.jumplus.dollarsbank.dao;

import com.cognixia.jumplus.dollarsbank.model.Account;

/**
 * The DAO for account.
 * @author Lori White
 * @version v1 (09/16/2020)
 */
public interface AccountDAO {
	/**
	 * Retrieves an account by account number.
	 * @param accountNumber the account's number to search by
	 * @return Account - the account found by account number
	 */
	public Account getByNumber(String accountNumber);
	/**
	 * Finds whether an account exists by account number.
	 * @param accountNumber the account's number to search by
	 * @return boolean - whether an account exists by account number
	 */
	public boolean existsByNumber(String accountNumber);
	/**
	 * Updates an account.
	 * @param account the account to update
	 * @param column the specific column to update or if null update all
	 * @return boolean - whether the account was updated
	 */
	public boolean update(Account account, String column);
	/**
	 * Adds an account.
	 * @param account the account to add
	 * @return Account - the account that was added
	 */
	public Account add(Account account);
}

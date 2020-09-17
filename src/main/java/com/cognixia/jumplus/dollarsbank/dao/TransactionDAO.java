package com.cognixia.jumplus.dollarsbank.dao;

import java.util.List;

import com.cognixia.jumplus.dollarsbank.model.Transaction;

/**
 * The DAO for transaction.
 * @author Lori White
 * @version v1 (09/16/2020)
 */
public interface TransactionDAO {
	/**
	 * Retrieves a list of transaction objects based on the account number.
	 * @param accountId the account number to search by
	 * @return List - the list of transaction objects based on the account number
	 */
	public List<Transaction> getByAccount(String accountId);
	/**
	 * Adds a transaction.
	 * @param transaction the transaction to add
	 * @return Transaction - the added transaction
	 */
	public Transaction add(Transaction transaction);
}

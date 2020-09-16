package com.cognixia.jumplus.dollarsbank.model;

import java.sql.Timestamp;

/**
 * The model for a savings account.
 * @author Lori White
 * @version v1 (09/16/2020)
 */
public class SavingsAccount extends Account{
	/**
	 * Default constructor.
	 */
	public SavingsAccount() {
		this("N/A", new Timestamp(0), new Timestamp(0), 0.0);
	}
	/**
	 * Overloaded constructor.
	 * @param accountNumber the account number
	 * @param dateOpened the date that the account was opened
	 * @param dateClosed the date that the account was closed
	 * @param balance the current account balance
	 */
	public SavingsAccount(String accountNumber, Timestamp dateOpened, Timestamp dateClosed, Double balance) {
		super(accountNumber, AccountType.SAVINGS, dateOpened, dateClosed, AccountType.SAVINGS.getInterestRate(), balance);
	}
}

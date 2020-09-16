package com.cognixia.jumplus.dollarsbank.model;

import java.sql.Timestamp;

/**
 * The model for a checking account.
 * @author Lori White
 * @version v1 (09/16/2020)
 */
public class CheckingAccount extends Account{
	/**
	 * Default constructor.
	 */
	public CheckingAccount() {
		this("N/A", new Timestamp(0), new Timestamp(0), 0.0);
	}
	/**
	 * Overloaded constructor.
	 * @param accountNumber the account number
	 * @param dateOpened the date that the account was opened
	 * @param dateClosed the date that the account was closed
	 * @param balance the current account balance
	 */
	public CheckingAccount(String accountNumber, Timestamp dateOpened, Timestamp dateClosed, Double balance) {
		super(accountNumber, AccountType.CHECKING, dateOpened, dateClosed, AccountType.CHECKING.getInterestRate(), balance);
	}
}

package com.cognixia.jumplus.dollarsbank.model;

import java.sql.Timestamp;

/**
 * The model for an account.
 * @author Lori White
 * @version v1 (09/16/2020)
 */
public abstract class Account {
	/**
	 * The enum for account types.
	 * @author Lori White
	 * @version v1 (09/16/2020)
	 */
	public enum AccountType {
		CHECKING("CHECKING_ACCOUNT", 0.0006), SAVINGS("SAVINGS_ACCOUNT", 0.0009);
		
		private final String value;
		private final Double interestRate;
		/**
		 * Overloaded constructor.
		 * @param value the String value of the account type
		 * @param interestRate the interest rate of the account type
		 */
		private AccountType(String value, Double interestRate) {
			this.value = value;
			this.interestRate = interestRate;
		}
		/**
		 * Retrieves the String value of the account type.
		 * @return String - the String value of the account type
		 */
		public String getValue() {
			return value;
		}
		/**
		 * Retrieves the interest rate of the account type.
		 * @return Double - the interest rate of the account type
		 */
		public Double getInterestRate() {
			return interestRate;
		}
	}
	
	private String accountNumber;
	private AccountType type;
	private Timestamp dateOpened;
	private Timestamp dateClosed;
	private Double interestRate;
	private Double balance;
	
	/**
	 * Overloaded constructor.
	 * @param accountNumber the account number
	 * @param type the type of account
	 * @param dateOpened the date that the account was opened
	 * @param dateClosed the date that the account was closed 
	 * @param interestRate the interest rate of accrued interest per month
	 * @param balance the current account balance
	 */
	public Account(String accountNumber, AccountType type, Timestamp dateOpened, Timestamp dateClosed,
			Double interestRate, Double balance) {
		super();
		this.accountNumber = accountNumber;
		this.type = type;
		this.dateOpened = dateOpened;
		this.dateClosed = dateClosed;
		this.interestRate = interestRate;
		this.balance = balance;
	}
	/**
	 * Retrieves the account number.
	 * @return String - the account number
	 */
	public String getAccountNumber() {
		return accountNumber;
	}
	/**
	 * Updates the account number
	 * @param accountNumber the account number
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	/**
	 * Retrieves the type of account.
	 * @return AccountType - the type of account
	 */
	public AccountType getType() {
		return type;
	}
	/**
	 * Updates the type of account.
	 * @param type the type of account
	 */
	public void setType(AccountType type) {
		this.type = type;
	}
	/**
	 * Retrieves the date that the account was opened.
	 * @return Timestamp - the date that the account was opened
	 */
	public Timestamp getDateOpened() {
		return dateOpened;
	}
	/**
	 * Updates the date that the account was opened.
	 * @param dateOpened the date that the account was opened
	 */
	public void setDateOpened(Timestamp dateOpened) {
		this.dateOpened = dateOpened;
	}
	/**
	 * Retrieves the date that the account was closed.
	 * @return Timestamp - the date that the account was closed
	 */
	public Timestamp getDateClosed() {
		return dateClosed;
	}
	/**
	 * Updates the date that the account was closed.
	 * @param dateClosed the date that the account was closed
	 */
	public void setDateClosed(Timestamp dateClosed) {
		this.dateClosed = dateClosed;
	}
	/**
	 * Retrieves the interest rate of accrued interest per month.
	 * @return Double - the interest rate of accrued interest per month
	 */
	public Double getInterestRate() {
		return interestRate;
	}
	/**
	 * Updates the interest rate of accrued interest per month.
	 * @param interestRate the interest rate of accrued interest per month
	 */
	public void setInterestRate(Double interestRate) {
		this.interestRate = interestRate;
	}
	/**
	 * Retrieves the current account balance.
	 * @return Double - the current account balance
	 */
	public Double getBalance() {
		return balance;
	}
	/**
	 * Updates the current account balance.
	 * @param balance the current account balance
	 */
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	/**
	 * Generates a string representation for this model.
	 * @return String - the string representation for this model
	 */
	@Override
	public String toString() {
		return "Account [accountNumber=" + accountNumber + ", type=" + type + ", dateOpened=" + dateOpened
				+ ", dateClosed=" + dateClosed + ", interestRate=" + interestRate + ", balance=" + balance + "]";
	}
}

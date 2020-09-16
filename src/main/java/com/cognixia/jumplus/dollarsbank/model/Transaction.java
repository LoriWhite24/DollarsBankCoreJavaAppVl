package com.cognixia.jumplus.dollarsbank.model;

import java.sql.Timestamp;

/**
 * The model for transaction.
 * @author Lori White
 * @version v1 (09/16/2020)
 */
public class Transaction {
	/**
	 * The enum for transaction types.
	 * @author Lori White
	 * @version v1 (09/16/2020)
	 */
	public enum TransactionType {
		DEPOSIT("DEPOSIT"), WITHDRAW("WITHDRAW"), TRANSFER("TRANSFER");
		
		private String value;
		/**
		 * Overloaded constructor.
		 * @param value the String value of the transaction type
		 */
		private TransactionType(String value) {
			this.value = value;
		}
		/**
		 * Retrieves the String value of the transaction type.
		 * @return String - the String value of the transaction type
		 */
		public String getValue() {
			return value;
		}
	}
	
	private Integer transactionId;
	private Timestamp timestamp;
	private Double amount;
	private TransactionType type;
	private String transactionDesc;
	private String customerId;
	private String accountId;
	/**
	 * Default constructor.
	 */
	public Transaction() {
		this(-1, new Timestamp(0), 0.0, TransactionType.DEPOSIT, "N/A", "N/A", "-00000000");
	}
	/**
	 * Overloaded constructor.
	 * @param transactionId the transaction's id
	 * @param timestamp the transaction's time stamp
	 * @param amount the transaction's amount
	 * @param type the type of transaction
	 * @param transactionDesc the transaction's description
	 * @param customerId the customer accociated with this transaction
	 * @param accountId the account accociated with this transaction
	 */
	public Transaction(Integer transactionId, Timestamp timestamp, Double amount, TransactionType type,
			String transactionDesc, String customerId, String accountId) {
		super();
		this.transactionId = transactionId;
		this.timestamp = timestamp;
		this.amount = amount;
		this.type = type;
		this.transactionDesc = transactionDesc;
		this.customerId = customerId;
		this.accountId = accountId;
	}
	/**
	 * Retrieves the transaction's id.
	 * @return Integer - the transaction's id
	 */
	public Integer getTransactionId() {
		return transactionId;
	}
	/**
	 * Updates the transaction's id.
	 * @param transactionId the transaction's id
	 */
	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}
	/**
	 * Retrieves the transaction's time stamp.
	 * @return Timestamp - the transaction's time stamp
	 */
	public Timestamp getTimestamp() {
		return timestamp;
	}
	/**
	 * Updates the transaction's time stamp.
	 * @param timestamp the transaction's time stamp
	 */
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	/**
	 * Retrieves the transaction's amount.
	 * @return Double - the transaction's amount
	 */
	public Double getAmount() {
		return amount;
	}
	/**
	 * Updates the transaction's amount.
	 * @param amount the transaction's amount
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	/**
	 * Retrieves the type of transaction.
	 * @return TransactionType - the type of transaction
	 */
	public TransactionType getType() {
		return type;
	}
	/**
	 * Updates the type of transaction.
	 * @param type the type of transaction
	 */
	public void setType(TransactionType type) {
		this.type = type;
	}
	/**
	 * Retrieves the transaction's description
	 * @return String - the transaction's description
	 */
	public String getTransactionDesc() {
		return transactionDesc;
	}
	/**
	 * Updates the transaction's description.
	 * @param transactionDesc the transaction's description
	 */
	public void setTransactionDesc(String transactionDesc) {
		this.transactionDesc = transactionDesc;
	}
	/**
	 * Retrieves the customer accociated with this transaction.
	 * @return String - the customer accociated with this transaction
	 */
	public String getCustomerId() {
		return customerId;
	}
	/**
	 * Updates the customer accociated with this transaction.
	 * @param customerId the customer accociated with this transaction
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	/**
	 * Retrieves the account accociated with this transaction.
	 * @return String - the account accociated with this transaction
	 */
	public String getAccountId() {
		return accountId;
	}
	/**
	 * Updates the account accociated with this transaction.
	 * @param accountId the account accociated with this transaction
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
		return "Transaction [transactionId=" + transactionId + ", timestamp=" + timestamp + ", amount=" + amount
				+ ", type=" + type + ", transactionDesc=" + transactionDesc + ", customerId=" + customerId
				+ ", accountId=" + accountId + "]";
	}	
}

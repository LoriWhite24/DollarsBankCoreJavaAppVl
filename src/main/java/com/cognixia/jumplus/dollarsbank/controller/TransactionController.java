package com.cognixia.jumplus.dollarsbank.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cognixia.jumplus.dollarsbank.config.ConnectionManagerProperties;
import com.cognixia.jumplus.dollarsbank.dao.TransactionDAO;
import com.cognixia.jumplus.dollarsbank.model.Transaction;
import com.cognixia.jumplus.dollarsbank.model.Transaction.TransactionType;

/**
 * The controller for transaction.
 * @author Lori White
 * @version v1 (09/17/2020)
 */
public class TransactionController implements TransactionDAO{
	private Connection conn = ConnectionManagerProperties.getConnection();
	/**
	 * Retrieves a list of transaction objects based on the account number.
	 * @param accountId the account number to search by
	 * @return List - the list of transaction objects based on the account number
	 */
	@Override
	public List<Transaction> getByAccount(String accountId) {
		List<Transaction> transactionList = new ArrayList<Transaction>();
		TransactionType type = null;
		
		try(PreparedStatement pstmt = conn.prepareStatement("select * from dollars_bank.transaction where account_id = ?")) { 
			
			pstmt.setString(1,  accountId);
			
			ResultSet rs = pstmt.executeQuery(); 
			
			while(rs.next()) {
				switch(rs.getString(4)) {
					case "DEPOSIT":
						type = TransactionType.DEPOSIT;
					break;
					case "WITHDRAW":
						type = TransactionType.WITHDRAW;
					break;
					case "TRANSFER":
						type = TransactionType.TRANSFER;
					break;
				}
				// add to list
				transactionList.add(new Transaction(rs.getInt(1), rs.getTimestamp(2), rs.getDouble(3), type, rs.getString(5), rs.getString(6), rs.getString(7)));
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return transactionList;
	}
	/**
	 * Adds a transaction.
	 * @param transaction the transaction to add
	 * @return Transaction - the added transaction
	 */
	@Override
	public Transaction add(Transaction transaction) {
		try {
			PreparedStatement pstmt = conn.prepareStatement("insert into dollars_bank.transaction values(?,?,?,?,?,?,?)");

			pstmt.setInt(1, transaction.getTransactionId());
			pstmt.setTimestamp(2, transaction.getTimestamp());
			pstmt.setDouble(3, transaction.getAmount());
			pstmt.setString(4, transaction.getType().getValue());
			pstmt.setString(5, transaction.getTransactionDesc());
			pstmt.setString(6, transaction.getCustomerId());
			pstmt.setString(7, transaction.getAccountId());

			int insert = pstmt.executeUpdate();

			if(insert > 0) {
				try(PreparedStatement stmt = conn.prepareStatement("select count(*) from dollars_bank.transaction")) {
					ResultSet rs = stmt.executeQuery();
					while(rs.next()) {
						transaction.setTransactionId(rs.getInt(1) + 1);
					}
					stmt.close();
					return transaction;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}

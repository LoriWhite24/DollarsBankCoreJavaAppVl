package com.cognixia.jumplus.dollarsbank.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cognixia.jumplus.dollarsbank.config.ConnectionManagerProperties;
import com.cognixia.jumplus.dollarsbank.dao.AccountDAO;
import com.cognixia.jumplus.dollarsbank.model.Account;
import com.cognixia.jumplus.dollarsbank.model.CheckingAccount;
import com.cognixia.jumplus.dollarsbank.model.SavingsAccount;

/**
 * The controller for account.
 * @author Lori White
 * @version v1 (09/17/2020)
 */
public class AccountController implements AccountDAO{
	private Connection conn = ConnectionManagerProperties.getConnection();
	/**
	 * Retrieves an account by account number.
	 * @param accountNumber the account's number to search by
	 * @return Account - the account found by account number
	 */
	@Override
	public Account getByNumber(String accountNumber) {
		Account account = null;

		// select * from student where student_id = ?
		try(PreparedStatement pstmt = conn.prepareStatement("select * from dollars_bank.account where account_number = ?")) {

			pstmt.setString(1, accountNumber);

			ResultSet rs = pstmt.executeQuery();

			if(rs.next()) {
				switch(rs.getString(2)) {
				case "CHECKING_ACCOUNT":
					account = new CheckingAccount(rs.getString(1), rs.getTimestamp(3), rs.getTimestamp(4), rs.getDouble(6));
				break;
				case "SAVINGS_ACCOUNT":
					account = new SavingsAccount(rs.getString(1), rs.getTimestamp(3), rs.getTimestamp(4), rs.getDouble(6));
				break;
			}
				
			}

			pstmt.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}


		return account;
	}
	/**
	 * Finds whether an account exists by account number.
	 * @param accountNumber the account's number to search by
	 * @return boolean - whether an account exists by account number
	 */
	@Override
	public boolean existsByNumber(String accountNumber) {
		return getByNumber(accountNumber) != null;
	}
	/**
	 * Updates an account.
	 * @param account the account to update
	 * @param column the specific column to update or if null update all
	 * @return boolean - whether the account was updated
	 */
	@Override
	public boolean update(Account account, String column) {
		try(PreparedStatement pstmt = conn.prepareStatement("update dollars_bank.account set " + column + " = ? where account_number = ?")) {
			
			switch(column) {
				case "date_opened":
					pstmt.setTimestamp(1, account.getDateOpened());
				break;
				case "date_closed":
					pstmt.setTimestamp(1, account.getDateClosed());
				break;
				case "interest_rate":
					pstmt.setDouble(1, account.getInterestRate());
				break;
				case "balance":
					pstmt.setDouble(1, account.getBalance());
				break;
			}
			
			pstmt.setString(2, account.getAccountNumber());

			int updated = pstmt.executeUpdate();

			if(updated > 0) {
				return true;
			}
			pstmt.close();
			
		} catch(SQLException e) {
			
			e.printStackTrace();
		}
		
		return false;
	}
	/**
	 * Adds an account.
	 * @param account the account to add
	 * @return Account - the account that was added
	 */
	@Override
	public Account add(Account account) {
		if(!existsByNumber(account.getAccountNumber())) {
			try {
				PreparedStatement pstmt = conn.prepareStatement("insert into dollars_bank.account values(?,?,?,?,?,?)");

				pstmt.setString(1, account.getAccountNumber());
				pstmt.setString(2, account.getType().getValue());
				pstmt.setTimestamp(3, account.getDateOpened());
				pstmt.setTimestamp(4, account.getDateClosed());
				pstmt.setDouble(5, account.getInterestRate());
				pstmt.setDouble(6, account.getBalance());

				int insert = pstmt.executeUpdate();

				if(insert > 0) {
					return getByNumber(account.getAccountNumber());
				}


				pstmt.close();

			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		
		return null;
	}
}

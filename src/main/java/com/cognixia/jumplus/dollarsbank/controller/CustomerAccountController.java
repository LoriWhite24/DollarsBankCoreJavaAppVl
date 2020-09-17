package com.cognixia.jumplus.dollarsbank.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cognixia.jumplus.dollarsbank.config.ConnectionManagerProperties;
import com.cognixia.jumplus.dollarsbank.dao.CustomerAccountDAO;
import com.cognixia.jumplus.dollarsbank.model.CustomerAccount;

/**
 * The controller for customer_account.
 * @author Lori White
 * @version v1 (09/17/2020)
 */
public class CustomerAccountController implements CustomerAccountDAO{
	private Connection conn = ConnectionManagerProperties.getConnection();
	/**
	 * Retrieves a list of customer-account objects by a customer id.
	 * @param customerId the customer's id to search for
	 * @return List - the list of customer-account objects by a customer id.
	 */
	@Override
	public List<CustomerAccount> getByCustomer(String customerId) {
		List<CustomerAccount> customerAccountList = new ArrayList<CustomerAccount>();
		
		try(PreparedStatement pstmt = conn.prepareStatement("select * from dollars_bank.customer_account where customer_id = ?")) { 
			
			pstmt.setString(1,  customerId);
			
			ResultSet rs = pstmt.executeQuery(); 
			
			while(rs.next()) {
				// add to list
				customerAccountList.add(new CustomerAccount(rs.getInt(1), rs.getString(2), rs.getString(3)));
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return customerAccountList;
	}
	/**
	 * Retrieves a list of customer-account objects by an account id.
	 * @param accountId the account's id to search for
	 * @return List - the list of customer-account objects by an account id.
	 */
	@Override
	public List<CustomerAccount> getByAccount(String accountId) {
		List<CustomerAccount> customerAccountList = new ArrayList<CustomerAccount>();
		
		try(PreparedStatement pstmt = conn.prepareStatement("select * from dollars_bank.customer_account where account_id = ?")) { 
			
			pstmt.setString(1,  accountId);
			
			ResultSet rs = pstmt.executeQuery(); 
			
			while(rs.next()) {
				// add to list
				customerAccountList.add(new CustomerAccount(rs.getInt(1), rs.getString(2), rs.getString(3)));
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return customerAccountList;
	}
}

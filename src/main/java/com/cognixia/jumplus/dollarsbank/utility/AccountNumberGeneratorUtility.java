package com.cognixia.jumplus.dollarsbank.utility;

import java.util.Random;

import com.cognixia.jumplus.dollarsbank.controller.AccountController;
import com.cognixia.jumplus.dollarsbank.dao.AccountDAO;

/**
 * The utility for generating account numbers for this app.
 * @author Lori White
 * @version v1 (09/17/2020)
 */
public class AccountNumberGeneratorUtility {
	/**
	 * Generates an unique account number. 
	 * @return String - the unique account number
	 */
	public static String generateNewAccountNumber() {
		AccountDAO repo = new AccountController();
		
		Random r = new Random();
		
		int length = r.nextInt((17 - 8) + 1) + 8;
		Integer digit;
		
		String number = "";
		
		do {
			for(int i = 0; i < length; i++)
			{
				digit = r.nextInt(10);
				
				number += digit.toString();
			}
		} while(repo.existsByNumber(number));
		
		return number;
	}
	/**
	 * Tests the integration of this class's generateNewAccountNumber function with the dollars_bank database.
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		System.out.println(generateNewAccountNumber());
	}
}

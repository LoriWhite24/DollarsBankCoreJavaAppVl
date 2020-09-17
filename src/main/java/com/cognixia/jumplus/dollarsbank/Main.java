package com.cognixia.jumplus.dollarsbank;

import java.util.Scanner;

import com.cognixia.jumplus.dollarsbank.controller.AccountController;
import com.cognixia.jumplus.dollarsbank.controller.AddressController;
import com.cognixia.jumplus.dollarsbank.controller.CustomerAccountController;
import com.cognixia.jumplus.dollarsbank.controller.CustomerController;
import com.cognixia.jumplus.dollarsbank.controller.TransactionController;
import com.cognixia.jumplus.dollarsbank.dao.AccountDAO;
import com.cognixia.jumplus.dollarsbank.dao.AddressDAO;
import com.cognixia.jumplus.dollarsbank.dao.CustomerAccountDAO;
import com.cognixia.jumplus.dollarsbank.dao.CustomerDAO;
import com.cognixia.jumplus.dollarsbank.dao.TransactionDAO;
import com.cognixia.jumplus.dollarsbank.model.Customer;
import com.cognixia.jumplus.dollarsbank.utility.ColorsUtility;
import com.cognixia.jumplus.dollarsbank.utility.ConsolePrinterUtility;
import com.cognixia.jumplus.dollarsbank.utility.DatabaseSetupUtility;

/**
 * Dollars Bank Core Java Application main functionality.
 * @author Lori White
 * @version v1 (09/16/2020)
 */
public class Main {
	private static AccountDAO accountRepo = new AccountController();
	private static AddressDAO addressRepo = new AddressController();
	private static CustomerAccountDAO customerAccountRepo = new CustomerAccountController();
	private static CustomerDAO customerRepo = new CustomerController();
	private static TransactionDAO transactionRepo = new TransactionController();
	
	private static Scanner in = new Scanner(System.in);
	
	/**
	 * The second entry point for this App.
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		boolean quit = false, loggedIn = false;
		int response;
		DatabaseSetupUtility.clrscr();
        //DatabaseSetupUtility.setup(".\\dollars_bank.sql");
        do {
        	ConsolePrinterUtility.header("top");
        	ConsolePrinterUtility.menu("top");
        	do {
        		ConsolePrinterUtility.menuChoice("top");
            	response = Integer.parseInt(in.nextLine().trim());
            	if(response > 3 || response < 1) {
            		ConsolePrinterUtility.error("input");
            	}
        	} while(response > 3 || response < 1);
        	switch(response) {
        	case 1:
        		ConsolePrinterUtility.header("new_account");
        		loggedIn = createNewAccount();
        		break;
        	case 2:
        		ConsolePrinterUtility.header("login");
        		loggedIn = login();
        		break;
        	}
        	if(loggedIn) {
        		ConsolePrinterUtility.header("logged_in");
            	ConsolePrinterUtility.menu("logged_in");
        		do {
            		ConsolePrinterUtility.menuChoice("logged_in");
                	response = Integer.parseInt(in.nextLine().trim());
                	if(response > 6 || response < 1) {
                		ConsolePrinterUtility.error("input");
                	}
            	} while(response > 6 || response < 1);
        		switch(response) {
            	case 1:
            		ConsolePrinterUtility.header("deposit");
            		break;
            	case 2:
            		ConsolePrinterUtility.header("withdraw");
            		break;
            	case 3:
            		ConsolePrinterUtility.header("transfer");
            		break;
            	case 4:
            		ConsolePrinterUtility.header("transactions");
            		break;
            	case 5:
            		ConsolePrinterUtility.header("customer");
            		break;
            	}
        		if(response == 6) {
            		loggedIn = false;
            	}
        	}
        	if(response == 3) {
        		quit = true;
        	}
        } while(!quit);
        in.close();
	}
	/**
	 * 
	 * @return
	 */
	private static boolean login() {
		String username = "", password = "";
		int counter = 0, max = 7; 
			do {
				ColorsUtility.colorDefault("User Id:");
				username = in.nextLine().trim();
				if(!customerRepo.existsById(username)) {
					ConsolePrinterUtility.error("login");
					counter++;
				}
				if(counter == max) {
					break;
				}
			} while(!customerRepo.existsById(username));

			Customer temp = customerRepo.getById(username);
			do {
				if(counter == max) {
					break;
				}
				ColorsUtility.colorDefault("Password:");
				password = in.nextLine().trim();
				if(!temp.getPassword().equals(password)) {
					ConsolePrinterUtility.error("login");
					counter++;
				} else {
					return true;
				}				
			} while(!temp.getPassword().equals(password));

		return false;
	}

	private static boolean createNewAccount() {
		
		return false;
	}
}

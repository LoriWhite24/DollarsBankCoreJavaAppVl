package com.cognixia.jumplus.dollarsbank;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import com.cognixia.jumplus.dollarsbank.model.Account;
import com.cognixia.jumplus.dollarsbank.model.Address;
import com.cognixia.jumplus.dollarsbank.model.CheckingAccount;
import com.cognixia.jumplus.dollarsbank.model.Customer;
import com.cognixia.jumplus.dollarsbank.model.CustomerAccount;
import com.cognixia.jumplus.dollarsbank.model.SavingsAccount;
import com.cognixia.jumplus.dollarsbank.model.Transaction;
import com.cognixia.jumplus.dollarsbank.model.Transaction.TransactionType;
import com.cognixia.jumplus.dollarsbank.utility.AccountNumberGeneratorUtility;
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
	private static Customer current = null;
	
	/**
	 * The second entry point for this App.
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		boolean quit = false, loggedIn = false;
		int response, choice;
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
        	case 3:
        		quit = true;
        		break;
        	}
        	if(loggedIn) {
        		do {
        			ConsolePrinterUtility.header("logged_in");
        			ConsolePrinterUtility.menu("logged_in");
        			do {
        				ConsolePrinterUtility.menuChoice("logged_in");
        				choice = Integer.parseInt(in.nextLine().trim());
        				if(choice > 6 || choice < 1) {
        					ConsolePrinterUtility.error("input");
        				}
        			} while(choice > 6 || choice < 1);
        			switch(choice) {
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
        				viewTransactions();
        				break;
        			case 5:
        				ConsolePrinterUtility.header("customer");
        				displayCustomer();
        				break;
        			case 6:
        				loggedIn = false;
        				current = null;
        				break;
        			}
        		} while(loggedIn);
        	}
        } while(!quit);
        in.close();
	}
	/**
	 * Displays the user info.
	 */
	private static void displayCustomer() {
		Address address = addressRepo.getById(current.getAddressid());
		ColorsUtility.colorOutput("Login Information:\nUser Id: " + current.getUserId() + " Password: " + current.getPassword() + "\nName: " + current.getFirstName() + ", " + current.getLastName() + "\nContact Information:\nEmail: " + current.getEmail() + " Phone Number: " + current.getPhoneNumber() + "\nAddress:\n" + address.getStreet() + " " + address.getCity() + ", " + address.getState() + " " + address.getZipcode());
	}
	/**
	 * Shows the users latest transactions.
	 */
	private static void viewTransactions() {
		List<CustomerAccount> accounts = customerAccountRepo.getByCustomer(current.getUserId());
		List<Transaction> list;
		DecimalFormat df = new DecimalFormat("#,###,##0.00");
		
		for(CustomerAccount ca : accounts) {
			ColorsUtility.colorOutput("Account " + ca.getAccountId() + ":");
			list = transactionRepo.getByAccount(ca.getAccountId());
			for(Transaction t : list) {
				ColorsUtility.colorOutput("Date: " + t.getTimestamp() + " Type: " + t.getType() + " Description: " + t.getTransactionDesc() + " Amount: $" + df.format(t.getAmount()));
			}
		}	 
	}
	/**
	 * Prompts a user to login.
	 * @return boolean - whether the user is logged in
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
					current = customerRepo.getById(username);
					return true;
				}				
			} while(!temp.getPassword().equals(password));

		return false;
	}
	/**
	 * Prompts a user to create a new user account and a new bank account.
	 * @return boolean - whether the user is logged in
	 */
	private static boolean createNewAccount() {
		String accountType = "", username = "", password = "", fname = "", lname = "", email = "", phone = "", street = "", city = "", state = "", zip = "", accountNum = AccountNumberGeneratorUtility.generateNewAccountNumber();
		Timestamp dateO = Timestamp.valueOf(LocalDateTime.now());
		Double amount = 0.0;
		Pattern passPattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,}$"), emailPattern = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$"), phonePattern = Pattern.compile("^(\\+0?1\\s)?\\(?\\d{3}\\)?[\\s.-]\\d{3}[\\s.-]\\d{4}$"), streetPattern = Pattern.compile("\\d+\\s+([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)"), zipPattern = Pattern.compile("^[0-9]{5}(?:-[0-9]{4})?$"), statePattern = Pattern.compile("^[A-Z]{2}$");
        Matcher matcher;
        Address address;
        Account account = null;
        Customer cust;
		
		do {
			ColorsUtility.colorDefault("User Id: (must be at least 8 characters)");
			username = in.nextLine().trim();
			if(username.length() < 8 || customerRepo.existsById(username)) {
				ConsolePrinterUtility.error("input");
			} 			
		} while(username.length() < 8 || customerRepo.existsById(username));
		do {
			ColorsUtility.colorDefault("Password: (must be at least 8 characters with a digit, a lowercase letter, an Uppercase letter, and a special character)");
			password = in.nextLine().trim();
			matcher = passPattern.matcher(password);
			if(!matcher.matches()) {
				ConsolePrinterUtility.error("input");
			} 			
		} while(!matcher.matches());
		do {
			ColorsUtility.colorDefault("First Name:");
			fname = in.nextLine().trim();
			if(fname.equals("")) {
				ConsolePrinterUtility.error("input");
			} 			
		} while(fname.equals(""));
		do {
			ColorsUtility.colorDefault("Last Name:");
			lname = in.nextLine().trim();
			if(lname.equals("")) {
				ConsolePrinterUtility.error("input");
			} 			
		} while(lname.equals(""));
		do {
			ColorsUtility.colorDefault("Email:");
			email = in.nextLine().trim();
			matcher = emailPattern.matcher(email);
			if(!matcher.matches() || customerRepo.existsByEmail(email)) {
				ConsolePrinterUtility.error("input");
			} 			
		} while(!matcher.matches() || customerRepo.existsByEmail(email));
		do {
			ColorsUtility.colorDefault("Phone Number:");
			phone = in.nextLine().trim();
			matcher = phonePattern.matcher(phone);
			if(!matcher.matches()) {
				ConsolePrinterUtility.error("input");
			} 			
		} while(!matcher.matches());
		do {
			ColorsUtility.colorDefault("Street Address:");
			street = in.nextLine().trim();
			matcher = streetPattern.matcher(street);
			if(!matcher.matches()) {
				ConsolePrinterUtility.error("input");
			} 			
		} while(!matcher.matches());
		do {
			ColorsUtility.colorDefault("City:");
			city = in.nextLine().trim();
			if(city.equals("")) {
				ConsolePrinterUtility.error("input");
			} 			
		} while(city.equals(""));
		do {
			ColorsUtility.colorDefault("State:");
			state = in.nextLine().trim();
			matcher = statePattern.matcher(state);
			if(!matcher.matches()) {
				ConsolePrinterUtility.error("input");
			} 			
		} while(!matcher.matches());
		do {
			ColorsUtility.colorDefault("Zipcode:");
			zip = in.nextLine().trim();
			matcher = zipPattern.matcher(zip);
			if(!matcher.matches()) {
				ConsolePrinterUtility.error("input");
			} 			
		} while(!matcher.matches());
		do {
			ColorsUtility.colorDefault("Account Type:");
			accountType = in.nextLine().trim().toLowerCase();
			if(accountType.charAt(0) != 'c' && accountType.charAt(0) != 's') {
				ConsolePrinterUtility.error("input");
			} 			
		} while(accountType.charAt(0) != 'c' && accountType.charAt(0) != 's');
		do {
			ColorsUtility.colorDefault("Initial Amount:");
			amount = Double.parseDouble(in.nextLine().trim());
			if(amount <= 0.0) {
				ConsolePrinterUtility.error("input");
			} 			
		} while(amount <= 0.0);
		
		if(addressRepo.existsByStreetAndZipcode(street, zip)) {
			address = addressRepo.getByStreetAndZipcode(street, zip);
		} else {
			address = addressRepo.add(new Address(0, street, city, state, zip));
		}
		
		cust = customerRepo.add(new Customer(username, password, fname, lname, email, phone, address.getAddressId()));
		
		switch(accountType.charAt(0)) {
		case 'c':
			account = new CheckingAccount(accountNum, dateO, null, amount);
			break;
		case 's':
			account = new SavingsAccount(accountNum, dateO, null, amount);
			break;
		}
		
		account = accountRepo.add(account);
		
		customerAccountRepo.add(new CustomerAccount(0, cust.getUserId(), account.getAccountNumber()));
		
		transactionRepo.add(new Transaction(0, dateO, amount, TransactionType.DEPOSIT, "Initial Deposit", cust.getUserId(), account.getAccountNumber()));
		
		return true;
	}
}

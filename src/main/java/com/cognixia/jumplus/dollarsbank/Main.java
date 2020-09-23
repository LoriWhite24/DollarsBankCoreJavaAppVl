package com.cognixia.jumplus.dollarsbank;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.DefaultEditorKit.CutAction;

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
	private static Customer currentCustomer = null;
	private static Account currentAccount = null;
	private static List<CustomerAccount> currentCustomerAccounts = null, currentOtherCustomerAccounts = null;
	private static List<Customer> currentOtherCustomers = new ArrayList<Customer>();
	
	@SuppressWarnings("serial")
	private static List<String> topMenu = new ArrayList<String>() {{add("Sign Up"); add("Login"); add("Exit");}}, loggedInMenu = new ArrayList<String>() {{add("Display Customer Information"); add("Update Customer Information"); add("Open a New Bank Account"); add("Display Account(s)"); add("Sign Out");}}, accounts = new ArrayList<String>(), accountMenu = new ArrayList<String>() {{add("Other Customers Linked to this Account"); add("Deposit Amount"); add("Withdraw Amount"); add("Funds Transfer"); add("View Recent Transactions"); add("Close Account"); add("Back");}}, currentOtherCustomersMenu = new ArrayList<String>(), namesMenu = new ArrayList<String>(), updateMenu = new ArrayList<String>() {{add("Password"); add("First Name"); add("Last Name"); add("Email"); add("Phone Number"); add("Address"); add("Back");}};
	private static DecimalFormat df = new DecimalFormat("#,###,##0.00");
	private static final int minChoice = 1;
	/**
	 * The second entry point for this App.
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		boolean quit = false, loggedIn = false, wentToAccounts = false, selectedAccount = false;
		int response;

        //DatabaseSetupUtility.setup(".\\dollars_bank.sql");
        do {
        	DatabaseSetupUtility.clrscr();
        	ConsolePrinterUtility.header("top");
        	ConsolePrinterUtility.menu(topMenu);
        	do {
        		ConsolePrinterUtility.menuChoice(topMenu.size());
            	response = Integer.parseInt(in.nextLine().trim());
            	if(response > topMenu.size() || response < minChoice) {
            		ConsolePrinterUtility.error("input");
            	}
        	} while(response > topMenu.size() || response < minChoice);
        	DatabaseSetupUtility.clrscr();
        	switch(response) {
        	case 1:
        		ConsolePrinterUtility.header("new_user_account");
        		loggedIn = createNewAccount(true);
        		DatabaseSetupUtility.clrscr();
        		break;
        	case 2:
        		ConsolePrinterUtility.header("login");
        		loggedIn = login();
        		DatabaseSetupUtility.clrscr();
        		break;
        	case 3:
        		quit = true;
        		break;
        	}
        	if(loggedIn) {
        		do {
        			ConsolePrinterUtility.header("logged_in");
        			ConsolePrinterUtility.menu(loggedInMenu);
        			do {
        				ConsolePrinterUtility.menuChoice(loggedInMenu.size());
        				response = Integer.parseInt(in.nextLine().trim());
        				if(response > loggedInMenu.size() || response < minChoice) {
        					ConsolePrinterUtility.error("input");
        				}
        			} while(response > loggedInMenu.size() || response < minChoice);
        			DatabaseSetupUtility.clrscr();
        			switch(response) {
        			case 1:
        				ConsolePrinterUtility.header("customer");
        				displayCustomer();
        				break;
        			case 2:
        				updateCustomer();
        				break;
        			case 3:
        				ConsolePrinterUtility.header("new_account");
        				createNewAccount(false);
        				break;
        			case 4: 
        				wentToAccounts = true;
        				currentCustomerAccounts = customerAccountRepo.getByCustomer(currentCustomer.getUserId());
        				for(CustomerAccount ca : currentCustomerAccounts) {
        					accounts.add(ca.getAccountId());
        				}
        				accounts.add("Back");
        				break;
        			case 5:
        				loggedIn = false;
        				currentCustomer = null;
        				break;
        			}
        			if(wentToAccounts) {
        				do {
        					ConsolePrinterUtility.header("accounts");
        					do {
        						ConsolePrinterUtility.menu(accounts);
        						ConsolePrinterUtility.menuChoice(accounts.size());
        						response = Integer.parseInt(in.nextLine().trim());
        						if(response < minChoice || response > accounts.size()) {
        							ConsolePrinterUtility.error("input");
        						}
        					} while(response < minChoice || response > accounts.size());
        					DatabaseSetupUtility.clrscr();
        					if(response == accounts.size()) {
        						accounts.removeAll(accounts);
        						currentCustomerAccounts = null;
        						wentToAccounts = false;
        					} else {
            					currentAccount = accountRepo.getByNumber(currentCustomerAccounts.get(response - 1).getAccountId());
            					selectedAccount = true;
            					if(currentAccount.getDateClosed() == null) {
            						do {
                						ColorsUtility.colorDefault("Account " + currentAccount.getAccountNumber() + ":");
                						ColorsUtility.colorOutput("Type: " + currentAccount.getType().getValue() + " Current Balance: $" + df.format(currentAccount.getBalance()));
                						ConsolePrinterUtility.header("account");
                    					do {
                    						ConsolePrinterUtility.menu(accountMenu);
                    						ConsolePrinterUtility.menuChoice(accountMenu.size());
                    						response = Integer.parseInt(in.nextLine().trim());
                    						if(response < minChoice || response > accountMenu.size()) {
                    							ConsolePrinterUtility.error("input");
                    						}
                    					} while(response < minChoice || response > accountMenu.size());
                    					DatabaseSetupUtility.clrscr();
                    					switch(response) {
                    					case 1:
                    						otherCustomers();
                    						break;
                    					case 2:
                    						transaction(TransactionType.DEPOSIT);
                    						break;
                    					case 3:
                            				transaction(TransactionType.WITHDRAW);
                            				break;
                    					case 4:
                            				transaction(TransactionType.TRANSFER);
                            				break;
                    					case 5:
                            				ConsolePrinterUtility.header("transactions");
                            				viewTransactions();
                            				break;
                    					case 6: 
                    						selectedAccount = closeAccount();
                    						break;
                    					case 7:
                    						currentAccount = null;
                    						selectedAccount = false;
                            				break;
                    					}
                					}while(selectedAccount);
            					} else {
            						DatabaseSetupUtility.clrscr();
            						viewTransactions();
            						openAccount();
            					}            					
        					}
        				}while(wentToAccounts);
        			}
        		} while(loggedIn);
        	}
        } while(!quit);
        in.close();
	}
	/**
	 * Lets the current customer update their details.
	 */
	private static void updateCustomer() {
		boolean doContinue = true;
		int response = 0;
		String password = "", fname = "", lname = "", email = "", phone = "", street = "", city = "", state = "", zip = "";
		Pattern passPattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,}$"), emailPattern = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$"), phonePattern = Pattern.compile("^(\\+0?1\\s)?\\(?\\d{3}\\)?[\\s.-]\\d{3}[\\s.-]\\d{4}$"), streetPattern = Pattern.compile("\\d+\\s+([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)"), zipPattern = Pattern.compile("^[0-9]{5}(?:-[0-9]{4})?$"), statePattern = Pattern.compile("^[A-Z]{2}$");
        Matcher matcher;
        Address address;
		do {
			ConsolePrinterUtility.header("customer_update");
			do {
				ConsolePrinterUtility.menu(updateMenu);
				ConsolePrinterUtility.menuChoice(updateMenu.size());
				response = Integer.parseInt(in.nextLine().trim());
				if(response < minChoice || response > updateMenu.size()) {
					ConsolePrinterUtility.error("input");
				}
			} while(response < minChoice || response > updateMenu.size());
			DatabaseSetupUtility.clrscr();
			switch(response) {
			case 1:
				do {
	        		ColorsUtility.colorDefault("Password: (must be at least 8 characters with a digit, a lowercase letter, an Uppercase letter, and a special character)");
	        		password = in.nextLine().trim();
	        		matcher = passPattern.matcher(password);
	        		if(!matcher.matches()) {
	        			ConsolePrinterUtility.error("input");
	        		} 			
	        	} while(!matcher.matches());
				currentCustomer.setPassword(password);
				customerRepo.update(currentCustomer, "password");
				break;
			case 2:
				do {
	        		ColorsUtility.colorDefault("First Name:");
	        		fname = in.nextLine().trim();
	        		if(fname.equals("")) {
	        			ConsolePrinterUtility.error("input");
	        		} 			
	        	} while(fname.equals(""));
				currentCustomer.setFirstName(fname);
				customerRepo.update(currentCustomer, "first_name");
				break;
			case 3:
				do {
	        		ColorsUtility.colorDefault("Last Name:");
	        		lname = in.nextLine().trim();
	        		if(lname.equals("")) {
	        			ConsolePrinterUtility.error("input");
	        		} 			
	        	} while(lname.equals(""));
				currentCustomer.setLastName(lname);
				customerRepo.update(currentCustomer, "last_name");
				break;
			case 4:
				do {
	        		ColorsUtility.colorDefault("Email:");
	        		email = in.nextLine().trim();
	        		matcher = emailPattern.matcher(email);
	        		if(!matcher.matches() || customerRepo.existsByEmail(email)) {
	        			ConsolePrinterUtility.error("input");
	        		} 			
	        	} while(!matcher.matches() || customerRepo.existsByEmail(email));
				currentCustomer.setEmail(email);
				customerRepo.update(currentCustomer, "email");
				break;
			case 5:
				do {
	        		ColorsUtility.colorDefault("Phone Number:");
	        		phone = in.nextLine().trim();
	        		matcher = phonePattern.matcher(phone);
	        		if(!matcher.matches()) {
	        			ConsolePrinterUtility.error("input");
	        		} 			
	        	} while(!matcher.matches());
				currentCustomer.setPhoneNumber(phone);
				customerRepo.update(currentCustomer, "phone_number");
				break;
			case 6:
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
	        	if(addressRepo.existsByStreetAndZipcode(street, zip)) {
	        		address = addressRepo.getByStreetAndZipcode(street, zip);
	        	} else {
	        		address = addressRepo.add(new Address(0, street, city, state, zip));
	        	}
	        	currentCustomer.setAddressid(address.getAddressId());
	        	customerRepo.update(currentCustomer, "address_id");
				break;
			case 7:
				doContinue = false;
				break;
			}
		} while(doContinue);
	}
	/**
	 * Lets the current customer Add or Remove another customer from the current account selected.
	 */
	private static void otherCustomers() {
		int response = 0, i;
		boolean doContinue = true;
		String check = null, email = "";
		Pattern emailPattern = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$");
		Matcher matcher;
		Address address = null;
		currentOtherCustomerAccounts = customerAccountRepo.getByAccount(currentAccount.getAccountNumber());
		for(CustomerAccount ca : currentOtherCustomerAccounts) {
			if(!currentCustomer.getUserId().equals(ca.getCustomerId())) {
				currentOtherCustomers.add(customerRepo.getById(ca.getCustomerId()));
			}
		}
		do {
			currentOtherCustomersMenu.add("Add an Existing Customer to this Account");
			if(currentOtherCustomers.size() > 0) {
				currentOtherCustomersMenu.add("Remove a Customer from this Account");
				currentOtherCustomersMenu.add("Display all Other Customer(s) on this Account");
				for(Customer c : currentOtherCustomers) {
					namesMenu.add(c.getFirstName() + ", " + c.getLastName());
				}
			}
			currentOtherCustomersMenu.add("Back");
			ConsolePrinterUtility.header("account");
			do {
				ConsolePrinterUtility.menu(currentOtherCustomersMenu);
				ConsolePrinterUtility.menuChoice(currentOtherCustomersMenu.size());
				response = Integer.parseInt(in.nextLine().trim());
				if(response < minChoice || response > currentOtherCustomersMenu.size()) {
					ConsolePrinterUtility.error("input");
				}
			} while(response < minChoice || response > currentOtherCustomersMenu.size());
			DatabaseSetupUtility.clrscr();
			if(response == currentOtherCustomersMenu.size()) {
				currentOtherCustomers.removeAll(currentOtherCustomers);
				currentOtherCustomerAccounts = null;
				doContinue = false;
			} else {
				switch(response) {
				case 1:
					do {
						ColorsUtility.colorDefault("Are you sure you want to add an existing customer to this account? (Yes or No)");
						check = in.nextLine().trim().toLowerCase().substring(0, 1);
						if(!check.equals("y") && !check.equals("n")) {
							ConsolePrinterUtility.error("input");
						}
					} while(!check.equals("y") && !check.equals("n"));
					if(check.equals("y")) {
						do {
							ColorsUtility.colorDefault("Enter their Email:");
							email = in.nextLine().trim();
							matcher = emailPattern.matcher(email);
							if(!matcher.matches() || !customerRepo.existsByEmail(email)) {
								ConsolePrinterUtility.error("input");
							} 			
						} while(!matcher.matches() || !customerRepo.existsByEmail(email));
						customerAccountRepo.add(new CustomerAccount(0, customerRepo.getByEmail(email).getUserId(), currentAccount.getAccountNumber()));
					}					
					break;
				case 2:
					do {
						ColorsUtility.colorDefault("Are you sure you want to remove a customer from this account? (Yes or No)");
						check = in.nextLine().trim().toLowerCase().substring(0, 1);
						if(!check.equals("y") && !check.equals("n")) {
							ConsolePrinterUtility.error("input");
						}
					} while(!check.equals("y") && !check.equals("n"));
					if(check.equals("y")) {
						DatabaseSetupUtility.clrscr();
						ConsolePrinterUtility.header("customers");
						do {
							ConsolePrinterUtility.menu(namesMenu);
							ConsolePrinterUtility.menuChoice(namesMenu.size());
							response = Integer.parseInt(in.nextLine().trim());
							if(response < minChoice || response > namesMenu.size()) {
								ConsolePrinterUtility.error("input");
							}
						} while(response < minChoice || response > namesMenu.size());
						customerAccountRepo.deleteById(currentOtherCustomerAccounts.get(response - 1).getCustomerAccountId());
					}
					break;
				case 3:
					i = 1;
					ColorsUtility.colorDefault("Other Customer(s) on this Account:");
					for(Customer c : currentOtherCustomers) {
						address = addressRepo.getById(c.getAddressid());
						ColorsUtility.colorOutput("Name: " + c.getFirstName() + ", " + c.getLastName() + "\nContact Information:\nEmail: " + c.getEmail() + " Phone Number: " + c.getPhoneNumber() + "\nAddress:\n" + address.getStreet() + " " + address.getCity() + ", " + address.getState() + " " + address.getZipcode());
						if(currentOtherCustomers.size() != i) {
							System.out.println();
						}
					}
					break;
				}
			}		
			currentOtherCustomersMenu.removeAll(currentOtherCustomersMenu);
			if(currentOtherCustomers.size() > 0) {
				namesMenu.removeAll(namesMenu);
			}
		} while(doContinue);
	}
	/**
	 * Reopens the current bank account the user has selected and makes sure the user wishes to reopens said account.
	 */
	private static void openAccount() {
		String check = null;
		Double amount;
		Transaction sent;
		do {
			ColorsUtility.colorDefault("Are you sure you want to re-open this account? (Yes or No)");
			check = in.nextLine().trim().toLowerCase().substring(0, 1);
			if(!check.equals("y") && !check.equals("n")) {
				ConsolePrinterUtility.error("input");
			}
		} while(!check.equals("y") && !check.equals("n"));
		if(check.equals("y")) {
			do {
				ColorsUtility.colorDefault("Amount to Deposit:");
				amount = Double.parseDouble(in.nextLine().trim());
				if(amount <= 0.0) {
					ConsolePrinterUtility.error("input");
				} 			
			} while(amount <= 0.0);
			sent = transactionRepo.add(new Transaction(0, Timestamp.valueOf(LocalDateTime.now()), amount, TransactionType.DEPOSIT, "Re-opening account " + currentAccount.getAccountNumber(), currentCustomer.getUserId(), currentAccount.getAccountNumber()));
			ColorsUtility.colorOutput("Transaction Sent: ");
			ColorsUtility.colorOutput("Date: " + sent.getTimestamp() + " Type: " + sent.getType() + " Description: " + sent.getTransactionDesc() + " Amount: $" + df.format(sent.getAmount()));
			currentAccount.setBalance(currentAccount.getBalance() + amount);
			accountRepo.update(currentAccount, "balance");
			currentAccount.setDateOpened(Timestamp.valueOf(LocalDateTime.now()));
			accountRepo.update(currentAccount, "date_opened");
			currentAccount.setDateClosed(null);
			accountRepo.update(currentAccount, "date_closed");
		}
	}
	/**
	 * Closes the current bank account the user has selected and makes sure the user wishes to close said account.
	 * @return boolean - whether the current account has been closed
	 */
	private static boolean closeAccount() {
		String check = null;
		Transaction sent;
		do {
			ColorsUtility.colorDefault("Are you sure you want to close this account? (Yes or No)");
			check = in.nextLine().trim().toLowerCase().substring(0, 1);
			if(!check.equals("y") && !check.equals("n")) {
				ConsolePrinterUtility.error("input");
			}
		} while(!check.equals("y") && !check.equals("n"));
		if(check.equals("n")) {
			return true;
		}
		sent = transactionRepo.add(new Transaction(0, Timestamp.valueOf(LocalDateTime.now()), (currentAccount.getBalance() * -1), TransactionType.WITHDRAW, "Closing account " + currentAccount.getAccountNumber(), currentCustomer.getUserId(), currentAccount.getAccountNumber()));
		ColorsUtility.colorOutput("Transaction Sent: ");
		ColorsUtility.colorOutput("Date: " + sent.getTimestamp() + " Type: " + sent.getType() + " Description: " + sent.getTransactionDesc() + " Amount: $" + df.format(sent.getAmount()));
		currentAccount.setBalance(currentAccount.getBalance() + (currentAccount.getBalance() * -1));
		accountRepo.update(currentAccount, "balance");
		currentAccount.setDateClosed(Timestamp.valueOf(LocalDateTime.now()));
		accountRepo.update(currentAccount, "date_closed");
		currentAccount = null;
		return false;
	}
	/**
	 * Prompts the user for an amount for the transaction that they have chosen. Will not allow transfers if they do not have more than one account.
	 * @param type the type of transaction 
	 */
	private static void transaction(TransactionType type) {
		int response = 0;
		Double amount;
		Account beingUsed = null;
		Transaction sent;
		List<String> choices = new ArrayList<String>();
		
		if(type == TransactionType.TRANSFER && accounts.size() <= 1) {
			ConsolePrinterUtility.error("number_of_accounts");
		} else {
			ConsolePrinterUtility.header(type.getValue().toLowerCase());
			
			switch(type) {
			case DEPOSIT:
				do {
					ColorsUtility.colorDefault("Amount to Deposit:");
					amount = Double.parseDouble(in.nextLine().trim());
					if(amount <= 0.0) {
						ConsolePrinterUtility.error("input");
					} 			
				} while(amount <= 0.0);
				sent = transactionRepo.add(new Transaction(0, Timestamp.valueOf(LocalDateTime.now()), amount, type, "Depositing funds of $" + df.format(amount), currentCustomer.getUserId(), currentAccount.getAccountNumber()));
				
				ColorsUtility.colorOutput("Transaction Sent: ");
				ColorsUtility.colorOutput("Date: " + sent.getTimestamp() + " Type: " + sent.getType() + " Description: " + sent.getTransactionDesc() + " Amount: $" + df.format(sent.getAmount()));
				currentAccount.setBalance(currentAccount.getBalance() + amount);
				accountRepo.update(currentAccount, "balance");
				break;
			case WITHDRAW:
				do {
					ColorsUtility.colorDefault("Amount to Withdraw:");
					amount = Double.parseDouble(in.nextLine().trim());
					if(amount <= 0.0) {
						ConsolePrinterUtility.error("input");
					} 			
				} while(amount <= 0.0);
				if(currentAccount.getBalance() < amount) {
					ConsolePrinterUtility.error("transaction");
				} else {
					amount *= -1;
					sent = transactionRepo.add(new Transaction(0, Timestamp.valueOf(LocalDateTime.now()), amount, type, "Withdrawing funds of $" + df.format(amount), currentCustomer.getUserId(), currentAccount.getAccountNumber()));
					
					ColorsUtility.colorOutput("Transaction Sent: ");
					ColorsUtility.colorOutput("Date: " + sent.getTimestamp() + " Type: " + sent.getType() + " Description: " + sent.getTransactionDesc() + " Amount: $" + df.format(sent.getAmount()));
					currentAccount.setBalance(currentAccount.getBalance() + amount);
					accountRepo.update(currentAccount, "balance");
				}
				break;
			case TRANSFER:
				do {
					ColorsUtility.colorDefault("Amount to Transfer:");
					amount = Double.parseDouble(in.nextLine().trim());
					if(amount <= 0.0) {
						ConsolePrinterUtility.error("input");
					} 			
				} while(amount <= 0.0);
				
				if(currentAccount.getBalance() < amount) {
					ConsolePrinterUtility.error("transaction");
				} else {
					for(CustomerAccount ca : currentCustomerAccounts) {
						if(!currentAccount.getAccountNumber().equals(ca.getAccountId())) {
							choices.add(ca.getAccountId());
						}					
					}
					
					ColorsUtility.colorDefault("Choose the Account to Transfer funds to:");				
					ConsolePrinterUtility.header("accounts");
					do {
						ConsolePrinterUtility.menu(choices);
						ConsolePrinterUtility.menuChoice(choices.size());
						response = Integer.parseInt(in.nextLine().trim());
						if(response < minChoice || response > choices.size()) {
							ConsolePrinterUtility.error("input");
						}
					} while(response < minChoice || response > choices.size());
					
					beingUsed = accountRepo.getByNumber(choices.get(response - 1));
					
					amount *= -1;
					sent = transactionRepo.add(new Transaction(0, Timestamp.valueOf(LocalDateTime.now()), amount, type, "Transfer from " + currentAccount.getAccountNumber() + " to " + beingUsed.getAccountNumber(), currentCustomer.getUserId(), currentAccount.getAccountNumber()));
					
					ColorsUtility.colorOutput("Transaction Sent: ");
					ColorsUtility.colorOutput("Date: " + sent.getTimestamp() + " Type: " + sent.getType() + " Description: " + sent.getTransactionDesc() + " Amount: $" + df.format(sent.getAmount()));
					currentAccount.setBalance(currentAccount.getBalance() + amount);
					accountRepo.update(currentAccount, "balance");
					amount *= -1;
					sent = transactionRepo.add(new Transaction(0, Timestamp.valueOf(LocalDateTime.now()), amount, type, "Transfer from " + currentAccount.getAccountNumber() + " to " + beingUsed.getAccountNumber(), currentCustomer.getUserId(), beingUsed.getAccountNumber()));
					
					ColorsUtility.colorOutput("Transaction Sent: ");
					ColorsUtility.colorOutput("Date: " + sent.getTimestamp() + " Type: " + sent.getType() + " Description: " + sent.getTransactionDesc() + " Amount: $" + df.format(sent.getAmount()));
					beingUsed.setBalance(beingUsed.getBalance() + amount);
					accountRepo.update(beingUsed, "balance");
				}				
				break;
			}
		}
	}
	/**
	 * Displays the user info.
	 */
	private static void displayCustomer() {
		Address address = addressRepo.getById(currentCustomer.getAddressid());
		ColorsUtility.colorOutput("Login Information:\nUser Id: " + currentCustomer.getUserId() + " Password: " + currentCustomer.getPassword() + "\nName: " + currentCustomer.getFirstName() + ", " + currentCustomer.getLastName() + "\nContact Information:\nEmail: " + currentCustomer.getEmail() + " Phone Number: " + currentCustomer.getPhoneNumber() + "\nAddress:\n" + address.getStreet() + " " + address.getCity() + ", " + address.getState() + " " + address.getZipcode());
	}
	/**
	 * Shows the users latest transactions.
	 */
	private static void viewTransactions() {
		List<Transaction> list = new ArrayList<Transaction>();
		String status = currentAccount.getDateClosed() == null? "OPEN" : "CLOSED";
		ColorsUtility.colorOutput("Account " + currentAccount.getAccountNumber() + ":");
		ColorsUtility.colorOutput("Type: " + currentAccount.getType().getValue() + " Current Status: " + status);
		list = transactionRepo.getByAccount(currentAccount.getAccountNumber());
		for(Transaction t : list) {
			ColorsUtility.colorOutput("Date: " + t.getTimestamp() + " Type: " + t.getType() + " Description: " + t.getTransactionDesc() + " Amount: $" + df.format(t.getAmount()));
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
					currentCustomer = customerRepo.getById(username);
					return true;
				}				
			} while(!temp.getPassword().equals(password));

		return false;
	}
	/**
	 * Prompts a user to create a new user account and or a new bank account.
	 * @param createNewCustomer whether to prompt the user to add a new customer or not
	 * @return boolean - whether the user is logged in
	 */
	private static boolean createNewAccount(boolean createNewCustomer) {
		String accountType = "", username = "", password = "", fname = "", lname = "", email = "", phone = "", street = "", city = "", state = "", zip = "", accountNum = AccountNumberGeneratorUtility.generateNewAccountNumber();
		Timestamp dateO = Timestamp.valueOf(LocalDateTime.now());
		Double amount = 0.0;
		Pattern passPattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,}$"), emailPattern = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$"), phonePattern = Pattern.compile("^(\\+0?1\\s)?\\(?\\d{3}\\)?[\\s.-]\\d{3}[\\s.-]\\d{4}$"), streetPattern = Pattern.compile("\\d+\\s+([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)"), zipPattern = Pattern.compile("^[0-9]{5}(?:-[0-9]{4})?$"), statePattern = Pattern.compile("^[A-Z]{2}$");
        Matcher matcher;
        Address address;
        Account account = null;
		boolean checked = true;
		String check = null;
        
        if(createNewCustomer) {
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
        	if(addressRepo.existsByStreetAndZipcode(street, zip)) {
        		address = addressRepo.getByStreetAndZipcode(street, zip);
        	} else {
        		address = addressRepo.add(new Address(0, street, city, state, zip));
        	}

        	currentCustomer = customerRepo.add(new Customer(username, password, fname, lname, email, phone, address.getAddressId()));
        } else {
    		do {
    			ColorsUtility.colorDefault("Are you sure you want to add a new bank account? (Yes or No)");
    			check = in.nextLine().trim().toLowerCase().substring(0, 1);
    			if(!check.equals("y") && !check.equals("n")) {
    				ConsolePrinterUtility.error("input");
    			}
    		} while(!check.equals("y") && !check.equals("n"));
    		if(check.equals("n")) {
    			checked = false;
    		}
        }
		if(checked) {
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

			switch(accountType.charAt(0)) {
			case 'c':
				account = new CheckingAccount(accountNum, dateO, null, amount);
				break;
			case 's':
				account = new SavingsAccount(accountNum, dateO, null, amount);
				break;
			}

			account = accountRepo.add(account);

			customerAccountRepo.add(new CustomerAccount(0, currentCustomer.getUserId(), account.getAccountNumber()));

			transactionRepo.add(new Transaction(0, dateO, amount, TransactionType.DEPOSIT, "Initial Deposit", currentCustomer.getUserId(), account.getAccountNumber()));
		}
		
		return true;
	}
}

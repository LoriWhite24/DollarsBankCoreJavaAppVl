package com.cognixia.jumplus.dollarsbank.utility;

/**
 * The utility for storing reusable output formating for this app.
 * @author Lori White
 * @version v1 (09/17/2020)
 */
public class ConsolePrinterUtility {
	/**
	 * Generates the header, colors, and then prints it to the header.
	 * @param choice the header type
	 */
	public static void header(String choice) {
		String header = "";
		
		switch(choice) {
		case "top":
			header = "+---------------------------+\n| DOLLARSBANK Welcomes You! |\n+---------------------------+";
			break;
		case "logged_in":
			header = "+-------------------+\n| Welcome Customer! |\n+-------------------+";
			break;
		case "new_account":
			header = "+-------------------------------+\n| Enter Details for New Account |\n+-------------------------------+";
			break;
		case "login":
			header = "+---------------------+\n| Enter Login Details |\n+---------------------+";
			break;
		case "customer":
			header = "+------------------+\n| Customer Details |\n+------------------+";
			break;
		case "transactions":
			header = "+-----------------------+\n| 5 Recent Transactions |\n+-----------------------+";
			break;
		case "deposit":
			header = "+---------+\n| Deposit |\n+---------+";
			break;
		case "withdraw":
			header = "+----------+\n| Withdraw |\n+----------+";
			break;
		case "transfer":
			header = "+----------+\n| Transfer |\n+----------+";
			break;
		}
		ColorsUtility.colorHeader(header);
	}
	/**
	 * Generates the menuChoice, colors, and then prints it to the menuChoice.
	 * @param choice the menuChoice type
	 */
	public static void menuChoice(String choice) {
		String menuChoice = "";
		
		switch(choice) {
		case "top":
			menuChoice = "Enter Choice (1, 2, or 3):";
			break;
		case "logged_in":
			menuChoice = "Enter Choice (1, 2, 3, 4, 5, or 6):";
			break;
		}
		ColorsUtility.colorChoice(menuChoice);
	}
	/**
	 * Generates the menu, colors, and then prints it to the menu.
	 * @param choice the menu type
	 */
	public static void menu(String choice) {
		String menu = "";
		
		switch(choice) {
		case "top":
			menu = "1. Create New Account\n2. Login\n3. Exit";
			break;
		case "logged_in":
			menu = "1. Deposit Amount\n2. Withdraw Amount\n3. Funds Transfer\n4. View 5 Recent Transactions\n5. Display Customer Information\n6. Sign Out";
			break;
		}
		ColorsUtility.colorMenu(menu);
	}
	/**
	 * Generates the error, colors, and then prints it to the error.
	 * @param choice the error type
	 */
	public static void error(String choice) {
		String error = "";
		
		switch(choice) {
		case "login":
			error = "Invalid Credentials. Please try again!";
			break;
		case "input":
			error = "Invalid Input. Please try again!";
			break;
		case "number_of_accounts":
			error = "You do not have enough bank accounts to make a transfer, please create a new account first.";
			break;
		}
		ColorsUtility.colorError(error);
	}
}

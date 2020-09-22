package com.cognixia.jumplus.dollarsbank.utility;

import java.util.List;

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
			header = "+---------------------------------------------+\n| Enter Details for New User and Bank Account |\n+---------------------------------------------+";
			break;
		case "login":
			header = "+---------------------+\n| Enter Login Details |\n+---------------------+";
			break;
		case "customer":
			header = "+------------------+\n| Customer Details |\n+------------------+";
			break;
		case "transactions":
			header = "+---------------------+\n| Recent Transactions |\n+---------------------+";
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
		case "accounts":
			header = "+---------------+\n| Your Accounts |\n+---------------+";
			break;
		case "account":
			header = "+------------------+\n| Choose an Action |\n+------------------+";
			break;
		}
		ColorsUtility.colorHeader(header);
	}
	/**
	 * Generates the menuChoice, colors, and then prints it to the menuChoice.
	 * @param numChoice the number of choices
	 */
	public static void menuChoice(int numChoice) {
		String menuChoice = "Enter Choice (";
		
		for(int i = 1; i <= numChoice; i++) {
			if(i == numChoice) {
				menuChoice += "or " + i + "):";
			} else {
				menuChoice += i + ", ";
			}
		}
		ColorsUtility.colorChoice(menuChoice);
	}
	/**
	 * Generates the menu, colors, and then prints it to the menu.
	 * @param choices the menu of choices to print
	 */
	public static void menu(List<String> choices) {
		String menu = "";
		for(int i = 1; i <= choices.size(); i++) {
			menu += i + ". " + choices.get(i - 1);
			if(i != choices.size()) {
				menu += "\n";
			}
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
		case "transaction":
			error = "Invalid Transaction.";
			break;
		}
		ColorsUtility.colorError(error);
	}
}

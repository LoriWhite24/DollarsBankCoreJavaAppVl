package com.cognixia.jumplus.dollarsbank;

import java.util.Scanner;

import com.cognixia.jumplus.dollarsbank.utility.ColorsUtility;
import com.cognixia.jumplus.dollarsbank.utility.DatabaseSetupUtility;

/**
 * Dollars Bank Core Java Application main functionality.
 * @author Lori White
 * @version v1 (09/16/2020)
 */
public class Main {
	/**
	 * The second entry point for this App.
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		boolean quit = false;
		String response;
		Scanner in = new Scanner(System.in);
		System.out.println( "Hello World!" );
        DatabaseSetupUtility.setup("src/main/resources/dollars_bank.sql");
        do {
        	ColorsUtility.main(new String[0]);
        	System.out.println( "Do you wish to quit?" );
        	response = in.nextLine().trim().toLowerCase();
        	if(response.charAt(0) == 'y') {
        		quit = true;
        	}
        } while(!quit);
        System.out.println( "Bye." );
	}
}

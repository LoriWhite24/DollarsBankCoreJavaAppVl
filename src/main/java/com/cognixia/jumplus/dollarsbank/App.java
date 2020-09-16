package com.cognixia.jumplus.dollarsbank;

import com.cognixia.jumplus.dollarsbank.config.ConnectionManagerProperties;
import com.cognixia.jumplus.dollarsbank.utility.DatabaseSetupUtility;

/**
 * Dollars Bank Core Java Application version 1
 * @author Lori White
 * @version v1 (09/16/2020)
 */
public class App 
{
	/**
	 * The main entry point for this App.
	 * @param args the command line arguments
	 */
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        DatabaseSetupUtility.setup("src/main/resources/dollars_bank.sql");
        ConnectionManagerProperties.close();
        System.out.println( "Bye." );
        System.exit(0);
    }
}

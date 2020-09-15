package com.cognixia.jumplus.dollarsbank;

import com.cognixia.jumplus.dollarsbank.config.ConnectionManagerProperties;
import com.cognixia.jumplus.dollarsbank.utility.DatabaseSetupUtility;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        DatabaseSetupUtility.setup("src/main/resources/dollars_bank.sql");
        ConnectionManagerProperties.close();
        System.out.println( "Bye." );
        System.exit(0);
    }
}

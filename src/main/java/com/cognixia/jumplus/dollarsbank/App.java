package com.cognixia.jumplus.dollarsbank;

import java.awt.GraphicsEnvironment;
import java.io.Console;
import java.io.IOException;

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
    public static void main( String[] args ) throws IOException
    {
    	Console console = System.console();
        if(console == null && !GraphicsEnvironment.isHeadless()){
            String filename = App.class.getProtectionDomain().getCodeSource().getLocation().toString().substring(6);
            try {
				Runtime.getRuntime().exec(new String[]{"cmd","/c","start","cmd","/k","java -jar \"" + filename + "\""});
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        System.out.println( "Hello World!" );
        DatabaseSetupUtility.setup("src/main/resources/dollars_bank.sql");
        System.out.println( "Bye." );
        Runtime.getRuntime().exec("taskkill /f /im cmd.exe");
    }
}

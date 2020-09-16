package com.cognixia.jumplus.dollarsbank.utility;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;

import org.apache.ibatis.jdbc.ScriptRunner;

import com.cognixia.jumplus.dollarsbank.config.ConnectionManagerProperties;

/**
 * The utility for running sql scripts for this app.
 * @author Lori White
 * @version v1 (09/15/2020)
 */
public class DatabaseSetupUtility {
	/**
	 * Runs the sql script for dollars_bank database setup.
	 * @param scriptName the sql script name and location in reference to this app
	 */
	public static void setup(String scriptName) {
		Connection con = ConnectionManagerProperties.getConnection();
		
		System.out.println("Connection established......");
		
		ScriptRunner sr = new ScriptRunner(con);
		
		try {
			Reader reader = new BufferedReader(new FileReader(scriptName));
			sr.runScript(reader);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		clrscr();
	}
	/**
	 * Clears the console in command line.
	 */
    private static void clrscr(){

        //Clears Screen in java

        try {

            if (System.getProperty("os.name").contains("Windows"))

                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();

            else

                Runtime.getRuntime().exec("clear");

        } catch (IOException | InterruptedException ex) {
        	System.out.printf("something went wrong :(");
        }

    }
	/**
	 * Tests the integration of this class's setup function with the dollars_bank database with a default location.
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
	      setup("src/main/resources/dollars_bank.sql");
	      clrscr();
	   }
}

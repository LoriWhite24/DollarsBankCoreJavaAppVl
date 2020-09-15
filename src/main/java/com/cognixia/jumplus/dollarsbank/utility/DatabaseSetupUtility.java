package com.cognixia.jumplus.dollarsbank.utility;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;

import org.apache.ibatis.jdbc.ScriptRunner;

import com.cognixia.jumplus.dollarsbank.config.ConnectionManagerProperties;

public class DatabaseSetupUtility {
	
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
	
	public static void main(String args[]) throws Exception {
	      setup("src/main/resources/dollars_bank.sql");
	      clrscr();
	   }
}

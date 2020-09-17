package com.cognixia.jumplus.dollarsbank.utility;

import java.util.Random;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.AnsiFormat;
import com.diogonunes.jcolor.Attribute;

/**
 * The utility for coloring the output for this app.
 * @author Lori White
 * @version v1 (09/17/2020)
 */
public class ColorsUtility {
	/**
	 * Prints the header with formating. 
	 * @param header the header to print
	 */
	public static void colorHeader(String header) {
		AnsiFormat fFormat = new AnsiFormat(Attribute.BRIGHT_BLUE_TEXT(), Attribute.BOLD());
		System.out.println(fFormat.format(header));
	}
	/**
	 * Prints the choice prompt with formating. 
	 * @param choice the choice prompt to print
	 */
	public static void colorChoice(String choice) {
		AnsiFormat fWarning = new AnsiFormat(Attribute.GREEN_TEXT());
		System.out.println(fWarning.format(choice));
	}
	/**
	 * Prints the error with formating. 
	 * @param error the error to print
	 */
	public static void colorError(String error) {		
		AnsiFormat fError = new AnsiFormat(Attribute.RED_BACK(), Attribute.WHITE_TEXT());
		System.out.println(fError.format(error));
	}
	/**
	 * Prints the default formating.
	 * @param defaultString the string to print
	 */
	public static void colorDefault(String defaultString) {
		AnsiFormat fFormat = new AnsiFormat(Attribute.TEXT_COLOR(99));
		System.out.println(fFormat.format(defaultString));
	}
	/**
	 * Prints the output with formating.
	 * @param output the output to print
	 */
	public static void colorOutput(String output) {
		AnsiFormat fInfo = new AnsiFormat(Attribute.CYAN_TEXT());
		System.out.println(fInfo.format(output));
	}
	/**
	 * Prints the menu choices with formating.
	 * @param menu the menu choices to print
	 */
	public static void colorMenu(String menu) {
		AnsiFormat fFormat = new AnsiFormat(Attribute.MAGENTA_TEXT());
		System.out.println(fFormat.format(menu));
	}
	/**
	 * Tests the integration of the jcolor library.
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		// Use Case 1: use Ansi.colorize() to format inline
		System.out.println(Ansi.colorize("This text will be yellow on magenta", Attribute.YELLOW_TEXT(), Attribute.MAGENTA_BACK()));
		System.out.println("\n");

		// Use Case 2: compose Attributes to create your desired format
		Attribute[] myFormat = new Attribute[]{Attribute.RED_TEXT(), Attribute.YELLOW_BACK(), Attribute.BOLD()};
		System.out.println(Ansi.colorize("This text will be red on yellow", myFormat));
		System.out.println("\n");

		// Use Case 3: AnsiFormat is syntactic sugar for an array of Attributes
		AnsiFormat fWarning = new AnsiFormat(Attribute.GREEN_TEXT(), Attribute.BLUE_BACK(), Attribute.BOLD());
		System.out.println(Ansi.colorize("AnsiFormat is just a pretty way to declare formats", fWarning));
		System.out.println(fWarning.format("...and use those formats without calling colorize() directly"));
		System.out.println("\n");

		// Use Case 4: you can define your formats and use them throughout your code
		AnsiFormat fInfo = new AnsiFormat(Attribute.CYAN_TEXT());
		AnsiFormat fError = new AnsiFormat(Attribute.RED_BACK());
		System.out.println(fInfo.format("This info message will be cyan"));
		System.out.println("This normal message will not be formatted");
		System.out.println(fError.format("This error message will be yellow on red"));
		System.out.println("\n");

		// Use Case 5: we support bright colors
		AnsiFormat fNormal = new AnsiFormat(Attribute.MAGENTA_BACK(), Attribute.YELLOW_TEXT());
		AnsiFormat fBright = new AnsiFormat(Attribute.BRIGHT_MAGENTA_BACK(), Attribute.BRIGHT_YELLOW_TEXT());
		System.out.println(fNormal.format("You can use normal colors ") + fBright.format(" and bright colors too"));

		// Use Case 6: we support 8-bit colors
		System.out.println("Any 8-bit color (0-255), as long as your terminal supports it:");
		for (int i = 0; i <= 255; i++) {
		    Attribute txtColor = Attribute.TEXT_COLOR(i);
		    System.out.print(Ansi.colorize(String.format("%4d", i), txtColor));
		}
		System.out.println("\n");

		// Use Case 7: we support true colors (RGB)
		System.out.println("Any TrueColor (RGB), as long as your terminal supports it:");
		for (int i = 0; i <= 300; i++) {
		    Attribute bkgColor = Attribute.BACK_COLOR(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255));
		    System.out.print(Ansi.colorize("   ", bkgColor));
		}
		System.out.println("\n");

		// Credits
		System.out.print("This example used JColor 5.0.0   ");
		System.out.print(Ansi.colorize("\tMADE ", Attribute.BOLD(), Attribute.BRIGHT_YELLOW_TEXT(), Attribute.GREEN_BACK()));
		System.out.println(Ansi.colorize("IN PORTUGAL\t", Attribute.BOLD(), Attribute.BRIGHT_YELLOW_TEXT(), Attribute.RED_BACK()));
		System.out.println("I hope you find it useful ;)");
	}
}

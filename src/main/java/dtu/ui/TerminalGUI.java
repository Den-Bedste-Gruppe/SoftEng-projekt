// Author: Kristian Sofus Knudsen

package dtu.ui;

import java.util.Scanner;

public class TerminalGUI {

	private Scanner input;
	private final String inputMarker = ">> ";


	TerminalGUI() {
		input = new Scanner(System.in);
	}

	public void println(String msg) {
		System.out.println(msg);
	}
	public void print(String msg) {
		System.out.print(msg);
	}

	public String inputString() {
		print(inputMarker);
		input = new Scanner(System.in);
		return input.nextLine();
	}
	public char inputChar() {
		print(inputMarker);
		input = new Scanner(System.in);
		String res = input.next();
		return res.toLowerCase().charAt(0);
	}
	public int inputInt() {
		input = new Scanner(System.in);
		while (true) {
			print(inputMarker);
			if (input.hasNextInt()) {
				return input.nextInt();
			}
			input.next();
		}
	}
	public double inputDouble() {
		input = new Scanner(System.in);
		while (true) {
			print(inputMarker);
			if (input.hasNextDouble()) {
				return input.nextDouble();
			}
			input.next();
		}
	}

	//Displays menu, takes integer answer
	public int numericalMenu(String[] options) {
		if (options.length == 0) {
			return -1; //Return -1 to mark empty array
		}
		Integer i = 1;
		for (String option : options) {
			println(i.toString() + ": " + option);
			i++;
		}
		// !! Does no input validation !! only bounds checking
		int answer = inputInt();
		while (answer < 1 || answer > options.length) {
			answer = inputInt();
		}
		return answer;
	}

	public void printErrorAndContinue(String error) {
		clearScreen();
		println(error);
		println("Press ENTER to return");
		inputString();
	}
	public void printErrorAndContinue(Exception error) {
		clearScreen();
		println(error.getMessage());
		println("Press ENTER to return");
		inputString();
	}

	public void clearScreen() {
		// Feels like black magic, but it's pushing the ANSI escape codes for
		// returning the cursor to the top-left, followed by clearing the screen. 
		// More info: https://en.wikipedia.org/wiki/ANSI_escape_code
		System.out.println("-- if you see this the terminal should have been cleared --");
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
	
	// Mads Harder
	public void pressEnterToReturn() {
		System.out.println("Press enter to return");
		inputString();
	}
	
	//Philip Hviid
	public int[] inputTimeFrame() {
		System.out.println("Enter start year:");
		int startYear = inputInt();
		System.out.println("Enter start week:");
		int startWeek = inputInt();
		System.out.println("Enter end year");
		int endYear = inputInt();
		System.out.println("Enter end week");
		int endWeek = inputInt();
		int[] timeframes = {startYear,startWeek,endYear,endWeek};
		return timeframes;
	}
}

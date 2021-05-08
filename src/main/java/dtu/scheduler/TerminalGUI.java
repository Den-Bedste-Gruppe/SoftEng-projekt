// Author: Kristian Sofus Knudsen

package dtu.scheduler;

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
		print(inputMarker);
		input = new Scanner(System.in);
		return input.nextInt();
	}
	public double inputDouble() {
		print(inputMarker);
		input = new Scanner(System.in);
		return input.nextDouble();
	}

	//Displays menu, takes integer answer
	public int numericalMenu(String[] options) {
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

	public void clearScreen() {
		// Feels like black magic, but it's pushing the ANSI escape codes for
		// returning the cursor to the top-left, followed by clearing the screen. 
		// More info: https://en.wikipedia.org/wiki/ANSI_escape_code
		System.out.println("-- if you see this the terminal should have been cleared --");
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
}

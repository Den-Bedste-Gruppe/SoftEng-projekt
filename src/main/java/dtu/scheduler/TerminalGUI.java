package dtu.scheduler;

import java.util.Scanner;

public class TerminalGUI {

	Scanner input;
	final String inputMarker = ">> ";


	TerminalGUI() {
		input = new Scanner(System.in);
	}

	public String inputString() {
		System.out.print(inputMarker);
		return input.next();
	}

	public int inputInt() {
		System.out.print(inputMarker);
		return input.nextInt();
	}

	//Displays menu, takes integer answer
	public int numericalMenu(String[] options) {
		Integer i = 1;
		for (String option : options) {
			System.out.println(i.toString() + ": " + option);
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
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
}

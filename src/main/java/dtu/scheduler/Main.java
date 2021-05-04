package dtu.scheduler;

public class Main {

	public static void main(String args[]) {
		TerminalGUI gui = new TerminalGUI();

		String[] mainMenu = {"Test 1", "Test 2", "Quit"};

		while (true) {
		int choice = gui.numericalMenu(mainMenu);
		if (choice == 1) {
			System.out.println("You picked 1");
		} else if (choice == 2) {
			System.out.println("You picked 2");
		} else {
			return;
		}}
	}
}
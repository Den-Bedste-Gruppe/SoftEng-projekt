package dtu.scheduler;

public class Main {

	public static TerminalGUI gui = new TerminalGUI();

	public static void main(String args[]) {
		
		loginScene();
		
	}

	public static void loginScene() {
		System.out.println("Enter user id:");
		String answer = gui.inputString();
		System.out.println("Your id: " + answer);
	}
}
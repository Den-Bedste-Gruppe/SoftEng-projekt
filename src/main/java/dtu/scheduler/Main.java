// Author: Kristian Sofus Knudsen

package dtu.scheduler;
import java.util.List;

import dtu.errors.WorkerDoesNotExistException;

public class Main {

	public static TerminalGUI gui = new TerminalGUI();
	//Really ought to make a default constructor for this
	public static SchedulingApp schedulingApp = new SchedulingApp();

	public static void main(String args[]) throws Exception {
		boolean programRunning = true;
		while (programRunning) {

			//Scenes are organized linearly a few cases (login -> mainMenu), but otherwise and mostly in a tree-like structure:
			//If entering the "Register Hours" scene from the "Main Menu" scene, when returning, you return to the "Main Menu" scene
			//If returning from the "Main Menu" scene, you loop back to the login scene, unless the program is quit

			//Program starts with logging in
			loginScene();
			//Once logged in, you arrive at the main menu
			//Other scenes are accessed from there
			mainMenuScene();
		}
	}

	public static void loginScene() throws WorkerDoesNotExistException {
		gui.clearScreen();

		gui.println("Enter user id:");
		while (true) {
		String user_id = gui.inputString();
		try {
			schedulingApp.logIn(user_id);
			//If no exception:
			break;
		} catch (WorkerDoesNotExistException e) {
			gui.clearScreen();
			gui.println("No user exists with given id, please try again:");
		}}
	}


	//Main menu scene
	//Remember to update both the options *and* switch statement
	//If we had more time we would do it in a less manual-labor way (separate class)
	public static void mainMenuScene() {
		gui.clearScreen();

		gui.println("Welcome " + schedulingApp.getCurrentUser());

		String[] mainMenuOptions = { //Examples, more to be added. Remember to update the switch case below
			"Personal scheduling",
			"Project management",
			"Logout"
		};
		
		while (true) {
		int menuChoice = gui.numericalMenu(mainMenuOptions);

		switch (menuChoice) {
			case 1:
				personalSchedulingScene();
				break;
			case 2:
				projectManagementScene();
				break;
			case 3:
				schedulingApp.logOut();
				return; // Return to main, initiates new login
		}}
	}


	public static void personalSchedulingScene() {
		gui.clearScreen();
	}
	public static void projectManagementScene() {
		gui.clearScreen();

		gui.println("Active projects:");

		List<Project> projects = schedulingApp.getProjects();
		for (Project p : projects) {
			gui.println(p.getProjectID());
		}

		gui.println("ENTER to exit");
		gui.inputString();
	}

}
// Author: Kristian Sofus Knudsen

package dtu.scheduler;
import java.util.List;

import dtu.errors.ProjectAlreadyExistsException;
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
		gui.clearScreen();
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
		List<Project> projects = schedulingApp.getProjects();

		gui.println("Active projects:");
		for (Project p : projects) {
			gui.println(p.getProjectID());
		}

		String[] projectMenuOptions = {
			"Create project",
			"Assign new leader to project",
			"Return"
		};

		while (true) {
		gui.clearScreen();
		gui.println("Active projects:");
		for (Project p : projects) {
			gui.print(p.getProjectID());
			if (p.getProjectLeader() != null) gui.print(" PL: " + p.getProjectLeader().getWorkerId());
			gui.println("");
		}
		gui.println("");

		String projectID;
		int menuChoice = gui.numericalMenu(projectMenuOptions);
		switch (menuChoice) {

			//Create project
			////////////////
			case 1:
				gui.clearScreen();
				gui.println("Enter new project ID:");
				projectID = gui.inputString();

				Project new_project;

				gui.println("Assign yourself as project leader? Y/N");
				if (gui.inputChar() == 'y') {
					new_project = new Project(projectID, schedulingApp.getCurrentUser());
				} else {
					new_project = new Project(projectID);
				}
				try {
					schedulingApp.addProject(new_project);
				} catch (ProjectAlreadyExistsException e) {
					gui.clearScreen();
					gui.println(e.getMessage());
					gui.println("Press ENTER to return");
					gui.inputString();
				}
				break;
			
			//Assign new leader to project
			//////////////////////////////
			case 2: 
				gui.clearScreen();
				gui.println("Enter project ID:");
				projectID = gui.inputString();
				gui.println("Enter worker ID:");
				String workerID = gui.inputString();

				try {
					schedulingApp.assignProjectLeader(projectID, workerID);
				} catch (Exception e) {
					gui.clearScreen();
					gui.println(e.getMessage());
					gui.println("Press ENTER to return");
					gui.inputString();
				}
				break;
			case 3:
				return; // Return to main menu
		}}
	}

}
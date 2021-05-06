// Author: Kristian Sofus Knudsen

package dtu.scheduler;
import java.util.List;

import dtu.errors.ProjectAlreadyExistsException;
import dtu.errors.WorkerDoesNotExistException;

public class Main {

	private static TerminalGUI gui = new TerminalGUI();
	//Really ought to make a default constructor for this
	private static SchedulingApp schedulingApp = new SchedulingApp();

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

	private static void loginScene() throws WorkerDoesNotExistException {
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
	private static void mainMenuScene() {
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



	
	private static void personalSchedulingScene() {
		gui.clearScreen();
		
		String[] personalMenuOptions = {
			"Register hours",
			"Change registered hours",
			//TODO find better name for nonproject 
			"Register nonproject activity",
			"Return"
		};

		while (true) {
			gui.clearScreen();

			int menuChoice = gui.numericalMenu(personalMenuOptions);
			switch (menuChoice) {

				//Register hours
				////////////////
				case 1:
					
					break;
				
				//Change registered hours
				/////////////////////////
				case 2:

					break;
				case 3:
					//nonProjectSchedulingScene() when done
					break;
				case 4:
					return; //Return to main menu
			}
		}
	}
	
	//TODO finish after making tests to ensure nonproject activities cannot be created without name
	private static void nonProjectSchedulingScene() {
		gui.clearScreen();
		
		//it says Register in the client, because that is the wording used in the requirement specifications
		//in our code it is actually scheduling of non project activities
		String[] nonProjectMenuOptions = {
				"Register sick leave",
				"Register vacation",
				//TODO find better name for nonproject 
				"Register course ",
				"Register other nonproject activity",
				"Return"
			};
		
		int menuChoice = gui.numericalMenu(nonProjectMenuOptions);
		switch (menuChoice) {
			case 1:
				schedulingApp.scheduleSickLeave();
				break;
			case 2:
				schedulingApp.scheduleVacation();
				break;
			case 3:
				schedulingApp.scheduleCourse();
				break;
			case 4:
				schedulingApp.scheduleNonProjectActivity(null);
				
				
	
		}
		
		
	}




	private static void projectManagementScene() {
		List<Project> projects = schedulingApp.getProjects();

		String[] projectMenuOptions = {
			"Create project",
			"Assign new leader to project",
			"Return"
		};

		while (true) {
		gui.clearScreen();
		
		printProjects(projects);

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
				printProjects(projects);
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

	private static void printProjects(List<Project> projects) {
		gui.println("Active projects:");
		for (Project p : projects) {
			gui.print(p.getProjectID());
			if (p.getProjectLeader() != null) gui.print(" PL: " + p.getProjectLeader().getWorkerId());
			gui.println("");
		}
		gui.println("");
	}
}
// Author: Kristian Sofus Knudsen

package dtu.ui;

import java.util.List;
import dtu.errors.ProjectAlreadyExistsException;
import dtu.errors.WorkerDoesNotExistException;
import dtu.scheduler.Activity;
import dtu.scheduler.DateHelper;
import dtu.scheduler.Project;
import dtu.scheduler.ProjectActivity;
import dtu.scheduler.SchedulingApp;
import dtu.scheduler.Worker;

public class Main {

	private static TerminalGUI gui = new TerminalGUI();
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
	private static void mainMenuScene() throws WorkerDoesNotExistException {
		gui.clearScreen();

		gui.println("Welcome " + schedulingApp.getCurrentUser());
		if(DateHelper.isItWednessDay()) {
			System.out.println("It is wednessday my dude");
		}

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




	private static void personalSchedulingScene() throws WorkerDoesNotExistException {
		gui.clearScreen();

		String[] personalMenuOptions = {
				"Register hours",
				"Change registered hours",
				"Register nonproject activity",
				"Request assistance for activity",
				"Check assistance requests: " + schedulingApp.getCurrentUser().getRequests().size(),
				"Return"
		};

		while (true) {
			gui.clearScreen();

			int menuChoice = gui.numericalMenu(personalMenuOptions);
			switch (menuChoice) {

			case 1:
				registerHoursScene();
				break;

			case 2:
				
				break;
			case 3:
				nonProjectSchedulingScene();
				break;
			case 4:
				requestAssistanceScene();
				break;
			case 5:
				checkAssistanceRequestsScene();
				break;
			case 6:
				return; //Return to main menu
			}
		}
	}
	
	private static void requestAssistanceScene() {
		List<ProjectActivity> activities = schedulingApp.getCurrentUsersActivities();
		gui.clearScreen();
		gui.println("Choose one of your assigned activities:");
		int choice = gui.numericalMenu(activitiesNames(activities)) - 1;
		ProjectActivity activity;
		try {
			activity = activities.get(choice);
			gui.println("Input workerId to request assistance");
			String targetWorkerId = gui.inputString();
			schedulingApp.requestAssistance(activity, targetWorkerId);;
		} catch (Exception e) {
			gui.printErrorAndContinue(e);
		}
	}
	
	private static void checkAssistanceRequestsScene() throws WorkerDoesNotExistException {
		List<AssistRequest> requests = schedulingApp.getWorkerRequests(schedulingApp.getCurrentUserID());
		gui.println("Choose requests to to be assigned to activity:");
		int choice = gui.numericalMenu(displayRequests(requests)) - 1;
		AssistRequest request;
		try {
			request = requests.get(choice);
			schedulingApp.acceptRequest(request);
		} catch (Exception e) {
			gui.printErrorAndContinue(e);
		}
		
	}
	

	private static String[] displayRequests(List<AssistRequest> requests) {
		String[] requestDisplay = new String[requests.size()];
		for (int i=0; i < requests.size(); i++) {
			requestDisplay[i] = "Assist with activity " + requests.get(i).getActivity().getName();
			requestDisplay[i] += ", requested by " + requests.get(i).getSenderId();
		}
		return requestDisplay;
		
	}

	private static void registerHoursScene() {
		List<ProjectActivity> activities = schedulingApp.getCurrentUsersActivities();
		
		gui.clearScreen();
		gui.println("Choose one of your assigned activities:");
		int choice = gui.numericalMenu(activitiesNames(activities)) - 1;
		ProjectActivity activity;
		try {
			activity = activities.get(choice);
			gui.println("Enter number of hours (0-24):");
			double hours = gui.inputDouble();
			schedulingApp.registerHours(hours, activity);
		} catch (Exception e) {
			gui.printErrorAndContinue(e);
		}
	}

	//Philip Hviid
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
		while (true) {
			gui.clearScreen();
			String name = null;
			int menuChoice = gui.numericalMenu(nonProjectMenuOptions);
			switch (menuChoice) {
			case 1:
				name="Sick Leave";
				break;
			case 2:
				name="Vacation";
				break;
			case 3:
				name="Course";
				break;
			case 4:
				name = customNonProjectActivityScene();
				break;
			case 5:
				return;
			}
			if(name!=null) {
				inputTimeFrameScene(name);
			}
		}
	}
	
	//Philip Hviid
	private static void inputTimeFrameScene(String name) {
		while(true) {
			gui.clearScreen();
			System.out.println("Enter start year:");
			int startYear = gui.inputInt();
			System.out.println("Enter start week:");
			int startWeek = gui.inputInt();
			System.out.println("Enter end year");
			int endYear = gui.inputInt();
			System.out.println("Enter end week");
			int endWeek = gui.inputInt();
			try {
				schedulingApp.scheduleNonProjectActivity(name, startYear, startWeek, endYear, endWeek);
				break;
			} catch (Exception e) {
				System.out.println(e.getMessage() + ", try again:");
			}
		}
	}

	//Philip Hviid
	private static String customNonProjectActivityScene() {
		gui.clearScreen();
		while(true) {
			System.out.println("Enter name/description of nonproject activity");
			String name = gui.inputString();
			if(!name.isBlank()) {
				return name;
			}


		}
	}


	private static void projectManagementScene() {
		List<Project> projects = schedulingApp.getProjects();
		String[] projectMenuOptions = {
				"Create project",
				"Assign new leader to project",
				"Manage project activities",
				"Check registered hours of a project",
				"Return"
		};

		while (true) {
			gui.clearScreen();
			printProjects(projects);

			int menuChoice = gui.numericalMenu(projectMenuOptions);
			switch (menuChoice) {

			case 1:
				createProjectScene();
				break;

			case 2: 
				assignLeaderScene();
				break;

			case 3:
				activityManagementScene();
				break;

			case 4:
				checkRegisteredHoursScene();
				break;

			case 5:
				return; // Return to main menu
			}}
	}



	private static void checkRegisteredHoursScene() {
		//Philip Hviid

		gui.clearScreen();	
		printProjects(schedulingApp.getProjects());
		Project currProject;
		
		// TODO why is this new project created. It could just be assigned null
		try {
			currProject = new Project("INITIALISATION");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		gui.println("Enter project ID:");
		String projectID = gui.inputString();
		
		if(schedulingApp.searchProject(projectID) != null) {
			currProject = schedulingApp.searchProject(projectID);
		} else {
			gui.printErrorAndContinue("No project found with given ID");
			return;
		}

		double hours = currProject.getProjectHours();
		int numOfActivities = currProject.getNumOfProjectActivities();

		String projectHoursInfo = "The Project with ID \"" + currProject.getProjectID() + "\" has " + hours + " hours spent over " + numOfActivities + " activities.";

		gui.println(projectHoursInfo);
		gui.pressEnterToReturn();
	}

	private static void createProjectScene() {
		gui.clearScreen();
		
		gui.println("Enter new project name:");
		String projectName = gui.inputString();

		Project new_project = null;

		gui.println("Assign yourself as project leader? Y/N");
		try {
			if (gui.inputChar() == 'y') {
				new_project = new Project(projectName, schedulingApp.getCurrentUser());
			} else {
				new_project = new Project(projectName);
			}
		} catch (Exception e) {
			gui.printErrorAndContinue(e);
			return;
		}
		try {
			schedulingApp.addProject(new_project);
		} catch (ProjectAlreadyExistsException e) {
			gui.printErrorAndContinue(e);
		}
	}

	private static void assignLeaderScene() {
		gui.clearScreen();
		printProjects(schedulingApp.getProjects());
		gui.println("Enter project ID:");
		String projectID = gui.inputString();
		gui.println("Enter worker ID:");
		String workerID = gui.inputString();

		try {
			schedulingApp.assignProjectLeader(projectID, workerID);
		} catch (Exception e) {
			gui.printErrorAndContinue(e);
		}
	}

	private static void activityManagementScene() {
		gui.clearScreen();
		if (schedulingApp.getProjects().size() == 0) {
			gui.printErrorAndContinue("There exists no projects.");
			return;
		}
		printProjects(schedulingApp.getProjects());
		gui.println("Enter project ID:");
		String projectID = gui.inputString();
		Project project = schedulingApp.searchProject(projectID);
		if (project == null) {
			gui.printErrorAndContinue("Project with ID " + projectID + " does not exist.");
			return;
		}

		String[] activityOptions = {
			"Add activity",
			"Assign worker to activity",
			"Set activity timeframe",
			"View activity details",
			"Return"
		};
		boolean managingActivities = true;
		while (managingActivities) {
			gui.clearScreen();

			//TODO: Burde nok tjekke om man er project leader først, men siden at schedulingApp ikke tjekker skal GUI heller ikke
			gui.println("Project: " + project.getName());
			gui.println("Activities:");
			for (Activity a : project.getActivities()) {
				gui.println("\t" + a.getName());
			}
			gui.println("");

			//Declaring some reused variable names
			int activityChoice;
			int[] timeframe = new int[4];
			ProjectActivity activity;
			
			int choice = gui.numericalMenu(activityOptions);
			switch (choice) {
				//Add activity
				//////////////
				case 1:
					gui.clearScreen();
					gui.println("Enter activity name:");
					String activityName = gui.inputString();
					try {
						schedulingApp.createProjectActivity(activityName, projectID);
					} catch (Exception e) {
						gui.printErrorAndContinue(e);
					}
					break;
				
				//Assign worker to activity
				///////////////////////////
				case 2:
					gui.clearScreen();
					gui.println("Choose an activity:");
					activityChoice = gui.numericalMenu(activitiesNames(project.getActivities())) - 1;
					activity = project.getActivities().get(activityChoice);

					gui.clearScreen();
					gui.println(schedulingApp.displayWorkerOverview(activity));

					gui.println("Enter worker ID:");
					String workerID = gui.inputString();

					try {
						schedulingApp.assignActivity(workerID, activity);	
					} catch (Exception e) {
						gui.printErrorAndContinue(e);
					}
					break;
				
				//Set activity timeframe
				////////////////////////
				case 3:
					gui.clearScreen();
					gui.println("Choose an activity:");
					activityChoice = gui.numericalMenu(activitiesNames(project.getActivities())) - 1;
					activity = project.getActivities().get(activityChoice);

					gui.clearScreen();
					gui.println("Enter four numbers like so: 'start-year start-week end-year end-week'");
					timeframe[0] = gui.inputInt();
					timeframe[1] = gui.inputInt();
					timeframe[2] = gui.inputInt();
					timeframe[3] = gui.inputInt();
					try {
						activity.setTimeFrame(timeframe[0], timeframe[1], timeframe[2], timeframe[3]);
					} catch (Exception e) {
						gui.printErrorAndContinue(e);
					}
					break;
				
				//View activity details
				///////////////////////
				case 4:
					gui.clearScreen();
					gui.println("Choose an activity:");
					activityChoice = gui.numericalMenu(activitiesNames(project.getActivities())) - 1;
					activity = project.getActivities().get(activityChoice);
					List<Worker> assignedWorkers = activity.getAssignedWorkers();
					
					gui.clearScreen();
					gui.println("Activity: " + activity.getName() + "\n");
					if (activity.getTimeframe().isEmpty()) {
						gui.println("No timeframe set yet.");
					} else {
						timeframe = activity.getTimeframe().getTimeFrameAsList();
						gui.println(String.format("Scheduled from %d week %d to %d week %d", timeframe[0], timeframe[1], timeframe[2], timeframe[3]));
					}
					gui.println("Total hours registered: " + activity.getTotalHoursSpent()); //If we ever get around to budgeted time, do "5.5/30.0" for example
					gui.println("Assigned workers:");
					for (Worker w : assignedWorkers) {
						try {
							gui.println(w.getWorkerId() + "\t Hours: " + w.getTimeRegistrationByActivity(activity).getHours());
						} catch (Exception e) {
							gui.println(w.getWorkerId() + "\t Hours: No hours registered");
						}
					}
					gui.println("\nPress ENTER to return");
					gui.inputString();
					break;

				case 5:
					managingActivities = false;
					break;
			}

		}

	}



	private static void printProjects(List<Project> projects) {
		gui.println("Active projects:");
		for (Project p : projects) {
			gui.print(p.getProjectID() + ": \"" + p.getName() + "\"");
			if (p.getProjectLeader() != null) gui.print(" PL: " + p.getProjectLeader().getWorkerId());
			gui.println("");
		}
		gui.println("");
	}

	private static String[] activitiesNames(List<ProjectActivity> activities) {
		String[] names = new String[activities.size()];
		for (int i=0; i < activities.size(); i++) {
			names[i] = activities.get(i).getName();
		}
		return names;
	}
}
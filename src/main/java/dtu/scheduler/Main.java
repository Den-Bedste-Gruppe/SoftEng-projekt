// Author: Kristian Sofus Knudsen

package dtu.scheduler;

public class Main {

	public static TerminalGUI gui = new TerminalGUI();
	//Really ought to make a default constructor for this
	public static SchedulingApp schedulingApp =
	new SchedulingApp(
		new WorkerDAO(new WorkerDatabase()),
		new ActivityAssigner(),
		new AssistRequestHandler()
	);

	public static void main(String args[]) throws Exception {

		gui.clearScreen();

		loginScene();
	}

	public static void loginScene() throws WorkerDoesNotExistException {
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

		gui.clearScreen();
		gui.println("Successfully logged in!");
		gui.inputString();
	}
}
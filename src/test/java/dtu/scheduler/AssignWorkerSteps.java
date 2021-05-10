package dtu.scheduler;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Iterator;
import dtu.errors.WorkerDoesNotExistException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

// Lucas Maac
public class AssignWorkerSteps {
	
	private String fakeID;
	private Worker worker;
	private SchedulingApp schedulingApp;
	private ErrorMessageHolder errMsg;
	private ActivityAssigner activityAssigner;
	private ProjectActivity activity;
	private String activityName = "Test Activity";
	private int activityCount = 0;
	private Project project;

	
	public AssignWorkerSteps(SchedulingApp app, ErrorMessageHolder errMsg, ActivityAssigner activityAssigner) {
		this.schedulingApp = app;
		this.errMsg = errMsg;
		this.activityAssigner = activityAssigner;
	}
	
	@Given("there is a worker with ID {string}")
	public void thereIsAWorkerWithID(String string) {
	    worker = new Worker(string);
	}
	
	@Given("that the worker with ID {string} is logged in")
	public void thatTheWorkerWithIDIsLoggedIn(String string) throws WorkerDoesNotExistException {
	    schedulingApp.logIn(worker.getWorkerId());
	}
	
	@Given("that there is an activity")
	public void thatThereIsAnActivity() throws Exception {
		project = new Project("P1");
		schedulingApp.addProject(project);
		schedulingApp.createProjectActivity("testname", project.getProjectID());
		activity = project.searchActivity("testname");
	}

	@When("the user assigns themselves to the activity")
	public void theUserAssignsThemselvesToTheActivity() throws Exception {
		schedulingApp.assignActivity(schedulingApp.getCurrentUserID(), activity);
	}

	@Then("the user is assigned to the activity")
	public void theUserIsAssignedToTheActivity() {
		assertTrue(schedulingApp.getCurrentUser().getActivities().contains(activity));
	}
	
	
	
//	@Given("the worker has {int} activities this week")
//	public void theWorkerHasActivitiesThisWeek(Integer int1) throws Exception {
//	    for (int i = 0; i < int1; i++) {
//			worker.addActivity(new ProjectActivity("Mock Activity " + (i + 1)));
//		}
	    
//	    Debug:
//	    for (Activity activity : worker.getActivities()) {
//			System.out.println(activity.getName());
//		}
//	    
//	}

	@Then("the user is informed that they are busy")
	public void theUserIsInformedThatTheyAreBusy() {
	    assertTrue(errMsg.getErrorMessage().equals("You already have 20 activities assigned"));
	}
	
	
	@When("the user assigns a user with ID {string} to the activity")
	public void theUserAssignsAUserWithIDToTheActivity(String string) throws Exception {
		fakeID = string;
	    try {
			schedulingApp.assignActivity(fakeID, activity);
		} catch (WorkerDoesNotExistException e) {
			errMsg.setErrorMessage(e.getMessage());
		} 
	}
	
	@Then("the user is informed that no worker with the initials {string} exists")
    public void theUserIsInformedThatNoWorkerWithTheInitialsExists(String string) {
        assertTrue(errMsg.getErrorMessage().equals("No user with exists with initials " + fakeID));
    }
	
	
	@Given("the worker has a request for assistance")
	public void aWorkerHasARequestForAssistance() throws Exception {
		project = new Project("P1");
		schedulingApp.addProject(project);
		schedulingApp.createProjectActivity("testname", project.getProjectID());
		activity = project.searchActivity("testname");
	    schedulingApp.requestAssistance(activity, "ZXCV");
	    schedulingApp.logOut();
	    schedulingApp.logIn("ZXCV");
	    assertTrue(schedulingApp.getCurrentUser().getRequests().size()==1);
	}

	@When("the worker accepts the requests")
	public void theWorkerAcceptsTheRequests() throws Exception {
	    schedulingApp.acceptRequest(schedulingApp.getWorkerRequests(schedulingApp.getCurrentUserID()).get(0));
	}
	
	@When("the user requests assistance to himself")
	public void theUserRequestsAssistanceToHimself() {
		try {
		    schedulingApp.requestAssistance(new ProjectActivity("test",new Project("test")), schedulingApp.getCurrentUserID());
		} catch (Exception e) {
			errMsg.setErrorMessage(e.getMessage());
		}

	}

	

	
}

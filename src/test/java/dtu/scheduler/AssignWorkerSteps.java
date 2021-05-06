package dtu.scheduler;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Iterator;

import dtu.errors.TooManyActivitiesException;
import dtu.errors.WorkerDoesNotExistException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AssignWorkerSteps {
	
	private String fakeID;
	private Worker worker;
	private SchedulingApp schedulingApp;
	private ErrorMessageHolder errMsg;
	private ActivityAssigner activityAssigner;
	private Activity activity;
	private String activityName = "Test Activity";

	
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
	public void thatThereIsAnActivity() {
	    activity = new Activity(activityName);
	}

	@When("the user assigns themselves to the activity")
	public void theUserAssignsThemselvesToTheActivity() throws WorkerDoesNotExistException {
			worker.addActivity(activity);
		
	}

	@Then("the user is assigned to the activity")
	public void theUserIsAssignedToTheActivity() {
		assertTrue(worker.getActivities().contains(activity));
	}
	
	
	
	@Given("the worker has {int} activities this week")
	public void theWorkerHasActivitiesThisWeek(Integer int1) throws TooManyActivitiesException {
	    for (int i = 0; i < int1; i++) {
			worker.addActivity(new Activity("Mock Activity " + (i + 1)));
		}
	    
//	    Debug:
//	    for (Activity activity : worker.getActivities()) {
//			System.out.println(activity.getName());
//		}
	    
	}

	@Then("the user is informed that they are busy")
	public void theUserIsInformedThatTheyAreBusy() {
	    assertTrue(errMsg.getErrorMessage().equals("You already have 20 activities assigned"));
	}
	
	
	@When("the user assigns a user with ID {string} to the activity")
	public void theUserAssignsAUserWithIDToTheActivity(String string) throws TooManyActivitiesException {
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
	
	
	
}

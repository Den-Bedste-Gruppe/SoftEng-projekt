package dtu.scheduler;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import dtu.errors.TooManyActivitiesException;
import dtu.errors.ProjectAlreadyExistsException;
import dtu.errors.WorkerDoesNotExistException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;



//Philip Hviid
public class ActivitySteps {
	private String targetWorkerId;
	private SchedulingApp schedulingApp;
	private ErrorMessageHolder msg;
	private ActivityAssigner activityAssigner;
	private Activity activity;
	private int amounthOfNonProjectRegistrations;
	private NonProjectActivity nonProjectActivity;
	private Project project;

	
	public ActivitySteps(SchedulingApp app, ErrorMessageHolder msg, ActivityAssigner activityAssigner) {
		this.schedulingApp = app;
		this.msg = msg;
		this.activityAssigner = activityAssigner;
	}
	
	@Given("the worker is on an activity")
	public void theWorkerIsOnAnActivity() throws WorkerDoesNotExistException, TooManyActivitiesException {
		String currUser = schedulingApp.getCurrentUserID();
		activity = new Activity();
		schedulingApp.assignActivity(currUser, activity);
	}

	@Given("there is another worker")
	public void thereIsAnotherWorker() {
	    targetWorkerId = "QWER";
	}

	@When("the user requests assistance")
	public void theUserRequestsAssistance() throws WorkerDoesNotExistException {
	    schedulingApp.requestAssistance(activity, targetWorkerId);
	}

	@Then("the other worker has a request for assistance")
	public void theOtherWorkerHasARequestForAssistance() throws WorkerDoesNotExistException {
	    assertTrue((schedulingApp.getWorkerRequests(targetWorkerId).size()==1));
	}

	@When("the worker requests assistance from invalid signature")
	public void theWorkerRequestsAssistanceFromInvalidSignature() {
	    try {
			schedulingApp.requestAssistance(activity, "ASDFG");
		} catch (WorkerDoesNotExistException e) {
			msg.setErrorMessage(e.getMessage());
		}
	}
	
	@Given("A project exists")
	public void aProjectExists() throws ProjectAlreadyExistsException {
		project = new Project("P1");
	    schedulingApp.addProject(project);
	    assertTrue(project.getActivities().size()==0);
	}

	@Given("Worker is the project leader of current project")
	public void workerIsTheProjectLeaderOfCurrentProject() {
	    schedulingApp.assingProjectLeader("P1",schedulingApp.getCurrentUser());
	}

	@When("Worker creates an activity")
	public void workerCreatesAnActivity() {
	    try {
			schedulingApp.createProjectActivity("act1", "P1");
		} catch (Exception e) {
			msg.setErrorMessage(e.getMessage());
		}
	}

	@Then("An activity is created by the worker")
	public void anActivityIsCreatedByTheWorker() {
	    assertTrue(project.getActivities().size()==1);
	}


	@Given("Activity with same name already exists")
	public void typeOfActivityAlreadyExist() throws Exception {
		schedulingApp.createProjectActivity("act1", "P1");
	}

	@When("Worker creates the activity")
	public void workerCreatesTheActivity() {
		try {
			schedulingApp.createProjectActivity("act1", "P1");
		} catch (Exception e) {
			msg.setErrorMessage(e.getMessage());
		}
	}

	@Then("The activity is not created by the worker")
	public void theActivityIsNotCreatedByTheWorker() {
	    assertFalse(project.getActivities().size()==2);
	}
	
	@Given("that a nonproject activity exists")
	public void thatANonprojectActivityExists() {
	    nonProjectActivity = new NonProjectActivity();
	}
	
	@When("the worker creates a nonproject activity")
	public void theWorkerCreatesANonprojectActivity() {
	    schedulingApp.createNonProjectActivity(nonProjectActivity);
	}

	@Then("the nonproject activity is added to the workers activities")
	public void theNonprojectActivityIsAddedToTheWorkersActivities() {
	    assertTrue(schedulingApp.workerHasNonProjectActivity(nonProjectActivity));
	}
	
	@Given("that worker is on a nonproject activity")
	public void thatWorkerIsOnANonprojectActivity() {
		nonProjectActivity = new NonProjectActivity();
		schedulingApp.createNonProjectActivity(nonProjectActivity);
	    assert(schedulingApp.workerHasNonProjectActivity(nonProjectActivity));
	}

	@When("the worker registers {double} hours on the nonproject activity")
	public void theWorkerRegistersHoursOnTheNonprojectActivity(double hours) {
	    try {
			schedulingApp.registeNonProjectrHours(hours, nonProjectActivity);
		} catch (Exception e) {
			msg.setErrorMessage(e.getMessage());
		}
	}
	
	@Then("the nonproject timeregistration is added")
	public void theNonprojectTimeregistrationIsAdded() {
		assertTrue(schedulingApp.getNonProjectTimeRegistrations().size()==amounthOfNonProjectRegistrations+1);
	}
	
	@Given("that the worker has {int} nonproject time registrations")
	public void thatTheWorkerHasNonprojectTimeRegistrations(Integer int1) {
	    amounthOfNonProjectRegistrations = schedulingApp.getNonProjectTimeRegistrations().size();
	    assertTrue(amounthOfNonProjectRegistrations==int1);
	    
	}

	@Then("no project is added")
	public void noProjectIsAdded() {
	    assertTrue(schedulingApp.getNonProjectTimeRegistrations().size()==amounthOfNonProjectRegistrations);
	}

}

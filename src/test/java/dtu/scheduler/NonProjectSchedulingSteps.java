package dtu.scheduler;

import static org.junit.jupiter.api.Assertions.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

// Philip HViid
public class NonProjectSchedulingSteps {
	private SchedulingApp schedulingApp;
	private ErrorMessageHolder msg;
	private int amounthOfNonProjectRegistrations;
	private NonProjectActivity nonProjectActivity;
	private int amounthOfNonProjectActivities;
	
	public NonProjectSchedulingSteps(SchedulingApp app, ErrorMessageHolder msg) {
		this.schedulingApp = app;
		this.msg = msg;
	}

	@Given("that a nonproject activity exists")
	public void thatANonprojectActivityExists() throws Exception {
	    nonProjectActivity = new NonProjectActivity("Test", 2021, 1, 2021, 2);
	}
	
	@When("the worker creates a nonproject activity")
	public void theWorkerCreatesANonprojectActivity() {
	    schedulingApp.createNonProjectActivity(nonProjectActivity);
	}

	@Then("the nonproject activity is added to the workers activities")
	public void theNonprojectActivityIsAddedToTheWorkersActivities() {
	    assertTrue(schedulingApp.getWorkersNonProjectActivities().size()==amounthOfNonProjectActivities+1);
	}
	
	@Given("that worker is on a nonproject activity")
	public void thatWorkerIsOnANonprojectActivity() throws Exception {
		nonProjectActivity = new NonProjectActivity("Test", 2021, 1, 2021, 1);
		schedulingApp.createNonProjectActivity(nonProjectActivity);
	    assert(schedulingApp.workerHasNonProjectActivity(nonProjectActivity));
	}

	@When("the worker registers nonproject activity")
	public void theWorkerRegistersHoursOnTheNonprojectActivity() {
	    try {
			schedulingApp.registerNonProjectActivity(nonProjectActivity);
		} catch (Exception e) {
			msg.setErrorMessage(e.getMessage());
		}
	}
	
	@Then("the nonproject registration is added")
	public void theNonprojectTimeregistrationIsAdded() {
		assertTrue(schedulingApp.getNonProjectRegistrations().size()==amounthOfNonProjectRegistrations+1);
	}
	
	@Given("that the worker has {int} nonproject registrations")
	public void thatTheWorkerHasNonprojectTimeRegistrations(Integer int1) {
	    amounthOfNonProjectRegistrations = schedulingApp.getNonProjectRegistrations().size();
	    assertTrue(amounthOfNonProjectRegistrations==int1);
	    
	}

	@Then("no nonproject registration is added")
	public void noProjectIsAdded() {
	    assertTrue(schedulingApp.getNonProjectRegistrations().size()==amounthOfNonProjectRegistrations);
	}
	
	@When("worker schedules nonproject activity")
	public void workerSchedulesNonprojectActivity() throws Exception {
		amounthOfNonProjectRegistrations = schedulingApp.getNonProjectRegistrations().size();
	    schedulingApp.scheduleNonProjectActivity("test", 2021, 1, 2021, 1);
	}

	@Given("worker already has {int} {string} activities")
	public void workerHasAlreadyHasActivity(Integer count, String type) throws Exception {
	    for(int i = 0; i < count; i++) {
	    	schedulingApp.scheduleNonProjectActivity(type, 2021, 1, 2021, 1);;
	    }
	}
	
	@When("worker schedules {string} activity")
	public void workerSchedulesActivity(String string) {
		amounthOfNonProjectActivities = schedulingApp.getWorkersNonProjectActivities().size();
		amounthOfNonProjectRegistrations = schedulingApp.getNonProjectRegistrations().size();
		try {
		    schedulingApp.scheduleNonProjectActivity(string, 2021, 1, 2021, 1);
		} catch (Exception e) {
			msg.setErrorMessage(e.getMessage());
		}

	}
	
	@Given("that the worker has {int} nonproject activities")
	public void thatTheWorkerHasNonprojectActivities(Integer int1) {
	    assertTrue(schedulingApp.getWorkersNonProjectActivities().size()==0);
	}
	
	@When("worker schedules nonproject activity without name")
	public void workerSchedulesNonprojectActivityWithoutName() {
		amounthOfNonProjectActivities = schedulingApp.getWorkersNonProjectActivities().size();
		amounthOfNonProjectRegistrations = schedulingApp.getNonProjectRegistrations().size();
	    try {
			schedulingApp.scheduleNonProjectActivity("", 2021, 1, 2021, 1);
		} catch (Exception e) {
			msg.setErrorMessage(e.getMessage());
		}
	}

	@Then("no nonproject activity is added to the workers activities")
	public void noNonprojectActivityIsAddedToTheWorkersActivities() {
		assertTrue(schedulingApp.getWorkersNonProjectActivities().size()==amounthOfNonProjectActivities);
	}
	
}

package dtu.scheduler;

import static org.junit.jupiter.api.Assertions.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class NonProjectSchedulingSteps {
	private SchedulingApp schedulingApp;
	private ErrorMessageHolder msg;
	private ActivityAssigner activityAssigner;
	private int amounthOfNonProjectRegistrations;
	private NonProjectActivity nonProjectActivity;
	private Project project;
	private int amounthOfNonProjectActivities;
	
	public NonProjectSchedulingSteps(SchedulingApp app, ErrorMessageHolder msg, ActivityAssigner activityAssigner) {
		this.schedulingApp = app;
		this.msg = msg;
		this.activityAssigner = activityAssigner;
	}

	@Given("that a nonproject activity exists")
	public void thatANonprojectActivityExists() {
	    nonProjectActivity = new NonProjectActivity("Test");
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
		nonProjectActivity = new NonProjectActivity("Test");
		schedulingApp.createNonProjectActivity(nonProjectActivity);
	    assert(schedulingApp.workerHasNonProjectActivity(nonProjectActivity));
	}

	@When("the worker registers nonproject activity")
	public void theWorkerRegistersHoursOnTheNonprojectActivity() {
	    try {
			schedulingApp.registerNonProject(nonProjectActivity);
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
		nonProjectActivity = new NonProjectActivity("test");
	    schedulingApp.scheduleNonProjectActivity(nonProjectActivity);
	}

	@Given("worker has already has {int} {string} activity")
	public void workerHasAlreadyHasActivity(Integer count, String type) {
	    for(int i = 0; i<count ; i++) {
	    	schedulingApp.scheduleNonProjectActivity(new NonProjectActivity(type));;
	    }
	}
	
	@When("worker schedules {string} activity")
	public void workerSchedulesActivity(String string) {
		amounthOfNonProjectActivities = schedulingApp.getWorkersNonProjectActivities().size();
		amounthOfNonProjectRegistrations = schedulingApp.getNonProjectRegistrations().size();
		nonProjectActivity = new NonProjectActivity(string);
	    schedulingApp.scheduleNonProjectActivity(nonProjectActivity);
	}
	
	private void workerHasNonProjectActivities(int count, String type) {
	    for(int i = 0; i<count ; i++) {
	    	schedulingApp.scheduleNonProjectActivity(new NonProjectActivity(type));
	    }
	}
}

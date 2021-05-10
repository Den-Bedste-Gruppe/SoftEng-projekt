// Author: Kristian Sofus Knudsen
//
// Tests for the register_hours and change_hours use cases/feature files

package dtu.scheduler;

import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TimeRegistrationTest {

	private SchedulingApp schedulingApp;
	private ProjectActivity testActivity;
	private ErrorMessageHolder errorMessageHolder;
	private Project project;

	public TimeRegistrationTest(SchedulingApp schedulingApp, ErrorMessageHolder errorMessageHolder) throws Exception {
		this.schedulingApp = schedulingApp;
		this.errorMessageHolder = errorMessageHolder;
		project = new Project("TEST");
		schedulingApp.addProject(project);
	}

	@Given("that the worker has {double} hours spent that week")
	public void thatTheWorkerHasHoursSpentThatWeek(double hours) throws Exception {
		if (hours != 0) {
			double diff = hours - schedulingApp.getWeeklyRegisteredHours();
			ProjectActivity dummy_activity = new ProjectActivity("Test", project);
			schedulingApp.registerHours(diff, dummy_activity);
		}
		assertTrue(schedulingApp.getWeeklyRegisteredHours() == hours);
	}

	@Given("that there is a project activity")
	public void thatThereIsAProjectActivity() throws Exception {
		testActivity = new ProjectActivity("test activity", project);
	}

	@Given("that the activity has {double} hours spent")
	public void thatTheActivityHasHoursSpent(double hours) throws Exception {
		if(hours!=0) {
			double diff = hours - testActivity.getTotalHoursSpent();
			schedulingApp.registerHours(hours, testActivity);
		}
		assertTrue(testActivity.getTotalHoursSpent() == hours);
	}

	@Given("that the worker has {double} hours registered on the activity")
	public void thatTheWorkerHasHoursRegisteredOnTheActivity(double hours) throws Exception {
		schedulingApp.registerHours(hours, testActivity);
		assertTrue(schedulingApp.getHoursRegisteredOnActivity(testActivity) == hours);
	}

	@When("the worker registers {double} hours on the activity")
	public void theWorkerRegistersHoursOnTheActivity(double hours) {
		try {
			schedulingApp.registerHours(hours, testActivity);
		} catch (Exception e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@When("the worker changes the amount of hours registered on the activity to {double} hours")
	public void theWorkerChangesTheAmountOfHoursRegisteredOnTheActivityToHours(double new_hours) throws Exception {
		try {
			schedulingApp.changeHoursOnActivity(new_hours, testActivity);
		} catch (Exception e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@Then("the activity has a total of {double} hours spent")
	public void theActivityHasATotalOfHoursSpent(double hours) {
		assertTrue(testActivity.getTotalHoursSpent() == hours);
	}

	@Then("the worker has a total of {double} hours spent that week")
	public void theWorkerHasATotalOfHoursSpentThatWeek(double hours) {
		assertTrue(schedulingApp.getWeeklyRegisteredHours() == hours);
	}
}

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
	private Activity test_activity;
	private ErrorMessageHolder errorMessageHolder;

	public TimeRegistrationTest(SchedulingApp schedulingApp, ErrorMessageHolder errorMessageHolder) {
		this.schedulingApp = schedulingApp;
		this.errorMessageHolder = errorMessageHolder;
	}

	@Given("that the worker has {double} hours spent that week")
	public void thatTheWorkerHasHoursSpentThatWeek(double hours) {
		double diff = hours - schedulingApp.getWeeklyRegisteredHours();
		Activity dummy_activity = new Activity();
		dummy_activity.registerHours(diff);
		assertTrue(schedulingApp.getWeeklyRegisteredHours() == hours);
	}

	@Given("that there is a project activity")
	public void thatThereIsAProjectActivity() {
		test_activity = new Activity();
	}

	@Given("that the activity has {double} hours spent")
	public void thatTheActivityHasHoursSpent(double hours) {
		double diff = hours - test_activity.getHoursSpent();
		test_activity.registerHours(diff);
		assertTrue(test_activity.getHoursSpent() == hours);
	}

	@When("the worker registers {double} hours on the activity")
	public void theWorkerRegistersHoursOnTheActivity(double hours) {
		try {
			schedulingApp.registerHours(hours, test_activity);
		} catch (Exception e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@Then("the activity has a total of {double} hours spent")
	public void theActivityHasATotalOfHoursSpent(double hours) {
		assertTrue(test_activity.getHoursSpent() == hours);
	}

	@Then("the worker has a total of {double} hours spent that week")
	public void theWorkerHasATotalOfHoursSpentThatWeek(double hours) {
		assertTrue(schedulingApp.getWeeklyRegisteredHours() == hours);
	}
}

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

	public TimeRegistrationTest(SchedulingApp schedulingApp) {
		this.schedulingApp = schedulingApp;
		this.errorMessageHolder = ErrorMessageHolder.getInstance();
	}

	@Given("that the worker has {double} hours spent that week")
	public void thatTheWorkerHasHoursSpentThatWeek(double hours) throws Exception {
		double diff = hours - schedulingApp.getWeeklyRegisteredHours();
		Activity dummy_activity = new Activity();
		try {
			schedulingApp.registerHours(diff, dummy_activity);
		} catch (Exception e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
		assertTrue(schedulingApp.getWeeklyRegisteredHours() == hours);
	}

	@Given("that there is a project activity")
	public void thatThereIsAProjectActivity() {
		test_activity = new Activity("test activity");
	}

	@Given("that the activity has {double} hours spent")
	public void thatTheActivityHasHoursSpent(double hours) {
		double diff = hours - test_activity.getTotalHoursSpent();
		TimeRegistration dummy_registration = new TimeRegistration(diff, test_activity, schedulingApp.getCurrentUser());
		assertTrue(test_activity.getTotalHoursSpent() == hours);
	}

	@Given("that the worker has {double} hours registered on the activity")
	public void thatTheWorkerHasHoursRegisteredOnTheActivity(double hours) throws Exception {
		schedulingApp.registerHours(hours, test_activity);
		assertTrue(schedulingApp.getHoursRegisteredOnActivity(test_activity) == hours);
	}

	@When("the worker registers {double} hours on the activity")
	public void theWorkerRegistersHoursOnTheActivity(double hours) {
		try {
			schedulingApp.registerHours(hours, test_activity);
		} catch (Exception e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@When("the worker changes the amount of hours registered on the activity to {double} hours")
	public void theWorkerChangesTheAmountOfHoursRegisteredOnTheActivityToHours(double new_hours) throws Exception {
		try {
			schedulingApp.changeHoursOnActivity(new_hours, test_activity);
		} catch (Exception e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@Then("the activity has a total of {double} hours spent")
	public void theActivityHasATotalOfHoursSpent(double hours) {
		assertTrue(test_activity.getTotalHoursSpent() == hours);
	}

	@Then("the worker has a total of {double} hours spent that week")
	public void theWorkerHasATotalOfHoursSpentThatWeek(double hours) {
		assertTrue(schedulingApp.getWeeklyRegisteredHours() == hours);
	}
}

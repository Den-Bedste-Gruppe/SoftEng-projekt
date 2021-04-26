package dtu.scheduler;

import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TimeRegistrationTest {

	private SchedulingApp schedulingApp;
	private Worker test_worker;
	private Activity test_activity;

	public TimeRegistrationTest(SchedulingApp schedulingApp, Worker test_worker) {
		this.schedulingApp = schedulingApp;
		this.test_worker = test_worker;
	}

	@Given("that the worker has {double} hours spent that week")
	public void thatTheWorkerHasHoursSpentThatWeek(double hours) {
		assertTrue(test_worker.getWeeklyRegisteredHours() == hours);
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
		test_worker.registerHours(hours, test_activity);
	}

	@Then("the activity has a total of {double} hours spent")
	public void theActivityHasATotalOfHoursSpent(double hours) {
		assertTrue(test_activity.getHoursSpent() == hours);
	}

	@Then("the worker has a total of {double} hours spent that week")
	public void theWorkerHasATotalOfHoursSpentThatWeek(double hours) {
		assertTrue(test_worker.getWeeklyRegisteredHours() == hours);
	}
}

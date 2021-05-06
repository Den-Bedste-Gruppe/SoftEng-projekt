package dtu.scheduler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import dtu.errors.ProjectAlreadyExistsException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CheckHoursSteps {
	
	private SchedulingApp schedulingApp;
	private Project project;
	private int projectID;
	private Activity activity1;
	private Activity activity2;
	private String result;
	
	public CheckHoursSteps(SchedulingApp schedulingApp) {
		this.schedulingApp = schedulingApp;
	}
	
	
	@Given("a project with ID {int}")
	public void aProjectWithID(Integer int1) throws ProjectAlreadyExistsException {
		projectID = int1;
		project = new Project(Integer.toString(projectID));
	    schedulingApp.addProject(project);
	}


	@Given("there is a project activity with {double} hours spent")
	public void thereIsAProjectActivityWithHoursSpent(double hours1) throws Exception {
		activity1 = new Activity("Clean up Kanban Board");
		double diff1 = hours1 - activity1.getTotalHoursSpent();
	    project.addActivity(activity1);
	    TimeRegistration registration1 = new TimeRegistration(diff1, activity1, "LMAA");
	}
	
	@Given("there is another project activity with {double} hours spent")
	public void thereIsAnotherProjectActivityWithHoursSpent(double hours2) throws Exception {
		activity2 = new Activity("Finish GUI");
		double diff2 = hours2 - activity2.getTotalHoursSpent();
		TimeRegistration registration2 = new TimeRegistration(diff2, activity2, "LMAA");
	    project.addActivity(activity2);
	}

	@When("the user checks the project overview for project with ID {int}")
	public void theUserChecksTheProjectOverviewForProjectWithID(Integer int1) {
	    result = project.getProjectHoursInfo();
	}

	@Then("the user is informed that total project time spent is {double} hours over {int} projects")
	public void theUserIsInformedThatTotalProjectTimeSpentIsHoursOverProjects(double totalHours, Integer int1) {
		String expected = "Project \"" + projectID + "\" has " + totalHours + " hours spent over " + int1 + " activities.";
		assertEquals(result, expected);
	}

	
}
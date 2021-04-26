package dtu.scheduler;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;

public class Create_ActivityTest {

	@Given("A project exist")
	public void a_project_exist () {
		assert(true) ;
		//insert project feature here
	}
	
	@And("Worker is the project leader of current project")
	public void worker_is_PL() {
		assert(true) ;
		//insert project leader assign here
	}
	
	@When("Worker creates an activity")
	public void worker_create_activity() {
	    // Write code here that turns the phrase above into concrete actions
	}
	
	@Then("An activity is created")
	public void the_activity_is_created() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	@And("Type of activity already exist")
	public void activity_already_exist() {
	    // Write code here that turns the phrase above into concrete actions
		assert(true);
	}
	
	@When("Worker creates the activity")
	public void worker_create_existing_activity() {
	    // Write code here that turns the phrase above into concrete actions

	}
	
	@Then("The activity is not created by the worker")
	public void activity_not_created() {
	    // Write code here that turns the phrase above into concrete actions
	}
	
	@And("the error message \"The form of activity already exist\"")
	public void error_activity_message () {
		
	}
	
	
}

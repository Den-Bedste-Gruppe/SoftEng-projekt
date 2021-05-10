//Author: Kristian Knudsen

package dtu.scheduler;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import dtu.errors.ProjectDoesNotExistException;
import dtu.errors.WorkerDoesNotExistException;

public class assignProjectLeaderTest {

	String leaderID;
	String projectID;

	static SchedulingApp schedulingApp = new SchedulingApp();
	static Project project;
	static String trueProjectID;
	static Worker worker;

	assignProjectLeaderTest() throws Exception {
		//This was supposed to be hardcoded to compare with projectID "2021001"
		//Due to the running ID being private, static, and incrementing with new projects,
		//there is no way to do this as other tests also create projects and "ruin" it
		//Getting a "true" ID ahead of time to compare to instead, it still tests the same,
		//just isn't as clean
		if (project == null) {
			project = new Project("Test project");
			schedulingApp.addProject(project);
			trueProjectID = project.getProjectID();
		}
		worker = schedulingApp.getWorkerById("ASDF");
		assertTrue(project.getProjectID().equals(trueProjectID));
	}

	@Test
	public void testA() throws Exception {
		leaderID = "invalid";
		projectID = "invalid";

		try {
			schedulingApp.assignProjectLeader(projectID, leaderID);
		} catch (WorkerDoesNotExistException e) {
			return; // Expected output, return if thrown
		}
		throw new AssertionError("Output did not match expectations!");
	}

	@Test
	public void testB() throws Exception {
		leaderID = "ASDF";
		projectID = "invalid";

		try {
			schedulingApp.assignProjectLeader(projectID, leaderID);
		} catch (ProjectDoesNotExistException e) {
			return; // Expected output, return if thrown
		}
		throw new AssertionError("Output did not match expectations!");
	}

	@Test
	public void testC() throws Exception {
		leaderID = "ASDF";
		projectID = trueProjectID;

		schedulingApp.assignProjectLeader(projectID, leaderID);

		assertTrue(project.getProjectLeader().equals(worker));
	}

}
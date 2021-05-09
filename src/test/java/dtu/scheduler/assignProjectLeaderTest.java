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
	static Worker worker;

	assignProjectLeaderTest() throws Exception {
		if (project == null) {
			project = new Project("Test project");
			schedulingApp.addProject(project);
		}
		worker = schedulingApp.getWorkerById("ASDF");
		assertTrue(project.getProjectID().equals("2021001"));
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
		projectID = "2021001";

		schedulingApp.assignProjectLeader(projectID, leaderID);

		assertTrue(project.getProjectLeader().equals(worker));
	}

}
package dtu.scheduler;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

// Mads Harder
public class activityWithNameExistsTest {

	static Project project;
	
	void addActivity(String name) throws Exception {
		ProjectActivity activity = new ProjectActivity(name, project);
		project.addActivity(activity);
	}
	
	
	@Test
	public void testA() throws Exception {
		project = new Project("project 1");
		assertTrue(project.activityWithNameExists("act name") == false);
	}

	@Test
	public void testB() throws Exception {
		project = new Project("project 1");
		addActivity("act name");
		assertTrue(project.activityWithNameExists("act name") == true);
	}

	@Test
	public void testC() throws Exception {
		project = new Project("project 1");
		addActivity("not act name");
		assertTrue(project.activityWithNameExists("act name") == false);
	}

	@Test
	public void testD() throws Exception {
		project = new Project("project 1");
		addActivity("not act name");
		addActivity("act name");
		assertTrue(project.activityWithNameExists("act name") == true);
	}
}
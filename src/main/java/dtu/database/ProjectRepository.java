package dtu.database;

import java.util.List;

import dtu.scheduler.Project;

public interface ProjectRepository {

	void add(Project project);

	List<Project> getProjects();

	Project search(String id);
}

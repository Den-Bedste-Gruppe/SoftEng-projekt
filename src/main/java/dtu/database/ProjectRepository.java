package dtu.database;

import java.util.List;
import dtu.scheduler.Project;
import dtu.errors.ProjectAlreadyExistsException;

public interface ProjectRepository {

	void add(Project project) throws ProjectAlreadyExistsException;

	List<Project> getProjects();

	Project search(String id);

	Project searchByName(String projectName);
}

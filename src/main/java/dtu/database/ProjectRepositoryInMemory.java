package dtu.database;

import java.util.ArrayList;
import java.util.List;

import dtu.errors.ProjectAlreadyExistsException;
import dtu.scheduler.Project;

public class ProjectRepositoryInMemory implements ProjectRepository {
	List<Project> projects = new ArrayList<>();

	@Override
	public void add(Project project) throws ProjectAlreadyExistsException {
		if (searchByName(project.getName()) == null) {
			projects.add(project);
		} else {
			throw new ProjectAlreadyExistsException("Project " + project.getName() + " already exists");
		}
	}

	@Override
	public List<Project> getProjects() {
		return projects;
	}
	
	
	public Project search(String ID) {
		for (int i = 0; i < projects.size(); i++) {
			Project p = projects.get(i);
			if (p.getProjectID().equals(ID)) {
				return p;
			}
		}
		return null;
	}

	public Project searchByName(String projectName) {
		for (int i = 0; i < projects.size(); i++) {
			Project p = projects.get(i);
			if (p.getName().equals(projectName)) {
				return p;
			}
		}
		return null;
	}

}

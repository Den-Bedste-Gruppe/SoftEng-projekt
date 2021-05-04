package dtu.scheduler;

import java.util.List;

public class ProjectSearch {
	// TODO maybe refactor
	private List<Project> projectArray;

	public ProjectSearch(List<Project> projectArray) {
		this.projectArray = projectArray;
	}
	
	public Project search(String ID) {
		for (int i = 0; i < projectArray.size(); i++) {
			Project p = projectArray.get(i);
			if (p.getProjectID().equals(ID)) {
				return p;
			}
		}
		return null;
	}

}

// Author: Kristian Sofus Knudsen
//
// Simple implementation to pass the tests in which it is involved

package dtu.scheduler;

import java.util.ArrayList;
import java.util.List;

public class ProjectActivity extends Activity {
	//doubles default to 0, no need to set it
	private double hoursSpent;
	private List<TimeRegistration> registrationList = new ArrayList<>();
	private List<Worker> assignedWorkers = new ArrayList<>();
	private int budgetedTime;
	private Project parrentProject;
	
	public ProjectActivity(String name, Project parrentProject) throws Exception {
		super(name);
		this.parrentProject = parrentProject;
	}


	public double getTotalHoursSpent() {
		return hoursSpent;
	}


	public void addRegistration(TimeRegistration new_registration) {
		registrationList.add(new_registration);
		hoursSpent += new_registration.getHours();
	}

	public void updateTotalHoursSpent() {
		double sum = 0;
		for (TimeRegistration r : registrationList) {
			sum += r.getHours();
		}
		hoursSpent = sum;
	}


	public void addWorker(Worker worker) throws Exception {
		for (Worker w : assignedWorkers) {
			if (w.getWorkerId() == worker.getWorkerId()) {
				throw new Exception("Worker already assigned to activity");
			}
		}
		assignedWorkers.add(worker);
	}

	public List<Worker> getAssignedWorkers() {
		return assignedWorkers;
	}


	public void setBudgetedTime(int hours) {
		if(hours<=0) {
			throw new IllegalArgumentException("budgeted time must be positive integer");
		}
		this.budgetedTime = hours;
		
	}
	
	public int getBudgetedTime() {
		return budgetedTime;
	}
	
}
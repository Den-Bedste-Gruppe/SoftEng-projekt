// Author: Kristian Sofus Knudsen

package dtu.scheduler;

import java.util.ArrayList;
import java.util.List;

public class ProjectActivity extends Activity {
	private double hoursSpent;
	private List<TimeRegistration> registrationList = new ArrayList<>();
	private List<Worker> assignedWorkers = new ArrayList<>();
	private int budgetedTime;
	private Project parentProject;
	
	public ProjectActivity(String name, Project parentProject) throws Exception {
		super(name);
		this.parentProject = parentProject;
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

	//Philip Hviid
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
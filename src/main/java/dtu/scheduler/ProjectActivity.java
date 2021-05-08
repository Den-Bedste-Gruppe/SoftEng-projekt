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
	
	
	
	public ProjectActivity(String name, int startYear, int startWeek, int endYear, int endWeek) throws Exception {
		super(name, startYear, startWeek, endYear, endWeek);
	}
	

	public ProjectActivity(String activityName) {
		super(activityName);
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
	
}
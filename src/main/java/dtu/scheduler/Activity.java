package dtu.scheduler;

// Philip Hviid
public abstract class Activity {
	private TimeFrame timeFrame;
	private String name;
	
	//protected as they can only be called from its child classes anyway
	protected Activity(String name) throws Exception {
		if(name.isEmpty()) {
			throw new IllegalArgumentException("activity cannot be created without name");
		}
		this.name = name;
		timeFrame = new TimeFrame();
	}
	
	public void setTimeFrame(int startYear, int startWeek, int endYear, int endWeek) throws Exception {
		if (DateHelper.isEndDateBeforeStartDate(startYear, startWeek, endYear, endWeek) ) {
			throw new Exception("The end date before the start date");
		}
		timeFrame.setTimeFrame(startYear, startWeek, endYear, endWeek);
	}

	public TimeFrame getTimeframe() {
		return timeFrame;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String new_name) {
		name = new_name;
	}

}

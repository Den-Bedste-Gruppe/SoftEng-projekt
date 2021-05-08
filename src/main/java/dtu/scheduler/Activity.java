package dtu.scheduler;

public abstract class Activity {
	private TimeFrame timeFrame = new TimeFrame();
	private String name;
	
	public void setTimeFrame(int startWeek, int endWeek) throws Exception {
		timeFrame.setTimeframe(startWeek, endWeek);
	}
	
	public Activity(String name) {
		this.name = name;
	}

	public Activity(String name, int startWeek, int endWeek) {
		this.name = name;
		
	}
	
	public int[] getTimeframe() {
		return timeFrame.getTimeFrame();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String new_name) {
		name = new_name;
	}

}

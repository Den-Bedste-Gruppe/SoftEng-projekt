package dtu.scheduler;
//Philip Hviid
public abstract class Activity {
	private TimeFrame timeFrame;
	private String name;
	
	protected Activity(String name) {
		timeFrame = new TimeFrame();
		this.name = name;
		if(name.isEmpty()) {
			throw new IllegalArgumentException("activity cannot be created without name");
		}
	}
	
	//protected as they can only be called from its child classes anyway
	protected Activity(String name, int startWeek, int endWeek) throws Exception {
		this.name = name;
		if(name.isEmpty()) {
			throw new IllegalArgumentException("activity cannot be created without name");
		}
		timeFrame = new TimeFrame();
		setTimeFrame(startWeek, endWeek);
	}
	
	public void setTimeFrame(int startWeek, int endWeek) throws Exception {
		timeFrame.setTimeFrame(startWeek, endWeek);
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

package dtu.scheduler;

public class TimeFrame {
	private int startWeek;
	private int endWeek;
	
	public void setTimeframe(int startWeek, int endWeek) throws Exception {
		if(startWeek < 1 || startWeek > 52) throw new Exception("The given date is not eligible to set time frame");
		if(endWeek < 1 || endWeek > 52) throw new Exception("The given date is not eligible to set time frame");
		setStartWeek(startWeek);
		setEndWeek(endWeek);
	}
	
	public int[] getTimeFrame() {
		int[] timeFrame = {startWeek, endWeek};
		return timeFrame;
	}
	
	public void setStartWeek(int startWeek) {
		this.startWeek = startWeek;
	}
	
	public void setEndWeek(int endWeek) {
		this.endWeek = endWeek;
	}
	

}

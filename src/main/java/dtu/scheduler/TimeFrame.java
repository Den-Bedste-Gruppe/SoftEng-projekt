package dtu.scheduler;

// Mads Harder
public class TimeFrame {
	private int startYear;
	private int startWeek;
	private int endYear;
	private int endWeek;
	
	public void setTimeFrame(int startYear, int startWeek, int endYear, int endWeek) throws Exception {
		int startNumOfWeeks = DateHelper.getWeeksInYear(startYear);
		int endNumOfWeeks = DateHelper.getWeeksInYear(endYear);
		// TODO maybe change error msg
		if(startWeek < 1 || startWeek > startNumOfWeeks) throw new Exception("The given date is not eligible to set time frame");
		if(endWeek < 1 || endWeek > endNumOfWeeks) throw new Exception("The given date is not eligible to set time frame");

		setStartYear(startYear);
		setStartWeek(startWeek);
		setEndYear(endYear);
		setEndWeek(endWeek);
	}
	
	public int[] getTimeFrame() {
		int[] timeFrame = {startYear, startWeek, endYear, endWeek};
		return timeFrame;
	}

	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}
	
	public void setStartWeek(int startWeek) {
		this.startWeek = startWeek;
	}

	public void setEndYear(int endYear) {
		this.endYear = endYear;
	}
	
	public void setEndWeek(int endWeek) {
		this.endWeek = endWeek;
	}

	public int getStartYear() {
		return startYear;
	}

	public int getStartWeek() {
		return startWeek;
	}

	public int getEndYear() {
		return endYear;
	}

	public int getEndWeek() {
		return endWeek;
	}
	

}

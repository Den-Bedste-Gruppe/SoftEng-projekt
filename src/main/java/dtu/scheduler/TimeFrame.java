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
	
	public int[] getTimeFrameAsList() {
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
	
	//Should use dates instead, would be a lot nicer, but not enough time
	//Philip Hviid
	public boolean hasOverlap(TimeFrame timeFrame) {
		if(timeFrame.isEmpty()) {
			return false;
		}
		int start1,start2, end1,end2;
		//concatting years and weeks, to easier compare dates
		start1 = Integer.valueOf(String.valueOf(startYear) 
				+ String.valueOf(startWeek));
		end1 = Integer.valueOf(String.valueOf(endYear) 
				+ String.valueOf(endWeek));
		start2 = Integer.valueOf(String.valueOf(timeFrame.getStartYear())
				+ String.valueOf(timeFrame.getStartWeek()));
		end2 = Integer.valueOf(String.valueOf(timeFrame.getEndYear())
				+ String.valueOf(timeFrame.getEndWeek()));
		//checks if either one ends before the other starts
		if(start1>end2 || start2>end1) {
			return false;
		}
		
		return true;
	}
	
	public boolean isEmpty() {
		if(startWeek==0) {
			return true;
		}
		return false;
	}

}

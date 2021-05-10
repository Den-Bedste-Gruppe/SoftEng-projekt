package dtu.scheduler;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


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
		//pre condition
		assertFalse(timeFrame.equals(null) && anyNegative(timeFrame.getTimeFrameAsList())
				&& anyNegative(this.getTimeFrameAsList()));
		boolean hasOverLap = true;
		int start1,start2, end1,end2;
		//concatting years and weeks, to easier compare dates
		start1 = DateHelper.concatDates(startYear, startWeek);
		end1 = DateHelper.concatDates(endYear, endWeek);
		start2 = DateHelper.concatDates(timeFrame.getStartYear(), timeFrame.getStartWeek());
		end2 = DateHelper.concatDates(timeFrame.getEndYear(), timeFrame.getEndWeek());
		
		//checks if either one ends before the other starts
		if(timeFrame.isEmpty() || start1>end2 || start2>end1) {
			hasOverLap = false;
		}
		//post condition
		assertTrue(!hasOverLap==(timeFrame.isEmpty() || start1>end2 || start2>end1));
		return hasOverLap;
	}
	
	private boolean anyNegative(int[] array) {
		for(int i : array) {
			if(i<0) return true;
		}
		return false;
	}
	

	public boolean isEmpty() {
		if(startWeek==0) {
			return true;
		}
		return false;
	}

}

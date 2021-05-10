package dtu.scheduler;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.Locale;

// Mads Harder
public class DateHelper {
	
	public static int thisWeek() {
		LocalDate now = LocalDate.now();
		return now.get(WeekFields.of(DayOfWeek.MONDAY, 7).weekOfYear());
	}

	public static int thisYear() {
		LocalDate now = LocalDate.now();
		return now.getYear();
	}
	
	public static LocalDate today() {
		return LocalDate.now();
	}
	
	public static boolean isItWednessDay() {
		return (LocalDate.now().getDayOfWeek().equals(DayOfWeek.WEDNESDAY));
	}
	
	public static int getWeeksInYear(int year) {
		// I hate time, timezone and dates...
		// Hours spent on this = 2.5

		// Germany is the close enough to denmark
		Locale userLocale = Locale.GERMANY;
	    WeekFields weekNumbering = WeekFields.of(userLocale);

	    LocalDate date = LocalDate.of(year, Month.DECEMBER, 31);
	    int numberOfWeeksInYear = date.get(weekNumbering.weekOfWeekBasedYear());
	    
	    if (numberOfWeeksInYear == 53) {
	    	return 53;
	    } else {
	    	return 52;
	    }
	}

	public static boolean isEndDateBeforeStartDate(int startYear, int startWeek, int endYear, int endWeek) {
		// I hate time, timezone and dates...
		// Hours spent on this = 1.5

	    Calendar calendar;
		long startUnixTime;
		long endUnixTime;

	    calendar = Calendar.getInstance();
	    
	    calendar.set(Calendar.YEAR, startYear);  
	    calendar.set(Calendar.WEEK_OF_YEAR, startWeek);  
	    startUnixTime = calendar.getTimeInMillis();
	    
	    calendar.set(Calendar.YEAR, endYear);  
	    calendar.set(Calendar.WEEK_OF_YEAR, endWeek);  
	    endUnixTime = calendar.getTimeInMillis();
	    
	    // TODO because of Unix time is start 1970 then
	    // it only work if year >= 1970. This can be fixed
	    // but we should not care about date before 1970
	    if ((endUnixTime - startUnixTime) >= 0) {
	    	return false;
	    } else {
	    	return true;
	    }

	}

	public static int getWeekFromDate(LocalDate date) {
		return date.get(WeekFields.of(DayOfWeek.MONDAY, 7).weekOfYear());
	}
	
	//padding with 0 in case it is under 10, to make sure year 2020 week 9 is not lower than 2019 week 10
	//yes, i know it is shitty, we are pretty damn low on time...
	public static int concatDates(int year, int week) {
		return  Integer.valueOf(String.valueOf(year) 
				+ (week>10? "0" : "") + String.valueOf(week));
	}
}

package dtu.scheduler;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.WeekFields;
import java.util.Locale;

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
	
	// Mads Harder
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
}

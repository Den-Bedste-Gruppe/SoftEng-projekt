package dtu.scheduler;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.WeekFields;

public class DateHelper {
	
	public static int thisWeek() {
		LocalDate now = LocalDate.now();
		return now.get(WeekFields.of(DayOfWeek.MONDAY, 7).weekOfYear());
	}
}

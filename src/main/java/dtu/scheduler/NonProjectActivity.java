package dtu.scheduler;

// Philip Hviid
public class NonProjectActivity extends Activity {
	public NonProjectActivity(String name, int startYear, int startWeek, int endYear, int endWeek) throws Exception {
		super(name);
		super.setTimeFrame(startYear, startWeek, endYear, endWeek);
	}
}
package dtu.scheduler;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

class hasOverlapTest {
	private TimeFrame callerTimeFrame;
	private TimeFrame parameterTimeFrame;
	
	hasOverlapTest() {
		callerTimeFrame = new TimeFrame();
		parameterTimeFrame = new TimeFrame();
	}

	@Test
	void testA() throws Exception {
		callerTimeFrame.setTimeFrame(2019, 5, 2019, 5);
		assertFalse(callerTimeFrame.hasOverlap(parameterTimeFrame));
	}
	
	@Test
	void testB1() throws Exception {
		callerTimeFrame.setTimeFrame(2019, 10, 2019, 12);
		parameterTimeFrame.setTimeFrame(2019, 05, 2019, 05);
		assertFalse(callerTimeFrame.hasOverlap(parameterTimeFrame));
	}

	@Test
	void testB2() throws Exception {
		callerTimeFrame.setTimeFrame(2019, 6, 2019, 6);
		parameterTimeFrame.setTimeFrame(2019, 07, 2019, 9);
		assertFalse(callerTimeFrame.hasOverlap(parameterTimeFrame));
	}

	@Test
	void testC() throws Exception {
		callerTimeFrame.setTimeFrame(2018, 5, 2018, 5);
		parameterTimeFrame.setTimeFrame(2018, 5, 2018, 5);
		assertTrue(callerTimeFrame.hasOverlap(parameterTimeFrame));
	}

}

package vitals;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class VitalsCheckerTest {

	@Test
	void testVitalsOkValidValues() throws InterruptedException {
		assertTrue(VitalsChecker.vitalsOk(98.6f, 75f, 95f));
	}

	@Test
	void testVitalsOkCriticalTemperatureHigh() throws InterruptedException {
		assertFalse(VitalsChecker.vitalsOk(102.1f, 75f, 95f));
	}

	@Test
	void testVitalsOkCriticalTemperatureLow() throws InterruptedException {
		assertFalse(VitalsChecker.vitalsOk(94.9f, 75f, 95f));
	}

	@Test
	void testVitalsOkInvalidPulseRateLow() throws InterruptedException {
		assertFalse(VitalsChecker.vitalsOk(98.6f, 59f, 95f));
	}

	@Test
	void testVitalsOkInvalidPulseRateHigh() throws InterruptedException {
		assertFalse(VitalsChecker.vitalsOk(98.6f, 101f, 95f));
	}

	@Test
	void testVitalsOkInvalidSpo2() throws InterruptedException {
		assertFalse(VitalsChecker.vitalsOk(98.6f, 75f, 89f));
	}

	@Test
	void testVitalsOkAllCritical() throws InterruptedException {
		assertFalse(VitalsChecker.vitalsOk(102.1f, 101f, 89f));
	}

	@Test
	void testVitalsOkAllValid() throws InterruptedException {
		assertTrue(VitalsChecker.vitalsOk(98.6f, 70f, 92f));
	}

	@Test
	void testVitalsOkBoundaryTemperatureHigh() throws InterruptedException {
		assertFalse(VitalsChecker.vitalsOk(102f, 75f, 95f));
	}

	@Test
	void testVitalsOkBoundaryTemperatureLow() throws InterruptedException {
		assertFalse(VitalsChecker.vitalsOk(95f, 75f, 95f));
	}

	@Test
	void testVitalsOkBoundaryPulseRateLow() throws InterruptedException {
		assertFalse(VitalsChecker.vitalsOk(98.6f, 60f, 95f));
	}

	@Test
	void testVitalsOkBoundaryPulseRateHigh() throws InterruptedException {
		assertFalse(VitalsChecker.vitalsOk(98.6f, 100f, 95f));
	}

	@Test
	void testVitalsOkBoundarySpo2() throws InterruptedException {
		assertTrue(VitalsChecker.vitalsOk(98.6f, 75f, 90f));
	}
}

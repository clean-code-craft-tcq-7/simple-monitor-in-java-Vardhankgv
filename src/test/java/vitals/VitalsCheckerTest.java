package vitals;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class VitalsCheckerTest {

	@Test
	public void testVitalsOkValidValues() throws InterruptedException {
		assertTrue(VitalsChecker.vitalsOk(98.6f, 75f, 95f));
	}

	@Test
	public void testVitalsOkCriticalTemperatureHigh() throws InterruptedException {
		assertFalse(VitalsChecker.vitalsOk(102.1f, 75f, 95f));
	}

	@Test
	public void testVitalsOkCriticalTemperatureLow() throws InterruptedException {
		assertFalse(VitalsChecker.vitalsOk(94.9f, 75f, 95f));
	}

	@Test
	public void testVitalsOkInvalidPulseRateLow() throws InterruptedException {
		assertFalse(VitalsChecker.vitalsOk(98.6f, 59f, 95f));
	}

	@Test
	public void testVitalsOkInvalidPulseRateHigh() throws InterruptedException {
		assertFalse(VitalsChecker.vitalsOk(98.6f, 101f, 95f));
	}

	@Test
	public void testVitalsOkInvalidSpo2() throws InterruptedException {
		assertFalse(VitalsChecker.vitalsOk(98.6f, 75f, 89f));
	}

	@Test
	public void testVitalsOkAllCritical() throws InterruptedException {
		assertFalse(VitalsChecker.vitalsOk(102.1f, 101f, 89f));
	}

	@Test
	public void testVitalsOkAllValid() throws InterruptedException {
		assertTrue(VitalsChecker.vitalsOk(98.6f, 70f, 92f));
	}

	@Test
	public void testVitalsOkBoundaryTemperatureHigh() throws InterruptedException {
		assertFalse(VitalsChecker.vitalsOk(102f, 75f, 95f));
	}

	@Test
	public void testVitalsOkBoundaryTemperatureLow() throws InterruptedException {
		assertFalse(VitalsChecker.vitalsOk(95f, 75f, 95f));
	}

	@Test
	public void testVitalsOkBoundaryPulseRateLow() throws InterruptedException {
		assertFalse(VitalsChecker.vitalsOk(98.6f, 60f, 95f));
	}

	@Test
	public void testVitalsOkBoundaryPulseRateHigh() throws InterruptedException {
		assertFalse(VitalsChecker.vitalsOk(98.6f, 100f, 95f));
	}

	@Test
	public void testVitalsOkBoundarySpo2() throws InterruptedException {
		assertTrue(VitalsChecker.vitalsOk(98.6f, 75f, 90f));
	}
}

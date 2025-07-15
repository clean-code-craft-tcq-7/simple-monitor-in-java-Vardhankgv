package vitals;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class VitalsCheckerTest {

	@Test
	void testVitalsOk_ValidValues() throws InterruptedException {
		assertTrue(VitalsChecker.vitalsOk(98.6f, 75f, 95f), "Vitals should be OK for valid values.");
	}

	@Test
	void testVitalsOk_CriticalTemperatureHigh() throws InterruptedException {
		assertFalse(VitalsChecker.vitalsOk(102.1f, 75f, 95f), "Vitals should not be OK for critical high temperature.");
	}

	@Test
	void testVitalsOk_CriticalTemperatureLow() throws InterruptedException {
		assertFalse(VitalsChecker.vitalsOk(94.9f, 75f, 95f), "Vitals should not be OK for critical low temperature.");
	}

	@Test
	void testVitalsOk_InvalidPulseRateLow() throws InterruptedException {
		assertFalse(VitalsChecker.vitalsOk(98.6f, 59f, 95f), "Vitals should not be OK for low pulse rate.");
	}

	@Test
	void testVitalsOk_InvalidPulseRateHigh() throws InterruptedException {
		assertFalse(VitalsChecker.vitalsOk(98.6f, 101f, 95f), "Vitals should not be OK for high pulse rate.");
	}

	@Test
	void testVitalsOk_InvalidSpo2() throws InterruptedException {
		assertFalse(VitalsChecker.vitalsOk(98.6f, 75f, 89f), "Vitals should not be OK for low oxygen saturation.");
	}

	@Test
	void testVitalsOk_AllCritical() throws InterruptedException {
		assertFalse(VitalsChecker.vitalsOk(102.1f, 101f, 89f), "Vitals should not be OK for all critical values.");
	}

	@Test
	void testVitalsOk_AllValid() throws InterruptedException {
		assertTrue(VitalsChecker.vitalsOk(98.6f, 70f, 92f), "Vitals should be OK for all valid values.");
	}

	@Test
	void testVitalsOk_BoundaryTemperatureHigh() throws InterruptedException {
		assertFalse(VitalsChecker.vitalsOk(102f, 75f, 95f), "Vitals should not be OK for boundary high temperature.");
	}

	@Test
	void testVitalsOk_BoundaryTemperatureLow() throws InterruptedException {
		assertFalse(VitalsChecker.vitalsOk(95f, 75f, 95f), "Vitals should not be OK for boundary low temperature.");
	}

	@Test
	void testVitalsOk_BoundaryPulseRateLow() throws InterruptedException {
		assertFalse(VitalsChecker.vitalsOk(98.6f, 60f, 95f), "Vitals should be OK for boundary low pulse rate.");
	}

	@Test
	void testVitalsOk_BoundaryPulseRateHigh() throws InterruptedException {
		assertFalse(VitalsChecker.vitalsOk(98.6f, 100f, 95f), "Vitals should be OK for boundary high pulse rate.");
	}

	@Test
	void testVitalsOk_BoundarySpo2() throws InterruptedException {
		assertTrue(VitalsChecker.vitalsOk(98.6f, 75f, 90f), "Vitals should be OK for boundary oxygen saturation.");
	}
}

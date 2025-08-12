package vitals;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class VitalsChecker {
	private static final Logger LOG = LoggerFactory.getLogger(VitalsChecker.class);

	private static final int CRITICAL_TEMPERATURE_LOW_THRESHOLD = 95;
	private static final int CRITICAL_TEMPERATURE_HIGH_THRESHOLD = 102;

	private static final int MIN_PULSE_VALUE = 60;
	private static final int MAX_PULSE_VALUE = 100;

	private static final int MIN_SPO2_VALUE = 90;

	/**
	 * Checks if the provided vital signs are within acceptable ranges.
	 *
	 * @param temperature the body temperature
	 * @param pulseRate   the pulse rate
	 * @param spo2        the oxygen saturation level
	 * @return true if all vital signs are okay, false otherwise
	 * @throws InterruptedException if the thread is interrupted during sleep
	 */
	public static boolean vitalsOk(float temperature, float pulseRate, float spo2) throws InterruptedException {
		LOG.debug("Received Vitals: \n temperature: {} \n Pulse Rate: {} \n Spo2: {}", temperature, pulseRate, spo2);
		return isValidTemperature(temperature) && isValidPulseRate(pulseRate) && isValidSpo2(spo2);
	}

	/**
	 * Validates a temperature value against specified thresholds.
	 *
	 * @param temperature the temperature to validate
	 * @return true if the temperature is valid, false otherwise
	 * @throws InterruptedException if the thread is interrupted during sleep
	 */
	private static boolean isValidTemperature(float temperature) throws InterruptedException {
		return isValidRange(temperature, CRITICAL_TEMPERATURE_LOW_THRESHOLD, CRITICAL_TEMPERATURE_HIGH_THRESHOLD,
				"Temperature is critical!");
	}

	/**
	 * Validates a pulse rate against specified thresholds.
	 *
	 * @param pulseRate the pulse rate to validate
	 * @return true if the pulse rate is valid, false otherwise
	 * @throws InterruptedException if the thread is interrupted during sleep
	 */
	private static boolean isValidPulseRate(float pulseRate) throws InterruptedException {
		return isValidRange(pulseRate, MIN_PULSE_VALUE, MAX_PULSE_VALUE, "Pulse Rate is out of range!");
	}

	/**
	 * Validates an oxygen saturation level.
	 *
	 * @param spo2 the oxygen saturation level to validate
	 * @return true if the oxygen saturation is valid, false otherwise
	 * @throws InterruptedException if the thread is interrupted during sleep
	 */
	private static boolean isValidSpo2(float spo2) throws InterruptedException {
		boolean isValidSpo2 = spo2 >= MIN_SPO2_VALUE;
		if (!isValidSpo2) {
			displayAlertMessage("Oxygen Saturation out of range!");
		}
		return isValidSpo2;
	}

	/**
	 * Validates a value against specified minimum and maximum thresholds.
	 *
	 * @param value        the value to validate
	 * @param min          the minimum acceptable value
	 * @param max          the maximum acceptable value
	 * @param alertMessage the alert message to display if the value is invalid
	 * @return true if the value is valid, false otherwise
	 * @throws InterruptedException if the thread is interrupted during sleep
	 */
	private static boolean isValidRange(float value, float min, float max, String alertMessage) throws InterruptedException {
		boolean isValid = value > min && value < max;
		if (!isValid) {
			LOG.warn("Provided value {} is not in range of {} and {}", value, min, max);
			displayAlertMessage(alertMessage);
		}
		return isValid;
	}

	/**
	 * Displays an alert message and simulates a visual alert.
	 *
	 * @param message the message to display
	 * @throws InterruptedException if the thread is interrupted during sleep
	 */
	private static void displayAlertMessage(String message) throws InterruptedException {
		System.out.println(message);
		for (int i = 0; i < 6; i++) {
			System.out.print("\r* ");
			Thread.sleep(1000);
			System.out.print("\r *");
			Thread.sleep(1000);
		}
	}
}

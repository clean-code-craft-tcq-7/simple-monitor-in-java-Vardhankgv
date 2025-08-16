package vitals;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ValidationUtils {
    private static final Logger LOG = LoggerFactory.getLogger(ValidationUtils.class);

    private ValidationUtils() {}
    /**
     * Checks if a value is within a given range (exclusive).
     * If not, logs a warning and shows an alert message.
     *
     * @param value        the value to check
     * @param min          the exclusive lower bound
     * @param max          the exclusive upper bound
     * @param alertMessage the message to display if out of range
     * @return true if value is within range, false otherwise
     * @throws InterruptedException if alert thread is interrupted
     */
    public static boolean isInRange(float value, float min, float max, String alertMessage) throws InterruptedException {
        boolean isValid = value > min && value < max;
        if (!isValid) {
            LOG.warn("Provided value {} is not in range of {} and {}", value, min, max);
            AlertUtils.displayAlertMessage(alertMessage);
        }
        return isValid;
    }
}

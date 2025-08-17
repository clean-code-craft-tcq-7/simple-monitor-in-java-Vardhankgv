package vitals;

public interface VitalValidator {
    /**
     * Validates a vital sign value.
     * @param value The vital sign value to validate.
     * @return true if valid, false otherwise.
     * @throws InterruptedException if thread sleep is interrupted.
     */
    boolean validate(float value) throws InterruptedException;
}


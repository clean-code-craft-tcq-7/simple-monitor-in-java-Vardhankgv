package vitals;

public class PulseRateValidator implements VitalValidator {

    private static final int MIN_PULSE_VALUE = 60;
    private static final int MAX_PULSE_VALUE = 100;

    @Override
    public boolean validate(float pulseRate) throws InterruptedException {
        return ValidationUtils.isInRange(
            pulseRate,
            MIN_PULSE_VALUE,
            MAX_PULSE_VALUE,
            "pulse.outOfRange"
        );
    }
}

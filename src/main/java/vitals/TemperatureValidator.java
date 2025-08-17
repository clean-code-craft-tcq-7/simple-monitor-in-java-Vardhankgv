package vitals;

public class TemperatureValidator implements VitalValidator {

    private static final int CRITICAL_TEMPERATURE_MIN_VALUE = 95;
    private static final int CRITICAL_TEMPERATURE_MAX_VALUE = 102;

    @Override
    public boolean validate(float temperature) throws InterruptedException {
        return ValidationUtils.isInRange(
            temperature,
            CRITICAL_TEMPERATURE_MIN_VALUE,
            CRITICAL_TEMPERATURE_MAX_VALUE,
            "temperature.critical"
        );
    }
}

package vitals;

public class RespiratoryRateValidator implements VitalValidator {
    private static final float RR_MIN_VALUE = 12;
    private static final float RR_MAX_VALUE = 20;

    @Override
    public boolean validate(float value) throws InterruptedException {
        return ValidationUtils.isInRange(
            value,
            RR_MIN_VALUE, 
            RR_MAX_VALUE, 
            "respiratoryRate.outOfRange"
        );
    }
}


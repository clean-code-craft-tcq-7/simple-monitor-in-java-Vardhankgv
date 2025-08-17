package vitals;

public class BloodPressureValidator implements VitalValidator {
    private static final float BP_MIN_VALUE = 90;
    private static final float BP_MAX_VALUE = 150;

    @Override
    public boolean validate(float value) throws InterruptedException {
        return ValidationUtils.isInRange(
            value,
            BP_MIN_VALUE, 
            BP_MAX_VALUE, 
            "bloodPressure.outOfRange"
        );
    }
}


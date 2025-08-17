package vitals;

public class BloodSugarValidator implements VitalValidator {
    private static final float MIN_SUGAR_VALUE = 70;
    private static final float MAX_SUGAR_VALUE = 110;

    @Override
    public boolean validate(float value) throws InterruptedException {
        return ValidationUtils.isInRange(
            value,
            MIN_SUGAR_VALUE, 
            MAX_SUGAR_VALUE, 
            "bloodSugar.outOfRange"
        );
    }
}

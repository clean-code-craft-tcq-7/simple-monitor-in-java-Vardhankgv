package vitals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class BloodPressureValidatorTest {

    @BeforeEach
    void setup() {
        AlertUtils.setAlertAnimator(Mockito.mock(AlertAnimator.class));
        AlertUtils.setMessageProvider(key -> key);
    }

    @Test
    void shouldReturnTrueWhenBloodPressureWithinRange() throws InterruptedException {
        BloodPressureValidator validator = new BloodPressureValidator();
        assertTrue(validator.validate(120f));
        assertTrue(validator.validate(140f));
    }

    @Test
    void shouldReturnFalseWhenBloodPressureEqualsLowerLimit() throws InterruptedException {
        BloodPressureValidator validator = new BloodPressureValidator();
        assertFalse(validator.validate(90f));
    }

    @Test
    void shouldReturnFalseWhenBloodPressureEqualsUpperLimit() throws InterruptedException {
        BloodPressureValidator validator = new BloodPressureValidator();
        assertFalse(validator.validate(150f));
    }

    @Test
    void shouldReturnFalseAndTriggerAlertWhenBloodPressureBelowLowerLimit() throws InterruptedException {
        BloodPressureValidator validator = new BloodPressureValidator();
        assertFalse(validator.validate(85f));
    }

    @Test
    void shouldReturnFalseAndTriggerAlertWhenBloodPressureAboveUpperLimit() throws InterruptedException {
        BloodPressureValidator validator = new BloodPressureValidator();
        assertFalse(validator.validate(160f));
    }
}

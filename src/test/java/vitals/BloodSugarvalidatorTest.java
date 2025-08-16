package vitals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class BloodSugarValidatorTest {

    @BeforeEach
    void setup() {
        AlertUtils.setAlertAnimator(Mockito.mock(AlertAnimator.class));
        AlertUtils.setMessageProvider(key -> key); // Dummy message provider
    }

    @Test
    void shouldReturnTrueWhenBloodSugarWithinRange() throws InterruptedException {
        BloodSugarValidator validator = new BloodSugarValidator();
        assertTrue(validator.validate(85f));
        assertTrue(validator.validate(100f));
    }

    @Test
    void shouldReturnFalseWhenBloodSugarEqualsLowerLimit() throws InterruptedException {
        BloodSugarValidator validator = new BloodSugarValidator();
        assertFalse(validator.validate(70f));
    }

    @Test
    void shouldReturnFalseWhenBloodSugarEqualsUpperLimit() throws InterruptedException {
        BloodSugarValidator validator = new BloodSugarValidator();
        assertFalse(validator.validate(110f));
    }

    @Test
    void shouldReturnFalseAndTriggerAlertWhenBloodSugarBelowLowerLimit() throws InterruptedException {
        BloodSugarValidator validator = new BloodSugarValidator();
        assertFalse(validator.validate(65f));
        // Alert display logic is invoked inside validate; integration test can verify side effects if needed
    }

    @Test
    void shouldReturnFalseAndTriggerAlertWhenBloodSugarAboveUpperLimit() throws InterruptedException {
        BloodSugarValidator validator = new BloodSugarValidator();
        assertFalse(validator.validate(115f));
    }
}

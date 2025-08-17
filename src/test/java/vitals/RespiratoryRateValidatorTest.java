package vitals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class RespiratoryRateValidatorTest {

    @BeforeEach
    void setup() {
        AlertUtils.setAlertAnimator(Mockito.mock(AlertAnimator.class));
        AlertUtils.setMessageProvider(key -> key);
    }

    @Test
    void shouldReturnTrueWhenRespiratoryRateWithinRange() throws InterruptedException {
        RespiratoryRateValidator validator = new RespiratoryRateValidator();
        assertTrue(validator.validate(15f));
        assertTrue(validator.validate(19f));
    }

    @Test
    void shouldReturnFalseWhenRespiratoryRateEqualsLowerLimit() throws InterruptedException {
        RespiratoryRateValidator validator = new RespiratoryRateValidator();
        assertFalse(validator.validate(12f));
    }

    @Test
    void shouldReturnFalseWhenRespiratoryRateEqualsUpperLimit() throws InterruptedException {
        RespiratoryRateValidator validator = new RespiratoryRateValidator();
        assertFalse(validator.validate(20f));
    }

    @Test
    void shouldReturnFalseAndTriggerAlertWhenRespiratoryRateBelowLowerLimit() throws InterruptedException {
        RespiratoryRateValidator validator = new RespiratoryRateValidator();
        assertFalse(validator.validate(10f));
    }

    @Test
    void shouldReturnFalseAndTriggerAlertWhenRespiratoryRateAboveUpperLimit() throws InterruptedException {
        RespiratoryRateValidator validator = new RespiratoryRateValidator();
        assertFalse(validator.validate(25f));
    }
}

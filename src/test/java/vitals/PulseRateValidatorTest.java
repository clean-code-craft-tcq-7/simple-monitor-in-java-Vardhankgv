package vitals;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class PulseRateValidatorTest {

    @BeforeEach
    void setup() {
        AlertUtils.setAlertAnimator(Mockito.mock(AlertAnimator.class));
        AlertUtils.setMessageProvider(key -> key);
    }

    @Test
    void shouldReturnTrueWhenPulseRateIsInValidRange() throws InterruptedException {
        PulseRateValidator validator = new PulseRateValidator();
        assertTrue(validator.validate(75));
    }

    @Test
    void shouldReturnFalseWhenPulseRateIsBelowMin() throws InterruptedException {
        PulseRateValidator validator = new PulseRateValidator();
        assertFalse(validator.validate(59));
    }

    @Test
    void shouldReturnFalseWhenPulseRateIsAboveMax() throws InterruptedException {
        PulseRateValidator validator = new PulseRateValidator();
        assertFalse(validator.validate(101));
    }
}

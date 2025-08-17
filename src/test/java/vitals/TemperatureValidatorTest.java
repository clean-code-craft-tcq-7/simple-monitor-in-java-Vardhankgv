package vitals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class TemperatureValidatorTest {

    @BeforeEach
    void setup() {
        AlertUtils.setAlertAnimator(Mockito.mock(AlertAnimator.class));
        AlertUtils.setMessageProvider(key -> key); // Dummy message provider
    }

    @Test
    void shouldReturnTrueWhenTemperatureWithinRange() throws InterruptedException {
        TemperatureValidator validator = new TemperatureValidator();
        assertTrue(validator.validate(98.6f));
    }

    @Test
    void shouldReturnFalseWhenTemperatureBelowThreshold() throws InterruptedException {
        TemperatureValidator validator = new TemperatureValidator();
        assertFalse(validator.validate(94.9f));
    }

    @Test
    void shouldReturnFalseWhenTemperatureAboveThreshold() throws InterruptedException {
        TemperatureValidator validator = new TemperatureValidator();
        assertFalse(validator.validate(102.1f));
    }
}

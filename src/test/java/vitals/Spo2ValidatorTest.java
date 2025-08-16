package vitals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class Spo2ValidatorTest {

    @BeforeEach
    void setup() {
        AlertUtils.setAlertAnimator(Mockito.mock(AlertAnimator.class));
        AlertUtils.setMessageProvider(key -> key);
    }

    @Test
    void shouldReturnTrueWhenSpo2IsEqualToMinimum() throws InterruptedException {
        Spo2Validator validator = new Spo2Validator();
        assertTrue(validator.validate(90));
    }

    @Test
    void shouldReturnFalseWhenSpo2IsBelowMinimum() throws InterruptedException {
        Spo2Validator validator = new Spo2Validator();
        assertFalse(validator.validate(89.9f));
    }

    @Test
    void shouldReturnTrueWhenSpo2IsAboveMinimum() throws InterruptedException {
        Spo2Validator validator = new Spo2Validator();
        assertTrue(validator.validate(95f));
    }
}

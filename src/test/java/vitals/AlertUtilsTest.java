package vitals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AlertUtilsTest {

    private AlertAnimator mockAnimator;

    @BeforeEach
    void setUp() {
        mockAnimator = Mockito.mock(AlertAnimator.class);
        AlertUtils.setAlertAnimator(mockAnimator);
        AlertUtils.setMessageProvider(key -> "Translated message for: " + key);
    }

    @Test
    void shouldCallAnimatorAndDisplayMessageWhenAlertIsTriggered() throws InterruptedException {
        AlertUtils.displayAlertMessage("spo2.outOfRange");

        // Verify animator is called
        verify(mockAnimator, times(1)).animate();
    }

    @Test
    void shouldThrowExceptionWhenMessageProviderIsNotSet() {
        AlertUtils.setMessageProvider(null);
        Exception exception = assertThrows(IllegalStateException.class, () ->
                AlertUtils.displayAlertMessage("spo2.outOfRange")
        );
        assertEquals("MessageProvider not set.", exception.getMessage());
    }
}


package vitals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class VitalsCheckerTest {

    private VitalsChecker vitalsValidator;

    @BeforeEach
    void setup() {
        // Prevent actual alert blinking and delay
        AlertUtils.setAlertAnimator(Mockito.mock(AlertAnimator.class));
        AlertUtils.setMessageProvider(key -> key);

        // Register all known validators
        Map<String, VitalValidator> validators = new HashMap<>();
        validators.put("temperature", new TemperatureValidator());
        validators.put("pulseRate", new PulseRateValidator());
        validators.put("spo2", new Spo2Validator());
        validators.put("bloodSugar", new BloodSugarValidator());
        validators.put("bloodPressure", new BloodPressureValidator());
        validators.put("respiratoryRate", new RespiratoryRateValidator());

        vitalsValidator = new VitalsChecker(validators);
    }

    @Test
    void shouldReturnTrueWhenAllVitalsAreWithinValidRange() throws InterruptedException {
        Map<String, Float> vitals = new HashMap<>();
        vitals.put("temperature", 98.6f);
        vitals.put("pulseRate", 75f);
        vitals.put("spo2", 97f);
        vitals.put("bloodSugar", 90f);
        vitals.put("bloodPressure", 120f);
        vitals.put("respiratoryRate", 16f);

        assertTrue(vitalsValidator.areValidVitals(vitals));
    }

    @Test
    void shouldReturnFalseWhenAnyOneVitalIsInvalid() throws InterruptedException {
        Map<String, Float> vitals = new HashMap<>();
        vitals.put("temperature", 98.6f);
        vitals.put("pulseRate", 75f);
        vitals.put("spo2", 97f);
        vitals.put("bloodSugar", 60f); // invalid
        vitals.put("bloodPressure", 120f);
        vitals.put("respiratoryRate", 16f);

        assertFalse(vitalsValidator.areValidVitals(vitals));
    }

    @Test
    void shouldReturnFalseWhenMultipleVitalsAreInvalid() throws InterruptedException {
        Map<String, Float> vitals = new HashMap<>();
        vitals.put("temperature", 98.6f);
        vitals.put("pulseRate", 55f); // invalid
        vitals.put("spo2", 88f); // invalid
        vitals.put("bloodSugar", 100f);
        vitals.put("bloodPressure", 151f); // invalid
        vitals.put("respiratoryRate", 16f);

        assertFalse(vitalsValidator.areValidVitals(vitals));
    }

    @Test
    void shouldReturnFalseWhenUnknownVitalIsPresentButKnownVitalsAreValid() throws InterruptedException {
        Map<String, Float> vitals = new HashMap<>();
        vitals.put("temperature", 98.6f);
        vitals.put("pulseRate", 75f);
        vitals.put("spo2", 95f);
        vitals.put("unknownVital", 999f); // should be ignored

        assertFalse(vitalsValidator.areValidVitals(vitals));
    }

    @Test
    void shouldReturnTrueWhenNoVitalsAreProvided() throws InterruptedException {
        Map<String, Float> vitals = new HashMap<>();
        assertTrue(vitalsValidator.areValidVitals(vitals)); // nothing to fail
    }

    @Test
    void shouldReturnFalseWhenKnownVitalIsPresentWithNullValidator() throws InterruptedException {
        // Custom case where known vital is provided but no validator is configured
        Map<String, Float> vitals = new HashMap<>();
        vitals.put("temperature", 98.6f);
        vitals.put("spo2", 97f);
        vitals.put("respiratoryRate", 16f);
        vitals.put("bloodSugar", 100f);

        // Do not add pulseRate validator on purpose
        Map<String, VitalValidator> partialValidators = new HashMap<>();
        partialValidators.put("temperature", new TemperatureValidator());
        partialValidators.put("spo2", new Spo2Validator());
        partialValidators.put("bloodSugar", new BloodSugarValidator());
        partialValidators.put("respiratoryRate", new RespiratoryRateValidator());

        VitalsChecker partialChecker = new VitalsChecker(partialValidators);

        // pulseRate will be ignored since it has no validator
        vitals.put("pulseRate", 80f);
        assertFalse(partialChecker.areValidVitals(vitals));
    }
}

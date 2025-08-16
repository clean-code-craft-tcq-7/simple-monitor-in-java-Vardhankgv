package vitals;

import java.util.Map;

public class VitalsChecker {
    private final Map<String, VitalValidator> validators;

    public VitalsChecker(Map<String, VitalValidator> validators) {
        this.validators = validators;
    }

    /**
     * Validates all provided vitals.
     * @param vitals map of vital name to its float value.
     * @return true if all validations pass.
     * @throws InterruptedException if any thread sleep is interrupted during alerts.
     */
    public boolean areValidVitals(Map<String, Float> vitals) throws InterruptedException {
    return vitals.entrySet().stream()
        .allMatch(entry -> isValidVital(entry.getKey(), entry.getValue()));
}

	private boolean isValidVital(String vitalName, Float value) {
		VitalValidator validator = validators.get(vitalName);
		if (validator == null) { 
			return false; // unknown vital is considered not valid
		} 

		try {
			return validator.validate(value);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			return false;
		}
	}

}

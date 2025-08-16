package vitals;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Spo2Validator implements VitalValidator {
    private static final Logger LOG = LoggerFactory.getLogger(Spo2Validator.class);

    private static final int MIN_SPO2_VALUE = 90;

    @Override
    public boolean validate(float spo2) throws InterruptedException {
        boolean isValid = spo2 >= MIN_SPO2_VALUE;
        if (!isValid) {
            LOG.warn("Oxygen Saturation level {} is below minimum {}", spo2, MIN_SPO2_VALUE);
            AlertUtils.displayAlertMessage("spo2.outOfRange");
        }
        return isValid;
    }
}

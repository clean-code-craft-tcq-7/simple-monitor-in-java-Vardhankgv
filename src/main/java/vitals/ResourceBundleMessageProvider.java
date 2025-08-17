package vitals;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.MissingResourceException;

public class ResourceBundleMessageProvider implements MessageProvider {
    private ResourceBundle resourceBundle;
    private String languageCode = "en";

    public ResourceBundleMessageProvider(String languageCode) {
        changeLanguage(languageCode);
    }

    public void changeLanguage(String languageCode) {
        this.languageCode = languageCode;
        initResourceBundle();
    }

    private void initResourceBundle() {
        try {
            Locale locale = new Locale(languageCode);
            this.resourceBundle = ResourceBundle.getBundle("messages", locale);
        } catch (MissingResourceException e) {
            // Fallback to English if the given locale isn't available
            changeLanguage("en");
        }
    }

    @Override
    public String getMessage(String key) {
        if (resourceBundle.containsKey(key)) {
            return resourceBundle.getString(key);
        }
        return "Message not found for key: " + key;
    }
}


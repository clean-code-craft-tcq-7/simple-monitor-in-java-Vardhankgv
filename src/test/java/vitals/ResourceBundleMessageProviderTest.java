package vitals;

import java.util.Locale;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ResourceBundleMessageProviderTest {

    private static final String EXISTING_KEY = "temperature.critical";
    private static final String NON_EXISTENT_KEY = "non.existent.key";

    @BeforeAll
    static void ensureTestEnvironment() {
        // Verify fallback language bundle exists
        Locale.setDefault(Locale.ENGLISH);
    }

    @Test
    void shouldReturnEnglishMessageWhenLanguageIsEN() {
        MessageProvider provider = new ResourceBundleMessageProvider("en");
        String message = provider.getMessage(EXISTING_KEY);

        assertNotNull(message);
        assertFalse(message.contains("Message not found"));
        assertEquals("Temperature is critical!", message);
    }

    @Test
    void shouldReturnGermanMessageWhenLanguageIsDE() {
        MessageProvider provider = new ResourceBundleMessageProvider("de");
        String message = provider.getMessage(EXISTING_KEY);

        assertEquals("Temperatur ist kritisch!", message);
    }

    @Test
    void shouldFallbackWithErrorMessageWhenKeyDoesNotExist() {
        MessageProvider provider = new ResourceBundleMessageProvider("en");
        String message = provider.getMessage(NON_EXISTENT_KEY);

        assertEquals("Message not found for key: non.existent.key", message);
    }

    @Test
    void shouldChangeLanguageDynamicallyAndReturnCorrectMessage() {
        ResourceBundleMessageProvider provider = new ResourceBundleMessageProvider("en");

        assertEquals("Temperature is critical!", provider.getMessage(EXISTING_KEY));

        provider.changeLanguage("de");

        assertEquals("Temperatur ist kritisch!", provider.getMessage(EXISTING_KEY));
    }

    @Test
    void shouldReturnEnglishMessageWhenLanguageCodeIsUpperCase() {
        MessageProvider provider = new ResourceBundleMessageProvider("EN");

        String message = provider.getMessage(EXISTING_KEY);

        assertEquals("Temperature is critical!", message);
    }

    @Test
    void shouldHandleUnsupportedLocaleGracefully() {
        MessageProvider provider = new ResourceBundleMessageProvider("fr"); // No 'messages_fr.properties'

        String message = provider.getMessage(EXISTING_KEY);
        // Falls back to default Locale (likely English)

        assertEquals("Temperature is critical!", message);
    }
}

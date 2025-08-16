package vitals;

public final class AlertUtils {
    private static MessageProvider messageProvider;
    private static AlertAnimator alertAnimator = new ConsoleBlinkingAnimator(); // default

    private AlertUtils() {}

    public static void setMessageProvider(MessageProvider provider) {
        messageProvider = provider;
    }

    public static void setAlertAnimator(AlertAnimator animator) {
        alertAnimator = animator;
    }

    public static void displayAlertMessage(String messageKey) throws InterruptedException {
        if (messageProvider == null) {
            throw new IllegalStateException("MessageProvider not set.");
        }

        String message = messageProvider.getMessage(messageKey);
        System.out.println(message);

        if (alertAnimator != null) {
            alertAnimator.animate();
        }
    }
}




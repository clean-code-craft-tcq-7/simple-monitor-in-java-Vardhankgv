package vitals;

public class ConsoleBlinkingAnimator implements AlertAnimator {

    @Override
    public void animate() throws InterruptedException {
        for (int i = 0; i < 6; i++) {
            System.out.print("\r* ");
            Thread.sleep(1000);
            System.out.print("\r *");
            Thread.sleep(1000);
        }
    }
}


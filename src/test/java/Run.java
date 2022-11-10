import steps.KinopoikSteps;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class Run {
    KinopoikSteps methodAnswer = new KinopoikSteps();
    @Test
    public void run() throws InterruptedException, IOException {
        methodAnswer.openApp();
        Thread.sleep(30000);
        methodAnswer.start();
        methodAnswer.startGame();
    }
}

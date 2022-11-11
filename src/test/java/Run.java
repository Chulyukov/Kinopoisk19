import steps.KinopoiskSteps;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class Run {
    KinopoiskSteps methodAnswer = new KinopoiskSteps();
    @Test
    public void run() throws InterruptedException, IOException {
        methodAnswer.openApp();
        Thread.sleep(30000);
        methodAnswer.start();
        methodAnswer.startGame();
    }
}

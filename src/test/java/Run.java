import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import steps.KinopoiskSteps;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class Run {
    KinopoiskSteps methodAnswer = new KinopoiskSteps();

    @BeforeEach
    void beforeTest() throws InterruptedException {
        methodAnswer.openApp();
        Thread.sleep(60000);
    }

    @Tag("Episode 1")
    @Test
    public void run() throws IOException {
        methodAnswer.startGame();
    }

    @Tag("Episode 2")
    @Test
    public void run2() throws IOException {
        methodAnswer.startGame2();
    }
}

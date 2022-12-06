import method.MethodAnswer;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class RunEpisode2 {
    MethodAnswer methodAnswer = new MethodAnswer();

    @Test
    public void run() throws InterruptedException, IOException {
        methodAnswer.openApp();
        methodAnswer.startNewEpisode();
        methodAnswer.startTextEpisode("src/test/resources/episode2.json");
    }
}
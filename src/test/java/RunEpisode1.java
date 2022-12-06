import method.MethodAnswer;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class RunEpisode1 {
    MethodAnswer methodAnswer = new MethodAnswer();

    @Test
    public void run() throws InterruptedException, IOException {
        methodAnswer.openApp();
        methodAnswer.start();
        methodAnswer.startImageEpisode("src/test/resources/episode1.json");
    }
}
import method.MethodAnswer;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class RunEpisode4 {
    MethodAnswer methodAnswer = new MethodAnswer();

    @Test
    public void run() throws IOException {
        methodAnswer.openApp();
        methodAnswer.startNewEpisode();
        methodAnswer.startImageEpisode("src/test/resources/episode4.json");
    }
}
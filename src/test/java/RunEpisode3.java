import method.MethodAnswer;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class RunEpisode3 {
    MethodAnswer methodAnswer = new MethodAnswer();

    @Test
    public void run() throws IOException {
        methodAnswer.openApp();
        methodAnswer.startNewEpisode();
        methodAnswer.startTextEpisode("src/test/resources/episode3.json");
    }
}
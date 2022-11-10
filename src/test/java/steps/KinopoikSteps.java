package steps;

import com.codeborne.selenide.Condition;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import pages.KinopoiskApp;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.switchTo;

public class KinopoikSteps {

    private final KinopoiskApp kinopoiskApp = new KinopoiskApp();

    private final File FILE = new File("src/test/resources/answers.json");

    public void openApp() {
        open("https://www.kinopoisk.ru/special/birthday19/");
    }

    public void start() {
        kinopoiskApp.game.shouldBe(Condition.visible).click();
        kinopoiskApp.startGame.shouldBe(Condition.visible).click();
    }

    public void startGame() throws InterruptedException, IOException {
        String image, answer;
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> ans = mapper.readValue(
                FILE,
                new TypeReference<Map<String, String>>() {}
        );

        while (true) {
            String text = "";
            String trueAnswer = "";
            image = kinopoiskApp.image.getAttribute("src");
            answer = kinopoiskApp.firstAnswerText.getText();
            if (ans.containsKey(kinopoiskApp.image.getAttribute("src"))) {
                kinopoiskApp.firstAnswerButt(ans.get(kinopoiskApp.image.getAttribute("src"))).click();
                Thread.sleep(2000);
            } else {
                kinopoiskApp.firstAnswerButt.shouldBe(Condition.visible).click();
                Thread.sleep(2000);
                if (!image.equals(kinopoiskApp.image.getAttribute("src"))) {
                    ans.put(image, answer);
                    mapper.writeValue(FILE, ans);
                } else {
                    switchTo().activeElement();
                    text = kinopoiskApp.answer.shouldBe(Condition.visible).getText();
                    int firstIndex = text.indexOf('«');
                    int secondIndex = text.indexOf('»');
                    trueAnswer = kinopoiskApp.answer.shouldBe(Condition.visible).getText().substring(firstIndex + 1, secondIndex);
                    ans.put(image, trueAnswer);
                    mapper.writeValue(FILE, ans);
                    if(kinopoiskApp.nextButt.exists()){
                        kinopoiskApp.nextButt.click();
                    } else {
                        kinopoiskApp.restart.click();
                    }
                }
            }
        }
    }
}
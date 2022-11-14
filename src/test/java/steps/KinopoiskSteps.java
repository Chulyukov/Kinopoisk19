package steps;

import com.codeborne.selenide.Condition;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import pages.KinopoiskApp;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static com.codeborne.selenide.Selenide.*;

public class KinopoiskSteps {

    private final KinopoiskApp kinopoiskApp = new KinopoiskApp();

    public void openApp() {
        open("https://www.kinopoisk.ru/special/birthday19/");
    }

    public void waitNewImage(String image) {
        int time = 0;
        while (time <= 30) {
            if (image.equals(kinopoiskApp.image.getAttribute("src"))) {
                sleep(100);
                time++;
            } else {
                break;
            }
        }
    }

    public void waitNewText(String text) {
        int time = 0;
        while (time <= 30) {
            if (text.equals(kinopoiskApp.gameText.getText())) {
                sleep(100);
                time++;
            } else {
                break;
            }
        }
    }

    public boolean checkThatModalNotFound() {
        int time = 0;
        while (time <= 20) {
            switchTo().activeElement();
            sleep(100);
            if (kinopoiskApp.nextButt.exists() | kinopoiskApp.restart.exists()) {
                return false;
            } else {
                time++;
            }
        }
        return true;
    }

    public void startGame() throws IOException {
        kinopoiskApp.game.shouldBe(Condition.visible).click();
        kinopoiskApp.startGame.shouldBe(Condition.visible).click();
        String image, answer;
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> ans = mapper.readValue(
                new File("src/test/resources/answers.json"),
                new TypeReference<Map<String, String>>() {
                }
        );
        while (true) {
            String text = "";
            String trueAnswer = "";
            image = kinopoiskApp.image.getAttribute("src");
            answer = kinopoiskApp.firstAnswerText.getText();
            if (ans.containsKey(kinopoiskApp.image.getAttribute("src"))) {
                kinopoiskApp.firstAnswerButt(ans.get(kinopoiskApp.image.getAttribute("src"))).click();
                waitNewImage(image);
            } else {
                kinopoiskApp.firstAnswerButt.shouldBe(Condition.visible).click();
                if (checkThatModalNotFound()) {
                    ans.put(image, answer);
                    mapper.writeValue(new File("src/test/resources/answers.json"), ans);
                    waitNewImage(image);
                } else {
                    switchTo().activeElement();
                    text = kinopoiskApp.answer.shouldBe(Condition.visible).getText();
                    int firstIndex = text.indexOf('«');
                    int secondIndex = text.lastIndexOf('»');
                    trueAnswer = kinopoiskApp.answer.shouldBe(Condition.visible).getText().substring(firstIndex + 1, secondIndex);
                    ans.put(image, trueAnswer);
                    mapper.writeValue(new File("src/test/resources/answers.json"), ans);
                    if (kinopoiskApp.nextButt.exists()) {
                        kinopoiskApp.nextButt.click();
                        waitNewImage(image);
                    } else {
                        kinopoiskApp.restart.click();
                        waitNewImage(image);
                    }
                }
            }
        }
    }

    public void startGame2() throws IOException {
        kinopoiskApp.game2.shouldBe(Condition.visible).click();
        kinopoiskApp.startGame.shouldBe(Condition.visible).click();
        String gameText, answer;
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> ans = mapper.readValue(
                new File("src/test/resources/answers2.json"),
                new TypeReference<Map<String, String>>() {
                }
        );
        while (true) {
            String text = "";
            String trueAnswer = "";
            gameText = kinopoiskApp.gameText.getText();
            answer = kinopoiskApp.firstAnswerText.getText();
            if (ans.containsKey(kinopoiskApp.gameText.getText())) {
                kinopoiskApp.firstAnswerButt(ans.get(kinopoiskApp.gameText.getText())).click();
                waitNewText(gameText);
            } else {
                kinopoiskApp.firstAnswerButt.shouldBe(Condition.visible).click();
                if (checkThatModalNotFound()) {
                    ans.put(gameText, answer);
                    mapper.writeValue(new File("src/test/resources/answers2.json"), ans);
                    waitNewText(gameText);
                } else {
                    switchTo().activeElement();
                    text = kinopoiskApp.answer.shouldBe(Condition.visible).getText();
                    int firstIndex = text.indexOf('«');
                    int secondIndex = text.lastIndexOf('»');
                    trueAnswer = kinopoiskApp.answer.shouldBe(Condition.visible).getText().substring(firstIndex + 1, secondIndex);
                    ans.put(gameText, trueAnswer);
                    mapper.writeValue(new File("src/test/resources/answers.json"), ans);
                    if (kinopoiskApp.nextButt.exists()) {
                        kinopoiskApp.nextButt.click();
                        waitNewText(gameText);
                    } else {
                        kinopoiskApp.restart.click();
                        waitNewText(gameText);
                    }
                }
            }
        }
    }
}

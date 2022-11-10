package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class KinopoiskApp {

    public SelenideElement game = $x("//button[contains(text(),'Играть')]");
    public SelenideElement startGame = $x("//button[contains(text(),'Начать игру')]");
    public SelenideElement image = $x("//div[@class='game__test-image']/img");
    public SelenideElement answer = $x("//div[@class='modal-wrong-answer__title']");
    public SelenideElement nextButt = $x("//div[@class='modal-wrong-answer__title']/following-sibling::button");
    public SelenideElement restart = $x("//div[@class='modal-wrong-answer__buttons-wrapper']/button[contains(text(),'Играть ещё раз')]");
    public SelenideElement firstAnswerText = $x("//div[@class='game__test-answers-item']//span");
    public SelenideElement firstAnswerButt = $x("//div[@class='game__test-answers-item']");

    public SelenideElement firstAnswerButt(String key) {
        return $x("//span[.=' " + key + " ']/ancestor::div[@class='game__test-answers-item']");
    }
}

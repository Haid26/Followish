package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class WishlistPage {
    private final SelenideElement wishlistName = $(".MuiTypography-h1.e1az68wg11.css-1e854mf"),
    addPresentButton = $(byText("Добавить подарок"));

    @Step("Проверка названия вишлиста")
    public WishlistPage checkName(String value){
        wishlistName.shouldHave(text(value));
        return this;
    }
}

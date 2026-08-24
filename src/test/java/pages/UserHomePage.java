package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;

public class UserHomePage {
    private final SelenideElement usernameLabel = $(".e1mfh2oq2 "),
    createWishlistButton = $(byText("Создать вишлист"));

    @Step("проверка что мы на странице пользователя")
    public UserHomePage checkPage(String userUrl){
        webdriver().shouldHave(url(userUrl));
        return this;
    }

    @Step("Открыть страницу создания вишлиста")
    public UserHomePage createWishlistClick(){
        createWishlistButton.click();
        return this;
    }
}

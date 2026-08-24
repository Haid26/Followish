package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AuthPage {
    private final SelenideElement emailInput = $("[name=email]"),
            submitButton = $("[type=submit]");

    @Step("Открытие страницы авторизации")
    public AuthPage openPage() {
        open("/auth");
        return this;
    }

    @Step("Переход к странице логина")
    public AuthPage enterEmail(String email){
        emailInput.setValue(email);
        submitButton.click();
        return this;
    }
}

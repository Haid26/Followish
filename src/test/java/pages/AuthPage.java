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

    @Step("Ввод почты")
    public AuthPage setEmail(String email)
    {
        emailInput.setValue(email);
        return this;
    }

    @Step("Нажатие на продолжить")
    public AuthPage submitClick(){
        submitButton.click();
        return this;
    }

    @Step("Ввод почты и переход к вводу пароля")
    public AuthPage enterEmail(String email){
        setEmail(email);
        submitClick();
        return this;
    }
}

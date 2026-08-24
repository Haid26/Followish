package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private final SelenideElement passwordInput = $("[name=password]"),
            submitButton = $("[type=submit]");

    @Step("Ввод пароля и авторизация")
    public LoginPage enterPassword (String password){
        passwordInput.setValue(password);
        submitButton.click();
        return this;
    }
}

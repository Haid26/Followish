package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private final SelenideElement passwordInput = $("[name=password]"),
            submitButton = $("[type=submit]");

    @Step("Ввод пароля")
    public LoginPage setPassword (String password){
        passwordInput.setValue(password);
        return this;
    }

    @Step("Нажатие на Войти")
    public LoginPage enterClick(){
        submitButton.click();
        return this;
    }

    @Step("Ввод пароля и авторизация")
    public LoginPage enterPassword(String password){
        setPassword(password);
        enterClick();
        return this;
    }
}

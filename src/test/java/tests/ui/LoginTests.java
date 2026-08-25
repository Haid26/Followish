package tests.ui;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Configuration.baseUrl;
import static testData.TestData.*;

@Feature("Авторизация пользователя")
@Owner("Haid26")
@DisplayName("Тесты на авторизацию пользователя")
public class LoginTests extends TestBase {

    private static final Logger log = LoggerFactory.getLogger(LoginTests.class);

    @Test
    @Story("user login")
    @Tag("Web")
    @Severity(value = SeverityLevel.BLOCKER)
    @DisplayName("тест на успешную авторизацию пользователя")
    public void shouldLogin() {
        authPage.openPage()
                .setEmail(user.getEmail())
                .submitClick();
        loginPage.setPassword(user.getPassword())
                .enterClick();
        userHomePage.checkPage(baseUrl + DEFAUL_USER_HOMEPAGE);
    }

}

package tests.ui;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Configuration.baseUrl;
import static testData.TestData.*;

@Epic("Авторизация пользователя")
@Owner("Haid26")
@DisplayName("Тесты на авторизацию пользователя")
@Tag("Web")
public class LoginTests extends TestBaseUI {

    @Test
    @Feature("Авторизация пользователя")
    @Story("Авторизация пользователя через UI")
    @Tag("Web")
    @Severity(value = SeverityLevel.BLOCKER)
    @DisplayName("Успешная авторизация пользователя")
    public void shouldLoginWeb() {
        authPage.openPage()
                .setEmail(user.getEmail())
                .submitClick();
        loginPage.setPassword(user.getPassword())
                .enterClick();
        userHomePage.checkPage(baseUrl + DEFAULT_USER_HOMEPAGE);
    }


}

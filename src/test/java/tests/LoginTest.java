package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.AuthPage;

import static com.codeborne.selenide.Configuration.baseUrl;
import static testData.TestData.*;

@Feature("Авторизация пользователя")
@Owner("Haid26")
public class LoginTest extends TestBase{

    private static final Logger log = LoggerFactory.getLogger(LoginTest.class);

    @Test
    @Story("user login")
    @Tag("Web")
    @DisplayName("тест на успешную авторизацию пользователя")
    public void successfulLoginTest(){
        authPage.openPage()
                .enterEmail(DEFAULT_USER);
        loginPage.enterPassword(DEFAULT_PASS);
        userHomePage.checkPage(baseUrl+DEFAUL_USER_HOMEPAGE);
    }

}

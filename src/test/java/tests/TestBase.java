package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.AllureListenerUI;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.AuthPage;
import pages.LoginPage;
import pages.UserHomePage;

import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {
    AuthPage authPage = new AuthPage();
    LoginPage loginPage = new LoginPage();
    UserHomePage userHomePage = new UserHomePage();

    @BeforeAll
    static void setupSelenideConfig() {

        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 5000;

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;

        Configuration.remote = System.getProperty("remoteUrl");
        Configuration.browserSize = System.getProperty("browserSize", "1920x1080");
        Configuration.baseUrl = System.getProperty("baseUrl", "https://followish.io");
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserVersion = System.getProperty("browserVersion");


    }

    @BeforeEach
    void addAllureSelenideListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        AllureListenerUI.screenshotAs("Last screenshot");
        AllureListenerUI.pageSource();
        AllureListenerUI.browserConsoleLogs();
        AllureListenerUI.addVideo();
        tearDown();
    }


    void tearDown() {
        closeWebDriver();
    }
}

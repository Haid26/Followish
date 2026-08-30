package tests.ui;

import api.ApiClient;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import allure.AllureListenerUI;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.AuthPage;
import pages.LoginPage;
import pages.UserHomePage;
import testData.User;
import testData.Wishlist;

import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.sleep;
import static testData.TestData.DEFAULT_PASS;
import static testData.TestData.DEFAULT_USER;

public class TestBaseUI {
    protected static final ApiClient api = new ApiClient();
    AuthPage authPage = new AuthPage();
    LoginPage loginPage = new LoginPage();
    UserHomePage userHomePage = new UserHomePage();
    Wishlist wishlist = new Wishlist();
    User user = new User();

    @BeforeAll
    static void setupConfig() {

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

        RestAssured.baseURI = System.getProperty("apiUrl", "https://core.followish.io");
        RestAssured.basePath = "/api";

    }

    @BeforeEach
    void setUpTestData() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        wishlist.generateTestData();
        user.setEmail(DEFAULT_USER);
        user.setPassword(DEFAULT_PASS);
        sleep(3000); //вынужденная мера, чтобы обойти проверку на бота
    }

    @AfterEach
    void addAttachments() {
        if (WebDriverRunner.hasWebDriverStarted()) {
            AllureListenerUI.screenshotAs("Last screenshot");
            AllureListenerUI.pageSource();
            AllureListenerUI.browserConsoleLogs();
            AllureListenerUI.addVideo();
            tearDown();
        }
    }


    void tearDown() {
        closeWebDriver();
    }
}

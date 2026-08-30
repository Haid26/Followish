package tests.api;

import api.ApiClient;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import testData.User;
import testData.Wishlist;

import static com.codeborne.selenide.Selenide.sleep;
import static testData.TestData.DEFAULT_PASS;
import static testData.TestData.DEFAULT_USER;

public class TestBaseApi {
    protected static final ApiClient api = new ApiClient();
    Wishlist wishlist = new Wishlist();
    User user = new User();

    @BeforeAll
    static void setupConfig(){
        RestAssured.baseURI = System.getProperty("apiUrl", "https://core.followish.io");
        RestAssured.basePath = "/api";
    }

    @BeforeEach
    void setUpTestData() {
        wishlist.generateTestData();
        user.setEmail(DEFAULT_USER);
        user.setPassword(DEFAULT_PASS);
        sleep(3000); //вынужденная мера, чтобы обойти проверку на бота
    }
}

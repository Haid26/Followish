package tests.ui;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.CreationWishlistPage;
import pages.UserHomePage;
import pages.WishlistPage;

import static com.codeborne.selenide.Selenide.sleep;
import static testData.TestData.DEFAULT_PASS;
import static testData.TestData.DEFAULT_USER;

@Feature("Вишлист")
@Owner("Haid26")
@DisplayName("Тесты на вишлисты")
public class WishlistTests extends TestBase {
    CreationWishlistPage creationWishlistPage = new CreationWishlistPage();
    WishlistPage wishlistPage = new WishlistPage();

    @Test
    @Story("create wish list")
    @Tag("Web")
    @Severity(value = SeverityLevel.CRITICAL)
    @DisplayName("тест на создание вишлиста")
    public void shoudCreateWishlist() {
        authPage.openPage()
                .enterEmail(DEFAULT_USER);
        loginPage.enterPassword(DEFAULT_PASS);
        userHomePage.createWishlistClick();
        creationWishlistPage.setName("FIRST_TEST")
                .setComment("To present or not to present - that is the question!")
                .setDate("30.08.2026")
                .saveButtonClick();
        wishlistPage.checkName("FIRST_TEST");

    }
}

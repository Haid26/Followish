package tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.CreationWishlistPage;
import pages.WishlistPage;

import static testData.TestData.*;

@Feature("Вишлист")
@Owner("Haid26")
@DisplayName("Тесты на вишлисты")
public class WishlistTests extends TestBase {
    CreationWishlistPage creationWishlistPage = new CreationWishlistPage();
    WishlistPage wishlistPage = new WishlistPage();

    @Test
    @Story("create wishlist")
    @Tag("Web")
    @Severity(value = SeverityLevel.CRITICAL)
    @DisplayName("тест на создание вишлиста")
    public void shouldCreateWishlist() {
        authPage.openPage()
                .enterEmail(user.getEmail());
        loginPage.enterPassword(user.getPassword());
        userHomePage.createWishlistClick();
        creationWishlistPage.setName(wishlist.getName())
                .setComment(wishlist.getComment())
                .setDate(wishlist.getDateEnd())
                .saveButtonClick();
        wishlistPage.checkName(wishlist.getName())
                .checkAlert(DEFAULT_SUCCESSFUL_WISHLIST_CREATION_MESSAGE);

    }

    @Test
    @Story("wishlist name is mandatory")
    @Tag("Web")
    @Severity(value = SeverityLevel.NORMAL)
    @DisplayName("тест на проверку обязательности имени")
    public void shouldBeMandatoryNameWishlist() {
        authPage.openPage()
                .enterEmail(user.getEmail());
        loginPage.enterPassword(user.getPassword());
        userHomePage.createWishlistClick();
        creationWishlistPage.saveButtonClick()
                .checkEmptyNameValidation(DEFAULT_EMPTY_FIELD_ERROR);

    }

    @Test
    @Story("delete wishlist")
    @Tag("Web")
    @Severity(value = SeverityLevel.NORMAL)
    @DisplayName("тест на удаление вишлиста cо страницы вишлиста")
    public void shouldDeleteWishlistFromWishlistPage() {
        authPage.openPage()
                .enterEmail(user.getEmail());
        loginPage.enterPassword(user.getPassword());
        userHomePage.createWishlistClick();
        creationWishlistPage.setName(wishlist.getName())
                .setComment(wishlist.getComment())
                .setDate(wishlist.getDateEnd())
                .saveButtonClick();
        wishlistPage.deleteWishlist();
        userHomePage.checkAlert(DEFAULT_SUCCESSFUL_WISHLIST_DELETION_MESSAGE);

    }

    @Test
    @Story("delete wishlist")
    @Tag("Web")
    @Severity(value = SeverityLevel.NORMAL)
    @DisplayName("тест на удаление вишлиста с домашней страницы пользователя")
    public void shouldDeleteWishlistFromHomePage() {
        authPage.openPage()
                .enterEmail(user.getEmail());
        loginPage.enterPassword(user.getPassword());
        userHomePage.createWishlistClick();
        creationWishlistPage.setName(wishlist.getName())
                .setComment(wishlist.getComment())
                .setDate(wishlist.getDateEnd())
                .saveButtonClick();
        wishlistPage.goToHomePage();
        userHomePage.deleteWishlist(wishlist.getName())
                .checkAlert(DEFAULT_SUCCESSFUL_WISHLIST_DELETION_MESSAGE);
    }

    @Test
    @Story("edit wishlist")
    @Tag("Web")
    @Severity(value = SeverityLevel.NORMAL)
    @DisplayName("тест на редактирование вишлиста cо страницы вишлиста")
    public void shouldEditWishlistFromWishlistPage() {
        authPage.openPage()
                .enterEmail(user.getEmail());
        loginPage.enterPassword(user.getPassword());
        userHomePage.createWishlistClick();
        creationWishlistPage.setName(wishlist.getName())
                .setComment(wishlist.getComment())
                .setDate(wishlist.getDateEnd())
                .saveButtonClick();
        wishlistPage.goToHomePage();
        userHomePage.gotoEditWishlistPage(wishlist.getName());
        wishlist.generateTestData();
        creationWishlistPage.setName(wishlist.getName())
                .setComment(wishlist.getComment())
                .setDate(wishlist.getDateEnd())
                .saveButtonClick();
        wishlistPage.checkAlert(DEFAULT_SUCCESSFUL_WISHLIST_UPDATE_MESSAGE)
                .checkName(wishlist.getName());
    }

    @Test
    @Story("edit wishlist")
    @Tag("Web")
    @Severity(value = SeverityLevel.NORMAL)
    @DisplayName("тест на редактирование вишлиста c домашней страницы пользователя")
    public void shouldEditWishlistFromHomePage() {
        authPage.openPage()
                .enterEmail(user.getEmail());
        loginPage.enterPassword(user.getPassword());
        userHomePage.createWishlistClick();
        creationWishlistPage.setName(wishlist.getName())
                .setComment(wishlist.getComment())
                .setDate(wishlist.getDateEnd())
                .saveButtonClick();
        wishlistPage.gotoEditWishlistPage();
        wishlist.generateTestData();
        creationWishlistPage.setName(wishlist.getName())
                .setComment(wishlist.getComment())
                .setDate(wishlist.getDateEnd())
                .saveButtonClick();
        wishlistPage.checkAlert(DEFAULT_SUCCESSFUL_WISHLIST_UPDATE_MESSAGE)
                .checkName(wishlist.getName());
    }

}

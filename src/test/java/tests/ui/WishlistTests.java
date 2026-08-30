package tests.ui;

import io.qameta.allure.*;
import models.login.LoginRequestModel;
import models.login.LoginResponseModel;
import models.wishlist.WishlistRequestModel;
import models.wishlist.WishlistResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.CreationWishlistPage;
import pages.WishlistPage;

import static testData.TestData.*;

@Epic("Вишлист")
@Owner("Haid26")
@DisplayName("Тесты на вишлисты")
@Tag("Web")
public class WishlistTests extends TestBaseUI {
    CreationWishlistPage creationWishlistPage = new CreationWishlistPage();
    WishlistPage wishlistPage = new WishlistPage();

    @Test
    @Feature("Создание вишлистов")
    @Story("Создание вишлиста в UI")
    @Tag("Web")
    @Severity(value = SeverityLevel.CRITICAL)
    @DisplayName("Успешное создание вишлиста")
    public void shouldCreateWishlistWeb() {
        LoginRequestModel loginBody = new LoginRequestModel(user.getEmail(), user.getPassword());
        LoginResponseModel loginResponse = api.auth.login(loginBody);
        user.setAccess(loginResponse.accessToken());
        user.setId(loginResponse.userInfo().id());
        authPage.openPage()
                .setToken(user.getAccess())
                .refreshPage();
        userHomePage.createWishlistClick();
        creationWishlistPage.setName(wishlist.getName())
                .setComment(wishlist.getComment())
                .setDate(wishlist.getDateEnd())
                .saveButtonClick();
        wishlistPage.checkName(wishlist.getName())
                .checkAlert(DEFAULT_SUCCESSFUL_WISHLIST_CREATION_MESSAGE);

    }

    @Test
    @Feature("Создание вишлистов")
    @Story("Создание вишлиста в UI")
    @Tag("Web")
    @Severity(value = SeverityLevel.NORMAL)
    @DisplayName("Проверка валидации имени")
    public void shouldBeMandatoryNameWishlist() {
        LoginRequestModel loginBody = new LoginRequestModel(user.getEmail(), user.getPassword());
        LoginResponseModel loginResponse = api.auth.login(loginBody);
        user.setAccess(loginResponse.accessToken());
        user.setId(loginResponse.userInfo().id());
        authPage.openPage()
                .setToken(user.getAccess())
                .refreshPage();
        userHomePage.createWishlistClick();
        creationWishlistPage.saveButtonClick()
                .checkEmptyNameValidation(DEFAULT_EMPTY_FIELD_ERROR);

    }

    @Test
    @Feature("Удаление вишлистов")
    @Story("Удаление вишлиста в UI")
    @Tag("Web")
    @Severity(value = SeverityLevel.NORMAL)
    @DisplayName("Успешное удаление вишлиста cо страницы вишлиста")
    public void shouldDeleteWishlistFromWishlistPage() {
        LoginRequestModel loginBody = new LoginRequestModel(user.getEmail(), user.getPassword());
        LoginResponseModel loginResponse = api.auth.login(loginBody);
        user.setAccess(loginResponse.accessToken());
        user.setId(loginResponse.userInfo().id());
        WishlistRequestModel createBody = new WishlistRequestModel(wishlist.getName(),
                wishlist.getDateEnd(),
                wishlist.getComment(),
                wishlist.getNameVisibleStatus(),
                wishlist.getViewPrivacyStatus(),
                wishlist.getReservePrivacyStatus(),
                wishlist.isProfileLinkVisible(),
                wishlist.getTheme());
        WishlistResponseModel createResponse = api.wishlists.create(createBody, user.getAccess());
        wishlist.setId(createResponse.id());
        wishlist.setLinkKey(createResponse.linkKey());
        authPage.openPage()
                .setToken(user.getAccess())
                .refreshPage();
        wishlistPage.open(wishlist.getLinkKey())
                .deleteWishlist();
        userHomePage.checkAlert(DEFAULT_SUCCESSFUL_WISHLIST_DELETION_MESSAGE);

    }

    @Test
    @Feature("Удаление вишлистов")
    @Story("Удаление вишлиста в UI")
    @Tag("Web")
    @Severity(value = SeverityLevel.NORMAL)
    @DisplayName("Успешное удаление вишлиста с домашней страницы пользователя")
    public void shouldDeleteWishlistFromHomePage() {
        LoginRequestModel loginBody = new LoginRequestModel(user.getEmail(), user.getPassword());
        LoginResponseModel loginResponse = api.auth.login(loginBody);
        user.setAccess(loginResponse.accessToken());
        user.setId(loginResponse.userInfo().id());
        WishlistRequestModel createBody = new WishlistRequestModel(wishlist.getName(),
                wishlist.getDateEnd(),
                wishlist.getComment(),
                wishlist.getNameVisibleStatus(),
                wishlist.getViewPrivacyStatus(),
                wishlist.getReservePrivacyStatus(),
                wishlist.isProfileLinkVisible(),
                wishlist.getTheme());
        WishlistResponseModel createResponse = api.wishlists.create(createBody, user.getAccess());
        wishlist.setId(createResponse.id());
        wishlist.setLinkKey(createResponse.linkKey());
        authPage.openPage()
                .setToken(user.getAccess())
                .refreshPage();
        userHomePage.deleteWishlist(wishlist.getName())
                .checkAlert(DEFAULT_SUCCESSFUL_WISHLIST_DELETION_MESSAGE);
    }

    @Test
    @Feature("Редактирование вишлистов")
    @Story("Редактирование вишлиста в UI")
    @Tag("Web")
    @Severity(value = SeverityLevel.NORMAL)
    @DisplayName("Успешное редактирование вишлиста cо страницы пользователя")
    public void shouldEditWishlistFromHomePage() {
        LoginRequestModel loginBody = new LoginRequestModel(user.getEmail(), user.getPassword());
        LoginResponseModel loginResponse = api.auth.login(loginBody);
        user.setAccess(loginResponse.accessToken());
        user.setId(loginResponse.userInfo().id());
        WishlistRequestModel createBody = new WishlistRequestModel(wishlist.getName(),
                wishlist.getDateEnd(),
                wishlist.getComment(),
                wishlist.getNameVisibleStatus(),
                wishlist.getViewPrivacyStatus(),
                wishlist.getReservePrivacyStatus(),
                wishlist.isProfileLinkVisible(),
                wishlist.getTheme());
        WishlistResponseModel createResponse = api.wishlists.create(createBody, user.getAccess());
        wishlist.setId(createResponse.id());
        wishlist.setLinkKey(createResponse.linkKey());
        authPage.openPage()
                .setToken(user.getAccess())
                .refreshPage();
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
    @Feature("Редактирование вишлистов")
    @Story("Редактирование вишлиста в UI")
    @Tag("Web")
    @Severity(value = SeverityLevel.NORMAL)
    @DisplayName("Успешное редактирование вишлиста c домашней страницы вишлиста")
    public void shouldEditWishlistFromWishlistPage() {
        LoginRequestModel loginBody = new LoginRequestModel(user.getEmail(), user.getPassword());
        LoginResponseModel loginResponse = api.auth.login(loginBody);
        user.setAccess(loginResponse.accessToken());
        user.setId(loginResponse.userInfo().id());
        WishlistRequestModel createBody = new WishlistRequestModel(wishlist.getName(),
                wishlist.getDateEnd(),
                wishlist.getComment(),
                wishlist.getNameVisibleStatus(),
                wishlist.getViewPrivacyStatus(),
                wishlist.getReservePrivacyStatus(),
                wishlist.isProfileLinkVisible(),
                wishlist.getTheme());
        WishlistResponseModel createResponse = api.wishlists.create(createBody, user.getAccess());
        wishlist.setId(createResponse.id());
        wishlist.setLinkKey(createResponse.linkKey());
        authPage.openPage()
                .setToken(user.getAccess())
                .refreshPage();
        wishlistPage.open(wishlist.getLinkKey())
                .gotoEditWishlistPage();
        wishlist.generateTestData();
        creationWishlistPage.setName(wishlist.getName())
                .setComment(wishlist.getComment())
                .setDate(wishlist.getDateEnd())
                .saveButtonClick();
        wishlistPage.checkAlert(DEFAULT_SUCCESSFUL_WISHLIST_UPDATE_MESSAGE)
                .checkName(wishlist.getName());
    }

}

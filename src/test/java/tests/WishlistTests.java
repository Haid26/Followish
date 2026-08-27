package tests;

import io.qameta.allure.*;
import models.common.NotFoundResponseModel;
import models.login.LoginRequestModel;
import models.login.LoginResponseModel;
import models.wishlist.WishlistDeleteModel;
import models.wishlist.WishlistGetListResponseModel;
import models.wishlist.WishlistRequestModel;
import models.wishlist.WishlistResponseModel;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.CreationWishlistPage;
import pages.WishlistPage;
import utils.DateTimeConvertor;

import java.util.Objects;

import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static testData.TestData.*;

@Epic("Вишлист")
@Owner("Haid26")
@DisplayName("Тесты на вишлисты")
public class WishlistTests extends TestBase {
    CreationWishlistPage creationWishlistPage = new CreationWishlistPage();
    WishlistPage wishlistPage = new WishlistPage();

    @Test
    @Feature("Создание вишлистов")
    @Story("Создание вишлиста в UI")
    @Tag("Web")
    @Severity(value = SeverityLevel.CRITICAL)
    @DisplayName("Успешное создание вишлиста")
    public void shouldCreateWishlistWeb() {
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
    @Feature("Создание вишлистов")
    @Story("Создание вишлиста в UI")
    @Tag("Web")
    @Severity(value = SeverityLevel.NORMAL)
    @DisplayName("Проверка валидации имени")
    public void shouldBeMandatoryNameWishlist() {
        authPage.openPage()
                .enterEmail(user.getEmail());
        loginPage.enterPassword(user.getPassword());
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
    @Feature("Удаление вишлистов")
    @Story("Удаление вишлиста в UI")
    @Tag("Web")
    @Severity(value = SeverityLevel.NORMAL)
    @DisplayName("Успешное удаление вишлиста с домашней страницы пользователя")
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
    @Feature("Редактирование вишлистов")
    @Story("Редактирование вишлиста в UI")
    @Tag("Web")
    @Severity(value = SeverityLevel.NORMAL)
    @DisplayName("Успешное редактирование вишлиста cо страницы вишлиста")
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
    @Feature("Редактирование вишлистов")
    @Story("Редактирование вишлиста в UI")
    @Tag("Web")
    @Severity(value = SeverityLevel.NORMAL)
    @DisplayName("Успешное редактирование вишлиста c домашней страницы пользователя")
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

    @Test
    @Feature("Создание вишлистов")
    @Story("Создание вишлиста через API")
    @Tag("Api")
    @Severity(value = SeverityLevel.CRITICAL)
    @DisplayName("Успешное создание вишлиста")
    public void shouldCreateWishlistApi() {
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
        if (Objects.equals(wishlist.getComment(), ""))
            wishlist.setComment(null);
        step("Проверка ответа метода", () -> {
            assertEquals(wishlist.getName(), createResponse.name());
            assertEquals(wishlist.getDateEnd(), DateTimeConvertor.convertDate(createResponse.dateEnd()));
            assertEquals(wishlist.getComment(), createResponse.comment());
            assertEquals(wishlist.getNameVisibleStatus(), createResponse.nameVisibleStatus());
            assertEquals(wishlist.getViewPrivacyStatus(), createResponse.viewPrivacyStatus());
            assertEquals(wishlist.getReservePrivacyStatus(), createResponse.reservePrivacyStatus());
            assertEquals(wishlist.isProfileLinkVisible(), createResponse.isProfileLinkVisible());
            assertEquals(wishlist.getTheme(), createResponse.theme());
            assertEquals(user.getId(), createResponse.userId());
        });

    }

    @Test
    @Feature("Редактирование вишлистов")
    @Story("Редактирование вишлиста через API")
    @Tag("Api")
    @Severity(value = SeverityLevel.CRITICAL)
    @DisplayName("Успешное редактирование вишлиста")
    public void shouldEditWishlistApi() {
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
        wishlist.generateTestData();

        WishlistRequestModel editBody = new WishlistRequestModel(wishlist.getName(),
                wishlist.getDateEnd(),
                wishlist.getComment(),
                wishlist.getNameVisibleStatus(),
                wishlist.getViewPrivacyStatus(),
                wishlist.getReservePrivacyStatus(),
                wishlist.isProfileLinkVisible(),
                wishlist.getTheme());
        WishlistResponseModel editResponse = api.wishlists.edit(editBody, wishlist.getLinkKey(), user.getAccess());
        if (Objects.equals(wishlist.getComment(), ""))
            wishlist.setComment(null);
        step("Проверка ответа метода", () -> {
            assertEquals(wishlist.getName(), editResponse.name());
            assertEquals(wishlist.getDateEnd(), DateTimeConvertor.convertDate(editResponse.dateEnd()));
            assertEquals(wishlist.getComment(), editResponse.comment());
            assertEquals(wishlist.getNameVisibleStatus(), editResponse.nameVisibleStatus());
            assertEquals(wishlist.getViewPrivacyStatus(), editResponse.viewPrivacyStatus());
            assertEquals(wishlist.getReservePrivacyStatus(), editResponse.reservePrivacyStatus());
            assertEquals(wishlist.isProfileLinkVisible(), editResponse.isProfileLinkVisible());
            assertEquals(wishlist.getTheme(), editResponse.theme());
            assertEquals(user.getId(), editResponse.userId());
        });

    }

    @Test
    @Feature("Удаление вишлистов")
    @Story("Удаление вишлиста через API")
    @Tag("Api")
    @Severity(value = SeverityLevel.CRITICAL)
    @DisplayName("Успешное удаление вишлиста")
    public void shouldDeleteWishlistApi() {
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

        WishlistDeleteModel deleteBody = new WishlistDeleteModel(wishlist.getId());
        WishlistDeleteModel deleteResponse = api.wishlists.delete(deleteBody, user.getAccess());
        step("Проверка ответа метода", () ->
                assertEquals(wishlist.getId(), deleteResponse.id()));

    }

    @Test
    @Feature("Получение вишлистов")
    @Story("Получение списка вишлистов через API")
    @Tag("Api")
    @Severity(value = SeverityLevel.CRITICAL)
    @DisplayName("Успешное получение списка вишлистов")
    public void shouldGetListWishlistApi() {
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
        WishlistResponseModel[] getListResponse = api.wishlists.getList(user.getAccess());
        step("Проверка ответа метода", () -> {
            for (int i = 0; i<getListResponse.length;i++)
                assertEquals(user.getId(),getListResponse[i].userId());
        });
    }

    @Test
    @Feature("Удаление вишлистов")
    @Story("Удаление вишлиста через API")
    @Tag("Api")
    @Severity(value = SeverityLevel.NORMAL)
    @DisplayName("Проверка ошибки удаления уже удаленного вишлиста")
    public void shouldNotDeleteWishlistTwiceApi() {
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

        WishlistDeleteModel deleteBody = new WishlistDeleteModel(wishlist.getId());
        WishlistDeleteModel deleteResponse = api.wishlists.delete(deleteBody, user.getAccess());
        NotFoundResponseModel deleteResponse2 = api.wishlists.deleteNotFound(deleteBody,user.getAccess());
        step("Проверка ответа метода", () ->
                assertEquals(DEFAULT_NOT_FOUND_MESSAGE, deleteResponse2.message()));

    }

    @Test
    @Feature("Редактирование вишлистов")
    @Story("Редактирование вишлиста через API")
    @Tag("Api")
    @Severity(value = SeverityLevel.NORMAL)
    @DisplayName("Проверка ошибки при редактировании несуществующего вишлиста")
    public void shouldNotEditNonExistWishlistApi() {
        LoginRequestModel loginBody = new LoginRequestModel(user.getEmail(), user.getPassword());
        LoginResponseModel loginResponse = api.auth.login(loginBody);
        user.setAccess(loginResponse.accessToken());
        user.setId(loginResponse.userInfo().id());
        WishlistRequestModel editBody = new WishlistRequestModel(wishlist.getName(),
                wishlist.getDateEnd(),
                wishlist.getComment(),
                wishlist.getNameVisibleStatus(),
                wishlist.getViewPrivacyStatus(),
                wishlist.getReservePrivacyStatus(),
                wishlist.isProfileLinkVisible(),
                wishlist.getTheme());
        NotFoundResponseModel editResponse = api.wishlists.editNotFound(editBody, DEFAULT_NON_EXIST_WISHLIST_LINK, user.getAccess());
        step("Проверка ответа метода", () ->
                assertEquals(DEFAULT_NOT_FOUND_MESSAGE, editResponse.message()));

    }
}

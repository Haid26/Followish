package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;

public class UserHomePage {
    private final SelenideElement usernameLabel = $(".e1mfh2oq2 "),
            createWishlistButton = $("a[href=\"/app/wishlists/create\"]"),
            alert = $("[role=alert]"),
            confirmDeletionButton = $(byTagAndText("button","Да, удалить"));

    private final ElementsCollection wishlistsList = $$(".MuiTypography-root.MuiTypography-h4.e1qs9cz81.css-7s55ya"),
            wishlistsMenuList = $$("[type=button][props]"),
            wishlistMenuButtons = $$("[role=menuitem]");

    @Step("проверка что мы на странице пользователя")
    public UserHomePage checkPage(String userUrl) {
        webdriver().shouldHave(url(userUrl));
        return this;
    }

    @Step("Открыть страницу создания вишлиста")
    public UserHomePage createWishlistClick() {
        createWishlistButton.click();
        return this;
    }

    @Step("Проверка уведомления об успешной операции")
    public UserHomePage checkAlert(String message) {
        alert.$(byText(message)).exists();
        return this;
    }

    @Step("Удаление вишлиста со страницы пользователя")
    public UserHomePage deleteWishlist(String wishlistName) {
        wishlistsMenuList.get(findWishlistNumber(wishlistName)).click();
        wishlistMenuButtons.get(3).click();
        confirmDeletionButton.click();
        return this;
    }

    @Step("Переход на страницу редактирования вишлиста")
    public UserHomePage gotoEditWishlistPage(String wishlistName){
        wishlistsMenuList.get(findWishlistNumber(wishlistName)).click();
        wishlistMenuButtons.get(0).click();
        return this;
    }

    private int findWishlistNumber(String name) {
        sleep(1000);
        for (int i = 0; i < wishlistsList.size(); i++) {
            if (wishlistsList.get(i).has(text(name)))
                return i;
        }
        return -1;
    }

}

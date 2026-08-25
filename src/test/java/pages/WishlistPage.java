package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class WishlistPage {
    private final SelenideElement wishlistName = $(".MuiTypography-h1.e1az68wg11.css-1e854mf"),
            addPresentButton = $(byText("Добавить подарок")),
            toWishlistsButton = $(byTagAndText("button", "К списку вишлистов")),
            confirmDeletionButton = $(byTagAndText("button", "Да, удалить")),
            alert = $("[role=alert]");

    private final ElementsCollection editWishListButtons = $$("[type=button][props]");

    @Step("Проверка названия вишлиста")
    public WishlistPage checkName(String value) {
        wishlistName.shouldHave(text(value));
        return this;
    }

    @Step("Проверка алерта об операции")
    public WishlistPage checkAlert(String message) {
        alert.$(byText(message)).exists();
        return this;
    }

    @Step("Переход к списку вишлистов")
    public WishlistPage goToHomePage() {
        toWishlistsButton.click();
        return this;
    }

    @Step("Удаление вишлиста со страницы вишлиста")
    public WishlistPage deleteWishlist() {
        editWishListButtons.get(1).click();
        confirmDeletionButton.click();
        return this;
    }

    @Step("Переход к редактированию вишлиста")
    public WishlistPage gotoEditWishlistPage() {
        editWishListButtons.get(0).click();
        return this;
    }

}

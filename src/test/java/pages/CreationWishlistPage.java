package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class CreationWishlistPage {
    private final SelenideElement wishlistNameInput = $("[name=name]"),
            commentInput = $("[name=comment]"),
            dateInput = $("[name=dateEnd]"),
            saveButton = $("[type=button]").$(byText("Сохранить")),
    errorNameMessage = $(".MuiFormHelperText-root");

    @Step("Ввод названия вишлиста")
    public CreationWishlistPage setName(String value) {
        wishlistNameInput.setValue(value);
        return this;
    }

    @Step("Ввод комментария")
    public CreationWishlistPage setComment(String value) {
        commentInput.setValue(value);
        return this;
    }

    @Step("Ввод даты")
    public CreationWishlistPage setDate(String value) {
        dateInput.setValue(value);
        return this;
    }

    @Step("Нажатие на кнопку сохранения")
    public CreationWishlistPage saveButtonClick(){
        saveButton.click();
        return this;
    }

    @Step("Проверка обязательности поля")
    public CreationWishlistPage checkEmptyNameValidation(String errorMessage){
       errorNameMessage.shouldHave(text(errorMessage));
       saveButton.shouldBe(disabled);
       return this;
    }

}

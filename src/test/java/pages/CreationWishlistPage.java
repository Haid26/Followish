package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CreationWishlistPage {
    private final SelenideElement wishlistNameInput = $("[name=name]"),
            commentInput = $("[name=comment]"),
            dateInput = $("[name=dateEnd]"),
            saveButton = $(byTagAndText("button","Сохранить")),
            errorNameMessage = $(".MuiFormHelperText-root"),
    dateChooser = $(".mantine-DatePicker-calendarHeaderLevel");

    private final ElementsCollection yearList = $$(".mantine-DatePicker-yearPickerControl"),
    monthList = $$(".mantine-DatePicker-monthPickerControl"),
    dayList = $$(".mantine-DatePicker-day:not([data-outside=true]):not([disabled])");

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
    public CreationWishlistPage setDate(String date) {
        String day;
        if(date.charAt(0)=='0'){
            day = date.substring(1, 2);
        }
        else {
            day = date.substring(0, 2);
        }
        String month = date.substring(3, 5);
        String year = date.substring(6, 10);
        int monthInt = Integer.parseInt(month);
        dateInput.click();
        dateChooser.click();
        dateChooser.click();
        yearList.findBy(text(year)).click();
        monthList.get(monthInt-1).click();
        dayList.findBy(text(day)).click();
        return this;
    }

    @Step("Нажатие на кнопку сохранения")
    public CreationWishlistPage saveButtonClick() {
        saveButton.click();
        return this;
    }

    @Step("Проверка обязательности поля")
    public CreationWishlistPage checkEmptyNameValidation(String errorMessage) {
        errorNameMessage.shouldHave(text(errorMessage));
        saveButton.shouldBe(disabled);
        return this;
    }

}

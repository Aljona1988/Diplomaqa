package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PaymentPage {

    private SelenideElement fieldNumber = $("[placeholder='0000 0000 0000 0000']");
    private SelenideElement fieldMonth = $("[placeholder='08']");
    private SelenideElement fieldYear = $("[placeholder='22']");
    private SelenideElement fieldOwner = $("fieldset > div:nth-child(3) > span > span:nth-child(1) > span > span > span.input__box > input");
    private SelenideElement fieldCVC = $("[placeholder='999']");
    private SelenideElement continueButton = $$(".button").find(exactText("Продолжить"));

    private SelenideElement successNotification = $(withText("Успешно"));
    private SelenideElement errorNotification = $(withText("Ошибка"));
    private SelenideElement fieldNumberError = $("fieldset > div:nth-child(1) > span > span > span.input__sub");
    private SelenideElement fieldMonthError = $("div:nth-child(2) > span > span:nth-child(1) > span > span > span.input__sub");
    private SelenideElement fieldYearError = $("div:nth-child(2) > span > span:nth-child(2) > span > span > span.input__sub");
    private SelenideElement fieldOwnerError = $("div:nth-child(3) > span > span:nth-child(1) > span > span > span.input__sub");
    private SelenideElement fieldCvcError = $("div:nth-child(3) > span > span:nth-child(2) > span > span > span.input__sub");

    public void enterCardInfo(DataHelper.CardInfo cardInfo) {
        fieldNumber.setValue(cardInfo.getCardNumber());
        fieldMonth.setValue(cardInfo.getMonth());
        fieldYear.setValue(cardInfo.getYear());
        fieldOwner.setValue(cardInfo.getOwner());
        fieldCVC.setValue(cardInfo.getCvc());
        continueButton.click();
    }

    public void successNotification() {
        successNotification.shouldBe(Condition.visible);
    }

    public void errorNotification() {
        errorNotification.shouldBe(Condition.visible);
    }

    public void messInvalidCardNumber() {
        fieldNumberError.shouldHave(text("Неверный формат"));
    }

    public void messInvalidMonth() {
        fieldMonthError.shouldHave(text("Неверный формат"));
    }

    public void messInvalidYear() {
        fieldYearError.shouldHave(text("Неверный формат"));
    }

    public void messInvalidOwner() {
        fieldOwnerError.shouldHave(text("Неверный формат"));
    }

    public void messInvalidCvc() {
        fieldCvcError.shouldHave(text("Неверный формат"));
    }

    public void messEmptyCardNumberField() {
        fieldNumberError.shouldHave(text("Неверный формат"));
    }

    public void messEmptyMonthField() {
        fieldMonthError.shouldHave(text("Неверный формат"));
    }

    public void messEmptyYearField() {
        fieldYearError.shouldHave(text("Неверный формат"));
    }

    public void messEmptyOwnerField() {
        fieldOwnerError.shouldHave(text("Поле обязательно для заполнения"));
    }

    public void messEmptyCvcField() {
        fieldCvcError.shouldHave(text("Неверный формат"));
    }

    public void messExpiredYearField() {fieldYearError.shouldHave(text("Истёк срок действия карты"));
    }

    public void messExpiredMonth() {
        fieldMonthError.shouldHave(text("Неверно указан срок действия карты"));
    }

}


package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CreditCardPage {


    private final SelenideElement payByCredit = $$("h3").find(text("Кредит по данным карты"));
    private SelenideElement fieldNumber = $("[placeholder='0000 0000 0000 0000']");
    private SelenideElement fieldMonth = $("[placeholder='08']");
    private SelenideElement fieldYear = $("[placeholder='22']");
    private SelenideElement fieldOwner = $(byText("Владелец")).parent().$(".input__control");;
    private SelenideElement fieldCVC = $("[placeholder='999']");
    private SelenideElement continueButton = $$(".button").find(exactText("Продолжить"));

    private SelenideElement okNotification = $(withText("Успешно"));
    private SelenideElement nokNotification = $(withText("Ошибка"));
    private SelenideElement fieldNumberError = $("fieldset > div:nth-child(1) > span > span > span.input__sub");
    private SelenideElement fieldMonthError = $("div:nth-child(2) > span > span:nth-child(1) > span > span > span.input__sub");
    private SelenideElement fieldYearError = $("div:nth-child(2) > span > span:nth-child(2) > span > span > span.input__sub");
    private SelenideElement fieldOwnerError = $("div:nth-child(3) > span > span:nth-child(1) > span > span > span.input__sub");
    private SelenideElement fieldCvcError = $("div:nth-child(3) > span > span:nth-child(2) > span > span > span.input__sub");

    public CreditCardPage() {
        payByCredit.shouldBe(visible);
    }
}

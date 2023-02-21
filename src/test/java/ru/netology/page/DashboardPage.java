package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$$;


public class DashboardPage {
    private final SelenideElement payButton = $$("button").find(exactText("Купить"));
    private final SelenideElement creditButton = $$("button").find(exactText("Купить в кредит"));

    //private final SelenideElement payByCredit = $$("h3").find(text("Кредит по данным карты"));

    public DebitCardPage payByDebitCard() {
        payButton.click();
        return new DebitCardPage();
    }

    public DebitCardPage payByCreditCard() {
        creditButton.click();
        return new DebitCardPage();
    }
}

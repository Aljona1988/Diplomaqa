package ru.netology.test;


import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.data.SqlHelper;
import ru.netology.page.PaymentPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class CreditCardTest {

    DashboardPage dashboardPage = new DashboardPage();

    @BeforeEach
    void setup() {
        open(System.getProperty("sut.url"));
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
        SqlHelper.cleanDataBase();
    }

    @Test
    @DisplayName("Оплата по одобренной кредитной карте")
    void shouldPayByAppDC() {
        val paymentPage = dashboardPage.payByCreditCard();
        val approvedCardInformation = DataHelper.getApprovedCardInfo();
        paymentPage.cardInfo(approvedCardInformation);
        paymentPage.okNotification();
        val paymentStatus = SqlHelper.getCreditEntity();
        assertEquals("APPROVED", paymentStatus);
    }

    @Test
    @DisplayName("Оплата по отклоненной кредитной карте")
    void shouldPayNotByDecDC() {
        val paymentPage = dashboardPage.payByCreditCard();
        val declinedCardInformation = DataHelper.getDeclinedCardInfo();
        paymentPage.cardInfo(declinedCardInformation);
        paymentPage.nokNotification();
        val paymentStatus = SqlHelper.getCreditEntity();
        assertEquals("DECLINED", paymentStatus);
    }

    @Test
    @DisplayName("Оплата по кредитной карте с невалидным номером")
    void shouldNotPayByInvNum() {
        val paymentPage = dashboardPage.payByCreditCard();
        val invalidCardInformation = DataHelper.getInvalidCardInfo();
        paymentPage.cardInfo(invalidCardInformation);
        paymentPage.messInvalidCardNumber();
    }

    @Test
    @DisplayName("Оплата по кредитной карте с неполным номером")
    void shouldErrorNotFullNum() {
        val paymentPage = dashboardPage.payByCreditCard();
        val notFullCardInformation = DataHelper.getNotFullCardInfo();
        paymentPage.cardInfo(notFullCardInformation);
        paymentPage.messErrorNum();
    }

    @Test
    @DisplayName("Оплата по кредитной карте с невалидным месяцем")
    void shouldErrorInvalidMonth() {
        val paymentPage = dashboardPage.payByCreditCard();
        val invalidMonthCardInformation = DataHelper.getInvalidMonthCardInfo();
        paymentPage.cardInfo(invalidMonthCardInformation);
        paymentPage.messInvalidMonth();
    }

    @Test
    @DisplayName("Оплата по кредитной карте с указанием истекшего месяца")
    void shouldErrorExpiredMonth() {
        val paymentPage = dashboardPage.payByCreditCard();
        val expiredMonthCardInformation = DataHelper.getExpiredMonthCardInfo();
        paymentPage.cardInfo(expiredMonthCardInformation);
        paymentPage.messExpiredMonth();
    }

    @Test
    @DisplayName("Оплата по кредитной карте с указанием истекшего года")
    void shouldErrorExpiredYear() {
        val paymentPage = dashboardPage.payByCreditCard();
        val expiredYearCardInformation = DataHelper.getExpiredYearCardInfo();
        paymentPage.cardInfo(expiredYearCardInformation);
        paymentPage.messExpiredYearField();
    }

    @Test
    @DisplayName("Оплата по кредитной карте с указанием невалидных значений в поле Владелец")
    void shouldErrorInvalidOwner() {
        val paymentPage = dashboardPage.payByCreditCard();;
        val invalidOwner = DataHelper.getInvalidOwnerCard();
        paymentPage.cardInfo(invalidOwner);
        paymentPage.messInvalidOwner();
    }

    @Test
    @DisplayName("Оплата по кредитной карте с указанием невалидных значений в поле Cvc")
    void shouldErrorCvc() {
        val paymentPage = dashboardPage.payByCreditCard();
        val invalidCvc = DataHelper.getInvalidCvc();
        paymentPage.cardInfo(invalidCvc);
        paymentPage.messInvalidCvc();
    }

    @Test
    @DisplayName("Отравка пустой формы")
    void shouldNotSendEmptyForm() {
        val paymentPage = dashboardPage.payByCreditCard();
        val emptyForm = DataHelper.getEmptyCardInfo();
        paymentPage.cardInfo(emptyForm);
        paymentPage.messEmptyCardNumberField();
        paymentPage.messEmptyMonthField();
        paymentPage.messEmptyYearField();
        paymentPage.messEmptyOwnerField();
        paymentPage.messEmptyCvcField();
    }

    @Test
    @DisplayName("Оплата по кредитной карте с пустым полем Номер карты")
    void shouldErrorEmptyCardNum() {
        val paymentPage = dashboardPage.payByCreditCard();
        val emptyCardNum = DataHelper.getEmptyCardNumber();
        paymentPage.cardInfo(emptyCardNum);
        paymentPage.messEmptyCardNumberField();
    }

    @Test
    @DisplayName("Оплата по кредитной карте с пустым полем Месяц")
    void shouldErrorEmptyMonth() {
        val paymentPage = dashboardPage.payByCreditCard();
        val emptyMonth = DataHelper.getEmptyMonth();
        paymentPage.cardInfo(emptyMonth);
        paymentPage.messEmptyMonthField();
    }
    @Test
    @DisplayName("Оплата по кредитной карте с пустым полем Год")
    void shouldErrorEmptyYear() {
        val paymentPage = dashboardPage.payByCreditCard();
        val emptyYear = DataHelper.getEmptyYear();
        paymentPage.cardInfo(emptyYear);
        paymentPage.messEmptyYearField();
    }
    @Test
    @DisplayName("Оплата по кредитной карте с пустым полем Владелец")
    void shouldErrorEmptyOwner() {
        val paymentPage = dashboardPage.payByCreditCard();
        val emptyOwner = DataHelper.getEmptyOwner();
        paymentPage.cardInfo(emptyOwner);
        paymentPage.messEmptyOwnerField();
    }
    @Test
    @DisplayName("Оплата по кредитной карте с пустым полем Cvc")
    void shouldErrorEmptyCvc() {
        val paymentPage = dashboardPage.payByCreditCard();
        val emptyCvc = DataHelper.getEmptyCvc();
        paymentPage.cardInfo(emptyCvc);
        paymentPage.messEmptyCvcField();
    }
    @Test
    @DisplayName("Оплата по кредитной карте с вводом 000 в поле Cvc")
    void shouldErrorZeroCvc() {
        val paymentPage = dashboardPage.payByCreditCard();
        val zeroCvc = DataHelper.getZeroCvc();
        paymentPage.cardInfo(zeroCvc);
        paymentPage.messInvalidCvc();
    }
    @Test
    @DisplayName("Оплата по кредитной карте с вводом 0 в поле Номер карты")
    void shouldErrorZeroCardNum() {
        val paymentPage = dashboardPage.payByCreditCard();
        val zeroCardNum = DataHelper.getCardZeroNumber();
        paymentPage.cardInfo(zeroCardNum);
        paymentPage.messZeroNum();
    }
    @Test
    @DisplayName("Оплата по кредитной карте с вводом 0 в поле Месяц")
    void shouldErrorZeroMonth() {
        val paymentPage = dashboardPage.payByCreditCard();
        val zeroMonth = DataHelper.getZeroMonthCardInfo();
        paymentPage.cardInfo(zeroMonth);
        paymentPage.messInvalidMonth();
    }
    @Test
    @DisplayName("Оплата по кредитной карте с вводом 0 в поле Год")
    void shouldErrorZeroYear() {
        val paymentPage = dashboardPage.payByCreditCard();
        val zeroYear = DataHelper.getZeroYearCardInformation();
        paymentPage.cardInfo(zeroYear);
        paymentPage.messInvalidYear();
    }
}

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

public class DebitCardTest {

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
    @DisplayName("Оплата по одобренной дебитовой карте")
    void shouldPayByAppDC() {
        val paymentPage = dashboardPage.payByDebitCard();
        val approvedCardInformation = DataHelper.getApprovedCardInfo();
        paymentPage.cardInfo(approvedCardInformation);
        paymentPage.okNotification();
        val paymentStatus = SqlHelper.getPaymentEntity();
        assertEquals("APPROVED", paymentStatus);
    }

    @Test
    @DisplayName("Оплата по отклоненной дебитовой карте")
    void shouldPayNotByDecDC() {
        val paymentPage = dashboardPage.payByDebitCard();
        val declinedCardInformation = DataHelper.getDeclinedCardInfo();
        paymentPage.cardInfo(declinedCardInformation);
        paymentPage.nokNotification();
        val paymentStatus = SqlHelper.getPaymentEntity();
        assertEquals("DECLINED", paymentStatus);
    }

    @Test
    @DisplayName("Оплата по дебитовой карте с невалидным номером")
    void shouldNotPayByInvNum() {
        val paymentPage = dashboardPage.payByDebitCard();
        val invalidCardInformation = DataHelper.getInvalidCardInfo();
        paymentPage.cardInfo(invalidCardInformation);
        paymentPage.messInvalidCardNumber();
    }

    @Test
    @DisplayName("Оплата по дебитовой карте с неполным номером")
    void shouldErrorNotFullNum() {
        val paymentPage = dashboardPage.payByDebitCard();
        val notFullCardInformation = DataHelper.getNotFullCardInfo();
        paymentPage.cardInfo(notFullCardInformation);
        paymentPage.messErrorNum();
    }

    @Test
    @DisplayName("Оплата по дебитовой карте с невалидным месяцем")
    void shouldErrorInvalidMonth() {
        val paymentPage = dashboardPage.payByDebitCard();
        val invalidMonthCardInformation = DataHelper.getInvalidMonthCardInfo();
        paymentPage.cardInfo(invalidMonthCardInformation);
        paymentPage.messInvalidMonth();
    }

    @Test
    @DisplayName("Оплата по дебитовой карте с указанием истекшего месяца")
    void shouldErrorExpiredMonth() {
        val paymentPage = dashboardPage.payByDebitCard();
        val expiredMonthCardInformation = DataHelper.getExpiredMonthCardInfo();
        paymentPage.cardInfo(expiredMonthCardInformation);
        paymentPage.messExpiredMonth();
    }

    @Test
    @DisplayName("Оплата по дебитовой карте с указанием истекшего года")
    void shouldErrorExpiredYear() {
        val paymentPage = dashboardPage.payByDebitCard();
        val expiredYearCardInformation = DataHelper.getExpiredYearCardInfo();
        paymentPage.cardInfo(expiredYearCardInformation);
        paymentPage.messExpiredYearField();
    }

    @Test
    @DisplayName("Оплата по дебитовой карте с указанием невалидных значений в поле Владелец")
    void shouldErrorInvalidOwner() {
        val paymentPage = dashboardPage.payByDebitCard();
        val invalidOwner = DataHelper.getInvalidOwnerCard();
        paymentPage.cardInfo(invalidOwner);
        paymentPage.messInvalidOwner();
    }

    @Test
    @DisplayName("Оплата по дебитовой карте с указанием невалидных значений в поле Cvc")
    void shouldErrorCvc() {
        val paymentPage = dashboardPage.payByDebitCard();
        val invalidCvc = DataHelper.getInvalidCvc();
        paymentPage.cardInfo(invalidCvc);
        paymentPage.messInvalidCvc();
    }

    @Test
    @DisplayName("Отравка пустой формы")
    void shouldNotSendEmptyForm() {
        val paymentPage = dashboardPage.payByDebitCard();
        val emptyForm = DataHelper.getEmptyCardInfo();
        paymentPage.cardInfo(emptyForm);
        paymentPage.messEmptyCardNumberField();
        paymentPage.messEmptyMonthField();
        paymentPage.messEmptyYearField();
        paymentPage.messEmptyOwnerField();
        paymentPage.messEmptyCvcField();
    }

    @Test
    @DisplayName("Оплата по дебитовой карте с пустым полем Номер карты")
    void shouldErrorEmptyCardNum() {
        val paymentPage = dashboardPage.payByDebitCard();
        val emptyCardNum = DataHelper.getEmptyCardNumber();
        paymentPage.cardInfo(emptyCardNum);
        paymentPage.messEmptyCardNumberField();
    }

    @Test
    @DisplayName("Оплата по дебитовой карте с пустым полем Месяц")
    void shouldErrorEmptyMonth() {
        val paymentPage = dashboardPage.payByDebitCard();
        val emptyMonth = DataHelper.getEmptyMonth();
        paymentPage.cardInfo(emptyMonth);
        paymentPage.messEmptyMonthField();
    }
    @Test
    @DisplayName("Оплата по дебитовой карте с пустым полем Год")
    void shouldErrorEmptyYear() {
        val paymentPage = dashboardPage.payByDebitCard();
        val emptyYear = DataHelper.getEmptyYear();
        paymentPage.cardInfo(emptyYear);
        paymentPage.messEmptyYearField();
    }
    @Test
    @DisplayName("Оплата по дебитовой карте с пустым полем Владелец")
    void shouldErrorEmptyOwner() {
        val paymentPage = dashboardPage.payByDebitCard();
        val emptyOwner = DataHelper.getEmptyOwner();
        paymentPage.cardInfo(emptyOwner);
        paymentPage.messEmptyOwnerField();
    }
    @Test
    @DisplayName("Оплата по дебитовой карте с пустым полем Cvc")
    void shouldErrorEmptyCvc() {
        val paymentPage = dashboardPage.payByDebitCard();
        val emptyCvc = DataHelper.getEmptyCvc();
        paymentPage.cardInfo(emptyCvc);
        paymentPage.messEmptyCvcField();
    }
    @Test
    @DisplayName("Оплата по дебитовой карте с вводом 000 в поле Cvc")
    void shouldErrorZeroCvc() {
        val paymentPage = dashboardPage.payByDebitCard();
        val zeroCvc = DataHelper.getZeroCvc();
        paymentPage.cardInfo(zeroCvc);
        paymentPage.messInvalidCvc();
    }
    @Test
    @DisplayName("Оплата по дебитовой карте с вводом 0 в поле Номер карты")
    void shouldErrorZeroCardNum() {
        val paymentPage = dashboardPage.payByDebitCard();
        val zeroCardNum = DataHelper.getCardZeroNumber();
        paymentPage.cardInfo(zeroCardNum);
        paymentPage.messZeroNum();
    }
    @Test
    @DisplayName("Оплата по дебитовой карте с вводом 0 в поле Месяц")
    void shouldErrorZeroMonth() {
        val paymentPage = dashboardPage.payByDebitCard();
        val zeroMonth = DataHelper.getZeroMonthCardInfo();
        paymentPage.cardInfo(zeroMonth);
        paymentPage.messInvalidMonth();
    }
    @Test
    @DisplayName("Оплата по дебитовой карте с вводом 0 в поле Год")
    void shouldErrorZeroYear() {
        val paymentPage = dashboardPage.payByDebitCard();
        val zeroYear = DataHelper.getZeroYearCardInformation();
        paymentPage.cardInfo(zeroYear);
        paymentPage.messInvalidYear();
    }
}
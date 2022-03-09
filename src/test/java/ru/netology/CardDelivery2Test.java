package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.Keys.*;


public class CardDelivery2Test {

    @Test
    public void shouldChangeDate() {

        var daysToAddForFirstMeeting = 3;
        var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
        var daysToAddForSecondMeeting = 5;
        var secondMeetingDate = DataGenerator.generateDate(daysToAddForSecondMeeting);

        open("http://localhost:9999");
//        Configuration.browser = "chrome";
//        Configuration.holdBrowserOpen = true;
        $("[placeholder='Город']").setValue(DataGenerator.generateCity("ru"));
        $("[placeholder='Дата встречи']").doubleClick();
        $("[placeholder='Дата встречи']").sendKeys(BACK_SPACE);
        $("[placeholder='Дата встречи']").setValue(firstMeetingDate);
        $("[name='name']").setValue(DataGenerator.generateName("ru"));
        $("[name='phone']").setValue(DataGenerator.generatePhone("ru"));
        $("[data-test-id='agreement']").click();
        $(withText("Запланировать")).click();

        $("[placeholder='Дата встречи']").doubleClick();
        $("[placeholder='Дата встречи']").sendKeys(BACK_SPACE);
        $("[placeholder='Дата встречи']").setValue(secondMeetingDate);
        $(withText("Запланировать")).click();
        $(withText("Перепланировать")).click();

        $("[class='notification__content']").shouldHave(Condition.text("Встреча успешно запланирована на " + secondMeetingDate),
                Duration.ofSeconds(15));
    }
}
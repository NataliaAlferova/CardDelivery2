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

    String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test
    public void shouldChangeDate() {
        String planningDate = generateDate(3);
        String changePlanningDate = generateDate(5);

        Faker faker = new Faker(new Locale("ru"));
        String name = faker.name().firstName();
        String surname = faker.name().lastName();
        String city = faker.address().cityName();
        String phoneNumber = faker.phoneNumber().phoneNumber();

        open("http://localhost:9999");
        Configuration.browser = "chrome";
        Configuration.holdBrowserOpen = true;
        $("[placeholder='Город']").setValue(city);
        $("[placeholder='Дата встречи']").doubleClick();
        $("[placeholder='Дата встречи']").sendKeys(BACK_SPACE);
        $("[placeholder='Дата встречи']").setValue(planningDate);
        $("[name='name']").setValue(name + " " + surname);
        $("[name='phone']").setValue(phoneNumber);
        $("[data-test-id='agreement']").click();
        $ (withText("Запланировать")).click();

        $("[placeholder='Дата встречи']").doubleClick();
        $("[placeholder='Дата встречи']").sendKeys(BACK_SPACE);
        $("[placeholder='Дата встречи']").setValue(changePlanningDate);
        $(withText("Запланировать")).click();
        $(withText("Перепланировать")).click();

        $("[class='notification__content']").shouldHave(Condition.text("Встреча успешно запланирована на "+ changePlanningDate),
                Duration.ofSeconds(15));
    }
}
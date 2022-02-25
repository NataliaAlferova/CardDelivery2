package ru.netology;
import com.github.javafaker.CreditCardType;
import com.github.javafaker.Faker;
import java.time.LocalDate;
import java.util.Locale;

public class DataGenerator {

    private DataGenerator() {
    }

    public DataGenerator setdDeliveryInfo (String locale) {
            Faker faker =new Faker(new Locale("ru"));
            faker.name().firstName();
            faker.name().lastName();
            faker.address().cityName();
            faker.phoneNumber().phoneNumber();
            return new DataGenerator();
    }
}
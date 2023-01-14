package model;

import com.github.javafaker.Faker;

import java.util.concurrent.TimeUnit;

import static util.RandomUtils.randomInt;

public class Institution {
    private String name;
    private String type;
    private String description;
    private String hours;
    private String address;
    private String tel;

    public Institution(Faker faker) {
        this.name = faker.company().name();
        this.type = faker.business().creditCardType();
        this.description = faker.lorem().fixedString(750);
        this.hours = faker.date().future(randomInt(100)+50, TimeUnit.HOURS).toString();
        this.address = faker.address().fullAddress();
        this.tel = faker.phoneNumber().cellPhone();
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String getHours() {
        return hours;
    }

    public String getAddress() {
        return address;
    }

    public String getTel() {
        return tel;
    }
}

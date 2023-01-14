package model;

import com.github.javafaker.Faker;

import static util.RandomUtils.randomBoolean;
import static util.RandomUtils.randomInt;

public class Program {
    private String title;
    private String days_nr;
    private String transport_type;
    private String is_abroad;
    private String is_for_children;
    private String description;

    public Program(Faker faker) {
        this.title = faker.company().name();
        this.days_nr = String.valueOf(randomInt(11) + 5);
        this.transport_type = faker.lorem().fixedString(20);
        this.is_abroad = String.valueOf(randomBoolean());
        this.is_for_children = String.valueOf(randomBoolean());
        this.description = faker.lorem().fixedString(750);
    }

    public String getTitle() {
        return title;
    }

    public String getDays_nr() {
        return days_nr;
    }

    public String getTransport_type() {
        return transport_type;
    }

    public String getIs_abroad() {
        return is_abroad;
    }

    public String getIs_for_children() {
        return is_for_children;
    }

    public String getDescription() {
        return description;
    }
}

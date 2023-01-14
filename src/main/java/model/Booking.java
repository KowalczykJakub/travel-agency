package model;

import com.github.javafaker.Faker;

import java.util.concurrent.TimeUnit;

import static util.RandomUtils.randomBoolean;
import static util.RandomUtils.randomInt;

public class Booking {
    private String date;
    private String is_paid;
    private String participants_nr;
    private String program_id;
    private String tour_id;

    public Booking(Faker faker) {
        this.date = faker.date().future(randomInt(100)+50, TimeUnit.HOURS).toString();
        this.is_paid = String.valueOf(randomBoolean());
        this.participants_nr = String.valueOf(randomInt(6));
        this.program_id = String.valueOf(randomInt(50));
        this.tour_id = String.valueOf(randomInt(300));
    }

    public String getDate() {
        return date;
    }

    public String getIs_paid() {
        return is_paid;
    }

    public String getParticipants_nr() {
        return participants_nr;
    }

    public String getProgram_id() {
        return program_id;
    }

    public String getTour_id() {
        return tour_id;
    }
}

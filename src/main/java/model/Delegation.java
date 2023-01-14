package model;

import com.github.javafaker.Faker;

import static util.RandomUtils.randomInt;
import static util.RandomUtils.randomRole;

public class Delegation {
    private String program_id;
    private String role;
    private String tour_id;

    public Delegation() {
        this.program_id = String.valueOf(randomInt(50));
        this.role = randomRole();
        this.tour_id = String.valueOf(randomInt(300));
    }

    public String getProgram_id() {
        return program_id;
    }

    public String getRole() {
        return role;
    }

    public String getTour_id() {
        return tour_id;
    }
}

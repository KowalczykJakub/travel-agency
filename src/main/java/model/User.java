package model;

import com.github.javafaker.Faker;

public class User {
    private String document_nr;
    private String email;
    private String first_name;
    private String second_name;
    private String surname;
    private String sex;
    private String login;
    private String password;

    public User(Faker faker) {
        this.document_nr = String.valueOf(faker.number().randomNumber(7, true));
        this.email = faker.internet().emailAddress();
        this.first_name = faker.name().firstName();
        this.second_name = faker.name().firstName();
        this.surname = faker.name().lastName();
        this.sex = faker.demographic().sex();
        this.login = faker.internet().uuid();
        this.password = faker.internet().uuid();
    }

    public String getDocument_nr() {
        return document_nr;
    }

    public String getEmail() {
        return email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getSecond_name() {
        return second_name;
    }

    public String getSurname() {
        return surname;
    }

    public String getSex() {
        return sex;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}

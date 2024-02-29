package ru.work.utils;

import com.github.javafaker.Faker;

public class UserBuilder {
    public static Faker faker = new Faker();
    public static User user = User.builder()
            .firstName(faker.name().firstName())
            .lastName(faker.name().lastName())
            .postalCode(String.valueOf(faker.number().randomNumber(6,false)))
            .build();
}

package ru.work.utils;

import com.github.javafaker.Faker;
import ru.work.models.Item;
import ru.work.models.User;


public class Builder {
    public static Faker faker = new Faker();
    public static User user = User.builder()
            .firstName(faker.name().firstName())
            .lastName(faker.name().lastName())
            .postalCode(String.valueOf(faker.number().randomNumber(6, false)))
            .build();

    public static Item backpack = Item.builder()
            .name("Sauce Labs Backpack")
            .cost(29.99)
            .build();
    public static Item bikeLight = Item.builder()
            .name("Sauce Labs Bike Light")
            .cost(9.99)
            .build();
    public static Item t_shirt = Item.builder()
            .name("Sauce Labs Bolt T-Shirt")
            .cost(15.99)
            .build();
    public static Item jacket = Item.builder()
            .name("Sauce Labs Fleece Jacket")
            .cost(49.99)
            .build();
    public static Item onesie = Item.builder()
            .name("Sauce Labs Onesie")
            .cost(7.99)
            .build();
    public static Item tShirtRed = Item.builder()
            .name("Test.allTheThings() T-Shirt (Red)")
            .cost(15.99)
            .build();
}

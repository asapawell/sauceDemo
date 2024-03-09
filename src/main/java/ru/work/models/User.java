package ru.work.models;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class User {
    private final String firstName;
    private final String lastName;
    private final String postalCode;
}


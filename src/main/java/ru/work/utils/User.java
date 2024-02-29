package ru.work.utils;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class User {
    private final String firstName;
    private final String lastName;
    private final String postalCode;
}


package ru.bzvs.higharc.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDto {

    private String firstName;

    private String secondName;

    private int age;

    private LocalDate birthDate;

    private String biography;

    private String city;

    private String password;
}

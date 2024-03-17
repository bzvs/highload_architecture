package ru.bzvs.higharc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;

    private String firstName;

    private String secondName;

    private int age;

    private Instant birthDate;

    private String biography;

    private String city;

    private String password;
}

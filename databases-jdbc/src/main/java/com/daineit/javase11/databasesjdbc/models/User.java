package com.daineit.javase11.databasesjdbc.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {
    private Integer id;
    private String firstName;
    private String lastName;
    private String gender;
}

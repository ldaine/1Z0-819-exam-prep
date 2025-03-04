package com.daineit.javase11.databasesjdbc.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Venue {
    public Integer id;
    private String name;
    private Integer capacity;
}

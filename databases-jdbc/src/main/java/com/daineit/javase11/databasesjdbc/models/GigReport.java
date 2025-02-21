package com.daineit.javase11.databasesjdbc.models;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GigReport {
    private Date date;
    private String act;
    private String recordlabel;
    private String venue;
    private int ticketsSold;
    private int capacity;
}

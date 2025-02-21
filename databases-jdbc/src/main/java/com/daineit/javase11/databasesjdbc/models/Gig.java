package com.daineit.javase11.databasesjdbc.models;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Gig {
    private Integer id;
    private Integer venueId;
    private Integer actId;
    private Date date;
    private String venue;
    private int ticketsSold;
    private double price;
}

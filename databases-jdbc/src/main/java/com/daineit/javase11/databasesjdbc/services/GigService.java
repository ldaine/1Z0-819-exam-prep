package com.daineit.javase11.databasesjdbc.services;

import java.sql.SQLException;
import java.util.List;

import com.daineit.javase11.databasesjdbc.models.GigReport;
import com.daineit.javase11.databasesjdbc.repositories.GigRepository;

public class GigService {
    final GigRepository repository;

    public GigService() {
        this.repository = new GigRepository();
    }

    public void printGigReport() {
        try {
            List<GigReport> result = this.repository.getGigReport();
            for (GigReport report : result) {
                System.out.printf("%s | %s: %s (%s) [%d out of %d]%n", report.getDate(), report.getVenue(), report.getAct(), report.getRecordlabel(), report.getTicketsSold(), report.getCapacity());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void printTotalSales() {
        try {
            var result = this.repository.getTotalSales();
            System.out.println("Total sales: " + result);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}

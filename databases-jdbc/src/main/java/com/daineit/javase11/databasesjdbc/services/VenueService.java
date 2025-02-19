package com.daineit.javase11.databasesjdbc.services;

import java.sql.SQLException;
import java.util.List;

import com.daineit.javase11.databasesjdbc.models.Venue;
import com.daineit.javase11.databasesjdbc.repositories.VenueRepository;

public class VenueService {
    final VenueRepository repository;

    public VenueService() {
        this.repository = new VenueRepository();
    }

    public void printAll() {
        try {
            List<Venue> result = this.repository.getAll();
            for (Venue act : result) {
                System.out.printf("%d - %s - %d%n", act.getId(), act.getName(), act.getCapacity());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void add(String name, int capacity) {
        try {
            Venue item = new Venue(null, name, capacity);
            this.repository.add(item);
            System.out.println("Item Updated");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }


    public void deleteById(Integer id) throws IllegalArgumentException{
        if(id == null){
            throw new IllegalArgumentException("venue id is required");
        }

        try {
            this.repository.deleteById(id);
            System.out.println("Item deleted");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}

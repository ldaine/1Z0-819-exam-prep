package com.daineit.javase11.databasesjdbc.services;

import java.sql.SQLException;
import java.util.List;

import com.daineit.javase11.databasesjdbc.models.Act;
import com.daineit.javase11.databasesjdbc.repositories.ActRepository;

public class ActService {
    final ActRepository repository;

    public ActService() {
        this.repository = new ActRepository();
    }

    public void printAll() {
        try {
            List<Act> result = this.repository.getAll();
            for (Act act : result) {
                System.out.printf("%d - %s - %s%n", act.getId(), act.getName(), act.getRecordLabel());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void printActsWithRecordLabels() {
        try {
            List<String> result = this.repository.getActsWithRecordLabels();
            for (String line : result) {
                System.out.println(line);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void add(String name, String recordLabel) throws IllegalArgumentException {
        if(name == null || name.isEmpty()){
            throw new IllegalArgumentException("act name is required");
        }
        try {
            Act item = new Act(null, name, recordLabel);
            this.repository.add(item);
            System.out.println("Item Updated");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void deleteById(Integer id) throws IllegalArgumentException{
        if(id == null){
            throw new IllegalArgumentException("act id is required");
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

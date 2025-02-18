package com.daineit.javase11.databasesjdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import com.daineit.javase11.databasesjdbc.models.*;
import com.daineit.javase11.databasesjdbc.repositories.*;

public class DatabasesWithJDBCApp {
    static final DBConfiguration dbConfig = new DBConfiguration();

    public static void main(String[] args) {

        final String url = "jdbc:postgresql://" + dbConfig.getUrl() + "/" + dbConfig.getName();
        Properties props = new Properties();
        props.put("user", dbConfig.getUser());
        props.put("password", dbConfig.getPassword());
        props.put("ssl", true);
        props.put("sslmode", "require");
        try (Connection conn = DriverManager.getConnection(url, props); Scanner scanner = new Scanner(System.in)) {
            ActRepository actRepo = new ActRepository(conn);
            VenueRepository venueRepo = new VenueRepository(conn);
            int input;
            listCommands();
            while (true) {
                System.out.println("Enter a command number:");
    
                if (scanner.hasNextInt()) {
                    input = scanner.nextInt();
                    scanner.nextLine();  // Consume the newline character
    
                    InputCommands command = InputCommands.fromValue(input);
    
                    if (command == null) {
                        System.out.println("Invalid command number. Please try again.");
                        continue;
                    }
    
                    switch (command) {
                        case EXIT:
                            System.out.println("Exiting the application. Goodbye!");
                            return; // Exit the program
                        case HELP:
                            listCommands();
                            break;
                        case ALL:
                            System.out.println("Get All");
                            List<Act> result = actRepo.getAll();
                            for (Act act : result) {
                                System.out.printf("%d - %s - %s%n", act.getId(), act.getName(), act.getRecordLabel());
                            }
                            break;
                        case ADD:
                            System.out.println("Get All");
                            break;
                        default:
                            System.out.println("Unknown command.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.next();  // Consume the invalid input
                }
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private static void listCommands() {
        System.out.printf("%n------------------------------------------%n");
        System.out.println("Possible commands:");
        for (InputCommands command : InputCommands.values()) {
            System.out.println(command.getValue() + ": " + command.getDescription());
        }
        System.out.printf("%n-------------------------------------------%n");
    }
}

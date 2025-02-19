package com.daineit.javase11.databasesjdbc;

import java.util.Scanner;

import com.daineit.javase11.databasesjdbc.services.ActService;
import com.daineit.javase11.databasesjdbc.services.VenueService;

public class DatabasesWithJDBCApp {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            ActService actService = new ActService();
            VenueService venueService = new VenueService();

            int input;
            listCommands();
            while (true) {
                System.out.println("Enter a command number:");

                if (scanner.hasNextInt()) {
                    input = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character

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
                            System.out.println(InputCommands.HELP.getDescription());
                            listCommands();
                            break;
                        case ALL_ACTS:
                            System.out.println(InputCommands.ALL_ACTS.getDescription());
                            actService.printAll();
                            break;
                        case ADD_ACT:
                            System.out.println(InputCommands.ADD_ACT.getDescription());
                            String actName = null;
                            String actRecordLabel = null;
                            System.out.println("Enter act name: ");
                            if (scanner.hasNext()) {
                                actName = scanner.nextLine();
                            }
                            System.out.println("Enter record label: ");
                            if (scanner.hasNext()) {
                                actRecordLabel = scanner.nextLine();
                            }
                            actService.add(actName, actRecordLabel);
                            break;
                        case ALL_VENUES:
                            System.out.println(InputCommands.ALL_VENUES.getDescription());
                            venueService.printAll();
                            break;
                        case ADD_VENUE:
                            System.out.println(InputCommands.ADD_VENUE.getDescription());
                            String venueName = null;
                            int capacity = 0;
                            System.out.println("Enter venue name: ");
                            if (scanner.hasNext()) {
                                venueName = scanner.nextLine();
                            }
                            System.out.println("Enter capacity: ");
                            if (scanner.hasNext()) {
                                capacity = scanner.nextInt();
                            }
                            venueService.add(venueName, capacity);
                            break;
                        case DELETE_ACT:
                            System.out.println(InputCommands.DELETE_ACT.getDescription());
                            Integer actId = null;
                            System.out.println("Enter act id: ");
                            if (scanner.hasNext()) {
                                actId = scanner.nextInt();
                            }
                            actService.deleteById(actId);
                            break;
                        case DELETE_VENUE:
                            System.out.println(InputCommands.DELETE_VENUE.getDescription());
                            Integer venueId = null;
                            System.out.println("Enter venue id: ");
                            if (scanner.hasNext()) {
                                venueId = scanner.nextInt();
                            }
                            venueService.deleteById(venueId);
                            break;
                        default:
                            System.out.println("Unknown command.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.next(); // Consume the invalid input
                }
            }
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

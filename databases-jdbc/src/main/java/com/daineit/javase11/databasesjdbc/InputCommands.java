package com.daineit.javase11.databasesjdbc;

public enum InputCommands {
    EXIT(0, "Exit the application"),
    HELP(1, "Help"),
    ALL_ACTS(2, "Get all acts"),
    ADD_ACT(3, "Add act"),
    ALL_VENUES(4, "Get all venues"),
    ADD_VENUE(5, "Add venue"),
    DELETE_ACT(6, "Delete act"),
    DELETE_VENUE(7, "Delete venue"),
    ACTS_WITH_LABELS(8, "Get acts with record labels"),
    GIG_REPORT(9, "Pring Gig Report"),
    TOTAL_SALES(10, "Pring total sales");

    private int value;
    private String description;

    InputCommands(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public static InputCommands fromValue(int value) {
        for (InputCommands cmd : InputCommands.values()) {
            if (cmd.getValue() == value) {
                return cmd;
            }
        }
        return null;
    }
}

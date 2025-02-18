package com.daineit.javase11.databasesjdbc;

public enum InputCommands {
    EXIT(1, "Exit the application"),
    HELP(2, "Help"),
    ALL(3, "Get all items"),
    ADD(4, "Stop the application");

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

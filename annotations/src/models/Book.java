package models;

import annotations.Author;
import annotations.Environment;
import annotations.Version;

@Author()
@Author("Someone else")
@Author("User x")
@Version(value = 3, author = "Me x2")
@Environment("prod")
public class Book {
    private String title;

    public Book(String title) {
        this.title = title;
    }
}

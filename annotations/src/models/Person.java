package models;

import annotations.Environment;

@Environment("staging")
public class Person {
    private Long id;
    private String name;

    public Person(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String greet(){
        return "Hi, my name is " + this.name + "!" ;
    }

    public String getDetails(){
        return String.format("[%d] %s", this.id, this.name);
    }

    @Deprecated
    public String getDetailsOld(){
        return String.format("(%d) %s", this.id, this.name);
    }

}

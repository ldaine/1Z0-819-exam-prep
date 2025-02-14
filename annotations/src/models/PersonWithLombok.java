package models;

import annotations.Environment;
import lombok.ToString;

@Environment("staging")
@ToString
public class PersonWithLombok {
    private Long id;
    private String name;

    public PersonWithLombok(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String greet(){
        return "Hi, my name is " + this.name + "!" ;
    }

}

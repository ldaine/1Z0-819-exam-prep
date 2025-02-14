package models;

import annotations.Version;

@Version(value = 2, author = "Me", environment = { "prod", "dev" })
// @Version(1) - this would also work, if there is only 'value' field
public class Developer extends Person {

    @Version(author = "Someone")
    public Developer(Long id, String name) {
        super(id, name);
    }

    @Override // this is opetional
    public String greet() {
        return super.greet() + " I am a developer.";
    }

    // this also overrides the method
    public String getDetails() {
        return "DEV: " + super.getDetails();
    }

    // @Version(value = 1, author = "Me") - doesn work beacuse 'ElementType.METHOD'
    // is not specified in Annotation target list
    @Override
    public String getDetailsOld() {
        return "OLD DEVELOPER: " + super.getDetails();
    }
}

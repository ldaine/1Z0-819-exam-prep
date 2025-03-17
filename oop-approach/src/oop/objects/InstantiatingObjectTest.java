package oop.objects;

public class InstantiatingObjectTest {
    private String prop1;
    private String prop2;
    private int prop3;

    {
        System.out.println("Initializer block. This is executed before constructor");
        System.out.println("Init Prop 1 value: " + prop1);
        System.out.println("Init Prop 2 value: " + prop2);
        System.out.println("Init Prop 3 value: " + prop3);
    }

    private InstantiatingObjectTest() {
        System.out.println("Executing No prop constructor");
    }

    public InstantiatingObjectTest(String prop1) {
        this();
        System.out.println("Executing 1 prop constructor");
        this.prop1 = prop1;
    }

    public String getProp1() {
        return this.prop1;
    }

    public String getProp2() {
        return this.prop2;
    }

    public int getProp3() {
        return this.prop3;
    }
}

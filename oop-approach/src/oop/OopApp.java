package oop;
import oop.objects.InstantiatingObjectTest;

public class OopApp {
    public static void main(String[] args) throws Exception {
        System.out.println("OOP Approach");
        instantiatingObjects();
    }

    private static void instantiatingObjects() {
        // instantiating objects
        printSeparator("Instantiating Objects");
        //new InstantiatingObjectTest(); // wont work because no prop constructir is set to private
        var test1 = new InstantiatingObjectTest("test 1");
        System.out.println("Test 1 Prop 1: " + test1.getProp1());
        // All properties which are not set in teh constructor has default type calue. 
        System.out.println("Properties which are not set:");
        System.out.println("Test 1 Prop 2: " + test1.getProp2()); // String is null
        System.out.println("Test 1 Prop 3: " + test1.getProp3()); // int is null
    }

    private static void printSeparator(String title) {
        System.out.println("----------------------------------");
        System.out.println(title);
        System.out.println("----------------------------------");
    }
}

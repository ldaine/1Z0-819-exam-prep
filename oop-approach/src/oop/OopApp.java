package oop;
import oop.objects.InstantiatingObjectTest;

public class OopApp {
    public static void main(String[] args) throws Exception {
        System.out.println("OOP Approach");
        instantiatingObjects();
        arbitraryNumberOfArguments();
        parametersVsArguments();
    }

    private static void parametersVsArguments() {
        printSeparator("Parameters vs Arguments");
        int firstArgument = 1;
        String secondArgument = "mystring";
        // the values passed to a method are arguments
        testMethod(firstArgument, secondArgument);
    }

    private static void testMethod(int firstParameter, String secondParameter){
        // firstParameter and secondParameter are parameters defined in method 
        System.out.println("Parameter value: " + firstParameter);
        System.out.println("Parameter value: " + secondParameter);
    }
    private static void arbitraryNumberOfArguments() {
        printSeparator("Arbitrary number of arguments in methods");
        //defining method when you dont know how many attributes should be provided
        printStrings("11", "12");
        printStrings("21", "22", "23", "24", "25");
    }

    private static void printStrings(String... values){
        for(String value : values){
            System.out.println(value);
        }
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

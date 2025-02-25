package com.daineit.lambdaexpr;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import com.daineit.lambdaexpr.data.CourseData;
import com.daineit.lambdaexpr.models.Course;
import com.daineit.lambdaexpr.models.CourseCategory;

public class LambdaApp {

    interface CourseFilter {
        boolean accept(Course course);
    }

    interface CourseFactory {
        Course create(CourseCategory category, String title, BigDecimal price, Integer durationInHours);
    }

    public static void main(String[] args) throws Exception {
        final List<Course> courses = CourseData.get();
        sortByPriceWithLambda(courses);
        printSeparator("ALL DATA - SORTED BY PRICE");
        printData(courses);

        // values for lambda expressions must be final or effectively final
        // (they should not be modified inside or outside lambda expressions)
        BigDecimal maxPrice = new BigDecimal(800);
        // maxPrice = new BigDecimal(900); // this would cause error in lambda
        // expression
        CourseFilter filter = course -> course.getPrice().compareTo(maxPrice) < 0;
        printSeparator("FILTERED DATA WITH MAX PRICE " + maxPrice);
        printFilteredData(courses, filter);

        printSeparator("USAGE OF 'this.' and 'super.' ");
        // this and super have the same meaning as in suurrounding code. This is different in anynomous class. 
        new ThisExample().printMessage();

        // EXCEPTION HANDLING
        try (FileWriter writer = new FileWriter("myfile.txt")) {
            // lambda expression cannot throw an exception. If you use an function inside of
            // lambda expression which may throw an exception, you need to catch it inside
            // of lambda manually.
            courses.forEach(course -> {
                try {
                    writer.write(course.toString() + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e.getMessage());
                }
            });
        } catch (IOException | RuntimeException e) {
            // TODO: handle exception
        }

        // METHOD REFERENCES
        printSeparator("METHOD REFERENCES");
        // with lambda expression
        // courses.forEach(course -> System.out.println(course));

        // with method reference
        // method references is a pointer to a method which also implement functional
        // interface.
        // is case of .forEach the used method should match the Consumer functional
        // interface 'void methodName(T value)' - returns void and accepts Class
        courses.forEach(System.out::println);

        // TYPES OF METHOD REFERENCES

        // static
        courses.removeIf(LambdaApp::isExpensive); // class name :: method name

        // instance methods
        printSeparator("METHOD REFERENCES - instance methods - all courses after removed expensive courses");
        courses.forEach(System.out::println); // instance method :: method name
        printSeparator("METHOD REFERENCES - instance methods - print only title");
        courses.stream()
                .map(Course::getTitle) // class name :: method name
                .forEach(System.out::println); // instance method :: method name

        // constructor methods
        printSeparator("METHOD REFERENCES - constructor methods");
        CourseFactory factory = Course::new; // class name :: new
        Course testCourse = factory.create(CourseCategory.TECHNOLOGY, "TEST", new BigDecimal("100.00"), 8);
        System.out.println(testCourse);
    }

    static boolean isExpensive(Course course) {
        return course.getPrice().compareTo(new BigDecimal(800)) >= 0;
    }

    private static void printFilteredData(List<Course> courses, CourseFilter filter) {
        for (Course course : courses) {
            if (filter.accept(course)) {
                System.out.println(course);
            }
        }
    }

    /* sort - this updates the existing array */
    private static void sortByPriceWithLambda(List<Course> courses) {
        // using anonymous class

        // courses.sort(new Comparator<Course>() {
        // @Override
        // public int compare(Course c1, Course c2) {
        // return c1.getPrice().compareTo(c2.getPrice());
        // }
        // });

        // using lambda
        courses.sort((c1, c2) -> c1.getPrice().compareTo(c2.getPrice()));
    }

    private static void printData(List<Course> courses) {
        for (Course course : courses) {
            System.out.println(course);
        }
    }

    private static void printSeparator(String title) {
        System.out.println("----------------------------------");
        System.out.println(title);
        System.out.println("----------------------------------");
    }
}

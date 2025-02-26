package functionalinterface;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

import functionalinterface.data.CourseData;

import functionalinterface.models.*;

public class FunctionalInterfaceApp {

    public static void main(String[] args) throws Exception {
        final List<Course> courses = CourseData.get();

        printCoursePrice(courses, "Java Programming for Beginners");

        printCoursesByCategory(courses);

        printAffordableCourse(courses);

    }

    private static void printAffordableCourse(final List<Course> courses) {
        BigDecimal maxPrice = new BigDecimal("600.00");
        Predicate<Course> isTechCourse = course -> course.getCategory() == CourseCategory.TECHNOLOGY;
        Predicate<Course> isAffordable = course -> course.getPrice().compareTo(maxPrice) < 0;

        findCourse(courses, isTechCourse.and(isAffordable)) // usage of functional composition
                .ifPresentOrElse(
                        course -> System.out.printf("'%s' is affordable course for EUR %.2f%n", course.getTitle(),
                                course.getPrice()),
                        () -> System.out.println("There are no affordable courses"));
    }

    private static void printCoursesByCategory(final List<Course> courses) {
        Map<CourseCategory, List<Course>> coursesByCategory = new HashMap<>();
        printCourseByCategory(courses, coursesByCategory);
    }

    private static void printCourseByCategory(final List<Course> courses,
            Map<CourseCategory, List<Course>> coursesByCategory) {
        for (Course course : courses) {
            CourseCategory category = course.getCategory();

            // normal style
            // if(!coursesByCategory.containsKey(category)){
            // coursesByCategory.put(category, new ArrayList<>());
            // }

            // coursesByCategory.get(category).add(course);

            // functional style
            coursesByCategory.computeIfAbsent(category, c -> new ArrayList<>()).add(course);
        }

        // print the result
        coursesByCategory.forEach((mapCategory, mapList) -> {
            System.out.println("Category: " + mapCategory);
            mapList.forEach(item -> System.out.println("- " + item.getTitle()));
        });
    }

    private static void printCoursePrice(final List<Course> courses, final String title) {
        Predicate<Course> findCoursePredicate = course -> course.getTitle().equals(title);

        // standard programming style
        // Optional<Course> result = findCourse(courses, predicate);
        // if (result.isPresent()) {
        // Course course = result.get();
        // System.out.println(course);
        // }

        // functional programming style
        // findCourse(courses, predicate)
        // .map(Course::getPrice) // same as course -> course.getPrice()
        // .ifPresentOrElse(
        // price -> System.out.printf("The price of '%s' is EUR %.2f%n", title, price),
        // () -> System.out.printf("%s is not available%n", title));

        // or
        // findCourse(courses, findCoursePredicate)
        // .map(Course::getPrice) // same as course -> course.getPrice()
        // .map(price -> String.format("The price of '%s' is EUR %.2f%n", title, price))
        // .ifPresentOrElse(
        // System.out::println,
        // () -> System.out.printf("%s is not available%n", title));

        // or
        Function<Course, BigDecimal> courseToPrice = Course::getPrice; // same as course -> course.getPrice()
        Function<BigDecimal, String> priceToMessage = price -> String.format("The price of '%s' is EUR %.2f%n", title,
                price);
        // use functional composition to get to get rid of second .map
        // Function<Course, String> courseToMessage =
        // courseToPrice.andThen(priceToMessage);
        Function<Course, String> courseToMessage = priceToMessage.compose(courseToPrice);

        findCourse(courses, findCoursePredicate)
                .map(courseToMessage)
                .ifPresentOrElse(
                        System.out::println,
                        () -> System.out.printf("%s is not available%n", title));

    }

    static Optional<Course> findCourse(List<Course> courses, Predicate<Course> predicate) {
        for (Course course : courses) {
            if (predicate.test(course)) {
                return Optional.of(course);
            }
        }
        return Optional.empty();
    }
}

package streamexamples;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import streamexamples.data.CourseData;

import streamexamples.models.*;

public class StreamExampleApp {

    public static void main(String[] args) throws Exception {
        final List<Course> courses = CourseData.get();
        streamMainPrinciple(courses);

        creatingStreams(courses);

        filterAndTransform(courses);

        searchingInStreams(courses);

        collectingStream(courses);
    }

    private static void collectingStream(final List<Course> courses) {
        List<String> bussinesCourseTitles = courses.stream()
                .filter(c -> c.getCategory() == CourseCategory.BUSINESS)
                .map(Course::getTitle)
                .collect(Collectors.toList());

        System.out.println(bussinesCourseTitles);
    }

    private static void searchingInStreams(final List<Course> courses) {
        Optional<Course> element = courses.stream()
                .filter(c -> c.getCategory() == CourseCategory.SCIENCE)
                // .findAny() // returns any one element which is returned from filter
                .findFirst();
        element.ifPresent(System.out::println);

        Predicate<Course> isScienceCourse = c -> c.getCategory() == CourseCategory.SCIENCE;
        boolean foundScienceCourse = courses.stream()
                // .noneMatch(isScienceCourse) // check if all elements DOES NOT match the
                // criteria
                // .allMatch(isScienceCourse) // check if all elements match the criteria
                .anyMatch(isScienceCourse); // check if any element match the criteria
    }

    private static void filterAndTransform(final List<Course> courses) {
        Predicate<Course> filterPredicate = c -> c.getCategory() == CourseCategory.ARTS;
        courses.stream()
                .filter(filterPredicate)
                .forEach(System.out::println);

        courses.stream()
                .map(c -> String.format("%s - %s", c.getTitle(), c.getDurationInHours()))
                .forEach(System.out::println);

        Pattern spaces = Pattern.compile("\\s+");
        courses.stream()
                .flatMap(c -> spaces.splitAsStream(c.getTitle())) // splits the title and returns seperate words
                .forEach(System.out::println);
    }

    private static void creatingStreams(final List<Course> courses) {
        Stream<Course> s1 = courses.stream();

        String[] array = new String[] { "one", "two", "three" };
        Stream<String> s2 = Arrays.stream(array);
        Stream<String> s3 = Stream.of("one", "two", "three");
        Stream<String> s4 = Stream.ofNullable("four");
        Stream<?> s5 = Stream.empty();

        // strart is inclusive, end is eclusive
        IntStream dice = new Random().ints(1, 7);
        try (BufferedReader in = new BufferedReader(new FileReader("README.md", StandardCharsets.UTF_8))) {
            in.lines().forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void streamMainPrinciple(final List<Course> courses) {
        // intermediate operations (passes the stream to the next operation)
        // example: filter, map, etc.
        // terminal operations receives stream and does the final action (for example
        // prints the result)
        // example: forEach

        // this wont print anything because there is no terminate operator assigned
        Stream<Course> courseStream = courses.stream().map(course -> {
            System.out.println(course);
            return course;
        });
        // using terminate operator - it can be also empty. the main point is that it is
        // there and it retirns a value which is not stream
        // outcomment the next lie if you want to print the courses
        // courseStream.forEach(i -> {});
    }

}

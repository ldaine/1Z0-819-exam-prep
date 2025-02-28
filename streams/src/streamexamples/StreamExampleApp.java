package streamexamples;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
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

                creatingStreamsWithFactoryMethods();

                reducingStreams(courses);

                collectingStreams(courses);

                groupingStreams(courses);

                partitioningCollectors(courses);

                // to use parallel streams switch courses.stream() to courses.parallelStream()
        }

        private static void partitioningCollectors(final List<Course> courses) {
                BigDecimal maxPrice = new BigDecimal("600.00");
                Map<Boolean, List<Course>> partitionedCourses = courses.stream()
                                .collect(Collectors.partitioningBy(c -> c.getPrice().compareTo(maxPrice) < 0));

                System.out.println("Affordable: ");
                partitionedCourses.get(true).forEach(System.out::println);

                System.out.println("Not Affordable: ");
                partitionedCourses.get(false).forEach(System.out::println);
        }

        private static void groupingStreams(final List<Course> courses) {
                Map<CourseCategory, List<Course>> coursesByCategory = courses.stream()
                                .collect(Collectors.groupingBy(Course::getCategory));
                Map<CourseCategory, List<String>> courseTitlesByCategory = courses.stream()
                                .collect(Collectors.groupingBy(
                                                Course::getCategory,
                                                Collectors.mapping(Course::getTitle, Collectors.toList())));

                Map<CourseCategory, BigDecimal> totalPerCategory = courses.stream()
                                .collect(Collectors.groupingBy(
                                                Course::getCategory,
                                                Collectors.mapping(
                                                                Course::getPrice,
                                                                Collectors.reducing(BigDecimal.ZERO,
                                                                                BigDecimal::add))));
        }

        private static void collectingStreams(final List<Course> courses) {
                // collection is nothing else as mutable reduction -
                // a collection op reduces a stream into a mutable result container.
                // a collect operation requires 3 functions:
                // supplier (Supplier), accumulator (BiConsumer) and combiner (BiConsumer)

                // using reducer
                List<String> result1 = courses.stream()
                                .reduce(new ArrayList<String>(),
                                                (list, course) -> {
                                                        ArrayList<String> newList = new ArrayList<>(list);
                                                        newList.add(course.getTitle());
                                                        return newList;
                                                },
                                                (list1, list2) -> {
                                                        ArrayList<String> newList = new ArrayList<>(list1);
                                                        newList.addAll(list2);
                                                        return newList;
                                                });

                // using collect
                List<String> result2 = courses.stream()
                                .collect(ArrayList::new,
                                                (list, course) -> list.add(course.getTitle()),
                                                ArrayList::addAll);

                // using Collectors factory methods
                Map<CourseCategory, BigDecimal> result3 = courses.stream()
                                .collect(Collectors.toMap(
                                                Course::getCategory, // key mapper function
                                                Course::getPrice, // calue mapper function
                                                BigDecimal::add // merge function
                                ));

                System.out.println(result3);
        }

        private static void reducingStreams(final List<Course> courses) {
                // reduce with 1 value
                Optional<BigDecimal> reducedValue = courses.stream()
                                .map(Course::getPrice)
                                .reduce((result, element) -> result.add(element));
                reducedValue.ifPresentOrElse(System.out::println, () -> System.out.println("not found"));

                // alternative
                Optional<BigDecimal> reducedValue2 = courses.stream()
                                .map(Course::getPrice)
                                .reduce(BigDecimal::add);
                reducedValue2.ifPresentOrElse(System.out::println, () -> System.out.println("not found"));

                // reduce with 2 values
                BigDecimal reducedValue3 = courses.stream()
                                .map(Course::getPrice)
                                .reduce(BigDecimal.ZERO, BigDecimal::add);
                System.out.println("reducedValue3 value: " + reducedValue3);

                // reduce with 3 values
                BigDecimal reducedValue4 = courses.stream()
                                .reduce(BigDecimal.ZERO, (result, course) -> result.add(course.getPrice()),
                                                BigDecimal::add);
                System.out.println("reducedValue4 value: " + reducedValue4);
        }

        private static void creatingStreamsWithFactoryMethods() {
                Stream<UUID> ids = Stream.generate(UUID::randomUUID);
                ids.limit(10).forEach(System.out::println);

                Stream<BigInteger> powersOfTwo = Stream.iterate(BigInteger.ONE, n -> n.multiply(BigInteger.TWO));
                powersOfTwo.limit(20).forEach(System.out::println);

                Stream<Character> alphabet = Stream.iterate('A', letter -> letter <= 'Z',
                                letter -> (char) (letter + 1));
                alphabet.forEach(System.out::println);

                Stream.Builder<String> builder = Stream.builder();
                builder.add("one");
                builder.add("two");
                builder.add("three");
                Stream<String> streamFromBuilder = builder.build();
                String result = streamFromBuilder.collect(Collectors.joining(", "));
                System.out.println("Result (streamFromBuilder): " + result);
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
                                .flatMap(c -> spaces.splitAsStream(c.getTitle())) // splits the title and returns
                                                                                  // seperate words
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

package functionalinterface;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import functionalinterface.data.CourseData;

import functionalinterface.models.Course;

public class FunctionalInterfaceApp {

    static Optional<Course> findCourse(List<Course> courses, Predicate<Course> predicate){
        for (Course course : courses) {
            if(predicate.test(course)){
                return Optional.of(course);
            }
        }
        return Optional.empty();
    }

    
    public static void main(String[] args) throws Exception {
        final List<Course> courses = CourseData.get();

        final String title = "Java Programming for Beginners";
        Predicate<Course> predicate = course -> course.getTitle().equals(title);
        Optional<Course> result = findCourse(courses, predicate);
        if(result.isPresent()){
            Course course = result.get();
            System.out.println(course);
        }
    }
}

package functionalinterface.data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import functionalinterface.models.*;

public class CourseData {

    private static final List<Course> COURSES = Arrays.asList(
            // TECHNOLOGY courses
            new Course(CourseCategory.TECHNOLOGY, "Java Programming for Beginners", new BigDecimal("500.00"), 40),
            new Course(CourseCategory.TECHNOLOGY, "Advanced Python Programming", new BigDecimal("750.50"), 60),
            new Course(CourseCategory.TECHNOLOGY, "Web Development with JavaScript", new BigDecimal("600.75"), 50),
            new Course(CourseCategory.TECHNOLOGY, "Data Science with R", new BigDecimal("900.25"), 80),
            new Course(CourseCategory.TECHNOLOGY, "Machine Learning with Python", new BigDecimal("1200.00"), 100),
            new Course(CourseCategory.TECHNOLOGY, "Cybersecurity Fundamentals", new BigDecimal("800.40"), 70),
            new Course(CourseCategory.TECHNOLOGY, "Cloud Computing with AWS", new BigDecimal("1100.90"), 90),
            new Course(CourseCategory.TECHNOLOGY, "Mobile App Development with Flutter", new BigDecimal("950.30"), 75),
            new Course(CourseCategory.TECHNOLOGY, "Blockchain Technology", new BigDecimal("1300.00"), 85),
            new Course(CourseCategory.TECHNOLOGY, "DevOps Practices", new BigDecimal("1000.10"), 80),

            // BUSINESS courses
            new Course(CourseCategory.BUSINESS, "Project Management Professional (PMP)", new BigDecimal("1000.00"), 70),
            new Course(CourseCategory.BUSINESS, "Digital Marketing", new BigDecimal("800.20"), 60),
            new Course(CourseCategory.BUSINESS, "Financial Analysis and Modeling", new BigDecimal("1100.75"), 90),
            new Course(CourseCategory.BUSINESS, "Entrepreneurship Essentials", new BigDecimal("700.50"), 40),
            new Course(CourseCategory.BUSINESS, "Leadership and Management", new BigDecimal("950.00"), 80),
            new Course(CourseCategory.BUSINESS, "Business Analytics", new BigDecimal("900.60"), 65),
            new Course(CourseCategory.BUSINESS, "Supply Chain Management", new BigDecimal("850.25"), 55),
            new Course(CourseCategory.BUSINESS, "Human Resource Management", new BigDecimal("750.15"), 50),
            new Course(CourseCategory.BUSINESS, "Sales and Negotiation Skills", new BigDecimal("650.45"), 45),
            new Course(CourseCategory.BUSINESS, "Corporate Finance", new BigDecimal("1200.00"), 100),

            // ARTS courses
            new Course(CourseCategory.ARTS, "Introduction to Graphic Design", new BigDecimal("550.00"), 45),
            new Course(CourseCategory.ARTS, "Photography Masterclass", new BigDecimal("650.75"), 50),
            new Course(CourseCategory.ARTS, "Creative Writing Workshop", new BigDecimal("500.00"), 40),
            new Course(CourseCategory.ARTS, "Digital Illustration", new BigDecimal("750.50"), 60),
            new Course(CourseCategory.ARTS, "Music Production Basics", new BigDecimal("850.25"), 70),
            new Course(CourseCategory.ARTS, "Art History", new BigDecimal("600.30"), 55),
            new Course(CourseCategory.ARTS, "Fashion Design", new BigDecimal("700.00"), 50),
            new Course(CourseCategory.ARTS, "Film Making Essentials", new BigDecimal("950.40"), 80),
            new Course(CourseCategory.ARTS, "Acting Techniques", new BigDecimal("800.50"), 65),
            new Course(CourseCategory.ARTS, "Interior Design", new BigDecimal("900.75"), 75),

            // SCIENCE courses
            new Course(CourseCategory.SCIENCE, "Introduction to Quantum Mechanics", new BigDecimal("1200.00"), 90),
            new Course(CourseCategory.SCIENCE, "Astrophysics for Beginners", new BigDecimal("1000.00"), 80),
            new Course(CourseCategory.SCIENCE, "Biochemistry Fundamentals", new BigDecimal("950.00"), 70),
            new Course(CourseCategory.SCIENCE, "Environmental Science", new BigDecimal("800.00"), 60),
            new Course(CourseCategory.SCIENCE, "Genetics and Evolution", new BigDecimal("1100.00"), 85),
            new Course(CourseCategory.SCIENCE, "Organic Chemistry", new BigDecimal("900.50"), 75),
            new Course(CourseCategory.SCIENCE, "Physics: Mechanics", new BigDecimal("850.75"), 65),
            new Course(CourseCategory.SCIENCE, "Astronomy 101", new BigDecimal("950.25"), 70),
            new Course(CourseCategory.SCIENCE, "Microbiology Basics", new BigDecimal("700.45"), 55),
            new Course(CourseCategory.SCIENCE, "Earth Sciences", new BigDecimal("750.60"), 60));

    public static List<Course> get() {
        return new ArrayList<>(COURSES);
    }
}

package functionalinterface.models;

import java.math.BigDecimal;
import java.util.Locale;
import java.util.Objects;

public class Course {

    private final CourseCategory category;
    private final String title;
    private final BigDecimal price;
    private final Integer durationInHours;

    public Course(CourseCategory category, String title, BigDecimal price, Integer durationInHours ) {
        this.category = category;
        this.title = title;
        this.price = price;
        this.durationInHours = durationInHours;
    }

    public CourseCategory getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getDurationInHours() {
        return durationInHours;
    }

    @Override
    public String toString() {
        return String.format(Locale.getDefault(), "%-10s %-40s %-3d hours $ %6.2f", category, title, durationInHours, price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return category == course.category && title.equals(course.title) && price.equals(course.price) && durationInHours.equals(course.durationInHours);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category, title, price, durationInHours);
    }

    public Course create(CourseCategory technology, String string, BigDecimal bigDecimal, int i) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }
}

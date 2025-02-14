
import annotations.Author;
import annotations.Authors;
import annotations.Environment;
import annotations.Version;
import models.Book;
import models.Developer;
import models.PersonWithLombok;

public class AnnotationsApp {
    @SuppressWarnings("deprecation") // removes warning for calling 'person.getDetailsOld()'
    public static void main(String[] args) throws Exception {
        printSeparator("build-in annotations");
        // override example
        Developer person = new Developer(1L, "John");
        System.out.println(person.getDetails());
        System.out.println(person.greet());

        // depricate example
        System.out.println(person.getDetailsOld());

        // custom annotations
        printSeparator("custom annotations");
        // will return 0, if retention policy of Annotation used is NOT set to
        // "@Retention(RetentionPolicy.RUNTIME)"
        var annotations = person.getClass().getAnnotations();
        System.out.println("class contains " + annotations.length + " annotations");

        // repeatable annotations
        printSeparator("repeatable annotations");
        Book book = new Book("My Book");

        var bookAnnotations = book.getClass().getAnnotations();
        System.out.println("Book class contains " + bookAnnotations.length + " annotations");

        // read the annotation details
        for (var annotation : bookAnnotations) {
            if (annotation instanceof Author) {
                System.out.println("Found Author Annotation on type.");
                var author = (Author) annotation;
                System.out.printf("Author - %s -%n", author.value());
            } else if (annotation instanceof Version) {
                System.out.println("Found Version Annotation on type.");
                var version = (Version) annotation;
                System.out.printf("Version #%d%n", version.value());
                System.out.printf("Version Author - %s -%n", version.author());
            } else if (annotation instanceof Authors) {
                System.out.println("Found Authors (many) Annotation on type.");
                var authors = (Authors) annotation;
                for (var author : authors.value()) {
                    System.out.printf("Author (one of many) - %s -%n", author.value());
                }
            } else if (annotation instanceof Environment) {
                System.out.println("Found Environment Annotation on type.");
                var env = (Environment) annotation;
                System.out.printf("Environment - %s -%n", env.value());
            }

        }

        // check for inherited annotation

        var envAnnotation = person.getClass().getAnnotation(Environment.class);
        String msg = envAnnotation == null ? "Does NOT have Env annotation": "Has Env annotation";
        System.out.println("Developer class " + msg);

        // test lombok toString annotation
        printSeparator("Lombok");
        System.out.println(person); //without lombok
        var personWithLombok = new PersonWithLombok(2L, "Susy");
        System.out.println(personWithLombok);
    }

    private static void printSeparator(String title) {
        System.out.println("----------------------------------");
        System.out.println(title);
        System.out.println("----------------------------------");
    }
}

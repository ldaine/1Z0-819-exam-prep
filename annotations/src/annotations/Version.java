package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.CONSTRUCTOR, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME) // default value if not set is RetentionPolicy.CLASS
public @interface Version {
    int value() default 1;

    String author();

    String license() default "MIT";

    String[] environment() default { "prod" };
}

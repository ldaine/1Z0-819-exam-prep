package annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// annotation on annotation must be same as in referenced annotation
@Retention(RetentionPolicy.RUNTIME)
public @interface Authors {
    Author[] value();
}

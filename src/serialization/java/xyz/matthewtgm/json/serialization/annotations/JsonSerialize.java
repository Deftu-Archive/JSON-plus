package xyz.matthewtgm.json.serialization.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonSerialize {
    /**
     * @return The directory and file name where the file is serialized to.
     */
    String value();

    /**
     * @return Whether or not the serialized JSON is formatted with indents.
     */
    boolean pretty() default true;
}
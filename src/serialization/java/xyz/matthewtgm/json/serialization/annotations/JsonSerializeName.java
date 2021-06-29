package xyz.matthewtgm.json.serialization.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonSerializeName {
    /**
     * @return The serialized name.
     */
    String value();
}
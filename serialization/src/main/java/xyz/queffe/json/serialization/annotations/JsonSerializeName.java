package xyz.queffe.json.serialization.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The name of a serialized field.
 * @author MatthewTGM
 * @since 2.0
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonSerializeName {
    /**
     * @return The serialized name.
     */
    String value();
}
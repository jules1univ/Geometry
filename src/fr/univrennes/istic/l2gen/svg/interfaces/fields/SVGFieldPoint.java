package fr.univrennes.istic.l2gen.svg.interfaces.fields;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.FIELD })
public @interface SVGFieldPoint {
    String x() default "x";

    String y() default "y";
}

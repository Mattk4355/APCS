package com.Mattk4355.Utils.Locks;

import java.lang.annotation.*;

/**
 * Indicates that the annotated interface is a "marker" interface, that will enable some functionality.
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MarkerInterface {
    String[] value() default {};
}
package com.Mattk4355.Utils.Locks;

import java.lang.annotation.*;

/**
 * Indicates the class(es) that the marked interface is to be used with.
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ForUseWith {
    Class<?>[] value() default {};
}

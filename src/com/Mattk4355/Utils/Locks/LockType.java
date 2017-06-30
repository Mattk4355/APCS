package com.Mattk4355.Utils.Locks;

import java.lang.annotation.*;

@Documented
@MarkerInterface
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@ForUseWith({Lock.class, Lockable.class})
public @interface LockType {
    Type value();
}

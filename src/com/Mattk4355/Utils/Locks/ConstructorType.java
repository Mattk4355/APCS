package com.Mattk4355.Utils.Locks;

import java.lang.annotation.*;

@Documented
@Target(ElementType.CONSTRUCTOR)
@Retention(RetentionPolicy.RUNTIME)
@interface ConstructorType {
    Type value();
}

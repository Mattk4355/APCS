package com.Mattk4355.Utils.Locks;

@ForUseWith({Lock.class, ConstructorType.class})
public enum Type {
    /** A lock bound to a class type */
    CLASS,

    /** A lock bound to an object */
    OBJECT
}

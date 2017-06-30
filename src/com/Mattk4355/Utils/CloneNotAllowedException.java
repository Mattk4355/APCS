package com.Mattk4355.Utils;

/**
 * Thrown to indicate that a given Object's canClone() method returned false
 */
@SuppressWarnings("WeakerAccess")
public class CloneNotAllowedException extends CloneNotSupportedException {
    /**
     * Constructs a new {@code CloneNotAllowedException}
     */
    public CloneNotAllowedException(){
        super();
    }
    /**
     * Constructs a new {@code CloneNotAllowedException} with the specified detail message
     * @param s the message to include
     */
    public CloneNotAllowedException(String s){
        super(s);
    }

    /**
     * Constructs a new {@code CloneNotAllowedException}
     * @param obj the object for which the canClone() method returned false
     * @return a new {@code CloneNotAllowedException}
     */
    public static CloneNotAllowedException forObj(CanClone<?> obj){
        Utils.Objects.requireNonNull(obj);
        return new CloneNotAllowedException("canClone() method of " + obj.getClass().getName() + " returned false.");
    }

    /**
     * Fast path for package methods. circumvents nullity check
     *
     * Constructs a new {@code CloneNotAllowedException}
     * @param obj the object for which the canClone() method returned false
     * @return a new {@code CloneNotAllowedException}
     */
    static CloneNotAllowedException forObj0(CanClone<?> obj){
        return new CloneNotAllowedException("canClone() method of " + obj.getClass().getName() + " returned false.");
    }
}

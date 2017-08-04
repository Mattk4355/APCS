package com.Mattk4355.Utils;

/**
 * An interface to override the default (protected) clone() method in {@linkplain Object}.
 * This interface allows for one generic clone method.
 * @see Utils.Objects#clone(CanClone)
 */

public interface CanClone<T> extends Cloneable{
    /**
     * @return a clone of the given object.
     *
     * @throws CloneNotAllowedException if the canClone() method returns {@code false}
     */
    T clone() throws CloneNotAllowedException;

    default boolean canClone(){
        return true;
    }
}

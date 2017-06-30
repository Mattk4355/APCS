package com.Mattk4355.Utils.Locks;

@MarkerInterface
public interface LockObject {
    /**
     * Returns true if the object is bound to a specific class, false otherwise.
     *
     * @return true if the object is bound to a specific class, false otherwise.
     */
    boolean isClassLocked();

    /**
     * Returns true if the object is bound to a specific object, false otherwise.
     *
     * @return true if the object is bound to a specific object, false otherwise.
     */
    boolean isObjectLocked();

    /**
     * @return a String representation of this <code> Lock </code>.
     */
    String toString();

    /**
     * Returns a new LockObject with the current class/Object lock.
     * The default implementation throws {@linkplain UnsupportedOperationException}
     *
     * @return a new LockObject with the current class/Object lock.
     * @throws UnsupportedOperationException if this {@code LockObject} does not
     *         support the copying of data
     */
    default LockObject copyData(){
        throw new UnsupportedOperationException("copyData");
    }
}

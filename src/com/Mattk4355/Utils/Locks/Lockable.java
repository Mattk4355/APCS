package com.Mattk4355.Utils.Locks;

/**
 * Interface used to "mark" an object as being able to use
 * {@linkplain Lock} objects.
 *
 * A class implementing this interface can use {@linkplain Lock}
 * objects for synchronization.
 *
 * It is <p> highly </p> recommended that objects also add the {@linkplain LockType}
 * annotation to the created lock objects.
 */
@MarkerInterface
@ForUseWith({Lock.class, LockObject.class})
public interface Lockable {}
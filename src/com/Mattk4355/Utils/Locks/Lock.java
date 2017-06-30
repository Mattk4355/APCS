package com.Mattk4355.Utils.Locks;

import com.Mattk4355.Utils.Utils.Objects;

/**
 * A "lock" object that can be used in the synchronized block. Ex.
 * private final Lock l = new Lock(this);
 *
 * public void someMethod(){
 *     synchronized(l){
 *         **code here**
 *     }
 * }
 */
@SuppressWarnings("WeakerAccess")
public final class Lock implements LockObject{
    private final Lockable objLock;
    private final Class<? extends Lockable> classLock;

    @ConstructorType(Type.OBJECT)
    public Lock(Lockable l){
        this.objLock = Objects.requireNonNull(l, "Object cannot be null");
        this.classLock = null;
    }
    @ConstructorType(Type.CLASS)
    public Lock(Class<? extends Lockable> cls){
        this.objLock = null;
        this.classLock = Objects.requireNonNull(cls, "Class cannot be null");
    }

    public boolean isClassLocked(){
        return classLock != null;
    }
    public boolean isObjectLocked(){
        return objLock != null;
    }

    @Override
    public String toString() {
        if (this.isClassLocked()) return this.getClass().getName() + ": Class lock: " + classLock.toString();
        else if (this.isObjectLocked()) return this.getClass().getName() + ": Object lock: " + objLock.toString();
        else throw new MalformedObjectException("Malformed Lock object"); //This should never happen.
    }
    public Lock copyData(){
        return copyData(this);
    }
    public static Lock copyData(Lock old){
        Objects.requireNonNull(old, "Old lock cannot be null");
        if (old.isClassLocked()) return new Lock(old.classLock);
        else if (old.isObjectLocked()) return new Lock(old.objLock);
        else throw new MalformedObjectException("Malformed Lock object"); //This should never happen.
    }
}
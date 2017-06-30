package com.Mattk4355.Utils.Locks;

/**
 * Thrown to indicate that an Object is in an illegal state.
 */
@SuppressWarnings("WeakerAccess")
public class IllegalObjectStateException extends RuntimeException{
    public IllegalObjectStateException(){
        super();
    }
    public IllegalObjectStateException(String message){
        super(message);
    }
    public IllegalObjectStateException(Throwable cause){
        super(cause);
    }
    public IllegalObjectStateException(String message, Throwable cause){
        super(message, cause);
    }
}

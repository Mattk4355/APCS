package com.Mattk4355.Utils.Locks;

/**
 * This exception is thrown to indicate that a(N)
 * Object is not in a state which it is supposed
 * to be in.
 */
public class MalformedObjectException extends IllegalObjectStateException{
    public MalformedObjectException(){
        super();
    }
    public MalformedObjectException(String message){
        super(message);
    }
    public MalformedObjectException(Throwable cause){
        super(cause);
    }
    public MalformedObjectException(String message, Throwable cause){
        super(message, cause);
    }
}

package com.Mattk4355.Utils;

import java.io.PrintStream;

/**
 * A class that represents a "timer". The timer functions just like a timer in real life, with the ability to be started,
 * stopped, and reset. This class contains several methods to allow the time of the timer to be printed to any given
 * PrintStream, as well as methods to print to System.out. The implementation of this method is designed to be thread-safe.
 *
 *
 * There are several considerations that one should keep in mind:
 *    -There are several methods that allow the state of the timer to be checked. It is recommended that these methods be
 *     used to check the timer state before any of the operations. The following would be a typical usage of the Timer
 *     class (if the class were to potentially be used by multiple threads): {@code
 *        Timer t = new Timer();
 *        if (t.isNotStarted()) t.start();
 *        //... whatever code here
 *        if (t.isRunning()){
 *            t.stop();
 *            t.printTime("Time taken: ");
 *        }
 *     }
 *    -Methods throw {@linkplain IllegalStateException}s if the timer is not in a valid state.
 *
 * @author Matthew Krawczyk
 * @version 1.1
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public final class Timer implements CanClone<Timer>{
    /** Number of milliseconds in a second */
    private static final int MSinS = 1000;

    /** The start time of this {@code Timer} (-1 if not set yet.) */
    private long start;
    /** The stop time of this {@code Timer} (-1 if not set yet). */
    private long stop;
    /** The state of this {@code Timer} (starts in State.NOT_STARTED). */
    private State state;

    /** The object on which instance methods will lock */
    private final Lock LOCK;

    private static final class Lock{}

    /**
     * Creates a new {@code Timer} object.
     */
    public Timer(){
        LOCK = new Lock();
        this.start = -1L;
        this.stop = -1L;
        this.state = State.NOT_STARTED;
    }

    /**
     * Starts this {@code Timer}.
     *
     * @return a reference to this {@code Timer}.
     *
     * @throws IllegalStateException if the timer has already
     *         been stopped or is currently running.
     */
    public final Timer start(){
        synchronized (LOCK){
            if (state == State.RUNNING) throw new IllegalStateException("Timer already running");
            if (state == State.STOPPED) throw new IllegalStateException("Timer already stopped");
            start = System.nanoTime();
            state = State.RUNNING;
            return this;
        }
    }

    /**
     * Stops this {@code Timer}.
     *
     * @return a reference to this {@code Timer}.
     *
     * @throws IllegalStateException if the timer has not
     *         been started.
     */
    public final Timer stop(){
        synchronized (LOCK){
            if (state != State.RUNNING) throw new IllegalStateException("Timer not started");
            stop = System.nanoTime();
            state = State.STOPPED;
            return this;
        }
    }

    /**
     * Resets this {@code Timer}. After this method returns the
     * timer can be restarted by calling the {@code start()} method
     *
     * @return a reference to this {@code Timer}.
     */
    public final Timer reset(){
        synchronized (LOCK){
            this.start = -1L;
            this.stop = -1L;
            this.state = State.NOT_STARTED;
            return this;
        }
    }

    /**
     * @return true iff this {@code Timer} is running
     */
    public final boolean isRunning(){
        return state == State.RUNNING;
    }

    /**
     * @return true iff this {@code Timer} is not stopped
     */
    public final boolean isStopped(){
        return state == State.STOPPED;
    }

    /**
     * @return true iff this {@code Timer} is not running
     */
    public final boolean isNotStarted(){
         return state == State.NOT_STARTED;
    }

    /**
     * @return the time of this {@code Timer}.
     *
     * @throws IllegalStateException if the timer has not yet
     *         been started or stopped.
     * @throws ArithmeticException if a long overflow occurred
     *         (should never conceivably happen)
     */
    public final long getTime(){
        synchronized (LOCK){
            if (state == State.NOT_STARTED) throw new IllegalStateException("Timer not started");
            if (state == State.RUNNING) throw new IllegalStateException("Timer not stopped");
            long time = this.stop - this.start;
            if (time < 0) throw new ArithmeticException("long overflow");
            return time;
        }
    }

    /**
     * Prints the timer's time to System.out.
     *
     * @return a reference to this {@code Timer}.
     *
     * @throws IllegalStateException if the timer has not yet
     *         been started or stopped.
     */
    public final Timer printTime(){
        return printTime(System.out);
    }

    /**
     * Prints the timer's time to System.out, prefaced with
     * {@code before}.
     *
     * @return a reference to this {@code Timer}.
     *
     * @throws IllegalStateException if the timer has not yet
     *         been started or stopped.
     */
    public final Timer printTime(String before){
        return printTime(System.out, before);
    }

    /**
     * Prints the timer's time to System.out, prefaced with
     * {@code before}, and postfaced with {@code after}.
     *
     * @return a reference to this {@code Timer}.
     *
     * @throws IllegalStateException if the timer has not yet
     *         been started or stopped.
     */
    public final Timer printTime(String before, String after){
        return printTime(System.out, before, after);
    }

    /**
     * Prints the timer's time to {@code out}.
     *
     * @return a reference to this {@code Timer}.
     *
     * @throws NullPointerException if {@code out} is null.
     * @throws IllegalStateException if the timer has not yet
     *         been started or stopped.
     */
    public final Timer printTime(PrintStream out){
        Utils.Objects.requireNonNull(out, "Output stream cannot be null");
        long time = this.getTime();
        out.println("Timer: " + time + "ms");
        return this;
    }

    /**
     * Prints the timer's time to {@code out}, prefaced with
     * {@code before}.
     *
     * @return a reference to this {@code Timer}.
     *
     * @throws NullPointerException if {@code out} is null.
     * @throws IllegalStateException if the timer has not yet
     *         been started or stopped.
     */
    public final Timer printTime(PrintStream out, String before){
        Utils.Objects.requireNonNull(out, "Output stream cannot be null");
        long time = this.getTime();
        out.println(before + time + "ms");
        return this;
    }

    /**
     * Prints the timer's time to {@code out}, prefaced with
     * {@code before}, and postfaced with {@code after}.
     *
     * @return a reference to this {@code Timer}.
     *
     * @throws NullPointerException if {@code out} is null.
     * @throws IllegalStateException if the timer has not yet
     *         been started or stopped.
     */
    public final Timer printTime(PrintStream out, String before, String after){
        Utils.Objects.requireNonNull(out, "Output stream cannot be null");
        long time = this.getTime();
        out.println(before + time + "ms" + after);
        return this;
    }

    /**
     * Prints the timer's time to System.out.
     *
     * @return a reference to this {@code Timer}.
     *
     * @throws IllegalStateException if the timer has not yet
     *         been started or stopped.
     */
    public Timer printTimeSec(){
        return printTimeSec(System.out);
    }

    /**
     * Prints the timer's time to System.out, prefaced with
     * {@code before}.
     *
     * @return a reference to this {@code Timer}.
     *
     * @throws IllegalStateException if the timer has not yet
     *         been started or stopped.
     */
    public final Timer printTimeSec(String before){
        return printTimeSec(System.out, before);
    }

    /**
     * Prints the timer's time to System.out, prefaced with
     * {@code before}, and postfaced with {@code after}.
     *
     * @return a reference to this {@code Timer}.
     *
     * @throws IllegalStateException if the timer has not yet
     *         been started or stopped.
     */
    public final Timer printTimeSec(String before, String after){
        return printTimeSec(System.out, before, after);
    }

    /**
     * Prints the timer's time to {@code out}.
     *
     * @return a reference to this {@code Timer}.
     *
     * @throws NullPointerException if {@code out} is null.
     * @throws IllegalStateException if the timer has not yet
     *         been started or stopped.
     */
    public Timer printTimeSec(PrintStream out){
        Utils.Objects.requireNonNull(out, "Output stream cannot be null");
        long time = this.getTime();
        long sec = time / MSinS;
        long extraMS = time % MSinS;
        out.println("Timer: " + sec + "s" + extraMS + "ms");
        return this;
    }

    /**
     * Prints the timer's time to {@code out}, prefaced with
     * {@code before}.
     *
     * @return a reference to this {@code Timer}.
     *
     * @throws NullPointerException if {@code out} is null.
     * @throws IllegalStateException if the timer has not yet
     *         been started or stopped.
     */
    public final Timer printTimeSec(PrintStream out, String before){
        Utils.Objects.requireNonNull(out, "Output stream cannot be null");
        long time = this.getTime();
        long sec = time / MSinS;
        long extraMS = time % MSinS;
        out.println(before + sec + "s" + extraMS + "ms");
        return this;
    }

    /**
     * Prints the timer's time to {@code out}, prefaced with
     * {@code before}, and postfaced with {@code after}.
     *
     * @return a reference to this {@code Timer}.
     *
     * @throws NullPointerException if {@code out} is null.
     * @throws IllegalStateException if the timer has not yet
     *         been started or stopped.
     */
    public final Timer printTimeSec(PrintStream out, String before, String after){
        Utils.Objects.requireNonNull(out, "Output stream cannot be null");
        long time = this.getTime();
        long sec = time / MSinS;
        long extraMS = time % MSinS;
        out.println(before + sec + "s" + extraMS + "ms" + after);
        return this;
    }

    /**
     * @return a String representation of this {@code Timer}
     */
    @Override
    public final String toString() {
        synchronized (LOCK){
            String zuper = super.toString();
            String times, state;
            switch (this.state){
                case RUNNING:
                    state = "State: Running";
                    times = "Start: " + this.start + "ms Stop: N/A";
                    break;
                case STOPPED:
                    state = "State: Stopped";
                    times = "Start: " + this.start + "ms Stop: " + this.stop + "ms" + " Total time: " + this.getTime() + "ms";
                    break;
                case NOT_STARTED:
                    state = "State: Not started";
                    times = "Start: N/A Stop: N/A";
                    break;
                default:
                    throw new IllegalStateException();
            }
            return zuper + ": " + state + " Times: " + times;
        }

    }

    @Override
    public final Timer clone(){
        try{
            Timer t = (Timer) super.clone();
            t.start = this.start;
            t.stop = this.stop;
            t.state = this.state;
            return t;
        }
        catch (CloneNotSupportedException x){
            throw new InternalError(x);
        }
    }


    private enum State{
        NOT_STARTED,
        RUNNING,
        STOPPED
    }
}

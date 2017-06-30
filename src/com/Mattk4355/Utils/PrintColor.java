package com.Mattk4355.Utils;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({"WeakerAccess", "unused"})
public final class PrintColor {
    private PrintColor(){
        throw new InternalError("No instancing this class!");
    }
    private static final HashMap<String, String> colorMap;
    public static final String DEFAULT = "DEFAULT";
    public static final String BLACK = "BLACK";
    public static final String RED = "RED";
    public static final String GREEN = "GREEN";
    public static final String YELLOW = "YELLOW";
    public static final String BLUE = "BLUE";
    public static final String PURPLE = "PURPLE";
    public static final String CYAN = "CYAN";
    public static final String WHITE =  "WHITE";
    static {
        colorMap = new HashMap<>();
        colorMap.put(DEFAULT, "\u001B[0m");
        colorMap.put(BLACK, "\u001B[30m");
        colorMap.put(RED, "\u001B[31m");
        colorMap.put(GREEN, "\u001B[32m");
        colorMap.put(YELLOW, "\u001B[33m");
        colorMap.put(BLUE, "\u001B[34m");
        colorMap.put(PURPLE, "\u001B[35m");
        colorMap.put(CYAN, "\u001B[36m");
        colorMap.put(WHITE, "\u001B[37m");
        RESET = colorMap.get("DEFAULT");
    }
    //RESET color is also default color
    private static final String RESET;

    /**
     * The "standard" output stream. This stream is already
     * open and ready to accept output data. Typically this stream
     * corresponds to display output or another output destination
     * specified by the host environment or user.
     *
     * No line termination:
     * @see PrintColor#print(int, String)
     * @see PrintColor#print(double, String)
     * @see PrintColor#print(float, String)
     * @see PrintColor#print(byte, String)
     * @see PrintColor#print(char, String)
     * @see PrintColor#print(short, String)
     * @see PrintColor#print(long, String)
     * @see PrintColor#print(boolean, String)
     * @see PrintColor#print(String, String)
     * @see PrintColor#print(Object, String)
     *
     * Line termination:
     * @see PrintColor#println(int, String)
     * @see PrintColor#println(double, String)
     * @see PrintColor#println(float, String)
     * @see PrintColor#println(byte, String)
     * @see PrintColor#println(char, String)
     * @see PrintColor#println(short, String)
     * @see PrintColor#println(long, String)
     * @see PrintColor#println(boolean, String)
     * @see PrintColor#println(String, String)
     * @see PrintColor#println(Object, String)
     */
    private static java.io.PrintStream out = null;

    static {
        out = System.out;
        checkIO();
    }

    /**
     * Prints the specified value with the specified color. Does not terminate the line.
     *
     * @param i the value to be printed.
     * @param color the color with which the value is to be printed.
     *
     * @throws IllegalArgumentException if the color String is invalid.
     */
    public synchronized static void print(int i, String color){
        checkIO();
        out.print(getAnsiColor(color));
        out.print(i);
        out.print(RESET);
        out.flush();
    }

    /**
     * Prints the specified value with the specified color, and terminates the line.
     *
     * @param i the value to be printed.
     * @param color the color with which the value is to be printed.
     *
     * @throws IllegalArgumentException if the color String is invalid.
     */
    public synchronized static void println(int i, String color){
        checkIO();
        out.print(getAnsiColor(color));
        out.print(i);
        out.println(RESET);
        out.flush();
    }

    /**
     * Prints the specified value with the specified color. Does not terminate the line.
     *
     * @param d the value to be printed.
     * @param color the color with which the value is to be printed.
     *
     * @throws IllegalArgumentException if the color String is invalid.
     */
    public synchronized static void print(double d, String color){
        checkIO();
        out.print(getAnsiColor(color));
        out.print(d);
        out.print(RESET);
        out.flush();
    }

    /**
     * Prints the specified value with the specified color, and terminates the line.
     *
     * @param d the value to be printed.
     * @param color the color with which the value is to be printed.
     *
     * @throws IllegalArgumentException if the color String is invalid.
     */
    public synchronized static void println(double d, String color){
        checkIO();
        out.print(getAnsiColor(color));
        out.print(d);
        out.println(RESET);
        out.flush();
    }

    /**
     * Prints the specified value with the specified color. Does not terminate the line.
     *
     * @param f the value to be printed.
     * @param color the color with which the value is to be printed.
     *
     * @throws IllegalArgumentException if the color String is invalid.
     */
    public synchronized static void print(float f, String color){
        checkIO();
        out.print(getAnsiColor(color));
        out.print(f);
        out.print(RESET);
        out.flush();
    }

    /**
     * Prints the specified value with the specified color, and terminates the line.
     *
     * @param f the value to be printed.
     * @param color the color with which the value is to be printed.
     *
     * @throws IllegalArgumentException if the color String is invalid.
     */
    public synchronized static void println(float f, String color){
        checkIO();
        out.print(getAnsiColor(color));
        out.print(f);
        out.println(RESET);
        out.flush();
    }

    /**
     * Prints the specified value with the specified color. Does not terminate the line.
     *
     * @param b the value to be printed.
     * @param color the color with which the value is to be printed.
     *
     * @throws IllegalArgumentException if the color String is invalid.
     */
    public synchronized static void print(byte b, String color){
        checkIO();
        out.print(getAnsiColor(color));
        out.print(b);
        out.print(RESET);
        out.flush();
    }

    /**
     * Prints the specified value with the specified color, and terminates the line.
     *
     * @param b the value to be printed.
     * @param color the color with which the value is to be printed.
     *
     * @throws IllegalArgumentException if the color String is invalid.
     */
    public synchronized static void println(byte b, String color){
        checkIO();
        out.print(getAnsiColor(color));
        out.print(b);
        out.println(RESET);
        out.flush();
    }

    /**
     * Prints the specified value with the specified color. Does not terminate the line.
     *
     * @param c the value to be printed.
     * @param color the color with which the value is to be printed.
     *
     * @throws IllegalArgumentException if the color String is invalid.
     */
    public synchronized static void print(char c, String color){
        checkIO();
        out.print(getAnsiColor(color));
        out.print(c);
        out.print(RESET);
        out.flush();
    }

    /**
     * Prints the specified value with the specified color, and terminates the line.
     *
     * @param c the value to be printed.
     * @param color the color with which the value is to be printed.
     *
     * @throws IllegalArgumentException if the color String is invalid.
     */
    public synchronized static void println(char c, String color){
        checkIO();
        out.print(getAnsiColor(color));
        out.print(c);
        out.println(RESET);
        out.flush();
    }

    /**
     * Prints the specified value with the specified color. Does not terminate the line.
     *
     * @param s the value to be printed.
     * @param color the color with which the value is to be printed.
     *
     * @throws IllegalArgumentException if the color String is invalid.
     */
    public synchronized static void print(short s, String color){
        checkIO();
        out.print(getAnsiColor(color));
        out.print(s);
        out.print(RESET);
        out.flush();
    }

    /**
     * Prints the specified value with the specified color, and terminates the line.
     *
     * @param s the value to be printed.
     * @param color the color with which the value is to be printed.
     *
     * @throws IllegalArgumentException if the color String is invalid.
     */
    public synchronized static void println(short s, String color){
        checkIO();
        out.print(getAnsiColor(color));
        out.print(s);
        out.println(RESET);
        out.flush();
    }

    /**
     * Prints the specified value with the specified color. Does not terminate the line.
     *
     * @param l the value to be printed.
     * @param color the color with which the value is to be printed.
     *
     * @throws IllegalArgumentException if the color String is invalid.
     */
    public synchronized static void print(long l, String color){
        checkIO();
        out.print(getAnsiColor(color));
        out.print(l);
        out.print(RESET);
        out.flush();
    }

    /**
     * Prints the specified value with the specified color, and terminates the line.
     *
     * @param l the value to be printed.
     * @param color the color with which the value is to be printed.
     *
     * @throws IllegalArgumentException if the color String is invalid.
     */
    public synchronized static void println(long l, String color){
        checkIO();
        out.print(getAnsiColor(color));
        out.print(l);
        out.println(RESET);
        out.flush();
    }

    /**
     * Prints the specified value with the specified color. Does not terminate the line.
     *
     * @param b the value to be printed.
     * @param color the color with which the value is to be printed.
     *
     * @throws IllegalArgumentException if the color String is invalid.
     */
    public synchronized static void print(boolean b, String color){
        checkIO();
        out.print(getAnsiColor(color));
        out.print(b);
        out.print(RESET);
        out.flush();
    }

    /**
     * Prints the specified value with the specified color, and terminates the line.
     *
     * @param b the value to be printed.
     * @param color the color with which the value is to be printed.
     *
     * @throws IllegalArgumentException if the color String is invalid.
     */
    public synchronized static void println(boolean b, String color){
        checkIO();
        out.print(getAnsiColor(color));
        out.print(b);
        out.println(RESET);
        out.flush();
    }

    /**
     * Prints the specified value with the specified color. Does not terminate the line.
     *
     * @param s the value to be printed.
     * @param color the color with which the value is to be printed.
     *
     * @throws IllegalArgumentException if the color String is invalid.
     */
    public synchronized static void print(String s, String color){
        checkIO();
        s = (s == null) ? "null" : s;
        out.print(getAnsiColor(color));
        out.print(s);
        out.print(RESET);
        out.flush();
    }

    /**
     * Prints the specified value with the specified color, and terminates the line.
     *
     * @param s the value to be printed.
     * @param color the color with which the value is to be printed.
     *
     * @throws IllegalArgumentException if the color String is invalid.
     */
    public synchronized static void println(String s, String color){
        checkIO();
        s = (s == null) ? "null" : s;
        out.print(getAnsiColor(color));
        out.print(s);
        out.println(RESET);
        out.flush();
    }

    /**
     * Prints the specified value with the specified color. Does not terminate the line.
     *
     * @param o the value to be printed.
     * @param color the color with which the value is to be printed.
     *
     * @throws IllegalArgumentException if the color String is invalid.
     */
    public synchronized static void print(Object o, String color){
        checkIO();
        String s = (o == null) ? "null" : o.toString();
        print(s, color);
    }

    /**
     * Prints the specified value with the specified color, and terminates the line.
     *
     * @param o the value to be printed.
     * @param color the color with which the value is to be printed.
     *
     * @throws IllegalArgumentException if the color String is invalid.
     */
    public synchronized static void println(Object o, String color){
        checkIO();
        String s = (o == null) ? "null" : o.toString();
        println(s, color);
    }

    /**
     * Reassigns the "standard" output stream to the System output stream. If the output
     * stream is already the System output stream, this method does nothing.
     */
    public static void setSystemOut(){
        if (!(out == System.out)) setOut(System.out);
    }

    /**
     * Reassigns the "standard" output stream.
     *
     * <p>First, if there is a security manager, its <code>checkPermission</code>
     * method is called with a <code>RuntimePermission("setIO")</code> permission
     *  to see if it's ok to reassign the "standard" output stream.
     *
     * @param Out the new standard output stream
     *
     * @throws SecurityException
     *        if a security manager exists and its
     *        <code>checkPermission</code> method doesn't allow
     *        reassigning of the standard output stream.
     *
     * @see SecurityManager#checkPermission
     * @see java.lang.RuntimePermission
     */
    public static void setOut(java.io.PrintStream Out){
        if (Out == null) throw new NullPointerException("Output stream cannot be null");
        final SecurityManager sm = System.getSecurityManager();
        if (sm != null) sm.checkPermission(new RuntimePermission("setIO"));
        out = Out;
    }

    /**
     * Checks to see if the output stream is null.
     *
     * @throws NullPointerException if the output stream is null.
     */
    public static void checkIO(){
        if (out == null) throw new NullPointerException("Output stream is null");
    }

    /**
     *
     * @param color the String for which to return an ANSI code
     * @return the ANSI code of the given <code> color </code> String
     *     @see PrintColor#colorMap
     *     @see Map#keySet()
     *
     * @throws IllegalArgumentException if the <code> color </code> String is invalid.
     * @throws NullPointerException if <code> color </code> is null.
     *     @see PrintColor#colorMap
     *     @see Map#values()
     */
    private synchronized static String getAnsiColor(String color){
        if (color == null) throw new NullPointerException("null color input");
        if (color.equals("")) throw new IllegalArgumentException(illegalColorMsg("*blank*"));
        String color0 = colorMap.get(color.toUpperCase());
        if (color0 != null)  return color0;
        throw new IllegalArgumentException(illegalColorMsg(color));
    }
    private static String illegalColorMsg(String color){
        return "Illegal color: " + color;
    }

    /**
     * Prints out the valid color values.
     *
     * @see PrintColor#colorMap
     * @see Map#keySet()
     */
    public static void printValidColors(){
        System.out.println("Valid color Strings (can be any case): " + Utils.Arrays.toString(colorMap.keySet()));
    }
}
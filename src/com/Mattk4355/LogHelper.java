//(C) Matthew Krawczyk
//LogHelper v1.1
package com.Mattk4355;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class LogHelper {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");

    /*
     * various debug messages
     * useful to print out values while testing
     */
    public void debug(String s){
        Calendar cal = Calendar.getInstance();
        System.out.println(ANSI_CYAN + sdf.format(cal.getTime()) + " [DEBUG]: " + s + ANSI_RESET);
    }
    public void debug(String var, boolean b){
        Calendar cal = Calendar.getInstance();
        System.out.println(ANSI_CYAN + sdf.format(cal.getTime()) + " [INFO]: boolean " + var + " is " + b + ANSI_RESET);
    }
    public void debug(String var, char c){
        Calendar cal = Calendar.getInstance();
        System.out.println(ANSI_CYAN + sdf.format(cal.getTime()) + " [INFO]: char " + var + " is " + c + ANSI_RESET);
    }
    public void debug(String var, double d){
        Calendar cal = Calendar.getInstance();
        System.out.println(ANSI_CYAN + sdf.format(cal.getTime()) + " [INFO]: double " + var + " is " + d + ANSI_RESET);
    }
    public void debug(String var, float f){
        Calendar cal = Calendar.getInstance();
        System.out.println(ANSI_CYAN + sdf.format(cal.getTime()) + " [INFO]: float " + var + " is " + f + ANSI_RESET);
    }
    public void debug(String var, int i){
        Calendar cal = Calendar.getInstance();
        System.out.println(ANSI_CYAN + sdf.format(cal.getTime()) + " [INFO]: int " + var + " is " + i + ANSI_RESET);
    }
    public void debug(String var, long l){
        Calendar cal = Calendar.getInstance();
        System.out.println(ANSI_CYAN + sdf.format(cal.getTime()) + " [INFO]: long " + var + " is " + l + ANSI_RESET);
    }

    /*
     * various information messages
     */
    public void info(String s){
        Calendar cal = Calendar.getInstance();
        System.out.println(ANSI_BLUE + sdf.format(cal.getTime()) + " [INFO]: " + s + ANSI_RESET);
    }
    public void info(String var, boolean b){
        Calendar cal = Calendar.getInstance();
        System.out.println(ANSI_BLUE + sdf.format(cal.getTime()) + " [INFO]: boolean " + var + " is " + b + ANSI_RESET);
    }
    public void info(String var, char c){
        Calendar cal = Calendar.getInstance();
        System.out.println(ANSI_BLUE + sdf.format(cal.getTime()) + " [INFO]: char " + var + " is " + c + ANSI_RESET);
    }
    public void info(String var, double d){
        Calendar cal = Calendar.getInstance();
        System.out.println(ANSI_BLUE + sdf.format(cal.getTime()) + " [INFO]: double " + var + " is " + d + ANSI_RESET);
    }
    public void info(String var, float f){
        Calendar cal = Calendar.getInstance();
        System.out.println(ANSI_BLUE + sdf.format(cal.getTime()) + " [INFO]: float " + var + " is " + f + ANSI_RESET);
    }
    public void info(String var, int i){
        Calendar cal = Calendar.getInstance();
        System.out.println(ANSI_BLUE + sdf.format(cal.getTime()) + " [INFO]: int " + var + " is " + i + ANSI_RESET);
    }
    public void info(String var, long l){
        Calendar cal = Calendar.getInstance();
        System.out.println(ANSI_BLUE + sdf.format(cal.getTime()) + " [INFO]: long " + var + " is " + l + ANSI_RESET);
    }

    /*
     * warning message
     */
    public void warning(String s){
        Calendar cal = Calendar.getInstance();
        System.out.println(ANSI_YELLOW + sdf.format(cal.getTime()) + " [WARNING]: " + s + ANSI_RESET);
    }

    /*
     * fatal message
     */
    public void fatal(String s){
        Calendar cal = Calendar.getInstance();
        System.out.println(ANSI_RED + sdf.format(cal.getTime()) + " [FATAL]: " + s + ANSI_RESET);
    }
}
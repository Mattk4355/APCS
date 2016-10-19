//(C) Matthew Krawczyk
package com.Mattk4355;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class LogHelper {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_CYAN = "\u001B[36m";
    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");

    public void debug(String s){
        Calendar cal = Calendar.getInstance();
        System.out.println(ANSI_CYAN + sdf.format(cal.getTime()) + " [DEBUG]: " + s + ANSI_RESET);

    }
    public void debug(String var, boolean b){
        Calendar cal = Calendar.getInstance();
        System.out.println(ANSI_CYAN + sdf.format(cal.getTime()) + " [INFO]: boolean " + var + " is " + b + ANSI_RESET);

    }
    public void info(String s){
        Calendar cal = Calendar.getInstance();
        System.out.println(ANSI_BLUE + sdf.format(cal.getTime()) + " [INFO]: " + s + ANSI_RESET);
    }
    public void info(String var, boolean b){
        Calendar cal = Calendar.getInstance();
        System.out.println(ANSI_BLUE + sdf.format(cal.getTime()) + " [INFO]: boolean " + var + " is " + b + ANSI_RESET);
    }
    public void warning(String s){
        Calendar cal = Calendar.getInstance();
        System.out.println(ANSI_YELLOW + sdf.format(cal.getTime()) + " [WARNING]: " + s + ANSI_RESET);
    }
    public void fatal(String s){
        Calendar cal = Calendar.getInstance();
        System.out.println(ANSI_RED + sdf.format(cal.getTime()) + " [FATAL]: " + s + ANSI_RESET);
    }
}
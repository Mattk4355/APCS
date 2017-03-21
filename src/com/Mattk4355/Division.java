package com.Mattk4355;

public final class Division {
    private Division() {}

    public synchronized static void printDivide(final int num, final int den){
        printDivide(num, den, 10);
    }
    public synchronized static void printDivide(final int num, final int den, final int numDecimals){
        if(numDecimals < 0) throw new IllegalArgumentException("number of decimal places < 0: " + numDecimals);
        printDivide(num, den, 0, numDecimals + 1);
    }
    private static void printDivide(final int num, final int den, final int op, final int numTimes){
        if (numTimes == op) return;
        if (op > numTimes) throw new IllegalArgumentException("Illegal operation number: " + op);
        int rem = num % den;
        System.out.print(num / den);
        if (op == 0) System.out.print('.');
        if (rem == 0) return;
        printDivide(rem * 10, den, op + 1, numTimes);
    }

    public static String stringDivide(final int num, final int den){
        return stringDivide(num, den, 10);
    }

    public static String stringDivide(final int num, final int den, final int numDecimals){
        return stringDivide("", num, den, 0, numDecimals + 1);
    }

    public static String stringDivide(String in, final int num, final int den, final int op, final int numTimes) {
        if (numTimes == op) return in;
        if (op > numTimes) throw new IllegalArgumentException("Illegal operation number: " + op);
        int rem = num % den;
        in = in.concat(Integer.toString(num / den));
        if (op == 0) in = in.concat(".");
        if (rem == 0) return in;
        return stringDivide(in, rem * 10, den, op + 1, numTimes);
    }
}
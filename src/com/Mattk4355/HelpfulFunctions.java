//(C) Matthew Krawczyk
package com.Mattk4355;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class HelpfulFunctions {
    private LogHelper logHelper = new LogHelper();

    /*
    * takes a file location (string) and an ArrayList (String)
    * writes contents of ArrayList to file
    */
    public void WriteToFile(String s, ArrayList<String> l) {
        try {
            PrintStream write = new PrintStream(new File(s));

            for (int i = 0; i < l.size(); i++) {
                write.println(l.get(i));
            }
        } catch (IOException e) {
            logHelper.fatal("Error while writing to file.");
        }
    }

    /*
    * takes a file location (string)
    * returns an ArrayList with the contents of the file
    */
    public ArrayList<String> ReadFromFile(String s) {
        ArrayList<String> x = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(s);
            Scanner input = new Scanner(fis);

            while (input.hasNextLine()) {
                x.add(input.nextLine());
            }
        } catch (FileNotFoundException e) {
            logHelper.fatal("Error while writing to file");
        }
        return x;
    }

    /*
    * returns largest integer in a set of 3
    */
    public int MaxInt(int a, int b, int c) {
        if ((a >= b && b >= c) || (a >= c && c >= b)) {
            return a;
        } else if ((b >= a && a >= c) || (b >= c && c >= a)) {
            return b;
        } else { //if ((c >= b && b >= a) || (c >= a && a >= b))
            return c;
        }
    }

    private long startTime;
    private long stopTime;

    private static final double NANOS_PER_SEC = 100000000.0;

    public void start() {
        startTime = System.nanoTime();
    }

    public void stop() {
        stopTime = System.nanoTime();
    }

    public double time() {
        return (stopTime - startTime) / NANOS_PER_SEC;
    }

    public String timeElapsed() {
        return "Elapsed time: " + time() + " seconds.";
    }

    /*
    * calculates the factorial of a number
    * returns that number
    */
    public int calcFactorial(int a) {
        int tot = 1;
        if (a < 0) {
            throw new IllegalArgumentException("Cannot compute negative factorials.");
        } else if (a == 0) {
            return 1;
        } else {
            for (int i = 1; i <= a; i++) {
                tot *= i;
            }
            return tot;
        }
    }

    /*
    * checks if a number is prime
    * returns true if number is prime (has 2 divisors [1 and itself])
    * returns false if number is not prime (has more than 2 divisors)
    */
    public boolean isPrime(int a) {
        ArrayList<Integer> divisors = new ArrayList<>();

        for (int i = 1; i <= a; i++) {
            if (a % i == 0) {
                divisors.add(i);
            }
        }
        return divisors.size() == 2;
    }
}
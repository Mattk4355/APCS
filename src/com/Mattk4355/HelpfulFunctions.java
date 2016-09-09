//(C) Matthew Krawczyk
package com.Mattk4355;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;

public class HelpfulFunctions {
    public void WriteToFile(String a, String b){
        try {
            PrintStream write = new PrintStream(new File(a));

            write.println(b);
        }
        catch (FileNotFoundException e){
            System.out.println("An Error Has occurred while trying to write to file.");
        }

    }
    /*
    * returns largest integer in a set of 3
    */
    public int MaxInt(int a, int b, int c){
        if ((a >= b && b >= c) || (a >= c && c >= b)) {
            return a;
        }
        else if ((b >= a && a >= c) || (b >= c && c >= a)) {
            return b;
        }
        else { //if ((c >= b && b >= a) || (c >= a && a >= b))
            return c;
        }
    }

    private long startTime;
    private long stopTime;

    public static final double NANOS_PER_SEC = 100000000.0;

    public void start(){
        startTime = System.nanoTime();
    }
    public void stop(){
        stopTime = System.nanoTime();
    }
    public double time(){
        return (stopTime - startTime)/ NANOS_PER_SEC;
    }
    public String timeElapsed(){
        return "Elapsed time: " + time() + " seconds.";
    }

    /*
    * calculates the factorial of a number
    * returns that number
    */
    public int calcFactorial(int a){
        int tot = 1;
        if (a < 0){
            throw new IllegalArgumentException("Cannot compute negative factorials.");
        }
        else if (a == 0){
            return 1;
        }
        else {
            for (int i = 1; i <= a; i++){
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
    public boolean isPrime(int a){
        ArrayList divisors = new ArrayList();

        for (int i = 1; i <= a; i++){
            if ( a % i == 0){
                divisors.add(i);
            }
        }
        return divisors.size() == 2;
    }
}

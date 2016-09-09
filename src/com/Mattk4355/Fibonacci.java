//(C) Matthew Krawczyk
package com.Mattk4355;

import java.util.Scanner;

public class Fibonacci {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("How many numbers to show?: ");
        int numPrinted = input.nextInt();

        int[] series = new int[numPrinted];

        series[0] = 0;
        series[1] = 1;

        for (int i = 2; i < numPrinted; i++){
            series[i] = series[i - 1] + series[i - 2];
        }

        System.out.println("Fibonacci Sequence of " + numPrinted + " numbers: ");
        for (int i = 0; i < numPrinted; i++){
            System.out.println(series[i] + " ");
        }
    }
}

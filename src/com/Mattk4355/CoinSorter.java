//(C) Matthew Krawczyk
package com.Mattk4355;

import java.util.Scanner;

public class CoinSorter {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int c, d, e, f, g, h, i, j, k;

        System.out.println( "Input amount here (in dollars.cents): ");
        double a = input.nextDouble() * 100;

        int b = (int) a;

        c = b / 10000;
        b -= (c * 10000);
        d = b / 5000;
        b -= (d * 5000);
        e = b / 2000;
        b -= (e * 2000);
        f = b / 1000;
        b -= (f * 1000);
        g = b / 500;
        b-= (g * 500);
        h = b / 100;
        b -= (h * 100);
        i = b / 25;
        b -= (i * 25);
        j = b / 10;
        b -= (j * 10);
        k = b / 5;
        b -= (k * 5);

        System.out.println((a / 100) + " dollars is: ");
        System.out.println("100 Dollar bills: " + c + "\n");
        System.out.println("50 Dollar bills: " + d + "\n");
        System.out.println("20 Dollar bills: " + e + "\n");
        System.out.println("10 Dollar bills: " + f + "\n");
        System.out.println("5 Dollar bills: " + g + "\n");
        System.out.println("Dollar bills: " + h + "\n");
        System.out.println("Quarters: " + i + "\n");
        System.out.println("Dimes: " + j + "\n");
        System.out.println("Nickels: " + k + "\n");
        System.out.println("Pennies: " + b + "\n" + "\n");
        System.out.println("Done!");


    }
}

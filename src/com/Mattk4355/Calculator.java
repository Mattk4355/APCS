//(C) Matthew Krawczyk
package com.Mattk4355;

import java.util.Scanner;

public class Calculator {
    public static void main(String[] args){
        double a = 0;
        Scanner input = new Scanner(System.in);

        System.out.println("Input choice (1 = Add, 2 = Subtract, 3 = Multiply, 4 = Divide, 5 = Modulo, 6 = Power): ");
        int b = input.nextInt();

        if (b == 1){
            a = Add();
        }
        else if (b == 2){
            a = Subtract();
        }
        else if (b == 3){
            a = Multiply();
        }
        else if (b == 4){
            a = Divide();
        }
        else if (b == 5){
            a = Modulo();
        }
        else if (b == 6){
            a = Power();
        }
        else{
            System.out.println("Invalid choice!");
        }

        System.out.println(a);
    }
    public static double Add() {
        Scanner input = new Scanner(System.in);

        System.out.println("Input 1st number: ");
        double a = input.nextDouble();
        System.out.println("Input 2nd number: ");
        double b = input.nextDouble();

        return a + b;
    }
    public static double Subtract() {
        Scanner input = new Scanner(System.in);

        System.out.println("Input 1st number: ");
        double a = input.nextDouble();
        System.out.println("Input 2nd number: ");
        double b = input.nextDouble();

        return a - b;
    }
    public static double Multiply() {
        Scanner input = new Scanner(System.in);

        System.out.println("Input 1st number: ");
        double a = input.nextDouble();
        System.out.println("Input 2nd number: ");
        double b = input.nextDouble();

        return a * b;
    }
    public static double Divide() {
        Scanner input = new Scanner(System.in);

        System.out.println("Input 1st number: ");
        double a = input.nextDouble();
        System.out.println("Input 2nd number: ");
        double b = input.nextDouble();

        return a / b;
    }
    public static double Modulo() {
        Scanner input = new Scanner(System.in);

        System.out.println("Input 1st number: ");
        double a = input.nextDouble();
        System.out.println("Input 2nd number: ");
        double b = input.nextDouble();

        return a % b;
    }
    public static double Power() {
        Scanner input = new Scanner(System.in);

        System.out.println("Input number: ");
        double a = input.nextDouble();
        System.out.println("Input power: ");
        double b = input.nextDouble();

        return Math.pow(a , b);
    }
}

package com.Mattk4355;

import java.util.Scanner;

public class IFElseLab {
    public static void part1(){
        Scanner input = new Scanner(System.in);

        System.out.print("Input number: ");
        int a = input.nextInt();

        if (a > 70){
            System.out.print("PASSING" + "\n");
        }
        else {
            System.out.print("NOT PASSING" + "\n");
        }
    }

    public static void part2(){
        Scanner input = new Scanner(System.in);

        System.out.print("Input number: ");
        int a = input.nextInt();

        if (a <= 50){
            System.out.print("GO" + "\n");
        }
        else {
            System.out.print("STOP" + "\n");
        }
    }

    public static void part3(){
        Scanner input = new Scanner(System.in);

        System.out.print("Input number: ");
        int a = input.nextInt();

        if (a % 2 == 0){
            System.out.print("EVEN" + "\n");
        }
        else {
            System.out.print("ODD" + "\n");
        }
    }

    public static void part4(){
        Scanner input = new Scanner(System.in);

        System.out.print("Input number: ");
        int a = input.nextInt();

        if (a % 5 == 0){
            System.out.print("MULTIPLE OF 5" + "\n");
        }
        else {
            System.out.print("NOT MULTIPLE OF 5" + "\n");
        }
    }

    public static void part5(){
        Scanner input = new Scanner(System.in);

        System.out.print("Input number: ");
        int a = input.nextInt();

        if (a <= 10){
            System.out.print("ONE DIGIT" + "\n");
        }
        else if (a >= 100){
            System.out.print("THREE DIGITS" + "\n");
        }
        else {
            System.out.print("TWO DIGITS" + "\n");
        }
    }

    public static void part6(){
        Scanner input = new Scanner(System.in);

        System.out.print("Input jersey number: ");
        int a = input.nextInt();

        if ((a == 16) || (a == 8) || (a == 80)){
            System.out.print("That number is retired from the San Francisco 49ers!" + "\n");
        }
    }

    public static void part7(){
        Scanner input = new Scanner(System.in);

        System.out.print("Input state name: ");
        String a = input.nextLine();

        if (a.equals("California") || a.equals("Hawaii") || a.equals("New York")){
            System.out.print("This state is awesome!" + "\n");
        }
        else {
            System.out.print("You should move to Cali/Hawaii/NY; its great here" + "\n");
        }
    }

    public static void part8(){
        Scanner input = new Scanner(System.in);

        System.out.print("Input coffee size: ");
        String a = input.nextLine();

        if (a.equals("Short")){
            System.out.print("8 oz" + "\n");
        }
        else if (a.equals("Tall")){
            System.out.print("12 oz" + "\n");
        }
        else if (a.equals("Grande" + "\n")){
            System.out.print("16 oz" + "\n");
        }
        else if (a.equals("Venti")){
            System.out.print("20 oz" + "\n");
        }
        else {
            System.out.print("Error: Invalid name" + "\n");
        }
    }

    public static void part9(){
        Scanner input = new Scanner(System.in);

        System.out.print("Input hours worked: ");
        double a = input.nextDouble();
        double PAY;

        if (a <= 40){
            PAY = a * 10;
        }
        else {
            double extraHours = a - 40;
            PAY = (40 * 10) + (extraHours * 15);
        }

        System.out.print("Gross Pay is: " + PAY + "\n");
    }

    public static void part10(){
        Scanner input = new Scanner(System.in);

        System.out.print("Input hours worked: ");
        double a = input.nextDouble();

        if (a > 60){
            System.out.print("Please see manager" + "\n");
        }

        else {
            double PAY;

            if (a <= 40){
                PAY = a * 10;
                System.out.print("Gross Pay is: " + PAY + "\n");
            }
            else {
                double extraHours = a - 40;
                PAY = (40 * 10) + (extraHours * 15);
                System.out.print("Gross Pay is: " + PAY + "\n");
            }
        }
    }

    public static void main(String[] args) {
        part1();
        part2();
        part3();
        part4();
        part5();
        part6();
        part7();
        part8();
        part9();
        part10();
    }
}
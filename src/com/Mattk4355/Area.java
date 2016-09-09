//(C) Matthew Krawczyk
package com.Mattk4355;

import java.util.Scanner;

public class Area {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        double area = 0;

        System.out.println("Enter the shape you wish to calculate the area for (1 = Square, 2 = rectangle, 3 = circle): ");
        int sel = input.nextInt();

        if (sel == 1){
            area = areaSquare();
        }
        else if (sel == 2){
            area = areaRectangle();
        }
        else if (sel == 3){
            area = areaCircle();
        }
        else{
            System.out.println("Enter a valid choice.");
        }

        System.out.println("Area is: " + area);
    }

    public static double areaSquare(){
        Scanner input = new Scanner(System.in);

        System.out.println("Input side length: ");
        int a = input.nextInt();

        return Math.pow(a, 2);
    }

    public static double areaRectangle(){
        Scanner input = new Scanner(System.in);

        System.out.println("Input side length 1: ");
        int a = input.nextInt();

        System.out.println("Input side length 2: ");
        int b = input.nextInt();

        return a * b;
    }

    public static double areaCircle(){
        Scanner input = new Scanner(System.in);

        System.out.println("Input radius: ");
        int a = input.nextInt();

        return Math.pow(a, 2) * Math.PI;
    }
}

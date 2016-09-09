//(C) Matthew Krawczyk
package com.Mattk4355;

import java.util.Scanner;

public class Gradebook {
    public static void main(String[] args){

        Scanner input = new Scanner(System.in);

        int grade = 0, grade1, grades;

        System.out.println("Input student name:");
        String student = input.nextLine();

        System.out.println("Input number of grades you wish to enter: ");
        grades = input.nextInt();

        for (int a = 0; grades > a; a++){

            System.out.println("Input grade " + (a + 1) + " :");
            grade1 = input.nextInt();

            grade = grade + grade1;
        }

        grade = grade / grades;

        if (grade >= 97){
            System.out.println( student + " got an A+");
        }
        else if (grade >= 93){
            System.out.println( student + " got an A");
        }
        else if (grade >= 90){
            System.out.println( student + " got an A-");
        }
        else if (grade >= 87){
            System.out.println( student + " got a B+");
        }
        else if (grade >= 83){
            System.out.println( student + " got a B");
        }
        else if (grade >= 80){
            System.out.println( student + " got a B-");
        }
        else if (grade >= 77){
            System.out.println( student + " got a C+");
        }
        else if (grade >= 73){
            System.out.println( student + " got a C");
        }
        else if (grade >= 70){
            System.out.println( student + " got a C-");
        }
        else if (grade >= 67){
            System.out.println( student + " got a D+");
        }
        else if (grade >= 63){
            System.out.println( student + " got a D");
        }
        else if (grade >= 60){
            System.out.println( student + " got a D-");
        }
        else{
            System.out.println( student + " got an F");
        }
    }
}

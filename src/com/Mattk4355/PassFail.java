//(C) Matthew Krawczyk
package com.Mattk4355;

import java.util.Scanner;

public class PassFail {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        System.out.println("Enter AP Test0 Score: ");
        int score = input.nextInt();

        System.out.println("Passed: " + checkPassed(score));
    }
    public static boolean checkPassed(int score){
        if (score > 0 && score <= 5){
            return !(score == 1 || score == 2);
        }
        else {
            System.out.println("Enter a Valid Score!");
            return false;
        }
    }
}

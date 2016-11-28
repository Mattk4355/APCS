// Matthew Krawczyk
// Period 4 APCS
// 11/28/16
package com.Mattk4355;

import java.util.Scanner;

public class FracSolve {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int tempNumerator, tempDenominator;
        Fraction fraction1, fraction2;
        System.out.print("Enter fraction 1 numerator: ");
        tempNumerator = input.nextInt();
        System.out.print("Enter fraction 1 denominator: ");
        tempDenominator = input.nextInt();
        fraction1 = new Fraction(tempNumerator, tempDenominator);

        System.out.print("Enter fraction 2 numerator: ");
        tempNumerator = input.nextInt();
        System.out.print("Enter fraction 2 denominator: ");
        tempDenominator = input.nextInt();
        fraction2 = new Fraction(tempNumerator , tempDenominator);

        System.out.print("What do you want to do (1 = add, 2 = subtract, 3 = multiply, 4 = divide): ");
        int option = input.nextInt();

        System.out.println();

        switch (option){
            case 1:
                fraction1.add(fraction1, fraction2);
                break;
            case 2:
                fraction1.subtract(fraction1, fraction2);
                break;
            case 3:
                fraction1.multiply(fraction1, fraction2);
                break;
            case 4:
                fraction1.divide(fraction1, fraction2);
                break;
            default:
                break;
        }
    }
}
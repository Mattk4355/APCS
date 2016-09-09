// Matthew Krawczyk
// Period 4 APCS
// 09/06/16
package com.Mattk4355;

import java.util.Scanner;

public class HelloProgram {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in); //for user input

        System.out.print("Name?: "); //Have user input name
        String name = input.nextLine(); //Name to print out in the next line

        System.out.println("Hello " + name);
        System.out.print("I am happy today.");
    }
}

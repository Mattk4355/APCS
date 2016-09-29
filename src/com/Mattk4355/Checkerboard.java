package com.Mattk4355;

import java.util.Scanner;

public class Checkerboard {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Input board length: ");
        int a = input.nextInt();

        for (int i = 0; i < a; i++) {
            if (i % 2 == 0){ //odd row
                for (int j = 0; j < a; j++) {
                    if(j % 2 == 0){ //odd column
                        System.out.print("*");
                    }
                    else{ //even column
                        System.out.print("  ");
                    }
                }
                System.out.println("");
            }
            else{ //even row
                for (int j = 0; j < a; j++) {
                    if (j % 2 == 0){ //even column
                        System.out.print("  ");
                    }
                    else{ //odd column
                        System.out.print("*");
                    }
                }
                System.out.println("");
            }
        }
    }
}

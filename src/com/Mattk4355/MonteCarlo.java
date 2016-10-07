// Matthew Krawczyk
// Period 4 APCS
// 10/04/16
package com.Mattk4355;

import java.util.Random;
import java.util.Scanner;

public class MonteCarlo {
    double score0 = 0, score20 = 0, score21 = 0, score22 = 0, score23 = 0, score24 = 0, score25 = 0;

    public static void main(String[] args) {
        MonteCarlo m = new MonteCarlo();
        m.run();
    }

    public void run(){
        Scanner input = new Scanner(System.in);
        System.out.print("Hold at 20 turn simulations?: ");
        int turns = input.nextInt();

        for (int i = 0; i < turns; i++) {
            Turn();
        }

        System.out.println("Points Probability");
        System.out.println("0 " + (score0/turns));
        System.out.println("20 " + (score20/turns));
        System.out.println("21 " + (score21/turns));
        System.out.println("22 " + (score22/turns));
        System.out.println("23 " + (score23/turns));
        System.out.println("24 " + (score24/turns));
        System.out.println("25 " + (score25/turns));

    }

    public void Turn(){
        int score = 0;
        while(true){
            Random ran = new Random();
            int x = ran.nextInt(6) + 1;
            switch (x){
                case 1: score0 += 1;
                    return;
                default: score += x;
                    break;
            }
            if (score >= 20 && score <= 25){
                switch (score){
                    case 20: score20 += 1;
                        return;
                    case 21: score21 += 1;
                        return;
                    case 22: score22 += 1;
                        return;
                    case 23: score23 += 1;
                        return;
                    case 24: score24 += 1;
                        return;
                    case 25: score25 += 1;
                        return;
                    default:
                        break;
                }
            }
        }
    }
}

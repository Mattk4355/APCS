// Matthew Krawczyk
// Period 4 APCS
// 10/18/16

package com.Mattk4355;

import java.util.Scanner;

public class CarDealer {
    public static void main(String[] args) {
        CarDealer c = new CarDealer();
        c.run();
    }
    public void run(){
        int totalPrice = 0;
        int x = Inventory();
        int y = addons();
        totalPrice += (x + y);

        System.out.print("***** CAR TOTAL $" + totalPrice + " *****");
    }
    public int Inventory(){
        Scanner input = new Scanner(System.in);
        System.out.println("Our Inventory: ");
        System.out.println("Model 1 - $15,000");
        System.out.println("Model 2 - $24,000");
        System.out.println("Model 3 - $40,000");

        System.out.print("Input model: ");
        int a = input.nextInt();
        switch (a){
            case 1: return 15000;
            case 2: return 24000;
            case 3: return 40000;
        }
        return 0;
    }
    public int addons(){
        Scanner input = new Scanner(System.in);
        int addons = 0;
        System.out.println("1 - Remote keyless entry ($1000)");
        System.out.println("2 - OnStar System ($1000)");
        System.out.println("3 - Anti-lock brake system (ABS) ($500)");
        System.out.println("4 - Telescopic steering wheel/ adjustable pedals ($1000)");
        System.out.println("5 - Sun roof ($800)");
        System.out.println("6 - Cold weather package ($1500)");
        System.out.println("7 - GPS Navigation system ($750)");
        while(true){
            System.out.print("What mode would you like to add (0 to quit)");
            int a = input.nextInt();
            switch (a){
                case 1: addons += 1000;
                    break;
                case 2: addons += 1000;
                    break;
                case 3: addons += 500;
                    break;
                case 4: addons += 1000;
                    break;
                case 5: addons += 800;
                    break;
                case 6: addons += 1500;
                    break;
                case 7: addons += 750;
                    break;
                default:
                    return addons;
            }
        }
    }
}

package com.Mattk4355;

import java.util.Random;
import java.util.Scanner;

public class Nim {
    public static void main(String[] args) {
        int PlayerRemove, CompRemove, user, mode, kill;
        Scanner input = new Scanner(System.in);
        Random rand = new Random();
        int marbles = rand.nextInt(90) + 10;
        user = rand.nextInt(1);

        while (true) {
            System.out.print("Input mode (0 = naive, 1 = smart): ");
            mode = input.nextInt();
            if (mode == 1) { //smart mode
                if (user == 0){
                    while (true){
                        while (true) {
                            System.out.println("Marbles remaining: " + marbles);
                            System.out.print("How many marbles to remove: ");
                            PlayerRemove = input.nextInt();

                            if (PlayerRemove >= 1 && PlayerRemove <= (0.5 * marbles)) {
                                marbles -= PlayerRemove;
                                System.out.println("Marbles remaining: " + marbles);
                                user = 0;
                                break;
                            }
                            else {
                                System.out.println("Input valid number");
                            }
                        }
                        if (marbles == 1) {
                            break;
                        }
                        while (true){
                            if (marbles == 63 || marbles == 31 || marbles == 15 || marbles == 7 || marbles == 3){
                                while (true) {
                                    CompRemove = rand.nextInt(marbles) + 1;

                                    if (CompRemove >= 1 && CompRemove <= (0.5 * marbles)) {
                                        marbles -= CompRemove;
                                        System.out.println("Marbles remaining: " + marbles);
                                        user = 1;
                                        break;
                                    }
                                }
                                if (marbles == 1) {
                                    break;
                                }
                            }
                            else {
                                if (marbles > 63){
                                    user = 1;
                                    marbles = 63;
                                    break;
                                }
                                else if (marbles > 31){
                                    user = 1;
                                    marbles = 31;
                                    break;
                                }
                                else if (marbles > 15){
                                    user = 1;
                                    marbles = 15;
                                    break;
                                }
                                else if (marbles > 7){
                                    user = 1;
                                    marbles = 7;
                                    break;
                                }
                                else if (marbles > 3) {
                                    user = 1;
                                    marbles = 3;
                                    break;
                                }
                                else if (marbles == 2){
                                    user = 1;
                                    marbles = 1;
                                    break;
                                }
                            }
                        }
                        if (marbles == 1) {
                            break;
                        }
                    }
                }
                else{
                    while (true){
                        while (true){
                            if (marbles == 63 || marbles == 31 || marbles == 15 || marbles == 7 || marbles == 3){
                                while (true) {
                                    System.out.println("Marbles remaining: " + marbles);
                                    CompRemove = rand.nextInt(marbles) + 1;

                                    if (CompRemove >= 1 && CompRemove <= (0.5 * marbles)) {
                                        marbles -= CompRemove;
                                        System.out.println("Marbles remaining: " + marbles);
                                        user = 1;
                                        break;
                                    }
                                }
                                if (marbles == 1) {
                                    break;
                                }
                            }
                            else {
                                System.out.println("Marbles remaining: " + marbles);
                                if (marbles > 63){
                                    user = 1;
                                    marbles = 63;
                                    break;
                                }
                                else if (marbles > 31){
                                    user = 1;
                                    marbles = 31;
                                    break;
                                }
                                else if (marbles > 15){
                                    user = 1;
                                    marbles = 15;
                                    break;
                                }
                                else if (marbles > 7){
                                    user = 1;
                                    marbles = 7;
                                    break;
                                }
                                else if (marbles > 3) {
                                    user = 1;
                                    marbles = 3;
                                    break;
                                }
                                else if (marbles == 2){
                                    user = 1;
                                    marbles = 1;
                                    break;
                                }
                            }
                        }
                        if (marbles == 1) {
                            break;
                        }
                        while (true) {
                            System.out.println("Marbles remaining: " + marbles);
                            System.out.print("How many marbles to remove: ");
                            PlayerRemove = input.nextInt();

                            if (PlayerRemove >= 1 && PlayerRemove <= (0.5 * marbles)) {
                                marbles -= PlayerRemove;
                                System.out.println("Marbles remaining: " + marbles);
                                user = 0;
                                break;
                            }
                            else {
                                System.out.println("Input valid number");
                            }
                        }
                        if (marbles == 1) {
                            break;
                        }
                    }
                }
                if (user == 0) {
                    System.out.println("You Win");
                }
                if (user == 1) {
                    System.out.println("You Lose!");
                }
                System.out.print("Would you like to continue (0 = yes, 1 = no): ");
                kill = input.nextInt();
                if (kill == 1) {
                    return;
                }
            }
            else { //naive mode
                if (user == 0) {
                    while (true) {
                        System.out.println("Marbles remaining: " + marbles);
                        while (true) {
                            System.out.print("How many marbles to remove: ");
                            PlayerRemove = input.nextInt();

                            if (PlayerRemove >= 1 && PlayerRemove <= (0.5 * marbles)) {
                                marbles -= PlayerRemove;
                                user = 0;
                                System.out.println("Marbles remaining: " + marbles);
                                break;
                            }
                            else {
                                System.out.println("Input valid number");
                            }
                        }
                        if (marbles == 1) {
                            break;
                        }

                        while (true) {
                            CompRemove = rand.nextInt(marbles) + 1;

                            if (CompRemove >= 1 && CompRemove <= (0.5 * marbles)) {
                                marbles -= CompRemove;
                                System.out.println("Marbles remaining: " + marbles);
                                user = 1;
                                break;
                            }
                        }
                        if (marbles == 1) {
                            break;
                        }
                    }
                }
                else {
                    while (true) {
                        System.out.println("Marbles remaining: " + marbles);
                        while (true) {
                            CompRemove = rand.nextInt(marbles) + 1;

                            if (CompRemove >= 1 && CompRemove <= (0.5 * marbles)) {
                                marbles -= CompRemove;
                                user = 1;
                                break;
                            }
                        }
                        if (marbles == 1) {
                            break;
                        }
                        while (true) {
                            System.out.print("How many marbles to remove: ");
                            PlayerRemove = input.nextInt();

                            if (PlayerRemove >= 1 && PlayerRemove <= (0.5 * marbles)) {
                                marbles -= PlayerRemove;
                                System.out.println("Marbles remaining: " + marbles);
                                user = 0;
                                break;
                            }
                            else {
                                System.out.println("Input valid number");
                            }
                        }
                        if (marbles == 1) {
                            break;
                        }
                    }
                }
                if (user == 0) {
                    System.out.println("You Win");
                }
                if (user == 1) {
                    System.out.println("You Lose!");
                }
                System.out.print("Would you like to continue (0 = yes, 1 = no): ");
                kill = input.nextInt();
                if (kill == 1) {
                    return;
                }
            }
        }
    }
}
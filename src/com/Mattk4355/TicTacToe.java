// Matthew Krawczyk
// Period 4 APCS
// 10/20/16
package com.Mattk4355;

import java.util.Scanner;

public class TicTacToe {
    //board and the necessary variables
    private String a1, a2, a3, b1, b2, b3, c1, c2, c3;
    //game conditions
    private String currentPlayer = "X", cell, winner = " ";

    Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        TicTacToe t = new TicTacToe();
        t.run();
    }
    public void run(){
        while (true){
            initBoard();
            printBoard();
            while (true){
                playerMove(currentPlayer);
                updateBoard(currentPlayer);

                if (winner.equals("X")){
                    System.out.println("X wins!");
                    break;
                }
                else if (winner.equals("O")){
                    System.out.println("O wins!");
                    break;
                }
                else if (winner.equals("draw")){
                    System.out.print("Draw!");
                    break;
                }
                printBoard2();
            }

            System.out.print("would you like to keep playing (1 = yes)");
            int a = input.nextInt();
            if (a != 1){
                return;
            }
        }
    }
    private void playerMove(String player){
        while (true){
            if (player.equals("X")){
                System.out.print("Player \"X\" enter cell for move: ");
                cell = input.nextLine();
                currentPlayer = "O";
            }
            if (player.equals("O")){
                System.out.print("Player \"O\" enter cell for move: ");
                cell = input.nextLine();
                currentPlayer = "X";
            }
            if (currentPlayer.equals("O")){
                if (cell.equals("a1") && a1.equals(" ")){
                    a1 = "X";
                    break;
                }
                else if (cell.equals("a2") && a2.equals(" ")){
                    a2 = "X";
                    break;
                }
                else if (cell.equals("a3") && a3.equals(" ")){
                    a3 = "X";
                    break;
                }
                else if (cell.equals("b1") && b1.equals(" ")){
                    b1 = "X";
                    break;
                }
                else if (cell.equals("b2") && b2.equals(" ")){
                    b2 = "X";
                    break;
                }
                else if (cell.equals("b3") && b3.equals(" ")){
                    b3 = "X";
                    break;
                }
                else if (cell.equals("c1") && c1.equals(" ")){
                    c1 = "X";
                    break;
                }
                else if (cell.equals("c2") && c2.equals(" ")){
                    c2 = "X";
                    break;
                }
                else if (cell.equals("c3") && c3.equals(" ")){
                    c3 = "X";
                    break;
                }
                else{
                    System.out.println("cell " + cell + " is an invalid move");
                }
            }
            else {
                if (cell.equals("a1") && a1.equals(" ")){
                    a1 = "O";
                    break;
                }
                else if (cell.equals("a2") && a2.equals(" ")){
                    a2 = "O";
                    break;
                }
                else if (cell.equals("a3") && a3.equals(" ")){
                    a3 = "O";
                    break;
                }
                else if (cell.equals("b1") && b1.equals(" ")){
                    b1 = "O";
                    break;
                }
                else if (cell.equals("b2") && b2.equals(" ")){
                    b2 = "O";
                    break;
                }
                else if (cell.equals("b3") && b3.equals(" ")){
                    b3 = "O";
                    break;
                }
                else if (cell.equals("c1") && c1.equals(" ")){
                    c1 = "O";
                    break;
                }
                else if (cell.equals("c2") && c2.equals(" ")){
                    c2 = "O";
                    break;
                }
                else if (cell.equals("c3") && c3.equals(" ")){
                    c3 = "O";
                    break;
                }
                else{
                    System.out.println("cell " + cell + " is an invalid move");
                }
            }
        }
    }
    private void initBoard(){
        a1 = " ";
        a2 = " ";
        a3 = " ";
        b1 = " ";
        b2 = " ";
        b3 = " ";
        c1 = " ";
        c2 = " ";
        c3 = " ";
    }
    private void printBoard(){
        System.out.println("Cell values: ");
        System.out.println(" a1 | a2 | a3");
        System.out.println("-------------");
        System.out.println(" b1 | b2 | b3");
        System.out.println("-------------");
        System.out.println(" c1 | c2 | c3");
    }
    private void printBoard2(){
        System.out.println(" " + a1 + " | " + a2 + " | "  + a3 + " ");
        System.out.println("-----------");
        System.out.println(" " + b1 + " | " + b2 + " | "  + b3 + " ");
        System.out.println("-----------");
        System.out.println(" " + c1 + " | " + c2 + " | "  + c3 + " ");
    }
    private void updateBoard(String player){
        if (hasWon(player)){
           if (player.equals("O")){
               winner = "X";
           }
           if (player.equals("X")){
               winner = "O";
           }
        }
        else if (isDraw()) {
            winner = "draw";
        }
    }
    private boolean hasWon(String player){
        if (player.equals("O")){
            if ((a1.equals("X") && a2.equals("X") && a3.equals("X")) ||
                    (b1.equals("X") && b2.equals("X") && b3.equals("X")) ||
                    (c1.equals("X") && c2.equals("X") && c3.equals("X"))){
                return true;
            }
            else if ((a1.equals("X") && b1.equals("X") && c1.equals("X")) ||
                    (a2.equals("X") && b2.equals("X") && c2.equals("X")) ||
                    (a3.equals("X") && b3.equals("X") && c3.equals("X"))){
                return true;
            }
            else if ((a1.equals("X") && b2.equals("X") && c3.equals("X")) ||
                    (a3.equals("X") && b2.equals("X") && c1.equals("X"))){
                return true;
            }
            else{
                return false;
            }
        }
        if (player.equals("X")){
            if ((a1.equals("O") && a2.equals("O") && a3.equals("O")) ||
                    (b1.equals("O") && b2.equals("O") && b3.equals("O")) ||
                    (c1.equals("O") && c2.equals("O") && c3.equals("O"))){
                return true;
            }
            else if ((a1.equals("O") && b1.equals("O") && c1.equals("O")) ||
                    (a2.equals("O") && b2.equals("O") && c2.equals("O")) ||
                    (a3.equals("O") && b3.equals("O") && c3.equals("O"))){
                return true;
            }
            else if ((a1.equals("O") && b2.equals("O") && c3.equals("O")) ||
                    (a3.equals("O") && b2.equals("O") && c1.equals("O"))){
                return true;
            }
            else{
                return false;
            }
        }
        return false;
    }
    private boolean isDraw(){
        return (!(a1.equals(" ") || a2.equals(" ") || a3.equals(" ") ||
                b1.equals(" ") || b2.equals(" ") || b3.equals(" ") ||
                c1.equals(" ") || c2.equals(" ") || c3.equals(" ")));
    }
}
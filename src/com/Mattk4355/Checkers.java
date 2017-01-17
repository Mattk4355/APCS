package com.Mattk4355;

import java.util.Scanner;

public class Checkers {
    private CheckerPiece[][] board = new CheckerPiece[10][10];
    private final CheckerPiece BLANK_PIECE = new CheckerPiece();
    private String currentPlayer = "Red";

    public static void main(String[] args) {
        Checkers checkers = new Checkers();
        checkers.run();
    }
    private void run(){
        Scanner input = new Scanner(System.in);
        initBoard();
        int fromX, toX, fromY, toY;

        while(true){
            printBoard();
            System.out.println(currentPlayer + "'s turn.");
            while(true){
                while (true){
                    System.out.print("Enter the x value you wish to move from: ");
                    fromX = input.nextInt() - 1;
                    System.out.print("Enter the y value you wish to move from: ");
                    fromY = input.nextInt() - 1;
                    System.out.print("Enter the x value you wish to move to: ");
                    toX = input.nextInt() - 1;
                    System.out.print("Enter the y value you wish to move to: ");
                    toY = input.nextInt() - 1;

                    if (inBounds(fromX) && inBounds(fromY) &&
                            inBounds(toX) && inBounds(toY)){
                        break;
                    }
                    System.out.println("Value(s) are not in bounds, enter valid one(s).");
                }

                if (pieceCanMove(pieceAt(fromX, fromY), fromX, fromY, toX, toY)){
                    movePiece(pieceAt(fromX, fromY), fromX, toX, fromY, toY);
                    if (checkWon()){
                        break;
                    }
                    else if (moveType(fromX, fromY, toX, toY) == 0){
                        break;
                    }
                }
                else {
                    System.out.println("Invalid move, enter a valid one.");
                }
            }
            if (checkWon()){
                break;
            }
            swapPlayer();
        }
        System.out.print(currentPlayer + " has won!");
    }
    private boolean inBounds(int value){
        return value >= 0 && value <= 9;
    }
    private boolean checkWon(){
        String player;
        if (currentPlayer.equals("Red")){
            player = "Black";
        }
        else {
            player = "Red";
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j].getName().equals(player)){
                    return false;
                }
            }
        }
        return true;
    }
    private void swapPlayer(){
        if (currentPlayer.equals("Red")){
            currentPlayer = "Black";
        }
        else{
            currentPlayer = "Red";
        }
    }
    private int moveType(int fromX, int fromY, int toX, int toY){
        if (Math.abs(fromX - toX) == 1 &&
                Math.abs(fromY - toY) == 1){
            return 0;
        }
        else if (Math.abs(fromX - toX) == 2 &&
                Math.abs(fromY - toY) == 2){
            return 1;
        }
        return -1;
    }
    private void movePiece(CheckerPiece piece, int fromX, int toX, int fromY, int toY){
        board[toX][toY] = piece;
        board[fromX][fromY] = BLANK_PIECE;

        if (piece.getName().equals("Black") && toY == 0){
            piece.setIsKing(true);
        }
        else if (piece.getName().equals("Red") && toY == 10){
            piece.setIsKing(true);
        }
    }
    private boolean pieceCanMove(CheckerPiece piece,int fromX, int fromY, int toX, int toY){
        if (!piece.getName().equals(currentPlayer)){
            return false;
        }

        if (piece.isKing() &&
                Math.abs(toX - fromX) == 2 &&
                Math.abs(toY - fromY) == 2 &&
                !isOccupied(toX, toY) &&
                isOccupiedByEnemy(Math.abs(fromX - toX) / 2, Math.abs(fromY - toY) / 2)){
            return true;
        }
        else if (piece.isKing() &&
                Math.abs(toX - fromX) == 1 &&
                Math.abs(fromY - toY) == 1 &&
                !isOccupied(toX, toY)){
            return true;
        }
        else if (toX - fromX == 1 &&
                Math.abs(fromY - toY) == 1 &&
                !isOccupied(toX, toY) &&
                piece.getName().equals("Red")){
            return true;
        }
        else if (fromX - toX == 1 &&
                Math.abs(fromY - toY) == 1 &&
                !isOccupied(toX, toY) &&
                piece.getName().equals("Black")){
            return true;
        }
        else if (fromX - toX == 2 &&
                Math.abs(fromY - toY) == 2 &&
                !isOccupied(toX, toY) &&
                piece.getName().equals("Black")&&
                isOccupiedByEnemy(Math.abs(fromX - toX) / 2, Math.abs(fromY - toY) / 2)){
            return true;
        }
        else if (toX - fromX == 2 &&
                Math.abs(fromY - toY) == 2 &&
                !isOccupied(toX, toY) &&
                piece.getName().equals("Red")&&
                isOccupiedByEnemy(Math.abs(fromX - toX) / 2, Math.abs(fromY - toY) / 2)){
            return true;
        }
        return false;
    }
    private boolean isOccupiedByEnemy(int posX, int posY){
        String player;
        if (currentPlayer.equals("Red")){
            player = "Black";
        }
        else {
            player = "Red";
        }
        return board[posX][posY].getName().equals(player);
    }
    private boolean isOccupied(int posX, int posY){
        return board[posX][posY] != BLANK_PIECE;
    }
    private CheckerPiece pieceAt(int posX, int posY){
        return board[posX][posY];
    }
    private void printBoard(){
        System.out.println("    1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10");
        System.out.println("   ---------------------------------------");
        for (int i = 0; i < board.length; i++) {
            if (i < board.length - 1){
                System.out.print((i + 1) + " | ");
            }
            else{
                System.out.print("10| ");
            }
            for (int j = 0; j < board[1].length; j++) {
                if (board[i][j] != BLANK_PIECE){
                    if (board[i][j].getName().equals("Red")){
                        System.out.print("R");
                    }
                    else{
                        System.out.print("B");
                    }
                }
                else{
                    System.out.print(" ");
                }
                if (j < board[0].length - 1){
                    System.out.print(" | ");
                }
            }
            System.out.println();
            if (i < board.length - 1){
                System.out.println("  |---|---|---|---|---|---|---|---|---|---");
            }
        }
    }
    private void initBoard(){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = BLANK_PIECE;
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (i == 0 || i == 2) {
                    if (j % 2 != 0) {
                        board[i][j] = new CheckerPiece("Red", false);
                    }
                }
                else if (i == 1 || i == 3) {
                    if (j % 2 == 0) {
                        board[i][j] = new CheckerPiece("Red", false);
                    }
                }
                else if (i == 6 || i == 8) {
                    if (j % 2 != 0) {
                        board[i][j] = new CheckerPiece("Black", false);
                    }
                }
                else if (i == 7 || i == 9) {
                    if (j % 2 == 0) {
                        board[i][j] = new CheckerPiece("Black", false);
                    }
                }
            }
        }
    }
}
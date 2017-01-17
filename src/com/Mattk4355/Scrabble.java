package com.Mattk4355;

import java.util.Scanner;

public class Scrabble {
    private static final char[] allowedValues = {'a', 'e', 'd', 'r', 'b', 'm', 'v', 'y', 'j', 'x'};

    public static void main(String[] args) {
        Scrabble scrabble = new Scrabble();
        scrabble.run();
    }
    public void run(){
        Scanner input = new Scanner(System.in);
        char[] letters;
        int start;

        System.out.println("Valid letters are: " + validLetters());
        do {
            System.out.print("Input a four character word: ");
            letters = input.next().toCharArray();

        } while (checkValidLetters(letters) && letters.length != 4);

        do{
            System.out.print("Input number of square on which to start: ");
             start = input.nextInt();
        } while (start <= 0 || start >= 37);

        printScore(letters, start);
    }
    private void run0(){
        final char[] values = {'j', 'a', 'v', 'a'};
        printScore(values, 1);
        printScore(values, 2);
        printScore(values, 4);
        printScore(values, 12);
        printScore(values, 21);
    }
    private void printScore(char[] values, int start){
        System.out.println("Score: " + checkPoints(values, start));
    }
    private int checkPoints(char[] values, int start){
        int c1val = basePointValue(values[0]);
        int c2val = basePointValue(values[1]);
        int c3val = basePointValue(values[2]);
        int c4val = basePointValue(values[3]);

        c1val *= multiplyChar(start);
        c2val *= multiplyChar(start + 1);
        c3val *= multiplyChar(start + 2);
        c4val *= multiplyChar(start + 3);

        int total = c1val + c2val + c3val + c4val;

        for (int i = start; i < start + 4; i++) {
            total *= multiplyWord(i);
        }
        return total;
    }
    private int basePointValue(char c){
        if (c == 'a' || c == 'e'){
            return 1;
        }
        else if (c == 'd' || c == 'r'){
            return 2;
        }
        else if (c == 'b' || c == 'm'){
            return 3;
        }
        else if (c == 'v' || c == 'y'){
            return 4;
        }
        else if (c == 'j' || c == 'x'){
            return 8;
        }
        return -1;
    }
    private String validLetters(){
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (int i = 0; i < allowedValues.length - 1; i++) {
            sb.append(allowedValues[i] + ", ");
        }
        return sb.append(allowedValues[allowedValues.length - 1] + "}").toString();
    }
    private boolean checkValidLetters(char[] toTest){
        boolean isTrue;
        for (char aToTest : toTest) {
            isTrue = false;
            for (char allowedValue : allowedValues){
                if (aToTest == allowedValue){
                    isTrue = true;
                    break;
                }
            }
            if (!isTrue){
                return false;
            }
        }
        return true;
    }
    private int multiplyChar(int pos){
        final int[] DOUBLE_LETTER = {3, 9, 15, 21, 27, 33, 39};
        final int[] TRIPLE_LETTER = {5, 10, 20, 25, 30, 35, 40};

        for (int aDOUBLE_LETTER : DOUBLE_LETTER) {
            if (pos == aDOUBLE_LETTER) {
                return 2;
            }
        }
        for (int aTRIPLE_LETTER : TRIPLE_LETTER){
            if (pos == aTRIPLE_LETTER){
                return 3;
            }
        }
        return 1;
    }
    private int multiplyWord(int pos){
        final int[] DOUBLE_WORD = {7, 14, 28};
        final int[] TRIPLE_WORD = {8, 16, 24, 32};

        for (int aDOUBLE_WORD : DOUBLE_WORD){
            if (pos == aDOUBLE_WORD){
                return 2;
            }
        }
        for (int aTRIPLE_WORD : TRIPLE_WORD){
            if (pos == aTRIPLE_WORD){
                return 3;
            }
        }
        return 1;
    }
}

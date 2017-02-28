// Matthew Krawczyk
// APCS Period 4
// 2/24/17

package com.Mattk4355;

import java.util.Scanner;

public final class Mowing {
    private String[][] lawn;
    public static void main(String[] args) {
        new Mowing().run();
    }
    private void run(){
        final Scanner input = new Scanner(System.in);
        OrderedPair input0;
        int w, h, startX, startY, treeX, treeY, numTrees;
        do {
            System.out.print("Enter lawn dimensions (in the form width,length): ");
            input0 = OrderedPair.decode(input.next());
        }
        while ((input0 == null) || ((w = input0.posX) < 3 & (h = input0.posY) < 3));

        lawn = new String[w][h];

        do{
            System.out.print("Input starting value (int the form vertical, horizontal): ");
            input0 = OrderedPair.decode(input.next());
        }
        while ((input0 == null) || (!inBound(startX = input0.posX, true)) & !inBound(startY = input0.posY, false));

        if (lawn.length >= 4 || lawn[0].length >= 4){
            System.out.print("Input trees? (Y/N): ");
            String trees = input.next();

            if (trees.equalsIgnoreCase("Y") || trees.equalsIgnoreCase("y")){
                do {
                    System.out.print("How many trees to add?: ");
                    numTrees = input.nextInt();
                }
                while (numTrees <= 0);

                while (numTrees > 0){
                    System.out.print("Enter coordinate of tree (in the form vertical, horizontal)");
                    if ((input0 = OrderedPair.decode(input.next())) != null){
                        if (inBoundTree(treeX = input0.posX, true) & inBoundTree(treeY = input0.posY, false)){
                            addTree(treeX, treeY);
                            numTrees--;
                        }
                    }
                }
            }
        }
        fill();

        printLawn("BLANK:");
        horizMove(startX, startY);
        printLawn("MOWED:");
    }
    private boolean inBound(int val, boolean horiz){
        return horiz ? (val > 1 && val < lawn.length - 1) : (val > 1 && val < lawn[0].length);
    }
    private boolean inBoundTree(int val, boolean horiz){
        return horiz ? (val > 0 && val < lawn.length) : (val > 0 && val < lawn[0].length);
    }
    private void addTree(int posX, int posY){
        lawn[posX][posY] = "T";
    }

    private void fill(){
        for (int i = 0; i < lawn.length; i++){
            for (int j = 0; j < lawn[0].length; j++) {
                lawn[i][j] = ".";
            }
        }
    }
    private void printLawn(String x){
        System.out.println(x);
        for (int i = 0; i < lawn[0].length; i++) {
            switch (i){
                case 0: System.out.print("  " + i + " "); break;
                default: System.out.print(" " + i + " ");
            }
        }
        System.out.println();
        int i = 0;
        for (String[] s : lawn){
            System.out.print(i++);
            for (String s0 : s){
                System.out.print(" " + s0 + " ");
            }
            System.out.println();
        }
    }
    private void horizMove(int posX, int posY){
        for (int i = 0; i + posX < lawn.length - 1; i++){
            if (canMow(posX + i, posY)){
                validate(posX + i, posY);
            }
        }
        for (int i = 0; i + posX > 0; i--){
            if (canMow(posX + i, posY)){
                validate(posX + i, posY);
            }
        }
        for (int i = 0; i + posX < lawn.length - 1; i++){
            checkVert(posX + i, posY);
        }
        for (int i = 0; i + posX > 0; i--){
            checkVert(posX + i, posY);
        }
    }
    private void checkVert(int posX, int posY){
        for (int i = 0; i + posY < lawn[0].length - 1; i++){
            if (canMow(posX, posY + i)){
                validate(posX, posY + i);
            }
        }
        for (int i = 0; i + posY > 0; i--){
            if (canMow(posX, posY + i)){
                validate(posX, posY + i);
            }
        }
        for (int i = 0; i + posY < lawn[0].length - 1; i++){
            if (!isValidated(posX, posY + i) && canMow(posX, posY + i)){
                checkHoriz(posX, posY + i);
            }
        }
        for (int i = 0; i + posY > 0; i--){
            if (!isValidated(posX, posY + i) && canMow(posX, posY + i)){
                checkHoriz(posX, posY + i);
            }
        }

    }
    private void checkHoriz(int posX, int posY){
        for (int i = 0; i + posX < lawn.length - 1; i++){
            if (canMow(posX + i, posY)){
                validate(posX + i, posY);
            }
        }
        for (int i = 0; i + posX > 0; i--){
            if (canMow(posX + i, posY)){
                validate(posX + i, posY);
            }
        }

        for (int i = 0; i + posX < lawn.length - 1; i++){
            if (!isValidated(posX + i, posY) && canMow(posX + i, posY)){
                checkVert(posX + i, posY);
            }
        }
        for (int i = 0; i + posX > 0; i--){
            if (!isValidated(posX + i, posY) && canMow(posX + i, posY)){
                checkVert(posX + i, posY);
            }
        }
    }
    private void validate(int x, int y){
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                lawn[x + i][y + j] = "C";
            }
        }
    }
    private boolean isValidated(int x, int y){
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (!(lawn[x + i][y + j].equals("C"))){
                    return false;
                }
            }
        }
        return true;
    }
    private boolean canMow(int x, int y){
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (lawn[x + i][y + j].equals("T")){
                    return false;
                }
            }
        }
        return true;
    }
    private static final class OrderedPair{
        private final int posX;
        private final int posY;

        private OrderedPair(int posX, int posY){
            this.posX = posX;
            this.posY = posY;
        }

        private static OrderedPair decode(String s){
            try{
                final String[] temp = s.split(",");
                final int x = Integer.parseInt(temp[0]);
                final int y = Integer.parseInt(temp[1]);

                return new OrderedPair(x, y);
            }
            catch (Exception x){
                return null;
            }
        }
    }
}
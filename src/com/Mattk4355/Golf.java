package com.Mattk4355;

import java.util.Scanner;

public class Golf {
    private Golfer golfer1, golfer2;
    public static void main(String[] args) {
        Golf g = new Golf();
        g.run();
    }
    private void run(){
        Scanner input = new Scanner(System.in);
        final int hole1par, hole2par, hole3par, hole4par, hole5par, hole6par, hole7par, hole8par, hole9par;
        int tempHole1Score, tempHole2Score, tempHole3Score, tempHole4Score, tempHole5Score, tempHole6Score, tempHole7Score, tempHole8Score, tempHole9Score;

        //hole par
        System.out.print("Enter hole 1 par: ");
        hole1par = input.nextInt();
        System.out.print("Enter hole 2 par: ");
        hole2par = input.nextInt();
        System.out.print("Enter hole 3 par: ");
        hole3par = input.nextInt();
        System.out.print("Enter hole 4 par: ");
        hole4par = input.nextInt();
        System.out.print("Enter hole 5 par: ");
        hole5par = input.nextInt();
        System.out.print("Enter hole 6 par: ");
        hole6par = input.nextInt();
        System.out.print("Enter hole 7 par: ");
        hole7par = input.nextInt();
        System.out.print("Enter hole 8 par: ");
        hole8par = input.nextInt();
        System.out.print("Enter hole 9 par: ");
        hole9par = input.nextInt();

        //Player 1
        System.out.print("Enter Hole 1 Score for player 1: ");
        tempHole1Score = input.nextInt();
        System.out.print("Enter Hole 2 Score for player 1: ");
        tempHole2Score = input.nextInt();
        System.out.print("Enter Hole 3 Score for player 1: ");
        tempHole3Score = input.nextInt();
        System.out.print("Enter Hole 4 Score for player 1: ");
        tempHole4Score = input.nextInt();
        System.out.print("Enter Hole 5 Score for player 1: ");
        tempHole5Score = input.nextInt();
        System.out.print("Enter Hole 6 Score for player 1: ");
        tempHole6Score = input.nextInt();
        System.out.print("Enter Hole 7 Score for player 1: ");
        tempHole7Score = input.nextInt();
        System.out.print("Enter Hole 8 Score for player 1: ");
        tempHole8Score = input.nextInt();
        System.out.print("Enter Hole 9 Score for player 1: ");
        tempHole9Score = input.nextInt();
        golfer1 = new Golfer("Player1", tempHole1Score, tempHole2Score, tempHole3Score, tempHole4Score, tempHole5Score, tempHole6Score, tempHole7Score, tempHole8Score, tempHole9Score);

        //Player 2
        System.out.print("Enter Hole 1 Score for player 2: ");
        tempHole1Score = input.nextInt();
        System.out.print("Enter Hole 2 Score for player 2: ");
        tempHole2Score = input.nextInt();
        System.out.print("Enter Hole 3 Score for player 2: ");
        tempHole3Score = input.nextInt();
        System.out.print("Enter Hole 4 Score for player 2: ");
        tempHole4Score = input.nextInt();
        System.out.print("Enter Hole 5 Score for player 2: ");
        tempHole5Score = input.nextInt();
        System.out.print("Enter Hole 6 Score for player 2: ");
        tempHole6Score = input.nextInt();
        System.out.print("Enter Hole 7 Score for player 2: ");
        tempHole7Score = input.nextInt();
        System.out.print("Enter Hole 8 Score for player 2: ");
        tempHole8Score = input.nextInt();
        System.out.print("Enter Hole 9 Score for player 2: ");
        tempHole9Score = input.nextInt();
        golfer2 = new Golfer("Player2", tempHole1Score, tempHole2Score, tempHole3Score, tempHole4Score, tempHole5Score, tempHole6Score, tempHole7Score, tempHole8Score, tempHole9Score);

        boolean player1won = false;
        int totalPar = hole1par + hole2par + hole3par + hole4par + hole5par + hole6par + hole7par + hole8par + hole9par;

        //1. Cumulative score
        if (golfer1.getTotalScore() > golfer2.getTotalScore()){
            System.out.println("1. " + golfer2.getTotalScore() + " " + golfer1.getTotalScore());
        }
        else {
            System.out.println("1. " + golfer1.getTotalScore() + " " + golfer2.getTotalScore());
            player1won = true;
        }

        if (player1won){
            System.out.println("2. " + printPar(golfer1.getTotalScore(), totalPar));
            System.out.println("3. " + printPar(golfer2.getTotalScore(), totalPar));
        }
        else{
            System.out.println("2. " + printPar(golfer2.getTotalScore(), totalPar));
            System.out.println("3. " + printPar(golfer1.getTotalScore(), totalPar));
        }

        //how many holes did the winner win?
        checkHolesWon();

        if (player1won){
            System.out.println("4. " + golfer1.getHolesWon());
        }
        else{
            System.out.println("4. " + golfer2.getHolesWon());
        }

        //highest hole total
        highestHoleTotal();
    }

    private String printPar(int totalScore, int totalPar){
        if (totalScore > totalPar){
            return (totalScore - totalPar) + " over par";
        }
        else if (totalScore < totalPar){
            return (totalPar - totalScore) + " under par";
        }
        return "par";
    }
    private void checkHolesWon(){
        //hole 1
        if (golfer1.getHole1score() > golfer2.getHole1score()){
            golfer2.addHoleWon();
        }
        else if (golfer2.getHole1score() > golfer1.getHole1score()){
            golfer1.addHoleWon();
        }
        //hole 2
        if (golfer1.getHole2score() > golfer2.getHole2score()){
            golfer2.addHoleWon();
        }
        else if (golfer2.getHole2score() > golfer1.getHole2score()){
            golfer1.addHoleWon();
        }
        //hole 3
        if (golfer1.getHole3score() > golfer2.getHole3score()){
            golfer2.addHoleWon();
        }
        else if (golfer2.getHole3score() > golfer1.getHole3score()){
            golfer1.addHoleWon();
        }
        //hole 4
        if (golfer1.getHole4score() > golfer2.getHole4score()){
            golfer2.addHoleWon();
        }
        else if (golfer2.getHole4score() > golfer1.getHole4score()){
            golfer1.addHoleWon();
        }
        //hole 5
        if (golfer1.getHole5score() > golfer2.getHole5score()){
            golfer2.addHoleWon();
        }
        else if (golfer2.getHole5score() > golfer1.getHole5score()){
            golfer1.addHoleWon();
        }
        //hole 6
        if (golfer1.getHole6score() > golfer2.getHole6score()){
            golfer2.addHoleWon();
        }
        else if (golfer2.getHole6score() > golfer1.getHole6score()){
            golfer1.addHoleWon();
        }
        //hole 7
        if (golfer1.getHole7score() > golfer2.getHole7score()){
            golfer2.addHoleWon();
        }
        else if (golfer2.getHole7score() > golfer1.getHole7score()){
            golfer1.addHoleWon();
        }
        //hole 8
        if (golfer1.getHole8score() > golfer2.getHole8score()){
            golfer2.addHoleWon();
        }
        else if (golfer2.getHole8score() > golfer1.getHole8score()) {
            golfer1.addHoleWon();
        }
        //hole 9
        if (golfer1.getHole9score() > golfer2.getHole9score()){
            golfer2.addHoleWon();
        }
        else if (golfer2.getHole9score() > golfer1.getHole9score()){
            golfer1.addHoleWon();
        }
    }
    private void highestHoleTotal(){
        int highestHoleTotal = 0, tempHoleTotal, hole = 0;

        tempHoleTotal = golfer1.getHole1score() + golfer2.getHole1score();
        if (tempHoleTotal > highestHoleTotal){
            hole = 1;
            highestHoleTotal = tempHoleTotal;
        }
        tempHoleTotal = golfer1.getHole2score() + golfer2.getHole2score();
        if (tempHoleTotal > highestHoleTotal){
            hole = 2;
            highestHoleTotal = tempHoleTotal;
        }
        tempHoleTotal = golfer1.getHole3score() + golfer2.getHole3score();
        if (tempHoleTotal > highestHoleTotal){
            hole = 3;
            highestHoleTotal = tempHoleTotal;
        }
        tempHoleTotal = golfer1.getHole4score() + golfer2.getHole4score();
        if (tempHoleTotal > highestHoleTotal){
            hole = 4;
            highestHoleTotal = tempHoleTotal;
        }
        tempHoleTotal = golfer1.getHole5score() + golfer2.getHole5score();
        if (tempHoleTotal > highestHoleTotal){
            hole = 5;
            highestHoleTotal = tempHoleTotal;
        }
        tempHoleTotal = golfer1.getHole6score() + golfer2.getHole6score();
        if (tempHoleTotal > highestHoleTotal){
            hole = 6;
            highestHoleTotal = tempHoleTotal;
        }
        tempHoleTotal = golfer1.getHole7score() + golfer2.getHole7score();
        if (tempHoleTotal > highestHoleTotal){
            hole = 7;
            highestHoleTotal = tempHoleTotal;
        }
        tempHoleTotal = golfer1.getHole8score() + golfer2.getHole8score();
        if (tempHoleTotal > highestHoleTotal){
            hole = 8;
            highestHoleTotal = tempHoleTotal;
        }
        tempHoleTotal = golfer1.getHole9score() + golfer2.getHole9score();
        if (tempHoleTotal > highestHoleTotal){
            hole = 9;
        }
        if (hole == 1){
            System.out.println("5. 1 " + (golfer1.getHole1score() + golfer2.getHole1score()));
        }
        else if (hole == 2){
            System.out.println("5. 2 " + (golfer1.getHole2score() + golfer2.getHole2score()));
        }
        else if (hole == 3){
            System.out.println("5. 3 " + (golfer1.getHole3score() + golfer2.getHole3score()));
        }
        else if (hole == 4){
            System.out.println("5. 4 " + (golfer1.getHole4score() + golfer2.getHole4score()));
        }
        else if (hole == 5){
            System.out.println("5. 5 " + (golfer1.getHole5score() + golfer2.getHole5score()));
        }
        else if (hole == 6){
            System.out.println("5. 6 " + (golfer1.getHole6score() + golfer2.getHole6score()));
        }
        else if (hole == 7){
            System.out.println("5. 7 " + (golfer1.getHole7score() + golfer2.getHole7score()));
        }
        else if (hole == 8){
            System.out.println("5. 8 " + (golfer1.getHole8score() + golfer2.getHole8score()));
        }
        else if (hole == 9){
            System.out.println("5. 9 " + (golfer1.getHole9score() + golfer2.getHole9score()));
        }
    }
}
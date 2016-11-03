// Matthew Krawczyk
// Period 4 APCS
// 10/31/16
package com.Mattk4355;

import java.util.Scanner;

public class Race {
    private Runner runner1, runner2, runner3, runner4, runner5, runner6, runner7;
    private String unit, fastestTimeName = "";

    public static void main(String[] args) {
        Race r = new Race();
        r.run();
    }
    public void run(){
        Scanner input = new Scanner(System.in);

        //set unit of measure
        System.out.print("Enter unit of time measure: ");
        unit = input.next();

        System.out.print("Input custom runners? (Yes/No): ");
        String temp = input.next();
        if (temp.equals("Yes")){
            setRunners();
        }
        else{
            setExampleRunners();
        }

        //overall winners
        checkFastestTime();
        setWon1("overall");
        checkFastestTime();
        setWon2("overall");

        //male winners
        checkFastestTime("male");
        setWon1("male");
        checkFastestTime("male");
        setWon2("male");

        //Female Winners
        checkFastestTime("female");
        setWon1("female");
        checkFastestTime("female");
        setWon2("female");

        //print winners
        printWinner("1st", "overall");
        printWinner("1st", "female");
        printWinner("1st", "male");
        printWinner("2nd", "male");
    }
    public void setRunners(){
        Scanner input = new Scanner(System.in);
        double tempTime;
        String tempGender;

        System.out.println("Gender is either male or female. Anything else WILL NOT WORK.");

        //enter runner 1 information
        System.out.print("Enter runner 1 time: ");
        tempTime = input.nextDouble();
        System.out.print("Enter runner 1 gender: ");
        tempGender = input.next();
        runner1 = new Runner(tempTime, tempGender);

        //enter runner 2 information
        System.out.print("Enter runner 2 time: ");
        tempTime = input.nextDouble();
        System.out.print("Enter runner 2 gender: ");
        tempGender = input.next();
        runner2 = new Runner(tempTime, tempGender);

        //enter runner 3 information
        System.out.print("Enter runner 3 time: ");
        tempTime = input.nextDouble();
        System.out.print("Enter runner 3 gender: ");
        tempGender = input.next();
        runner3 = new Runner(tempTime, tempGender);

        //enter runner 4 information
        System.out.print("Enter runner 4 time: ");
        tempTime = input.nextDouble();
        System.out.print("Enter runner 4 gender: ");
        tempGender = input.next();
        runner4 = new Runner(tempTime, tempGender);

        //enter runner 5 information
        System.out.print("Enter runner 5 time: ");
        tempTime = input.nextDouble();
        System.out.print("Enter runner 5 gender: ");
        tempGender = input.next();
        runner5 = new Runner(tempTime, tempGender);

        //enter runner 6 information
        System.out.print("Enter runner 6 time: ");
        tempTime = input.nextDouble();
        System.out.print("Enter runner 6 gender: ");
        tempGender = input.next();
        runner6 = new Runner(tempTime, tempGender);

        //enter runner 7 information
        System.out.print("Enter runner 7 time: ");
        tempTime = input.nextDouble();
        System.out.print("Enter runner 7 gender: ");
        tempGender = input.next();
        runner7 = new Runner(tempTime, tempGender);

        printRunners();
    }
    public void setExampleRunners(){
        runner1 = new Runner(46, "female");
        runner2 = new Runner(39, "female");
        runner3 = new Runner(41, "male");
        runner4 = new Runner(29, "male");
        runner5 = new Runner(30, "male");
        runner6 = new Runner(31, "female");
        runner7 = new Runner(53, "male");

        printRunners();
    }
    public void printRunners(){
        System.out.println("Runners: ");
        System.out.println("Runner 1: Time: " + runner1.getTime() + unit + " Gender: " + runner1.getGender());
        System.out.println("Runner 2: Time: " + runner2.getTime() + unit + " Gender: " + runner2.getGender());
        System.out.println("Runner 3: Time: " + runner3.getTime() + unit + " Gender: " + runner3.getGender());
        System.out.println("Runner 4: Time: " + runner4.getTime() + unit + " Gender: " + runner4.getGender());
        System.out.println("Runner 5: Time: " + runner5.getTime() + unit + " Gender: " + runner5.getGender());
        System.out.println("Runner 6: Time: " + runner6.getTime() + unit + " Gender: " + runner6.getGender());
        System.out.println("Runner 7: Time: " + runner7.getTime() + unit + " Gender: " + runner7.getGender());
    }
    public void checkFastestTime(){
        fastestTimeName = "";
        double fastestTime = 2147483647;

        if ((runner1.getTime() < fastestTime) && !(runner1.getHasWon())){
            fastestTime = runner1.getTime();
            fastestTimeName = "runner1";
        }
        if ((runner2.getTime() < fastestTime) && !(runner2.getHasWon())){
            fastestTime = runner2.getTime();
            fastestTimeName = "runner2";
        }
        if ((runner3.getTime() < fastestTime) && !(runner3.getHasWon())){
            fastestTime = runner3.getTime();
            fastestTimeName = "runner3";
        }
        if ((runner4.getTime() < fastestTime) && !(runner4.getHasWon())){
            fastestTime = runner4.getTime();
            fastestTimeName = "runner4";
        }
        if ((runner5.getTime() < fastestTime) && !(runner5.getHasWon())){
            fastestTime = runner5.getTime();
            fastestTimeName = "runner5";
        }
        if ((runner6.getTime() < fastestTime) && !(runner6.getHasWon())){
            fastestTime = runner6.getTime();
            fastestTimeName = "runner6";
        }
        if ((runner7.getTime() < fastestTime) && !(runner7.getHasWon())){
            fastestTimeName = "runner7";
        }
    }
    public void checkFastestTime(String gender){
        fastestTimeName = "";
        double fastestTime = 2147483647;

        if ((runner1.getTime() < fastestTime) && !(runner1.getHasWon()) && (runner1.getGender().equals(gender))){
            fastestTime = runner1.getTime();
            fastestTimeName = "runner1";
        }
        if ((runner2.getTime() < fastestTime) && !(runner2.getHasWon()) && (runner2.getGender().equals(gender))){
            fastestTime = runner2.getTime();
            fastestTimeName = "runner2";
        }
        if ((runner3.getTime() < fastestTime) && !(runner3.getHasWon()) && (runner3.getGender().equals(gender))){
            fastestTime = runner3.getTime();
            fastestTimeName = "runner3";
        }
        if ((runner4.getTime() < fastestTime) && !(runner4.getHasWon()) && (runner4.getGender().equals(gender))){
            fastestTime = runner4.getTime();
            fastestTimeName = "runner4";
        }
        if ((runner5.getTime() < fastestTime) && !(runner5.getHasWon()) && (runner5.getGender().equals(gender))){
            fastestTime = runner5.getTime();
            fastestTimeName = "runner5";
        }
        if ((runner6.getTime() < fastestTime) && !(runner6.getHasWon()) && (runner6.getGender().equals(gender))){
            fastestTime = runner6.getTime();
            fastestTimeName = "runner6";
        }
        if ((runner7.getTime() < fastestTime) && !(runner7.getHasWon()) && (runner7.getGender().equals(gender))){
            fastestTimeName = "runner7";
        }
    }
    public void setWon1(String bracket){
        if (fastestTimeName.equals("runner1")){
            runner1.setHasWon(true);
            runner1.setHasWonBracket("1st " + bracket);
        }
        if (fastestTimeName.equals("runner2")){
            runner2.setHasWon(true);
            runner2.setHasWonBracket("1st " + bracket);
        }
        if (fastestTimeName.equals("runner3")){
            runner3.setHasWon(true);
            runner3.setHasWonBracket("1st " + bracket);
        }
        if (fastestTimeName.equals("runner4")){
            runner4.setHasWon(true);
            runner4.setHasWonBracket("1st " + bracket);
        }
        if (fastestTimeName.equals("runner5")){
            runner5.setHasWon(true);
            runner5.setHasWonBracket("1st " + bracket);
        }
        if (fastestTimeName.equals("runner6")){
            runner6.setHasWon(true);
            runner6.setHasWonBracket("1st " + bracket);
        }
        if (fastestTimeName.equals("runner7")){
            runner7.setHasWon(true);
            runner7.setHasWonBracket("1st " + bracket);
        }
    }
    public void setWon2(String bracket){
        if (fastestTimeName.equals("runner1")){
            runner1.setHasWon(true);
            runner1.setHasWonBracket("2nd " + bracket);
        }
        if (fastestTimeName.equals("runner2")){
            runner2.setHasWon(true);
            runner2.setHasWonBracket("2nd " + bracket);
        }
        if (fastestTimeName.equals("runner3")){
            runner3.setHasWon(true);
            runner3.setHasWonBracket("2nd " + bracket);
        }
        if (fastestTimeName.equals("runner4")){
            runner4.setHasWon(true);
            runner4.setHasWonBracket("2nd " + bracket);
        }
        if (fastestTimeName.equals("runner5")){
            runner5.setHasWon(true);
            runner5.setHasWonBracket("2nd " + bracket);
        }
        if (fastestTimeName.equals("runner6")){
            runner6.setHasWon(true);
            runner6.setHasWonBracket("2nd " + bracket);
        }
        if (fastestTimeName.equals("runner7")){
            runner7.setHasWon(true);
            runner7.setHasWonBracket("2nd " + bracket);
        }
    }
    public void printWinner(String pos, String bracket){
        if (runner1.getHasWonBracket().equals(pos + " " + bracket)){
            System.out.println(pos + " " + bracket + ": " + runner1.getTime() + unit);
        }
        if (runner2.getHasWonBracket().equals(pos + " " + bracket)){
            System.out.println(pos + " " + bracket + ": " + runner2.getTime() + unit);
        }
        if (runner3.getHasWonBracket().equals(pos + " " + bracket)){
            System.out.println(pos + " " + bracket + ": " + runner3.getTime() + unit);
        }
        if (runner4.getHasWonBracket().equals(pos + " " + bracket)){
            System.out.println(pos + " " + bracket + ": " + runner4.getTime() + unit);
        }
        if (runner5.getHasWonBracket().equals(pos + " " + bracket)){
            System.out.println(pos + " " + bracket + ": " + runner5.getTime() + unit);
        }
        if (runner6.getHasWonBracket().equals(pos + " " + bracket)){
            System.out.println(pos + " " + bracket + ": " + runner6.getTime() + unit);
        }
        if (runner7.getHasWonBracket().equals(pos + " " + bracket)){
            System.out.println(pos + " " + bracket + ": " + runner7.getTime() + unit);
        }
    }
}
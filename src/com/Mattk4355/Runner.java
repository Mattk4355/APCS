// Matthew Krawczyk
// Period 4 APCS
// 10/31/16
package com.Mattk4355;

public class Runner {
    private double time;
    private boolean hasWon;
    private String gender;
    private String hasWonBracket;

    public Runner(double Time, String Gender){
        time = Time;
        gender = Gender;
        hasWonBracket = "";
        hasWon = false;
    }
    public void setHasWon(boolean HasWon) {
        hasWon = HasWon;
    }
    public void setHasWonBracket(String HasWonBracket) {
        hasWonBracket = HasWonBracket;
    }
    public double getTime() {
        return time;
    }
    public String getGender() {
        return gender;
    }
    public boolean getHasWon() {
        return hasWon;
    }
    public String getHasWonBracket() {
        return hasWonBracket;
    }
}
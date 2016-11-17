package com.Mattk4355;

import com.Mattk4355.LogHelper;

public class Golfer {
    private final int hole1score, hole2score, hole3score, hole4score, hole5score, hole6score, hole7score, hole8score, hole9score;
    private int holesWon = 0;
    private final String name;

    Golfer(String Name, int Hole1score, int Hole2score, int Hole3score, int Hole4score, int Hole5score, int Hole6score, int Hole7score, int Hole8score, int Hole9score){
        name = Name;
        hole1score = Hole1score;
        hole2score = Hole2score;
        hole3score = Hole3score;
        hole4score = Hole4score;
        hole5score = Hole5score;
        hole6score = Hole6score;
        hole7score = Hole7score;
        hole8score = Hole8score;
        hole9score = Hole9score;
    }
    public int getTotalScore(){
        return hole1score + hole2score + hole3score + hole4score + hole5score + hole6score + hole7score + hole8score + hole9score;
    }
    public void addHoleWon() {
        holesWon += 1;
    }
    public int getHolesWon() {
        return holesWon;
    }
    public String getName() {
        return name;
    }
    public int getHole1score() {
        return hole1score;
    }
    public int getHole2score() {
        return hole2score;
    }
    public int getHole3score() {
        return hole3score;
    }
    public int getHole4score() {
        return hole4score;
    }
    public int getHole5score() {
        return hole5score;
    }
    public int getHole6score() {
        return hole6score;
    }
    public int getHole7score() {
        return hole7score;
    }
    public int getHole8score() {
        return hole8score;
    }
    public int getHole9score() {
        return hole9score;
    }
}
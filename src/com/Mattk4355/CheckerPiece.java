package com.Mattk4355;

public class CheckerPiece {
    private final String name;
    private boolean isKing;

    public CheckerPiece(){
        name = "Blank piece";
    }
    public CheckerPiece(String Name, boolean IsKing){
        name = Name;
        isKing = IsKing;
    }
    public String getName() {
        return name;
    }
    public boolean isKing() {
        return isKing;
    }
    public void setIsKing(boolean IsKing) {
        isKing = IsKing;
    }
}
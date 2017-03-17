//Matthew Krawczyk
//Period 4 APCS
//02/27/2017
package com.Mattk4355.Farm;

public class Cow implements Animal {
    private String type;
    private String sound;

    public Cow(String type, String sound){
        this.type = type;
        this.sound = sound;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getSound() {
        return sound;
    }
}

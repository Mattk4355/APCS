//Matthew Krawczyk
//Period 4 APCS
//02/27/2017
package com.Mattk4355.Farm;

import java.util.Random;

public class Chick implements Animal{
    private String type;
    private String sound1;
    private String sound2;

    public Chick(String type, String sound1){
        this(type, sound1, null);
    }

    public Chick(String type, String sound1, String sound2){
        this.type = type;
        this.sound1 = sound1;
        this.sound2 = sound2;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getSound() {
        return (sound2 == null) ? sound1 : (new Random().nextInt(1) == 0) ? sound1 : sound2;
    }
}

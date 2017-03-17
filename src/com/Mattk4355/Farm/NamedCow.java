//Matthew Krawczyk
//Period 4 APCS
//02/27/2017
package com.Mattk4355.Farm;

public class NamedCow extends Cow {
    private String name;
    public NamedCow(String type, String sound, String name){
        super(type, sound);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

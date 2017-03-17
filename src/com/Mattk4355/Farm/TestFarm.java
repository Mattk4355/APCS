//Matthew Krawczyk
//Period 4 APCS
//02/27/2017
package com.Mattk4355.Farm;

public class TestFarm {
    public static void main(String[] args) {
        Cow c = new Cow("Cow", "moo");
        print(c);

    }
    private static void print(Animal a){
        System.out.println(a.getType() + " goes " + a.getSound());
    }
}

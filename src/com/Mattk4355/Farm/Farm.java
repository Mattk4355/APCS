//Matthew Krawczyk
//Period 4 APCS
//02/27/2017
package com.Mattk4355.Farm;

import java.util.ArrayList;
public class Farm {
    private final ArrayList<Animal> animals;
    private Farm(){
        animals = new ArrayList<>();
        animals.add(new NamedCow("Cow", "moo", "Elise"));
        animals.add(new Chick("Chick", "cluck"));
        animals.add(new Pig("Pig", "oink"));
        animals.add(new Chick("Chick", "cheep", "cluck"));
    }

    public static void main(String[] args) {
        Farm f = new Farm();
        f.animalSounds();
    }
    private void animalSounds(){
        for (Animal a : animals){
            System.out.println(a.getType() + " goes " + a.getSound());
        }
        System.out.println("The cow is known as: " + ((NamedCow) animals.get(3)).getName());
    }
}
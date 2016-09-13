package com.Mattk4355;

import java.util.Scanner;

public class MadLibs {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Choose a number (valid options are 1,2,3): ");
        int x = input.nextInt();

        switch (x){
            case 1: MadLib1();
                break;
            case 2: MadLib2();
                break;
            case 3: MadLib3();
                break;
            default: System.out.print("Invalid number entered.");
                break;
        }
    }
    public static void MadLib1(){
        Scanner input = new Scanner(System.in);

        System.out.print("Silly word:");
        String word1 = input.nextLine();

        System.out.print("Last Name: ");
        String word2 = input.nextLine();

        System.out.print("Illness: ");
        String word3 = input.nextLine();

        System.out.print("Noun (Plural): ");
        String word4 = input.nextLine();

        System.out.print("Adjective: ");
        String word5 = input.nextLine();

        System.out.print("Adjective: ");
        String word6 = input.nextLine();

        System.out.print("Silly word: ");
        String word7 = input.nextLine();

        System.out.print("Place: ");
        String word8 = input.nextLine();

        System.out.print("Number: ");
        String word9 = input.nextLine();

        System.out.print("Adjective: ");
        String word10 = input.nextLine();

        System.out.println("");
        System.out.println("Mad Lib 1: Sick Note");
        System.out.println("");

        System.out.println("Dear School Nurse:");
        System.out.println(word1 + " " + word2 + " will not be attending school ");
        System.out.println("today. He/she has come down with a case of " + word3 + " and has ");
        System.out.println("horrible " + word4 + " and a/an" + word5 + " fever. We");
        System.out.println("have made an appointment with the " + word6 + "Dr.");
        System.out.println(word7 + ", who studied for many years in " + word8);
        System.out.println("and has " + word9 + "degrees in pediatrics. He will send you all the ");
        System.out.println("information you need. Thank you!");
        System.out.println("Sincerely");
        System.out.println("Mrs. " + word10);
    }
    public static void MadLib2(){
        Scanner input = new Scanner(System.in);

        System.out.print("Adjective: ");
        String word1 = input.nextLine();

        System.out.print("Adjective: ");
        String word2 = input.nextLine();

        System.out.print("Celebrity: ");
        String word3 = input.nextLine();

        System.out.print("Celebrity: ");
        String word4 = input.nextLine();

        System.out.print("Animal: ");
        String word5 = input.nextLine();

        System.out.print("Verb: ");
        String word6 = input.nextLine();

        System.out.print("Noun: ");
        String word7 = input.nextLine();

        System.out.print("Noun: ");
        String word8 = input.nextLine();

        System.out.print("Part of body: ");
        String word9 = input.nextLine();

        System.out.print("Adverb: ");
        String word10 = input.nextLine();

        System.out.print("Adjective: ");
        String word11 = input.nextLine();

        System.out.print("Adverb: ");
        String word12 = input.nextLine();

        System.out.print("Adjective: ");
        String word13 = input.nextLine();

        System.out.print("Adjective: ");
        String word14 = input.nextLine();

        System.out.print("Part of body: ");
        String word15 = input.nextLine();

        System.out.print("Adjective: ");
        String word16 = input.nextLine();

        System.out.print("Noun: ");
        String word17 = input.nextLine();

        System.out.print("Noun: ");
        String word18 = input.nextLine();

        System.out.print("Person in room(male):");
        String word19 = input.nextLine();

        System.out.println("");
        System.out.println("Mad Lib 2: My Dream Man");
        System.out.println("");

        System.out.println("My \"Dream Man\" should, first of all be very " + word1 + " and ");
        System.out.println(word2 + " . He should have a physique like " + word3 + ", a");
        System.out.println("profile like " + word4 + ", and the intelligence of a/an ");
        System.out.println(word5 + ". He must be polite and must always remember to");
        System.out.println(word6 + " my " + word7 + ", to tip his ");
        System.out.println(word8 + " and to make my " + word9 + " when crossing the ");
        System.out.println("street. He should move " + word10 + ", have a/an ");
        System.out.println(word11 + " voice, and should always dress " + word12 + ".");
        System.out.println("I would also like him to be a/an " + word13 + " dancer, and when we ");
        System.out.println("are alone he should whisper " + word14 + " nothings into my");
        System.out.println(word15 + " and hold my " + word16);
        System.out.println(word17 + ". I know a/an " + word18 + " is hard to find. In ");
        System.out.println("fact the only one I can think of is " + word19);

    }
    public static void MadLib3(){
        Scanner input = new Scanner(System.in);

        System.out.print("Noun (plural): ");
        String word1 = input.nextLine();

        System.out.print("Place: ");
        String word2 = input.nextLine();

        System.out.print("Noun: ");
        String word3 = input.nextLine();

        System.out.print("Noun (plural): ");
        String word4 = input.nextLine();

        System.out.print("Noun: ");
        String word5 = input.nextLine();

        System.out.print("Adjective: ");
        String word6 = input.nextLine();

        System.out.print("Verb: ");
        String word7 = input.nextLine();

        System.out.print("Number: ");
        String word8 = input.nextLine();

        System.out.print("Adjective: ");
        String word9 = input.nextLine();

        System.out.print("Body part: ");
        String word10 = input.nextLine();

        System.out.print("Verb: ");
        String word11 = input.nextLine();

        System.out.println("");
        System.out.println("Mad Lib 3: Romeo and Juliet: Prologue");
        System.out.println("");

        System.out.println("Two " + word1 + ", both alike in dignity, ");
        System.out.println("In fair " + word2 + ", where we lay our scene,");
        System.out.println("From ancient " + word3 + "   break to new mutiny,");
        System.out.println("Where civil blood makes civil hands unclean.");
        System.out.println("From forth the fatal loins of these two foes");
        System.out.println("A pair of star-cross`d " + word4 + " take their life;");
        System.out.println("Whole misadventured piteous overthrows");
        System.out.println("Do with their " + word5 + " bury their parents` strife.");
        System.out.println("The fearful passage of their " + word6 + " love,");
        System.out.println("And the continuance of their parents` rage,");
        System.out.println("Which, but their children`s end, nought could " + word7);
        System.out.println("Is now the " + word8 + " hours` traffic of our stage;");
        System.out.println("The which if you with " + word9 + " " + word10 + " attend,");
        System.out.println("What here shall " + word11 + ", our toil shall strive to mend.");
    }
}

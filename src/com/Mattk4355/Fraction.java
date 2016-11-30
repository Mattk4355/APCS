// Matthew Krawczyk
// Period 4 APCS
// 11/28/16
package com.Mattk4355;

public class Fraction {
    private int numerator;
    private int denominator;

    public Fraction(int Numerator, int Denominator){
        numerator = Numerator;
        denominator = Denominator;
    }
    public int getDenominator() {
        return denominator;
    }
    public int getNumerator() {
        return numerator;
    }
    public void add(Fraction fraction1, Fraction fraction2){
        if (fraction1.getDenominator() == fraction2.getDenominator()){
            simplify((fraction1.getNumerator() + fraction2.getNumerator()), fraction1.getDenominator());
        }
        else{
            simplify(((fraction1.getNumerator() * fraction2.getDenominator()) + (fraction2.getNumerator() * fraction1.getDenominator())), (fraction1.getDenominator() * fraction2.getDenominator()));
        }
    }
    public void subtract(Fraction fraction1, Fraction fraction2){
        if (fraction1.getDenominator() == fraction2.getDenominator()){
            simplify((fraction1.getNumerator() - fraction2.getNumerator()), fraction1.getDenominator());
        }
        else{
            simplify(((fraction1.getNumerator() * fraction2.getDenominator()) - (fraction2.getNumerator() * fraction1.getDenominator())), (fraction1.getDenominator() * fraction2.getDenominator()));
        }
    }
    public void multiply(Fraction fraction1, Fraction fraction2){
        simplify((fraction1.getNumerator() * fraction2.getNumerator()), (fraction1.getDenominator() * fraction2.getDenominator()));
    }
    public void divide(Fraction fraction1, Fraction fraction2){
        simplify((fraction1.getNumerator() * fraction2.getDenominator()), (fraction1.getDenominator() * fraction2.getNumerator()));
    }
    public void simplify(int numerator, int denominator){
        while(true){
            if (denominator % numerator == 0){
                denominator /= numerator;
                numerator = 1;
                break;
            }
            else if ((numerator % 2 == 0) && (denominator % 2 == 0)){
                numerator /= 2;
                denominator /= 2;
            }
            else if ((numerator % 3 == 0) && (denominator % 3 == 0)){
                numerator /= 3;
                denominator /= 3;
            }
            else{
                break;
            }
        }
        if (denominator == 0){
            System.out.println("Undefined");
        }
        else{
            System.out.println(numerator + "/" + denominator);
        }
    }
}
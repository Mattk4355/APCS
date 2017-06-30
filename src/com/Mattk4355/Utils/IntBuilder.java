package com.Mattk4355.Utils;

@SuppressWarnings({"WeakerAccess", "unused"})
public final class IntBuilder {
    private int[] values;
    private final int value;
    private boolean negate;

    public IntBuilder(){
        this(0);
    }
    public IntBuilder(int value){
        this.value = value;
        values = new int[0];
    }
    public IntBuilder(IntBuilder other){
        this(Math.abs(other.getValue()));
    }

    /**
     * Negates the current value of this <code> IntBuilder </code>
     */
    public final void negate(){
        negate = !negate;
    }

    /**
     * Appends the specified integer to the end of the number.
     *
     * @param i the value to append
     */
    public final void append(int i){
        if (i < 0){
            throw new IllegalArgumentException("Cannot add a negative number");
        }
        if (i >= 0 && i <= 9){
            appendSingleDigit(i);
        }
        else{
            appendMultiDigit(i);
        }
    }

    /**
     * Appends the specified integers from the array to the end of the number.
     *
     * @param ints the value(s) to append
     */
    public final void append(int[] ints){
        append(ints, 0, ints.length);
    }
    public final void append(int[] ints, int from, int to){
        if (from < to){
            throw new IllegalArgumentException("from < to: " + from + " < " + to);
        }
        if (from < 0){
            throw new IllegalArgumentException("from < 0: " + from + " < 0");
        }
        if (to > ints.length){
            throw new IllegalArgumentException("to > ints.length: " + to + " > " + ints.length);
        }
        for (int i = from; i < to; i++) {
            append(ints[i]);
        }
    }

    /**
     * Appends the specified integer (converted from a String) to the end of the number.
     *
     * @param s the value to append
     */
    public void append(String s){
        append(Integer.parseInt(s));
    }

    /**
     * Returns the value of this <code> IntBuilder </code>
     *
     * @return the value of this <code> IntBuilder </code>
     */
    public final int getValue(){
        int temp = 0;
        for (int i = 0, j = values.length - 1; i < values.length; i++, j--) {
            temp += (((int) Math.pow(10, j)) * values[i]);
        }
        int val = temp + value;
        return negate ? val : -val;
    }

    private void appendSingleDigit(int i){
        values = Utils.Arrays.copyOf(values, values.length + 1);
        values[values.length - 1] = i;
    }

    private void appendMultiDigit(int i){
        int temp = i, count = 0, divisor;
        while (temp / 10 > 0){
            temp /= 10;
            count++;
        }
        for (int j = count; j >= 0; j--) {
            divisor = (int) Math.pow(10, j);
            appendSingleDigit(i / divisor);
            i -= ((i / divisor) * divisor);
        }
    }

    @Override
    public final String toString() {
        return "Size = " + (values.length - 1) + " Value = " + this.getValue();
    }
}
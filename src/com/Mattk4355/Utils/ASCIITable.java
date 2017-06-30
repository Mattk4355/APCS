package com.Mattk4355.Utils;

@SuppressWarnings({"WeakerAccess", "unused"})
public class ASCIITable {
    private ASCIITable(){}

    private static final char[] CHAR_VALUES;
    private static final char[] TRIMMED_CHAR_VALUES;

    public static int TABLE_SIZE = 128;

    /**
     * @return the ASCII int value of {@code c}
     */
    public static int toASCII(char c){
        return (int) c;
    }

    /**
     * @param ascii a number between 0 and 127
     * @return return the character value of {@code ascii}
     *
     * @throws IllegalArgumentException id {@code ascii}
     *         is less than 0 or greater than 127
     */
    public static char toChar(int ascii){
        if (ascii < 0 || ascii > TABLE_SIZE - 1) throw new IllegalArgumentException("Not a valid ascii integer: " + ascii);
        return CHAR_VALUES[ascii];
    }

    /**
     * @return the ASCII table from 0 to 127
     */
    public static char[] getASCIITable(){
        return Utils.Arrays.copyOf(CHAR_VALUES);
    }

    /**
     * @return the ASCII table from 0 to 127, with values that cannot
     * be displayed removed (the index is left blank).
     */
    public static char[] getReadableTable(){
        return Utils.Arrays.copyOf(TRIMMED_CHAR_VALUES);
    }

    static {
        Timer t = new Timer().start();
        //fill table
        CHAR_VALUES = new char[TABLE_SIZE];
        for (int i = 0, j = -9; i < TABLE_SIZE; i++, j++) CHAR_VALUES[i] = ((char)('\t' + j));

        //fill readable table
        TRIMMED_CHAR_VALUES = new char[TABLE_SIZE];
        final int offset = 32;
        System.arraycopy(CHAR_VALUES, offset, TRIMMED_CHAR_VALUES, offset, TABLE_SIZE - offset);
        t.stop().printTimeSec("ASCIITable init completed in ");
    }
}
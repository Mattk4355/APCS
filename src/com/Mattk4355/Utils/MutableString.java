package com.Mattk4355.Utils;

import com.Mattk4355.Utils.Utils.Objects;

import java.util.Iterator;
import java.util.NoSuchElementException;

@SuppressWarnings({"WeakerAccess", "unused"})
public final class MutableString
        implements CharSequence, Comparable<MutableString>, Iterable<Character>, CanClone<MutableString>/*, Serializable*/ {
    /**
     * The characters of this {@code MutableString}
     */
    private /*transient*/ volatile char[] chars;

    /**
     * The hash code of this {@code MutableString} the last time it was updated
     */
    private /*transient*/ int hash;

    /**
     * The length of this {@code MutableString} the last time it was updated
     */
    private /*transient*/ int length;

    /** The object on which instance methods will lock */
    private final Lock LOCK;

    /** The object on which class methods will lock */
    private static final Lock STATIC_LOCK = new Lock();

    /** Lock class */
    private static final class Lock{}

    //Constructors

    public MutableString(){
        LOCK = new Lock();
        this.chars = "".toCharArray();
    }

    public MutableString(char[] chars){
        LOCK = new Lock();
        Objects.requireNonNull(chars);
        this.chars = chars;
    }

    /**
     * If this constructor is used, it is recommended that the char array is obtained by calling the getChars() method
     */
    public MutableString(String s){
        LOCK = new Lock();
        Objects.requireNonNull(s);
        this.chars = s.toCharArray();
    }

    /**
     * If this constructor is used, it is recommended that the char array is obtained by calling the getChars() method
     */
    public MutableString(String s, int start, int end){
        this(s.toCharArray(), start, end);
    }

    /**
     * If this constructor is used, it is recommended that the char array is obtained by calling the getChars() method
     */
    public MutableString(MutableString other, int start, int end){
        this(other.toCharArray(), start, end);
    }

    public MutableString(char[] chars, int start, int end){
        LOCK = new Lock();
        Objects.requireNonNull(chars);
        if (start < 0 || start > chars.length || start > end) throw new IndexOutOfBoundsException("Illegal start index: " + start);
        if (end > chars.length || end < 0 || end < start) throw new IndexOutOfBoundsException("Illegal end index: " + end);
        if (start == 0 && chars.length == end){
            this.chars = chars;
            return;
        }
        char[] tmp = new char[end - start];
        for (int i = start, j = 0; i < end; i++, j++){
            tmp[j] = chars[i];
        }
        this.chars = tmp;
    }

    /**
     * @return the number of characters in this {@code MutableString}
     */
    @Override
    public final int length(){
        synchronized (LOCK){
            int len = getLength();
            if (len != length) length = len;
            return len;
        }
    }
    private int getLength(){
        return chars.length;
    }

    /**
     * @return the character at {@code index}
     *
     * @throws IllegalArgumentException if the index was out of bounds
     */
    @Override
    public final char charAt(int index) {
        synchronized (LOCK){
            if (index < 0 || index > this.length()) throw new IndexOutOfBoundsException("Illegal index: " + index);
            return chars[index];
        }
    }

    /**
     * @return a String containing the characters of this {@code MutableString}
     */
    @Override
    public final String toString() {
        return new String(this.getChars());
    }

    /**
     * Replaces all occurrences of {@code replace} with {@code with}
     *
     * @param replace the value to replace
     * @param with the value with which to replace it
     */
    public final void replaceAll(char replace, char with){
        synchronized (LOCK){
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] == replace) chars[i] = with;
            }
        }
    }

    /**
     * Replaces all occurrences of {@code replace} with {@code with}
     *
     * @param replace the value to replace
     * @param with the value with which to replace it
     */
    public final void replace(char replace, char with){
        synchronized (LOCK){
            replace0(replace, with, 1);
        }
    }

    /**
     * Replaces the {@code occurrence}th occurrences of {@code replace} with {@code with}
     *
     * @param occurrence which occurrence to replace
     * @param replace the value to replace
     * @param with the value with which to replace it
     */
    public final void replace(char replace, char with, int occurrence){
        synchronized (LOCK){
            replace0(replace, with, occurrence);
        }
    }

    private void replace0(char replace, char with, int occurrence){
        int x;
        if (occurrence < 1 || indexOf(replace) < 0 || (x = occurs(replace)) <= 0 || x < occurrence) return;
        for (int i = 0, j = 0; i < chars.length; i++) {
            if (chars[i] == replace) j++;
            if (j == occurrence){
                chars[i] = with;
                return;
            }
        }
    }

    /**
     * Replaces all occurrences of {@code replace} with {@code with}
     *
     * @param replace the value to replace
     * @param with the value with which to replace it
     */
    public final void replaceAll(String replace, String with){
        synchronized (LOCK){
            replaceAll0(replace.toCharArray(), with.toCharArray());
        }
    }

    /**
     * Replaces all occurrences of {@code replace} with {@code with}
     *
     * @param replace the value to replace
     * @param with the value with which to replace it
     */
    public final void replaceAll(char[] replace, char[] with){
        synchronized (LOCK){
            replaceAll0(replace, with);
        }
    }

    private void replaceAll0(char[] replace, char[] with){
        if (replace.length == 1 && with.length == 1){ //fast path for single chars
            replaceAll(replace[0], with[0]);
            return;
        }
        if (replace.length == with.length){ //fast path
            fastReplaceAll(replace, with);
            return;
        }
        for (int i = 0; i < chars.length - replace.length + 1; i++) {
            if (regionMatch(replace, i)){
                int diff = with.length - replace.length;
                char[] new0 = new char[chars.length + diff];
                System.arraycopy(chars, 0, new0, 0, i);
                System.arraycopy(with, 0, new0, i, with.length);
                System.arraycopy(chars, i + replace.length, new0, i + with.length,
                        chars.length - replace.length - i);
                this.chars = new0;
            }
        }
    }

    private void fastReplaceAll(char[] replace, char[] with){
        assert replace.length == with.length;
        for (int i = 0; i < chars.length - 1; i++) {
            if (regionMatch(replace, i)) regionReplace(with, i);
        }
    }

    /**
     * Replaces the first occurrence of {@code replace} with {@code with}
     *
     * @param replace the value to replace
     * @param with the value with which to replace it
     */
    public final void replace(String replace, String with){
        synchronized (LOCK){
            replace0(replace.toCharArray(), with.toCharArray(), 1);
        }
    }

    /**
     * Replaces the first occurrence of {@code replace} with {@code with}
     *
     * @param replace the value to replace
     * @param with the value with which to replace it
     */
    public final void replace(char[] replace, char[] with){
        synchronized (LOCK){
            replace0(replace, with, 1);
        }
    }
    /**
     * Replaces the {@code occurrence}th occurrence of {@code replace} with {@code with}
     *
     * @param replace the value to replace
     * @param with the value with which to replace it
     */
    public final void replace(String replace, String with, int occurrence){
        synchronized (LOCK){
            replace0(replace.toCharArray(), with.toCharArray(), occurrence);
        }
    }

    /**
     * Replaces the {@code occurrence}th occurrence of {@code replace} with {@code with}
     *
     * @param replace the value to replace
     * @param with the value with which to replace it
     */
    public final void replace(char[] replace, char[] with, int occurrence){
        synchronized (LOCK){
            replace0(replace, with, occurrence);
        }
    }

    private void replace0(char[] replace, char[] with, int occurrence){
        int x;
        if (occurrence < 1 || indexOf(replace) < 0 || (x = occurs(replace)) <= 0 || x < occurrence) return;
        if (replace.length == 1 && with.length == 1){ //fast path for single char
            replace(replace[0], with[0], occurrence);
            return;
        }
        if (replace.length == with.length){ //fast path
            fastReplace(replace, with, occurrence);
            return;
        }
        for (int i = 0, j = 0; i < chars.length - replace.length; i++) {
            if (regionMatch(replace, i)) j++;
            if (j == occurrence){
                int diff = with.length - replace.length;
                char[] new0 = new char[chars.length + diff];
                System.arraycopy(chars, 0, new0, 0, i);
                System.arraycopy(with, 0, new0, i, with.length);
                System.arraycopy(chars, i + replace.length, new0, i + with.length,
                        chars.length - replace.length - i);
                this.chars = new0;
                return;
            }
        }
    }

    private void fastReplace(char[] replace, char[] with, int occurrence){
        assert replace.length == with.length;
        for (int i = 0, j = 0; i < chars.length - replace.length; i++) {
            if (regionMatch(replace, i)) j++;
            if (j == occurrence){
                regionReplace(with, i);
                return;
            }
        }
    }

    private void regionReplace(char[] c, int start){
        for (int i = start, j = 0; i < start + c.length; i++, j++) {
            chars[i] = c[j];
        }
    }

    private int occurs(char c){
        int i = 0;
        for (char x : chars){
            if (x == c) i++;
        }
        return i;
    }

    private int occurs(char[] c){
        int i = 0;
        for (int j = 0; j < chars.length - c.length; j++) {
            if (regionMatch(c, j)) i++;
        }
        return i;
    }

    /**
     * @param c the character
     * @return the index of c, or -1 if it does not exist
     */
    public final int indexOf(char c){
        synchronized (LOCK){
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] == c) return i;
            }
            return -1;
        }
    }

    /**
     * @param c the character
     * @return the last index of c, or -1 if it does not exist
     */
    public final int lastIndexOf(char c){
        synchronized (LOCK){
            for (int i = chars.length - 1; i >= 0; i--) {
                if (chars[i] == c) return i;
            }
            return -1;
        }
    }

    /**
     * @param s the string
     * @return the index of s, or -1 if it does not exist
     */
    public final int indexOf(String s){
        synchronized (LOCK){
            return indexOf(s.toCharArray());
        }
    }

    /**
     * @param s the string
     * @return the last index of s, or -1 if it does not exist
     */
    public final int lastIndexOf(String s){
        synchronized (LOCK){
            return lastIndexOf(s.toCharArray());
        }
    }

    /**
     * @param c the character array
     * @return the index of c, or -1 if it does not exist
     */
    public final int indexOf(char[] c){
        synchronized (LOCK){
            for (int i = 0; i < chars.length - c.length; i++) {
                if (regionMatch(c, i)){
                    return i;
                }
            }
            return -1;
        }
    }

    /**
     * @param c the character array
     * @return the last index of c, or -1 if it does not exist
     */
    public final int lastIndexOf(char[] c){
        synchronized (LOCK){
            for (int i = chars.length - c.length - 1; i >= 0; i--) {
                if (regionMatch(c, i)){
                    return i;
                }
            }
            return -1;
        }
    }

    private boolean regionMatch(char[] c, int start){
        for (int i = start, j = 0; i < start + c.length; i++, j++){
            if (!(chars[i] == c[j])) return false;
        }
        return true;
    }

    /**
     * Removes the first instance of {@code c}
     */
    public final void remove(char c){
        synchronized (LOCK){
            remove0(c, 1);
        }
    }

    /**
     * Removes the {@code occurrence}th instance of {@code c}
     */
    public final void remove(char c, int occurrence){
        synchronized (LOCK){
            remove0(c, occurrence);
        }
    }

    private void remove0(char c, int occurrence){
        int x;
        if (occurrence < 1 || indexOf(c) < 0 || (x = occurs(c)) <= 0 || x < occurrence) return;
        for (int i = 0, j = 0; i < this.chars.length; i++) {
            if (chars[i] == c) j++;
            if (j == occurrence){
                removeCharAt0(i);
                return;
            }
        }
    }

    /**
     * Removes all instances of {@code c}
     */
    public final void removeAll(char c){
        synchronized (LOCK){
            for (int i = 0; i < this.chars.length; i++) {
                if (chars[i] == c) removeCharAt0(i);
            }
        }
    }

    /**
     * Removes the char at {@code index}
     */
    public final void removeCharAt(int index){
        synchronized (LOCK){
            removeCharAt0(index);
        }
    }

    private void removeCharAt0(int index){
        if (index < 0 || index >= this.chars.length) throw new IllegalArgumentException("Illegal index: " + index);
        char[] tmp = new char[this.length() - 1];
        System.arraycopy(this.getChars(), 0, tmp, 0, index);
        System.arraycopy(this.getChars(), index + 1, tmp, index, this.length() - index - 1);
        this.chars = tmp;
    }

    /**
     * Removes characters starting at {@code index} up to {@code index + len - l}
     */
    public final void removeCharsAt(int index, int len){
        synchronized (LOCK){
            removeCharsAt0(index, len);
        }
    }

    private void removeCharsAt0(int index, int len){
        if (index < 0 || index > this.length() || index + len > this.length()) return; //no-op
        char[] tmp = new char[this.length() - len];
        System.arraycopy(this.chars, 0, tmp, 0, index);
        System.arraycopy(this.chars, index + len, tmp, index, this.length() - len - index);
        this.chars = tmp;
    }

    /**
     * Removes the first instance of {@code s}
     */
    public final void remove(String s){
        synchronized (LOCK){
            remove0(s.toCharArray(), 1);
        }
    }

    /**
     * Removes the {@code occurrence}th instance of {@code s}
     */
    public final void remove(String s, int occurrence){
        synchronized (LOCK){
            remove0(s.toCharArray(), occurrence);
        }
    }

    /**
     * Removes the first instance of {@code c}
     */
    public final void remove(char[] c){
        synchronized (LOCK){
            remove0(c, 1);
        }
    }

    /**
     * Removes the {@code occurrence}th instance of {@code c}
     */
    public final void remove(char[] c, int occurrence){
        synchronized (LOCK){
            remove0(c, occurrence);
        }
    }

    private void remove0(char[] c, int occurrence){
        if (c.length == 1){ //fast path for single char
            remove0(c[0], occurrence);
            return;
        }
        int x;
        if (occurrence < 1 || indexOf(c) < 0 || (x = occurs(c)) <= 0 || x < occurrence) return;
        for (int i = 0, j = 0; i < this.chars.length - c.length; i++) {
            if (regionMatch(c, i)) j++;
            if (j == occurrence){
                removeAt(i, c.length);
                return;
            }
        }
    }

    /**
     * Removes all instances of {@code s}
     */
    public final void removeAll(String s){
        synchronized (LOCK){
            removeAll0(s.toCharArray());
        }
    }

    /**
     * Removes all instances of {@code c}
     */
    public void removeAll(char[] c){
        synchronized (LOCK){
            removeAll0(c);
        }
    }

    private void removeAll0(char[] c){
        int x;
        if (indexOf(c) < 0) return;
        for (int i = 0, j = 0; i < this.chars.length - c.length; i++) {
            if (regionMatch(c, i)) removeAt(i, c.length);
        }
    }

    private void removeAt(int index, int len){
        char[] tmp = new char[this.chars.length - len];
        System.arraycopy(this.chars, 0, tmp, 0, index);
        System.arraycopy(this.chars, index + len, tmp, index, this.chars.length - index - len);
        this.chars = tmp;
    }

    /**
     * @return a copy of the characters of this {@code MutableString}
     */
    public final char[] toCharArray(){
        synchronized (LOCK){
            char[] ret = new char[this.chars.length];
            System.arraycopy(this.chars, 0, ret, 0, this.chars.length);
            return ret;
        }
    }

    /**
     * @return the characters of this {@code MutableString}
     */
    public final char[] getChars(){
        synchronized (LOCK){
            return chars;
        }
    }

    /**
     * Checks that two regions are exactly identical (same characters and case of characters)
     *
     * @throws NullPointerException if {@code one} or {@code two} is null
     */
    public static boolean regionMatches(MutableString one, MutableString two, int start, int end){
        synchronized (STATIC_LOCK){
            Objects.requireNonNull(one);
            Objects.requireNonNull(two);
            return regionMatches0(one, two, start, end, false);
        }
    }

    /**
     * Checks that two regions have the same characters, and if ignoreCase is {@code false}, that they are the same case
     *
     * @throws NullPointerException if {@code one} or {@code two} is null
     */
    public static boolean regionMatches(MutableString one, MutableString two, int start, int end, boolean ignoreCase){
        synchronized (STATIC_LOCK){
            Objects.requireNonNull(one);
            Objects.requireNonNull(two);
            return regionMatches0(one, two, start, end, ignoreCase);
        }
    }

    private static boolean regionMatches0(MutableString one, MutableString two, int start, int end, boolean ignoreCase){
        if (one.length() == 0 && two.length() == 0) return true; //two empty strings are equal
        if (end < start || start < 0 || end > one.length() || end > two.length()) return false; //would throw ArrayIndexOutOfBoundsException otherwise
        final char[] c1 = one.getChars(), c2 = two.getChars();
        for (int i = start; i < end; i++) {
            if (!charEquals(c1[i], c2[i], ignoreCase)) return false;
        }
        return true;
    }

    //Check both upper case and lower case (if ignoreCase) to avoid any weirdness
    private static boolean charEquals(char a, char b, boolean ignoreCase){
        return ignoreCase ? ((Character.toUpperCase(a) == Character.toUpperCase(b)) ||
                (Character.toLowerCase(a) == Character.toLowerCase(b))) : (a == b);
    }

    /**
     * MutableString one  = ...;
     * MutableString two = ...;
     * int start = ...;
     * int end = ...;
     *
     * one.regionMatches(two, start, end);
     * is equivalent to
     * MutableString.regionMatches(one, two, start, end);
     * or
     * MutableString.regionMatches(one, two, start, end, false);
     */
    public final boolean regionMatches(MutableString other, int start, int end){
        synchronized (LOCK){
            Objects.requireNonNull(other);
            return regionMatches0(this, other, start, end, false);
        }
    }

    /**
     * Return true iff this {@code MutableString} has:
     * -the same number of characters
     * -the characters in each position are the same (case-sensitive)
     * @return Return true iff this {@code MutableString} is equal to {@code other}
     */
    @Override
    public final boolean equals(Object o) {
        synchronized (LOCK){
            Objects.requireNonNull(o, "Object to compare cannot be null");
            return (o == this) || (o instanceof MutableString && equals(this, (MutableString) o));
        }
    }

    /**
     * Return true iff this {@code MutableString} has:
     * -the same number of characters
     * -the characters in each position are the same (case-sensitive)
     * @return Return true iff this {@code one} is equal to {@code two}
     */
    public static boolean equals(MutableString one, MutableString two){
        synchronized (STATIC_LOCK){
            Objects.requireNonNull(one);
            Objects.requireNonNull(two);
            return one.length() == two.length() && regionMatches0(one, two, 0, one.length(), false);
        }
    }

    /**
     * Return true iff this {@code MutableString} has:
     * -the same number of characters
     * -the characters in each position are the same (case-insensitive)
     * @return Return true iff this {@code MutableString} is equal to {@code other},
     * excluding case differences
     */
    public final boolean equalsIgnoreCase(MutableString other){
       synchronized (LOCK){
           Objects.requireNonNull(other, "MutableString to compare cannot be null");
           return (other == this) || equalsIgnoreCase(this, other);
       }
    }

    /**
     * Return true iff this {@code MutableString} has:
     * -the same number of characters
     * -the characters in each position are the same (case-insensitive)
     * @return Return true iff this {@code one} is equal to {@code two},
     * excluding case differences
     */
    public static boolean equalsIgnoreCase(MutableString one, MutableString two){
        synchronized (STATIC_LOCK){
            Objects.requireNonNull(one);
            Objects.requireNonNull(two);
            return one.length() == two.length() && regionMatches0(one, two, 0, one.length(), true);
        }
    }

    /**
     * Returns a sub-sequence of this {@code MutableString}, that begins at {@code start}
     * and goes to {@code end} - 1
     * @return a sub-sequence of this {@code MutableString}
     *
     * @implNote identical to {@linkplain MutableString#subString(int, int)}
     */
    @Override
    public final CharSequence subSequence(int start, int end) {
        synchronized (LOCK){
            return this.subString0(start, end);
        }
    }

    /**
     * Returns a substring of this {@code MutableString}, that begins at {@code start}
     * and goes to {@code end} - 1
     * @return a substring of this {@code MutableString}
     */
    public final MutableString subString(int start, int end){
        synchronized (LOCK){
            return subString0(start, end);
        }
    }

    private MutableString subString0(int start, int end){
        return (start == 0 && end == this.length()) ? this : new MutableString(this.getChars(), start, end);
    }

    /**
     * Returns a substring of this {@code MutableString}, that begins at {@code start}
     * and goes to {@code this.chars.length} - 1
     * @return a substring of this {@code MutableString}
     */
    public final MutableString subString(int start){
        synchronized (LOCK){
            return (start == 0) ? this : new MutableString(this.getChars(), start, this.length());
        }
    }


    /**
     * Sole instance for the empty string iterator.
     */
    private static final EmptyMutableStringIterator emptyMutableStringIterator = new EmptyMutableStringIterator();

    /**
     * @return an iterator over the characters of this {@code MutableString}, starting
     * at 0
     */
    @Override
    public final Iterator<Character> iterator() {
        synchronized (LOCK){
            return this.length() > 0 ? new MutableStringIterator() : emptyMutableStringIterator;
        }
    }

    /**
     * @return an iterator over the characters of this {@code MutableString}, starting
     * at {@code index}
     */
    public final Iterator<Character> iterator(int index) {
        synchronized (LOCK){
            return this.length() > 0 ? new MutableStringIterator(index) : emptyMutableStringIterator;
        }
    }

    /**
     * @return a reverse iterator over the characters of this {@code MutableString}, starting
     * at 0
     */
    public final Iterator<Character> reversed(){
        synchronized (LOCK){
            return this.length() > 0 ? new ReverseMutableStringIterator() : emptyMutableStringIterator;
        }
    }

    /**
     * @return a reverse iterator over the characters of this {@code MutableString}, starting
     * at {@code index}
     */
    public synchronized final Iterator<Character> reversed(int index){
        synchronized (LOCK){
            return this.length() > 0 ? new ReverseMutableStringIterator(index) : emptyMutableStringIterator;
        }
    }

    /**
     * concatenates the characters {@code other} to the end of this {@code MutableString},
     * separated by {@code delimiter}
     * @return this {@code MutableString}
     */
    public final MutableString join(String delimiter, MutableString other){
        synchronized (LOCK){
            Objects.requireNonNull(delimiter);
            Objects.requireNonNull(other);
            if (other.length() == 0) return this;
            return this.concat0(delimiter.toCharArray()).concat0(other.getChars());
        }
    }

    /**
     * concatenates the characters of each {@code MutableString} in {@code toJoin} to the
     * end of this {@code MutableString}, separated by {@code delimiter}
     * @return this {@code MutableString}
     */
    public final MutableString joinAll(String delimiter, MutableString... toJoin){
        synchronized (LOCK){
            Objects.requireNonNull(delimiter);
            if (toJoin.length == 0) return this;
            for (MutableString s : toJoin){
                Objects.requireNonNull(s);
                this.concat0(delimiter.toCharArray()).concat0(s.getChars());
            }
            return this;
        }
    }

    /**
     * concatenates the characters {@code other} to the end of this {@code MutableString},
     * separated by {@code delimiter}
     * @return this {@code MutableString}
     */
    public final MutableString join(MutableString delimiter, MutableString other){
        synchronized (LOCK){
            Objects.requireNonNull(delimiter);
            Objects.requireNonNull(other);
            if (other.length() == 0) return this;
            return this.concat0(delimiter.toCharArray()).concat0(other.getChars());
        }
    }

    /**
     * concatenates the characters of each {@code MutableString} in {@code toJoin} to the
     * end of this {@code MutableString}, separated by {@code delimiter}
     * @return this {@code MutableString}
     */
    public final MutableString joinAll(MutableString delimiter, MutableString... toJoin){
        synchronized (LOCK){
            Objects.requireNonNull(delimiter);
            if (toJoin.length == 0) return this;
            for (MutableString s : toJoin){
                Objects.requireNonNull(s);
                this.concat0(delimiter.toCharArray()).concat0(s.getChars());
            }
            return this;
        }
    }

    /**
     * Splits this {@code MutableString} around the regular expression {@code regex}
     * @return a two element array if split, one array if unsplit
     *
     * @see String#split(String)
     */
    public final String[] split(String regex){
        synchronized (LOCK){
            return (new String(this.getChars())).split(regex);
        }
    }

    /**
     * Splits this {@code MutableString} around the regular expression {@code regex}
     * at most {@code limit} - 1 times
     * @return a limit - 1 element array if split, one array if unsplit
     *
     * @see String#split(String, int)
     */
    public final String[] split(String regex, int limit){
        synchronized (LOCK){
            return (new String(this.getChars())).split(regex, limit);
        }
    }

    /**
     * concatenates {@code c} to the end of this {@code MutableString}
     * @return this {@code MutableString}
     */
    public final MutableString concat(char c){
        synchronized (LOCK){
            return concat0(c);
        }
    }

    /**
     * concatenates the characters {@code s} to the end of this {@code MutableString}
     * @return this {@code MutableString}
     */
    public final MutableString concat(String s){
        synchronized (LOCK){
            return concat0(s.toCharArray());
        }
    }

    /**
     * concatenates the characters {@code other} to the end of this {@code MutableString}
     * @return this {@code MutableString}
     */
    public final MutableString concat(MutableString other){
        synchronized (LOCK){
            return concat0(other.getChars());
        }
    }

    /**
     * concatenates {@code chars} to the end of this {@code MutableString}
     * @return this {@code MutableString}
     */
    public final MutableString concat(char[] chars){
        synchronized (LOCK){
            return concat0(chars);
        }
    }

    private MutableString concat0(char c){
        char[] tmp = new char[this.length() + 1];
        System.arraycopy(this.getChars(), 0, tmp, 0, this.length());
        tmp[tmp.length - 1] = c;
        this.chars = tmp;
        return this;
    }

    private MutableString concat0(char[] chars){
        if (chars.length == 0) return this;
        if (chars.length == 1) return concat0(chars[0]);
        char[] tmp = new char[this.length() + chars.length];
        System.arraycopy(this.getChars(), 0, tmp, 0, this.length());
        System.arraycopy(chars, 0, tmp, this.length(), chars.length);
        this.chars = tmp;
        return this;
    }

    /**
     * @return a copy of this {@code MutableString}.
     *         It is recommended that the char array is obtained by calling the
     *         getChars() method
     */
    public final MutableString clone(){
        synchronized (LOCK){
            try{
                MutableString m = (MutableString) super.clone();
                m.chars = this.toCharArray();
                return m;
            }
            catch (CloneNotSupportedException x) {
                throw new InternalError(x);
            }
        }
    }

    /**
     * @return a hash code of this {@code MutableString}, or 0 if empty
     */
    @Override
    public final int hashCode() {
        synchronized (LOCK){
            int h = getHash();
            if (h != hash) hash = h;
            return h;
        }
    }

    private int getHash(){
        int h = 0;
        if (this.length() > 0){
            for (char c : chars) h = 31 * h + c;
        }
        return h;
    }

    /**
     * Compares two strings lexicographically.
     * The comparison is based on the Unicode value of each character in
     * the strings. The character sequence represented by this
     * {@code MutableString} object is compared lexicographically to the
     * character sequence represented by the argument string. The result is
     * a negative integer if this {@code MutableString} object
     * lexicographically precedes the argument string. The result is a
     * positive integer if this {@code MutableString} object lexicographically
     * follows the argument string. The result is zero if the strings
     * are equal; {@code compareTo} returns {@code 0} exactly when
     * the {@link #equals(Object)} method would return {@code true}.
     * <p>
     * This is the definition of lexicographic ordering. If two strings are
     * different, then either they have different characters at some index
     * that is a valid index for both strings, or their lengths are different,
     * or both. If they have different characters at one or more index
     * positions, let <i>k</i> be the smallest such index; then the string
     * whose character at position <i>k</i> has the smaller value, as
     * determined by using the &lt; operator, lexicographically precedes the
     * other string. In this case, {@code compareTo} returns the
     * difference of the two character values at position {@code k} in
     * the two MutableStrings -- that is, the value:
     * <blockquote><pre>
     * this.charAt(k)-anotherString.charAt(k)
     * </pre></blockquote>
     * If there is no index position at which they differ, then the shorter
     * string lexicographically precedes the longer string. In this case,
     * {@code compareTo} returns the difference of the lengths of the
     * strings -- that is, the value:
     * <blockquote><pre>
     * this.length()-anotherString.length()
     * </pre></blockquote>
     *
     * @param   other   the {@code MutableString} to be compared.
     * @return  the value {@code 0} if the argument string is equal to
     *          this MutableString; a value less than {@code 0} if this MutableString
     *          is lexicographically less than the string argument; and a
     *          value greater than {@code 0} if this string is
     *          lexicographically greater than the string argument.
     */
    @Override
    public int compareTo(MutableString other) {
        synchronized (LOCK){
            final char[] v1 = this.getChars();
            final char[] v2 = other.getChars();

            final int len1 = v1.length;
            final int len2 = v2.length;
            final int lim = Math.min(len1, len2);

            for (int k = 0; k < lim; ){
                char c1 = v1[k];
                char c2 = v2[k];
                if (c1 != c2) return c1 - c2;
                k++;
            }

            return len1 - len2;
        }
    }

    // Iterator Classes
    /**
     * Iterator
     */
    private final class MutableStringIterator implements Iterator<Character>{
        private MutableString string;
        private int i;

        private MutableStringIterator(){
            this(0);
        }

        private MutableStringIterator(int pos){
            this.string = MutableString.this;
            if (pos < 0 || pos >= string.length()) throw new IllegalArgumentException("Illegal iterator position: " + pos);
            this.i = pos;
        }

        @Override
        public boolean hasNext() {
            return i < string.length();
        }

        @Override
        public Character next() {
            if (hasNext()) return string.charAt(i++);
            throw new NoSuchElementException();
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) return true;
            if (o instanceof MutableStringIterator){
                MutableStringIterator other = (MutableStringIterator) o;
                return MutableString.equals(this.string, other.string) && this.i == other.i;
            }
            return false;
        }
    }
    /**
     * Reverse Iterator
     */
    private final class ReverseMutableStringIterator implements Iterator<Character>{
        private MutableString string;
        private int i;

        private ReverseMutableStringIterator(){
            this(MutableString.this.length() - 1);
        }

        private ReverseMutableStringIterator(int pos){
            this.string = MutableString.this;
            if (pos < 0 || pos >= string.length()) throw new IllegalArgumentException("Illegal iterator position: " + pos);
            this.i = pos;
        }

        @Override
        public boolean hasNext() {
            return i >= 0;
        }

        @Override
        public Character next() {
            if (hasNext()) return string.charAt(i--);
            throw new NoSuchElementException();
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) return true;
            if (o instanceof ReverseMutableStringIterator){
                ReverseMutableStringIterator other = (ReverseMutableStringIterator) o;
                return MutableString.equals(this.string, other.string) && other.i == this.i;
            }
            return false;
        }
    }
    /**
     * Empty Iterator
     */
    private static final class EmptyMutableStringIterator implements Iterator<Character>{
        private EmptyMutableStringIterator(){}

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public Character next() {
            throw new NoSuchElementException();
        }
    }
}
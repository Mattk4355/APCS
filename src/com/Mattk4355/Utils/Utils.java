package com.Mattk4355.Utils;

import com.Mattk4355.Utils.Files.FileManipulator;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * A class that contains utility classes
 *
 * to avoid having to put Utils before one of the classes one may add:
 * {@code import com.Mattk4355.Utils.Utils.*;}
 * @implNote if such an import is used, some default java classes my need
 *           to be referenced with their fully qualified names
 *           (eg. java.util.Objects)
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public final class Utils {
    private Utils(){
        throw new InternalError("No instancing this class!");
    }

    private static final class Lock{}

    /**
     * Class that has methods to deal with Objects
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static final class Objects{
        public static final String NULL_DEFAULT = "null";
        public static final String DEFAULT_NULL_MESSAGE = "Argument cannot be null.";
        private Objects(){
            throw new InternalError("No instancing this class!");
        }

        private static final Lock LOCK = new Lock();

        /**
         * Returns a String representation of the given Object.
         *
         * @param t the Object to covert to a String.
         * @return a String representation of the given Object.
         */
        public static <T> String toString(T t){
            synchronized (LOCK){
                return toString(t, NULL_DEFAULT);
            }
        }

        /**
         * Returns a String representation of the given Object.
         *
         * @param t the Object to covert to a String.
         * @param nullDefault the default String for null objects.
         * @return a String representation of the given Object.
         * @throws IllegalArgumentException if <code> nullDefault </code> is null.
         */
        public static <T> String toString(T t, String nullDefault){
            synchronized (LOCK){
                return toString0(t, nullDefault);
            }
        }

        private static <T> String toString0(T t, String nullDefault){
            if (nullDefault == null) nullDefault = NULL_DEFAULT + " (null message provided)";
            if (t == null) return nullDefault;
            if (t instanceof int[]) return Arrays.toString((int[]) t);
            else if (t instanceof double[]) return Arrays.toString((double[]) t);
            else if (t instanceof float[]) return Arrays.toString((float[]) t);
            else if (t instanceof byte[]) return Arrays.toString((byte[]) t);
            else if (t instanceof char[]) return Arrays.toString((char[]) t);
            else if (t instanceof short[]) return Arrays.toString((short[]) t);
            else if (t instanceof long[]) return Arrays.toString((long[]) t);
            else if (t instanceof boolean[]) return Arrays.toString((boolean[]) t);
            else if (t instanceof Object[]) return Arrays.toString((Object[]) t);
            else if (t instanceof Collection<?>) return Arrays.toString((Collection<?>) t);
            else return t.toString();
        }

        /**
         * Checks if two Objects are equal to each other. If both are null,
         * this method returns <code> true </code>.
         *
         * @param t one Object.
         * @param u the other Object.
         * @param <T> the type of Objects to compare.
         * @return true if the objects equal each other. If both are null,
         *         this method returns <code> true </code>.
         */
        public static <T> boolean equals(T t, T u){
            synchronized (LOCK){
                return (t == null) ? (u == null) : t.equals(u);
            }
        }

        /**
         * Checks if two arrays are "equal" to each other. Two arrays are considered egual if:
         *      -They have the same number of elements and
         *      -The elements in the same position of the array are equal.
         * @param t one array
         * @param u the other array
         * @param <T> The type of array
         * @return true if the arrays are equal, false otherwise.
         *
         * @throws IllegalArgumentException if <code> t </code>
         *         and <code> u </code> are not the same array class,
         *         and non-null.
         */
        public static <T> boolean arrayEquals(T t, T u){
            synchronized (LOCK){
                if (t == null) return u == null;
                if (t instanceof int[] && u instanceof int[]) return Arrays.equals((int[]) t, (int[]) u);
                else if (t instanceof double[] && u instanceof double[]) return Arrays.equals((double[]) t, (double[]) u);
                else if (t instanceof float[] && u instanceof float[]) return Arrays.equals((float[]) t, (float[]) u);
                else if (t instanceof byte[] && u instanceof byte[]) return Arrays.equals((byte[]) t, (byte[]) u);
                else if (t instanceof short[] && u instanceof short[]) return Arrays.equals((short[]) t, (short[]) u);
                else if (t instanceof char[] && u instanceof char[]) return Arrays.equals((char[]) t, (char[]) u);
                else if (t instanceof long[] && u instanceof long[]) return Arrays.equals((long[]) t, (long[]) u);
                else if (t instanceof boolean[] && u instanceof boolean[]) return Arrays.equals((boolean[]) t, (boolean[]) u);
                else if (t instanceof Object[] && u instanceof Object[]) return Arrays.equals((Object[]) t, (Object[]) u);
                else throw new IllegalArgumentException("Illegal class combination: t: " + t.getClass().toString()
                            + " u:" + u.getClass().toString());
            }
        }

        /**
         * Throws a {@linkplain NullPointerException} if <code> t </code>
         *        is null.
         *
         * @param t the Object.
         * @param <T> the type of Object.
         * @return the Object.
         *
         * @throws NullPointerException if <code> t </code> is null.
         */
        public static <T> T requireNonNull(T t){
            synchronized (LOCK){
                return requireNonNull0(t, DEFAULT_NULL_MESSAGE);
            }
        }

        /**
         * Throws a {@linkplain NullPointerException} if <code> t </code>
         *        is null.
         *
         * @param t the Object.
         * @param message the message to include in the {@linkplain NullPointerException}.
         * @param <T> the type of Object.
         * @return the Object.
         *
         * @throws NullPointerException if <code> t </code> is null.
         */
        public static <T> T requireNonNull(T t, String message){
            synchronized (LOCK){
                return requireNonNull0(t, message);
            }
        }

        private static <T> T requireNonNull0(T t, String message){
            if (message == null) message = DEFAULT_NULL_MESSAGE + " (null message provided)";
            if (t == null) throw new NullPointerException(message);
            return t;
        }

        /**
         * Throws a {@linkplain NullPointerException} if any Object in
         *        <code> args </code> is null.
         *
         * @param args the array of Objects to check for non-nullity.
         * @param <T> the type of Object.
         * @return the array of Objetcs.
         *
         * @throws NullPointerException if any argument in <code> args </code>
         *        is null.
         */
        @SafeVarargs
        public static <T> T[] requireAllNonNull(T... args){
            synchronized (LOCK){
                for (int i = 0; i < args.length; i++) {
                    if (args[i] == null) throw new NullPointerException(argNotNull(i + 1));
                }
                return args;
            }
        }
        private static String argNotNull(int i){
            return "Argument " + i + " cannot be null";
        }

        /**
         * Returns <code> true </code> if <code> t </code> is null, false otherwise.
         *
         * @param t the Object.
         * @param <T> the type of Object.
         * @return <code> true </code> if <code> t </code> is null, false otherwise.
         */
        public static <T> boolean isNull(T t){
            return t == null;
        }

        /**
         * Returns <code> true </code> if <code> t </code> is not null, false otherwise.
         *
         * @param t the Object.
         * @param <T> the type of Object.
         * @return <code> true </code> if <code> t </code> is not null, false otherwise.
         */
        public static <T> boolean nonNull(T t){
            return t != null;
        }

        /**
         * Returns the hash code of the Object, or 0 if it is null.
         *
         * @param t the Object.
         * @param <T> the type of Object.
         * @return the hash code of the Object, or 0 if it is null.
         */
        public static <T> int hashCode(T t){
            synchronized (LOCK){
                return t != null ? t.hashCode() : 0;
            }
        }

        /**
         * Clones the given Object.
         * @param obj the Object.
         * @param <T> the class of the Object
         * @return a clone of the given Object.
         *
         * @throws NullPointerException if <code> obj </code> is null.
         * @throws CloneNotAllowedException if the <code> canClone() </code>
         *         method of <code> obj </code> returns <code> false </code>
         */
        public static <T extends CanClone<T>> T clone(T obj) throws CloneNotAllowedException{
            requireNonNull(obj, "Object to clone cannot be null");
            if (obj.canClone()) return obj.clone(); //defaults to true
            else throw CloneNotAllowedException.forObj0(obj);
        }

        /**
         * Returns the same hash code for the given object as
         * would be returned by the default method hashCode(),
         * whether or not the given object's class overrides
         * hashCode(). returns 0 if the Object is null.
         * @see System#identityHashCode(Object)
         *
         * @param t the Object.
         * @param <T> the type of Object.
         * @return the "identity" hash code of the Object, or 0 if it is null.
         */
        public static <T> int identityHashCode(T t){
            synchronized (LOCK){
                return t != null ? System.identityHashCode(t) : 0;
            }
        }

        public static <T extends Comparable<T>> int compare(T one, T two){
            synchronized (LOCK){
                requireNonNull(one);
                requireNonNull(two);
                return one.compareTo(two);
            }
        }

        public static <T extends Comparable<T>> int reverseCompare(T one, T two){
            synchronized (LOCK){
                requireNonNull(one);
                requireNonNull(two);
                return two.compareTo(one);
            }
        }

        public static <T> int compare(T one, T two, Comparator<T> comparator){
            synchronized (LOCK){
                requireNonNull(comparator);
                return comparator.compare(one, two);
            }
        }

        public static <T> int reverseCompare(T one, T two, Comparator<T> comparator){
            synchronized (LOCK){
                requireNonNull(comparator);
                return comparator.compare(two, one);
            }
        }
    }

    /**
     * Class that has methods to deal with Arrays
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static final class Arrays {
        private Arrays() {
            throw new InternalError("No instancing this class!");
        }

        private static final Lock LOCK = new Lock();

        @SafeVarargs
        public static <T> ObjectArrayList<T> asArrayList(T... a){
            Objects.requireNonNull(a);
            return new ObjectArrayList<>(a);
        }
        @SafeVarargs
        public static <T> List<T> asList(T... a){
            Objects.requireNonNull(a);
            return java.util.Arrays.asList(a);
        }

        /**
         * Returns a String representation of the values of the array
         *
         * @param i the array
         * @return A String representation of the values of the array
         */
        public static String toString(int[] i){
            synchronized (LOCK){
                if (i == null) return "null";
                int iMax = i.length - 1;
                if(iMax == -1) return "{}";
                StringBuilder sb = new StringBuilder();
                sb.append("{");

                for (int j = 0; ; j++) {
                    if (j == iMax) return sb.append(i[j]).append("}").toString();
                    sb.append(i[j]).append(", ");
                }
            }
        }

        /**
         * Returns a String representation of the values of the array
         *
         * @param d the array
         * @return A String representation of the values of the array
         */
        public static String toString(double[] d){
            synchronized (LOCK){
                if (d == null) return "null";
                int iMax = d.length - 1;
                if(iMax == -1) return "{}";
                StringBuilder sb = new StringBuilder();
                sb.append("{");
                for (int j = 0; ; j++) {
                    if (j == iMax) return sb.append(d[j]).append("}").toString();
                    sb.append(d[j]).append(", ");
                }
            }
        }

        /**
         * Returns a String representation of the values of the array
         *
         * @param f the array
         * @return A String representation of the values of the array
         */
        public static String toString(float[] f){
            synchronized (LOCK){
                if (f == null) return "null";
                int iMax = f.length - 1;
                if(iMax == -1) return "{}";
                StringBuilder sb = new StringBuilder();
                sb.append("{");
                for (int j = 0; ; j++) {
                    if (j == iMax) return sb.append(f[j]).append("}").toString();
                    sb.append(f[j]).append(", ");
                }
            }
        }

        /**
         * Returns a String representation of the values of the array
         *
         * @param b the array
         * @return A String representation of the values of the array
         */
        public static String toString(byte[] b){
            synchronized (LOCK){
                if (b == null) return "null";
                int iMax = b.length - 1;
                if(iMax == -1) return "{}";
                StringBuilder sb = new StringBuilder();
                sb.append("{");
                for (int j = 0; ; j++) {
                    if (j == iMax) return sb.append(b[j]).append("}").toString();
                    sb.append(b[j]).append(", ");
                }
            }
        }

        /**
         * Returns a String representation of the values of the array
         *
         * @param c the array
         * @return A String representation of the values of the array
         */
        public static String toString(char[] c){
            synchronized (LOCK){
                if (c == null) return "null";
                int iMax = c.length - 1;
                if(iMax == -1) return "{}";
                StringBuilder sb = new StringBuilder();
                sb.append("{");
                for (int j = 0; ; j++) {
                    if (j == iMax) return sb.append(c[j]).append("}").toString();
                    sb.append(c[j]).append(", ");
                }
            }
        }

        /**
         * Returns a String representation of the values of the array
         *
         * @param s the array
         * @return A String representation of the values of the array
         */
        public static String toString(short[] s){
            synchronized (LOCK){
                if (s == null) return "null";
                int iMax = s.length - 1;
                if(iMax == -1) return "{}";
                StringBuilder sb = new StringBuilder();
                sb.append("{");
                for (int j = 0; ; j++) {
                    if (j == iMax) return sb.append(s[s.length - 1]).append("}").toString();
                    sb.append(s[j]).append(", ");
                }
            }
        }

        /**
         * Returns a String representation of the values of the array
         *
         * @param l the array
         * @return A String representation of the values of the array
         */
        public static String toString(long[] l){
            synchronized (LOCK){
                if (l == null) return "null";
                int iMax = l.length - 1;
                if(iMax == -1) return "{}";
                StringBuilder sb = new StringBuilder();
                sb.append("{");

                for (int j = 0; ; j++) {
                    if (j == iMax) return sb.append(l[l.length - 1]).append("}").toString();
                    sb.append(l[j]).append(", ");
                }
            }
        }

        /**
         * Returns a String representation of the values of the array
         *
         * @param b the array
         * @return A String representation of the values of the array
         */
        public static String toString(boolean[] b){
            synchronized (LOCK){
                if (b == null) return "null";
                int iMax = b.length - 1;
                if(iMax == -1) return "{}";
                StringBuilder sb = new StringBuilder();
                sb.append("{");
                for (int j = 0; ; j++) {
                    if (j == iMax) return sb.append(b[j]).append("}").toString();
                    sb.append(b[j]).append(", ");
                }
            }
        }

        /**
         * Returns a String representation of the values of the array
         *
         * @param s the array
         * @return A String representation of the values of the array
         */
        public static String toString(String[] s){
            synchronized (LOCK){
                if (s == null) return "null";
                int iMax = s.length - 1;
                if(iMax == -1) return "{}";
                StringBuilder sb = new StringBuilder();
                sb.append("{");
                for (int j = 0; ; j++) {
                    if (j == iMax){
                        if (s[j] == null) return sb.append("null}").toString();
                        else return sb.append(s[j]).append("}").toString();
                    }
                    if (s[j] == null) sb.append("null, ");
                    else sb.append(s[j]).append(", ");
                }
            }
        }

        /**
         * Returns a String representation of the values of the array
         *
         * @param t the array
         * @return A String representation of the values of the array
         */
        public static <T> String toString(T[] t){
            synchronized (LOCK){
                if (t == null) return "null";
                int iMax = t.length - 1;
                if(iMax == -1) return "{}";
                ArrayList<Object> dejaVu = new ArrayList<>();
                dejaVu.add(t);
                return toString(t, dejaVu);
            }
        }
        private static <T> String toString(T[] t, ArrayList<Object> dejaVu){
            if (t == null) return "null"; //sanity check
            int iMax = t.length - 1;
            if(iMax == -1) return "{}";

            StringBuilder sb = new StringBuilder();
            sb.append("{");
            for (int j = 0; ; j++){
                if (j == iMax){
                    if (t[j] == null) return sb.append("null}").toString();
                    else if (isPrimitiveArray(t[j])) return sb.append(primitiveArrayToString(t[j])).append("}").toString(); //primitive array type
                    else if (isObjectArray(t[j])){ //Object array type
                        if (dejaVu.contains(t[j])) return sb.append("{...}}").toString();
                        else{
                            dejaVu.add(t[j]);
                            return sb.append(toString((Object[]) t[j], dejaVu)).append("}").toString();
                        }
                    }
                    else if (isCollection(t[j])){ //some form of collection
                        if (dejaVu.contains(t[j])) return sb.append("{...}}").toString();
                        else{
                            Object[] temp = ((Collection) t[j]).toArray();
                            dejaVu.add(t[j]); //Add collection as well to prevent infinite loops
                            dejaVu.add(temp);
                            return sb.append(toString(temp, dejaVu)).append("}").toString();
                        }
                    }
                    else return sb.append(t[j].toString()).append("}").toString(); //anything else
                }

                if (t[j] == null) sb.append("null, ");
                else if (isPrimitiveArray(t[j])) sb.append(primitiveArrayToString(t[j])).append(", "); //primitive array type
                else if (isObjectArray(t[j])){ //Object array type
                    if (dejaVu.contains(t[j])) sb.append("{...}, ");
                    else{
                        dejaVu.add(t[j]);
                        sb.append(toString((Object[]) t[j], dejaVu)).append(", ");
                    }
                }
                else if (isCollection(t[j])){ //some form of collection
                    if (dejaVu.contains(t[j])) sb.append("{...}, ");
                    else{
                        Object[] temp = ((Collection) t[j]).toArray();
                        dejaVu.add(t[j]); //Add collection as well to prevent infinite loops
                        dejaVu.add(temp);
                        sb.append(toString(temp, dejaVu));
                    }
                }
                else sb.append(t[j].toString()).append(", "); //anything else
            }
        }
        private static <T> String primitiveArrayToString(T t){
            if (t == null){
                //Shouldn't happen, since we check for this in the above code
                throw new IllegalArgumentException("attempted to cast null object to primitive array type");
            }
            if (t instanceof int[]) return toString((int[]) t);
            else if (t instanceof double[]) return toString((double[]) t);
            else if (t instanceof float[]) return toString((float[]) t);
            else if (t instanceof byte[]) return toString((byte[]) t);
            else if (t instanceof char[]) return toString((char[]) t);
            else if (t instanceof short[]) return toString((short[]) t);
            else if (t instanceof long[]) return toString((long[]) t);
            else if (t instanceof boolean[]) return toString((boolean[]) t);
            else{
                //Also shouldn't happen, but just in case.
                String className = t.getClass().getCanonicalName();
                throw new IllegalArgumentException("attempted to cast" + className + " to a primitive array");
            }
        }
        /**
         * Returns true if the given object is an Object array, false otherwise.
         *
         * @return true if the given object is an Object array, false otherwise.
         */
        public static <T> boolean isObjectArray(T t){
            return t instanceof Object[];
        }

        /**
         * Returns true if the given object is a Collection, false otherwise.
         *
         * @return true if the given object is a Colection, false otherwise.
         */
        public static <T> boolean isCollection(T t){
            return t instanceof Collection<?>;
        }

        /**
         * Returns true if the given object is a primitive array, false otherwise.
         *
         * @return true if the given object is a primitive array, false otherwise.
         */
        public static <T> boolean isPrimitiveArray(T t) {
            return t instanceof int[] || t instanceof double[] ||
                    t instanceof float[] || t instanceof byte[] ||
                    t instanceof char[] || t instanceof short[] ||
                    t instanceof long[] || t instanceof boolean[];
        }

        /**
         * Returns true if the given object is an array, false otherwise.
         *
         * @return true if the given object is an array, false otherwise.
         */
        public static <T> boolean isArray(T t){
            return isPrimitiveArray(t) || isObjectArray(t);
        }

        /**
         * Returns a String representation of the values of the Collection
         *
         * @param c the Collection
         * @return A String representation of the values of the Collection
         */
        public static String toString(Collection<?> c){
            synchronized (LOCK){
                if (c == null) return "null";
                if (c.isEmpty()) return "{}";
                ArrayList<Object> dejaVu = new ArrayList<>();
                Object[] arr = c.toArray();
                dejaVu.add(arr);
                dejaVu.add(c);
                return toString(arr, dejaVu);
            }
        }

        /**
         * Returns a String representation of the values of the ObjectArrayList
         *
         * @param list the ObjectArrayList
         * @return A String representation of the values of the ObjectArrayList
         */
        public static String toString(ObjectArrayList<?> list){
            synchronized (LOCK){
                if (list == null) return "null";
                if (list.isEmpty()) return "{}";
                ArrayList<Object> dejaVu = new ArrayList<>();
                Object[] arr = list.toArray();
                dejaVu.add(arr);
                dejaVu.add(list);
                return toString(arr, dejaVu);
            }
        }

        /**
         * Returns a new array from the given arguments.
         *
         * @return a new array from the given arguments.
         */
        @SafeVarargs
        public static <T> T[] newArray(T... args){
            return args;
        }

        /**
         * Copies the specified array.
         *
         * @param original the array to be copied
         * @return a copy of the original array.
         * @throws NullPointerException if <tt>original</tt> is null
         */
        public static <T> T[] copyOf(T[] original){
            synchronized (LOCK){
                //noinspection unchecked
                return (T[]) copyOf0(original, original.length, original.getClass());
            }
        }

        /**
         * Copies the specified array.
         *
         * @param original the array to be copied
         * @return a copy of the original array.
         * @throws NullPointerException if <tt>original</tt> is null
         */
        public static <T,U> T[] copyOf(U[] original, Class<? extends T[]> newType){
            synchronized (LOCK){
                //noinspection RedundantCast
                return (T[]) copyOf0(original, original.length, newType);
            }
        }

        /**
         * Copies the specified array.
         *
         * @param original the array to be copied
         * @return a copy of the original array.
         * @throws NullPointerException if <tt>original</tt> is null
         */
        public synchronized static byte[] copyOf(byte[] original){
            synchronized (LOCK){
                return copyOf0(original, original.length);
            }
        }

        /**
         * Copies the specified array.
         *
         * @param original the array to be copied
         * @return a copy of the original array.
         * @throws NullPointerException if <tt>original</tt> is null
         */
        public static short[] copyOf(short[] original){
            synchronized (LOCK){
                return copyOf0(original, original.length);
            }
        }

        /**
         * Copies the specified array.
         *
         * @param original the array to be copied
         * @return a copy of the original array.
         * @throws NullPointerException if <tt>original</tt> is null
         */
        public static int[] copyOf(int[] original){
            synchronized (LOCK){
                return copyOf0(original, original.length);
            }
        }

        /**
         * Copies the specified array.
         *
         * @param original the array to be copied
         * @return a copy of the original array.
         * @throws NullPointerException if <tt>original</tt> is null
         */
        public static long[] copyOf(long[] original){
            synchronized (LOCK){
                return copyOf0(original, original.length);
            }
        }

        /**
         * Copies the specified array.
         *
         * @param original the array to be copied
         * @return a copy of the original array.
         * @throws NullPointerException if <tt>original</tt> is null
         */
        public static char[] copyOf(char[] original){
            synchronized (LOCK){
                return copyOf0(original, original.length);
            }
        }

        /**
         * Copies the specified array.
         *
         * @param original the array to be copied
         * @return a copy of the original array.
         * @throws NullPointerException if <tt>original</tt> is null
         */
        public static float[] copyOf(float[] original){
            synchronized (LOCK){
                return copyOf0(original, original.length);
            }
        }

        /**
         * Copies the specified array.
         *
         * @param original the array to be copied
         * @return a copy of the original array.
         * @throws NullPointerException if <tt>original</tt> is null
         */
        public static double[] copyOf(double[] original){
            synchronized (LOCK){
                return copyOf0(original, original.length);
            }
        }

        /**
         * Copies the specified array.
         *
         * @param original the array to be copied
         * @return a copy of the original array.
         * @throws NullPointerException if <tt>original</tt> is null
         */
        public static boolean[] copyOf(boolean[] original){
            synchronized (LOCK){
                return copyOf0(original, original.length);
            }
        }

        /**
         * Copies the specified array, truncating or padding with nulls (if necessary)
         * so the copy has the specified length.  For all indices that are
         * valid in both the original array and the copy, the two arrays will
         * contain identical values.  For any indices that are valid in the
         * copy but not the original, the copy will contain <tt>null</tt>.
         * Such indices will exist if and only if the specified length
         * is greater than that of the original array.
         * The resulting array is of exactly the same class as the original array.
         *
         * @param <T> the class of the objects in the array
         * @param original the array to be copied
         * @param newLength the length of the copy to be returned
         * @return a copy of the original array, truncated or padded with nulls
         *     to obtain the specified length
         * @throws NegativeArraySizeException if <tt>newLength</tt> is negative
         * @throws NullPointerException if <tt>original</tt> is null
         */
        public static <T> T[] copyOf(T[] original, int newLength) {
            synchronized (LOCK){
                //noinspection unchecked
                return (T[]) copyOf0(original, newLength, original.getClass());
            }
        }

        /**
         * Copies the specified array, truncating or padding with nulls (if necessary)
         * so the copy has the specified length.  For all indices that are
         * valid in both the original array and the copy, the two arrays will
         * contain identical values.  For any indices that are valid in the
         * copy but not the original, the copy will contain <tt>null</tt>.
         * Such indices will exist if and only if the specified length
         * is greater than that of the original array.
         * The resulting array is of the class <tt>newType</tt>.
         *
         * @param <U> the class of the objects in the original array
         * @param <T> the class of the objects in the returned array
         * @param original the array to be copied
         * @param newLength the length of the copy to be returned
         * @param newType the class of the copy to be returned
         * @return a copy of the original array, truncated or padded with nulls
         *     to obtain the specified length
         * @throws NegativeArraySizeException if <tt>newLength</tt> is negative
         * @throws NullPointerException if <tt>original</tt> is null
         * @throws ArrayStoreException if an element copied from
         *     <tt>original</tt> is not of a runtime type that can be stored in
         *     an array of class <tt>newType</tt>
         */
        public static <T,U> T[] copyOf(U[] original, int newLength, Class<? extends T[]> newType) {
            synchronized (LOCK){
                return copyOf0(original, newLength, newType);
            }
        }

        private static <T, U> T[] copyOf0(U[] original, int newLength, Class<? extends T[]> newType){
            @SuppressWarnings({"unchecked", "RedundantCast"})
            T[] copy = ((Object) newType == (Object) Object[].class)
                    ? (T[]) new Object[newLength]
                    : (T[]) java.lang.reflect.Array.newInstance(newType.getComponentType(), newLength);
            //noinspection SuspiciousSystemArraycopy
            System.arraycopy(original, 0, copy, 0, Math.min(original.length, newLength));
            return copy;
        }

        /**
         * Copies the specified array, truncating or padding with zeros (if necessary)
         * so the copy has the specified length.  For all indices that are
         * valid in both the original array and the copy, the two arrays will
         * contain identical values.  For any indices that are valid in the
         * copy but not the original, the copy will contain <tt>(byte)0</tt>.
         * Such indices will exist if and only if the specified length
         * is greater than that of the original array.
         *
         * @param original the array to be copied
         * @param newLength the length of the copy to be returned
         * @return a copy of the original array, truncated or padded with zeros
         *     to obtain the specified length
         * @throws NegativeArraySizeException if <tt>newLength</tt> is negative
         * @throws NullPointerException if <tt>original</tt> is null
         */
        public static byte[] copyOf(byte[] original, int newLength) {
            synchronized (LOCK){
                return copyOf0(original, newLength);
            }
        }

        private static byte[] copyOf0(byte[] original, int newLength){
            byte[] copy = new byte[newLength];
            System.arraycopy(original, 0, copy, 0,
                    Math.min(original.length, newLength));
            return copy;
        }

        /**
         * Copies the specified array, truncating or padding with zeros (if necessary)
         * so the copy has the specified length.  For all indices that are
         * valid in both the original array and the copy, the two arrays will
         * contain identical values.  For any indices that are valid in the
         * copy but not the original, the copy will contain <tt>(short)0</tt>.
         * Such indices will exist if and only if the specified length
         * is greater than that of the original array.
         *
         * @param original the array to be copied
         * @param newLength the length of the copy to be returned
         * @return a copy of the original array, truncated or padded with zeros
         *     to obtain the specified length
         * @throws NegativeArraySizeException if <tt>newLength</tt> is negative
         * @throws NullPointerException if <tt>original</tt> is null
         */
        public static short[] copyOf(short[] original, int newLength) {
            synchronized (LOCK){
                return copyOf0(original, newLength);
            }
        }

        private static short[] copyOf0(short[] original, int newLength) {
            short[] copy = new short[newLength];
            System.arraycopy(original, 0, copy, 0,
                    Math.min(original.length, newLength));
            return copy;
        }

        /**
         * Copies the specified array, truncating or padding with zeros (if necessary)
         * so the copy has the specified length.  For all indices that are
         * valid in both the original array and the copy, the two arrays will
         * contain identical values.  For any indices that are valid in the
         * copy but not the original, the copy will contain <tt>0</tt>.
         * Such indices will exist if and only if the specified length
         * is greater than that of the original array.
         *
         * @param original the array to be copied
         * @param newLength the length of the copy to be returned
         * @return a copy of the original array, truncated or padded with zeros
         *     to obtain the specified length
         * @throws NegativeArraySizeException if <tt>newLength</tt> is negative
         * @throws NullPointerException if <tt>original</tt> is null
         */
        public static int[] copyOf(int[] original, int newLength) {
            synchronized (LOCK){
                return copyOf0(original, newLength);
            }
        }

        private static int[] copyOf0(int[] original, int newLength) {
            int[] copy = new int[newLength];
            System.arraycopy(original, 0, copy, 0, Math.min(original.length, newLength));
            return copy;
        }

        /**
         * Copies the specified array, truncating or padding with zeros (if necessary)
         * so the copy has the specified length.  For all indices that are
         * valid in both the original array and the copy, the two arrays will
         * contain identical values.  For any indices that are valid in the
         * copy but not the original, the copy will contain <tt>0L</tt>.
         * Such indices will exist if and only if the specified length
         * is greater than that of the original array.
         *
         * @param original the array to be copied
         * @param newLength the length of the copy to be returned
         * @return a copy of the original array, truncated or padded with zeros
         *     to obtain the specified length
         * @throws NegativeArraySizeException if <tt>newLength</tt> is negative
         * @throws NullPointerException if <tt>original</tt> is null
         */
        public static long[] copyOf(long[] original, int newLength) {
            synchronized (LOCK){
                return copyOf0(original, newLength);
            }
        }

        private static long[] copyOf0(long[] original, int newLength) {
            long[] copy = new long[newLength];
            System.arraycopy(original, 0, copy, 0, Math.min(original.length, newLength));
            return copy;
        }

        /**
         * Copies the specified array, truncating or padding with null characters (if necessary)
         * so the copy has the specified length.  For all indices that are valid
         * in both the original array and the copy, the two arrays will contain
         * identical values.  For any indices that are valid in the copy but not
         * the original, the copy will contain <tt>'\\u000'</tt>.  Such indices
         * will exist if and only if the specified length is greater than that of
         * the original array.
         *
         * @param original the array to be copied
         * @param newLength the length of the copy to be returned
         * @return a copy of the original array, truncated or padded with null characters
         *     to obtain the specified length
         * @throws NegativeArraySizeException if <tt>newLength</tt> is negative
         * @throws NullPointerException if <tt>original</tt> is null
         */
        public static char[] copyOf(char[] original, int newLength) {
            synchronized (LOCK){
                return copyOf0(original, newLength);
            }
        }

        private static char[] copyOf0(char[] original, int newLength) {
            char[] copy = new char[newLength];
            System.arraycopy(original, 0, copy, 0, Math.min(original.length, newLength));
            return copy;
        }

        /**
         * Copies the specified array, truncating or padding with zeros (if necessary)
         * so the copy has the specified length.  For all indices that are
         * valid in both the original array and the copy, the two arrays will
         * contain identical values.  For any indices that are valid in the
         * copy but not the original, the copy will contain <tt>0f</tt>.
         * Such indices will exist if and only if the specified length
         * is greater than that of the original array.
         *
         * @param original the array to be copied
         * @param newLength the length of the copy to be returned
         * @return a copy of the original array, truncated or padded with zeros
         *     to obtain the specified length
         * @throws NegativeArraySizeException if <tt>newLength</tt> is negative
         * @throws NullPointerException if <tt>original</tt> is null
         */
        public static float[] copyOf(float[] original, int newLength) {
            synchronized (LOCK){
                return copyOf0(original, newLength);
            }
        }

        private static float[] copyOf0(float[] original, int newLength) {
            float[] copy = new float[newLength];
            System.arraycopy(original, 0, copy, 0, Math.min(original.length, newLength));
            return copy;
        }

        /**
         * Copies the specified array, truncating or padding with zeros (if necessary)
         * so the copy has the specified length.  For all indices that are
         * valid in both the original array and the copy, the two arrays will
         * contain identical values.  For any indices that are valid in the
         * copy but not the original, the copy will contain <tt>0d</tt>.
         * Such indices will exist if and only if the specified length
         * is greater than that of the original array.
         *
         * @param original the array to be copied
         * @param newLength the length of the copy to be returned
         * @return a copy of the original array, truncated or padded with zeros
         *     to obtain the specified length
         * @throws NegativeArraySizeException if <tt>newLength</tt> is negative
         * @throws NullPointerException if <tt>original</tt> is null
         */
        public static double[] copyOf(double[] original, int newLength) {
            synchronized (LOCK){
                return copyOf0(original, newLength);
            }
        }

        private static double[] copyOf0(double[] original, int newLength) {
            double[] copy = new double[newLength];
            System.arraycopy(original, 0, copy, 0, Math.min(original.length, newLength));
            return copy;
        }

        /**
         * Copies the specified array, truncating or padding with <tt>false</tt> (if necessary)
         * so the copy has the specified length.  For all indices that are
         * valid in both the original array and the copy, the two arrays will
         * contain identical values.  For any indices that are valid in the
         * copy but not the original, the copy will contain <tt>false</tt>.
         * Such indices will exist if and only if the specified length
         * is greater than that of the original array.
         *
         * @param original the array to be copied
         * @param newLength the length of the copy to be returned
         * @return a copy of the original array, truncated or padded with false elements
         *     to obtain the specified length
         * @throws NegativeArraySizeException if <tt>newLength</tt> is negative
         * @throws NullPointerException if <tt>original</tt> is null
         */
        public static boolean[] copyOf(boolean[] original, int newLength) {
            synchronized (LOCK){
                return copyOf0(original, newLength);
            }
        }

        private static boolean[] copyOf0(boolean[] original, int newLength) {
            boolean[] copy = new boolean[newLength];
            System.arraycopy(original, 0, copy, 0, Math.min(original.length, newLength));
            return copy;
        }

        /**
         * Copies the specified range of the specified array into a new array.
         * The initial index of the range (<tt>from</tt>) must lie between zero
         * and <tt>original.length</tt>, inclusive.  The value at
         * <tt>original[from]</tt> is placed into the initial element of the copy
         * (unless <tt>from == original.length</tt> or <tt>from == to</tt>).
         * Values from subsequent elements in the original array are placed into
         * subsequent elements in the copy.  The final index of the range
         * (<tt>to</tt>), which must be greater than or equal to <tt>from</tt>,
         * may be greater than <tt>original.length</tt>, in which case
         * <tt>null</tt> is placed in all elements of the copy whose index is
         * greater than or equal to <tt>original.length - from</tt>.  The length
         * of the returned array will be <tt>to - from</tt>.
         * <p>
         * The resulting array is of exactly the same class as the original array.
         *
         * @param <T> the class of the objects in the array
         * @param original the array from which a range is to be copied
         * @param from the initial index of the range to be copied, inclusive
         * @param to the final index of the range to be copied, exclusive.
         *     (This index may lie outside the array.)
         * @return a new array containing the specified range from the original array,
         *     truncated or padded with nulls to obtain the required length
         * @throws ArrayIndexOutOfBoundsException if {@code from < 0}
         *     or {@code from > original.length}
         * @throws IllegalArgumentException if <tt>from &gt; to</tt>
         * @throws NullPointerException if <tt>original</tt> is null
         */
        @SuppressWarnings("unchecked")
        public synchronized static <T> T[] copyOfRange(T[] original, int from, int to) {
            synchronized (LOCK){
                return copyOfRange0(original, from, to, (Class<? extends T[]>) original.getClass());
            }
        }

        /**
         * Copies the specified range of the specified array into a new array.
         * The initial index of the range (<tt>from</tt>) must lie between zero
         * and <tt>original.length</tt>, inclusive.  The value at
         * <tt>original[from]</tt> is placed into the initial element of the copy
         * (unless <tt>from == original.length</tt> or <tt>from == to</tt>).
         * Values from subsequent elements in the original array are placed into
         * subsequent elements in the copy.  The final index of the range
         * (<tt>to</tt>), which must be greater than or equal to <tt>from</tt>,
         * may be greater than <tt>original.length</tt>, in which case
         * <tt>null</tt> is placed in all elements of the copy whose index is
         * greater than or equal to <tt>original.length - from</tt>.  The length
         * of the returned array will be <tt>to - from</tt>.
         * The resulting array is of the class <tt>newType</tt>.
         *
         * @param <U> the class of the objects in the original array
         * @param <T> the class of the objects in the returned array
         * @param original the array from which a range is to be copied
         * @param from the initial index of the range to be copied, inclusive
         * @param to the final index of the range to be copied, exclusive.
         *     (This index may lie outside the array.)
         * @param newType the class of the copy to be returned
         * @return a new array containing the specified range from the original array,
         *     truncated or padded with nulls to obtain the required length
         * @throws ArrayIndexOutOfBoundsException if {@code from < 0}
         *     or {@code from > original.length}
         * @throws IllegalArgumentException if <tt>from &gt; to</tt>
         * @throws NullPointerException if <tt>original</tt> is null
         * @throws ArrayStoreException if an element copied from
         *     <tt>original</tt> is not of a runtime type that can be stored in
         *     an array of class <tt>newType</tt>.
         */
        public synchronized static <T,U> T[] copyOfRange(U[] original, int from, int to, Class<? extends T[]> newType) {
            synchronized (LOCK){
                return copyOfRange0(original, from, to, newType);
            }
        }

        private static <T,U> T[] copyOfRange0(U[] original, int from, int to, Class<? extends T[]> newType) {
            int newLength = to - from;
            if (newLength < 0) {
                throw new IllegalArgumentException(from + " > " + to);
            }
            @SuppressWarnings({"unchecked", "RedundantCast"})
            T[] copy = ((Object)newType == (Object)Object[].class)
                    ? (T[]) new Object[newLength]
                    : (T[]) java.lang.reflect.Array.newInstance(newType.getComponentType(), newLength);
            //noinspection SuspiciousSystemArraycopy
            System.arraycopy(original, from, copy, 0, Math.min(original.length - from, newLength));
            return copy;
        }

        /**
         * Copies the specified range of the specified array into a new array.
         * The initial index of the range (<tt>from</tt>) must lie between zero
         * and <tt>original.length</tt>, inclusive.  The value at
         * <tt>original[from]</tt> is placed into the initial element of the copy
         * (unless <tt>from == original.length</tt> or <tt>from == to</tt>).
         * Values from subsequent elements in the original array are placed into
         * subsequent elements in the copy.  The final index of the range
         * (<tt>to</tt>), which must be greater than or equal to <tt>from</tt>,
         * may be greater than <tt>original.length</tt>, in which case
         * <tt>(byte)0</tt> is placed in all elements of the copy whose index is
         * greater than or equal to <tt>original.length - from</tt>.  The length
         * of the returned array will be <tt>to - from</tt>.
         *
         * @param original the array from which a range is to be copied
         * @param from the initial index of the range to be copied, inclusive
         * @param to the final index of the range to be copied, exclusive.
         *     (This index may lie outside the array.)
         * @return a new array containing the specified range from the original array,
         *     truncated or padded with zeros to obtain the required length
         * @throws ArrayIndexOutOfBoundsException if {@code from < 0}
         *     or {@code from > original.length}
         * @throws IllegalArgumentException if <tt>from &gt; to</tt>
         * @throws NullPointerException if <tt>original</tt> is null
         */
        public static byte[] copyOfRange(byte[] original, int from, int to) {
            synchronized (LOCK){
                int newLength = to - from;
                if (newLength < 0) throw new IllegalArgumentException(from + " > " + to);
                byte[] copy = new byte[newLength];
                System.arraycopy(original, from, copy, 0, Math.min(original.length - from, newLength));
                return copy;
            }
        }

        /**
         * Copies the specified range of the specified array into a new array.
         * The initial index of the range (<tt>from</tt>) must lie between zero
         * and <tt>original.length</tt>, inclusive.  The value at
         * <tt>original[from]</tt> is placed into the initial element of the copy
         * (unless <tt>from == original.length</tt> or <tt>from == to</tt>).
         * Values from subsequent elements in the original array are placed into
         * subsequent elements in the copy.  The final index of the range
         * (<tt>to</tt>), which must be greater than or equal to <tt>from</tt>,
         * may be greater than <tt>original.length</tt>, in which case
         * <tt>(short)0</tt> is placed in all elements of the copy whose index is
         * greater than or equal to <tt>original.length - from</tt>.  The length
         * of the returned array will be <tt>to - from</tt>.
         *
         * @param original the array from which a range is to be copied
         * @param from the initial index of the range to be copied, inclusive
         * @param to the final index of the range to be copied, exclusive.
         *     (This index may lie outside the array.)
         * @return a new array containing the specified range from the original array,
         *     truncated or padded with zeros to obtain the required length
         * @throws ArrayIndexOutOfBoundsException if {@code from < 0}
         *     or {@code from > original.length}
         * @throws IllegalArgumentException if <tt>from &gt; to</tt>
         * @throws NullPointerException if <tt>original</tt> is null
         */
        public static short[] copyOfRange(short[] original, int from, int to) {
            synchronized (LOCK){
                int newLength = to - from;
                if (newLength < 0) throw new IllegalArgumentException(from + " > " + to);
                short[] copy = new short[newLength];
                System.arraycopy(original, from, copy, 0, Math.min(original.length - from, newLength));
                return copy;
            }
        }

        /**
         * Copies the specified range of the specified array into a new array.
         * The initial index of the range (<tt>from</tt>) must lie between zero
         * and <tt>original.length</tt>, inclusive.  The value at
         * <tt>original[from]</tt> is placed into the initial element of the copy
         * (unless <tt>from == original.length</tt> or <tt>from == to</tt>).
         * Values from subsequent elements in the original array are placed into
         * subsequent elements in the copy.  The final index of the range
         * (<tt>to</tt>), which must be greater than or equal to <tt>from</tt>,
         * may be greater than <tt>original.length</tt>, in which case
         * <tt>0</tt> is placed in all elements of the copy whose index is
         * greater than or equal to <tt>original.length - from</tt>.  The length
         * of the returned array will be <tt>to - from</tt>.
         *
         * @param original the array from which a range is to be copied
         * @param from the initial index of the range to be copied, inclusive
         * @param to the final index of the range to be copied, exclusive.
         *     (This index may lie outside the array.)
         * @return a new array containing the specified range from the original array,
         *     truncated or padded with zeros to obtain the required length
         * @throws ArrayIndexOutOfBoundsException if {@code from < 0}
         *     or {@code from > original.length}
         * @throws IllegalArgumentException if <tt>from &gt; to</tt>
         * @throws NullPointerException if <tt>original</tt> is null
         */
        public static int[] copyOfRange(int[] original, int from, int to) {
            synchronized (LOCK){
                int newLength = to - from;
                if (newLength < 0) throw new IllegalArgumentException(from + " > " + to);
                int[] copy = new int[newLength];
                System.arraycopy(original, from, copy, 0, Math.min(original.length - from, newLength));
                return copy;
            }
        }

        /**
         * Copies the specified range of the specified array into a new array.
         * The initial index of the range (<tt>from</tt>) must lie between zero
         * and <tt>original.length</tt>, inclusive.  The value at
         * <tt>original[from]</tt> is placed into the initial element of the copy
         * (unless <tt>from == original.length</tt> or <tt>from == to</tt>).
         * Values from subsequent elements in the original array are placed into
         * subsequent elements in the copy.  The final index of the range
         * (<tt>to</tt>), which must be greater than or equal to <tt>from</tt>,
         * may be greater than <tt>original.length</tt>, in which case
         * <tt>0L</tt> is placed in all elements of the copy whose index is
         * greater than or equal to <tt>original.length - from</tt>.  The length
         * of the returned array will be <tt>to - from</tt>.
         *
         * @param original the array from which a range is to be copied
         * @param from the initial index of the range to be copied, inclusive
         * @param to the final index of the range to be copied, exclusive.
         *     (This index may lie outside the array.)
         * @return a new array containing the specified range from the original array,
         *     truncated or padded with zeros to obtain the required length
         * @throws ArrayIndexOutOfBoundsException if {@code from < 0}
         *     or {@code from > original.length}
         * @throws IllegalArgumentException if <tt>from &gt; to</tt>
         * @throws NullPointerException if <tt>original</tt> is null
         */
        public static long[] copyOfRange(long[] original, int from, int to) {
            synchronized (LOCK){
                int newLength = to - from;
                if (newLength < 0) throw new IllegalArgumentException(from + " > " + to);
                long[] copy = new long[newLength];
                System.arraycopy(original, from, copy, 0, Math.min(original.length - from, newLength));
                return copy;
            }
        }

        /**
         * Copies the specified range of the specified array into a new array.
         * The initial index of the range (<tt>from</tt>) must lie between zero
         * and <tt>original.length</tt>, inclusive.  The value at
         * <tt>original[from]</tt> is placed into the initial element of the copy
         * (unless <tt>from == original.length</tt> or <tt>from == to</tt>).
         * Values from subsequent elements in the original array are placed into
         * subsequent elements in the copy.  The final index of the range
         * (<tt>to</tt>), which must be greater than or equal to <tt>from</tt>,
         * may be greater than <tt>original.length</tt>, in which case
         * <tt>'\\u000'</tt> is placed in all elements of the copy whose index is
         * greater than or equal to <tt>original.length - from</tt>.  The length
         * of the returned array will be <tt>to - from</tt>.
         *
         * @param original the array from which a range is to be copied
         * @param from the initial index of the range to be copied, inclusive
         * @param to the final index of the range to be copied, exclusive.
         *     (This index may lie outside the array.)
         * @return a new array containing the specified range from the original array,
         *     truncated or padded with null characters to obtain the required length
         * @throws ArrayIndexOutOfBoundsException if {@code from < 0}
         *     or {@code from > original.length}
         * @throws IllegalArgumentException if <tt>from &gt; to</tt>
         * @throws NullPointerException if <tt>original</tt> is null
         */
        public static char[] copyOfRange(char[] original, int from, int to) {
            synchronized (LOCK){
                int newLength = to - from;
                if (newLength < 0) throw new IllegalArgumentException(from + " > " + to);
                char[] copy = new char[newLength];
                System.arraycopy(original, from, copy, 0, Math.min(original.length - from, newLength));
                return copy;
            }
        }

        /**
         * Copies the specified range of the specified array into a new array.
         * The initial index of the range (<tt>from</tt>) must lie between zero
         * and <tt>original.length</tt>, inclusive.  The value at
         * <tt>original[from]</tt> is placed into the initial element of the copy
         * (unless <tt>from == original.length</tt> or <tt>from == to</tt>).
         * Values from subsequent elements in the original array are placed into
         * subsequent elements in the copy.  The final index of the range
         * (<tt>to</tt>), which must be greater than or equal to <tt>from</tt>,
         * may be greater than <tt>original.length</tt>, in which case
         * <tt>0f</tt> is placed in all elements of the copy whose index is
         * greater than or equal to <tt>original.length - from</tt>.  The length
         * of the returned array will be <tt>to - from</tt>.
         *
         * @param original the array from which a range is to be copied
         * @param from the initial index of the range to be copied, inclusive
         * @param to the final index of the range to be copied, exclusive.
         *     (This index may lie outside the array.)
         * @return a new array containing the specified range from the original array,
         *     truncated or padded with zeros to obtain the required length
         * @throws ArrayIndexOutOfBoundsException if {@code from < 0}
         *     or {@code from > original.length}
         * @throws IllegalArgumentException if <tt>from &gt; to</tt>
         * @throws NullPointerException if <tt>original</tt> is null
         */
        public static float[] copyOfRange(float[] original, int from, int to) {
            synchronized (LOCK){
                int newLength = to - from;
                if (newLength < 0) throw new IllegalArgumentException(from + " > " + to);
                float[] copy = new float[newLength];
                System.arraycopy(original, from, copy, 0, Math.min(original.length - from, newLength));
                return copy;
            }
        }

        /**
         * Copies the specified range of the specified array into a new array.
         * The initial index of the range (<tt>from</tt>) must lie between zero
         * and <tt>original.length</tt>, inclusive.  The value at
         * <tt>original[from]</tt> is placed into the initial element of the copy
         * (unless <tt>from == original.length</tt> or <tt>from == to</tt>).
         * Values from subsequent elements in the original array are placed into
         * subsequent elements in the copy.  The final index of the range
         * (<tt>to</tt>), which must be greater than or equal to <tt>from</tt>,
         * may be greater than <tt>original.length</tt>, in which case
         * <tt>0d</tt> is placed in all elements of the copy whose index is
         * greater than or equal to <tt>original.length - from</tt>.  The length
         * of the returned array will be <tt>to - from</tt>.
         *
         * @param original the array from which a range is to be copied
         * @param from the initial index of the range to be copied, inclusive
         * @param to the final index of the range to be copied, exclusive.
         *     (This index may lie outside the array.)
         * @return a new array containing the specified range from the original array,
         *     truncated or padded with zeros to obtain the required length
         * @throws ArrayIndexOutOfBoundsException if {@code from < 0}
         *     or {@code from > original.length}
         * @throws IllegalArgumentException if <tt>from &gt; to</tt>
         * @throws NullPointerException if <tt>original</tt> is null
         */
        public static double[] copyOfRange(double[] original, int from, int to) {
            synchronized (LOCK){
                int newLength = to - from;
                if (newLength < 0) throw new IllegalArgumentException(from + " > " + to);
                double[] copy = new double[newLength];
                System.arraycopy(original, from, copy, 0, Math.min(original.length - from, newLength));
                return copy;
            }
        }

        /**
         * Copies the specified range of the specified array into a new array.
         * The initial index of the range (<tt>from</tt>) must lie between zero
         * and <tt>original.length</tt>, inclusive.  The value at
         * <tt>original[from]</tt> is placed into the initial element of the copy
         * (unless <tt>from == original.length</tt> or <tt>from == to</tt>).
         * Values from subsequent elements in the original array are placed into
         * subsequent elements in the copy.  The final index of the range
         * (<tt>to</tt>), which must be greater than or equal to <tt>from</tt>,
         * may be greater than <tt>original.length</tt>, in which case
         * <tt>false</tt> is placed in all elements of the copy whose index is
         * greater than or equal to <tt>original.length - from</tt>.  The length
         * of the returned array will be <tt>to - from</tt>.
         *
         * @param original the array from which a range is to be copied
         * @param from the initial index of the range to be copied, inclusive
         * @param to the final index of the range to be copied, exclusive.
         *     (This index may lie outside the array.)
         * @return a new array containing the specified range from the original array,
         *     truncated or padded with false elements to obtain the required length
         * @throws ArrayIndexOutOfBoundsException if {@code from < 0}
         *     or {@code from > original.length}
         * @throws IllegalArgumentException if <tt>from &gt; to</tt>
         * @throws NullPointerException if <tt>original</tt> is null
         */
        public static boolean[] copyOfRange(boolean[] original, int from, int to) {
            synchronized (LOCK){
                int newLength = to - from;
                if (newLength < 0) throw new IllegalArgumentException(from + " > " + to);
                boolean[] copy = new boolean[newLength];
                System.arraycopy(original, from, copy, 0, Math.min(original.length - from, newLength));
                return copy;
            }
        }

        /**
         * Returns true if the arrays are equal, false otherwise.
         * The arrays are equal iff:
         * -They have the same length.
         * -The elements are the same for any given index.
         *
         * @param one one array.
         * @param two the other array.
         * @return true if the arrays are equal, false otherwise.
         */
        public static boolean equals(int[] one, int[] two){
            synchronized (LOCK){
                return equals0(one, two);
            }
        }

        private static boolean equals0(int[] one, int[] two){
            if (one == null || two == null) return one == null && two == null;
            if (one.length != two.length) return false;
            for (int i = 0; i < one.length; i++) {
                if (!(one[i] == two[i])) return false;
            }
            return true;
        }

        /**
         * Returns true if the arrays are equal, false otherwise.
         * The arrays are equal iff:
         * -They have the same length.
         * -The elements are the same for any given index.
         *
         * @param one one array.
         * @param two the other array.
         * @return true if the arrays are equal, false otherwise.
         */
        public static boolean equals(double[] one, double[] two){
            synchronized (LOCK){
                return equals0(one, two);
            }
        }

        private static boolean equals0(double[] one, double[] two){
            if (one == null || two == null) return one == null && two == null;
            if (one.length != two.length) return false;
            for (int i = 0; i < one.length; i++) {
                if (!(one[i] == two[i])) return false;
            }
            return true;
        }

        /**
         * Returns true if the arrays are equal, false otherwise.
         * The arrays are equal iff:
         * -They have the same length.
         * -The elements are the same for any given index.
         *
         * @param one one array.
         * @param two the other array.
         * @return true if the arrays are equal, false otherwise.
         */
        public static boolean equals(float[] one, float[] two){
            synchronized (LOCK){
                return equals0(one, two);
            }
        }

        private static boolean equals0(float[] one, float[] two){
            if (one == null || two == null) return one == null && two == null;
            if (one.length != two.length) return false;
            for (int i = 0; i < one.length; i++) {
                if (!(one[i] == two[i])) return false;
            }
            return true;
        }

        /**
         * Returns true if the arrays are equal, false otherwise.
         * The arrays are equal iff:
         * -They have the same length.
         * -The elements are the same for any given index.
         *
         * @param one one array.
         * @param two the other array.
         * @return true if the arrays are equal, false otherwise.
         */
        public static boolean equals(byte[] one, byte[] two){
            synchronized (LOCK){
                return equals0(one, two);
            }
        }

        private static boolean equals0(byte[] one, byte[] two){
            if (one == null || two == null) return one == null && two == null;
            if (one.length != two.length) return false;
            for (int i = 0; i < one.length; i++) {
                if (!(one[i] == two[i])) return false;
            }
            return true;
        }

        /**
         * Returns true if the arrays are equal, false otherwise.
         * The arrays are equal iff:
         * -They have the same length.
         * -The elements are the same for any given index.
         *
         * @param one one array.
         * @param two the other array.
         * @return true if the arrays are equal, false otherwise.
         */
        public static boolean equals(short[] one, short[] two){
            synchronized (LOCK){
                return equals0(one, two);
            }
        }

        private static boolean equals0(short[] one, short[] two){
            if (one == null || two == null) return one == null && two == null;
            if (one.length != two.length)return false;
            for (int i = 0; i < one.length; i++) {
                if (!(one[i] == two[i])) return false;
            }
            return true;
        }

        /**
         * Returns true if the arrays are equal, false otherwise.
         * The arrays are equal iff:
         * -They have the same length.
         * -The elements are the same for any given index.
         *
         * @param one one array.
         * @param two the other array.
         * @return true if the arrays are equal, false otherwise.
         */
        public static boolean equals(char[] one, char[] two){
            synchronized (LOCK){
                return equals0(one, two);
            }
        }

        private static boolean equals0(char[] one, char[] two){
            if (one == null || two == null) return one == null && two == null;
            if (one.length != two.length) return false;
            for (int i = 0; i < one.length; i++) {
                if (!(one[i] == two[i])) return false;
            }
            return true;
        }

        /**
         * Returns true if the arrays are equal, false otherwise.
         * The arrays are equal iff:
         * -They have the same length.
         * -The elements are the same for any given index.
         *
         * @param one one array.
         * @param two the other array.
         * @return true if the arrays are equal, false otherwise.
         */
        public static boolean equals(long[] one, long[] two){
            synchronized (LOCK){
                return equals0(one, two);
            }
        }

        private static boolean equals0(long[] one, long[] two){
            if (one == null || two == null) return one == null && two == null;
            if (one.length != two.length) return false;
            for (int i = 0; i < one.length; i++) {
                if (!(one[i] == two[i])) return false;
            }
            return true;
        }

        /**
         * Returns true if the arrays are equal, false otherwise.
         * The arrays are equal iff:
         * -They have the same length.
         * -The elements are the same for any given index.
         *
         * @param one one array.
         * @param two the other array.
         * @return true if the arrays are equal, false otherwise.
         */
        public static boolean equals(boolean[] one, boolean[] two){
            synchronized (LOCK){
                return equals0(one, two);
            }
        }

        private static boolean equals0(boolean[] one, boolean[] two){
            if (one == null || two == null) return one == null && two == null;
            if (one.length != two.length) return false;
            for (int i = 0; i < one.length; i++) {
                if (!(one[i] == two[i])) return false;
            }
            return true;
        }

        /**
         * Returns true if the arrays are equal, false otherwise.
         * The arrays are equal iff:
         * -They have the same length.
         * -The elements are the same for any given index.
         *
         * @param one one array.
         * @param two the other array.
         * @param <T> the type of array
         * @return true if the arrays are equal, false otherwise.
         */
        public static <T> boolean equals(T[] one, T[] two){
            synchronized (LOCK){
                return equals0(one, two);
            }
        }

        private static <T> boolean equals0(T[] one, T[] two){
            if (one == null || two == null) return one == null && two == null;
            if (one.length != two.length) return false;
            for (int i = 0; i < one.length; i++) {
                boolean equal;
                if (one[i] == null || two[i] == null) equal = (one[i] == null && two[i] == null);
                else if (Arrays.isArray(one[i]) /*Is array?*/ && (one[i].getClass() == two[i].getClass()) /*Same array type?*/)
                    equal = arrayEquals(one[i], two[i]);
                else equal = Objects.equals(one[i], two[i]);
                if (!equal) return false;
            }
            return true;
        }

        /**
         * Returns true if the arrays are equal, false otherwise.
         * The arrays are equal iff:
         * -They have the same length.
         * -The elements are the same for any given index.
         *
         * @param t one array
         * @param u the other array
         * @return true if the arrays are equal, false otherwise
         *
         * @throws IllegalArgumentException if <code> t </code> or
         *         <code> u </code> is null, or if <code> t </code>
         *         and <code> u </code> are not the same array class.
         */
        private static <T> boolean arrayEquals(T t, T u){
            if (t == null) throw new IllegalArgumentException("Array 1 is null for call to arrayEquals()");
            if (u == null) throw new IllegalArgumentException("Array 2 is null for call to arrayEquals()");
            if (t instanceof int[] && u instanceof int[]) return equals0((int[]) t, (int[]) u);
            else if (t instanceof double[] && u instanceof double[]) return equals0((double[]) t, (double[]) u);
            else if (t instanceof float[] && u instanceof float[]) return equals0((float[]) t, (float[]) u);
            else if (t instanceof byte[] && u instanceof byte[]) return equals0((byte[]) t, (byte[]) u);
            else if (t instanceof short[] && u instanceof short[]) return equals0((short[]) t, (short[]) u);
            else if (t instanceof char[] && u instanceof char[]) return equals0((char[]) t, (char[]) u);
            else if (t instanceof long[] && u instanceof long[])  return equals0((long[]) t, (long[]) u);
            else if (t instanceof boolean[] && u instanceof boolean[]) return equals0((boolean[]) t, (boolean[]) u);
            else if (t instanceof Object[] && u instanceof Object[]) return equals0((Object[]) t, (Object[]) u);
            else throw new IllegalArgumentException("Illegal class combination for arrayEquals(): t: " + t.getClass().toString()
                        + " u:" + u.getClass().toString());
        }

        /**
         * Returns the hash code of the given array.
         *
         * @param arr the array
         * @return the hash code of the given array.
         */
        public static <T> int hashCode(T[] arr){
            synchronized (LOCK){
                return hashCode0(arr);
            }
        }
        private static <T> int hashCode0(T[] arr){
            if (arr == null) return 0;
            int result = 1;
            for (T element : arr){
                int elementHash = (element != null ? element.hashCode() : 0);
                result = 31 * result + elementHash;
            }
            return result;
        }

        /**
         * Returns the "deep" hash code of the given array.
         * The deep hash code is obtained by finding the hash
         * code of each individual element of the array (and calling
         * the appropriate methods to find the hash code of elements
         * that are arrays.)
         *
         * @param arr the array
         * @return the hash code of the given array.
         *
         * @implNote behavior is undefined if the array contains itself
         */
        public static <T> int deepHashCode(T[] arr) {
            synchronized (LOCK){
               return deepHashCode0(arr);
            }
        }

        public static <T> int deepHashCode0(T[] arr) {
            if (arr == null) return 0;
            int result = 1;
            for (T element : arr) {
                int elementHash = 0;
                if (element instanceof Object[]) elementHash = deepHashCode0((Object[]) element);
                else if (element instanceof byte[]) elementHash = hashCode0((byte[]) element);
                else if (element instanceof short[]) elementHash = hashCode0((short[]) element);
                else if (element instanceof int[]) elementHash = hashCode0((int[]) element);
                else if (element instanceof long[]) elementHash = hashCode0((long[]) element);
                else if (element instanceof char[]) elementHash = hashCode0((char[]) element);
                else if (element instanceof float[]) elementHash = hashCode0((float[]) element);
                else if (element instanceof double[]) elementHash = hashCode0((double[]) element);
                else if (element instanceof boolean[]) elementHash = hashCode0((boolean[]) element);
                else if (element != null) elementHash = element.hashCode();
                result = 31 * result + elementHash;
            }
            return result;
        }

        /**
         * Returns the hash code of the given array.
         *
         * @param arr the array
         * @return the hash code of the given array.
         */
        public static int hashCode(int[] arr){
            synchronized (LOCK){
                return hashCode0(arr);
            }
        }

        private static int hashCode0(int[] arr){
            if (arr == null) return 0;
            int result = 1;
            for (int element : arr){
                result = 31 * result + element;
            }
            return result;
        }

        /**
         * Returns the hash code of the given array.
         *
         * @param arr the array
         * @return the hash code of the given array.
         */
        public static int hashCode(double[] arr){
            synchronized (LOCK){
                return hashCode0(arr);
            }
        }

        private static int hashCode0(double[] arr){
            if (arr == null) return 0;
            int result = 1;
            for (double element : arr) {
                long bits = Double.doubleToLongBits(element);
                result = 31 * result + (int)(bits ^ (bits >>> 32));
            }
            return result;
        }

        /**
         * Returns the hash code of the given array.
         *
         * @param arr the array
         * @return the hash code of the given array.
         */
        public static int hashCode(float[] arr){
            synchronized (LOCK){
                return hashCode0(arr);
            }
        }

        private static int hashCode0(float[] arr){
            if (arr == null) return 0;
            int result = 1;
            for (float element : arr){
                result = 31 * result + Float.floatToIntBits(element);
            }
            return result;
        }

        /**
         * Returns the hash code of the given array.
         *
         * @param arr the array
         * @return the hash code of the given array.
         */
        public static int hashCode(byte[] arr){
            synchronized (LOCK){
                return hashCode0(arr);
            }
        }

        private static int hashCode0(byte[] arr){
            if (arr == null) return 0;
            int result = 1;
            for (byte element : arr){
                result = 31 * result + element;
            }
            return result;
        }

        /**
         * Returns the hash code of the given array.
         *
         * @param arr the array
         * @return the hash code of the given array.
         */
        public static int hashCode(char[] arr){
            synchronized (LOCK){
                return hashCode0(arr);
            }
        }

        private static int hashCode0(char[] arr){
            if (arr == null) return 0;
            int result = 1;
            for (char element : arr){
                result = 31 * result + element;
            }
            return result;
        }

        /**
         * Returns the hash code of the given array.
         *
         * @param arr the array
         * @return the hash code of the given array.
         */
        public static int hashCode(short[] arr){
            synchronized (LOCK){
                return hashCode0(arr);
            }
        }

        private static int hashCode0(short[] arr){
            if (arr == null) return 0;
            int result = 1;
            for (short element : arr){
                result = 31 * result + element;
            }
            return result;
        }

        /**
         * Returns the hash code of the given array.
         *
         * @param arr the array
         * @return the hash code of the given array.
         */
        public static int hashCode(long[] arr) {
            synchronized (LOCK){
                return hashCode0(arr);
            }
        }

        private static int hashCode0(long[] arr) {
            if (arr == null) return 0;
            int result = 1;
            for (long element : arr) {
                int elementHash = (int)(element ^ (element >>> 32));
                result = 31 * result + elementHash;
            }
            return result;
        }

        /**
         * Returns the hash code of the given array.
         *
         * @param arr the array
         * @return the hash code of the given array.
         */
        public static int hashCode(boolean[] arr){
            synchronized (LOCK){
                return hashCode0(arr);
            }
        }

        private static int hashCode0(boolean[] arr){
            if (arr == null) return 0;
            int result = 1;
            for (boolean element : arr) {
                result = 31 * result + (element ? 1231 : 1237);
            }
            return result;
        }

        /**
         * @return an array of the remaining items of the iterator
         */
        @SuppressWarnings("unchecked")
        public static <T> T[] getArray(Iterator<T> it){
            synchronized (LOCK){
                Objects.requireNonNull(it, "Iterator cannot be null");
                ArrayList<T> tmp = getList0(it);
                if (tmp.size() == 0) return (T[]) new Object[0];
                return tmp.toArray((T[]) new Object[tmp.size()]);
            }
        }

        /**
         * @return an ArrayList of the remaining items of the iterator
         */
        public static <T> ArrayList<T> getList(Iterator<T> it){
            synchronized (LOCK){
                return getList0(it);
            }
        }

        private static <T> ArrayList<T> getList0(Iterator<T> it){
            Objects.requireNonNull(it, "Iterator cannot be null");
            if (!it.hasNext()) new ArrayList<>(0);
            ArrayList<T> vals = new ArrayList<>();
            while (it.hasNext()) vals.add(it.next());
            return vals;
        }

        /**
         * @return an array of the remaining items of the iterator
         */
        public static Object[] getUnParametrizedArray(Iterator<?> it){
            synchronized (LOCK){
                Objects.requireNonNull(it, "Iterator cannot be null");
                ArrayList<Object> tmp = getUnParametrizedList0(it);
                if (tmp.size() == 0) return new Object[0];
                return tmp.toArray();
            }
        }

        /**
         * @return an ArrayList of the remaining items of the iterator
         */
        public static ArrayList<Object> getUnParametrizedList(Iterator<?> it){
            synchronized (LOCK){
                return getUnParametrizedList0(it);
            }
        }

        private static ArrayList<Object> getUnParametrizedList0(Iterator<?> it){
            Objects.requireNonNull(it, "Iterator cannot be null");
            if (!it.hasNext()) new ArrayList<>(0);
            ArrayList<Object> vals = new ArrayList<>();
            while (it.hasNext()) vals.add(it.next());
            return vals;
        }
    }

    /**
     * Class that deals with mathematical operations
     */
    public static final class Maths {
        private Maths(){}

        /**
         * @return the maximum of two values {@code a} and {@code b}
         */
        public static int max(int a, int b){
            return (a >= b) ? a : b;
        }

        /**
         * @return the maximum of two values {@code a} and {@code b}
         */
        public static double max(double a, double b){
            return (a >= b) ? a : b;
        }

        /**
         * @return the maximum of two values {@code a} and {@code b}
         */
        public static float max(float a, float b){
            return (a >= b) ? a : b;
        }

        /**
         * @return the maximum of two values {@code a} and {@code b}
         */
        public static long max(long a, long b){
            return (a >= b) ? a : b;
        }

        /**
         * @return the minimum of two values {@code a} and {@code b}
         */
        public static int min(int a, int b){
            return (a <= b) ? a : b;
        }

        /**
         * @return the minimum of two values {@code a} and {@code b}
         */
        public static double min(double a, double b){
            return (a <= b) ? a : b;
        }

        /**
         * @return the minimum of two values {@code a} and {@code b}
         */
        public static float min(float a, float b){
            return (a <= b) ? a : b;
        }

        /**
         * @return the minimum of two values {@code a} and {@code b}
         */
        public static long min(long a, long b){
            return (a <= b) ? a : b;
        }

        /**
         * @return the the largest integer of {@code d}
         * ex.
         * - d = 1.01 return => 1
         * - d = 1.99 return => 1
         */
        public static double floor(double d){
            return (int) d;
        }

        /**
         * @return the the largest integer of {@code f}
         * ex.
         * - f = 1.01 return => 1
         * - f = 1.99 return => 1
         */
        public static float floor(float f){
            return (int) f;
        }

        /**
         * @return the the largest integer of {@code l}
         * ex.
         * - l = 1.01 return => 1
         * - l = 1.99 return => 1
         */
        public static long floor(long l){
            return (int) l;
        }

        /**
         * @return the absolute value of {@code i}
         */
        public static int abs(int i){
            return (i < 0) ? -i : i;
        }

        /**
         * @return the absolute value of {@code i}
         */
        public static double abs(double d){
            return (d < 0) ? -d : d;
        }

        /**
         * @return the absolute value of {@code i}
         */
        public static float abs(float f){
            return (f < 0) ? -f : f;
        }

        /**
         * @return the absolute value of {@code i}
         */
        public static long abs(long l){
            return (l < 0) ? -l : l;
        }

        /**
         * @return {@code base} raised to the {@code exponent} power
         */
        public static double pow(double base, int exponent){
            if (exponent == 0) return 1.0;
            if (base == 0) return 0.0;
            double sum = 1.0;
            for (int i = 0; i < exponent; i++) sum *= base;
            return sum;
        }

        /**
         * @return {@code base} raised to the {@code exponent} power
         */
        public static int pow(int base, int exponent){
            if (exponent == 0) return 1;
            if (base == 0) return 0;
            int sum = 1;
            for (int i = 0; i < exponent; i++) sum *= base;
            return sum;
        }
    }

    /**
     * Class that deals with Array sorting
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static final class Sorts {
        private static final class SortType{
            private SortType(final String type){}
        }
        private Sorts(){
            throw new InternalError("No instancing this class!");
        }

        private static final Lock LOCK = new Lock();

        //Sorting types
        public static final SortType BUBBLE_SORT = new SortType("Bubble Sort");
        public static final SortType SELECTION_SORT = new SortType("Selection Sort");
        public static final SortType INSERTION_SORT = new SortType("Insertion Sort");
        public static final SortType MERGE_SORT = new SortType("Merge Sort");
        public static final SortType QUICK_SORT = new SortType("Quick Sort");

        //Tuning Constants
        /**
         * If the length of an array to be sorted is less than this
         * constant, bubble sort is used in preference to insertion sort.
         */
        private static final int BUBBLE_SORT_THRESHOLD = 10;
        /**
         * If the length of an array to be sorted is less than this
         * constant, insertion sort is used in preference to Quicksort.
         */
        private static final int INSERTION_SORT_THRESHOLD = 47;
        /**
         * If the length of an array to be sorted is less than this
         * constant, Quicksort is used in preference to merge sort.
         */
        private static final int QUICKSORT_THRESHOLD = 286;

        //int array sorting

        public static boolean isSorted(final int[] arr){
            synchronized (LOCK){
                if (arr == null) return false;
                if (arr.length < 2) return true;
                for (int i = 1; i < arr.length; i++) {
                    if (!(arr[i - 1] <= arr[i])) return false;
                }
                return true;
            }
        }

        public static int[] sort(final int[] arr, final SortType type){
            Objects.requireNonNull(arr, "Array cannot be null");
            Objects.requireNonNull(type, "SortType cannot be null");
            if (arr.length < 2) return arr;
            if (type == BUBBLE_SORT) return bubbleSort(arr);
            else if (type == SELECTION_SORT) return selectionSort(arr);
            else if (type == INSERTION_SORT) return insertionSort(arr);
            else if (type == MERGE_SORT) return mergeSort(arr);
            else if (type == QUICK_SORT) return quickSort(arr);
            else throw new IllegalArgumentException("Illegal SortType"); //Should never happen
        }

        /**
         * Determines what type of sorting algorithm to use on the given array.
         * @see Sorts#BUBBLE_SORT_THRESHOLD
         * @see Sorts#INSERTION_SORT_THRESHOLD
         * @see Sorts#QUICKSORT_THRESHOLD
         *
         * @param arr the array.
         * @return the sorted array.
         */
        public static int[] optimizedSort(final int[] arr){
            Objects.requireNonNull(arr, "Array cannot be null");
            if (arr.length < 2) return arr;
            final int len = arr.length;
            if (len < BUBBLE_SORT_THRESHOLD) return bubbleSort(arr);
            else if (len < INSERTION_SORT_THRESHOLD) return insertionSort(arr);
            else if (len < QUICKSORT_THRESHOLD) return quickSort(arr);
            else return mergeSort(arr); //if (len >= QUICKSORT_THRESHOLD)
        }

        /**
         * Sorts the array if if is not already sorted.
         *
         * @param arr the array.
         * @return the sorted array, or the array itself if it is already sorted.
         */
        public static int[] sortIfNotSorted(final int[] arr){
            Objects.requireNonNull(arr, "Array cannot be null");
            if (isSorted(arr)) return arr;
            else return optimizedSort(arr);
        }
        /**
         * Sorts the array if if is not already sorted.
         *
         * @param arr the array.
         * @param type the type of sort to use.
         * @return the sorted array, or the array itself if it is already sorted.
         */
        public static int[] sortIfNotSorted(final int[] arr, final SortType type){
            Objects.requireNonNull(arr, "Array cannot be null");
            Objects.requireNonNull(type, "SortType cannot be null");
            if (isSorted(arr)) return arr;
            else return sort(arr, type);
        }

        /**
         * Bubble Sort.
         * Best time O(n)
         * Average time O(n^2)
         * Worst time O(n^2)
         *
         * @param arr the array
         * @return the sorted array
         */
        public static int[] bubbleSort(final int[] arr){
            Objects.requireNonNull(arr, "Array cannot be null");
            if (arr.length < 2) return arr;
            for (int i = 0; i < arr.length; i++){
                for (int j = 0; j < arr.length - 1; j++) {
                    if (arr[j] > arr[j + 1]){
                        int temp = arr[j + 1];
                        arr[j + 1] = arr[j];
                        arr[j] = temp;
                    }
                }
            }
            return arr;
        }

        /**
         * Selection Sort.
         * Best time O(n^2)
         * Average time O(n^2)
         * Worst time O(n^2)
         *
         * @param arr the array
         * @return the sorted array
         */
        public static int[] selectionSort(final int[] arr){
           synchronized (LOCK){
               Objects.requireNonNull(arr, "Array cannot be null");
               if (arr.length < 2)  return arr;
               int min;
               int index = 0;
               for (int i = 0; i < arr.length; i++){
                   min = Integer.MAX_VALUE;
                   for (int j = i; j < arr.length; j++) {
                       if (arr[j] < min){
                           index = j;
                           min = arr[j];
                       }
                   }
                   int temp = arr[i];
                   arr[i] = arr[index];
                   arr[index] = temp;
               }
               return arr;
           }
        }

        /**
         * Insertion Sort.
         * Best time O(n)
         * Average time O(n^2)
         * Worst time O(n^2)
         *
         * @param arr the array
         * @return the sorted array
         */
        public static int[] insertionSort(final int[] arr){
            synchronized (LOCK){
                Objects.requireNonNull(arr, "Array cannot be null");
                if (arr.length < 2) return arr;
                for (int i = 0; i < arr.length; i++) {
                    for (int j = 0; j < arr.length - 1; j++) {
                        if (arr[j] > arr[j + 1]){
                            int temp = arr[j + 1];
                            arr[j + 1] = arr[j];
                            arr[j] = temp;
                        }
                    }
                }
                return arr;
            }
        }

        /**
         * Merge Sort.
         * Best time O(n*log(n))
         * Average time O(n*log(n))
         * Worst time O(n*log(n))
         *
         * @param arr the array
         * @return the sorted array
         */
        public static int[] mergeSort(final int[] arr){
            synchronized (LOCK){
                Objects.requireNonNull(arr, "Array cannot be null");
                if (arr.length < 2) return arr;
                return mergeSort0(arr);
            }
        }
        private static int[] mergeSort0(int[] arr){
            if (arr.length > 1){
                int a1 = arr.length / 2;
                @SuppressWarnings("UnnecessaryLocalVariable")
                int a2 = a1; //don't really need this, just there for clarity

                int[] one = new int[a1];
                int[] two = new int[a2];

                System.arraycopy(arr, 0, one, 0, a1);
                System.arraycopy(arr, a1, two, 0, a2);

                one = mergeSort0(one);
                two = mergeSort0(two);

                int i = 0, j = 0, k = 0;
                while(one.length != j && two.length != k){
                    if (one[j] < two[k]){
                        arr[i] = one[j];
                        i++;
                        j++;
                    }
                    else{
                        arr[i] = two[k];
                        i++;
                        k++;
                    }
                }
                while(one.length != j){
                    arr[i] = one[j];
                    i++;
                    j++;
                }
                while (two.length != k){
                    arr[i] = two[k];
                    i++;
                    k++;
                }
            }
            return arr;
        }

        /**
         * Quick Sort
         * Best time O(n*log(n))
         * Average time O(n*log(n))
         * Worst time O(n^2)
         *
         * @param arr the array
         * @return the sorted array.
         */
        public static int[] quickSort(final int[] arr){
            synchronized (LOCK){
                Objects.requireNonNull(arr, "Array cannot be null");
                if (arr.length < 2) return arr;
                return quickSort0(arr, 0, arr.length - 1);
            }
        }

        private static int[] quickSort0(final int[] arr, int low, int high){
            int i = low, j = high;
            int pivot = arr[low + (high - low) / 2];
            while (i <= j){
                while (arr[i] < pivot) i++;
                while (arr[j] > pivot) j--;

                if (i <= j){
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                    i++;
                    j--;
                }
            }

            if (low < j) return quickSort0(arr, low, j);
            if (i < high) return quickSort0(arr, i, high);
            return arr;
        }

        //double array sorting

        public static boolean isSorted(final double[] arr){
            synchronized (LOCK){
                if (arr == null) return false;
                if (arr.length < 2) return true;
                for (int i = 1; i < arr.length; i++) {
                    if (!(arr[i - 1] <= arr[i])) return false;
                }
                return true;
            }
        }

        public static double[] sort(final double[] arr, final SortType type){
            Objects.requireNonNull(arr, "Array cannot be null");
            Objects.requireNonNull(type, "SortType cannot be null");
            if (arr.length < 2) return arr;
            if (type == BUBBLE_SORT) return bubbleSort(arr);
            else if (type == SELECTION_SORT) return selectionSort(arr);
            else if (type == INSERTION_SORT) return insertionSort(arr);
            else if (type == MERGE_SORT) return mergeSort(arr);
            else if (type == QUICK_SORT) return quickSort(arr);
            else throw new IllegalArgumentException("Illegal SortType"); //Should never happen
        }

        /**
         * Determines what type of sorting algorithm to use on the given array.
         * @see Sorts#BUBBLE_SORT_THRESHOLD
         * @see Sorts#INSERTION_SORT_THRESHOLD
         * @see Sorts#QUICKSORT_THRESHOLD
         *
         * @param arr the array.
         * @return the sorted array.
         */
        public static double[] optimizedSort(final double[] arr){
            Objects.requireNonNull(arr, "Array cannot be null");
            if (arr.length < 2) return arr;
            final int len = arr.length;
            if (len < BUBBLE_SORT_THRESHOLD) return bubbleSort(arr);
            else if (len < INSERTION_SORT_THRESHOLD) return insertionSort(arr);
            else if (len < QUICKSORT_THRESHOLD) return quickSort(arr);
            else return mergeSort(arr); //if (len >= QUICKSORT_THRESHOLD)
        }

        /**
         * Sorts the array if if is not already sorted.
         *
         * @param arr the array.
         * @return the sorted array, or the array itself if it is already sorted.
         */
        public static double[] sortIfNotSorted(final double[] arr){
            Objects.requireNonNull(arr, "Array cannot be null");
            if (isSorted(arr)) return arr;
            else return optimizedSort(arr);
        }
        /**
         * Sorts the array if if is not already sorted.
         *
         * @param arr the array.
         * @param type the type of sort to use.
         * @return the sorted array, or the array itself if it is already sorted.
         */
        public static double[] sortIfNotSorted(final double[] arr, final SortType type){
            Objects.requireNonNull(arr, "Array cannot be null");
            Objects.requireNonNull(type, "SortType cannot be null");
            if (isSorted(arr)) return arr;
            else return sort(arr, type);
        }

        /**
         * Bubble Sort.
         * Best time O(n)
         * Average time O(n^2)
         * Worst time O(n^2)
         *
         * @param arr the array
         * @return the sorted array
         */
        public static double[] bubbleSort(final double[] arr){
            synchronized (LOCK){
                Objects.requireNonNull(arr, "Array cannot be null");
                if (arr.length < 2) return arr;
                for (int i = 0; i < arr.length; i++){
                    for (int j = 0; j < arr.length - 1; j++) {
                        if (arr[j] > arr[j + 1]){
                            double temp = arr[j + 1];
                            arr[j + 1] = arr[j];
                            arr[j] = temp;
                        }
                    }
                }
                return arr;
            }
        }

        /**
         * Selection Sort.
         * Best time O(n^2)
         * Average time O(n^2)
         * Worst time O(n^2)
         *
         * @param arr the array
         * @return the sorted array
         */
        public static double[] selectionSort(final double[] arr){
            synchronized (LOCK){
                Objects.requireNonNull(arr, "Array cannot be null");
                if (arr.length < 2) return arr;
                double min;
                int index = 0;
                for (int i = 0; i < arr.length; i++){
                    min = Double.MAX_VALUE;
                    for (int j = i; j < arr.length; j++) {
                        if (arr[j] < min){
                            index = j;
                            min = arr[j];
                        }
                    }
                    double temp = arr[i];
                    arr[i] = arr[index];
                    arr[index] = temp;
                }
                return arr;
            }
        }

        /**
         * Insertion Sort.
         * Best time O(n)
         * Average time O(n^2)
         * Worst time O(n^2)
         *
         * @param arr the array
         * @return the sorted array
         */
        public static double[] insertionSort(final double[] arr){
            synchronized (LOCK){
                Objects.requireNonNull(arr, "Array cannot be null");
                if (arr.length < 2) return arr;
                for (int i = 0; i < arr.length; i++) {
                    for (int j = 0; j < arr.length - 1; j++) {
                        if (arr[j] > arr[j + 1]){
                            double temp = arr[j + 1];
                            arr[j + 1] = arr[j];
                            arr[j] = temp;
                        }
                    }
                }
                return arr;
            }
        }

        /**
         * Merge Sort.
         * Best time O(n*log(n))
         * Average time O(n*log(n))
         * Worst time O(n*log(n))
         *
         * @param arr the array
         * @return the sorted array
         */
        public static double[] mergeSort(final double[] arr){
           synchronized (LOCK){
               Objects.requireNonNull(arr, "Array cannot be null");
               if (arr.length < 2) return arr;
               return mergeSort0(arr);
           }
        }
        private static double[] mergeSort0(double[] arr){
            if (arr.length > 1){
                int a1 = arr.length / 2;
                @SuppressWarnings("UnnecessaryLocalVariable")
                int a2 = a1; //don't really need this, just there for clarity

                double[] one = new double[a1];
                double[] two = new double[a2];

                System.arraycopy(arr, 0, one, 0, a1);
                System.arraycopy(arr, a1, two, 0, a2);

                one = mergeSort0(one);
                two = mergeSort0(two);

                int i = 0, j = 0, k = 0;
                while(one.length != j && two.length != k){
                    if (one[j] < two[k]){
                        arr[i] = one[j];
                        i++;
                        j++;
                    }
                    else{
                        arr[i] = two[k];
                        i++;
                        k++;
                    }
                }
                while(one.length != j){
                    arr[i] = one[j];
                    i++;
                    j++;
                }
                while (two.length != k){
                    arr[i] = two[k];
                    i++;
                    k++;
                }
            }
            return arr;
        }

        /**
         * Quick Sort
         * Best time O(n*log(n))
         * Average time O(n*log(n))
         * Worst time O(n^2)
         *
         * @param arr the array
         * @return the sorted array.
         */
        public static double[] quickSort(final double[] arr){
            synchronized (LOCK){
                Objects.requireNonNull(arr, "Array cannot be null");
                if (arr.length < 2)  return arr;
                return quickSort0(arr, 0, arr.length - 1);
            }
        }
        private static double[] quickSort0(final double[] arr, int low, int high){
            int i = low, j = high;
            double pivot = arr[low + (high - low) / 2];
            while (i <= j){
                while (arr[i] < pivot) i++;
                while (arr[j] > pivot)  j--;

                if (i <= j){
                    double temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                    i++;
                    j--;
                }
            }

            if (low < j) return quickSort0(arr, low, j);
            if (i < high) return quickSort0(arr, i, high);
            return arr;
        }

        //float array sorting

        public static boolean isSorted(final float[] arr){
            synchronized (LOCK){
                if (arr == null) return false;
                if (arr.length < 2) return true;
                for (int i = 1; i < arr.length; i++) {
                    if (!(arr[i - 1] <= arr[i])) return false;
                }
                return true;
            }
        }

        public static float[] sort(final float[] arr, final SortType type){
            Objects.requireNonNull(arr, "Array cannot be null");
            Objects.requireNonNull(type, "SortType cannot be null");
            if (arr.length < 2) return arr;
            if (type == BUBBLE_SORT) return bubbleSort(arr);
            else if (type == SELECTION_SORT) return selectionSort(arr);
            else if (type == INSERTION_SORT) return insertionSort(arr);
            else if (type == MERGE_SORT) return mergeSort(arr);
            else if (type == QUICK_SORT)  return quickSort(arr);
            else throw new IllegalArgumentException("Illegal SortType"); //Should never happen
        }

        /**
         * Determines what type of sorting algorithm to use on the given array.
         * @see Sorts#BUBBLE_SORT_THRESHOLD
         * @see Sorts#INSERTION_SORT_THRESHOLD
         * @see Sorts#QUICKSORT_THRESHOLD
         *
         * @param arr the array.
         * @return the sorted array.
         */
        public static float[] optimizedSort(final float[] arr){
            Objects.requireNonNull(arr, "Array cannot be null");
            if (arr.length < 2) return arr;
            final int len = arr.length;
            if (len < BUBBLE_SORT_THRESHOLD) return bubbleSort(arr);
            else if (len < INSERTION_SORT_THRESHOLD) return insertionSort(arr);
            else if (len < QUICKSORT_THRESHOLD) return quickSort(arr);
            else return mergeSort(arr); //if (len >= QUICKSORT_THRESHOLD)
        }

        /**
         * Sorts the array if if is not already sorted.
         *
         * @param arr the array.
         * @return the sorted array, or the array itself if it is already sorted.
         */
        public static float[] sortIfNotSorted(final float[] arr){
            Objects.requireNonNull(arr, "Array cannot be null");
            if (isSorted(arr)) return arr;
            else return optimizedSort(arr);
        }
        /**
         * Sorts the array if if is not already sorted.
         *
         * @param arr the array.
         * @param type the type of sort to use.
         * @return the sorted array, or the array itself if it is already sorted.
         */
        public static float[] sortIfNotSorted(final float[] arr, final SortType type){
            Objects.requireNonNull(arr, "Array cannot be null");
            Objects.requireNonNull(type, "SortType cannot be null");
            if (isSorted(arr)) return arr;
            else return sort(arr, type);
        }

        /**
         * Bubble Sort.
         * Best time O(n)
         * Average time O(n^2)
         * Worst time O(n^2)
         *
         * @param arr the array
         * @return the sorted array
         */
        public static float[] bubbleSort(final float[] arr){
            synchronized (LOCK){
                Objects.requireNonNull(arr, "Array cannot be null");
                if (arr.length < 2)  return arr;
                for (int i = 0; i < arr.length; i++){
                    for (int j = 0; j < arr.length - 1; j++) {
                        if (arr[j] > arr[j + 1]){
                            float temp = arr[j + 1];
                            arr[j + 1] = arr[j];
                            arr[j] = temp;
                        }
                    }
                }
                return arr;
            }
        }

        /**
         * Selection Sort.
         * Best time O(n^2)
         * Average time O(n^2)
         * Worst time O(n^2)
         *
         * @param arr the array
         * @return the sorted array
         */
        public static float[] selectionSort(final float[] arr){
            synchronized (LOCK){
                Objects.requireNonNull(arr, "Array cannot be null");
                if (arr.length < 2) return arr;
                float min;
                int index = 0;
                for (int i = 0; i < arr.length; i++){
                    min = Float.MAX_VALUE;
                    for (int j = i; j < arr.length; j++) {
                        if (arr[j] < min){
                            index = j;
                            min = arr[j];
                        }
                    }
                    float temp = arr[i];
                    arr[i] = arr[index];
                    arr[index] = temp;
                }
                return arr;
            }
        }

        /**
         * Insertion Sort.
         * Best time O(n)
         * Average time O(n^2)
         * Worst time O(n^2)
         *
         * @param arr the array
         * @return the sorted array
         */
        public static float[] insertionSort(final float[] arr){
            synchronized (LOCK){
                Objects.requireNonNull(arr, "Array cannot be null");
                if (arr.length < 2) return arr;
                for (int i = 0; i < arr.length; i++) {
                    for (int j = 0; j < arr.length - 1; j++) {
                        if (arr[j] > arr[j + 1]){
                            float temp = arr[j + 1];
                            arr[j + 1] = arr[j];
                            arr[j] = temp;
                        }
                    }
                }
                return arr;
            }
        }

        /**
         * Merge Sort.
         * Best time O(n*log(n))
         * Average time O(n*log(n))
         * Worst time O(n*log(n))
         *
         * @param arr the array
         * @return the sorted array
         */
        public static float[] mergeSort(final float[] arr){
           synchronized (LOCK){
               Objects.requireNonNull(arr, "Array cannot be null");
               if (arr.length < 2) return arr;
               return mergeSort0(arr);
           }
        }
        private static float[] mergeSort0(float[] arr){
            if (arr.length > 1){
                int a1 = arr.length / 2;
                @SuppressWarnings("UnnecessaryLocalVariable")
                int a2 = a1; //don't really need this, just there for clarity

                float[] one = new float[a1];
                float[] two = new float[a2];

                System.arraycopy(arr, 0, one, 0, a1);
                System.arraycopy(arr, a1, two, 0, a2);

                one = mergeSort0(one);
                two = mergeSort0(two);

                int i = 0, j = 0, k = 0;
                while(one.length != j && two.length != k){
                    if (one[j] < two[k]){
                        arr[i] = one[j];
                        i++;
                        j++;
                    }
                    else{
                        arr[i] = two[k];
                        i++;
                        k++;
                    }
                }
                while(one.length != j){
                    arr[i] = one[j];
                    i++;
                    j++;
                }
                while (two.length != k){
                    arr[i] = two[k];
                    i++;
                    k++;
                }
            }
            return arr;
        }

        /**
         * Quick Sort
         * Best time O(n*log(n))
         * Average time O(n*log(n))
         * Worst time O(n^2)
         *
         * @param arr the array
         * @return the sorted array.
         */
        public static float[] quickSort(final float[] arr){
            synchronized (LOCK){
                Objects.requireNonNull(arr, "Array cannot be null");
                if (arr.length < 2) return arr;
                return quickSort0(arr, 0, arr.length - 1);
            }
        }
        private static float[] quickSort0(final float[] arr, int low, int high){
            int i = low, j = high;
            float pivot = arr[low + (high - low) / 2];
            while (i <= j){
                while (arr[i] < pivot) i++;
                while (arr[j] > pivot) j--;

                if (i <= j){
                    float temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                    i++;
                    j--;
                }
            }

            if (low < j){
                return quickSort0(arr, low, j);
            }
            if (i < high){
                return quickSort0(arr, i, high);
            }
            return arr;
        }

        //short array sorting

        public static boolean isSorted(final short[] arr){
            synchronized (LOCK){
                if (arr == null) return false;
                if (arr.length < 2) return true;
                for (int i = 1; i < arr.length; i++) {
                    if (!(arr[i - 1] <= arr[i])) return false;
                }
                return true;
            }
        }

        public static short[] sort(final short[] arr, final SortType type){
            Objects.requireNonNull(arr, "Array cannot be null");
            Objects.requireNonNull(type, "SortType cannot be null");
            if (arr.length < 2) return arr;
            if (type == BUBBLE_SORT) return bubbleSort(arr);
            else if (type == SELECTION_SORT) return selectionSort(arr);
            else if (type == INSERTION_SORT) return insertionSort(arr);
            else if (type == MERGE_SORT) return mergeSort(arr);
            else if (type == QUICK_SORT) return quickSort(arr);
            else throw new IllegalArgumentException("Illegal SortType"); //Should never happen
        }

        /**
         * Determines what type of sorting algorithm to use on the given array.
         * @see Sorts#BUBBLE_SORT_THRESHOLD
         * @see Sorts#INSERTION_SORT_THRESHOLD
         * @see Sorts#QUICKSORT_THRESHOLD
         *
         * @param arr the array.
         * @return the sorted array.
         */
        public static short[] optimizedSort(final short[] arr){
            Objects.requireNonNull(arr, "Array cannot be null");
            if (arr.length < 2)  return arr;
            final int len = arr.length;
            if (len < BUBBLE_SORT_THRESHOLD) return bubbleSort(arr);
            else if (len < INSERTION_SORT_THRESHOLD) return insertionSort(arr);
            else if (len < QUICKSORT_THRESHOLD) return quickSort(arr);
            else return mergeSort(arr); //if (len >= QUICKSORT_THRESHOLD)
        }

        /**
         * Sorts the array if if is not already sorted.
         *
         * @param arr the array.
         * @return the sorted array, or the array itself if it is already sorted.
         */
        public static short[] sortIfNotSorted(final short[] arr){
            Objects.requireNonNull(arr, "Array cannot be null");
            if (isSorted(arr)) return arr;
            else return optimizedSort(arr);
        }
        /**
         * Sorts the array if if is not already sorted.
         *
         * @param arr the array.
         * @param type the type of sort to use.
         * @return the sorted array, or the array itself if it is already sorted.
         */
        public static short[] sortIfNotSorted(final short[] arr, final SortType type){
            Objects.requireNonNull(arr, "Array cannot be null");
            Objects.requireNonNull(type, "SortType cannot be null");
            if (isSorted(arr)) return arr;
            else return sort(arr, type);
        }

        /**
         * Bubble Sort.
         * Best time O(n)
         * Average time O(n^2)
         * Worst time O(n^2)
         *
         * @param arr the array
         * @return the sorted array
         */
        public static short[] bubbleSort(final short[] arr){
           synchronized (LOCK){
               Objects.requireNonNull(arr, "Array cannot be null");
               if (arr.length < 2) return arr;
               for (int i = 0; i < arr.length; i++){
                   for (int j = 0; j < arr.length - 1; j++) {
                       if (arr[j] > arr[j + 1]){
                           short temp = arr[j + 1];
                           arr[j + 1] = arr[j];
                           arr[j] = temp;
                       }
                   }
               }
               return arr;
           }
        }

        /**
         * Selection Sort.
         * Best time O(n^2)
         * Average time O(n^2)
         * Worst time O(n^2)
         *
         * @param arr the array
         * @return the sorted array
         */
        public static short[] selectionSort(final short[] arr){
            synchronized (LOCK){
                Objects.requireNonNull(arr, "Array cannot be null");
                if (arr.length < 2) return arr;
                short min;
                int index = 0;
                for (int i = 0; i < arr.length; i++){
                    min = Short.MAX_VALUE;
                    for (int j = i; j < arr.length; j++) {
                        if (arr[j] < min){
                            index = j;
                            min = arr[j];
                        }
                    }
                    short temp = arr[i];
                    arr[i] = arr[index];
                    arr[index] = temp;
                }
                return arr;
            }
        }

        /**
         * Insertion Sort.
         * Best time O(n)
         * Average time O(n^2)
         * Worst time O(n^2)
         *
         * @param arr the array
         * @return the sorted array
         */
        public static short[] insertionSort(final short[] arr){
            synchronized (LOCK){
                Objects.requireNonNull(arr, "Array cannot be null");
                if (arr.length < 2) return arr;
                for (int i = 0; i < arr.length; i++) {
                    for (int j = 0; j < arr.length - 1; j++) {
                        if (arr[j] > arr[j + 1]){
                            short temp = arr[j + 1];
                            arr[j + 1] = arr[j];
                            arr[j] = temp;
                        }
                    }
                }
                return arr;
            }
        }

        /**
         * Merge Sort.
         * Best time O(n*log(n))
         * Average time O(n*log(n))
         * Worst time O(n*log(n))
         *
         * @param arr the array
         * @return the sorted array
         */
        public static short[] mergeSort(final short[] arr){
            synchronized (LOCK){
                Objects.requireNonNull(arr, "Array cannot be null");
                if (arr.length < 2) return arr;
                return mergeSort0(arr);
            }
        }
        private static short[] mergeSort0(short[] arr){
            if (arr.length > 1){
                int a1 = arr.length / 2;
                @SuppressWarnings("UnnecessaryLocalVariable")
                int a2 = a1; //don't really need this, just there for clarity

                short[] one = new short[a1];
                short[] two = new short[a2];

                System.arraycopy(arr, 0, one, 0, a1);
                System.arraycopy(arr, a1, two, 0, a2);

                one = mergeSort0(one);
                two = mergeSort0(two);

                int i = 0, j = 0, k = 0;
                while(one.length != j && two.length != k){
                    if (one[j] < two[k]){
                        arr[i] = one[j];
                        i++;
                        j++;
                    }
                    else{
                        arr[i] = two[k];
                        i++;
                        k++;
                    }
                }
                while(one.length != j){
                    arr[i] = one[j];
                    i++;
                    j++;
                }
                while (two.length != k){
                    arr[i] = two[k];
                    i++;
                    k++;
                }
            }
            return arr;
        }

        /**
         * Quick Sort
         * Best time O(n*log(n))
         * Average time O(n*log(n))
         * Worst time O(n^2)
         *
         * @param arr the array
         * @return the sorted array.
         */
        public static short[] quickSort(final short[] arr){
            synchronized (LOCK){
                Objects.requireNonNull(arr, "Array cannot be null");
                if (arr.length < 2) return arr;
                return quickSort0(arr, 0, arr.length - 1);
            }
        }
        private static short[] quickSort0(final short[] arr, int low, int high){
            int i = low, j = high;
            short pivot = arr[low + (high - low) / 2];
            while (i <= j){
                while (arr[i] < pivot) i++;
                while (arr[j] > pivot) j--;

                if (i <= j){
                    short temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                    i++;
                    j--;
                }
            }

            if (low < j) return quickSort0(arr, low, j);
            if (i < high) return quickSort0(arr, i, high);
            return arr;
        }

        //long array sorting

        public static boolean isSorted(final long[] arr){
            synchronized (LOCK){
                if (arr == null) return false;
                if (arr.length < 2) return true;
                for (int i = 1; i < arr.length; i++) {
                    if (!(arr[i - 1] <= arr[i])) return false;
                }
                return true;
            }
        }

        public static long[] sort(final long[] arr, final SortType type){
            Objects.requireNonNull(arr, "Array cannot be null");
            Objects.requireNonNull(type, "SortType cannot be null");
            if (arr.length < 2) return arr;
            if (type == BUBBLE_SORT) return bubbleSort(arr);
            else if (type == SELECTION_SORT) return selectionSort(arr);
            else if (type == INSERTION_SORT) return insertionSort(arr);
            else if (type == MERGE_SORT) return mergeSort(arr);
            else if (type == QUICK_SORT) return quickSort(arr);
            else throw new IllegalArgumentException("Illegal SortType"); //Should never happen
        }

        /**
         * Determines what type of sorting algorithm to use on the given array.
         * @see Sorts#BUBBLE_SORT_THRESHOLD
         * @see Sorts#INSERTION_SORT_THRESHOLD
         * @see Sorts#QUICKSORT_THRESHOLD
         *
         * @param arr the array.
         * @return the sorted array.
         */
        public static long[] optimizedSort(final long[] arr){
            Objects.requireNonNull(arr, "Array cannot be null");
            if (arr.length < 2)  return arr;
            final int len = arr.length;
            if (len < BUBBLE_SORT_THRESHOLD) return bubbleSort(arr);
            else if (len < INSERTION_SORT_THRESHOLD) return insertionSort(arr);
            else if (len < QUICKSORT_THRESHOLD) return quickSort(arr);
            else return mergeSort(arr); //if (len >= QUICKSORT_THRESHOLD)
        }

        /**
         * Sorts the array if if is not already sorted.
         *
         * @param arr the array.
         * @return the sorted array, or the array itself if it is already sorted.
         */
        public static long[] sortIfNotSorted(final long[] arr){
            Objects.requireNonNull(arr, "Array cannot be null");
            if (isSorted(arr)) return arr;
            else return optimizedSort(arr);
        }
        /**
         * Sorts the array if if is not already sorted.
         *
         * @param arr the array.
         * @param type the type of sort to use.
         * @return the sorted array, or the array itself if it is already sorted.
         */
        public static long[] sortIfNotSorted(final long[] arr, final SortType type){
            Objects.requireNonNull(arr, "Array cannot be null");
            Objects.requireNonNull(type, "SortType cannot be null");
            if (isSorted(arr)) return arr;
            else return sort(arr, type);
        }

        /**
         * Bubble Sort.
         * Best time O(n)
         * Average time O(n^2)
         * Worst time O(n^2)
         *
         * @param arr the array
         * @return the sorted array
         */
        public static long[] bubbleSort(final long[] arr){
            synchronized (LOCK){
                Objects.requireNonNull(arr, "Array cannot be null");
                if (arr.length < 2) return arr;
                for (int i = 0; i < arr.length; i++){
                    for (int j = 0; j < arr.length - 1; j++) {
                        if (arr[j] > arr[j + 1]){
                            long temp = arr[j + 1];
                            arr[j + 1] = arr[j];
                            arr[j] = temp;
                        }
                    }
                }
                return arr;
            }
        }

        /**
         * Selection Sort.
         * Best time O(n^2)
         * Average time O(n^2)
         * Worst time O(n^2)
         *
         * @param arr the array
         * @return the sorted array
         */
        public static long[] selectionSort(final long[] arr){
            synchronized (LOCK){
                Objects.requireNonNull(arr, "Array cannot be null");
                if (arr.length < 2) return arr;
                long min;
                int index = 0;
                for (int i = 0; i < arr.length; i++){
                    min = Long.MAX_VALUE;
                    for (int j = i; j < arr.length; j++) {
                        if (arr[j] < min){
                            index = j;
                            min = arr[j];
                        }
                    }
                    long temp = arr[i];
                    arr[i] = arr[index];
                    arr[index] = temp;
                }
                return arr;
            }
        }

        /**
         * Insertion Sort.
         * Best time O(n)
         * Average time O(n^2)
         * Worst time O(n^2)
         *
         * @param arr the array
         * @return the sorted array
         */
        public static long[] insertionSort(final long[] arr){
            synchronized (LOCK){
                Objects.requireNonNull(arr, "Array cannot be null");
                if (arr.length < 2) return arr;
                for (int i = 0; i < arr.length; i++) {
                    for (int j = 0; j < arr.length - 1; j++) {
                        if (arr[j] > arr[j + 1]){
                            long temp = arr[j + 1];
                            arr[j + 1] = arr[j];
                            arr[j] = temp;
                        }
                    }
                }
                return arr;
            }
        }

        /**
         * Merge Sort.
         * Best time O(n*log(n))
         * Average time O(n*log(n))
         * Worst time O(n*log(n))
         *
         * @param arr the array
         * @return the sorted array
         */
        public static long[] mergeSort(final long[] arr){
           synchronized (LOCK){
               Objects.requireNonNull(arr, "Array cannot be null");
               if (arr.length < 2) return arr;
               return mergeSort0(arr);
           }
        }
        private static long[] mergeSort0(long[] arr){
            if (arr.length > 1){
                int a1 = arr.length / 2;
                @SuppressWarnings("UnnecessaryLocalVariable")
                int a2 = a1; //don't really need this, just there for clarity

                long[] one = new long[a1];
                long[] two = new long[a2];

                System.arraycopy(arr, 0, one, 0, a1);
                System.arraycopy(arr, a1, two, 0, a2);

                one = mergeSort0(one);
                two = mergeSort0(two);

                int i = 0, j = 0, k = 0;
                while(one.length != j && two.length != k){
                    if (one[j] < two[k]){
                        arr[i] = one[j];
                        i++;
                        j++;
                    }
                    else{
                        arr[i] = two[k];
                        i++;
                        k++;
                    }
                }
                while(one.length != j){
                    arr[i] = one[j];
                    i++;
                    j++;
                }
                while (two.length != k){
                    arr[i] = two[k];
                    i++;
                    k++;
                }
            }
            return arr;
        }

        /**
         * Quick Sort
         * Best time O(n*log(n))
         * Average time O(n*log(n))
         * Worst time O(n^2)
         *
         * @param arr the array
         * @return the sorted array.
         */
        public static long[] quickSort(final long[] arr){
            synchronized (LOCK){
                Objects.requireNonNull(arr, "Array cannot be null");
                if (arr.length < 2) return arr;
                return quickSort0(arr, 0, arr.length - 1);
            }
        }
        private static long[] quickSort0(final long[] arr, int low, int high){
            int i = low, j = high;
            long pivot = arr[low + (high - low) / 2];
            while (i <= j){
                while (arr[i] < pivot) i++;
                while (arr[j] > pivot) j--;

                if (i <= j){
                    long temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                    i++;
                    j--;
                }
            }

            if (low < j) return quickSort0(arr, low, j);
            if (i < high) return quickSort0(arr, i, high);
            return arr;
        }

        //byte array sorting

        public static boolean isSorted(final byte[] arr){
            synchronized (LOCK){
                if (arr == null) return false;
                if (arr.length < 2) return true;
                for (int i = 1; i < arr.length; i++) {
                    if (!(arr[i - 1] <= arr[i])) return false;
                }
                return true;
            }
        }

        public static byte[] sort(final byte[] arr, final SortType type){
            Objects.requireNonNull(arr, "Array cannot be null");
            Objects.requireNonNull(type, "SortType cannot be null");
            if (arr.length < 2) return arr;
            if (type == BUBBLE_SORT) return bubbleSort(arr);
            else if (type == SELECTION_SORT) return selectionSort(arr);
            else if (type == INSERTION_SORT) return insertionSort(arr);
            else if (type == MERGE_SORT) return mergeSort(arr);
            else if (type == QUICK_SORT) return quickSort(arr);
            else throw new IllegalArgumentException("Illegal SortType"); //Should never happen
        }

        /**
         * Determines what type of sorting algorithm to use on the given array.
         * @see Sorts#BUBBLE_SORT_THRESHOLD
         * @see Sorts#INSERTION_SORT_THRESHOLD
         * @see Sorts#QUICKSORT_THRESHOLD
         *
         * @param arr the array.
         * @return the sorted array.
         */
        public static byte[] optimizedSort(final byte[] arr){
            Objects.requireNonNull(arr, "Array cannot be null");
            if (arr.length < 2)  return arr;
            final int len = arr.length;
            if (len < BUBBLE_SORT_THRESHOLD) return bubbleSort(arr);
            else if (len < INSERTION_SORT_THRESHOLD) return insertionSort(arr);
            else if (len < QUICKSORT_THRESHOLD)  return quickSort(arr);
            else return mergeSort(arr); //if (len >= QUICKSORT_THRESHOLD)
        }

        /**
         * Sorts the array if if is not already sorted.
         *
         * @param arr the array.
         * @return the sorted array, or the array itself if it is already sorted.
         */
        public static byte[] sortIfNotSorted(final byte[] arr){
            Objects.requireNonNull(arr, "Array cannot be null");
            if (isSorted(arr)) return arr;
            else return optimizedSort(arr);
        }
        /**
         * Sorts the array if if is not already sorted.
         *
         * @param arr the array.
         * @param type the type of sort to use.
         * @return the sorted array, or the array itself if it is already sorted.
         */
        public static byte[] sortIfNotSorted(final byte[] arr, final SortType type){
            Objects.requireNonNull(arr, "Array cannot be null");
            Objects.requireNonNull(type, "SortType cannot be null");
            if (isSorted(arr))return arr;
            else return sort(arr, type);
        }

        /**
         * Bubble Sort.
         * Best time O(n)
         * Average time O(n^2)
         * Worst time O(n^2)
         *
         * @param arr the array
         * @return the sorted array
         */
        public static byte[] bubbleSort(final byte[] arr){
            synchronized (LOCK){
                Objects.requireNonNull(arr, "Array cannot be null");
                if (arr.length < 2) return arr;
                for (int i = 0; i < arr.length; i++){
                    for (int j = 0; j < arr.length - 1; j++) {
                        if (arr[j] > arr[j + 1]){
                            byte temp = arr[j + 1];
                            arr[j + 1] = arr[j];
                            arr[j] = temp;
                        }
                    }
                }
                return arr;
            }
        }

        /**
         * Selection Sort.
         * Best time O(n^2)
         * Average time O(n^2)
         * Worst time O(n^2)
         *
         * @param arr the array
         * @return the sorted array
         */
        public static byte[] selectionSort(final byte[] arr){
            synchronized (LOCK){
                Objects.requireNonNull(arr, "Array cannot be null");
                if (arr.length < 2) return arr;
                byte min;
                int index = 0;
                for (int i = 0; i < arr.length; i++){
                    min = Byte.MAX_VALUE;
                    for (int j = i; j < arr.length; j++) {
                        if (arr[j] < min){
                            index = j;
                            min = arr[j];
                        }
                    }
                    byte temp = arr[i];
                    arr[i] = arr[index];
                    arr[index] = temp;
                }
                return arr;
            }
        }

        /**
         * Insertion Sort.
         * Best time O(n)
         * Average time O(n^2)
         * Worst time O(n^2)
         *
         * @param arr the array
         * @return the sorted array
         */
        public static byte[] insertionSort(final byte[] arr){
            synchronized (LOCK){
                Objects.requireNonNull(arr, "Array cannot be null");
                if (arr.length < 2) return arr;
                for (int i = 0; i < arr.length; i++) {
                    for (int j = 0; j < arr.length - 1; j++) {
                        if (arr[j] > arr[j + 1]){
                            byte temp = arr[j + 1];
                            arr[j + 1] = arr[j];
                            arr[j] = temp;
                        }
                    }
                }
                return arr;
            }
        }

        /**
         * Merge Sort.
         * Best time O(n*log(n))
         * Average time O(n*log(n))
         * Worst time O(n*log(n))
         *
         * @param arr the array
         * @return the sorted array
         */
        public static byte[] mergeSort(final byte[] arr){
            synchronized (LOCK){
                Objects.requireNonNull(arr, "Array cannot be null");
                if (arr.length < 2) return arr;
                return mergeSort0(arr);
            }
        }
        private static byte[] mergeSort0(byte[] arr){
            if (arr.length > 1){
                int a1 = arr.length / 2;
                @SuppressWarnings("UnnecessaryLocalVariable")
                int a2 = a1; //don't really need this, just there for clarity

                byte[] one = new byte[a1];
                byte[] two = new byte[a2];

                System.arraycopy(arr, 0, one, 0, a1);
                System.arraycopy(arr, a1, two, 0, a2);

                one = mergeSort0(one);
                two = mergeSort0(two);

                int i = 0, j = 0, k = 0;
                while(one.length != j && two.length != k){
                    if (one[j] < two[k]){
                        arr[i] = one[j];
                        i++;
                        j++;
                    }
                    else{
                        arr[i] = two[k];
                        i++;
                        k++;
                    }
                }
                while(one.length != j){
                    arr[i] = one[j];
                    i++;
                    j++;
                }
                while (two.length != k){
                    arr[i] = two[k];
                    i++;
                    k++;
                }
            }
            return arr;
        }

        /**
         * Quick Sort
         * Best time O(n*log(n))
         * Average time O(n*log(n))
         * Worst time O(n^2)
         *
         * @param arr the array
         * @return the sorted array.
         */
        public static byte[] quickSort(final byte[] arr){
            synchronized (LOCK){
                Objects.requireNonNull(arr, "Array cannot be null");
                if (arr.length < 2) return arr;
                return quickSort0(arr, 0, arr.length - 1);
            }
        }
        private static byte[] quickSort0(final byte[] arr, int low, int high){
            int i = low, j = high;
            byte pivot = arr[low + (high - low) / 2];
            while (i <= j){
                while (arr[i] < pivot) i++;
                while (arr[j] > pivot) j--;

                if (i <= j){
                    byte temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                    i++;
                    j--;
                }
            }

            if (low < j) return quickSort0(arr, low, j);
            if (i < high) return quickSort0(arr, i, high);
            return arr;
        }

        //char array sorting

        public static boolean isSorted(final char[] arr){
            synchronized (LOCK){
                if (arr == null) return false;
                if (arr.length < 2) return true;
                for (int i = 1; i < arr.length; i++) {
                    if (!(arr[i - 1] <= arr[i])) return false;
                }
                return true;
            }
        }

        public static char[] sort(final char[] arr, final SortType type){
            Objects.requireNonNull(arr, "Array cannot be null");
            Objects.requireNonNull(type, "SortType cannot be null");
            if (arr.length < 2) return arr;
            if (type == BUBBLE_SORT) return bubbleSort(arr);
            else if (type == SELECTION_SORT) return selectionSort(arr);
            else if (type == INSERTION_SORT) return insertionSort(arr);
            else if (type == MERGE_SORT) return mergeSort(arr);
            else if (type == QUICK_SORT) return quickSort(arr);
            else throw new IllegalArgumentException("Illegal SortType"); //Should never happen
        }

        /**
         * Determines what type of sorting algorithm to use on the given array.
         * @see Sorts#BUBBLE_SORT_THRESHOLD
         * @see Sorts#INSERTION_SORT_THRESHOLD
         * @see Sorts#QUICKSORT_THRESHOLD
         *
         * @param arr the array.
         * @return the sorted array.
         */
        public static char[] optimizedSort(final char[] arr){
            Objects.requireNonNull(arr, "Array cannot be null");
            if (arr.length < 2) return arr;
            final int len = arr.length;
            if (len < BUBBLE_SORT_THRESHOLD) return bubbleSort(arr);
            else if (len < INSERTION_SORT_THRESHOLD) return insertionSort(arr);
            else if (len < QUICKSORT_THRESHOLD) return quickSort(arr);
            else return mergeSort(arr); //if (len >= QUICKSORT_THRESHOLD)
        }

        /**
         * Sorts the array if if is not already sorted.
         *
         * @param arr the array.
         * @return the sorted array, or the array itself if it is already sorted.
         */
        public static char[] sortIfNotSorted(final char[] arr){
            Objects.requireNonNull(arr, "Array cannot be null");
            if (isSorted(arr)) return arr;
            else return optimizedSort(arr);
        }

        /**
         * Sorts the array if if is not already sorted.
         *
         * @param arr the array.
         * @param type the type of sort to use.
         * @return the sorted array, or the array itself if it is already sorted.
         */
        public static char[] sortIfNotSorted(final char[] arr, final SortType type){
            Objects.requireNonNull(arr, "Array cannot be null");
            Objects.requireNonNull(type, "SortType cannot be null");
            if (isSorted(arr)) return arr;
            else return sort(arr, type);
        }

        /**
         * Bubble Sort.
         * Best time O(n)
         * Average time O(n^2)
         * Worst time O(n^2)
         *
         * @param arr the array
         * @return the sorted array
         */
        public static char[] bubbleSort(final char[] arr){
            synchronized (LOCK){
                Objects.requireNonNull(arr, "Array cannot be null");
                if (arr.length < 2) return arr;
                for (int i = 0; i < arr.length; i++){
                    for (int j = 0; j < arr.length - 1; j++) {
                        if (arr[j] > arr[j + 1]){
                            char temp = arr[j + 1];
                            arr[j + 1] = arr[j];
                            arr[j] = temp;
                        }
                    }
                }
                return arr;
            }
        }

        /**
         * Selection Sort.
         * Best time O(n^2)
         * Average time O(n^2)
         * Worst time O(n^2)
         *
         * @param arr the array
         * @return the sorted array
         */
        public static char[] selectionSort(final char[] arr){
            synchronized (LOCK){
                Objects.requireNonNull(arr, "Array cannot be null");
                if (arr.length < 2) return arr;
                char min;
                int index = 0;
                for (int i = 0; i < arr.length; i++){
                    min = Character.MAX_VALUE;
                    for (int j = i; j < arr.length; j++) {
                        if (arr[j] < min){
                            index = j;
                            min = arr[j];
                        }
                    }
                    char temp = arr[i];
                    arr[i] = arr[index];
                    arr[index] = temp;
                }
                return arr;
            }
        }

        /**
         * Insertion Sort.
         * Best time O(n)
         * Average time O(n^2)
         * Worst time O(n^2)
         *
         * @param arr the array
         * @return the sorted array
         */
        public static char[] insertionSort(final char[] arr){
            synchronized (LOCK){
                Objects.requireNonNull(arr, "Array cannot be null");
                if (arr.length < 2) return arr;
                for (int i = 0; i < arr.length; i++) {
                    for (int j = 0; j < arr.length - 1; j++) {
                        if (arr[j] > arr[j + 1]){
                            char temp = arr[j + 1];
                            arr[j + 1] = arr[j];
                            arr[j] = temp;
                        }
                    }
                }
                return arr;
            }
        }

        /**
         * Merge Sort.
         * Best time O(n*log(n))
         * Average time O(n*log(n))
         * Worst time O(n*log(n))
         *
         * @param arr the array
         * @return the sorted array
         */
        public static char[] mergeSort(final char[] arr){
            synchronized (LOCK){
                Objects.requireNonNull(arr, "Array cannot be null");
                if (arr.length < 2) return arr;
                return mergeSort0(arr);
            }
        }
        private static char[] mergeSort0(char[] arr){
            if (arr.length > 1){
                int a1 = arr.length / 2;
                @SuppressWarnings("UnnecessaryLocalVariable")
                int a2 = a1; //don't really need this, just there for clarity

                char[] one = new char[a1];
                char[] two = new char[a2];

                System.arraycopy(arr, 0, one, 0, a1);
                System.arraycopy(arr, a1, two, 0, a2);

                one = mergeSort0(one);
                two = mergeSort0(two);

                int i = 0, j = 0, k = 0;
                while(one.length != j && two.length != k){
                    if (one[j] < two[k]){
                        arr[i] = one[j];
                        i++;
                        j++;
                    }
                    else{
                        arr[i] = two[k];
                        i++;
                        k++;
                    }
                }
                while(one.length != j){
                    arr[i] = one[j];
                    i++;
                    j++;
                }
                while (two.length != k){
                    arr[i] = two[k];
                    i++;
                    k++;
                }
            }
            return arr;
        }

        /**
         * Quick Sort
         * Best time O(n*log(n))
         * Average time O(n*log(n))
         * Worst time O(n^2)
         *
         * @param arr the array
         * @return the sorted array.
         */
        public static char[] quickSort(final char[] arr){
            synchronized (LOCK){
                Objects.requireNonNull(arr, "Array cannot be null");
                if (arr.length < 2) return arr;
                return quickSort0(arr, 0, arr.length - 1);
            }
        }
        private static char[] quickSort0(final char[] arr, int low, int high){
            int i = low, j = high;
            char pivot = arr[low + (high - low) / 2];
            while (i <= j){
                while (arr[i] < pivot) i++;
                while (arr[j] > pivot) j--;

                if (i <= j){
                    char temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                    i++;
                    j--;
                }
            }

            if (low < j) return quickSort0(arr, low, j);
            if (i < high) return quickSort0(arr, i, high);
            return arr;
        }

        //Object array sorting (Comparable Objects)

        public static <T extends Comparable<T>> boolean isSorted(final T[] arr){
            synchronized (LOCK){
                if (arr == null) return false;
                if (arr.length < 2) return true;
                for (int i = 1; i < arr.length; i++) {
                    if (!(arr[i - 1].compareTo(arr[i]) <= 0)) return false;
                }
                return true;
            }
        }

        public static <T extends Comparable<T>> T[] sort(final T[] arr, final SortType type){
            Objects.requireNonNull(arr, "Array cannot be null");
            Objects.requireNonNull(type, "SortType cannot be null");
            if (arr.length < 2) return arr;
            if (type == BUBBLE_SORT) return bubbleSort(arr);
            else if (type == SELECTION_SORT) return selectionSort(arr);
            else if (type == INSERTION_SORT) return insertionSort(arr);
            else if (type == MERGE_SORT) return mergeSort(arr);
            else if (type == QUICK_SORT) return quickSort(arr);
            else throw new IllegalArgumentException("Illegal SortType"); //Should never happen
        }

        /**
         * Determines what type of sorting algorithm to use on the given array.
         * @see Sorts#BUBBLE_SORT_THRESHOLD
         * @see Sorts#INSERTION_SORT_THRESHOLD
         * @see Sorts#QUICKSORT_THRESHOLD
         *
         * @param arr the array.
         * @return the sorted array.
         */
        public static <T extends Comparable<T>> T[] optimizedSort(final T[] arr){
            Objects.requireNonNull(arr, "Array cannot be null");
            if (arr.length < 2) return arr;
            final int len = arr.length;
            if (len < BUBBLE_SORT_THRESHOLD) return bubbleSort(arr);
            else if (len < INSERTION_SORT_THRESHOLD) return insertionSort(arr);
            else if (len < QUICKSORT_THRESHOLD) return quickSort(arr);
            else return mergeSort(arr); //if (len >= QUICKSORT_THRESHOLD)
        }

        /**
         * Sorts the array if if is not already sorted.
         *
         * @param arr the array.
         * @return the sorted array, or the array itself if it is already sorted.
         */
        public static <T extends Comparable<T>> T[] sortIfNotSorted(final T[] arr){
            Objects.requireNonNull(arr, "Array cannot be null");
            if (isSorted(arr)) return arr;
            else return optimizedSort(arr);
        }
        /**
         * Sorts the array if if is not already sorted.
         *
         * @param arr the array.
         * @param type the type of sort to use.
         * @return the sorted array, or the array itself if it is already sorted.
         */
        public static <T extends Comparable<T>> T[] sortIfNotSorted(final T[] arr, final SortType type){
            Objects.requireNonNull(arr, "Array cannot be null");
            Objects.requireNonNull(type, "SortType cannot be null");
            if (isSorted(arr)) return arr;
            else return sort(arr, type);
        }

        /**
         * Bubble Sort.
         * Best time O(n)
         * Average time O(n^2)
         * Worst time O(n^2)
         *
         * @param arr the array
         * @return the sorted array
         */
        public static <T extends Comparable<T>> T[] bubbleSort(final T[] arr){
            synchronized (LOCK){
                Objects.requireNonNull(arr, "Array cannot be null");
                if (arr.length < 2) return arr;
                for (int i = 0; i < arr.length; i++){
                    for (int j = 0; j < arr.length - 1; j++) {
                        if (arr[j].compareTo(arr[j + 1]) > 0){
                            T temp = arr[j + 1];
                            arr[j + 1] = arr[j];
                            arr[j] = temp;
                        }
                    }
                }
                return arr;
            }
        }

        /**
         * Selection Sort.
         * Best time O(n^2)
         * Average time O(n^2)
         * Worst time O(n^2)
         *
         * @param arr the array
         * @return the sorted array
         */
        public static <T extends Comparable<T>> T[] selectionSort(final T[] arr){
            synchronized (LOCK){
                Objects.requireNonNull(arr, "Array cannot be null");
                if (arr.length < 2) return arr;
                T min;
                int index = 0;
                for (int i = 0; i < arr.length; i++){
                    min = arr[i];
                    for (int j = i; j < arr.length; j++) {
                        if (arr[j].compareTo(min) < 0){
                            index = j;
                            min = arr[j];
                        }
                    }
                    T temp = arr[i];
                    arr[i] = arr[index];
                    arr[index] = temp;
                }
                return arr;
            }
        }

        /**
         * Insertion Sort.
         * Best time O(n)
         * Average time O(n^2)
         * Worst time O(n^2)
         *
         * @param arr the array
         * @return the sorted array
         */
        public static <T extends Comparable<T>> T[] insertionSort(final T[] arr){
            synchronized (LOCK){
                Objects.requireNonNull(arr, "Array cannot be null");
                if (arr.length < 2) return arr;
                for (int i = 0; i < arr.length; i++) {
                    for (int j = 0; j < arr.length - 1; j++) {
                        if (arr[j].compareTo(arr[j + 1]) > 0){
                            T temp = arr[j + 1];
                            arr[j + 1] = arr[j];
                            arr[j] = temp;
                        }
                    }
                }
                return arr;
            }
        }

        /**
         * Merge Sort.
         * Best time O(n*log(n))
         * Average time O(n*log(n))
         * Worst time O(n*log(n))
         *
         * @param arr the array
         * @return the sorted array
         */
        public static <T extends Comparable<T>> T[] mergeSort(final T[] arr){
            synchronized (LOCK){
                Objects.requireNonNull(arr, "Array cannot be null");
                if (arr.length < 2) return arr;
                return mergeSort0(arr);
            }
        }
        @SuppressWarnings("unchecked")
        private static <T extends Comparable<T>> T[] mergeSort0(T[] arr){
            if (arr.length > 1){
                int a1 = arr.length / 2;
                @SuppressWarnings("UnnecessaryLocalVariable")
                int a2 = a1; //don't really need this, just there for clarity

                T[] one = (T[]) new Object[a1];
                T[] two = (T[]) new Object[a2];

                System.arraycopy(arr, 0, one, 0, a1);
                System.arraycopy(arr, a1, two, 0, a2);

                one = mergeSort0(one);
                two = mergeSort0(two);

                int i = 0, j = 0, k = 0;
                while(one.length != j && two.length != k){
                    if (one[j].compareTo(two[k]) < 0){
                        arr[i] = one[j];
                        i++;
                        j++;
                    }
                    else{
                        arr[i] = two[k];
                        i++;
                        k++;
                    }
                }
                while(one.length != j){
                    arr[i] = one[j];
                    i++;
                    j++;
                }
                while (two.length != k){
                    arr[i] = two[k];
                    i++;
                    k++;
                }
            }
            return arr;
        }

        /**
         * Quick Sort
         * Best time O(n*log(n))
         * Average time O(n*log(n))
         * Worst time O(n^2)
         *
         * @param arr the array
         * @return the sorted array.
         */
        public static <T extends Comparable<T>> T[] quickSort(final T[] arr){
            synchronized (LOCK){
                Objects.requireNonNull(arr, "Array cannot be null");
                if (arr.length < 2) return arr;
                return quickSort0(arr, 0, arr.length - 1);
            }
        }
        private static <T extends Comparable<T>> T[] quickSort0(final T[] arr, int low, int high){
            int i = low, j = high;
            T pivot = arr[low + (high - low) / 2];
            while (i <= j){
                while (arr[i].compareTo(pivot) < 0)  i++;
                while (arr[j].compareTo(pivot) > 0) j--;

                if (i <= j){
                    T temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                    i++;
                    j--;
                }
            }

            if (low < j) return quickSort0(arr, low, j);
            if (i < high) return quickSort0(arr, i, high);
            return arr;
        }

        @SafeVarargs
        public static <T extends Comparable<T>> Comparator<T> naturalOrder(Class<T>... clz){
            return Comparator.naturalOrder();
        }

        //Object array sorting (Objects are not comparable (using the Comparable interface)-with provided comparator)

        public static <T> boolean isSorted(final T[] arr, final Comparator<T> c){
            synchronized (LOCK){
                Objects.requireNonNull(c, "Comparator cannot be null");
                if (arr == null) return false;
                if (arr.length < 2) return true;
                for (int i = 1; i < arr.length; i++) {
                    if (!(c.compare(arr[i - 1], arr[i]) <= 0)) return false;
                }
                return true;
            }
        }
        public static <T> T[] sort(final T[] arr, final Comparator<T> c, final SortType type){
            Objects.requireNonNull(arr, "Array cannot be null");
            Objects.requireNonNull(c, "Comparator cannot be null");
            Objects.requireNonNull(type, "SortType cannot be null");
            if (arr.length < 2) return arr;
            if (type == BUBBLE_SORT) return bubbleSort(arr, c);
            else if (type == SELECTION_SORT) return selectionSort(arr, c);
            else if (type == INSERTION_SORT) return insertionSort(arr, c);
            else if (type == MERGE_SORT) return mergeSort(arr, c);
            else if (type == QUICK_SORT) return quickSort(arr, c);
            else throw new IllegalArgumentException("Illegal SortType"); //Should never happen
        }

        /**
         * Determines what type of sorting algorithm to use on the given array.
         * @see Sorts#BUBBLE_SORT_THRESHOLD
         * @see Sorts#INSERTION_SORT_THRESHOLD
         * @see Sorts#QUICKSORT_THRESHOLD
         *
         * @param arr the array.
         * @return the sorted array.
         */
        public static <T> T[] optimizedSort(final T[] arr, final Comparator<T> c){
            Objects.requireNonNull(arr, "Array cannot be null");
            Objects.requireNonNull(c, "Comparator cannot be null");
            if (arr.length < 2) return arr;
            final int len = arr.length;
            if (len < BUBBLE_SORT_THRESHOLD) return bubbleSort(arr, c);
            else if (len < INSERTION_SORT_THRESHOLD) return insertionSort(arr, c);
            else if (len < QUICKSORT_THRESHOLD) return quickSort(arr, c);
            else return mergeSort(arr, c); //if (len >= QUICKSORT_THRESHOLD)
        }

        /**
         * Sorts the array if if is not already sorted.
         *
         * @param arr the array.
         * @return the sorted array, or the array itself if it is already sorted.
         */
        public static <T> T[] sortIfNotSorted(final T[] arr, final Comparator<T> c){
            Objects.requireNonNull(arr, "Array cannot be null");
            Objects.requireNonNull(c, "Comparator cannot be null");
            if (isSorted(arr, c)) return arr;
            else return optimizedSort(arr, c);
        }
        /**
         * Sorts the array if if is not already sorted.
         *
         * @param arr the array.
         * @param type the type of sort to use.
         * @return the sorted array, or the array itself if it is already sorted.
         */
        public static <T> T[] sortIfNotSorted(final T[] arr, final Comparator<T> c, final SortType type){
            Objects.requireNonNull(arr, "Array cannot be null");
            Objects.requireNonNull(c, "Comparator cannot be null");
            Objects.requireNonNull(type, "SortType cannot be null");
            if (isSorted(arr, c)) return arr;
            else return sort(arr, c, type);
        }

        /**
         * Bubble Sort.
         * Best time O(n)
         * Average time O(n^2)
         * Worst time O(n^2)
         *
         * @param arr the array
         * @return the sorted array
         */
        public static <T> T[] bubbleSort(final T[] arr, final Comparator<T> c){
            synchronized (LOCK){
                Objects.requireNonNull(arr, "Array cannot be null");
                Objects.requireNonNull(c, "Comparator cannot be null");
                if (arr.length < 2) return arr;
                for (int i = 0; i < arr.length; i++){
                    for (int j = 0; j < arr.length - 1; j++) {
                        if (c.compare(arr[j], arr[j + 1]) > 0){
                            T temp = arr[j + 1];
                            arr[j + 1] = arr[j];
                            arr[j] = temp;
                        }
                    }
                }
                return arr;
            }
        }

        /**
         * Selection Sort.
         * Best time O(n^2)
         * Average time O(n^2)
         * Worst time O(n^2)
         *
         * @param arr the array
         * @return the sorted array
         */
        public static <T> T[] selectionSort(final T[] arr, final Comparator<T> c){
            synchronized (LOCK){
                Objects.requireNonNull(arr, "Array cannot be null");
                Objects.requireNonNull(c, "Comparator cannot be null");
                if (arr.length < 2) return arr;
                T min;
                int index = 0;
                for (int i = 0; i < arr.length; i++){
                    min = arr[i];
                    for (int j = i; j < arr.length; j++) {
                        if (c.compare(arr[j], min) < 0){
                            index = j;
                            min = arr[j];
                        }
                    }
                    T temp = arr[i];
                    arr[i] = arr[index];
                    arr[index] = temp;
                }
                return arr;
            }
        }

        /**
         * Insertion Sort.
         * Best time O(n)
         * Average time O(n^2)
         * Worst time O(n^2)
         *
         * @param arr the array
         * @return the sorted array
         */
        public static <T> T[] insertionSort(final T[] arr, final Comparator<T> c){
            synchronized (LOCK){
                Objects.requireNonNull(arr, "Array cannot be null");
                Objects.requireNonNull(c, "Comparator cannot be null");
                if (arr.length < 2) return arr;
                for (int i = 0; i < arr.length; i++) {
                    for (int j = 0; j < arr.length - 1; j++) {
                        if (c.compare(arr[j], arr[j + 1]) > 0){
                            T temp = arr[j + 1];
                            arr[j + 1] = arr[j];
                            arr[j] = temp;
                        }
                    }
                }
                return arr;
            }
        }

        /**
         * Merge Sort.
         * Best time O(n*log(n))
         * Average time O(n*log(n))
         * Worst time O(n*log(n))
         *
         * @param arr the array
         * @return the sorted array
         */
        public static <T> T[] mergeSort(final T[] arr, final Comparator<T> c){
            synchronized (LOCK){
                Objects.requireNonNull(arr, "Array cannot be null");
                Objects.requireNonNull(c, "Comparator cannot be null");
                if (arr.length < 2) return arr;
                return mergeSort0(arr, c);
            }
        }
        @SuppressWarnings("unchecked")
        private static <T> T[] mergeSort0(T[] arr, final Comparator<T> c){
            if (arr.length > 1){
                int a1 = arr.length / 2;
                @SuppressWarnings("UnnecessaryLocalVariable")
                int a2 = a1; //don't really need this, just there for clarity

                T[] one = (T[]) new Object[a1];
                T[] two = (T[]) new Object[a2];

                System.arraycopy(arr, 0, one, 0, a1);
                System.arraycopy(arr, a1, two, 0, a2);

                one = mergeSort0(one, c);
                two = mergeSort0(two, c);

                int i = 0, j = 0, k = 0;
                while(one.length != j && two.length != k){
                    if (c.compare(one[j], two[k]) < 0){
                        arr[i] = one[j];
                        i++;
                        j++;
                    }
                    else{
                        arr[i] = two[k];
                        i++;
                        k++;
                    }
                }
                while(one.length != j){
                    arr[i] = one[j];
                    i++;
                    j++;
                }
                while (two.length != k){
                    arr[i] = two[k];
                    i++;
                    k++;
                }
            }
            return arr;
        }

        /**
         * Quick Sort
         * Best time O(n*log(n))
         * Average time O(n*log(n))
         * Worst time O(n^2)
         *
         * @param arr the array
         * @return the sorted array.
         */
        public static <T> T[] quickSort(final T[] arr, final Comparator<T> c){
            synchronized (LOCK){
                Objects.requireNonNull(arr, "Array cannot be null");
                Objects.requireNonNull(c, "Comparator cannot be null");
                if (arr.length < 2) return arr;
                return quickSort0(arr, c, 0, arr.length - 1);
            }
        }
        private static <T> T[] quickSort0(final T[] arr, final Comparator<T> c, int low, int high){
            int i = low, j = high;
            T pivot = arr[low + (high - low) / 2];
            while (i <= j){
                while (c.compare(arr[i], pivot) < 0) i++;
                while (c.compare(arr[j], pivot) > 0) j--;

                if (i <= j){
                    T temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                    i++;
                    j--;
                }
            }

            if (low < j) return quickSort0(arr, c, low, j);
            if (i < high)return quickSort0(arr, c, i, high);
            return arr;
        }
    }

    /**
     * Logger class
     */
    public static final class Logger implements CanClone<Logger>{
        private String fileName;
        private ArrayList<String> logEntries;

        public Logger(){
            this(null);
        }
        public Logger(String fileName){
            logEntries = new ArrayList<>();
            this.fileName = fileName;
        }
        private String getDateAndTime(){
            return new SimpleDateFormat("MM_dd_yy-hh_mma").format(Calendar.getInstance().getTime());
        }
        private String getTime(){
            return new SimpleDateFormat("hh:mm:ssa").format(Calendar.getInstance().getTime());
        }

        /**
         * Standard logging events
         */
        public final void addLogEntry(String logEntry){
            logEntries.add(logEntry);
        }
        public final void error(String message){
            addLogEntry(getTime() + " [ERROR]: " + message);
        }
        public final void info(String message){
            addLogEntry(getTime() + " [INFO]: " + message);
        }
        public final void debug(String message){
            addLogEntry(getTime() + " [DEBUG]: " + message);
        }
        public final void warning(String message){
            addLogEntry(getTime() + " [WARNING]: " + message);
        }
        public final void throwable(Throwable t){
            StackTraceElement[] log = t.getStackTrace();
            addLogEntry(getTime() + "[THROWABLE]: " + t.toString());
            for (StackTraceElement element : log) addLogEntry("at " + element.toString());
            Throwable cause = t.getCause();
            if (cause != null){
                ArrayList<Throwable> list = new ArrayList<>();
                list.add(t);
                causeOf(cause, list);
            }
            addLogEntry("");
        }
        public final void exception(Exception e){
            StackTraceElement[] log = e.getStackTrace();
            addLogEntry(getTime() + " [EXCEPTION]: " + e.toString());
            for (StackTraceElement element : log) addLogEntry("\tat " + element.toString());
            Throwable cause = e.getCause();
            if (cause != null){
                ArrayList<Throwable> list = new ArrayList<>();
                list.add(e);
                causeOf(cause, list);
            }
            addLogEntry("");
        }
        private void causeOf(Throwable t, ArrayList<Throwable> list){
            t.printStackTrace();
            addLogEntry("Caused by: " + t.toString());
            StackTraceElement[] log0 = t.getStackTrace();
            for (StackTraceElement element : log0) addLogEntry("\tat " + element.toString());
            Throwable t0 = t.getCause();
            if (t0 != null){
                if (list.contains(t0)) addLogEntry("[CIRCULAR]: " + t0.toString());
                else{
                    list.add(t0);
                    causeOf(t0, list);
                }
            }
        }

        /**
         * variable logging (with variable name passed in)
         */
        public final void logValueOf(int i, String varName){
            addLogEntry("Value of int " + varName + " is: " + i);
        }
        public final void logValueOf(long l, String varName){
            addLogEntry("Value of long " + varName + " is: " + l);
        }
        public final void logValueOf(float f, String varName){
            addLogEntry("Value of float " + varName + " is: " + f);
        }
        public final void logValueOf(double d, String varName){
            addLogEntry("Value of double " + varName + " is: " + d);
        }
        public final void logValueOf(char c, String varName){
            addLogEntry("Value of char " + varName + " is: " + c);
        }
        public final void logValueOf(String s, String varName){
            addLogEntry("Value of String " + varName + " is: " + s);
        }
        public final <T> void logValueOf(T obj, String varName){
            addLogEntry("Value of " + varName + " is: " + Objects.toString(obj));
        }

        public final String[] getLog(){
            return logEntries.toArray(new String[logEntries.size()]);
        }
        public final void writeLog(){
            final String absolutePath = FileManipulator.ABSOLUTE_PATH;
            if (fileName == null) writeToFile(absolutePath + "\\" + getDateAndTime() + ".log");
            else writeToFile(absolutePath + "\\" + fileName + "-" + getDateAndTime() + ".log");
        }
        public final void writeLog(String path){
            if (path == null) throw new IllegalArgumentException("Path cannot be null.");
            if (path.equals("")) throw new IllegalArgumentException("Path cannot be empty");
            writeToFile(path);
        }
        private void writeToFile(String path){
            File file = new File(path);
            try{
                if (!file.createNewFile()) {
                    System.err.print("Error creating file.");
                    return;
                }
                PrintWriter writer = new PrintWriter(file, "UTF-8");

                for (String logEntry : logEntries) writer.println(logEntry);
                writer.close();
            }
            catch (IOException e){
                System.err.print("Error writing to file.");
            }
        }
        @Override
        public final String toString(){
            return this.getClass().getName() + " Entries: " + this.logEntries.size();
        }
        @Override
        public final Logger clone() {
            try{
                Logger l = (Logger) super.clone();
                l.logEntries = new ArrayList<>(this.logEntries);
                l.fileName = this.fileName;
                return l;
            }
            catch (CloneNotSupportedException e){
                // this shouldn't happen, since we are Cloneable
                throw new InternalError(e);
            }
        }
        @Override
        public final boolean equals(Object o){
            if (o == this) return true;
            if (o instanceof Logger){
                Logger that = (Logger) o;
                if (!(that.logEntries.size() == this.logEntries.size()) ||
                        !this.fileName.equals(that.fileName)) return false;
                for (int i = 0; i < logEntries.size(); i++){
                    if (!(that.logEntries.get(i).equals(this.logEntries.get(i)))) return false;
                }
                return true;
            }
            return false;
        }
        @Override
        public final int hashCode(){
            return Arrays.deepHashCode(logEntries.toArray());
        }
    }

    public static final class ReflectionHelper{
        private ReflectionHelper(){}

        private static final boolean sunReflectionSupported;
        private static final Method getCallerClass;
        private static final Object lock;

        static {
            Method getCallerClass0;
            try{
                final Class<?> reflect = Class.forName("sun.reflect.Reflection");
                getCallerClass0 = reflect.getDeclaredMethod("getCallerClass", int.class);
            }
            catch (ClassNotFoundException | NoSuchMethodException x){
                getCallerClass0 = null;
            }
            sunReflectionSupported = getCallerClass0 != null;
            getCallerClass = getCallerClass0;
            lock = new Object();
        }

        /**
         * @return the caller class at the index of {@code index} on the list
         *
         * @implNote The index for the caller class of the method/constructor that this method
         *           was called from is {@code 2}
         */
        public static Class<?> getCallerClass(final int index){
            if (!reflectionSupported()) throw new IllegalStateException("sun.reflect.Reflection.getCallerClass() is not supported");
            synchronized (lock){
                if (index < 0) throw new IllegalArgumentException(Integer.toString(index));
                try {
                    return (Class<?>) getCallerClass.invoke(null, index + 1);
                }
                catch (final IllegalAccessException | InvocationTargetException x){
                    throw new IllegalStateException("Error in ReflectionUtil.getCallerClass(int)", x);
                }

            }
        }

        /**
         * @return The caller class of the method/constructor that this method was called from
         */
        public static Class<?> getCallerClass(){
            return getCallerClass(3);
        }

        public static Class<?>[] getCallerTrace(){
            if (!reflectionSupported()) throw new IllegalStateException("sun.reflect.Reflection.getCallerClass() is not supported");
            synchronized (lock){
                ArrayList<Class<?>> list = new ArrayList<>();
                try{
                    int i = 0;
                    Class<?> clz = (Class<?>) getCallerClass.invoke(null, i);
                    while (clz != null){
                        list.add(clz);
                        clz = (Class<?>) getCallerClass.invoke(null, ++i);
                    }

                }
                catch (IllegalAccessException | InvocationTargetException x){
                    System.out.println("Exception navigating through frames");
                }
                return list.toArray(new Class<?>[list.size()]);
            }
        }

        public static String[] getCallerTraceString(){
            if (!reflectionSupported()) throw new IllegalStateException("sun.reflect.Reflection.getCallerClass() is not supported");
            synchronized (lock){
                ArrayList<String> list = new ArrayList<>();
                try{
                    int i = 0;
                    Class<?> clz = (Class<?>) getCallerClass.invoke(null, i);
                    while (clz != null){
                        list.add("Caller class at index[" + i + "]->" + clz.getName());
                        clz = (Class<?>) getCallerClass.invoke(null, ++i);
                    }

                }
                catch (IllegalAccessException | InvocationTargetException x){
                    System.out.println("Exception navigating through frames");
                }
                return list.toArray(new String[list.size()]);
            }
        }

        public static void printCallerTrace(){
            if (!reflectionSupported()) throw new IllegalStateException("sun.reflect.Reflection.getCallerClass() is not supported");
            synchronized (lock){
                try{
                    int i = 0;
                    Class<?> clz = (Class<?>) getCallerClass.invoke(null, i);
                    while (clz != null){
                        System.out.println("Caller class at index[" + i + "]->" + clz.getName());
                        clz = (Class<?>) getCallerClass.invoke(null, ++i);
                    }

                }
                catch (IllegalAccessException | InvocationTargetException x){
                    System.out.println("Exception navigating through frames");
                }
            }
        }

        public static boolean reflectionSupported(){
            return sunReflectionSupported;
        }
    }
}
package com.Mattk4355.Utils.Files;

import TestArea.Program;
import com.Mattk4355.Utils.Utils;

import java.io.*;
import java.awt.*;
import java.util.ArrayList;

@SuppressWarnings({"WeakerAccess", "unused"})
public final class FileManipulator {
    /**
     * suppress default constructor, ensuring non-instantiability.
     */
    private FileManipulator(){}
    static {
        currentDesktop = Desktop.getDesktop();
        File tmp = new File("");
        ABSOLUTE_PATH = getAbsolutePath(tmp);
    }
    //Current Desktop
    private static final Desktop currentDesktop;

    //New line String
    public static final String newLine = "\n";

    //Absolute path String
    public static final String ABSOLUTE_PATH;

    /**
     * The system-dependent default name-separator character.  This field is
     * initialized to contain the first character of the value of the system
     * property <code>file.separator</code>.  On UNIX systems the value of this
     * field is <code>'/'</code>; on Microsoft Windows systems it is <code>'\\'</code>.
     *
     * @see java.lang.System#getProperty(java.lang.String)
     * @see File#separatorChar
     */
    public static final char FILE_NAME_SEPARATOR_CHAR = File.separatorChar;

    /**
     * The system-dependent default name-separator character, represented as a
     * string for convenience.  This string contains a single character, namely
     * <code>{@link #FILE_NAME_SEPARATOR_CHAR}</code>.
     *
     * @see File#separator
     */
    public static final String FILE_NAME_SEPARATOR = File.separator;


//    public static final char FILE_PATH_SEPARATOR_CHAR = File.pathSeparatorChar;
//    public static final String FILE_PATH_SEPARATOR = File.pathSeparator;

    public static Desktop getCurrentDesktop(){
        return currentDesktop;
    }

    public static String getAbsolutePath(String path){
        Utils.Objects.requireNonNull(path, "Path cannot be null.");
        File file = new File(path);
        return getAbsolutePath0(file);
    }

    public static String getAbsolutePath(File file){
        Utils.Objects.requireNonNull(file, "File cannot be null.");
        return getAbsolutePath0(file);
    }

    private static String getAbsolutePath0(File file){
        return file.getAbsolutePath();
    }

    public static File newFile(String path){
        Utils.Objects.requireNonNull(path, "Path cannot be null.");
        return new File(path);
    }

    public static boolean isExecutable(String path){
        return RunnablePath.forOS().isExecutable(path);
    }

    public static void run(String path) throws IOException{
        Utils.Objects.requireNonNull(path, "Path cannot be null.");
        if (!RunnablePath.forOS().isExecutable(path)) throw new IllegalArgumentException(illeagalPathmsg(path));
        File file = new File(path);
        open0(file);
    }

    public static void run(File file) throws IOException{
        Utils.Objects.requireNonNull(file, "File cannot be null.");
        String path = file.getPath();
        if (!RunnablePath.forOS().isExecutable(path)) throw new IllegalArgumentException(illeagalPathmsg(path));
        open0(file);
    }

    private static String illeagalPathmsg(String path){
        return "path \"" + path + "\" is not executable";
    }

    public static int run(Program p) throws IOException{
        Utils.Objects.requireNonNull(p, "Program to execute cannot be null");
        return p.run();
    }

    /**
     * @return the name of this Operating System
     *
     * @see System#getProperty(String)
     */
    public static String OS(){
        return System.getProperty("os.name");
    }

    /**
     * Opens a file based on the given path.
     * @param path the path to the file.
     *
     * @throws IOException if an I/O error occurs.
     */
    public static void open(String path) throws IOException{
        Utils.Objects.requireNonNull(path, "Path cannot be null.");
        File file = new File(path);
        open0(file);
    }

    /**
     * Opens a file based on the given file.
     * @param file the file.
     *
     * @throws IOException if an I/O error occurs.
     */
    public static void open(File file) throws IOException{
        Utils.Objects.requireNonNull(file, "File cannot be null.");
        open0(file);
    }

    private static void open0(File file) throws IOException{
        currentDesktop.open(file);
    }

    /**
     * Checks if a file exists at the given path.
     *
     * @return true if the file exists at the given path, false otherwise.
     */
    public static boolean exists(String path){
        Utils.Objects.requireNonNull(path, "Path cannot be null.");
        File file = new File(path);
        return exists0(file);
    }

    /**
     * Checks if a file exists.
     *
     * @return true if the file exists, false otherwise.
     */
    public static boolean exists(File file){
        Utils.Objects.requireNonNull(file, "File cannot be null.");
        return exists0(file);
    }

    private static boolean exists0(File file){
        return file.exists();
    }

    /**
     * Writes the given String array to the file specified by <code> path </code>
     * @param path the path to the file that the array is to be written to.
     * @param values the values to be written.
     *
     * @throws IOException if an I/O error occurs
     */
    public static void write(String path, String... values) throws IOException{
        Utils.Objects.requireNonNull(path, "Path cannot be null.");
        Utils.Objects.requireNonNull(values, "Values to write cannot be null");
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        write0(writer, values);
    }

    /**
     * Writes the given String array to the specified file.
     * @param file the file that the array is to be written to.
     * @param values the values to be written.
     *
     * @throws IOException if an I/O error occurs
     */
    public static void write(File file, String... values) throws IOException{
        Utils.Objects.requireNonNull(file, "File cannot be null.");
        Utils.Objects.requireNonNull(values, "Values to write cannot be null");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        write0(writer, values);
    }

    /**
     * Writes the given String array to the specified BufferedWriter.
     * @param writer the writer with which to write.
     * @param values the values to be written.
     *
     * @throws IOException if an I/O error occurs
     */
    public static void write(BufferedWriter writer, String... values) throws IOException{
        Utils.Objects.requireNonNull(values, "Values to write cannot be null");
        Utils.Objects.requireNonNull(writer, "Writer cannot be null");
        write0(writer, values);
    }

    private static void write0(BufferedWriter writer, String... values) throws IOException{
        for (String s : values){
            if (s.equals(newLine)) writer.newLine();
            else writer.write(s);
        }
        writer.flush();
        writer.close();
    }

    /**
     * Reads a file specified by the given path.
     *
     * @return a String array of the contents of the file.
     *
     * @throws IOException if an I/O error occurs
     */
    public static String[] read(String path) throws IOException{
        Utils.Objects.requireNonNull(path, "Path cannot be null.");
        BufferedReader reader = new BufferedReader(new FileReader(new File(path)));
        return read0(reader);
    }

    /**
     * Reads the specified file.
     *
     * @return a String array of the contents of the file.
     *
     * @throws IOException if an I/O error occurs
     */
    public static String[] read(File file) throws IOException{
        Utils.Objects.requireNonNull(file, "File cannot be null.");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        return read0(reader);
    }

    /**
     * Reads the file from the specified BufferedReader.
     *
     * @return a String array of the contents of the file.
     *
     * @throws IOException if an I/O error occurs
     */
    public static String[] read(BufferedReader reader) throws IOException{
        Utils.Objects.requireNonNull(reader, "Reader cannot be null");
        return read0(reader);
    }

    private static String[] read0(BufferedReader reader) throws IOException{
        ArrayList<String> tmpList = new ArrayList<>();
        String s;
        while ((s = reader.readLine()) != null) tmpList.add(s);
        return tmpList.toArray(new String[tmpList.size()]);
    }

    public static String[] removeComments(String[] values, String... comments){
        Utils.Objects.requireNonNull(values, "Values cannot be null.");
        Utils.Objects.requireNonNull(comments, "Comments cannot be null");
        if (comments.length == 0) return values; //no comment types
        ArrayList<String> tmpList = new ArrayList<>();
        for (String s : values){
            if (!isComment(s, comments)) tmpList.add(s);
        }
        return tmpList.toArray(new String[tmpList.size()]);
    }
    private static boolean isComment(String data, String[] comments){
        for (String s : comments){
            if (data.startsWith(s)) return true;
        }
        return false;
    }
}
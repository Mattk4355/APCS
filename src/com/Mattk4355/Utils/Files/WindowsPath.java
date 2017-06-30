package com.Mattk4355.Utils.Files;

import java.io.File;

final class WindowsPath extends RunnablePath {
    private WindowsPath(){}
    static final RunnablePath INSTANCE = new WindowsPath();

    private static final String[] EXEC_ENDINGS = {".bat", ".bin", ".cmd", ".com", ".cpl", ".exe", ".gadget", ".inf", ".ins",
            ".inx", ".isu", ".job", ".jse", ".lnk", ".msi", ".msp", ".mst", ".psf", ".pif", ".ps1", ".reg", ".rgs", ".scr",
            ".sct", ".shb", ".shs", ".u3p", ".vb", ".vbe", ".vbs", ".vbscript", ".ws", ".wsf", ".wsh"};
    private static final ExtensionList list = new ExtensionList(EXEC_ENDINGS);

    boolean isExecutable(String path){
        int index = path.lastIndexOf(File.separator);
        String name = index >= 0 ? path.substring(index) : path;
        String ending = name.split("\\.")[1];
        //check for names with multiple dots (aka test.exe.bak)
        while (ending.contains(".")) ending = ending.split("\\.")[1];
        //add back dot for list
        ending = "." + ending;
        return list.hasExtension(ending);
    }
}

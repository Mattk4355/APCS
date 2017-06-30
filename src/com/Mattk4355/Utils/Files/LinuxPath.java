package com.Mattk4355.Utils.Files;

import java.io.File;


final class LinuxPath extends RunnablePath{
    private LinuxPath(){}
    static final RunnablePath INSTANCE = new LinuxPath();

    private static final String[] EXEC_ENDINGS = {".bin", ".csh", ".ksh", ".out", ".run"};
    private static final ExtensionList list = new ExtensionList(EXEC_ENDINGS);

    boolean isExecutable(String path){
        int index = path.lastIndexOf(File.separator);
        String name = index > 0 ? path.substring(index) : path;
        String ending = name.split("\\.")[1];
        //check for names with multiple dots (aka test.exe.bak)
        while (ending.contains(".")) ending = ending.split("\\.")[1];
        //add back dot for list
        ending = "." + ending;
        return list.hasExtension(ending);
    }
}

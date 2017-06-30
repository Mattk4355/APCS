package com.Mattk4355.Utils.Files;

import java.io.File;

final class MacPath extends RunnablePath{
    private MacPath(){}
    static final RunnablePath INSTANCE = new MacPath();

    private static final String[] EXEC_ENDINGS = {".action", ".app", ".bin", ".command", ".csh", ".osx", ".workflow"};
    private static final ExtensionList list = new ExtensionList(EXEC_ENDINGS);

    @Override
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

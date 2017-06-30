package com.Mattk4355.Utils.Files;

abstract class RunnablePath {
    abstract boolean isExecutable(String path);

    //cache to avoid unnecessary calls
    private static RunnablePath OS = null;
    static RunnablePath forOS(){
        if (OS == null) OS = forOS0(); //init on first call
        return OS;
    }

    //allow OS to be updated (for whatever reason)
    static void refreshOS(){
        OS = forOS0();
    }

    private static RunnablePath forOS0(){
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("windows")) return WindowsPath.INSTANCE;
        else if (os.contains("mac")) return MacPath.INSTANCE;
        else if (os.contains("linux")) return LinuxPath.INSTANCE;
        else return UndefinedPath.INSTANCE;
    }
}

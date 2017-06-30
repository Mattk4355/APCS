package com.Mattk4355.Utils.Files;

final class UndefinedPath extends RunnablePath{
    private UndefinedPath(){}
    static final RunnablePath INSTANCE = new UndefinedPath();

    @Override
    boolean isExecutable(String path) {
        return false;
    }
}

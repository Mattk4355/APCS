package com.Mattk4355.Utils.Files;

import com.Mattk4355.Utils.ImmutableList;

final class ExtensionList extends ImmutableList<String> {
    ExtensionList(String[] list){
        super(list);
    }
    boolean hasExtension(String extension){
        for (String s : this){
            if (s.equalsIgnoreCase(extension)) return true;
        }
        return false;
    }
}

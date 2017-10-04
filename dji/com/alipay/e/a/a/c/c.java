package com.alipay.e.a.a.c;

import java.io.File;
import java.io.FileFilter;
import java.util.regex.Pattern;

class c implements FileFilter {
    final /* synthetic */ b a;

    c(b bVar) {
        this.a = bVar;
    }

    public boolean accept(File file) {
        return Pattern.matches("cpu[0-9]+", file.getName());
    }
}

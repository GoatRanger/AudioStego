package com.nokia.maps;

import java.io.File;

public class bm {
    private static final String a = bm.class.getName();

    public Boolean a(String str) {
        File file = new File(str);
        Boolean valueOf = Boolean.valueOf(file.exists());
        if (valueOf.booleanValue()) {
            return valueOf;
        }
        return Boolean.valueOf(file.mkdir());
    }

    public Boolean b(String str) {
        bj.e(a, "fileExisted : filePath:", new Object[]{str});
        return Boolean.valueOf(new File(str).exists());
    }

    public Boolean a(String str, String str2) {
        bj.e(a, "renameTo : fromFilePath:" + str + " toFilePath:" + str2, new Object[0]);
        return Boolean.valueOf(new File(str).renameTo(new File(str2)));
    }

    public Boolean c(String str) {
        bj.e(a, "delete : filePath:", new Object[]{str});
        return Boolean.valueOf(new File(str).delete());
    }
}

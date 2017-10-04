package com.nokia.maps;

import android.content.Context;
import java.io.File;

public class bf {
    static String a;
    private static Object b = new Object();

    public static Boolean a(String str) {
        bj.c("LibraryLoader", ">> Load library(" + str + ") ...", new Object[0]);
        Boolean valueOf = Boolean.valueOf(true);
        try {
            System.loadLibrary(str);
            return valueOf;
        } catch (Error e) {
            bj.c("LibraryLoader", String.format("Library %s loaded with error: %s", new Object[]{str, e.getLocalizedMessage()}), new Object[0]);
            return Boolean.valueOf(false);
        } catch (Exception e2) {
            bj.c("LibraryLoader", String.format("Library %s loaded with exception: %s", new Object[]{str, e2.getLocalizedMessage()}), new Object[0]);
            return Boolean.valueOf(false);
        }
    }

    static boolean a(Context context) {
        if (a == null) {
            synchronized (b) {
                if (a == null) {
                    a = "gnustl_shared";
                    File file = new File(context.getApplicationInfo().dataDir + "/lib");
                    if (file.exists() && file.isDirectory()) {
                        File[] listFiles = file.listFiles();
                        StringBuilder stringBuilder = new StringBuilder("lib");
                        stringBuilder.append("gnustl_shared");
                        stringBuilder.append(".so");
                        String stringBuilder2 = stringBuilder.toString();
                        if (listFiles != null) {
                            for (File file2 : listFiles) {
                                if (file2.isFile() && file2.getName().compareTo(stringBuilder2) == 0) {
                                    a = "gnustl_shared";
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        return a(a).booleanValue();
    }
}

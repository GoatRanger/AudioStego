package com.here.b.c;

import android.annotation.SuppressLint;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class c {
    public static String a(String str) {
        if (str == null || str.length() <= 8) {
            return UUID.randomUUID().toString() + a() + "i";
        }
        if (b(str) && str.endsWith("i")) {
            return str.substring(0, str.length() - 1);
        }
        return str;
    }

    @SuppressLint({"SimpleDateFormat"})
    public static String a() {
        return new SimpleDateFormat("yyyy-MM").format(new Date());
    }

    private static boolean b(String str) {
        if (str.endsWith("i")) {
            str = str.substring(0, str.length() - 1);
        }
        if (str.length() >= 7) {
            str = str.substring(str.length() - 7);
        }
        if (a().equals(str)) {
            return false;
        }
        return true;
    }
}

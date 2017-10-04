package com.dji.frame.c;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class l {
    private static final String a = "yyyy-MM-dd";
    private static final String b = "yyyy-MM-dd hh:mm:ss";

    public static String a(Date date, String str) {
        return new SimpleDateFormat(str).format(date);
    }

    public static String a(Date date) {
        return a(date, a);
    }

    public static String a() {
        return a(new Date(), a);
    }

    public static String b() {
        return a(new Date(), b);
    }

    public static String b(Date date) {
        return a(date, b);
    }

    public static String a(ArrayList<String> arrayList, String str) {
        StringBuffer stringBuffer = new StringBuffer();
        if (arrayList != null && arrayList.size() > 0) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                stringBuffer.append((String) it.next());
                stringBuffer.append(str);
            }
            stringBuffer.delete(stringBuffer.length() - 1, stringBuffer.length());
        }
        return stringBuffer.toString();
    }

    public static boolean a(String str) {
        return str == null || str.length() == 0;
    }
}

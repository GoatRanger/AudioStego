package com.here.a.a.a;

import com.here.a.a.a.a.m;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class s {
    private static final Pattern a = Pattern.compile("P(?:(\\d+)Y)?(?:(\\d+)M)?(?:(\\d+)D)?(?:T(?:(\\d+)H)?(?:(\\d+)M)?(?:(\\d+)S)?)?");
    private static final ThreadLocal<DateFormat> b = new ThreadLocal<DateFormat>() {
        protected /* synthetic */ Object initialValue() {
            return a();
        }

        protected DateFormat a() {
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        }
    };

    public static String a(Date date) {
        return ((DateFormat) b.get()).format(date);
    }

    public static Date a(String str) {
        try {
            return ((DateFormat) b.get()).parse(str);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public static long b(String str) {
        Matcher matcher = a.matcher(str);
        if (matcher.matches()) {
            int[] iArr = new int[matcher.groupCount()];
            for (int i = 1; i <= matcher.groupCount(); i++) {
                try {
                    iArr[i - 1] = Integer.parseInt(matcher.group(i));
                } catch (NumberFormatException e) {
                    iArr[i - 1] = 0;
                }
            }
            return (long) ((((((946080000 * iArr[0]) + (iArr[1] * 2592000)) + (86400 * iArr[2])) + (iArr[3] * 3600)) + (iArr[4] * 60)) + iArr[5]);
        }
        throw new IllegalArgumentException("Cannot parse give duration value.");
    }

    public static double a(m mVar, m mVar2) {
        double toRadians = Math.toRadians(mVar2.a - mVar.a);
        double toRadians2 = Math.toRadians(mVar2.b - mVar.b);
        toRadians = (Math.sin(toRadians / 2.0d) * Math.sin(toRadians / 2.0d)) + (Math.sin(toRadians2 / 2.0d) * ((Math.cos(Math.toRadians(mVar.a)) * Math.cos(Math.toRadians(mVar2.a))) * Math.sin(toRadians2 / 2.0d)));
        return 6371000.0d * (Math.atan2(Math.sqrt(toRadians), Math.sqrt(1.0d - toRadians)) * 2.0d);
    }

    public static String a(Set<String> set) {
        return a((Set) set, ",");
    }

    public static String a(Set<String> set, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            stringBuilder.append((String) it.next());
            if (it.hasNext()) {
                stringBuilder.append(str);
            }
        }
        return stringBuilder.toString();
    }
}

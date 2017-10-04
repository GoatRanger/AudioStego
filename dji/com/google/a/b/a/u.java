package com.google.a.b.a;

import com.alipay.sdk.h.a;
import com.google.a.r;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public abstract class u {
    private static final u[] a = new u[]{new f(), new c(), new j(), new b(), new af(), new e(), new ag(), new i(), new y(), new aa(), new v(), new x(), new n(), new ak(), new ae(), new ad(), new p(), new t(), new l(), new ai()};
    private static final Pattern b = Pattern.compile("\\d+");
    private static final Pattern c = Pattern.compile(a.b);
    private static final Pattern d = Pattern.compile("=");
    private static final String e = "﻿";

    public abstract q b(r rVar);

    protected static String c(r rVar) {
        String a = rVar.a();
        if (a.startsWith("﻿")) {
            return a.substring(1);
        }
        return a;
    }

    public static q d(r rVar) {
        for (u b : a) {
            q b2 = b.b(rVar);
            if (b2 != null) {
                return b2;
            }
        }
        return new ab(rVar.a(), null);
    }

    protected static void a(String str, StringBuilder stringBuilder) {
        if (str != null) {
            stringBuilder.append('\n');
            stringBuilder.append(str);
        }
    }

    protected static void a(String[] strArr, StringBuilder stringBuilder) {
        if (strArr != null) {
            for (String str : strArr) {
                stringBuilder.append('\n');
                stringBuilder.append(str);
            }
        }
    }

    protected static String[] b(String str) {
        if (str == null) {
            return null;
        }
        return new String[]{str};
    }

    protected static String c(String str) {
        int indexOf = str.indexOf(92);
        if (indexOf < 0) {
            return str;
        }
        int length = str.length();
        StringBuilder stringBuilder = new StringBuilder(length - 1);
        stringBuilder.append(str.toCharArray(), 0, indexOf);
        indexOf = 0;
        for (int i = indexOf; i < length; i++) {
            char charAt = str.charAt(i);
            if (indexOf == 0 && charAt == '\\') {
                indexOf = 1;
            } else {
                stringBuilder.append(charAt);
                indexOf = 0;
            }
        }
        return stringBuilder.toString();
    }

    protected static int a(char c) {
        if (c >= '0' && c <= '9') {
            return c - 48;
        }
        if (c >= 'a' && c <= 'f') {
            return (c - 97) + 10;
        }
        if (c < 'A' || c > 'F') {
            return -1;
        }
        return (c - 65) + 10;
    }

    protected static boolean a(CharSequence charSequence, int i) {
        return charSequence != null && i > 0 && i == charSequence.length() && b.matcher(charSequence).matches();
    }

    protected static boolean a(CharSequence charSequence, int i, int i2) {
        if (charSequence == null || i2 <= 0) {
            return false;
        }
        int i3 = i + i2;
        if (charSequence.length() < i3 || !b.matcher(charSequence.subSequence(i, i3)).matches()) {
            return false;
        }
        return true;
    }

    static Map<String, String> d(String str) {
        int indexOf = str.indexOf(63);
        if (indexOf < 0) {
            return null;
        }
        Map hashMap = new HashMap(3);
        for (CharSequence a : c.split(str.substring(indexOf + 1))) {
            a(a, hashMap);
        }
        return hashMap;
    }

    private static void a(CharSequence charSequence, Map<String, String> map) {
        String[] split = d.split(charSequence, 2);
        if (split.length == 2) {
            try {
                map.put(split[0], e(split[1]));
            } catch (IllegalArgumentException e) {
            }
        }
    }

    static String e(String str) {
        try {
            return URLDecoder.decode(str, "UTF-8");
        } catch (Throwable e) {
            throw new IllegalStateException(e);
        }
    }

    static String[] a(String str, String str2, char c, boolean z) {
        int length = str2.length();
        int i = 0;
        List list = null;
        while (i < length) {
            i = str2.indexOf(str, i);
            if (i < 0) {
                break;
            }
            int length2 = i + str.length();
            Object obj = 1;
            List list2 = list;
            int i2 = length2;
            while (obj != null) {
                int indexOf = str2.indexOf(c, i2);
                if (indexOf < 0) {
                    i2 = str2.length();
                    obj = null;
                } else if (str2.charAt(indexOf - 1) == '\\') {
                    i2 = indexOf + 1;
                } else {
                    if (list2 == null) {
                        list2 = new ArrayList(3);
                    }
                    String c2 = c(str2.substring(length2, indexOf));
                    if (z) {
                        c2 = c2.trim();
                    }
                    if (!c2.isEmpty()) {
                        list2.add(c2);
                    }
                    i2 = indexOf + 1;
                    obj = null;
                }
            }
            int i3 = i2;
            list = list2;
            i = i3;
        }
        if (list == null || list.isEmpty()) {
            return null;
        }
        return (String[]) list.toArray(new String[list.size()]);
    }

    static String b(String str, String str2, char c, boolean z) {
        String[] a = a(str, str2, c, z);
        return a == null ? null : a[0];
    }
}

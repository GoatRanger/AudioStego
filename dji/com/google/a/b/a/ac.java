package com.google.a.b.a;

import java.util.regex.Pattern;

public final class ac extends q {
    private static final Pattern a = Pattern.compile(":/*([^/@]+)@[^/]+");
    private final String b;
    private final String c;

    public ac(String str, String str2) {
        super(r.URI);
        this.b = a(str);
        this.c = str2;
    }

    public String a() {
        return this.b;
    }

    public String b() {
        return this.c;
    }

    public boolean c() {
        return a.matcher(this.b).find();
    }

    public String q() {
        StringBuilder stringBuilder = new StringBuilder(30);
        q.a(this.c, stringBuilder);
        q.a(this.b, stringBuilder);
        return stringBuilder.toString();
    }

    private static String a(String str) {
        String trim = str.trim();
        int indexOf = trim.indexOf(58);
        if (indexOf < 0) {
            return "http://" + trim;
        }
        if (a(trim, indexOf)) {
            return "http://" + trim;
        }
        return trim;
    }

    private static boolean a(String str, int i) {
        int i2 = i + 1;
        int indexOf = str.indexOf(47, i2);
        if (indexOf < 0) {
            indexOf = str.length();
        }
        return u.a(str, i2, indexOf - i2);
    }
}

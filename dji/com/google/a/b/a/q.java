package com.google.a.b.a;

public abstract class q {
    private final r a;

    public abstract String q();

    protected q(r rVar) {
        this.a = rVar;
    }

    public final r r() {
        return this.a;
    }

    public final String toString() {
        return q();
    }

    public static void a(String str, StringBuilder stringBuilder) {
        if (str != null && !str.isEmpty()) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append('\n');
            }
            stringBuilder.append(str);
        }
    }

    public static void a(String[] strArr, StringBuilder stringBuilder) {
        if (strArr != null) {
            for (String a : strArr) {
                a(a, stringBuilder);
            }
        }
    }
}

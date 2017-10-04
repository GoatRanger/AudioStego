package com.ut.mini.d;

import java.util.Arrays;
import java.util.Comparator;

public class f {
    private static f a = null;
    private b b = new b();
    private a c = new a();

    private class a implements Comparator<String> {
        final /* synthetic */ f a;

        private a(f fVar) {
            this.a = fVar;
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return a((String) obj, (String) obj2);
        }

        public int a(String str, String str2) {
            if (n.a(str) || n.a(str2)) {
                return 0;
            }
            return str.compareTo(str2);
        }
    }

    private class b implements Comparator<String> {
        final /* synthetic */ f a;

        private b(f fVar) {
            this.a = fVar;
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return a((String) obj, (String) obj2);
        }

        public int a(String str, String str2) {
            if (n.a(str) || n.a(str2)) {
                return 0;
            }
            return str.compareTo(str2) * -1;
        }
    }

    private f() {
    }

    public static synchronized f a() {
        f fVar;
        synchronized (f.class) {
            if (a == null) {
                a = new f();
            }
            fVar = a;
        }
        return fVar;
    }

    public String[] a(String[] strArr, boolean z) {
        Comparator comparator;
        if (z) {
            comparator = this.c;
        } else {
            comparator = this.b;
        }
        if (comparator == null || strArr == null || strArr.length <= 0) {
            return null;
        }
        Arrays.sort(strArr, comparator);
        return strArr;
    }
}

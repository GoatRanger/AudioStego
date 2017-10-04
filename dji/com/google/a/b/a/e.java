package com.google.a.b.a;

import com.google.a.r;
import java.util.ArrayList;
import java.util.List;

public final class e extends a {
    public /* synthetic */ q b(r rVar) {
        return a(rVar);
    }

    public d a(r rVar) {
        String c = u.c(rVar);
        if (!c.startsWith("BIZCARD:")) {
            return null;
        }
        String a = a(a.b("N:", c, true), a.b("X:", c, true));
        String b = a.b("T:", c, true);
        String b2 = a.b("C:", c, true);
        return new d(u.b(a), null, null, a(a.b("B:", c, true), a.b("M:", c, true), a.b("F:", c, true)), null, u.b(a.b("E:", c, true)), null, null, null, a.a("A:", c, true), null, b2, null, b, null, null);
    }

    private static String[] a(String str, String str2, String str3) {
        List arrayList = new ArrayList(3);
        if (str != null) {
            arrayList.add(str);
        }
        if (str2 != null) {
            arrayList.add(str2);
        }
        if (str3 != null) {
            arrayList.add(str3);
        }
        int size = arrayList.size();
        if (size == 0) {
            return null;
        }
        return (String[]) arrayList.toArray(new String[size]);
    }

    private static String a(String str, String str2) {
        if (str == null) {
            return str2;
        }
        if (str2 != null) {
            str = str + ' ' + str2;
        }
        return str;
    }
}

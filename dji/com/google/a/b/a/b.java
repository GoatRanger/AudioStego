package com.google.a.b.a;

import com.google.a.r;
import com.here.odnp.debug.DebugFile;
import java.util.ArrayList;
import java.util.List;

public final class b extends u {
    public /* synthetic */ q b(r rVar) {
        return a(rVar);
    }

    public d a(r rVar) {
        String c = u.c(rVar);
        if (!c.contains("MEMORY") || !c.contains(DebugFile.EOL)) {
            return null;
        }
        return new d(u.b(u.b("NAME1:", c, '\r', true)), null, u.b("NAME2:", c, '\r', true), a("TEL", 3, c, true), null, a("MAIL", 3, c, true), null, null, u.b("MEMORY:", c, '\r', false), u.b("ADD:", c, '\r', true) == null ? null : new String[]{u.b("ADD:", c, '\r', true)}, null, null, null, null, null, null);
    }

    private static String[] a(String str, int i, String str2, boolean z) {
        List list = null;
        for (int i2 = 1; i2 <= i; i2++) {
            String b = u.b(str + i2 + ':', str2, '\r', z);
            if (b == null) {
                break;
            }
            if (list == null) {
                list = new ArrayList(i);
            }
            list.add(b);
        }
        if (list == null) {
            return null;
        }
        return (String[]) list.toArray(new String[list.size()]);
    }
}

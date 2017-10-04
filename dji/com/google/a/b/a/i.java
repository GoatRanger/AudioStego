package com.google.a.b.a;

import com.google.a.r;
import java.util.Map;

public final class i extends u {
    public /* synthetic */ q b(r rVar) {
        return a(rVar);
    }

    public h a(r rVar) {
        String str = null;
        String c = u.c(rVar);
        if (c.startsWith("mailto:") || c.startsWith("MAILTO:")) {
            String str2;
            String substring = c.substring(7);
            int indexOf = substring.indexOf(63);
            if (indexOf >= 0) {
                substring = substring.substring(0, indexOf);
            }
            substring = u.e(substring);
            Map d = u.d(c);
            if (d != null) {
                if (substring.isEmpty()) {
                    str2 = (String) d.get("to");
                } else {
                    str2 = substring;
                }
                substring = (String) d.get("subject");
                str = (String) d.get("body");
            } else {
                str2 = substring;
                substring = null;
            }
            return new h(str2, substring, str, c);
        } else if (j.a(c)) {
            return new h(c, null, null, "mailto:" + c);
        } else {
            return null;
        }
    }
}

package dji.thirdparty.b.a.b;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import dji.pilot.publics.control.p3cupgrade.b;
import dji.sdksharedlib.b.d;
import dji.thirdparty.b.a.h;
import dji.thirdparty.b.ab;
import dji.thirdparty.b.ad;
import dji.thirdparty.b.t;
import dji.thirdparty.b.t.a;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public final class j {
    static final String a = h.a().b();
    public static final String b = (a + "-Sent-Millis");
    public static final String c = (a + "-Received-Millis");
    public static final String d = (a + "-Selected-Protocol");
    public static final String e = (a + "-Response-Source");

    private j() {
    }

    public static long a(ab abVar) {
        return a(abVar.c());
    }

    public static long a(ad adVar) {
        return a(adVar.g());
    }

    public static long a(t tVar) {
        return b(tVar.a("Content-Length"));
    }

    private static long b(String str) {
        long j = -1;
        if (str != null) {
            try {
                j = Long.parseLong(str);
            } catch (NumberFormatException e) {
            }
        }
        return j;
    }

    public static boolean a(ad adVar, t tVar, ab abVar) {
        for (String str : d(adVar)) {
            if (!dji.thirdparty.b.a.j.a(tVar.c(str), abVar.b(str))) {
                return false;
            }
        }
        return true;
    }

    public static boolean b(ad adVar) {
        return b(adVar.g());
    }

    public static boolean b(t tVar) {
        return c(tVar).contains("*");
    }

    private static Set<String> d(ad adVar) {
        return c(adVar.g());
    }

    public static Set<String> c(t tVar) {
        Set<String> emptySet = Collections.emptySet();
        int a = tVar.a();
        for (int i = 0; i < a; i++) {
            if ("Vary".equalsIgnoreCase(tVar.a(i))) {
                String b = tVar.b(i);
                if (emptySet.isEmpty()) {
                    emptySet = new TreeSet(String.CASE_INSENSITIVE_ORDER);
                }
                for (String trim : b.split(",")) {
                    emptySet.add(trim.trim());
                }
            }
        }
        return emptySet;
    }

    public static t c(ad adVar) {
        return a(adVar.k().a().c(), adVar.g());
    }

    public static t a(t tVar, t tVar2) {
        Set c = c(tVar2);
        if (c.isEmpty()) {
            return new a().a();
        }
        a aVar = new a();
        int a = tVar.a();
        for (int i = 0; i < a; i++) {
            String a2 = tVar.a(i);
            if (c.contains(a2)) {
                aVar.a(a2, tVar.b(i));
            }
        }
        return aVar.a();
    }

    static boolean a(String str) {
        return (d.ck.equalsIgnoreCase(str) || "Keep-Alive".equalsIgnoreCase(str) || "Proxy-Authenticate".equalsIgnoreCase(str) || "Proxy-Authorization".equalsIgnoreCase(str) || "TE".equalsIgnoreCase(str) || "Trailers".equalsIgnoreCase(str) || "Transfer-Encoding".equalsIgnoreCase(str) || b.n.equalsIgnoreCase(str)) ? false : true;
    }

    public static List<dji.thirdparty.b.h> a(t tVar, String str) {
        List<dji.thirdparty.b.h> arrayList = new ArrayList();
        int a = tVar.a();
        for (int i = 0; i < a; i++) {
            if (str.equalsIgnoreCase(tVar.a(i))) {
                String b = tVar.b(i);
                int i2 = 0;
                while (i2 < b.length()) {
                    int a2 = c.a(b, i2, MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                    String trim = b.substring(i2, a2).trim();
                    a2 = c.a(b, a2);
                    if (!b.regionMatches(true, a2, "realm=\"", 0, "realm=\"".length())) {
                        break;
                    }
                    i2 = "realm=\"".length() + a2;
                    a2 = c.a(b, i2, "\"");
                    String substring = b.substring(i2, a2);
                    i2 = c.a(b, c.a(b, a2 + 1, ",") + 1);
                    arrayList.add(new dji.thirdparty.b.h(trim, substring));
                }
            }
        }
        return arrayList;
    }
}

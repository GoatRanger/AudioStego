package dji.pilot2.scan.b;

import android.content.Intent;
import android.net.Uri;
import dji.pilot2.scan.android.f.c;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public final class a {
    public static final Set<com.google.a.a> a = EnumSet.of(com.google.a.a.o, new com.google.a.a[]{com.google.a.a.p, com.google.a.a.h, com.google.a.a.g, com.google.a.a.m, com.google.a.a.n});
    static final Set<com.google.a.a> b = EnumSet.of(com.google.a.a.c, com.google.a.a.d, com.google.a.a.e, com.google.a.a.i, com.google.a.a.b);
    static final Set<com.google.a.a> c = EnumSet.of(com.google.a.a.l);
    static final Set<com.google.a.a> d = EnumSet.of(com.google.a.a.f);
    static final Set<com.google.a.a> e = EnumSet.of(com.google.a.a.a);
    static final Set<com.google.a.a> f = EnumSet.of(com.google.a.a.k);
    private static final Pattern g = Pattern.compile(",");
    private static final Set<com.google.a.a> h = EnumSet.copyOf(a);
    private static final Map<String, Set<com.google.a.a>> i = new HashMap();

    static {
        h.addAll(b);
        i.put(c.d, h);
        i.put(c.c, a);
        i.put(c.e, c);
        i.put(c.f, d);
        i.put(c.g, e);
        i.put(c.h, f);
    }

    private a() {
    }

    public static Set<com.google.a.a> a(Intent intent) {
        Iterable iterable = null;
        CharSequence stringExtra = intent.getStringExtra(c.i);
        if (stringExtra != null) {
            iterable = Arrays.asList(g.split(stringExtra));
        }
        return a(iterable, intent.getStringExtra(c.b));
    }

    public static Set<com.google.a.a> a(Uri uri) {
        Iterable queryParameters = uri.getQueryParameters(c.i);
        if (!(queryParameters == null || queryParameters.size() != 1 || queryParameters.get(0) == null)) {
            queryParameters = Arrays.asList(g.split((CharSequence) queryParameters.get(0)));
        }
        return a(queryParameters, uri.getQueryParameter(c.b));
    }

    private static Set<com.google.a.a> a(Iterable<String> iterable, String str) {
        if (iterable != null) {
            Set<com.google.a.a> noneOf = EnumSet.noneOf(com.google.a.a.class);
            try {
                for (String valueOf : iterable) {
                    noneOf.add(com.google.a.a.valueOf(valueOf));
                }
                return noneOf;
            } catch (IllegalArgumentException e) {
            }
        }
        if (str != null) {
            return (Set) i.get(str);
        }
        return null;
    }
}

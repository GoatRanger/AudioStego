package com.here.a.a.a.a;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class t {
    public final String a;
    public final a b;
    public final boolean c;
    public final ad<String> d;
    public final ad<String> e;
    private final Collection<String> f;

    public enum a {
        AGENCY,
        AGENCY_LOGO,
        TARIFF,
        ALERT,
        WEBSITE,
        BOOKING,
        UNKNOWN;

        public static a a(String str) {
            if ("agency".equalsIgnoreCase(str)) {
                return AGENCY;
            }
            if ("logo".equalsIgnoreCase(str)) {
                return AGENCY_LOGO;
            }
            if ("tariff".equalsIgnoreCase(str)) {
                return TARIFF;
            }
            if ("alert".equalsIgnoreCase(str)) {
                return ALERT;
            }
            if ("website".equalsIgnoreCase(str)) {
                return WEBSITE;
            }
            if ("booking".equalsIgnoreCase(str)) {
                return BOOKING;
            }
            return UNKNOWN;
        }
    }

    public t(String str, a aVar, boolean z, String str2, String str3, Collection<String> collection) {
        if (str == null || aVar == null) {
            throw new NullPointerException("Link href and type can't be null.");
        }
        if (collection == null) {
            collection = Collections.emptyList();
        }
        this.a = str;
        this.b = aVar;
        this.c = z;
        this.d = ad.b(str2);
        this.e = ad.b(str3);
        this.f = collection;
    }

    public Collection<String> a() {
        return Collections.unmodifiableCollection(this.f);
    }

    public static t fromJSON(o oVar) {
        boolean z = true;
        Collection collection = null;
        String[] split = oVar.a("@sec_ids", "").split("\\s");
        String i = oVar.i("@href");
        a a = a.a(oVar.i("@id"));
        if (!(oVar.b("@req") || oVar.j("@req").intValue() == 1)) {
            z = false;
        }
        String a2 = oVar.a("$", null);
        String a3 = oVar.a("@href_text", null);
        if (split.length > 0) {
            collection = Arrays.asList(split);
        }
        return new t(i, a, z, a2, a3, collection);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        t tVar = (t) obj;
        if (this.c == tVar.c && this.a.equals(tVar.a) && this.e.equals(tVar.e) && this.b == tVar.b && this.d.equals(tVar.d) && this.f.equals(tVar.f)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((this.c ? 1 : 0) + (((((this.a.hashCode() * 31) + this.e.hashCode()) * 31) + this.b.hashCode()) * 31)) * 31) + this.d.hashCode()) * 31) + this.f.hashCode();
    }
}

package com.here.a.a.a.a;

import com.here.a.a.a.s;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ak {
    public final String a;
    public final b b;
    public final boolean c;
    public final ad<String> d;
    public final ad<Integer> e;
    public final ad<Long> f;
    public final ad<Boolean> g;
    public final ad<Boolean> h;
    public final ad<Boolean> i;
    public final ad<String> j;
    private List<s> k;

    public ak(String str, b bVar, boolean z, List<s> list, String str2, Integer num, Long l, Boolean bool, Boolean bool2, Boolean bool3, String str3) {
        if (str == null || bVar == null) {
            throw new NullPointerException("Station name and address can't be null.");
        }
        this.a = str;
        this.b = bVar;
        this.c = z;
        if (list == null) {
            list = Collections.emptyList();
        }
        this.k = list;
        this.d = ad.b(str2);
        this.e = ad.b(num);
        this.f = ad.b(l);
        this.g = ad.b(bool);
        this.h = ad.b(bool2);
        this.i = ad.b(bool3);
        this.j = ad.b(str3);
    }

    public ak(ak akVar) {
        if (akVar == null) {
            throw new IllegalArgumentException("Station can't be null.");
        }
        this.a = akVar.a;
        this.b = akVar.b;
        this.c = akVar.c;
        this.k = akVar.k;
        this.d = akVar.d;
        this.e = akVar.e;
        this.f = akVar.f;
        this.g = akVar.g;
        this.h = akVar.h;
        this.i = akVar.i;
        this.j = akVar.j;
    }

    public List<s> a() {
        return Collections.unmodifiableList(this.k);
    }

    public static ak fromJSON(o oVar) {
        Boolean bool;
        Boolean bool2;
        Boolean bool3;
        String str;
        boolean z = true;
        String i = oVar.i("@name");
        b fromJSON = b.fromJSON(oVar);
        boolean z2 = oVar.b("@has_board") || oVar.j("@has_board").intValue() == 1;
        List a = a(oVar);
        String a2 = oVar.a("@id", null);
        Integer j = oVar.b("@distance") ? null : oVar.j("@distance");
        Long valueOf = oVar.b("@duration") ? null : Long.valueOf(s.b(oVar.i("@duration")));
        if (oVar.b("@blindguide")) {
            bool = null;
        } else {
            bool = Boolean.valueOf(oVar.j("@blindguide").intValue() == 1);
        }
        if (oVar.b("@elevator")) {
            bool2 = null;
        } else {
            bool2 = Boolean.valueOf(oVar.j("@elevator").intValue() == 1);
        }
        if (oVar.b("@escalator")) {
            bool3 = null;
        } else {
            if (oVar.j("@escalator").intValue() != 1) {
                z = false;
            }
            bool3 = Boolean.valueOf(z);
        }
        if (oVar.b("Info")) {
            str = null;
        } else {
            str = oVar.c("Info").i("$");
        }
        return new ak(i, fromJSON, z2, a, a2, j, valueOf, bool, bool2, bool3, str);
    }

    protected static List<s> a(o oVar) {
        if (!oVar.b("Lines")) {
            o c = oVar.c("Lines");
            if (!c.b("Line")) {
                p d = c.d("Line");
                if (d.a() > 0) {
                    List<s> arrayList = new ArrayList(d.a());
                    Iterator it = d.iterator();
                    while (it.hasNext()) {
                        arrayList.add(s.fromJSON((o) it.next()));
                    }
                    return arrayList;
                }
            }
        }
        return null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ak akVar = (ak) obj;
        if (this.a.equals(akVar.a) && this.b.equals(akVar.b) && this.d.equals(akVar.d)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((this.a.hashCode() * 31) + this.b.hashCode()) * 31) + this.d.hashCode();
    }
}

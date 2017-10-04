package com.here.a.a.a.a;

import com.here.a.a.a.s;
import java.util.ArrayList;
import java.util.List;

public class ap extends aj {
    public final ad<String> a;
    public final ad<Boolean> j;
    public final ad<Boolean> k;
    public final ad<Boolean> l;

    public ap(Boolean bool, Boolean bool2, Boolean bool3, String str, k kVar, e eVar, int i, long j, String str2, String str3, Integer num, n nVar, List<v> list) {
        super(kVar, eVar, i, j, str2, str3, num, nVar, list);
        this.a = ad.b(str);
        this.j = ad.b(bool3);
        this.k = ad.b(bool);
        this.l = ad.b(bool2);
    }

    public static ap a(o oVar, List<w> list) {
        n nVar;
        Boolean valueOf;
        Boolean valueOf2;
        boolean z;
        Boolean valueOf3;
        k fromJSON;
        e fromJSON2;
        int intValue;
        long b;
        String a;
        String a2;
        Integer j;
        o c = oVar.c("Walk");
        String a3 = c.a("@maneuvers_id", null);
        List list2 = null;
        if (!(a3 == null || list == null || list.isEmpty())) {
            for (w wVar : list) {
                if (wVar.a.c() && a3.equals(wVar.a.b())) {
                    List<v> a4 = wVar.a();
                    List arrayList = new ArrayList();
                    for (v vVar : a4) {
                        arrayList.addAll(vVar.e.a());
                    }
                    nVar = new n(arrayList);
                    if (c.b("@elevator")) {
                        valueOf = Boolean.valueOf(c.j("@elevator").intValue() != 1);
                    } else {
                        valueOf = null;
                    }
                    if (c.b("@escalator")) {
                        valueOf2 = Boolean.valueOf(c.j("@escalator").intValue() != 1);
                    } else {
                        valueOf2 = null;
                    }
                    z = c.b("@_guide") || c.j("@_guide").intValue() == 1;
                    valueOf3 = Boolean.valueOf(z);
                    fromJSON = k.fromJSON(oVar.c("Dep"));
                    fromJSON2 = e.fromJSON(oVar.c("Arr"));
                    intValue = c.j("@distance").intValue();
                    b = s.b(c.i("@duration"));
                    a = oVar.a("@id", null);
                    a2 = oVar.a("@quality", null);
                    j = oVar.b("@uncertainity") ? null : oVar.j("@uncertainity");
                    if (!oVar.b("Graph")) {
                        nVar = n.a(oVar.i("Graph"));
                    }
                    return new ap(valueOf, valueOf2, valueOf3, a3, fromJSON, fromJSON2, intValue, b, a, a2, j, nVar, list2);
                }
            }
        }
        nVar = null;
        if (c.b("@elevator")) {
            if (c.j("@elevator").intValue() != 1) {
            }
            valueOf = Boolean.valueOf(c.j("@elevator").intValue() != 1);
        } else {
            valueOf = null;
        }
        if (c.b("@escalator")) {
            if (c.j("@escalator").intValue() != 1) {
            }
            valueOf2 = Boolean.valueOf(c.j("@escalator").intValue() != 1);
        } else {
            valueOf2 = null;
        }
        if (!c.b("@_guide")) {
        }
        valueOf3 = Boolean.valueOf(z);
        fromJSON = k.fromJSON(oVar.c("Dep"));
        fromJSON2 = e.fromJSON(oVar.c("Arr"));
        intValue = c.j("@distance").intValue();
        b = s.b(c.i("@duration"));
        a = oVar.a("@id", null);
        a2 = oVar.a("@quality", null);
        if (oVar.b("@uncertainity")) {
        }
        if (oVar.b("Graph")) {
            nVar = n.a(oVar.i("Graph"));
        }
        return new ap(valueOf, valueOf2, valueOf3, a3, fromJSON, fromJSON2, intValue, b, a, a2, j, nVar, list2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        ap apVar = (ap) obj;
        if (this.j.equals(apVar.j) && this.k.equals(apVar.k) && this.l.equals(apVar.l)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((super.hashCode() * 31) + this.j.hashCode()) * 31) + this.k.hashCode()) * 31) + this.l.hashCode();
    }
}

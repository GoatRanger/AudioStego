package com.here.a.a.a.a;

import com.here.a.a.a.s;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class q extends aj {
    public final boolean a;
    private List<r> j;

    public q(List<r> list, boolean z, k kVar, e eVar, int i, long j, String str, String str2, Integer num, n nVar) {
        super(kVar, eVar, i, j, str, str2, num, nVar, null);
        if (list == null) {
            list = Collections.emptyList();
        }
        this.j = list;
        this.a = z;
    }

    public List<r> a() {
        return Collections.unmodifiableList(this.j);
    }

    public static q fromJSON(o oVar) {
        boolean z = true;
        n nVar = null;
        o c = oVar.c("Journey");
        k fromJSON = k.fromJSON(oVar.c("Dep"));
        e fromJSON2 = e.fromJSON(oVar.c("Arr"));
        List a = a(c);
        if (!(c.b("@intermediate") || c.j("@intermediate").intValue() == 1)) {
            z = false;
        }
        int b = b(fromJSON, fromJSON2);
        long a2 = a(fromJSON, fromJSON2);
        String a3 = oVar.a("@id", null);
        String a4 = oVar.a("@quality", null);
        Integer j = oVar.b("@uncertainity") ? null : oVar.j("@uncertainity");
        if (!oVar.b("Graph")) {
            nVar = n.a(oVar.i("Graph"));
        }
        return new q(a, z, fromJSON, fromJSON2, b, a2, a3, a4, j, nVar);
    }

    private static List<r> a(o oVar) {
        if (!oVar.b("Stop")) {
            p d = oVar.d("Stop");
            if (d.a() > 0) {
                List<r> arrayList = new ArrayList(d.a());
                Iterator it = d.iterator();
                while (it.hasNext()) {
                    arrayList.add(r.fromJSON((o) it.next()));
                }
                return arrayList;
            }
        }
        return null;
    }

    private static long a(k kVar, e eVar) {
        if (kVar.g.c() && eVar.g.c()) {
            return (((Date) eVar.g.b()).getTime() - ((Date) kVar.g.b()).getTime()) / 1000;
        }
        throw new IllegalArgumentException("Departure and Arrival should have times set.");
    }

    private static int b(k kVar, e eVar) {
        return new Double(s.a(kVar.a(), eVar.a())).intValue();
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
        q qVar = (q) obj;
        if (this.a == qVar.a && this.j.equals(qVar.j)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((this.a ? 1 : 0) + (super.hashCode() * 31)) * 31) + this.j.hashCode();
    }
}

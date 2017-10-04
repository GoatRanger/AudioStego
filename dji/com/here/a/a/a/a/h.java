package com.here.a.a.a.a;

import com.here.a.a.a.i;
import com.here.a.a.a.s;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class h {
    public final Date a;
    public final int b;
    public final int c;
    public final int d;
    private final List<f> e;

    public h(Date date, int i, int i2, int i3, List<f> list) {
        if (date == null || list == null) {
            throw new IllegalArgumentException("Ref Time and Cities can't be null.");
        }
        this.a = date;
        this.b = i;
        this.c = i2;
        this.d = i3;
        this.e = list;
    }

    public h(h hVar) {
        if (hVar == null) {
            throw new IllegalArgumentException("Response can't be null.");
        }
        this.a = hVar.a;
        this.b = hVar.b;
        this.c = hVar.c;
        this.d = hVar.d;
        this.e = hVar.e;
    }

    public static h b(o oVar, i iVar) {
        o c = oVar.c("Coverage");
        o c2 = c.c("CityCount");
        p d = c.c("Cities").d("City");
        List arrayList = new ArrayList(d.a());
        Iterator it = d.iterator();
        while (it.hasNext()) {
            arrayList.add(f.fromJSON((o) it.next()));
        }
        return new h(s.a(c.i("@ref_time")), c2.j("@RT").intValue(), c2.j("@SR").intValue(), c2.j("@TT").intValue(), arrayList);
    }

    public List<f> b() {
        return Collections.unmodifiableList(this.e);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        h hVar = (h) obj;
        if (this.a.equals(hVar.a) && this.b == hVar.b && this.c == hVar.c && this.d == hVar.d && this.e.equals(hVar.e)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((this.a.hashCode() * 31) + this.b) * 31) + this.c) * 31) + this.d) * 31) + this.e.hashCode();
    }
}

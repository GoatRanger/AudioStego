package com.nokia.maps.a;

import com.here.a.a.a.a.c;
import com.here.a.a.a.a.s;
import com.here.a.a.a.a.t;
import com.here.android.mpa.urbanmobility.Alert;
import com.here.android.mpa.urbanmobility.Line;
import com.here.android.mpa.urbanmobility.Operator;
import com.nokia.maps.am;
import com.nokia.maps.ce;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class f {
    private static am<Alert, f> j;
    private String a;
    private Operator b;
    private Collection<Line> c;
    private String d;
    private Date e;
    private Date f;
    private String g;
    private String h;
    private String i;

    protected f(c cVar) {
        this.a = cVar.a;
        this.b = ai.a(new ai(cVar.b));
        List<s> a = cVar.a();
        if (a.isEmpty()) {
            this.c = Collections.emptyList();
        } else {
            this.c = new ArrayList(a.size());
            for (s xVar : a) {
                this.c.add(x.a(new x(xVar)));
            }
        }
        this.d = cVar.d;
        this.e = (Date) cVar.g.c(null);
        this.f = (Date) cVar.h.c(null);
        this.g = cVar.f.c() ? ((t) cVar.f.b()).a : "";
        this.h = cVar.i.c() ? ((t) cVar.i.b()).a : "";
        this.i = (String) cVar.j.c("");
    }

    public String a() {
        return this.a;
    }

    public Operator b() {
        return this.b;
    }

    public Collection<Line> c() {
        return Collections.unmodifiableCollection(this.c);
    }

    public String d() {
        return this.d;
    }

    public Date e() {
        return this.e != null ? new Date(this.e.getTime()) : null;
    }

    public Date f() {
        return this.f != null ? new Date(this.f.getTime()) : null;
    }

    public String g() {
        return this.g;
    }

    public String h() {
        return this.h;
    }

    public String i() {
        return this.i;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r5) {
        /*
        r4 = this;
        r0 = 1;
        r1 = 0;
        if (r4 != r5) goto L_0x0005;
    L_0x0004:
        return r0;
    L_0x0005:
        if (r5 == 0) goto L_0x0011;
    L_0x0007:
        r2 = r4.getClass();
        r3 = r5.getClass();
        if (r2 == r3) goto L_0x0013;
    L_0x0011:
        r0 = r1;
        goto L_0x0004;
    L_0x0013:
        r5 = (com.nokia.maps.a.f) r5;
        r2 = r4.a;
        r3 = r5.a;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x004b;
    L_0x001f:
        r2 = r4.b;
        r3 = r5.b;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x004b;
    L_0x0029:
        r2 = r4.c;
        r3 = r5.c;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x004b;
    L_0x0033:
        r2 = r4.d;
        r3 = r5.d;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x004b;
    L_0x003d:
        r2 = r4.e;
        if (r2 == 0) goto L_0x004d;
    L_0x0041:
        r2 = r4.e;
        r3 = r5.e;
        r2 = r2.equals(r3);
        if (r2 != 0) goto L_0x0051;
    L_0x004b:
        r0 = r1;
        goto L_0x0004;
    L_0x004d:
        r2 = r5.e;
        if (r2 != 0) goto L_0x004b;
    L_0x0051:
        r2 = r4.f;
        if (r2 == 0) goto L_0x007e;
    L_0x0055:
        r2 = r4.f;
        r3 = r5.f;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x004b;
    L_0x005f:
        r2 = r4.g;
        r3 = r5.g;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x004b;
    L_0x0069:
        r2 = r4.h;
        r3 = r5.h;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x004b;
    L_0x0073:
        r2 = r4.i;
        r3 = r5.i;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x004b;
    L_0x007d:
        goto L_0x0004;
    L_0x007e:
        r2 = r5.f;
        if (r2 != 0) goto L_0x004b;
    L_0x0082:
        goto L_0x005f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nokia.maps.a.f.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.e != null ? this.e.hashCode() : 0) + (((((((this.a.hashCode() * 31) + this.b.hashCode()) * 31) + this.c.hashCode()) * 31) + this.d.hashCode()) * 31)) * 31;
        if (this.f != null) {
            i = this.f.hashCode();
        }
        return ((((((hashCode + i) * 31) + this.g.hashCode()) * 31) + this.h.hashCode()) * 31) + this.i.hashCode();
    }

    public static void a(am<Alert, f> amVar) {
        j = amVar;
    }

    static Alert a(f fVar) {
        if (fVar != null) {
            return (Alert) j.a(fVar);
        }
        return null;
    }

    static {
        ce.a(Alert.class);
    }
}

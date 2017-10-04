package com.nokia.maps.a;

import com.here.a.a.a.a.af;
import com.here.a.a.a.a.r;
import com.here.android.mpa.urbanmobility.IntermediateStop;
import com.here.android.mpa.urbanmobility.RealTimeInfo;
import com.here.android.mpa.urbanmobility.Station;
import com.nokia.maps.am;
import com.nokia.maps.ce;
import com.nokia.maps.k;
import java.util.Date;

public class v {
    private static am<IntermediateStop, v> e;
    private static k<IntermediateStop, v> f;
    private Station a;
    private Date b;
    private Date c;
    private RealTimeInfo d;

    protected v(r rVar) {
        RealTimeInfo a;
        this.a = at.a(new at(rVar.a));
        this.b = (Date) rVar.c.c(null);
        this.c = (Date) rVar.b.c(null);
        if (rVar.d.c()) {
            a = ak.a(new ak((af) rVar.d.b()));
        } else {
            a = null;
        }
        this.d = a;
    }

    public Station a() {
        return this.a;
    }

    public Date b() {
        return this.b != null ? new Date(this.b.getTime()) : null;
    }

    public Date c() {
        return this.c != null ? new Date(this.c.getTime()) : null;
    }

    public RealTimeInfo d() {
        return this.d;
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
        r5 = (com.nokia.maps.a.v) r5;
        r2 = r4.a;
        r3 = r5.a;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x002d;
    L_0x001f:
        r2 = r4.b;
        if (r2 == 0) goto L_0x002f;
    L_0x0023:
        r2 = r4.b;
        r3 = r5.b;
        r2 = r2.equals(r3);
        if (r2 != 0) goto L_0x0033;
    L_0x002d:
        r0 = r1;
        goto L_0x0004;
    L_0x002f:
        r2 = r5.b;
        if (r2 != 0) goto L_0x002d;
    L_0x0033:
        r2 = r4.c;
        if (r2 == 0) goto L_0x0050;
    L_0x0037:
        r2 = r4.c;
        r3 = r5.c;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x002d;
    L_0x0041:
        r2 = r4.d;
        if (r2 == 0) goto L_0x0055;
    L_0x0045:
        r2 = r4.d;
        r3 = r5.d;
        r2 = r2.equals(r3);
        if (r2 != 0) goto L_0x0004;
    L_0x004f:
        goto L_0x002d;
    L_0x0050:
        r2 = r5.c;
        if (r2 != 0) goto L_0x002d;
    L_0x0054:
        goto L_0x0041;
    L_0x0055:
        r2 = r5.d;
        if (r2 != 0) goto L_0x002d;
    L_0x0059:
        goto L_0x0004;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nokia.maps.a.v.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        int hashCode2 = this.a.hashCode() * 31;
        if (this.b != null) {
            hashCode = this.b.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode2 = (hashCode + hashCode2) * 31;
        if (this.c != null) {
            hashCode = this.c.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode = (hashCode + hashCode2) * 31;
        if (this.d != null) {
            i = this.d.hashCode();
        }
        return hashCode + i;
    }

    public static void a(k<IntermediateStop, v> kVar, am<IntermediateStop, v> amVar) {
        f = kVar;
        e = amVar;
    }

    static IntermediateStop a(v vVar) {
        if (vVar != null) {
            return (IntermediateStop) e.a(vVar);
        }
        return null;
    }

    static {
        ce.a(IntermediateStop.class);
    }
}

package com.nokia.maps.a;

import android.graphics.Color;
import com.here.a.a.a.a.s;
import com.here.android.mpa.urbanmobility.LineStyle;
import com.here.android.mpa.urbanmobility.LineStyle.LineNameIconShape;
import com.nokia.maps.am;
import com.nokia.maps.ce;

public class y {
    private static am<LineStyle, y> e;
    private String a;
    private String b;
    private String c;
    private LineNameIconShape d;

    protected y(s sVar) {
        this.a = (String) sVar.h.c(null);
        this.b = (String) sVar.i.c(null);
        this.c = (String) sVar.j.c(null);
        String toLowerCase = ((String) sVar.k.c("")).toLowerCase();
        if ("rectangle".equals(toLowerCase)) {
            this.d = LineNameIconShape.RECTANGLE;
        } else if ("roundedrect".equals(toLowerCase)) {
            this.d = LineNameIconShape.ROUNDED_RECT;
        } else {
            this.d = LineNameIconShape.UNKNOWN;
        }
    }

    public int a() {
        return this.a != null ? Color.parseColor(this.a) : -16777216;
    }

    public int b() {
        return this.b != null ? Color.parseColor(this.b) : -16777216;
    }

    public int c() {
        return this.c != null ? Color.parseColor(this.c) : 0;
    }

    public LineNameIconShape d() {
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
        r5 = (com.nokia.maps.a.y) r5;
        r2 = r4.a;
        if (r2 == 0) goto L_0x0025;
    L_0x0019:
        r2 = r4.a;
        r3 = r5.a;
        r2 = r2.equals(r3);
        if (r2 != 0) goto L_0x0029;
    L_0x0023:
        r0 = r1;
        goto L_0x0004;
    L_0x0025:
        r2 = r5.a;
        if (r2 != 0) goto L_0x0023;
    L_0x0029:
        r2 = r4.b;
        if (r2 == 0) goto L_0x004c;
    L_0x002d:
        r2 = r4.b;
        r3 = r5.b;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0023;
    L_0x0037:
        r2 = r4.c;
        if (r2 == 0) goto L_0x0051;
    L_0x003b:
        r2 = r4.c;
        r3 = r5.c;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0023;
    L_0x0045:
        r2 = r4.d;
        r3 = r5.d;
        if (r2 != r3) goto L_0x0023;
    L_0x004b:
        goto L_0x0004;
    L_0x004c:
        r2 = r5.b;
        if (r2 != 0) goto L_0x0023;
    L_0x0050:
        goto L_0x0037;
    L_0x0051:
        r2 = r5.c;
        if (r2 != 0) goto L_0x0023;
    L_0x0055:
        goto L_0x0045;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nokia.maps.a.y.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        if (this.a != null) {
            hashCode = this.a.hashCode();
        } else {
            hashCode = 0;
        }
        int i2 = hashCode * 31;
        if (this.b != null) {
            hashCode = this.b.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode = (hashCode + i2) * 31;
        if (this.c != null) {
            i = this.c.hashCode();
        }
        return ((hashCode + i) * 31) + this.d.hashCode();
    }

    public static void a(am<LineStyle, y> amVar) {
        e = amVar;
    }

    static LineStyle a(y yVar) {
        if (yVar != null) {
            return (LineStyle) e.a(yVar);
        }
        return null;
    }

    static {
        ce.a(LineStyle.class);
    }
}

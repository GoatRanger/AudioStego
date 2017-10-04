package com.nokia.maps.a;

import com.here.a.a.a.a.a;
import com.here.a.a.a.a.af;
import com.here.a.a.a.a.ak;
import com.here.a.a.a.a.b;
import com.here.a.a.a.a.k;
import com.here.a.a.a.a.u;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.urbanmobility.AccessPoint;
import com.here.android.mpa.urbanmobility.Address;
import com.here.android.mpa.urbanmobility.Location;
import com.here.android.mpa.urbanmobility.Location.LocationType;
import com.here.android.mpa.urbanmobility.RealTimeInfo;
import com.here.android.mpa.urbanmobility.Station;
import com.nokia.maps.am;
import com.nokia.maps.ce;
import java.util.Date;

public class aa {
    private static am<Location, aa> j;
    private LocationType a;
    private Address b;
    private Station c;
    private GeoCoordinate d;
    private String e;
    private AccessPoint f;
    private Date g;
    private boolean h;
    private RealTimeInfo i;

    protected aa(u uVar) {
        Address a;
        Station a2;
        RealTimeInfo realTimeInfo = null;
        this.a = uVar instanceof k ? LocationType.DEPARTURE : LocationType.ARRIVAL;
        if (uVar.d.c()) {
            a = e.a(new e((b) uVar.d.b()));
        } else {
            a = null;
        }
        this.b = a;
        if (uVar.e.c()) {
            a2 = at.a(new at((ak) uVar.e.b()));
        } else {
            a2 = null;
        }
        this.c = a2;
        if (this.b == null && this.c == null) {
            throw new IllegalArgumentException("Both Address and Station can't be null.");
        }
        AccessPoint a3;
        this.d = this.b != null ? this.b.getCoordinate() : this.c.getCoordinate();
        this.e = (String) uVar.f.c("");
        if (uVar.i.c()) {
            a3 = d.a(new d((a) uVar.i.b()));
        } else {
            a3 = null;
        }
        this.f = a3;
        this.g = (Date) uVar.g.c(null);
        this.h = uVar.h.c();
        if (this.h) {
            realTimeInfo = ak.a(new ak((af) uVar.h.b()));
        }
        this.i = realTimeInfo;
    }

    public LocationType a() {
        return this.a;
    }

    public Address b() {
        return this.b;
    }

    public Station c() {
        return this.c;
    }

    public GeoCoordinate d() {
        return this.d;
    }

    public String e() {
        return this.e;
    }

    public AccessPoint f() {
        return this.f;
    }

    public Date g() {
        return this.g != null ? new Date(this.g.getTime()) : null;
    }

    public boolean h() {
        return this.h;
    }

    public RealTimeInfo i() {
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
        r5 = (com.nokia.maps.a.aa) r5;
        r2 = r4.h;
        r3 = r5.h;
        if (r2 != r3) goto L_0x0029;
    L_0x001b:
        r2 = r4.b;
        if (r2 == 0) goto L_0x002b;
    L_0x001f:
        r2 = r4.b;
        r3 = r5.b;
        r2 = r2.equals(r3);
        if (r2 != 0) goto L_0x002f;
    L_0x0029:
        r0 = r1;
        goto L_0x0004;
    L_0x002b:
        r2 = r5.b;
        if (r2 != 0) goto L_0x0029;
    L_0x002f:
        r2 = r4.c;
        if (r2 == 0) goto L_0x0068;
    L_0x0033:
        r2 = r4.c;
        r3 = r5.c;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0029;
    L_0x003d:
        r2 = r4.e;
        if (r2 == 0) goto L_0x006d;
    L_0x0041:
        r2 = r4.e;
        r3 = r5.e;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0029;
    L_0x004b:
        r2 = r4.f;
        if (r2 == 0) goto L_0x0072;
    L_0x004f:
        r2 = r4.f;
        r3 = r5.f;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0029;
    L_0x0059:
        r2 = r4.i;
        if (r2 == 0) goto L_0x0077;
    L_0x005d:
        r2 = r4.i;
        r3 = r5.i;
        r2 = r2.equals(r3);
        if (r2 != 0) goto L_0x0004;
    L_0x0067:
        goto L_0x0029;
    L_0x0068:
        r2 = r5.c;
        if (r2 != 0) goto L_0x0029;
    L_0x006c:
        goto L_0x003d;
    L_0x006d:
        r2 = r5.e;
        if (r2 != 0) goto L_0x0029;
    L_0x0071:
        goto L_0x004b;
    L_0x0072:
        r2 = r5.f;
        if (r2 != 0) goto L_0x0029;
    L_0x0076:
        goto L_0x0059;
    L_0x0077:
        r2 = r5.i;
        if (r2 != 0) goto L_0x0029;
    L_0x007b:
        goto L_0x0004;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nokia.maps.a.aa.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        int hashCode2 = (this.b != null ? this.b.hashCode() : 0) * 31;
        if (this.c != null) {
            hashCode = this.c.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode2 = (hashCode + hashCode2) * 31;
        if (this.e != null) {
            hashCode = this.e.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode2 = (hashCode + hashCode2) * 31;
        if (this.f != null) {
            hashCode = this.f.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode2 = (((hashCode + hashCode2) * 31) + this.g.hashCode()) * 31;
        if (this.h) {
            hashCode = 1;
        } else {
            hashCode = 0;
        }
        hashCode = (hashCode + hashCode2) * 31;
        if (this.i != null) {
            i = this.i.hashCode();
        }
        return hashCode + i;
    }

    public static void a(am<Location, aa> amVar) {
        j = amVar;
    }

    static Location a(aa aaVar) {
        if (aaVar != null) {
            return (Location) j.a(aaVar);
        }
        return null;
    }

    static {
        ce.a(Location.class);
    }
}

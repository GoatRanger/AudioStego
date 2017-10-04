package com.nokia.maps.a;

import com.here.a.a.a.a.af;
import com.here.a.a.a.a.af.a;
import com.here.a.a.a.s;
import com.here.android.mpa.urbanmobility.RealTimeInfo;
import com.here.android.mpa.urbanmobility.RealTimeInfo$RealTimeStatus;
import com.nokia.maps.am;
import com.nokia.maps.ce;
import java.util.Date;

public class ak {
    private static am<RealTimeInfo, ak> g;
    private String a;
    private RealTimeInfo$RealTimeStatus b;
    private Date c;
    private Date d;
    private String e;
    private String f;

    protected ak(af afVar) {
        this.d = (Date) afVar.d.c(null);
        this.c = (Date) afVar.c.c(null);
        this.e = afVar.d.c() ? s.a((Date) afVar.d.b()) : "";
        this.f = afVar.c.c() ? s.a((Date) afVar.c.b()) : "";
        this.a = (String) afVar.f.c("");
        a aVar = (a) afVar.g.c(a.OK);
        RealTimeInfo$RealTimeStatus realTimeInfo$RealTimeStatus = a.OK == aVar ? RealTimeInfo$RealTimeStatus.OK : a.CANCELLED == aVar ? RealTimeInfo$RealTimeStatus.CANCELLED : a.ADDITIONAL == aVar ? RealTimeInfo$RealTimeStatus.ADDITIONAL : a.REDIRECTED == aVar ? RealTimeInfo$RealTimeStatus.REDIRECTED : a.REPLACED == aVar ? RealTimeInfo$RealTimeStatus.REPLACED : RealTimeInfo$RealTimeStatus.OK;
        this.b = realTimeInfo$RealTimeStatus;
    }

    public String a() {
        return this.a;
    }

    public RealTimeInfo$RealTimeStatus b() {
        return this.b;
    }

    public String c() {
        return this.f;
    }

    public Date d() {
        return this.c != null ? new Date(this.c.getTime()) : null;
    }

    public String e() {
        return this.e;
    }

    public Date f() {
        return this.d != null ? new Date(this.d.getTime()) : null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ak akVar = (ak) obj;
        if (this.a.equals(akVar.a) && this.b == akVar.b && (this.c == null ? akVar.c != null : !this.c.equals(akVar.c))) {
            if (this.d != null) {
                if (this.d.equals(akVar.d)) {
                    return true;
                }
            } else if (akVar.d == null) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        int hashCode2 = ((this.a.hashCode() * 31) + this.b.hashCode()) * 31;
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

    public static void a(am<RealTimeInfo, ak> amVar) {
        g = amVar;
    }

    static RealTimeInfo a(ak akVar) {
        if (akVar != null) {
            return (RealTimeInfo) g.a(akVar);
        }
        return null;
    }

    static {
        ce.a(RealTimeInfo.class);
    }
}

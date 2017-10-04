package com.nokia.maps.a;

import com.here.a.a.a.s;
import com.here.android.mpa.urbanmobility.Line;
import com.here.android.mpa.urbanmobility.Operator;
import com.here.android.mpa.urbanmobility.RealTimeInfo;
import java.util.Date;

public abstract class a {
    protected Line a;
    protected String b;
    protected Operator c;
    protected Date d;
    protected RealTimeInfo e;
    protected boolean f;
    protected boolean g;
    protected boolean h;

    public Line a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public Operator c() {
        return this.c;
    }

    public Date d() {
        return this.d != null ? new Date(this.d.getTime()) : null;
    }

    public boolean e() {
        return this.f;
    }

    public RealTimeInfo f() {
        return this.e;
    }

    public boolean g() {
        return this.g;
    }

    public boolean h() {
        return this.h;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        a aVar = (a) obj;
        if (this.f == aVar.f && this.g == aVar.g && this.h == aVar.h) {
            if (this.a != null) {
                if (this.a.equals(aVar.a)) {
                    return true;
                }
            } else if (aVar.a == null && this.b.equals(aVar.b)) {
                if (this.c != null) {
                    if (this.c.equals(aVar.c)) {
                        return true;
                    }
                } else if (aVar.c == null) {
                    if (this.d != null) {
                        if (this.d.equals(aVar.d)) {
                            return true;
                        }
                    } else if (aVar.d == null) {
                        if (this.e != null) {
                            if (this.e.equals(aVar.e)) {
                                return true;
                            }
                        } else if (aVar.e == null) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public int hashCode() {
        int hashCode;
        int i = 1;
        int hashCode2 = (((this.a != null ? this.a.hashCode() : 0) * 31) + this.b.hashCode()) * 31;
        if (this.c != null) {
            hashCode = this.c.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode2 = (hashCode + hashCode2) * 31;
        if (this.d != null) {
            hashCode = this.d.hashCode();
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
        if (this.f) {
            hashCode = 1;
        } else {
            hashCode = 0;
        }
        hashCode2 = (hashCode + hashCode2) * 31;
        if (this.g) {
            hashCode = 1;
        } else {
            hashCode = 0;
        }
        hashCode = (hashCode + hashCode2) * 31;
        if (!this.h) {
            i = 0;
        }
        return hashCode + i;
    }

    public String i() {
        return this.d != null ? s.a(this.d) : "";
    }
}

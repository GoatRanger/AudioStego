package com.here.android.mpa.urbanmobility;

import com.nokia.maps.a.a;
import com.nokia.maps.annotation.HybridPlus;
import java.util.Date;

@HybridPlus
public abstract class AbstractDeparture<T extends a> {
    T a;

    AbstractDeparture(T t) {
        if (t == null) {
            throw new IllegalArgumentException("Impl can't be null.");
        }
        this.a = t;
    }

    public Line getLine() {
        return this.a.a();
    }

    public Date getTime() {
        return this.a.d();
    }

    @Deprecated
    public String getTimeAsString() {
        return this.a.i();
    }

    public boolean hasRealTimeInfo() {
        return this.a.e();
    }

    public RealTimeInfo getRealTimeInfo() {
        return this.a.f();
    }

    public String getDirection() {
        return this.a.b();
    }

    public Operator getOperator() {
        return this.a.c();
    }

    public boolean isBikeAllowed() {
        return this.a.g();
    }

    public boolean isBarrierFree() {
        return this.a.h();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.a.equals(((AbstractDeparture) obj).a);
    }

    public int hashCode() {
        return this.a.hashCode() + 31;
    }
}

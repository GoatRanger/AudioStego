package com.here.android.mpa.urbanmobility;

import com.nokia.maps.a.ak;
import com.nokia.maps.annotation.HybridPlus;
import java.util.Date;

@HybridPlus
public final class RealTimeInfo {
    private ak a;

    private RealTimeInfo(ak akVar) {
        if (akVar == null) {
            throw new IllegalArgumentException("Impl can't be null.");
        }
        this.a = akVar;
    }

    public Date getDepartureTime() {
        return this.a.f();
    }

    @Deprecated
    public String getDepartureTimeAsString() {
        return this.a.e();
    }

    public Date getArrivalTime() {
        return this.a.d();
    }

    @Deprecated
    public String getArrivalTimeAsString() {
        return this.a.c();
    }

    public String getPlatform() {
        return this.a.a();
    }

    public RealTimeStatus getStatus() {
        return this.a.b();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.a.equals(((RealTimeInfo) obj).a);
    }

    public int hashCode() {
        return this.a.hashCode() + 31;
    }

    static {
        ak.a(new 1());
    }
}

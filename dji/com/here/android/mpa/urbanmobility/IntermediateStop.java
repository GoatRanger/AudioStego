package com.here.android.mpa.urbanmobility;

import com.nokia.maps.a.v;
import com.nokia.maps.am;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.k;
import java.util.Date;

@HybridPlus
public final class IntermediateStop {
    private v a;

    private IntermediateStop(v vVar) {
        if (vVar == null) {
            throw new IllegalArgumentException("Impl can't be null.");
        }
        this.a = vVar;
    }

    public Station getStation() {
        return this.a.a();
    }

    public Date getDepartureTime() {
        return this.a.b();
    }

    public Date getArrivalTime() {
        return this.a.c();
    }

    public RealTimeInfo getRealTimeInfo() {
        return this.a.d();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.a.equals(((IntermediateStop) obj).a);
    }

    public int hashCode() {
        return this.a.hashCode() + 31;
    }

    static {
        v.a(new k<IntermediateStop, v>() {
            public v a(IntermediateStop intermediateStop) {
                return intermediateStop != null ? intermediateStop.a : null;
            }
        }, new am<IntermediateStop, v>() {
            public IntermediateStop a(v vVar) {
                return new IntermediateStop(vVar);
            }
        });
    }
}

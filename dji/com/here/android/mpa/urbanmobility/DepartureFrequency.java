package com.here.android.mpa.urbanmobility;

import com.nokia.maps.a.q;
import com.nokia.maps.am;
import com.nokia.maps.annotation.HybridPlus;

@HybridPlus
public final class DepartureFrequency {
    public static final int UNKNOWN_INTERVAL = -1;
    private q a;

    private DepartureFrequency(q qVar) {
        if (qVar == null) {
            throw new IllegalArgumentException("Impl can't be null.");
        }
        this.a = qVar;
    }

    public int getMaxScheduledInterval() {
        return this.a.a();
    }

    public int getMinScheduledInterval() {
        return this.a.b();
    }

    public int getMaxRealTimeInterval() {
        return this.a.c();
    }

    public int getMinRealTimeInterval() {
        return this.a.d();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.a.equals(((DepartureFrequency) obj).a);
    }

    public int hashCode() {
        return this.a.hashCode() + 31;
    }

    static {
        q.a(new am<DepartureFrequency, q>() {
            public DepartureFrequency a(q qVar) {
                return new DepartureFrequency(qVar);
            }
        });
    }
}

package com.here.android.mpa.urbanmobility;

import com.nokia.maps.a.af;
import com.nokia.maps.am;
import com.nokia.maps.annotation.HybridPlus;
import java.util.Collection;

@HybridPlus
public final class MultiBoardResult {
    private af a;

    private MultiBoardResult(af afVar) {
        if (afVar == null) {
            throw new IllegalArgumentException("Impl can't be null.");
        }
        this.a = afVar;
    }

    public Collection<StationWithDepartureBoard> getStations() {
        return this.a.a();
    }

    public Collection<Line> getLines() {
        return this.a.b();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.a.equals(((MultiBoardResult) obj).a);
    }

    public int hashCode() {
        return this.a.hashCode() + 31;
    }

    static {
        af.a(new am<MultiBoardResult, af>() {
            public MultiBoardResult a(af afVar) {
                return new MultiBoardResult(afVar);
            }
        });
    }
}

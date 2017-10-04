package com.here.android.mpa.urbanmobility;

import com.nokia.maps.a.aw;
import com.nokia.maps.am;
import com.nokia.maps.annotation.HybridPlus;

@HybridPlus
public final class StationWithDepartureBoard extends Station {
    private aw a;

    private StationWithDepartureBoard(aw awVar) {
        super(awVar);
        this.a = awVar;
    }

    public DepartureBoard getDepartureBoard() {
        return this.a.n();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.a.equals(((StationWithDepartureBoard) obj).a);
    }

    public int hashCode() {
        return this.a.hashCode() + 31;
    }

    static {
        aw.b(new am<StationWithDepartureBoard, aw>() {
            public StationWithDepartureBoard a(aw awVar) {
                return new StationWithDepartureBoard(awVar);
            }
        });
    }
}

package com.nokia.maps.a;

import com.here.android.mpa.urbanmobility.DepartureBoard;
import com.here.android.mpa.urbanmobility.StationWithDepartureBoard;
import com.nokia.maps.am;
import com.nokia.maps.ce;

public class aw extends at {
    private static am<StationWithDepartureBoard, aw> b;
    private DepartureBoard a;

    protected aw(com.here.a.a.a.a.am amVar) {
        super(amVar);
        this.a = o.a(new o(amVar.b()));
    }

    public DepartureBoard n() {
        return this.a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        aw awVar = (aw) obj;
        if (super.equals(awVar) && this.a.equals(awVar.a)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (super.hashCode() * 31) + this.a.hashCode();
    }

    public static void b(am<StationWithDepartureBoard, aw> amVar) {
        b = amVar;
    }

    static StationWithDepartureBoard a(aw awVar) {
        if (awVar != null) {
            return (StationWithDepartureBoard) b.a(awVar);
        }
        return null;
    }

    static {
        ce.a(StationWithDepartureBoard.class);
    }
}

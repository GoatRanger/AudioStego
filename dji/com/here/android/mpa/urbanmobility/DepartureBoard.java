package com.here.android.mpa.urbanmobility;

import com.nokia.maps.a.o;
import com.nokia.maps.am;
import com.nokia.maps.annotation.HybridPlus;
import java.util.Collection;
import java.util.List;

@HybridPlus
public final class DepartureBoard {
    private o a;

    private DepartureBoard(o oVar) {
        if (oVar == null) {
            throw new IllegalArgumentException("Impl can't be null.");
        }
        this.a = oVar;
    }

    public List<Departure> getDepartures() {
        return this.a.a();
    }

    public Collection<Line> getLines() {
        return this.a.b();
    }

    public Collection<Operator> getOperators() {
        return this.a.c();
    }

    public Collection<Link> getOperatorDisclaimers() {
        return this.a.d();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.a.equals(((DepartureBoard) obj).a);
    }

    public int hashCode() {
        return this.a.hashCode() + 31;
    }

    static {
        o.a(new am<DepartureBoard, o>() {
            public DepartureBoard a(o oVar) {
                return new DepartureBoard(oVar);
            }
        });
    }
}

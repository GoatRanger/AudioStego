package com.here.android.mpa.urbanmobility;

import com.nokia.maps.a.s;
import com.nokia.maps.am;
import com.nokia.maps.annotation.HybridPlus;
import java.util.Collection;

@HybridPlus
public class ExploredCoverage {
    private s a;

    private ExploredCoverage(s sVar) {
        if (sVar == null) {
            throw new IllegalArgumentException("Impl can't be null.");
        }
        this.a = sVar;
    }

    public int getLinesCount() {
        return this.a.a();
    }

    public int getRadius() {
        return this.a.b();
    }

    public int getStopsCount() {
        return this.a.c();
    }

    public Collection<Station> getStations() {
        return this.a.d();
    }

    public Collection<Line> getLines() {
        return this.a.e();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.a.equals(((ExploredCoverage) obj).a);
    }

    public int hashCode() {
        return this.a.hashCode() + 31;
    }

    static {
        s.a(new am<ExploredCoverage, s>() {
            public ExploredCoverage a(s sVar) {
                return new ExploredCoverage(sVar);
            }
        });
    }
}

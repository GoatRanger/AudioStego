package com.here.android.mpa.urbanmobility;

import com.nokia.maps.a.ah;
import com.nokia.maps.am;
import com.nokia.maps.annotation.HybridPlus;

@HybridPlus
public class NearbyCoverageResult {
    private ah a;

    private NearbyCoverageResult(ah ahVar) {
        if (ahVar == null) {
            throw new IllegalArgumentException("Impl can't be null.");
        }
        this.a = ahVar;
    }

    public String getGeoRef() {
        return this.a.a();
    }

    public boolean isCovered() {
        return this.a.b();
    }

    public int getLinesCount() {
        return this.a.c();
    }

    public int getRadius() {
        return this.a.d();
    }

    public int getStopsCount() {
        return this.a.e();
    }

    public CoverageType getType() {
        return this.a.f();
    }

    public ExploredCoverage getExploredCoverage() {
        return this.a.g();
    }

    public City getCity() {
        return this.a.h();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.a.equals(((NearbyCoverageResult) obj).a);
    }

    public int hashCode() {
        return this.a.hashCode() + 31;
    }

    static {
        ah.a(new am<NearbyCoverageResult, ah>() {
            public NearbyCoverageResult a(ah ahVar) {
                return new NearbyCoverageResult(ahVar);
            }
        });
    }
}

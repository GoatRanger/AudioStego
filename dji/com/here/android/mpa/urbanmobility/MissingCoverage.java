package com.here.android.mpa.urbanmobility;

import com.nokia.maps.a.ad;
import com.nokia.maps.am;
import com.nokia.maps.annotation.HybridPlus;
import java.util.Collection;

@HybridPlus
public class MissingCoverage {
    private ad a;

    private MissingCoverage(ad adVar) {
        if (adVar == null) {
            throw new IllegalArgumentException("Impl can't be null.");
        }
        this.a = adVar;
    }

    public CoverageType getType() {
        return this.a.a();
    }

    public Collection<Operator> getOperators() {
        return this.a.b();
    }

    public Collection<Line> getLines() {
        return this.a.c();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.a.equals(((MissingCoverage) obj).a);
    }

    public int hashCode() {
        return this.a.hashCode() + 31;
    }

    static {
        ad.a(new am<MissingCoverage, ad>() {
            public MissingCoverage a(ad adVar) {
                return new MissingCoverage(adVar);
            }
        });
    }
}

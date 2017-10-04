package com.here.android.mpa.urbanmobility;

import com.nokia.maps.a.j;
import com.nokia.maps.am;
import com.nokia.maps.annotation.HybridPlus;
import java.util.List;

@HybridPlus
public class CityCoverageResult extends CitySearchResult {
    private j a;

    private CityCoverageResult(j jVar) {
        super(jVar);
        if (jVar == null) {
            throw new IllegalArgumentException("Impl can't be null.");
        }
        this.a = jVar;
    }

    public List<City> getNearbyCities() {
        return this.a.a();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.a.equals(((CityCoverageResult) obj).a);
    }

    public int hashCode() {
        return this.a.hashCode() + 31;
    }

    static {
        j.a(new am<CityCoverageResult, j>() {
            public CityCoverageResult a(j jVar) {
                return new CityCoverageResult(jVar);
            }
        });
    }
}

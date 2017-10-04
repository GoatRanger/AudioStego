package com.here.android.mpa.urbanmobility;

import com.here.android.mpa.common.GeoCoordinate;
import com.nokia.maps.a.k;
import com.nokia.maps.am;
import com.nokia.maps.annotation.HybridPlus;
import java.util.Collection;
import java.util.Date;

@HybridPlus
public class City {
    private k a;

    private City(k kVar) {
        if (kVar == null) {
            throw new IllegalArgumentException("Impl can't be null.");
        }
        this.a = kVar;
    }

    public String getName() {
        return this.a.a();
    }

    public String getCountry() {
        return this.a.b();
    }

    public Date getCreated() {
        return this.a.c();
    }

    public Date getUpdated() {
        return this.a.d();
    }

    public GeoCoordinate getLocation() {
        return this.a.e();
    }

    public String getDisplayName() {
        return this.a.f();
    }

    public int getDistance() {
        return this.a.g();
    }

    public double getRelevancy() {
        return this.a.h();
    }

    public String getState() {
        return this.a.i();
    }

    public int getPopulation() {
        return this.a.j();
    }

    public int getStopsCount() {
        return this.a.k();
    }

    public float getQuality() {
        return this.a.l();
    }

    public int getLinesCount() {
        return this.a.m();
    }

    public MissingCoverage getMissingCoverage() {
        return this.a.n();
    }

    public Collection<Operator> getOperators() {
        return this.a.o();
    }

    public Collection<Provider> getProviders() {
        return this.a.p();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.a.equals(((City) obj).a);
    }

    public int hashCode() {
        return this.a.hashCode() + 31;
    }

    static {
        k.a(new am<City, k>() {
            public City a(k kVar) {
                return new City(kVar);
            }
        });
    }
}

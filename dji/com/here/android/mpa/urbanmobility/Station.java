package com.here.android.mpa.urbanmobility;

import com.here.android.mpa.common.GeoCoordinate;
import com.nokia.maps.a.at;
import com.nokia.maps.am;
import com.nokia.maps.annotation.HybridPlus;
import java.util.Collection;

@HybridPlus
public class Station {
    public static final int DISTANCE_UNKNOWN = -1;
    public static final long DURATION_UNKNOWN = -1;
    private at a;

    Station(at atVar) {
        if (atVar == null) {
            throw new IllegalArgumentException("Impl can't be null.");
        }
        this.a = atVar;
    }

    public String getName() {
        return this.a.a();
    }

    public String getId() {
        return this.a.b();
    }

    public GeoCoordinate getCoordinate() {
        return this.a.i();
    }

    public Address getAddress() {
        return this.a.h();
    }

    public Collection<Line> getLines() {
        return this.a.j();
    }

    public Collection<LineCategory> getLineCategories() {
        return this.a.k();
    }

    public String getInfo() {
        return this.a.g();
    }

    public boolean hasBlindGuide() {
        return this.a.c();
    }

    public boolean hasElevator() {
        return this.a.d();
    }

    public boolean hasEscalator() {
        return this.a.e();
    }

    public boolean hasDepartureBoard() {
        return this.a.f();
    }

    public int getDistanceToStation() {
        return this.a.l();
    }

    public long getDurationToStation() {
        return this.a.m();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.a.equals(((Station) obj).a);
    }

    public int hashCode() {
        return this.a.hashCode() + 31;
    }

    static {
        at.a(new am<Station, at>() {
            public Station a(at atVar) {
                return new Station(atVar);
            }
        });
    }
}

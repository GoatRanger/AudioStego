package com.here.android.mpa.urbanmobility;

import com.here.android.mpa.common.GeoCoordinate;
import com.nokia.maps.a.d;
import com.nokia.maps.am;
import com.nokia.maps.annotation.HybridPlus;

@HybridPlus
public final class AccessPoint {
    private d a;

    private AccessPoint(d dVar) {
        if (dVar == null) {
            throw new IllegalArgumentException("Impl can't be null.");
        }
        this.a = dVar;
    }

    public GeoCoordinate getCoordinate() {
        return this.a.a();
    }

    public String getName() {
        return this.a.b();
    }

    public String getId() {
        return this.a.c();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.a.equals(((AccessPoint) obj).a);
    }

    public int hashCode() {
        return this.a.hashCode() + 31;
    }

    static {
        d.a(new am<AccessPoint, d>() {
            public AccessPoint a(d dVar) {
                return new AccessPoint(dVar);
            }
        });
    }
}

package com.here.android.mpa.urbanmobility;

import com.here.android.mpa.common.GeoCoordinate;
import com.nokia.maps.a.aa;
import com.nokia.maps.am;
import com.nokia.maps.annotation.HybridPlus;
import java.util.Date;

@HybridPlus
public class Location {
    private aa a;

    @HybridPlus
    public enum LocationType {
        DEPARTURE,
        ARRIVAL
    }

    private Location(aa aaVar) {
        if (aaVar == null) {
            throw new IllegalArgumentException("Impl can't be null.");
        }
        this.a = aaVar;
    }

    public LocationType getType() {
        return this.a.a();
    }

    public Address getAddress() {
        return this.a.b();
    }

    public Station getStation() {
        return this.a.c();
    }

    public GeoCoordinate getCoordinate() {
        return this.a.d();
    }

    public String getPlatform() {
        return this.a.e();
    }

    public Date getTime() {
        return this.a.g();
    }

    public boolean hasRealTimeInfo() {
        return this.a.h();
    }

    public RealTimeInfo getRealTimeInfo() {
        return this.a.i();
    }

    public AccessPoint getAccessPoint() {
        return this.a.f();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.a.equals(((Location) obj).a);
    }

    public int hashCode() {
        return this.a.hashCode() + 31;
    }

    static {
        aa.a(new am<Location, aa>() {
            public Location a(aa aaVar) {
                return new Location(aaVar);
            }
        });
    }
}

package com.here.android.mpa.common;

import com.nokia.maps.GeoPositionImpl;
import com.nokia.maps.am;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.k;
import java.util.Date;

public class GeoPosition {
    @HybridPlus
    public static final int SOURCE_CACHE = 4;
    @HybridPlus
    public static final int SOURCE_FUSION = 32;
    @HybridPlus
    public static final int SOURCE_HARDWARE = 16;
    @HybridPlus
    public static final int SOURCE_INDOOR = 8;
    @HybridPlus
    public static final int SOURCE_NONE = 0;
    @HybridPlus
    public static final int SOURCE_OFFLINE = 2;
    @HybridPlus
    public static final int SOURCE_ONLINE = 1;
    @HybridPlus
    public static final int TECHNOLOGY_BLE = 4;
    @HybridPlus
    public static final int TECHNOLOGY_CELL = 2;
    @HybridPlus
    public static final int TECHNOLOGY_GNSS = 8;
    @HybridPlus
    public static final int TECHNOLOGY_NONE = 0;
    @HybridPlus
    public static final int TECHNOLOGY_WIFI = 1;
    @Online
    public static final int UNKNOWN = 1073741824;
    private GeoPositionImpl a;

    @Online
    public GeoPosition(GeoCoordinate geoCoordinate) {
        this(new GeoPositionImpl(geoCoordinate.a));
    }

    protected GeoPosition(GeoPositionImpl geoPositionImpl) {
        this.a = geoPositionImpl;
    }

    @Online
    public final GeoCoordinate getCoordinate() {
        return this.a.a();
    }

    @Online
    public final float getLatitudeAccuracy() {
        return this.a.getLatitudeAccuracy();
    }

    @Online
    public final float getLongitudeAccuracy() {
        return this.a.getLongitudeAccuracy();
    }

    @Online
    public final float getAltitudeAccuracy() {
        return this.a.getAltitudeAccuracy();
    }

    @Online
    public final double getSpeed() {
        return this.a.getSpeed();
    }

    @Online
    public final double getHeading() {
        return this.a.getHeading();
    }

    @Online
    public final Date getTimestamp() {
        return this.a.b();
    }

    @Online
    public final boolean isValid() {
        return this.a.isValid();
    }

    @Online
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (GeoPosition.class.isInstance(obj)) {
            return this.a.equals(obj);
        }
        return false;
    }

    @Online
    public int hashCode() {
        return this.a.hashCode() + 217;
    }

    @HybridPlus
    public String getBuildingId() {
        return this.a.c();
    }

    @HybridPlus
    public String getBuildingName() {
        return this.a.d();
    }

    @HybridPlus
    public Integer getFloorId() {
        return this.a.e();
    }

    @HybridPlus
    public int getPositionSource() {
        return this.a.f();
    }

    @HybridPlus
    public int getPositionTechnology() {
        return this.a.g();
    }

    static {
        GeoPositionImpl.a(new k<GeoPosition, GeoPositionImpl>() {
            public GeoPositionImpl a(GeoPosition geoPosition) {
                return geoPosition.a;
            }
        }, new am<GeoPosition, GeoPositionImpl>() {
            public GeoPosition a(GeoPositionImpl geoPositionImpl) {
                return new GeoPosition(geoPositionImpl);
            }
        });
    }
}

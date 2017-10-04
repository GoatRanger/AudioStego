package com.nokia.maps;

import android.location.Location;
import android.os.Bundle;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.GeoPosition;
import com.here.services.common.Types.Source;
import com.here.services.common.Types.Technology;
import com.here.services.location.util.LocationHelper;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;
import dji.pilot.usercenter.protocol.d;
import java.util.Date;
import java.util.Iterator;

@Online
public class GeoPositionImpl extends BaseNativeObject {
    private static k<GeoPosition, GeoPositionImpl> a = null;
    private static am<GeoPosition, GeoPositionImpl> b = null;
    private String c;
    private String d;
    private Integer e;
    private int f = 0;
    private int g = 0;

    private native void createNative(GeoCoordinateImpl geoCoordinateImpl);

    private native void createNative(GeoCoordinateImpl geoCoordinateImpl, float f, float f2, float f3, long j);

    private native void destroyNative();

    private native GeoCoordinateImpl getCoordinateNative();

    private native long getTimestampNative();

    public native float getAltitudeAccuracy();

    public native double getHeading();

    public native float getLatitudeAccuracy();

    public native float getLongitudeAccuracy();

    public native double getSpeed();

    public native boolean isValid();

    static {
        ce.a(GeoPosition.class);
    }

    static GeoPositionImpl a(GeoPosition geoPosition) {
        if (a != null) {
            return (GeoPositionImpl) a.a(geoPosition);
        }
        return null;
    }

    public static GeoPosition a(GeoPositionImpl geoPositionImpl) {
        if (geoPositionImpl != null) {
            return (GeoPosition) b.a(geoPositionImpl);
        }
        return null;
    }

    public static void a(k<GeoPosition, GeoPositionImpl> kVar, am<GeoPosition, GeoPositionImpl> amVar) {
        a = kVar;
        b = amVar;
    }

    public GeoPositionImpl() {
        createNative(new GeoCoordinateImpl());
    }

    @OnlineNative
    protected GeoPositionImpl(int i) {
        this.nativeptr = i;
    }

    public GeoPositionImpl(GeoCoordinateImpl geoCoordinateImpl) {
        if (geoCoordinateImpl.d()) {
            createNative(geoCoordinateImpl);
            return;
        }
        throw new IllegalArgumentException("GeoCoordinate provided is invalid");
    }

    public GeoPositionImpl(Location location) {
        float accuracy;
        float speed;
        float bearing;
        GeoCoordinateImpl geoCoordinateImpl = new GeoCoordinateImpl(location.getLatitude(), location.getLongitude(), location.getAltitude());
        if (location.hasAccuracy()) {
            accuracy = location.getAccuracy();
        } else {
            accuracy = 1.07374182E9f;
        }
        if (location.hasSpeed()) {
            speed = location.getSpeed();
        } else {
            speed = 1.07374182E9f;
        }
        if (location.hasBearing()) {
            bearing = location.getBearing();
        } else {
            bearing = 1.07374182E9f;
        }
        createNative(geoCoordinateImpl, accuracy, speed, bearing, location.getTime());
        a(location);
    }

    public GeoCoordinate a() {
        return GeoCoordinateImpl.create(getCoordinateNative());
    }

    public void a(Bundle bundle) {
        if (isValid() && bundle != null) {
            Location location = new Location("");
            GeoCoordinateImpl coordinateNative = getCoordinateNative();
            location.setLatitude(coordinateNative.a());
            location.setLongitude(coordinateNative.b());
            location.setAltitude(coordinateNative.c());
            location.setAccuracy(getLongitudeAccuracy());
            location.setSpeed((float) getSpeed());
            location.setBearing((float) getHeading());
            location.setTime(getTimestampNative());
            location.setExtras(bundle);
            a(location);
        }
    }

    public Date b() {
        return new Date(getTimestampNative());
    }

    public String c() {
        return this.c;
    }

    public String d() {
        return this.d;
    }

    public Integer e() {
        return this.e;
    }

    public int f() {
        return this.f;
    }

    @HybridPlus
    public int g() {
        return this.g;
    }

    private void a(Location location) {
        this.c = LocationHelper.getBuildingId(location);
        this.d = LocationHelper.getBuildingName(location);
        this.e = LocationHelper.getFloorId(location);
        Iterator it = LocationHelper.getSources(location).iterator();
        while (it.hasNext()) {
            switch ((Source) it.next()) {
                case Cache:
                    this.f |= 4;
                    break;
                case Fusion:
                    this.f |= 32;
                    break;
                case Hardware:
                    this.f |= 16;
                    break;
                case HighAccuracy:
                    this.f |= 8;
                    break;
                case Offline:
                    this.f |= 2;
                    break;
                case Online:
                    this.f |= 1;
                    break;
                default:
                    break;
            }
        }
        it = LocationHelper.getTechnologies(location).iterator();
        while (it.hasNext()) {
            switch ((Technology) it.next()) {
                case BluetoothLE:
                    this.g |= 4;
                    break;
                case Cell:
                case Cellular:
                    this.g |= 2;
                    break;
                case Gnss:
                    this.g |= 8;
                    break;
                case Wlan:
                    this.g |= 1;
                    break;
                default:
                    break;
            }
        }
    }

    public String toString() {
        GeoCoordinateImpl geoCoordinateImpl = GeoCoordinateImpl.get(a());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getClass().getName());
        stringBuilder.append("[coordinate=" + geoCoordinateImpl);
        stringBuilder.append(d.H);
        return stringBuilder.toString();
    }

    protected void finalize() {
        destroyNative();
    }
}

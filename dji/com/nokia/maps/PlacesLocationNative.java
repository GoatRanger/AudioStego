package com.nokia.maps;

import com.here.android.mpa.common.GeoBoundingBox;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.search.Address;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;
import dji.pilot.usercenter.protocol.d;

@Online
public class PlacesLocationNative extends BaseNativeObject {
    private native void createNative();

    private native void destroyNative();

    private final native PlacesAddressNative getAddressNative();

    private final native GeoBoundingBoxImpl getBoundingBoxNative();

    private final native GeoCoordinateImpl getCoordinateNative();

    private native void setAddressNative(PlacesAddress placesAddress);

    private native void setCoordinateNative(GeoCoordinateImpl geoCoordinateImpl);

    public native String getId();

    public native void setBoundingBoxNative(GeoBoundingBoxImpl geoBoundingBoxImpl);

    public native void setId(String str);

    public PlacesLocationNative() {
        createNative();
    }

    @OnlineNative
    private PlacesLocationNative(int i) {
        this.nativeptr = i;
    }

    public Address a() {
        PlacesAddress placesAddress = new PlacesAddress(getAddressNative());
        if (placesAddress != null) {
            return PlacesAddress.create(placesAddress);
        }
        return null;
    }

    public GeoCoordinate b() {
        GeoCoordinateImpl coordinateNative = getCoordinateNative();
        if (coordinateNative != null) {
            return GeoCoordinateImpl.create(coordinateNative);
        }
        return null;
    }

    public GeoBoundingBox c() {
        GeoBoundingBoxImpl boundingBoxNative = getBoundingBoxNative();
        if (boundingBoxNative != null) {
            return GeoBoundingBoxImpl.create(boundingBoxNative);
        }
        return null;
    }

    public String toString() {
        return "PlacesLocation [getAddress()=" + a() + ", getId()=" + getId() + ", getCoordinate()=" + b() + ", getBoundingBox()=" + c() + d.H;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((b() == null ? 0 : b().hashCode()) + (((c() == null ? 0 : c().hashCode()) + (((a() == null ? 0 : a().hashCode()) + 31) * 31)) * 31)) * 31;
        if (getId() != null) {
            i = getId().hashCode();
        }
        return hashCode + i;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PlacesLocationNative placesLocationNative = (PlacesLocationNative) obj;
        if (c() == null || b() == null || getId() == null || a() == null) {
            return false;
        }
        if (!(a().equals(placesLocationNative.a()) && c().equals(placesLocationNative.c()) && b().equals(placesLocationNative.b()) && getId().equals(placesLocationNative.getId()))) {
            z = false;
        }
        return z;
    }

    protected void finalize() {
        bj.e("PlacesLocation", "nativeptr=0x%X", new Object[]{Integer.valueOf(this.nativeptr)});
        destroyNative();
    }
}

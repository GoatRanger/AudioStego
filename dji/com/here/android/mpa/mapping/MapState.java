package com.here.android.mpa.mapping;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.here.android.mpa.common.GeoCoordinate;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;

@Online
public final class MapState implements Parcelable {
    public static final Creator<MapState> CREATOR = new Creator<MapState>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public MapState a(Parcel parcel) {
            return new MapState(parcel);
        }

        public MapState[] a(int i) {
            return new MapState[i];
        }
    };
    private float a;
    private float b;
    private double c;
    private GeoCoordinate d;

    @OnlineNative
    public MapState(float f, float f2, double d, GeoCoordinate geoCoordinate) {
        this.a = f;
        this.b = f2;
        this.c = d;
        this.d = new GeoCoordinate(geoCoordinate);
    }

    private MapState(Parcel parcel) {
        this.a = parcel.readFloat();
        this.b = parcel.readFloat();
        this.c = parcel.readDouble();
        this.d = new GeoCoordinate(parcel.readDouble(), parcel.readDouble(), parcel.readDouble());
    }

    public final float getTilt() {
        return this.a;
    }

    public final float getOrientation() {
        return this.b;
    }

    public final double getZoomLevel() {
        return this.c;
    }

    public final GeoCoordinate getCenter() {
        return this.d;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.a);
        parcel.writeFloat(this.b);
        parcel.writeDouble(this.c);
        parcel.writeDouble(this.d.getLatitude());
        parcel.writeDouble(this.d.getLongitude());
        parcel.writeDouble(this.d.getAltitude());
    }
}

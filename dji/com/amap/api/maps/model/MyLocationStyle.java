package com.amap.api.maps.model;

import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;
import dji.pilot.visual.a.d;
import it.sauronsoftware.ftp4j.FTPCodes;

public class MyLocationStyle implements Parcelable {
    private BitmapDescriptor a;
    private float b = d.c;
    private float c = d.c;
    private int d = Color.argb(100, 0, 0, 180);
    private int e = Color.argb(255, 0, 0, FTPCodes.SERVICE_READY_FOR_NEW_USER);
    private float f = 1.0f;

    public MyLocationStyle myLocationIcon(BitmapDescriptor bitmapDescriptor) {
        this.a = bitmapDescriptor;
        return this;
    }

    public MyLocationStyle anchor(float f, float f2) {
        this.b = f;
        this.c = f2;
        return this;
    }

    public MyLocationStyle radiusFillColor(int i) {
        this.d = i;
        return this;
    }

    public MyLocationStyle strokeColor(int i) {
        this.e = i;
        return this;
    }

    public MyLocationStyle strokeWidth(float f) {
        this.f = f;
        return this;
    }

    public BitmapDescriptor getMyLocationIcon() {
        return this.a;
    }

    public float getAnchorU() {
        return this.b;
    }

    public float getAnchorV() {
        return this.c;
    }

    public int getRadiusFillColor() {
        return this.d;
    }

    public int getStrokeColor() {
        return this.e;
    }

    public float getStrokeWidth() {
        return this.f;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.a, i);
        parcel.writeFloat(this.b);
        parcel.writeFloat(this.c);
        parcel.writeInt(this.d);
        parcel.writeInt(this.e);
        parcel.writeFloat(this.f);
    }
}

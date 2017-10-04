package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class CircleOptions implements SafeParcelable {
    public static final zzb CREATOR = new zzb();
    private final int mVersionCode;
    private LatLng zzaJN;
    private double zzaJO;
    private float zzaJP;
    private int zzaJQ;
    private int zzaJR;
    private float zzaJS;
    private boolean zzaJT;

    public CircleOptions() {
        this.zzaJN = null;
        this.zzaJO = 0.0d;
        this.zzaJP = 10.0f;
        this.zzaJQ = -16777216;
        this.zzaJR = 0;
        this.zzaJS = 0.0f;
        this.zzaJT = true;
        this.mVersionCode = 1;
    }

    CircleOptions(int i, LatLng latLng, double d, float f, int i2, int i3, float f2, boolean z) {
        this.zzaJN = null;
        this.zzaJO = 0.0d;
        this.zzaJP = 10.0f;
        this.zzaJQ = -16777216;
        this.zzaJR = 0;
        this.zzaJS = 0.0f;
        this.zzaJT = true;
        this.mVersionCode = i;
        this.zzaJN = latLng;
        this.zzaJO = d;
        this.zzaJP = f;
        this.zzaJQ = i2;
        this.zzaJR = i3;
        this.zzaJS = f2;
        this.zzaJT = z;
    }

    public CircleOptions center(LatLng latLng) {
        this.zzaJN = latLng;
        return this;
    }

    public int describeContents() {
        return 0;
    }

    public CircleOptions fillColor(int i) {
        this.zzaJR = i;
        return this;
    }

    public LatLng getCenter() {
        return this.zzaJN;
    }

    public int getFillColor() {
        return this.zzaJR;
    }

    public double getRadius() {
        return this.zzaJO;
    }

    public int getStrokeColor() {
        return this.zzaJQ;
    }

    public float getStrokeWidth() {
        return this.zzaJP;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public float getZIndex() {
        return this.zzaJS;
    }

    public boolean isVisible() {
        return this.zzaJT;
    }

    public CircleOptions radius(double d) {
        this.zzaJO = d;
        return this;
    }

    public CircleOptions strokeColor(int i) {
        this.zzaJQ = i;
        return this;
    }

    public CircleOptions strokeWidth(float f) {
        this.zzaJP = f;
        return this;
    }

    public CircleOptions visible(boolean z) {
        this.zzaJT = z;
        return this;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzb.zza(this, parcel, i);
    }

    public CircleOptions zIndex(float f) {
        this.zzaJS = f;
        return this;
    }
}

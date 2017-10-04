package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class PolylineOptions implements SafeParcelable {
    public static final zzi CREATOR = new zzi();
    private int mColor;
    private final int mVersionCode;
    private float zzaJS;
    private boolean zzaJT;
    private float zzaJX;
    private final List<LatLng> zzaKu;
    private boolean zzaKw;

    public PolylineOptions() {
        this.zzaJX = 10.0f;
        this.mColor = -16777216;
        this.zzaJS = 0.0f;
        this.zzaJT = true;
        this.zzaKw = false;
        this.mVersionCode = 1;
        this.zzaKu = new ArrayList();
    }

    PolylineOptions(int i, List list, float f, int i2, float f2, boolean z, boolean z2) {
        this.zzaJX = 10.0f;
        this.mColor = -16777216;
        this.zzaJS = 0.0f;
        this.zzaJT = true;
        this.zzaKw = false;
        this.mVersionCode = i;
        this.zzaKu = list;
        this.zzaJX = f;
        this.mColor = i2;
        this.zzaJS = f2;
        this.zzaJT = z;
        this.zzaKw = z2;
    }

    public PolylineOptions add(LatLng latLng) {
        this.zzaKu.add(latLng);
        return this;
    }

    public PolylineOptions add(LatLng... latLngArr) {
        this.zzaKu.addAll(Arrays.asList(latLngArr));
        return this;
    }

    public PolylineOptions addAll(Iterable<LatLng> iterable) {
        for (LatLng add : iterable) {
            this.zzaKu.add(add);
        }
        return this;
    }

    public PolylineOptions color(int i) {
        this.mColor = i;
        return this;
    }

    public int describeContents() {
        return 0;
    }

    public PolylineOptions geodesic(boolean z) {
        this.zzaKw = z;
        return this;
    }

    public int getColor() {
        return this.mColor;
    }

    public List<LatLng> getPoints() {
        return this.zzaKu;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public float getWidth() {
        return this.zzaJX;
    }

    public float getZIndex() {
        return this.zzaJS;
    }

    public boolean isGeodesic() {
        return this.zzaKw;
    }

    public boolean isVisible() {
        return this.zzaJT;
    }

    public PolylineOptions visible(boolean z) {
        this.zzaJT = z;
        return this;
    }

    public PolylineOptions width(float f) {
        this.zzaJX = f;
        return this;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzi.zza(this, parcel, i);
    }

    public PolylineOptions zIndex(float f) {
        this.zzaJS = f;
        return this;
    }
}

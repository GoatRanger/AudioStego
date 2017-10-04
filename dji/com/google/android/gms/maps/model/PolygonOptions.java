package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class PolygonOptions implements SafeParcelable {
    public static final zzh CREATOR = new zzh();
    private final int mVersionCode;
    private float zzaJP;
    private int zzaJQ;
    private int zzaJR;
    private float zzaJS;
    private boolean zzaJT;
    private final List<LatLng> zzaKu;
    private final List<List<LatLng>> zzaKv;
    private boolean zzaKw;

    public PolygonOptions() {
        this.zzaJP = 10.0f;
        this.zzaJQ = -16777216;
        this.zzaJR = 0;
        this.zzaJS = 0.0f;
        this.zzaJT = true;
        this.zzaKw = false;
        this.mVersionCode = 1;
        this.zzaKu = new ArrayList();
        this.zzaKv = new ArrayList();
    }

    PolygonOptions(int i, List<LatLng> list, List list2, float f, int i2, int i3, float f2, boolean z, boolean z2) {
        this.zzaJP = 10.0f;
        this.zzaJQ = -16777216;
        this.zzaJR = 0;
        this.zzaJS = 0.0f;
        this.zzaJT = true;
        this.zzaKw = false;
        this.mVersionCode = i;
        this.zzaKu = list;
        this.zzaKv = list2;
        this.zzaJP = f;
        this.zzaJQ = i2;
        this.zzaJR = i3;
        this.zzaJS = f2;
        this.zzaJT = z;
        this.zzaKw = z2;
    }

    public PolygonOptions add(LatLng latLng) {
        this.zzaKu.add(latLng);
        return this;
    }

    public PolygonOptions add(LatLng... latLngArr) {
        this.zzaKu.addAll(Arrays.asList(latLngArr));
        return this;
    }

    public PolygonOptions addAll(Iterable<LatLng> iterable) {
        for (LatLng add : iterable) {
            this.zzaKu.add(add);
        }
        return this;
    }

    public PolygonOptions addHole(Iterable<LatLng> iterable) {
        ArrayList arrayList = new ArrayList();
        for (LatLng add : iterable) {
            arrayList.add(add);
        }
        this.zzaKv.add(arrayList);
        return this;
    }

    public int describeContents() {
        return 0;
    }

    public PolygonOptions fillColor(int i) {
        this.zzaJR = i;
        return this;
    }

    public PolygonOptions geodesic(boolean z) {
        this.zzaKw = z;
        return this;
    }

    public int getFillColor() {
        return this.zzaJR;
    }

    public List<List<LatLng>> getHoles() {
        return this.zzaKv;
    }

    public List<LatLng> getPoints() {
        return this.zzaKu;
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

    public boolean isGeodesic() {
        return this.zzaKw;
    }

    public boolean isVisible() {
        return this.zzaJT;
    }

    public PolygonOptions strokeColor(int i) {
        this.zzaJQ = i;
        return this;
    }

    public PolygonOptions strokeWidth(float f) {
        this.zzaJP = f;
        return this;
    }

    public PolygonOptions visible(boolean z) {
        this.zzaJT = z;
        return this;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzh.zza(this, parcel, i);
    }

    public PolygonOptions zIndex(float f) {
        this.zzaJS = f;
        return this;
    }

    List zzya() {
        return this.zzaKv;
    }
}

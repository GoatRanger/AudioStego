package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.dynamic.zzd.zza;
import dji.pilot.visual.a.d;

public final class MarkerOptions implements SafeParcelable {
    public static final zzf CREATOR = new zzf();
    private float mAlpha;
    private final int mVersionCode;
    private boolean zzaJT;
    private LatLng zzaJq;
    private float zzaKb;
    private float zzaKc;
    private String zzaKk;
    private BitmapDescriptor zzaKl;
    private boolean zzaKm;
    private boolean zzaKn;
    private float zzaKo;
    private float zzaKp;
    private float zzaKq;
    private String zzajf;

    public MarkerOptions() {
        this.zzaKb = d.c;
        this.zzaKc = 1.0f;
        this.zzaJT = true;
        this.zzaKn = false;
        this.zzaKo = 0.0f;
        this.zzaKp = d.c;
        this.zzaKq = 0.0f;
        this.mAlpha = 1.0f;
        this.mVersionCode = 1;
    }

    MarkerOptions(int i, LatLng latLng, String str, String str2, IBinder iBinder, float f, float f2, boolean z, boolean z2, boolean z3, float f3, float f4, float f5, float f6) {
        this.zzaKb = d.c;
        this.zzaKc = 1.0f;
        this.zzaJT = true;
        this.zzaKn = false;
        this.zzaKo = 0.0f;
        this.zzaKp = d.c;
        this.zzaKq = 0.0f;
        this.mAlpha = 1.0f;
        this.mVersionCode = i;
        this.zzaJq = latLng;
        this.zzajf = str;
        this.zzaKk = str2;
        this.zzaKl = iBinder == null ? null : new BitmapDescriptor(zza.zzbk(iBinder));
        this.zzaKb = f;
        this.zzaKc = f2;
        this.zzaKm = z;
        this.zzaJT = z2;
        this.zzaKn = z3;
        this.zzaKo = f3;
        this.zzaKp = f4;
        this.zzaKq = f5;
        this.mAlpha = f6;
    }

    public MarkerOptions alpha(float f) {
        this.mAlpha = f;
        return this;
    }

    public MarkerOptions anchor(float f, float f2) {
        this.zzaKb = f;
        this.zzaKc = f2;
        return this;
    }

    public int describeContents() {
        return 0;
    }

    public MarkerOptions draggable(boolean z) {
        this.zzaKm = z;
        return this;
    }

    public MarkerOptions flat(boolean z) {
        this.zzaKn = z;
        return this;
    }

    public float getAlpha() {
        return this.mAlpha;
    }

    public float getAnchorU() {
        return this.zzaKb;
    }

    public float getAnchorV() {
        return this.zzaKc;
    }

    public BitmapDescriptor getIcon() {
        return this.zzaKl;
    }

    public float getInfoWindowAnchorU() {
        return this.zzaKp;
    }

    public float getInfoWindowAnchorV() {
        return this.zzaKq;
    }

    public LatLng getPosition() {
        return this.zzaJq;
    }

    public float getRotation() {
        return this.zzaKo;
    }

    public String getSnippet() {
        return this.zzaKk;
    }

    public String getTitle() {
        return this.zzajf;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public MarkerOptions icon(BitmapDescriptor bitmapDescriptor) {
        this.zzaKl = bitmapDescriptor;
        return this;
    }

    public MarkerOptions infoWindowAnchor(float f, float f2) {
        this.zzaKp = f;
        this.zzaKq = f2;
        return this;
    }

    public boolean isDraggable() {
        return this.zzaKm;
    }

    public boolean isFlat() {
        return this.zzaKn;
    }

    public boolean isVisible() {
        return this.zzaJT;
    }

    public MarkerOptions position(LatLng latLng) {
        this.zzaJq = latLng;
        return this;
    }

    public MarkerOptions rotation(float f) {
        this.zzaKo = f;
        return this;
    }

    public MarkerOptions snippet(String str) {
        this.zzaKk = str;
        return this;
    }

    public MarkerOptions title(String str) {
        this.zzajf = str;
        return this;
    }

    public MarkerOptions visible(boolean z) {
        this.zzaJT = z;
        return this;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzf.zza(this, parcel, i);
    }

    IBinder zzxZ() {
        return this.zzaKl == null ? null : this.zzaKl.zzxw().asBinder();
    }
}

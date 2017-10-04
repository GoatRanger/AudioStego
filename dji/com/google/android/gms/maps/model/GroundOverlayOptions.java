package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.dynamic.zzd.zza;
import dji.pilot.visual.a.d;

public final class GroundOverlayOptions implements SafeParcelable {
    public static final zzc CREATOR = new zzc();
    public static final float NO_DIMENSION = -1.0f;
    private final int mVersionCode;
    private float zzaJL;
    private float zzaJS;
    private boolean zzaJT;
    private BitmapDescriptor zzaJV;
    private LatLng zzaJW;
    private float zzaJX;
    private float zzaJY;
    private LatLngBounds zzaJZ;
    private float zzaKa;
    private float zzaKb;
    private float zzaKc;

    public GroundOverlayOptions() {
        this.zzaJT = true;
        this.zzaKa = 0.0f;
        this.zzaKb = d.c;
        this.zzaKc = d.c;
        this.mVersionCode = 1;
    }

    GroundOverlayOptions(int i, IBinder iBinder, LatLng latLng, float f, float f2, LatLngBounds latLngBounds, float f3, float f4, boolean z, float f5, float f6, float f7) {
        this.zzaJT = true;
        this.zzaKa = 0.0f;
        this.zzaKb = d.c;
        this.zzaKc = d.c;
        this.mVersionCode = i;
        this.zzaJV = new BitmapDescriptor(zza.zzbk(iBinder));
        this.zzaJW = latLng;
        this.zzaJX = f;
        this.zzaJY = f2;
        this.zzaJZ = latLngBounds;
        this.zzaJL = f3;
        this.zzaJS = f4;
        this.zzaJT = z;
        this.zzaKa = f5;
        this.zzaKb = f6;
        this.zzaKc = f7;
    }

    private GroundOverlayOptions zza(LatLng latLng, float f, float f2) {
        this.zzaJW = latLng;
        this.zzaJX = f;
        this.zzaJY = f2;
        return this;
    }

    public GroundOverlayOptions anchor(float f, float f2) {
        this.zzaKb = f;
        this.zzaKc = f2;
        return this;
    }

    public GroundOverlayOptions bearing(float f) {
        this.zzaJL = ((f % 360.0f) + 360.0f) % 360.0f;
        return this;
    }

    public int describeContents() {
        return 0;
    }

    public float getAnchorU() {
        return this.zzaKb;
    }

    public float getAnchorV() {
        return this.zzaKc;
    }

    public float getBearing() {
        return this.zzaJL;
    }

    public LatLngBounds getBounds() {
        return this.zzaJZ;
    }

    public float getHeight() {
        return this.zzaJY;
    }

    public BitmapDescriptor getImage() {
        return this.zzaJV;
    }

    public LatLng getLocation() {
        return this.zzaJW;
    }

    public float getTransparency() {
        return this.zzaKa;
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

    public GroundOverlayOptions image(BitmapDescriptor bitmapDescriptor) {
        this.zzaJV = bitmapDescriptor;
        return this;
    }

    public boolean isVisible() {
        return this.zzaJT;
    }

    public GroundOverlayOptions position(LatLng latLng, float f) {
        boolean z = true;
        zzx.zza(this.zzaJZ == null, "Position has already been set using positionFromBounds");
        zzx.zzb(latLng != null, "Location must be specified");
        if (f < 0.0f) {
            z = false;
        }
        zzx.zzb(z, "Width must be non-negative");
        return zza(latLng, f, -1.0f);
    }

    public GroundOverlayOptions position(LatLng latLng, float f, float f2) {
        boolean z = true;
        zzx.zza(this.zzaJZ == null, "Position has already been set using positionFromBounds");
        zzx.zzb(latLng != null, "Location must be specified");
        zzx.zzb(f >= 0.0f, "Width must be non-negative");
        if (f2 < 0.0f) {
            z = false;
        }
        zzx.zzb(z, "Height must be non-negative");
        return zza(latLng, f, f2);
    }

    public GroundOverlayOptions positionFromBounds(LatLngBounds latLngBounds) {
        zzx.zza(this.zzaJW == null, "Position has already been set using position: " + this.zzaJW);
        this.zzaJZ = latLngBounds;
        return this;
    }

    public GroundOverlayOptions transparency(float f) {
        boolean z = f >= 0.0f && f <= 1.0f;
        zzx.zzb(z, "Transparency must be in the range [0..1]");
        this.zzaKa = f;
        return this;
    }

    public GroundOverlayOptions visible(boolean z) {
        this.zzaJT = z;
        return this;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzc.zza(this, parcel, i);
    }

    public GroundOverlayOptions zIndex(float f) {
        this.zzaJS = f;
        return this;
    }

    IBinder zzxY() {
        return this.zzaJV.zzxw().asBinder();
    }
}

package com.google.android.gms.maps;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.internal.zza;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;

public final class StreetViewPanoramaOptions implements SafeParcelable {
    public static final zzb CREATOR = new zzb();
    private final int mVersionCode;
    private Boolean zzaIB;
    private Boolean zzaIH;
    private StreetViewPanoramaCamera zzaJo;
    private String zzaJp;
    private LatLng zzaJq;
    private Integer zzaJr;
    private Boolean zzaJs;
    private Boolean zzaJt;
    private Boolean zzaJu;

    public StreetViewPanoramaOptions() {
        this.zzaJs = Boolean.valueOf(true);
        this.zzaIH = Boolean.valueOf(true);
        this.zzaJt = Boolean.valueOf(true);
        this.zzaJu = Boolean.valueOf(true);
        this.mVersionCode = 1;
    }

    StreetViewPanoramaOptions(int i, StreetViewPanoramaCamera streetViewPanoramaCamera, String str, LatLng latLng, Integer num, byte b, byte b2, byte b3, byte b4, byte b5) {
        this.zzaJs = Boolean.valueOf(true);
        this.zzaIH = Boolean.valueOf(true);
        this.zzaJt = Boolean.valueOf(true);
        this.zzaJu = Boolean.valueOf(true);
        this.mVersionCode = i;
        this.zzaJo = streetViewPanoramaCamera;
        this.zzaJq = latLng;
        this.zzaJr = num;
        this.zzaJp = str;
        this.zzaJs = zza.zza(b);
        this.zzaIH = zza.zza(b2);
        this.zzaJt = zza.zza(b3);
        this.zzaJu = zza.zza(b4);
        this.zzaIB = zza.zza(b5);
    }

    public int describeContents() {
        return 0;
    }

    public Boolean getPanningGesturesEnabled() {
        return this.zzaJt;
    }

    public String getPanoramaId() {
        return this.zzaJp;
    }

    public LatLng getPosition() {
        return this.zzaJq;
    }

    public Integer getRadius() {
        return this.zzaJr;
    }

    public Boolean getStreetNamesEnabled() {
        return this.zzaJu;
    }

    public StreetViewPanoramaCamera getStreetViewPanoramaCamera() {
        return this.zzaJo;
    }

    public Boolean getUseViewLifecycleInFragment() {
        return this.zzaIB;
    }

    public Boolean getUserNavigationEnabled() {
        return this.zzaJs;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public Boolean getZoomGesturesEnabled() {
        return this.zzaIH;
    }

    public StreetViewPanoramaOptions panningGesturesEnabled(boolean z) {
        this.zzaJt = Boolean.valueOf(z);
        return this;
    }

    public StreetViewPanoramaOptions panoramaCamera(StreetViewPanoramaCamera streetViewPanoramaCamera) {
        this.zzaJo = streetViewPanoramaCamera;
        return this;
    }

    public StreetViewPanoramaOptions panoramaId(String str) {
        this.zzaJp = str;
        return this;
    }

    public StreetViewPanoramaOptions position(LatLng latLng) {
        this.zzaJq = latLng;
        return this;
    }

    public StreetViewPanoramaOptions position(LatLng latLng, Integer num) {
        this.zzaJq = latLng;
        this.zzaJr = num;
        return this;
    }

    public StreetViewPanoramaOptions streetNamesEnabled(boolean z) {
        this.zzaJu = Boolean.valueOf(z);
        return this;
    }

    public StreetViewPanoramaOptions useViewLifecycleInFragment(boolean z) {
        this.zzaIB = Boolean.valueOf(z);
        return this;
    }

    public StreetViewPanoramaOptions userNavigationEnabled(boolean z) {
        this.zzaJs = Boolean.valueOf(z);
        return this;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzb.zza(this, parcel, i);
    }

    public StreetViewPanoramaOptions zoomGesturesEnabled(boolean z) {
        this.zzaIH = Boolean.valueOf(z);
        return this;
    }

    byte zzxA() {
        return zza.zze(this.zzaIB);
    }

    byte zzxE() {
        return zza.zze(this.zzaIH);
    }

    byte zzxP() {
        return zza.zze(this.zzaJs);
    }

    byte zzxQ() {
        return zza.zze(this.zzaJt);
    }

    byte zzxR() {
        return zza.zze(this.zzaJu);
    }
}

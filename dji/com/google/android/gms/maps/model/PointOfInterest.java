package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class PointOfInterest implements SafeParcelable {
    public static final zzg CREATOR = new zzg();
    private final int mVersionCode;
    public final String name;
    public final LatLng zzaKr;
    public final String zzaKs;

    PointOfInterest(int i, LatLng latLng, String str, String str2) {
        this.mVersionCode = i;
        this.zzaKr = latLng;
        this.zzaKs = str;
        this.name = str2;
    }

    public int describeContents() {
        return 0;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzg.zza(this, parcel, i);
    }
}

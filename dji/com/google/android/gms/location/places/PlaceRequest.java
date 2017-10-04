package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import java.util.concurrent.TimeUnit;

public final class PlaceRequest implements SafeParcelable {
    public static final Creator<PlaceRequest> CREATOR = new zzk();
    static final long zzaGv = TimeUnit.HOURS.toMillis(1);
    private final int mPriority;
    final int mVersionCode;
    private final long zzaEE;
    private final long zzaEj;
    private final PlaceFilter zzaGw;

    public PlaceRequest(int i, PlaceFilter placeFilter, long j, int i2, long j2) {
        this.mVersionCode = i;
        this.zzaGw = placeFilter;
        this.zzaEE = j;
        this.mPriority = i2;
        this.zzaEj = j2;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PlaceRequest)) {
            return false;
        }
        PlaceRequest placeRequest = (PlaceRequest) obj;
        return zzw.equal(this.zzaGw, placeRequest.zzaGw) && this.zzaEE == placeRequest.zzaEE && this.mPriority == placeRequest.mPriority && this.zzaEj == placeRequest.zzaEj;
    }

    public long getExpirationTime() {
        return this.zzaEj;
    }

    public long getInterval() {
        return this.zzaEE;
    }

    public int getPriority() {
        return this.mPriority;
    }

    public int hashCode() {
        return zzw.hashCode(this.zzaGw, Long.valueOf(this.zzaEE), Integer.valueOf(this.mPriority), Long.valueOf(this.zzaEj));
    }

    public String toString() {
        return zzw.zzv(this).zzg("filter", this.zzaGw).zzg("interval", Long.valueOf(this.zzaEE)).zzg("priority", Integer.valueOf(this.mPriority)).zzg("expireAt", Long.valueOf(this.zzaEj)).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzk.zza(this, parcel, i);
    }

    public PlaceFilter zzwO() {
        return this.zzaGw;
    }
}

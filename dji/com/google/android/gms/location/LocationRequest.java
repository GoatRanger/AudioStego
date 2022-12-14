package com.google.android.gms.location;

import android.os.Parcel;
import android.os.SystemClock;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.here.odnp.posclient.pos.IPositioningSession;

public final class LocationRequest implements SafeParcelable {
    public static final LocationRequestCreator CREATOR = new LocationRequestCreator();
    public static final int PRIORITY_BALANCED_POWER_ACCURACY = 102;
    public static final int PRIORITY_HIGH_ACCURACY = 100;
    public static final int PRIORITY_LOW_POWER = 104;
    public static final int PRIORITY_NO_POWER = 105;
    int mPriority;
    private final int mVersionCode;
    long zzaEE;
    long zzaEF;
    int zzaEG;
    float zzaEH;
    long zzaEI;
    long zzaEj;
    boolean zzasP;

    public LocationRequest() {
        this.mVersionCode = 1;
        this.mPriority = 102;
        this.zzaEE = 3600000;
        this.zzaEF = 600000;
        this.zzasP = false;
        this.zzaEj = IPositioningSession.NotSet;
        this.zzaEG = Integer.MAX_VALUE;
        this.zzaEH = 0.0f;
        this.zzaEI = 0;
    }

    LocationRequest(int i, int i2, long j, long j2, boolean z, long j3, int i3, float f, long j4) {
        this.mVersionCode = i;
        this.mPriority = i2;
        this.zzaEE = j;
        this.zzaEF = j2;
        this.zzasP = z;
        this.zzaEj = j3;
        this.zzaEG = i3;
        this.zzaEH = f;
        this.zzaEI = j4;
    }

    public static LocationRequest create() {
        return new LocationRequest();
    }

    private static void zzK(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("invalid interval: " + j);
        }
    }

    private static void zzd(float f) {
        if (f < 0.0f) {
            throw new IllegalArgumentException("invalid displacement: " + f);
        }
    }

    private static void zzgP(int i) {
        switch (i) {
            case 100:
            case 102:
            case 104:
            case 105:
                return;
            default:
                throw new IllegalArgumentException("invalid quality: " + i);
        }
    }

    public static String zzgQ(int i) {
        switch (i) {
            case 100:
                return "PRIORITY_HIGH_ACCURACY";
            case 102:
                return "PRIORITY_BALANCED_POWER_ACCURACY";
            case 104:
                return "PRIORITY_LOW_POWER";
            case 105:
                return "PRIORITY_NO_POWER";
            default:
                return "???";
        }
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LocationRequest)) {
            return false;
        }
        LocationRequest locationRequest = (LocationRequest) obj;
        return this.mPriority == locationRequest.mPriority && this.zzaEE == locationRequest.zzaEE && this.zzaEF == locationRequest.zzaEF && this.zzasP == locationRequest.zzasP && this.zzaEj == locationRequest.zzaEj && this.zzaEG == locationRequest.zzaEG && this.zzaEH == locationRequest.zzaEH;
    }

    public long getExpirationTime() {
        return this.zzaEj;
    }

    public long getFastestInterval() {
        return this.zzaEF;
    }

    public long getInterval() {
        return this.zzaEE;
    }

    public long getMaxWaitTime() {
        long j = this.zzaEI;
        return j < this.zzaEE ? this.zzaEE : j;
    }

    public int getNumUpdates() {
        return this.zzaEG;
    }

    public int getPriority() {
        return this.mPriority;
    }

    public float getSmallestDisplacement() {
        return this.zzaEH;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return zzw.hashCode(Integer.valueOf(this.mPriority), Long.valueOf(this.zzaEE), Long.valueOf(this.zzaEF), Boolean.valueOf(this.zzasP), Long.valueOf(this.zzaEj), Integer.valueOf(this.zzaEG), Float.valueOf(this.zzaEH));
    }

    public LocationRequest setExpirationDuration(long j) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (j > IPositioningSession.NotSet - elapsedRealtime) {
            this.zzaEj = IPositioningSession.NotSet;
        } else {
            this.zzaEj = elapsedRealtime + j;
        }
        if (this.zzaEj < 0) {
            this.zzaEj = 0;
        }
        return this;
    }

    public LocationRequest setExpirationTime(long j) {
        this.zzaEj = j;
        if (this.zzaEj < 0) {
            this.zzaEj = 0;
        }
        return this;
    }

    public LocationRequest setFastestInterval(long j) {
        zzK(j);
        this.zzasP = true;
        this.zzaEF = j;
        return this;
    }

    public LocationRequest setInterval(long j) {
        zzK(j);
        this.zzaEE = j;
        if (!this.zzasP) {
            this.zzaEF = (long) (((double) this.zzaEE) / 6.0d);
        }
        return this;
    }

    public LocationRequest setMaxWaitTime(long j) {
        zzK(j);
        this.zzaEI = j;
        return this;
    }

    public LocationRequest setNumUpdates(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("invalid numUpdates: " + i);
        }
        this.zzaEG = i;
        return this;
    }

    public LocationRequest setPriority(int i) {
        zzgP(i);
        this.mPriority = i;
        return this;
    }

    public LocationRequest setSmallestDisplacement(float f) {
        zzd(f);
        this.zzaEH = f;
        return this;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Request[").append(zzgQ(this.mPriority));
        if (this.mPriority != 105) {
            stringBuilder.append(" requested=");
            stringBuilder.append(this.zzaEE).append("ms");
        }
        stringBuilder.append(" fastest=");
        stringBuilder.append(this.zzaEF).append("ms");
        if (this.zzaEI > this.zzaEE) {
            stringBuilder.append(" maxWait=");
            stringBuilder.append(this.zzaEI).append("ms");
        }
        if (this.zzaEj != IPositioningSession.NotSet) {
            long elapsedRealtime = this.zzaEj - SystemClock.elapsedRealtime();
            stringBuilder.append(" expireIn=");
            stringBuilder.append(elapsedRealtime).append("ms");
        }
        if (this.zzaEG != Integer.MAX_VALUE) {
            stringBuilder.append(" num=").append(this.zzaEG);
        }
        stringBuilder.append(']');
        return stringBuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        LocationRequestCreator.zza(this, parcel, i);
    }
}

package com.google.android.gms.location;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import dji.pilot.usercenter.protocol.d;
import java.util.Comparator;

public class DetectedActivity implements SafeParcelable {
    public static final DetectedActivityCreator CREATOR = new DetectedActivityCreator();
    public static final int IN_VEHICLE = 0;
    public static final int ON_BICYCLE = 1;
    public static final int ON_FOOT = 2;
    public static final int RUNNING = 8;
    public static final int STILL = 3;
    public static final int TILTING = 5;
    public static final int UNKNOWN = 4;
    public static final int WALKING = 7;
    public static final Comparator<DetectedActivity> zzaEf = new Comparator<DetectedActivity>() {
        public /* synthetic */ int compare(Object obj, Object obj2) {
            return zza((DetectedActivity) obj, (DetectedActivity) obj2);
        }

        public int zza(DetectedActivity detectedActivity, DetectedActivity detectedActivity2) {
            int compareTo = Integer.valueOf(detectedActivity2.getConfidence()).compareTo(Integer.valueOf(detectedActivity.getConfidence()));
            return compareTo == 0 ? Integer.valueOf(detectedActivity.getType()).compareTo(Integer.valueOf(detectedActivity2.getType())) : compareTo;
        }
    };
    private final int mVersionCode;
    int zzaEg;
    int zzaEh;

    public DetectedActivity(int i, int i2) {
        this.mVersionCode = 1;
        this.zzaEg = i;
        this.zzaEh = i2;
    }

    public DetectedActivity(int i, int i2, int i3) {
        this.mVersionCode = i;
        this.zzaEg = i2;
        this.zzaEh = i3;
    }

    private int zzgK(int i) {
        return i > 15 ? 4 : i;
    }

    public static String zzgL(int i) {
        switch (i) {
            case 0:
                return "IN_VEHICLE";
            case 1:
                return "ON_BICYCLE";
            case 2:
                return "ON_FOOT";
            case 3:
                return "STILL";
            case 4:
                return "UNKNOWN";
            case 5:
                return "TILTING";
            case 7:
                return "WALKING";
            case 8:
                return "RUNNING";
            default:
                return Integer.toString(i);
        }
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DetectedActivity detectedActivity = (DetectedActivity) obj;
        return this.zzaEg == detectedActivity.zzaEg && this.zzaEh == detectedActivity.zzaEh;
    }

    public int getConfidence() {
        return this.zzaEh;
    }

    public int getType() {
        return zzgK(this.zzaEg);
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return zzw.hashCode(Integer.valueOf(this.zzaEg), Integer.valueOf(this.zzaEh));
    }

    public String toString() {
        return "DetectedActivity [type=" + zzgL(getType()) + ", confidence=" + this.zzaEh + d.H;
    }

    public void writeToParcel(Parcel parcel, int i) {
        DetectedActivityCreator.zza(this, parcel, i);
    }
}

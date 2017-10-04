package com.here.android.mpa.streetlevel;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.nokia.maps.annotation.HybridPlusNative;

public final class StreetLevelModelState implements Parcelable {
    public static final Creator<StreetLevelModelState> CREATOR = new Creator<StreetLevelModelState>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public StreetLevelModelState a(Parcel parcel) {
            return new StreetLevelModelState(parcel);
        }

        public StreetLevelModelState[] a(int i) {
            return new StreetLevelModelState[i];
        }
    };
    private float a;
    private float b;
    private float c;

    @HybridPlusNative
    private StreetLevelModelState(float f, float f2, float f3) {
        this.c = f;
        this.b = f2;
        this.a = f3;
    }

    public float getZoom() {
        return this.c;
    }

    public float getHeading() {
        return this.b;
    }

    public float getPitch() {
        return this.a;
    }

    public int hashCode() {
        return ((((Float.floatToIntBits(this.b) + 31) * 31) + Float.floatToIntBits(this.a)) * 31) + Float.floatToIntBits(this.c);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof StreetLevelModelState)) {
            return false;
        }
        StreetLevelModelState streetLevelModelState = (StreetLevelModelState) obj;
        if (Float.floatToIntBits(this.b) != Float.floatToIntBits(streetLevelModelState.b)) {
            return false;
        }
        if (Float.floatToIntBits(this.a) != Float.floatToIntBits(streetLevelModelState.a)) {
            return false;
        }
        if (Float.floatToIntBits(this.c) != Float.floatToIntBits(streetLevelModelState.c)) {
            return false;
        }
        return true;
    }

    protected StreetLevelModelState(Parcel parcel) {
        this.a = parcel.readFloat();
        this.b = parcel.readFloat();
        this.c = parcel.readFloat();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.a);
        parcel.writeFloat(this.b);
        parcel.writeFloat(this.c);
    }
}

package com.amap.api.mapcore.util;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class bh implements Creator<bg> {
    bh() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }

    public bg a(Parcel parcel) {
        return new bg(parcel);
    }

    public bg[] a(int i) {
        return new bg[i];
    }
}

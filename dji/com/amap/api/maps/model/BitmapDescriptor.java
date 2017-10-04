package com.amap.api.maps.model;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.mapcore.util.dj;

public final class BitmapDescriptor implements Parcelable, Cloneable {
    public static final BitmapDescriptorCreator CREATOR = new BitmapDescriptorCreator();
    int a = 0;
    int b = 0;
    Bitmap c;

    BitmapDescriptor(Bitmap bitmap) {
        if (bitmap != null) {
            this.a = bitmap.getWidth();
            this.b = bitmap.getHeight();
            this.c = a(bitmap, dj.a(this.a), dj.a(this.b));
        }
    }

    private BitmapDescriptor(Bitmap bitmap, int i, int i2) {
        this.a = i;
        this.b = i2;
        this.c = bitmap;
    }

    public BitmapDescriptor clone() {
        try {
            return new BitmapDescriptor(Bitmap.createBitmap(this.c), this.a, this.b);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public Bitmap getBitmap() {
        return this.c;
    }

    public int getWidth() {
        return this.a;
    }

    public int getHeight() {
        return this.b;
    }

    private Bitmap a(Bitmap bitmap, int i, int i2) {
        return dj.a(bitmap, i, i2);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.c, i);
        parcel.writeInt(this.a);
        parcel.writeInt(this.b);
    }

    public void recycle() {
        if (this.c != null && !this.c.isRecycled()) {
            this.c.recycle();
            this.c = null;
        }
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (this.c == null || this.c.isRecycled() || obj == null) {
            return z;
        }
        if (this == obj) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return z;
        }
        BitmapDescriptor bitmapDescriptor = (BitmapDescriptor) obj;
        if (bitmapDescriptor.c == null || bitmapDescriptor.c.isRecycled() || this.a != bitmapDescriptor.getWidth() || this.b != bitmapDescriptor.getHeight()) {
            return z;
        }
        try {
            return this.c.sameAs(bitmapDescriptor.c);
        } catch (Throwable th) {
            return z;
        }
    }
}

package com.amap.api.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.mapcore.util.cu;
import com.amap.api.mapcore.util.dg;
import com.amap.api.mapcore.util.dj;

public final class CameraPosition implements Parcelable {
    public static final CameraPositionCreator CREATOR = new CameraPositionCreator();
    public final float bearing;
    public final boolean isAbroad;
    public final LatLng target;
    public final float tilt;
    public final float zoom;

    public static final class Builder {
        private LatLng a;
        private float b;
        private float c;
        private float d;

        public Builder(CameraPosition cameraPosition) {
            target(cameraPosition.target).bearing(cameraPosition.bearing).tilt(cameraPosition.tilt).zoom(cameraPosition.zoom);
        }

        public Builder target(LatLng latLng) {
            this.a = latLng;
            return this;
        }

        public Builder zoom(float f) {
            this.b = f;
            return this;
        }

        public Builder tilt(float f) {
            this.c = f;
            return this;
        }

        public Builder bearing(float f) {
            this.d = f;
            return this;
        }

        public CameraPosition build() {
            cu.a(this.a);
            return new CameraPosition(this.a, this.b, this.c, this.d);
        }
    }

    public CameraPosition(LatLng latLng, float f, float f2, float f3) {
        cu.a((Object) latLng, (Object) "CameraPosition 位置不能为null ");
        this.target = latLng;
        this.zoom = dj.a(f);
        this.tilt = dj.a(f2, this.zoom);
        if (((double) f3) <= 0.0d) {
            f3 = (f3 % 360.0f) + 360.0f;
        }
        this.bearing = f3 % 360.0f;
        this.isAbroad = !dg.a(latLng.latitude, latLng.longitude);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.bearing);
        parcel.writeFloat((float) this.target.latitude);
        parcel.writeFloat((float) this.target.longitude);
        parcel.writeFloat(this.tilt);
        parcel.writeFloat(this.zoom);
    }

    public int describeContents() {
        return 0;
    }

    public int hashCode() {
        return super.hashCode();
    }

    public static final CameraPosition fromLatLngZoom(LatLng latLng, float f) {
        return new CameraPosition(latLng, f, 0.0f, 0.0f);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(CameraPosition cameraPosition) {
        return new Builder(cameraPosition);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CameraPosition)) {
            return false;
        }
        CameraPosition cameraPosition = (CameraPosition) obj;
        if (this.target.equals(cameraPosition.target) && Float.floatToIntBits(this.zoom) == Float.floatToIntBits(cameraPosition.zoom) && Float.floatToIntBits(this.tilt) == Float.floatToIntBits(cameraPosition.tilt) && Float.floatToIntBits(this.bearing) == Float.floatToIntBits(cameraPosition.bearing)) {
            return true;
        }
        return false;
    }

    public String toString() {
        return dj.a(dj.a("target", this.target), dj.a("zoom", Float.valueOf(this.zoom)), dj.a("tilt", Float.valueOf(this.tilt)), dj.a("bearing", Float.valueOf(this.bearing)));
    }
}

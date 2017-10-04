package com.amap.api.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.mapcore.util.cu;
import com.amap.api.mapcore.util.dj;

public final class LatLngBounds implements Parcelable {
    public static final LatLngBoundsCreator CREATOR = new LatLngBoundsCreator();
    private final int a;
    public final LatLng northeast;
    public final LatLng southwest;

    public static final class Builder {
        private double a = Double.POSITIVE_INFINITY;
        private double b = Double.NEGATIVE_INFINITY;
        private double c = Double.NaN;
        private double d = Double.NaN;

        public Builder include(LatLng latLng) {
            this.a = Math.min(this.a, latLng.latitude);
            this.b = Math.max(this.b, latLng.latitude);
            double d = latLng.longitude;
            if (Double.isNaN(this.c)) {
                this.c = d;
                this.d = d;
            } else if (!a(d)) {
                if (LatLngBounds.c(this.c, d) < LatLngBounds.d(this.d, d)) {
                    this.c = d;
                } else {
                    this.d = d;
                }
            }
            return this;
        }

        private boolean a(double d) {
            boolean z = false;
            if (this.c > this.d) {
                if (this.c <= d || d <= this.d) {
                    z = true;
                }
                return z;
            } else if (this.c > d || d > this.d) {
                return false;
            } else {
                return true;
            }
        }

        public LatLngBounds build() {
            boolean z;
            if (Double.isNaN(this.c)) {
                z = false;
            } else {
                z = true;
            }
            cu.a(z, (Object) "no included points");
            return new LatLngBounds(new LatLng(this.a, this.c, false), new LatLng(this.b, this.d, false));
        }
    }

    LatLngBounds(int i, LatLng latLng, LatLng latLng2) {
        cu.a((Object) latLng, (Object) "null southwest");
        cu.a((Object) latLng2, (Object) "null northeast");
        cu.a(latLng2.latitude >= latLng.latitude, "southern latitude exceeds northern latitude (%s > %s)", new Object[]{Double.valueOf(latLng.latitude), Double.valueOf(latLng2.latitude)});
        this.a = i;
        this.southwest = latLng;
        this.northeast = latLng2;
    }

    public LatLngBounds(LatLng latLng, LatLng latLng2) {
        this(1, latLng, latLng2);
    }

    int a() {
        return this.a;
    }

    public static Builder builder() {
        return new Builder();
    }

    public boolean contains(LatLng latLng) {
        return a(latLng.latitude) && b(latLng.longitude);
    }

    public boolean contains(LatLngBounds latLngBounds) {
        if (latLngBounds != null && contains(latLngBounds.southwest) && contains(latLngBounds.northeast)) {
            return true;
        }
        return false;
    }

    public boolean intersects(LatLngBounds latLngBounds) {
        if (latLngBounds == null) {
            return false;
        }
        if (a(latLngBounds) || latLngBounds.a(this)) {
            return true;
        }
        return false;
    }

    private boolean a(LatLngBounds latLngBounds) {
        if (latLngBounds == null || latLngBounds.northeast == null || latLngBounds.southwest == null || this.northeast == null || this.southwest == null) {
            return false;
        }
        double d = ((latLngBounds.northeast.latitude + latLngBounds.southwest.latitude) - this.northeast.latitude) - this.southwest.latitude;
        double d2 = ((this.northeast.latitude - this.southwest.latitude) + latLngBounds.northeast.latitude) - latLngBounds.southwest.latitude;
        if (Math.abs(((latLngBounds.northeast.longitude + latLngBounds.southwest.longitude) - this.northeast.longitude) - this.southwest.longitude) >= ((this.northeast.longitude - this.southwest.longitude) + latLngBounds.northeast.longitude) - this.southwest.longitude || Math.abs(d) >= d2) {
            return false;
        }
        return true;
    }

    public LatLngBounds including(LatLng latLng) {
        double d;
        double min = Math.min(this.southwest.latitude, latLng.latitude);
        double max = Math.max(this.northeast.latitude, latLng.latitude);
        double d2 = this.northeast.longitude;
        double d3 = this.southwest.longitude;
        double d4 = latLng.longitude;
        if (b(d4)) {
            d4 = d3;
            d = d2;
        } else if (c(d3, d4) < d(d2, d4)) {
            d = d2;
        } else {
            d = d4;
            d4 = d3;
        }
        return new LatLngBounds(new LatLng(min, d4, false), new LatLng(max, d, false));
    }

    private static double c(double d, double d2) {
        return ((d - d2) + 360.0d) % 360.0d;
    }

    private static double d(double d, double d2) {
        return ((d2 - d) + 360.0d) % 360.0d;
    }

    private boolean a(double d) {
        return this.southwest.latitude <= d && d <= this.northeast.latitude;
    }

    private boolean b(double d) {
        boolean z = false;
        if (this.southwest.longitude > this.northeast.longitude) {
            if (this.southwest.longitude <= d || d <= this.northeast.longitude) {
                z = true;
            }
            return z;
        } else if (this.southwest.longitude > d || d > this.northeast.longitude) {
            return false;
        } else {
            return true;
        }
    }

    public int hashCode() {
        return dj.a(new Object[]{this.southwest, this.northeast});
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LatLngBounds)) {
            return false;
        }
        LatLngBounds latLngBounds = (LatLngBounds) obj;
        if (this.southwest.equals(latLngBounds.southwest) && this.northeast.equals(latLngBounds.northeast)) {
            return true;
        }
        return false;
    }

    public String toString() {
        return dj.a(dj.a("southwest", this.southwest), dj.a("northeast", this.northeast));
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        LatLngBoundsCreator.a(this, parcel, i);
    }
}

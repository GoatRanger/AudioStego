package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzx;

public final class LatLngBounds implements SafeParcelable {
    public static final zzd CREATOR = new zzd();
    private final int mVersionCode;
    public final LatLng northeast;
    public final LatLng southwest;

    public static final class Builder {
        private double zzaKf = Double.POSITIVE_INFINITY;
        private double zzaKg = Double.NEGATIVE_INFINITY;
        private double zzaKh = Double.NaN;
        private double zzaKi = Double.NaN;

        private boolean zzi(double d) {
            boolean z = false;
            if (this.zzaKh <= this.zzaKi) {
                return this.zzaKh <= d && d <= this.zzaKi;
            } else {
                if (this.zzaKh <= d || d <= this.zzaKi) {
                    z = true;
                }
                return z;
            }
        }

        public LatLngBounds build() {
            zzx.zza(!Double.isNaN(this.zzaKh), "no included points");
            return new LatLngBounds(new LatLng(this.zzaKf, this.zzaKh), new LatLng(this.zzaKg, this.zzaKi));
        }

        public Builder include(LatLng latLng) {
            this.zzaKf = Math.min(this.zzaKf, latLng.latitude);
            this.zzaKg = Math.max(this.zzaKg, latLng.latitude);
            double d = latLng.longitude;
            if (Double.isNaN(this.zzaKh)) {
                this.zzaKh = d;
                this.zzaKi = d;
            } else if (!zzi(d)) {
                if (LatLngBounds.zzb(this.zzaKh, d) < LatLngBounds.zzc(this.zzaKi, d)) {
                    this.zzaKh = d;
                } else {
                    this.zzaKi = d;
                }
            }
            return this;
        }
    }

    LatLngBounds(int i, LatLng latLng, LatLng latLng2) {
        zzx.zzb(latLng, "null southwest");
        zzx.zzb(latLng2, "null northeast");
        zzx.zzb(latLng2.latitude >= latLng.latitude, "southern latitude exceeds northern latitude (%s > %s)", new Object[]{Double.valueOf(latLng.latitude), Double.valueOf(latLng2.latitude)});
        this.mVersionCode = i;
        this.southwest = latLng;
        this.northeast = latLng2;
    }

    public LatLngBounds(LatLng latLng, LatLng latLng2) {
        this(1, latLng, latLng2);
    }

    public static Builder builder() {
        return new Builder();
    }

    private static double zzb(double d, double d2) {
        return ((d - d2) + 360.0d) % 360.0d;
    }

    private static double zzc(double d, double d2) {
        return ((d2 - d) + 360.0d) % 360.0d;
    }

    private boolean zzh(double d) {
        return this.southwest.latitude <= d && d <= this.northeast.latitude;
    }

    private boolean zzi(double d) {
        boolean z = false;
        if (this.southwest.longitude <= this.northeast.longitude) {
            return this.southwest.longitude <= d && d <= this.northeast.longitude;
        } else {
            if (this.southwest.longitude <= d || d <= this.northeast.longitude) {
                z = true;
            }
            return z;
        }
    }

    public boolean contains(LatLng latLng) {
        return zzh(latLng.latitude) && zzi(latLng.longitude);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LatLngBounds)) {
            return false;
        }
        LatLngBounds latLngBounds = (LatLngBounds) obj;
        return this.southwest.equals(latLngBounds.southwest) && this.northeast.equals(latLngBounds.northeast);
    }

    public LatLng getCenter() {
        double d = (this.southwest.latitude + this.northeast.latitude) / 2.0d;
        double d2 = this.northeast.longitude;
        double d3 = this.southwest.longitude;
        return new LatLng(d, d3 <= d2 ? (d2 + d3) / 2.0d : ((d2 + 360.0d) + d3) / 2.0d);
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return zzw.hashCode(new Object[]{this.southwest, this.northeast});
    }

    public LatLngBounds including(LatLng latLng) {
        double min = Math.min(this.southwest.latitude, latLng.latitude);
        double max = Math.max(this.northeast.latitude, latLng.latitude);
        double d = this.northeast.longitude;
        double d2 = this.southwest.longitude;
        double d3 = latLng.longitude;
        if (zzi(d3)) {
            d3 = d2;
            d2 = d;
        } else if (zzb(d2, d3) < zzc(d, d3)) {
            d2 = d;
        } else {
            double d4 = d2;
            d2 = d3;
            d3 = d4;
        }
        return new LatLngBounds(new LatLng(min, d3), new LatLng(max, d2));
    }

    public String toString() {
        return zzw.zzv(this).zzg("southwest", this.southwest).zzg("northeast", this.northeast).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzd.zza(this, parcel, i);
    }
}

package com.here.android.mpa.ar;

import android.annotation.TargetApi;
import com.nokia.maps.ARPoseReadingImpl;
import com.nokia.maps.am;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.k;

@TargetApi(14)
@HybridPlus
public final class ARPoseReading {
    private ARPoseReadingImpl a;

    private ARPoseReading(ARPoseReadingImpl aRPoseReadingImpl) {
        this.a = aRPoseReadingImpl;
    }

    public double getLatitude() {
        return this.a.getLatitude();
    }

    public double getLongitude() {
        return this.a.getLongitude();
    }

    public float getAltitude() {
        return this.a.getAltitude();
    }

    public float getPitch() {
        return this.a.a();
    }

    public float getHeading() {
        return this.a.b();
    }

    public float getRoll() {
        return this.a.c();
    }

    public long getTimestamp() {
        return this.a.getTimestamp();
    }

    public int hashCode() {
        return (this.a == null ? 0 : this.a.hashCode()) + 31;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ARPoseReading aRPoseReading = (ARPoseReading) obj;
        if (this.a == null) {
            if (aRPoseReading.a != null) {
                return false;
            }
            return true;
        } else if (this.a.equals(aRPoseReading.a)) {
            return true;
        } else {
            return false;
        }
    }

    static {
        ARPoseReadingImpl.a(new k<ARPoseReading, ARPoseReadingImpl>() {
            public ARPoseReadingImpl a(ARPoseReading aRPoseReading) {
                return aRPoseReading.a;
            }
        }, new am<ARPoseReading, ARPoseReadingImpl>() {
            public ARPoseReading a(ARPoseReadingImpl aRPoseReadingImpl) {
                if (aRPoseReadingImpl != null) {
                    return new ARPoseReading(aRPoseReadingImpl);
                }
                return null;
            }
        });
    }
}

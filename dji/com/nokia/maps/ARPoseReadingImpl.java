package com.nokia.maps;

import com.here.android.mpa.ar.ARPoseReading;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;

@HybridPlus
public class ARPoseReadingImpl extends BaseNativeObject {
    private static am<ARPoseReading, ARPoseReadingImpl> a = null;
    private static k<ARPoseReading, ARPoseReadingImpl> b = null;

    private native void createNative(float f, float f2, float f3, double d, double d2, double d3, long j);

    private native void destroyNative();

    public native float getAltitude();

    public native double getLatitude();

    public native double getLongitude();

    public native float getRotationX();

    public native float getRotationY();

    public native float getRotationZ();

    public native long getTimestamp();

    native void setAltitude(double d);

    native void setLatitude(double d);

    native void setLongitude(double d);

    native void setRotationX(float f);

    native void setRotationY(float f);

    native void setRotationZ(float f);

    native void setTimestamp(long j);

    static {
        ce.a(ARPoseReading.class);
    }

    @HybridPlusNative
    ARPoseReadingImpl(int i) {
        this.nativeptr = i;
    }

    public static void a(k<ARPoseReading, ARPoseReadingImpl> kVar, am<ARPoseReading, ARPoseReadingImpl> amVar) {
        b = kVar;
        a = amVar;
    }

    static ARPoseReadingImpl a(ARPoseReading aRPoseReading) {
        return (ARPoseReadingImpl) b.a(aRPoseReading);
    }

    static ARPoseReading a(ARPoseReadingImpl aRPoseReadingImpl) {
        return aRPoseReadingImpl != null ? (ARPoseReading) a.a(aRPoseReadingImpl) : null;
    }

    void b(ARPoseReadingImpl aRPoseReadingImpl) {
        setAltitude((double) aRPoseReadingImpl.getAltitude());
        setLatitude(aRPoseReadingImpl.getLatitude());
        setLongitude(aRPoseReadingImpl.getLongitude());
        setRotationX(aRPoseReadingImpl.getRotationX());
        setRotationY(aRPoseReadingImpl.getRotationY());
        setRotationZ(aRPoseReadingImpl.getRotationZ());
        setTimestamp(aRPoseReadingImpl.getTimestamp());
    }

    protected void finalize() {
        destroyNative();
    }

    public float a() {
        return getRotationX();
    }

    public float b() {
        return getRotationY();
    }

    public float c() {
        return getRotationZ();
    }

    public int hashCode() {
        return this.nativeptr + 31;
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
        if (this.nativeptr != ((ARPoseReadingImpl) obj).nativeptr) {
            return false;
        }
        return true;
    }
}

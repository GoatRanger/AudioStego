package com.here.posclient;

public class CrowdsourcingTest {
    public static native void dumpFingerprints();

    public static native boolean getActiveCollection();

    public static native boolean getAutoUpload();

    public static native boolean getCollectionStatus();

    public static native long getGnssFingerprintCount();

    public static native long getNonGnssFingerprintCount();

    public static native void sendFingerprints();

    public static native boolean setActiveCollection(boolean z);

    public static native boolean setAutoUpload(boolean z);

    public static native void setUsername(String str);

    private CrowdsourcingTest() {
    }
}

package com.here.posclient.ext;

import com.here.posclient.PositionEstimate;

public class PositioningControl {
    public static native long availableFeatures();

    public static native void dumpCachedData();

    public static native int handleGlobalLocationSettingChanged(boolean z);

    public static native boolean isFeatureUsableHere(long j, PositionEstimate positionEstimate);

    public static native boolean isNetworkingEnabled();

    public static native int setNetworkingEnabled(boolean z);

    public static native void setWorkingRadioMapPath(String str);

    public static native int toggleFeature(long j, boolean z);

    static {
        System.loadLibrary("posclient");
    }

    private PositioningControl() {
    }
}

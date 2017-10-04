package dji.sdksharedlib.hardware.abstractions.e;

import dji.common.gimbal.DJIGimbalCalibrationState;

/* synthetic */ class h$2 {
    static final /* synthetic */ int[] a = new int[DJIGimbalCalibrationState.values().length];

    static {
        try {
            a[DJIGimbalCalibrationState.Default.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            a[DJIGimbalCalibrationState.Start.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            a[DJIGimbalCalibrationState.Fail.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            a[DJIGimbalCalibrationState.Success.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
    }
}

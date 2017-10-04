package dji.pilot.active;

import dji.midware.data.config.P3.DeviceType;

/* synthetic */ class DJIActiveController$5 {
    static final /* synthetic */ int[] a = new int[DeviceType.values().length];
    static final /* synthetic */ int[] b = new int[DJIActiveController$a.values().length];

    static {
        try {
            b[DJIActiveController$a.NoStart.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            b[DJIActiveController$a.ServerBackFail.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            b[DJIActiveController$a.ServerNoAvailableDevices.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            b[DJIActiveController$a.LocalMcFail.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
        try {
            b[DJIActiveController$a.NoConnectMc.ordinal()] = 5;
        } catch (NoSuchFieldError e5) {
        }
        try {
            b[DJIActiveController$a.Noerror.ordinal()] = 6;
        } catch (NoSuchFieldError e6) {
        }
        try {
            a[DeviceType.FLYC.ordinal()] = 1;
        } catch (NoSuchFieldError e7) {
        }
        try {
            a[DeviceType.CAMERA.ordinal()] = 2;
        } catch (NoSuchFieldError e8) {
        }
        try {
            a[DeviceType.BATTERY.ordinal()] = 3;
        } catch (NoSuchFieldError e9) {
        }
        try {
            a[DeviceType.OFDM.ordinal()] = 4;
        } catch (NoSuchFieldError e10) {
        }
        try {
            a[DeviceType.OSD.ordinal()] = 5;
        } catch (NoSuchFieldError e11) {
        }
        try {
            a[DeviceType.GLASS.ordinal()] = 6;
        } catch (NoSuchFieldError e12) {
        }
        try {
            a[DeviceType.GIMBAL.ordinal()] = 7;
        } catch (NoSuchFieldError e13) {
        }
    }
}

package dji.sdksharedlib.hardware.abstractions.a.a;

import dji.midware.data.model.P3.DataOsdGetPushWirelessState.SdrWirelessState;

/* synthetic */ class e$4 {
    static final /* synthetic */ int[] a = new int[SdrWirelessState.values().length];

    static {
        try {
            a[SdrWirelessState.STRONG_DISTURBANCE.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            a[SdrWirelessState.VIDEO_DISTURBANCE.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            a[SdrWirelessState.RC_DISTURBANCE.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            a[SdrWirelessState.LOW_SIGNAL_POWER.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
        try {
            a[SdrWirelessState.CUSTOM_SIGNAL_DISTURBANCE.ordinal()] = 5;
        } catch (NoSuchFieldError e5) {
        }
        try {
            a[SdrWirelessState.RC_TO_GLASS_DIST.ordinal()] = 6;
        } catch (NoSuchFieldError e6) {
        }
        try {
            a[SdrWirelessState.UAV_HAL_RESTART.ordinal()] = 7;
        } catch (NoSuchFieldError e7) {
        }
        try {
            a[SdrWirelessState.GLASS_DIST_RC_ANTENNA.ordinal()] = 8;
        } catch (NoSuchFieldError e8) {
        }
        try {
            a[SdrWirelessState.DISCONNECT_RC_DISTURB.ordinal()] = 9;
        } catch (NoSuchFieldError e9) {
        }
        try {
            a[SdrWirelessState.DISCONNECT_UAV_DISTURB.ordinal()] = 10;
        } catch (NoSuchFieldError e10) {
        }
        try {
            a[SdrWirelessState.DISCONNECT_WEEK_SIGNAL.ordinal()] = 11;
        } catch (NoSuchFieldError e11) {
        }
        try {
            a[SdrWirelessState.INTERNAL_EVENT.ordinal()] = 12;
        } catch (NoSuchFieldError e12) {
        }
    }
}

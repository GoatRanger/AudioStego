package dji.sdksharedlib.hardware.abstractions.h;

import dji.common.remotecontroller.DJIRCControlStyle;
import dji.midware.data.model.P3.DataRcGetPushFollowFocus.CtrlDirection;
import dji.midware.data.model.P3.DataRcGetPushFollowFocus.CtrlType;

/* synthetic */ class a$28 {
    static final /* synthetic */ int[] a = new int[DJIRCControlStyle.values().length];
    static final /* synthetic */ int[] b = new int[CtrlType.values().length];
    static final /* synthetic */ int[] c = new int[CtrlDirection.values().length];

    static {
        try {
            c[CtrlDirection.CW.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            c[CtrlDirection.CCW.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            c[CtrlDirection.OTHER.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            b[CtrlType.APERTURE.ordinal()] = 1;
        } catch (NoSuchFieldError e4) {
        }
        try {
            b[CtrlType.FOCAL_LENGTH.ordinal()] = 2;
        } catch (NoSuchFieldError e5) {
        }
        try {
            b[CtrlType.OTHER.ordinal()] = 3;
        } catch (NoSuchFieldError e6) {
        }
        try {
            a[DJIRCControlStyle.SlaveCustom.ordinal()] = 1;
        } catch (NoSuchFieldError e7) {
        }
        try {
            a[DJIRCControlStyle.SlaveDefault.ordinal()] = 2;
        } catch (NoSuchFieldError e8) {
        }
    }
}

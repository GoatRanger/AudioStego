package dji.sdksharedlib.hardware.abstractions.c;

import dji.common.camera.DJICameraSettingsDef.CameraContrast;
import dji.common.camera.DJICameraSettingsDef.CameraSharpness;
import dji.common.camera.DJICameraSettingsDef.CameraVideoFrameRate;
import dji.common.camera.DJICameraSettingsDef.CameraVideoResolution;

/* synthetic */ class b$68 {
    static final /* synthetic */ int[] a = new int[CameraVideoResolution.values().length];
    static final /* synthetic */ int[] b = new int[CameraVideoFrameRate.values().length];
    static final /* synthetic */ int[] c = new int[CameraSharpness.values().length];
    static final /* synthetic */ int[] d = new int[CameraContrast.values().length];

    static {
        try {
            d[CameraContrast.Standard.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            d[CameraContrast.Hard.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            d[CameraContrast.Soft.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            c[CameraSharpness.Standard.ordinal()] = 1;
        } catch (NoSuchFieldError e4) {
        }
        try {
            c[CameraSharpness.Hard.ordinal()] = 2;
        } catch (NoSuchFieldError e5) {
        }
        try {
            c[CameraSharpness.Soft.ordinal()] = 3;
        } catch (NoSuchFieldError e6) {
        }
        try {
            b[CameraVideoFrameRate.FrameRate_24fps.ordinal()] = 1;
        } catch (NoSuchFieldError e7) {
        }
        try {
            b[CameraVideoFrameRate.FrameRate_25fps.ordinal()] = 2;
        } catch (NoSuchFieldError e8) {
        }
        try {
            b[CameraVideoFrameRate.FrameRate_30fps.ordinal()] = 3;
        } catch (NoSuchFieldError e9) {
        }
        try {
            b[CameraVideoFrameRate.FrameRate_48fps.ordinal()] = 4;
        } catch (NoSuchFieldError e10) {
        }
        try {
            b[CameraVideoFrameRate.FrameRate_50fps.ordinal()] = 5;
        } catch (NoSuchFieldError e11) {
        }
        try {
            b[CameraVideoFrameRate.FrameRate_60fps.ordinal()] = 6;
        } catch (NoSuchFieldError e12) {
        }
        try {
            b[CameraVideoFrameRate.FrameRate_120fps.ordinal()] = 7;
        } catch (NoSuchFieldError e13) {
        }
        try {
            a[CameraVideoResolution.Resolution_1280x720.ordinal()] = 1;
        } catch (NoSuchFieldError e14) {
        }
        try {
            a[CameraVideoResolution.Resolution_1920x1080.ordinal()] = 2;
        } catch (NoSuchFieldError e15) {
        }
        try {
            a[CameraVideoResolution.Resolution_2704X1520.ordinal()] = 3;
        } catch (NoSuchFieldError e16) {
        }
        try {
            a[CameraVideoResolution.Resolution_3840x2160.ordinal()] = 4;
        } catch (NoSuchFieldError e17) {
        }
        try {
            a[CameraVideoResolution.Resolution_4096x2160.ordinal()] = 5;
        } catch (NoSuchFieldError e18) {
        }
    }
}

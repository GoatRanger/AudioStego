package dji.pilot.fpv.activity;

import dji.common.camera.DJICameraSettingsDef.CameraOrientation;
import dji.midware.data.config.P3.ProductType;

/* synthetic */ class DJIPreviewActivityKumquat$6 {
    static final /* synthetic */ int[] a = new int[ProductType.values().length];
    static final /* synthetic */ int[] b = new int[CameraOrientation.values().length];

    static {
        try {
            b[CameraOrientation.Landscape.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            b[CameraOrientation.Portrait.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            a[ProductType.KumquatS.ordinal()] = 1;
        } catch (NoSuchFieldError e3) {
        }
        try {
            a[ProductType.KumquatX.ordinal()] = 2;
        } catch (NoSuchFieldError e4) {
        }
    }
}

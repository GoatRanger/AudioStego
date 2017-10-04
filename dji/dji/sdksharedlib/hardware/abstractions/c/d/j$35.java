package dji.sdksharedlib.hardware.abstractions.c.d;

import dji.common.camera.DJICameraSettingsDef.CameraPhotoFileFormat;
import dji.common.camera.DJICameraSettingsDef.CameraThermalDigitalZoomScale;
import dji.midware.data.model.P3.DataCameraGetStateInfo.SDCardState;

/* synthetic */ class j$35 {
    static final /* synthetic */ int[] a = new int[CameraThermalDigitalZoomScale.values().length];
    static final /* synthetic */ int[] b = new int[SDCardState.values().length];
    static final /* synthetic */ int[] c = new int[CameraPhotoFileFormat.values().length];

    static {
        try {
            c[CameraPhotoFileFormat.JPEG.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            c[CameraPhotoFileFormat.TIFF14Bit.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            c[CameraPhotoFileFormat.RadiometricJPEG.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            c[CameraPhotoFileFormat.TIFF14BitLinearLowTempResolution.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
        try {
            c[CameraPhotoFileFormat.TIFF14BitLinearHighTempResolution.ordinal()] = 5;
        } catch (NoSuchFieldError e5) {
        }
        try {
            b[SDCardState.Normal.ordinal()] = 1;
        } catch (NoSuchFieldError e6) {
        }
        try {
            b[SDCardState.Full.ordinal()] = 2;
        } catch (NoSuchFieldError e7) {
        }
        try {
            b[SDCardState.None.ordinal()] = 3;
        } catch (NoSuchFieldError e8) {
        }
        try {
            a[CameraThermalDigitalZoomScale.x1.ordinal()] = 1;
        } catch (NoSuchFieldError e9) {
        }
        try {
            a[CameraThermalDigitalZoomScale.x2.ordinal()] = 2;
        } catch (NoSuchFieldError e10) {
        }
        try {
            a[CameraThermalDigitalZoomScale.x4.ordinal()] = 3;
        } catch (NoSuchFieldError e11) {
        }
        try {
            a[CameraThermalDigitalZoomScale.x8.ordinal()] = 4;
        } catch (NoSuchFieldError e12) {
        }
    }
}

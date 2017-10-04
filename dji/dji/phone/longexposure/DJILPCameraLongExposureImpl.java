package dji.phone.longexposure;

import android.graphics.Bitmap;

public class DJILPCameraLongExposureImpl implements a {
    protected static final native void blendAverage(Bitmap bitmap, int[] iArr, int i);

    protected static final native void blendMax1(Bitmap bitmap, int[] iArr);

    protected static final native void blendMax2(Bitmap bitmap, int[] iArr);

    protected static final native void blendScreen(Bitmap bitmap, int[] iArr);

    protected static final native void blendScreenTranslucence(Bitmap bitmap, int[] iArr, int i);

    protected static final native void blendTranslucence(Bitmap bitmap, int[] iArr, int i);

    protected static final native void blenderInitialize(Bitmap bitmap, int i);

    protected static final native void blenderUninitialize();

    protected static final native void decodeYUV420SP(int[] iArr, byte[] bArr, int i, int i2);

    public void decodeYUV420SPtoRGB(int[] iArr, byte[] bArr, int i, int i2) {
        decodeYUV420SP(iArr, bArr, i, i2);
    }

    public void blenderInit(Bitmap bitmap, int i) {
        blenderInitialize(bitmap, i);
    }

    public void blenderUninit() {
        blenderUninitialize();
    }

    public void blendUseAverage(Bitmap bitmap, int[] iArr, int i) {
        blendAverage(bitmap, iArr, i);
    }

    public void blendUseMax1(Bitmap bitmap, int[] iArr) {
    }

    public void blendUseMax2(Bitmap bitmap, int[] iArr) {
    }

    public void blendUseScreen(Bitmap bitmap, int[] iArr) {
    }

    public void blendUseTranslucence(Bitmap bitmap, int[] iArr, int i) {
    }

    public void blendUseScreenTranslucence(Bitmap bitmap, int[] iArr, int i) {
    }

    static {
        System.loadLibrary("ImageJni");
    }
}

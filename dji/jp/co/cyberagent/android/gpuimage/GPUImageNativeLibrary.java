package jp.co.cyberagent.android.gpuimage;

public class GPUImageNativeLibrary {
    public static native int RGBA2ARGB(int[] iArr);

    public static native int RGBA2RGB(int[] iArr);

    public static native void YUVtoARBG(byte[] bArr, int i, int i2, int[] iArr);

    public static native void YUVtoRBGA(byte[] bArr, int i, int i2, int[] iArr);

    static {
        System.loadLibrary("gpuimage-library");
    }
}

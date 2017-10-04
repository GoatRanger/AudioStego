package dji.phone.tracking;

public class DJINativeTracking {
    private static final String a = "track";

    public static native int areaInit(byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, int i7);

    public static native void release();

    public static native int tapInit(byte[] bArr, int i, int i2, int i3, int i4, int i5);

    public static native int track(byte[] bArr, int i, int[] iArr);

    static {
        System.loadLibrary("DJITK");
    }
}

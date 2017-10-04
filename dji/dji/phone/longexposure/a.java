package dji.phone.longexposure;

import android.graphics.Bitmap;

public interface a {
    public static final int a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 4;
    public static final int f = 5;

    void blendUseAverage(Bitmap bitmap, int[] iArr, int i);

    void blendUseMax1(Bitmap bitmap, int[] iArr);

    void blendUseMax2(Bitmap bitmap, int[] iArr);

    void blendUseScreen(Bitmap bitmap, int[] iArr);

    void blendUseScreenTranslucence(Bitmap bitmap, int[] iArr, int i);

    void blendUseTranslucence(Bitmap bitmap, int[] iArr, int i);

    void blenderInit(Bitmap bitmap, int i);

    void blenderUninit();

    void decodeYUV420SPtoRGB(int[] iArr, byte[] bArr, int i, int i2);
}

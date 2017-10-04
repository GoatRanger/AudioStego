package dji.pilot2.utils;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.support.v4.view.MotionEventCompat;

public class b {
    private static float a = 10.0f;
    private static float b = 10.0f;
    private static int c = 7;

    public static Bitmap a(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] iArr = new int[(width * height)];
        int[] iArr2 = new int[(width * height)];
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        for (int i = 0; i < c; i++) {
            a(iArr, iArr2, width, height, a);
            a(iArr2, iArr, height, width, b);
        }
        b(iArr, iArr2, width, height, a);
        b(iArr2, iArr, height, width, b);
        createBitmap.setPixels(iArr, 0, width, 0, 0, width, height);
        return createBitmap;
    }

    public static void a(int[] iArr, int[] iArr2, int i, int i2, float f) {
        int i3;
        int i4 = i - 1;
        int i5 = (int) f;
        int i6 = (i5 * 2) + 1;
        int[] iArr3 = new int[(i6 * 256)];
        for (i3 = 0; i3 < i6 * 256; i3++) {
            iArr3[i3] = i3 / i6;
        }
        int i7 = 0;
        int i8 = 0;
        while (i7 < i2) {
            int i9;
            int i10 = 0;
            int i11 = 0;
            int i12 = 0;
            i6 = 0;
            for (i3 = -i5; i3 <= i5; i3++) {
                i9 = iArr[a(i3, 0, i - 1) + i8];
                i10 += (i9 >> 24) & 255;
                i11 += (i9 >> 16) & 255;
                i12 += (i9 >> 8) & 255;
                i6 += i9 & 255;
            }
            i9 = i11;
            int i13 = i10;
            int i14 = i7;
            i11 = i6;
            i10 = i12;
            i12 = 0;
            while (i12 < i) {
                iArr2[i14] = (((iArr3[i13] << 24) | (iArr3[i9] << 16)) | (iArr3[i10] << 8)) | iArr3[i11];
                i3 = (i12 + i5) + 1;
                if (i3 > i4) {
                    i6 = i4;
                } else {
                    i6 = i3;
                }
                i3 = i12 - i5;
                if (i3 < 0) {
                    i3 = 0;
                }
                i6 = iArr[i6 + i8];
                i3 = iArr[i3 + i8];
                i13 += ((i6 >> 24) & 255) - ((i3 >> 24) & 255);
                i9 += ((16711680 & i6) - (16711680 & i3)) >> 16;
                i10 += ((MotionEventCompat.ACTION_POINTER_INDEX_MASK & i6) - (MotionEventCompat.ACTION_POINTER_INDEX_MASK & i3)) >> 8;
                i12++;
                i14 += i2;
                i11 += (i6 & 255) - (i3 & 255);
            }
            i7++;
            i8 += i;
        }
    }

    public static void b(int[] iArr, int[] iArr2, int i, int i2, float f) {
        float f2 = f - ((float) ((int) f));
        float f3 = 1.0f / (1.0f + (2.0f * f2));
        int i3 = 0;
        int i4 = 0;
        while (i3 < i2) {
            iArr2[i3] = iArr[0];
            int i5 = i3 + i2;
            for (int i6 = 1; i6 < i - 1; i6++) {
                int i7 = i4 + i6;
                int i8 = iArr[i7 - 1];
                int i9 = iArr[i7];
                i7 = iArr[i7 + 1];
                int i10 = (i7 >> 24) & 255;
                int i11 = (i7 >> 16) & 255;
                int i12 = (int) (((float) (((i8 >> 8) & 255) + ((i7 >> 8) & 255))) * f2);
                i7 = ((int) (((float) ((i7 & 255) + (i8 & 255))) * f2)) + (i9 & 255);
                iArr2[i5] = ((int) (((float) i7) * f3)) | (((((int) (((float) (((int) (((float) (((i8 >> 24) & 255) + i10)) * f2)) + ((i9 >> 24) & 255))) * f3)) << 24) | (((int) (((float) (((int) (((float) (((i8 >> 16) & 255) + i11)) * f2)) + ((i9 >> 16) & 255))) * f3)) << 16)) | (((int) (((float) (i12 + ((i9 >> 8) & 255))) * f3)) << 8));
                i5 += i2;
            }
            iArr2[i5] = iArr[i - 1];
            i3++;
            i4 += i;
        }
    }

    public static int a(int i, int i2, int i3) {
        if (i < i2) {
            return i2;
        }
        return i > i3 ? i3 : i;
    }
}

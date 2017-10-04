package dji.midware.media.b;

import dji.midware.media.e;
import dji.midware.natives.FPVController;

public class b {
    public static a a(int i, int i2) {
        a aVar = null;
        if (i == 21 && i2 == 19) {
            aVar = new a() {
                public void a(byte[] bArr, byte[] bArr2, int i, int i2) {
                    int i3 = 0;
                    int i4 = i * i2;
                    int i5 = i4 / 4;
                    System.arraycopy(bArr, 0, bArr2, 0, i * i2);
                    while (i3 < i5) {
                        bArr2[i4 + i3] = bArr[(i3 * 2) + i4];
                        bArr2[(i4 + i5) + i3] = bArr[((i3 * 2) + i4) + 1];
                        i3++;
                    }
                }
            };
        } else if (i == 21 && i2 == 19) {
        }
        if (aVar == null) {
            e.a(new Exception("color convertor missing:" + i + "->" + i2));
        }
        return aVar;
    }

    public static byte[] a(byte[] bArr, byte[] bArr2, int i, int i2) {
        int i3 = 0;
        int i4 = i * i2;
        int i5 = i4 / 4;
        System.arraycopy(bArr, 0, bArr2, 0, i4);
        while (i3 < i5) {
            bArr2[(i3 * 2) + i4] = bArr[(i4 + i3) + i5];
            bArr2[((i3 * 2) + i4) + 1] = bArr[i4 + i3];
            i3++;
        }
        return bArr2;
    }

    public static byte[] b(byte[] bArr, byte[] bArr2, int i, int i2) {
        int i3 = i * i2;
        int i4 = i3 / 4;
        System.arraycopy(bArr, 0, bArr2, 0, i3);
        System.arraycopy(bArr, i3, bArr2, i3 + i4, i4);
        System.arraycopy(bArr, i3 + i4, bArr2, i3, i4);
        return bArr2;
    }

    public static void c(byte[] bArr, byte[] bArr2, int i, int i2) {
        int i3;
        for (i3 = 0; i3 < i * i2; i3++) {
            bArr2[i3] = bArr[i3];
        }
        for (i3 = i * i2; i3 < (i * i2) + (((i / 2) * i2) / 2); i3++) {
            bArr2[i3] = bArr[(((i / 2) * i2) / 2) + i3];
        }
        for (i3 = (i * i2) + (((i / 2) * i2) / 2); i3 < (i * i2) + ((((i / 2) * i2) / 2) * 2); i3++) {
            bArr2[i3] = bArr[i3 - (((i / 2) * i2) / 2)];
        }
    }

    public static int a(Object obj, Object obj2, int i, int i2, int i3) {
        return FPVController.native_transcodeYUVConvert(obj, obj2, i, i2, i3);
    }
}

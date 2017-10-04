package dji.midware.util;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import dji.midware.natives.GroudStation;
import dji.thirdparty.g.b.b.a.f;
import dji.thirdparty.g.b.b.e;
import java.io.IOException;

public class g {
    public static Bitmap a(byte[] bArr, int i, int i2) {
        int i3 = i + 2;
        if (c.f(bArr, i) != 55551) {
            return null;
        }
        int f;
        int f2;
        while (i3 < bArr.length) {
            f = c.f(bArr, i3);
            i3 += 2;
            f2 = c.f(bArr, i3);
            i3 += 2;
            if (f == 57855) {
                break;
            }
            i3 += f2;
            f = c.f(bArr, i3);
            i3 += 2;
            if (55807 == f) {
                return null;
            }
        }
        f = c.f(bArr, i3);
        i3 += 2;
        f2 = c.f(bArr, i3);
        i3 += 2;
        int i4 = i3 + 2;
        if (((c.f(bArr, i3) != 0 ? 1 : 0) | ((f != 30789 ? 1 : 0) | (f2 != 26217 ? 1 : 0))) != 0) {
            return null;
        }
        i3 = i4 + 4;
        int i5 = 0;
        int i6 = 0;
        Object obj = null;
        Object obj2 = null;
        while (true) {
            if (obj != null && obj2 != null) {
                break;
            }
            int b = c.b(bArr, i3);
            i3 += 4;
            i3 = i4 + b;
            int f3 = c.f(bArr, i3);
            b = i3 + 2;
            Object obj3 = obj2;
            obj2 = obj;
            f2 = i6;
            i6 = i5;
            i5 = b;
            for (int i7 = 0; i7 < f3; i7++) {
                int f4 = c.f(bArr, i5);
                i5 += 2;
                c.f(bArr, i5);
                i5 += 2;
                c.b(bArr, i5);
                i5 += 4;
                b = c.b(bArr, i5);
                i5 += 4;
                if (f4 == 513) {
                    obj3 = 1;
                    f2 = b;
                } else if (f4 == 514) {
                    obj2 = 1;
                    i6 = b;
                }
                if (obj2 != null && r0 != null) {
                    break;
                }
            }
            Object obj4 = obj3;
            i3 = i5;
            i5 = i6;
            i6 = f2;
            obj = obj2;
            obj2 = obj4;
        }
        i3 = i4 + i6;
        if (i5 <= 0) {
            return null;
        }
        if (i3 + i5 > i2) {
            return null;
        }
        Options options = new Options();
        options.inPreferredConfig = Config.RGB_565;
        options.inSampleSize = 1;
        return BitmapFactory.decodeByteArray(bArr, i3, i5, options);
    }

    public static Bitmap b(byte[] bArr, int i, int i2) {
        int i3 = i + 4;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (true) {
            if ((i5 == 0 || r0 == 0) && i3 + 18 < i2 - i) {
                int b = c.b(bArr, i3);
                i3 += 4;
                i3 = i + b;
                int f = c.f(bArr, i3);
                i3 += 2;
                for (int i8 = 0; i8 < f; i8++) {
                    int f2 = c.f(bArr, i3);
                    i3 += 2;
                    c.f(bArr, i3);
                    i3 += 2;
                    c.b(bArr, i3);
                    i3 += 4;
                    b = c.b(bArr, i3);
                    i3 += 4;
                    if (f2 == 273) {
                        i4 = 1;
                        i6 = b;
                    } else if (f2 == 279) {
                        i5 = 1;
                        i7 = b;
                    }
                    if (i5 != 0 && r0 != 0) {
                        break;
                    }
                }
            }
        }
        if (i5 == 0 || r0 == 0) {
            return null;
        }
        i4 = i + i6;
        if (i7 <= 0) {
            return null;
        }
        if (i4 + i7 > i2) {
            return null;
        }
        Options options = new Options();
        options.inPreferredConfig = Config.RGB_565;
        options.inSampleSize = 1;
        return BitmapFactory.decodeByteArray(bArr, i4, i7, options);
    }

    public static Bitmap c(byte[] bArr, int i, int i2) {
        Options options = new Options();
        options.inPreferredConfig = Config.RGB_565;
        options.inSampleSize = 1;
        return BitmapFactory.decodeByteArray(bArr, i, i2, options);
    }

    public static Bitmap a(byte[] bArr) {
        return Bitmap.createBitmap(GroudStation.native_yuv422ToImage(bArr, 160, 120), 160, 120, Config.ARGB_8888);
    }

    public static Bitmap d(byte[] bArr, int i, int i2) {
        try {
            Object obj = new byte[i2];
            System.arraycopy(bArr, i, obj, 0, i2);
            dji.thirdparty.g.b.b.g gVar = (dji.thirdparty.g.b.b.g) dji.thirdparty.g.g.a(obj);
            e a = gVar.a(f.I);
            e a2 = gVar.a(f.J);
            e a3 = gVar.a(f.aO);
            e a4 = gVar.a(f.bb);
            int n = gVar.a(f.K).n() / 8;
            int n2 = a4.n();
            int[] iArr = new int[(n2 / n)];
            a(obj, a3.n(), n2, n, iArr);
            return Bitmap.createBitmap(iArr, a.n(), a2.n(), Config.ARGB_8888);
        } catch (dji.thirdparty.g.e e) {
            e.printStackTrace();
            return null;
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private static void a(byte[] bArr, int i, int i2, int i3, int[] iArr) {
        int i4 = 100000;
        int i5 = i + i2;
        int i6 = i;
        int i7 = 0;
        while (i6 < i5) {
            int i8 = (i6 - i) / i3;
            for (int i9 = 0; i9 < i3; i9++) {
                iArr[i8] = iArr[i8] + ((bArr[i6 + i9] & 255) << (i9 * 8));
            }
            if (i4 >= iArr[i8]) {
                i4 = iArr[i8];
            }
            if (i7 <= iArr[i8]) {
                i7 = iArr[i8];
            }
            i6 += i3;
        }
        float f = (((float) (i7 - i4)) * 1.0f) / 256.0f;
        for (i7 = 0; i7 < iArr.length; i7++) {
            iArr[i7] = Math.round(((float) (iArr[i7] - i4)) / f);
            iArr[i7] = ((iArr[i7] | (iArr[i7] << 8)) | (iArr[i7] << 16)) | -16777216;
        }
    }
}

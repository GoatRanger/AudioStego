package com.alipay.e.a.a.b.b;

import android.support.v4.media.TransportMediator;

public final class g {
    public static final byte[] a = e.a("7B726A5DDD72CBF8D1700FB6EB278AFD7559C40A3761E5A71614D0AC9461ED8EE9F6AAEB443CD648");
    public static final byte[] b = e.a("C9582A82777392CAA65AD7F5228150E3F966C09D6A00288B5C6E0CFB441E111B713B4E0822A8C830");
    public static final int c = 8;
    public static final int d = 20;

    private g() {
    }

    public static byte[] a(byte[] bArr) {
        Object obj = new byte[20];
        if (b.a(obj, (byte) 0, 0, 20)) {
            Object obj2 = new byte[20];
            if (b.a(obj2, (byte) 0, 0, 20)) {
                Object a = c.a(bArr, a);
                System.arraycopy(a, 0, obj, 0, a.length);
                a = c.a(bArr, b);
                System.arraycopy(a, 0, obj2, 0, a.length);
                r2 = new byte[8];
                int i = obj[19] & 15;
                r2[3] = (byte) (obj[i] & TransportMediator.KEYCODE_MEDIA_PAUSE);
                r2[2] = (byte) (obj[i + 1] & 255);
                r2[1] = (byte) (obj[i + 2] & 255);
                r2[0] = (byte) (obj[i + 3] & 255);
                int i2 = obj2[19] & 15;
                r2[4] = (byte) (obj2[i2] & TransportMediator.KEYCODE_MEDIA_PAUSE);
                r2[5] = (byte) (obj2[i2 + 1] & 255);
                r2[6] = (byte) (obj2[i2 + 2] & 255);
                r2[7] = (byte) (obj2[i2 + 3] & 255);
                return r2;
            }
            throw new IllegalStateException("failed to init hash2.");
        }
        throw new IllegalStateException("failed to init hash1.");
    }

    public static byte[] a(byte[] bArr, int i) {
        byte[] a = a(bArr);
        if (a == null || i <= 0) {
            return null;
        }
        if (i >= 8) {
            return a;
        }
        byte[] bArr2 = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            bArr2[i2] = a[i2];
        }
        return bArr2;
    }
}

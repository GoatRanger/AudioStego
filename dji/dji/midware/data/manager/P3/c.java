package dji.midware.data.manager.P3;

import dji.midware.data.config.P3.p;
import dji.midware.natives.GroudStation;

public class c {
    private static final int b = 8;
    private static final int c = 3;
    private static c d;
    private boolean a = true;

    public static synchronized c getInstance() {
        c cVar;
        synchronized (c.class) {
            if (d == null) {
                d = new c();
            }
            cVar = d;
        }
        return cVar;
    }

    public byte[] a(byte[] bArr, int i) {
        if (bArr == null) {
            return null;
        }
        int length = (bArr.length - 9) - 2;
        Object obj = new byte[length];
        System.arraycopy(bArr, 9, obj, 0, length);
        System.arraycopy(GroudStation.native_rcDataDeal(obj, i), 0, bArr, 9, length);
        return bArr;
    }

    public byte[] b(byte[] bArr, int i) {
        if (bArr == null) {
            return null;
        }
        int length = (bArr.length - 9) - 2;
        Object obj = new byte[length];
        System.arraycopy(bArr, 9, obj, 0, length);
        System.arraycopy(GroudStation.native_rcDataDeal(obj, i), 0, bArr, 9, length);
        return bArr;
    }

    public boolean a() {
        return this.a;
    }

    public void a(boolean z) {
        this.a = z;
    }

    public boolean a(byte[] bArr) {
        if ((dji.midware.util.c.a(bArr[8]) & 7) == 3) {
            return true;
        }
        return false;
    }

    public boolean b(byte[] bArr) {
        short a = dji.midware.util.c.a(bArr[9]);
        short a2 = dji.midware.util.c.a(bArr[10]);
        if (a == p.COMMON.a() || (a == p.CAMERA.a() && (a != p.CAMERA.a() || a2 == (short) 16 || a2 == (short) 17 || a2 == (short) 112 || a2 == (short) 113))) {
            return false;
        }
        return true;
    }

    public void c(byte[] bArr) {
        bArr[8] = (byte) (bArr[8] | 3);
    }
}

package dji.midware.data.a.a;

import dji.midware.data.b.a.a;
import dji.midware.natives.GroudStation;

public class c extends a {
    public static final int t = 1000;
    public static final int u = 2;
    public int v = 1000;
    public int w = 2;

    public void a() {
        int i;
        if (this.p == null) {
            i = 13;
        } else {
            i = this.p.length + 13;
        }
        this.c = i;
        byte[] bArr = new byte[this.c];
        bArr[0] = this.a;
        byte[] a = dji.midware.util.c.a((this.b << 10) | this.c);
        bArr[1] = a[0];
        bArr[2] = a[1];
        bArr[3] = GroudStation.native_calcCrc8(dji.midware.util.c.e(bArr, 0, 3));
        bArr[4] = (byte) ((this.e << 5) | this.f);
        bArr[5] = (byte) ((this.g << 5) | this.h);
        this.i = this.i == 0 ? a.getSeq() : this.i;
        a = dji.midware.util.c.a(this.i);
        bArr[6] = a[0];
        bArr[7] = a[1];
        bArr[8] = (byte) (((this.j << 7) | (this.k << 5)) | this.l);
        bArr[9] = (byte) this.m;
        bArr[10] = (byte) this.n;
        i = 11;
        if (this.p != null) {
            byte[] bArr2 = this.p;
            int length = bArr2.length;
            int i2 = 0;
            while (i2 < length) {
                bArr[i] = bArr2[i2];
                i2++;
                i++;
            }
        }
        byte[] b = dji.midware.util.c.b(GroudStation.native_calcCrc16(dji.midware.util.c.e(bArr, 0, this.c - 2)));
        bArr[i] = b[0];
        i++;
        bArr[i] = b[1];
        i++;
        this.r = bArr;
    }

    public String toString() {
        if (this.r != null) {
            return dji.midware.util.c.i(this.r);
        }
        return "";
    }

    public void b() {
        byte[] b = dji.midware.util.c.b(GroudStation.native_calcCrc16(dji.midware.util.c.e(this.r, 0, this.c - 2)));
        this.r[this.c - 2] = b[0];
        this.r[this.c - 1] = b[1];
    }
}

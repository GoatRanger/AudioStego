package dji.midware.data.a.a;

import com.google.android.gms.location.places.Place;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.p;
import dji.midware.util.c;

public class b extends a {
    public boolean t = true;
    byte[] u = new byte[4];
    public p v;
    private byte[] w = new byte[4];

    public b(byte[] bArr) {
        if (bArr != null && bArr.length >= 13) {
            this.r = bArr;
            this.a = bArr[0];
            short a = c.a(bArr, 1);
            this.b = a >> 10;
            this.c = a & Place.TYPE_SUBLOCALITY_LEVEL_1;
            this.d = bArr[3];
            this.e = a(4) >> 5;
            this.f = a(4) & 31;
            this.g = a(5) >> 5;
            this.h = a(5) & 31;
            this.w[0] = bArr[6];
            this.w[1] = bArr[7];
            this.i = c.b(this.w);
            this.j = a(8) >> 7;
            this.k = (a(8) >> 5) & 3;
            this.l = a(8) & 7;
            this.m = a(9);
            this.n = a(10);
            int i = 11;
            a();
            if (this.j == 1 && this.t) {
                this.o = a(11);
                i = 12;
            }
            int length = (bArr.length - i) - 2;
            if (length > 0) {
                this.p = new byte[length];
                System.arraycopy(bArr, i, this.p, 0, length);
            }
            this.u[0] = bArr[bArr.length - 2];
            this.u[1] = bArr[bArr.length - 1];
            this.q = c.b(this.w);
        }
    }

    private int a(int i) {
        return c.a(this.r[i]);
    }

    private void a() {
        try {
            this.v = p.find(this.m);
            this.t = this.v.b().c(this.n);
        } catch (Exception e) {
            e.printStackTrace();
            DJILogHelper.getInstance().LOGD(this.s, "cmdset=" + this.m);
        }
    }

    public String toString() {
        return c.i(this.r);
    }
}

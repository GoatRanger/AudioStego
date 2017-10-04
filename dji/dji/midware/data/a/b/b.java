package dji.midware.data.a.b;

import dji.midware.util.c;

public class b extends a {
    public int k;

    public b(byte[] bArr) {
        if (bArr != null && bArr.length >= 10) {
            this.j = bArr;
            this.a = bArr[0] >> 6;
            this.b = bArr[0] & 63;
            this.c = bArr[1] >> 5;
            this.d = bArr[1] & 31;
            short a = c.a(bArr, 2);
            this.e = a >> 12;
            this.f = a & 4095;
            this.g = c.f(bArr, 4);
            this.h = c.b(bArr, 6);
            this.k = bArr.length - this.b;
            if (this.k > 0) {
                this.i = new byte[this.k];
                System.arraycopy(bArr, this.b, this.i, 0, this.k);
            }
        }
    }

    public String toString() {
        return c.i(this.j);
    }
}

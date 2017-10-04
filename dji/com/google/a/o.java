package com.google.a;

public final class o extends j {
    private final byte[] a;
    private final int b;
    private final int c;
    private final int d;
    private final int e;

    public o(int i, int i2, int[] iArr) {
        super(i, i2);
        this.b = i;
        this.c = i2;
        this.d = 0;
        this.e = 0;
        this.a = new byte[(i * i2)];
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = i3 * i;
            for (int i5 = 0; i5 < i; i5++) {
                int i6 = iArr[i4 + i5];
                int i7 = (i6 >> 16) & 255;
                int i8 = (i6 >> 8) & 255;
                i6 &= 255;
                if (i7 == i8 && i8 == i6) {
                    this.a[i4 + i5] = (byte) i7;
                } else {
                    this.a[i4 + i5] = (byte) ((i6 + ((i7 + i8) + i8)) >> 2);
                }
            }
        }
    }

    private o(byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6) {
        super(i5, i6);
        if (i3 + i5 > i || i4 + i6 > i2) {
            throw new IllegalArgumentException("Crop rectangle does not fit within image data.");
        }
        this.a = bArr;
        this.b = i;
        this.c = i2;
        this.d = i3;
        this.e = i4;
    }

    public byte[] a(int i, byte[] bArr) {
        if (i < 0 || i >= h()) {
            throw new IllegalArgumentException("Requested row is outside the image: " + i);
        }
        int g = g();
        if (bArr == null || bArr.length < g) {
            bArr = new byte[g];
        }
        System.arraycopy(this.a, ((this.e + i) * this.b) + this.d, bArr, 0, g);
        return bArr;
    }

    public byte[] a() {
        int i = 0;
        int g = g();
        int h = h();
        if (g == this.b && h == this.c) {
            return this.a;
        }
        int i2 = g * h;
        byte[] bArr = new byte[i2];
        int i3 = (this.e * this.b) + this.d;
        if (g == this.b) {
            System.arraycopy(this.a, i3, bArr, 0, i2);
            return bArr;
        }
        Object obj = this.a;
        while (i < h) {
            System.arraycopy(obj, i3, bArr, i * g, g);
            i3 += this.b;
            i++;
        }
        return bArr;
    }

    public boolean b() {
        return true;
    }

    public j a(int i, int i2, int i3, int i4) {
        return new o(this.a, this.b, this.c, this.d + i, this.e + i2, i3, i4);
    }
}

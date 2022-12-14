package com.google.a;

public final class n extends j {
    private static final int a = 2;
    private final byte[] b;
    private final int c;
    private final int d;
    private final int e;
    private final int f;

    public n(byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, boolean z) {
        super(i5, i6);
        if (i3 + i5 > i || i4 + i6 > i2) {
            throw new IllegalArgumentException("Crop rectangle does not fit within image data.");
        }
        this.b = bArr;
        this.c = i;
        this.d = i2;
        this.e = i3;
        this.f = i4;
        if (z) {
            a(i5, i6);
        }
    }

    public byte[] a(int i, byte[] bArr) {
        if (i < 0 || i >= h()) {
            throw new IllegalArgumentException("Requested row is outside the image: " + i);
        }
        int g = g();
        if (bArr == null || bArr.length < g) {
            bArr = new byte[g];
        }
        System.arraycopy(this.b, ((this.f + i) * this.c) + this.e, bArr, 0, g);
        return bArr;
    }

    public byte[] a() {
        int i = 0;
        int g = g();
        int h = h();
        if (g == this.c && h == this.d) {
            return this.b;
        }
        int i2 = g * h;
        byte[] bArr = new byte[i2];
        int i3 = (this.f * this.c) + this.e;
        if (g == this.c) {
            System.arraycopy(this.b, i3, bArr, 0, i2);
            return bArr;
        }
        Object obj = this.b;
        while (i < h) {
            System.arraycopy(obj, i3, bArr, i * g, g);
            i3 += this.c;
            i++;
        }
        return bArr;
    }

    public boolean b() {
        return true;
    }

    public j a(int i, int i2, int i3, int i4) {
        return new n(this.b, this.c, this.d, this.e + i, this.f + i2, i3, i4, false);
    }

    public int[] i() {
        int g = g() / 2;
        int h = h() / 2;
        int[] iArr = new int[(g * h)];
        byte[] bArr = this.b;
        int i = (this.f * this.c) + this.e;
        for (int i2 = 0; i2 < h; i2++) {
            int i3 = i2 * g;
            for (int i4 = 0; i4 < g; i4++) {
                iArr[i3 + i4] = ((bArr[(i4 * 2) + i] & 255) * 65793) | -16777216;
            }
            i += this.c * 2;
        }
        return iArr;
    }

    public int j() {
        return g() / 2;
    }

    public int k() {
        return h() / 2;
    }

    private void a(int i, int i2) {
        byte[] bArr = this.b;
        int i3 = this.e + (this.f * this.c);
        for (int i4 = 0; i4 < i2; i4++) {
            int i5 = i3 + (i / 2);
            int i6 = (i3 + i) - 1;
            int i7 = i3;
            while (i7 < i5) {
                byte b = bArr[i7];
                bArr[i7] = bArr[i6];
                bArr[i6] = b;
                i7++;
                i6--;
            }
            i3 += this.c;
        }
    }
}

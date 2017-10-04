package dji.midware.media.k.b;

import android.support.v4.media.TransportMediator;

public class b {
    public final a a;
    int b;
    int c;
    private boolean d = false;
    private long e;
    private final dji.midware.media.c.a f;

    public enum a {
        YUV,
        H264
    }

    public int a() {
        return this.f.b();
    }

    public dji.midware.media.c.a b() {
        return this.f;
    }

    public long c() {
        return this.e;
    }

    public void a(long j) {
        this.e = j;
    }

    public boolean d() {
        return this.d;
    }

    public void a(boolean z) {
        this.d = z;
    }

    protected void a(byte[] bArr, byte[] bArr2, int i, int i2) {
        int i3 = i * i2;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        while (i6 < i2) {
            int i7 = 0;
            int i8 = i5;
            int i9 = i4;
            while (i7 < i) {
                byte b = bArr2[(i8 << 2) + 2];
                byte b2 = bArr2[(i8 << 2) + 1];
                byte b3 = bArr2[(i8 << 2) + 0];
                int i10 = ((((b * 76) + (b2 * 150)) + (b3 * 29)) + 128) >> 8;
                i4 = (((((b * -43) - (b2 * 84)) + (b3 * TransportMediator.KEYCODE_MEDIA_PAUSE)) + 128) >> 8) + 128;
                i5 = (((((b * TransportMediator.KEYCODE_MEDIA_PAUSE) - (b2 * 106)) - (b3 * 21)) + 128) >> 8) + 128;
                int i11 = i9 + 1;
                if (i10 < 0) {
                    i10 = 0;
                } else if (i10 > 255) {
                    i10 = 255;
                }
                bArr[i9] = (byte) i10;
                if (i6 % 2 == 0 && i8 % 2 == 0) {
                    i9 = i3 + 1;
                    i10 = i5 < 0 ? 0 : i5 > 255 ? 255 : i5;
                    bArr[i3] = (byte) i10;
                    i5 = i9 + 1;
                    i10 = i4 < 0 ? 0 : i4 > 255 ? 255 : i4;
                    bArr[i9] = (byte) i10;
                    i10 = i5;
                } else {
                    i10 = i3;
                }
                i7++;
                i8++;
                i3 = i10;
                i9 = i11;
            }
            i6++;
            i5 = i8;
            i4 = i9;
        }
    }

    public b(a aVar, int i) {
        this.a = aVar;
        switch (aVar) {
            case H264:
                this.f = new dji.midware.media.c.a(i, false);
                return;
            case YUV:
                this.f = new dji.midware.media.c.a(i, true);
                return;
            default:
                this.f = null;
                throw new RuntimeException("unknown frame type");
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        if (this.e == bVar.e && this.a == bVar.a) {
            return true;
        }
        return false;
    }

    public void a(int i, int i2) {
        this.b = i;
        this.c = i2;
    }

    public int e() {
        return this.b;
    }

    public int f() {
        return this.c;
    }
}

package dji.pilot.fpv.model;

import dji.midware.data.manager.P3.n;
import dji.midware.util.c;

public class d extends n {
    private byte a;
    private byte b;
    private float c;
    private float d;
    private long e;
    private final int f = 18;

    public d(boolean z) {
        super(z);
    }

    public void clear() {
        super.clear();
        this.a = (byte) 0;
        this.b = (byte) 0;
        this.c = 0.0f;
        this.d = 0.0f;
        this.e = 0;
    }

    protected void doPack() {
    }

    public void a(byte[] bArr, boolean z) {
        setRecData(bArr);
        this.a = (byte) ((Integer) get(0, 1, Integer.class)).intValue();
        this.b = (byte) ((Integer) get(1, 1, Integer.class)).intValue();
        this.c = ((Float) get(2, 4, Float.class)).floatValue();
        this.d = ((Float) get(6, 4, Float.class)).floatValue();
        this.e = ((Long) get(10, 8, Long.class)).longValue();
        if (this.e < 1390000000000L && z) {
            this.e *= 1000;
        }
    }

    public byte[] getRecData() {
        byte[] bArr = new byte[18];
        bArr[0] = this.a;
        bArr[1] = this.b;
        c.a(c.a(this.c), bArr, 2);
        c.a(c.a(this.d), bArr, 6);
        c.a(c.a(this.e), bArr, 10);
        return bArr;
    }

    public byte a() {
        return this.a;
    }

    public void a(int i) {
        this.a = (byte) i;
    }

    public byte b() {
        return this.b;
    }

    public void b(int i) {
        this.b = (byte) i;
    }

    public float c() {
        return this.c;
    }

    public void a(float f) {
        this.c = f;
    }

    public float d() {
        return this.d;
    }

    public void b(float f) {
        this.d = f;
    }

    public long e() {
        if (this.e < 1390000000000L) {
        }
        return this.e;
    }

    public void a(long j) {
        this.e = j;
    }
}

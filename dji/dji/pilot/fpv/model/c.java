package dji.pilot.fpv.model;

import dji.midware.data.manager.P3.n;

public class c extends n {
    private static final int d = 20;
    public double a;
    public double b;
    public float c;

    public c(boolean z) {
        super(z);
    }

    public void clear() {
        this.a = 0.0d;
        this.b = 0.0d;
        this.c = 0.0f;
    }

    public void setRecData(byte[] bArr) {
        super.setRecData(bArr);
        this.a = ((Double) get(0, 8, Double.class)).doubleValue();
        this.b = ((Double) get(8, 8, Double.class)).doubleValue();
        this.c = ((Float) get(16, 4, Float.class)).floatValue();
    }

    public byte[] getRecData() {
        byte[] bArr = new byte[20];
        dji.midware.util.c.a(dji.midware.util.c.a(this.a), bArr, 0);
        dji.midware.util.c.a(dji.midware.util.c.a(this.b), bArr, 8);
        dji.midware.util.c.a(dji.midware.util.c.a(this.c), bArr, 16);
        return bArr;
    }

    protected void doPack() {
    }
}

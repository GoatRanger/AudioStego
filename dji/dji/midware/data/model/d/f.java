package dji.midware.data.model.d;

import dji.midware.data.a.b.b;
import dji.midware.data.manager.P3.n;

public class f extends n {
    private static f a = null;
    private b b;

    public static synchronized f getInstance() {
        f fVar;
        synchronized (f.class) {
            if (a == null) {
                a = new f();
            }
            fVar = a;
        }
        return fVar;
    }

    protected boolean isChanged(byte[] bArr) {
        return true;
    }

    protected void setPushRecData(byte[] bArr) {
        super.setPushRecData(bArr);
    }

    protected void doPack() {
    }

    public b a() {
        return this.b;
    }

    public void a(b bVar) {
        this.b = bVar;
        setPushRecData(bVar.i);
    }

    public int b() {
        return ((Integer) get(0, 4, Integer.class)).intValue();
    }

    public int c() {
        return ((Integer) get(4, 4, Integer.class)).intValue();
    }

    public long d() {
        return ((Long) get(8, 4, Long.class)).longValue();
    }

    public long e() {
        return ((Long) get(12, 4, Long.class)).longValue();
    }

    public int f() {
        return 32;
    }
}

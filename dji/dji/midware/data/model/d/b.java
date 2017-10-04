package dji.midware.data.model.d;

import dji.midware.data.manager.P3.n;

public class b extends n {
    private static b a = null;
    private dji.midware.data.a.b.b b;

    public static synchronized b getInstance() {
        b bVar;
        synchronized (b.class) {
            if (a == null) {
                a = new b();
            }
            bVar = a;
        }
        return bVar;
    }

    protected void setPushRecData(byte[] bArr) {
        super.setPushRecData(bArr);
    }

    protected void doPack() {
    }

    public dji.midware.data.a.b.b a() {
        return this.b;
    }

    public void a(dji.midware.data.a.b.b bVar) {
        this.b = bVar;
        setPushRecData(bVar.j);
    }
}

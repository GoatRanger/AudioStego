package dji.midware.data.model.d;

import dji.midware.data.a.b.b;
import dji.midware.data.manager.P3.n;

public class d extends n {
    private static d a = null;
    private b b;

    public static synchronized d getInstance() {
        d dVar;
        synchronized (d.class) {
            if (a == null) {
                a = new d();
            }
            dVar = a;
        }
        return dVar;
    }

    protected boolean isChanged(byte[] bArr) {
        return true;
    }

    protected void setPushRecData(byte[] bArr) {
        super.setPushRecData(bArr);
    }

    protected void doPack() {
    }

    public int a() {
        return ((Integer) get(0, 4, Integer.class)).intValue();
    }

    public int b() {
        return ((Integer) get(4, 4, Integer.class)).intValue();
    }

    public b c() {
        return this.b;
    }

    public void a(b bVar) {
        this.b = bVar;
        setPushRecData(bVar.i);
    }
}

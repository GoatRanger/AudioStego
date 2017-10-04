package dji.midware.data.model.d;

import dji.midware.data.a.b.b;
import dji.midware.data.manager.P3.n;

public class e extends n {
    private static e a = null;
    private b b;

    public static synchronized e getInstance() {
        e eVar;
        synchronized (e.class) {
            if (a == null) {
                a = new e();
            }
            eVar = a;
        }
        return eVar;
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
        setPushRecData(bVar.j);
    }
}

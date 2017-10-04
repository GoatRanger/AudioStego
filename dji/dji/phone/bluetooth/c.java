package dji.phone.bluetooth;

import dji.midware.b.a.e;

public class c {
    private static final String b = "DJILPBleHolder";
    dji.midware.b.c a;

    private static class a {
        private static final c a = new c();

        private a() {
        }
    }

    public static c getInstance() {
        return a.a;
    }

    public void a() {
        if (this.a != null) {
            if (this.a.isConnected()) {
                getInstance().d();
            }
            this.a.destroy();
            this.a = null;
        }
    }

    public boolean b() {
        if (dji.midware.b.c.getInstance().isConnected()) {
            return true;
        }
        return false;
    }

    public void c() {
        dji.midware.b.c.getInstance().d();
    }

    public void a(int i, e eVar) {
        if (this.a == null) {
            this.a = dji.midware.b.c.getInstance();
        }
        this.a.b(i);
        this.a.f().a(eVar);
    }

    public void a(String str) {
        dji.midware.b.c.getInstance().a(str);
    }

    public void d() {
        dji.midware.b.c.getInstance().e();
    }
}

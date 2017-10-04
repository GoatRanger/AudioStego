package dji.phone.controview;

import dji.pilot.phonecamera.a.c;

public class b {
    private static final String b = "LonganCameraModeManager";
    private static b c = null;
    boolean a = false;
    private a d = a.TAKEPHOTO;

    public enum a {
        TAKEPHOTO,
        RECORD
    }

    private b() {
    }

    public static synchronized b getInstance() {
        b bVar;
        synchronized (b.class) {
            if (c == null) {
                c = new b();
            }
            bVar = c;
        }
        return bVar;
    }

    public void a() {
        if (c.a().o() == 1) {
            this.d = a.RECORD;
        } else if (c.a().o() == 0) {
            this.d = a.TAKEPHOTO;
        }
    }

    public void a(a aVar) {
        if (aVar != this.d) {
            this.d = aVar;
            dji.thirdparty.a.c.a().e(this.d);
        }
    }

    public a b() {
        return this.d;
    }
}

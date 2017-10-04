package dji.device.timelapse;

import dji.device.camera.a.e;
import dji.midware.data.model.P3.DataGimbalGetPushParams;
import dji.thirdparty.a.c;
import java.util.ArrayList;

public class b {
    public static String h = null;
    private static final String i = "LonganTimelapseController";
    private static b j = null;
    public int a = 50;
    public int b = 300;
    public int c = this.a;
    public int d = this.b;
    boolean e = false;
    b f = b.STATIONARY;
    ArrayList<a> g = new ArrayList();

    public class a {
        public int a;
        public int b;
        public int c;
        public int d;
        final /* synthetic */ b e;

        public a(b bVar, int i, int i2, int i3, int i4) {
            this.e = bVar;
            this.a = i;
            this.b = i2;
            this.c = i3;
            this.d = i4;
        }
    }

    public enum b {
        STATIONARY(0),
        MOTION(1),
        OTHER(2);
        
        private int d;

        private b(int i) {
            this.d = i;
        }

        public int a() {
            return this.d;
        }

        public boolean a(int i) {
            return this.d == i;
        }

        public static b find(int i) {
            b bVar = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2].a(i)) {
                    return values()[i2];
                }
            }
            return bVar;
        }
    }

    public static synchronized b getInstance() {
        b bVar;
        synchronized (b.class) {
            if (j == null) {
                j = new b();
                j.a();
            }
            bVar = j;
        }
        return bVar;
    }

    private b() {
    }

    public void a() {
        c.a().a(this);
    }

    public void b() {
        j = null;
        c.a().d(this);
    }

    public void a(b bVar) {
        this.f = bVar;
    }

    public b c() {
        return this.f;
    }

    public void a(int i) {
        this.c = i;
    }

    public void b(int i) {
        this.d = i;
    }

    public void d() {
        if (this.g.isEmpty()) {
            e.getInstance().a(2).a(dji.device.camera.a.e.a.TIMELAPSE);
        }
        this.g.add(new a(this, DataGimbalGetPushParams.getInstance().getYaw(), DataGimbalGetPushParams.getInstance().getPitch(), this.c, this.d));
        a(this.g.size(), this.c, this.d, true, 0, 0);
    }

    public void e() {
        a(this.g.size(), this.c, 0, false, 0, 0);
        this.g.remove(this.g.size() - 1);
    }

    public void f() {
        e.getInstance().a(0).a(1, DataGimbalGetPushParams.getInstance().getYaw(), DataGimbalGetPushParams.getInstance().getPitch()).b(this.a).c(this.b).a(dji.device.camera.a.e.a.TIMELAPSE);
    }

    public void a(int i, int i2, int i3, boolean z, int i4, int i5) {
        int i6 = 0;
        if (z) {
            i4 = DataGimbalGetPushParams.getInstance().getYaw();
            i5 = DataGimbalGetPushParams.getInstance().getPitch();
        }
        if (this.f != b.STATIONARY && this.f == b.MOTION) {
            i6 = 1;
        }
        e.getInstance().a(i6).a(i, i4, i5).b(i2).c(i3).a(dji.device.camera.a.e.a.TIMELAPSE);
    }

    public ArrayList<a> g() {
        return this.g;
    }

    public void h() {
        this.g.clear();
    }

    public void onEventMainThread(dji.device.camera.a.a aVar) {
        if (aVar.d() == dji.device.camera.a.a.a.TAKEPHOTO) {
            h();
        }
    }

    public void onEventMainThread(dji.device.camera.a.b.c cVar) {
        if (cVar == dji.device.camera.a.b.c.NOT_TIMING && this.g != null && !this.g.isEmpty()) {
            this.g.clear();
        }
    }
}

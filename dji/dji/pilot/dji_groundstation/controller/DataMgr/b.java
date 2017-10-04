package dji.pilot.dji_groundstation.controller.DataMgr;

public class b extends a {
    private static final String b = "CourseLockDataMgr";
    private static b c = null;
    private boolean d = false;
    private boolean e = false;
    private float f = 0.0f;

    public boolean i() {
        return this.e;
    }

    public boolean j() {
        return this.d;
    }

    public void a(boolean z) {
        this.d = z;
    }

    public void b(boolean z) {
        this.e = z;
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

    private b() {
    }

    public void e() {
        super.e();
        c = null;
    }

    public void a(float f) {
        this.f = f;
    }

    public float k() {
        return this.f;
    }
}

package dji.pilot.fpv.control;

import dji.pilot.fpv.model.f;

public class s {
    private static s i = null;
    public f a;
    public int b;
    public float c;
    public long d;
    public float e = 0.0f;
    public float f = 0.0f;
    public float g = 0.0f;
    public boolean h = true;
    private boolean j = false;

    public static synchronized s getInstance() {
        s sVar;
        synchronized (s.class) {
            if (i == null) {
                i = new s();
            }
            sVar = i;
        }
        return sVar;
    }

    public void a() {
        this.j = true;
    }

    public void b() {
        this.j = false;
    }

    public boolean c() {
        return this.j;
    }
}

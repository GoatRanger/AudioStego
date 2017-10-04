package dji.pilot.publics.control.rc;

import dji.midware.data.config.P3.a;
import dji.pilot.publics.control.rc.c.b;

class b$6 implements b {
    final /* synthetic */ b a;

    b$6(b bVar) {
        this.a = bVar;
    }

    public void a(c cVar, int i, int i2) {
        b.a(this.a, b.b(this.a) + ((long) i));
        int b = (int) ((b.b(this.a) * 200) / b.c(this.a));
        if (b.d(this.a) != b) {
            b.a(this.a, b);
            b.b(this.a, b, 200);
        }
    }

    public void a(c cVar, int i) {
        b.b(this.a, i);
    }

    public void b(c cVar, int i) {
        int b = (int) ((b.b(this.a) * 200) / b.c(this.a));
        if (b.d(this.a) != b) {
            b.a(this.a, b);
            b.b(this.a, b, 200);
        }
    }

    public void a(c cVar, a aVar, int i, int i2) {
        b.a(this.a, cVar, aVar, i, i2);
    }
}

package dji.sdksharedlib.hardware.abstractions;

import dji.common.error.DJIError;
import dji.sdksharedlib.b.c;
import dji.sdksharedlib.e.b;

public class b$b implements b$d {
    final /* synthetic */ b a;
    private c b;
    private dji.sdksharedlib.c.c c;

    public b$b(b bVar, c cVar, dji.sdksharedlib.c.c cVar2) {
        this.a = bVar;
        this.b = cVar;
        this.c = cVar2;
    }

    public void a(Object obj) {
        this.a.a(obj, this.b.f(), new b$g(this.a, this.b, this.c));
    }

    public void a(final DJIError dJIError) {
        if (this.c != null) {
            b.a(new Runnable(this) {
                final /* synthetic */ b$b b;

                public void run() {
                    this.b.c.a(dJIError);
                }
            }, true);
        }
    }

    public boolean a() {
        return this.c != null;
    }
}

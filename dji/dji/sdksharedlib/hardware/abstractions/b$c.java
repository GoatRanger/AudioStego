package dji.sdksharedlib.hardware.abstractions;

import dji.common.error.DJIError;
import dji.sdksharedlib.c.h;
import dji.sdksharedlib.e.b;
import dji.sdksharedlib.hardware.abstractions.b.e;

public class b$c implements e {
    final /* synthetic */ b a;
    private String b;
    private h c;
    private Object d;

    public b$c(b bVar, String str, Object obj, h hVar) {
        this.a = bVar;
        this.b = str;
        this.c = hVar;
        this.d = obj;
    }

    public void a(Object obj) {
        this.a.a(this.d, this.b);
        if (this.c != null) {
            b.a(new Runnable(this) {
                final /* synthetic */ b$c a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.c.a();
                }
            }, true);
        }
    }

    public void a(final DJIError dJIError) {
        if (this.c != null) {
            b.a(new Runnable(this) {
                final /* synthetic */ b$c b;

                public void run() {
                    this.b.c.a(dJIError);
                }
            }, true);
        }
    }
}

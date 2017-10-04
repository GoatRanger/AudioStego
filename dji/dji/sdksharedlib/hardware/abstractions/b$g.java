package dji.sdksharedlib.hardware.abstractions;

import dji.common.error.DJIError;
import dji.sdksharedlib.c.c;
import dji.sdksharedlib.d.a;
import dji.sdksharedlib.d.a$a;
import dji.sdksharedlib.d.a$b;
import dji.sdksharedlib.e.b;

public class b$g implements b$d {
    final /* synthetic */ b a;
    private c b;
    private dji.sdksharedlib.b.c c;

    public b$g(b bVar, c cVar) {
        this.a = bVar;
        this.b = cVar;
    }

    public b$g(b bVar, dji.sdksharedlib.b.c cVar, c cVar2) {
        this.a = bVar;
        this.c = cVar;
        this.b = cVar2;
    }

    public void a(final a aVar) {
        if (this.b != null) {
            b.a(new Runnable(this) {
                final /* synthetic */ b$g b;

                public void run() {
                    this.b.b.a(aVar);
                }
            }, true);
        }
    }

    public void a(Object obj) {
        if (this.b != null) {
            a aVar;
            if (this.a.b(this.c) == DJISDKCacheUpdateType.DYNAMIC) {
                aVar = new a(obj, a$b.Valid, a$a.Get, 100);
            } else {
                aVar = new a(obj, a$b.Valid, a$a.Get);
            }
            b.a(new Runnable(this) {
                final /* synthetic */ b$g b;

                public void run() {
                    this.b.b.a(aVar);
                }
            }, true);
        }
    }

    public void a(final DJIError dJIError) {
        if (this.b != null) {
            b.a(new Runnable(this) {
                final /* synthetic */ b$g b;

                public void run() {
                    this.b.b.a(dJIError);
                }
            }, true);
        }
    }

    public boolean a() {
        return this.b != null;
    }
}

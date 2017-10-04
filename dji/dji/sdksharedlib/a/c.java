package dji.sdksharedlib.a;

import dji.common.error.DJIError;
import dji.log.DJILogHelper;
import dji.sdksharedlib.DJISDKCache;
import dji.sdksharedlib.d.a;
import dji.sdksharedlib.e.b;

public class c implements dji.sdksharedlib.c.c, Runnable {
    public final String a = "RepeatGet";
    private dji.sdksharedlib.b.c b;
    private dji.sdksharedlib.c.c c;
    private int d;
    private int e;
    private int f;
    private int g = -1;
    private Runnable h = null;

    private void b() {
        DJISDKCache.getInstance().getValue(b.f("InternalSerialNumber"), new dji.sdksharedlib.c.c(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void a(a aVar) {
            }

            public void a(DJIError dJIError) {
            }
        });
    }

    private void c() {
        new c(b.f("InternalSerialNumber"), 3, new dji.sdksharedlib.c.c(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void a(a aVar) {
            }

            public void a(DJIError dJIError) {
            }
        }).a();
    }

    public c(dji.sdksharedlib.b.c cVar, dji.sdksharedlib.c.c cVar2) {
        this.b = cVar;
        this.d = 3;
        this.e = 1000;
        this.c = cVar2;
        this.f = 0;
    }

    public c(dji.sdksharedlib.b.c cVar, int i, dji.sdksharedlib.c.c cVar2) {
        this.b = cVar;
        this.d = i;
        this.e = 1000;
        this.c = cVar2;
        this.f = 0;
    }

    public c(dji.sdksharedlib.b.c cVar, int i, int i2, dji.sdksharedlib.c.c cVar2) {
        this.b = cVar;
        this.d = i;
        this.e = i2;
        this.c = cVar2;
        this.f = 0;
    }

    public void a() {
        DJISDKCache.getInstance().getValue(this.b, this);
    }

    public void run() {
        a();
    }

    public void a(a aVar) {
        if (this.c != null) {
            this.c.a(aVar);
        }
    }

    public void a(DJIError dJIError) {
        this.f++;
        if (this.f < this.d) {
            b.a(this, (long) this.e, true);
            DJILogHelper.getInstance().LOGD("RepeatGet", "repeat time : " + this.f, true, false);
            if (this.g > 0 && this.g == this.f) {
                dji.midware.util.b.a(this.h);
                return;
            }
            return;
        }
        this.c.a(dJIError);
    }
}

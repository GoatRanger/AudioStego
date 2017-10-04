package dji.sdksharedlib.hardware.abstractions;

import dji.common.error.DJIError;
import dji.sdksharedlib.c.b;
import dji.sdksharedlib.hardware.abstractions.b.e;

public class b$a implements e {
    final /* synthetic */ b a;
    private String b;
    private b c;
    private Object[] d;

    public b$a(b bVar, String str, b bVar2, Object... objArr) {
        this.a = bVar;
        this.b = str;
        this.c = bVar2;
        this.d = objArr;
    }

    public void a(Object obj) {
        this.a.a(obj, this.b);
        if (this.c != null) {
            dji.sdksharedlib.e.b.a(new Runnable(this) {
                final /* synthetic */ b$a a;

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
            dji.sdksharedlib.e.b.a(new Runnable(this) {
                final /* synthetic */ b$a b;

                public void run() {
                    this.b.c.a(dJIError);
                }
            }, true);
        }
    }
}

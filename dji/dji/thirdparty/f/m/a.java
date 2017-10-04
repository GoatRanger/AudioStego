package dji.thirdparty.f.m;

import dji.thirdparty.f.d.b;
import dji.thirdparty.f.l;
import java.util.concurrent.atomic.AtomicReference;

public final class a implements l {
    static final b b = new b() {
        public void a() {
        }
    };
    final AtomicReference<b> a;

    public a() {
        this.a = new AtomicReference();
    }

    private a(b bVar) {
        this.a = new AtomicReference(bVar);
    }

    public static a c() {
        return new a();
    }

    public static a a(b bVar) {
        return new a(bVar);
    }

    public boolean b() {
        return this.a.get() == b;
    }

    public final void n_() {
        if (((b) this.a.get()) != b) {
            b bVar = (b) this.a.getAndSet(b);
            if (bVar != null && bVar != b) {
                bVar.a();
            }
        }
    }
}

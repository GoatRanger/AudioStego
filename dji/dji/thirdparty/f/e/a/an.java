package dji.thirdparty.f.e.a;

import dji.thirdparty.f.d;
import dji.thirdparty.f.d$f;
import dji.thirdparty.f.d.b;
import dji.thirdparty.f.d.c;
import dji.thirdparty.f.d.n;
import dji.thirdparty.f.d.o;
import dji.thirdparty.f.g.e;
import dji.thirdparty.f.k;
import dji.thirdparty.f.l;
import java.util.concurrent.atomic.AtomicBoolean;

public final class an<T, Resource> implements d$f<T> {
    private final n<Resource> a;
    private final o<? super Resource, ? extends d<? extends T>> b;
    private final c<? super Resource> c;
    private final boolean d;

    private static final class a<Resource> extends AtomicBoolean implements b, l {
        private static final long a = 4262875056400218316L;
        private c<? super Resource> b;
        private Resource c;

        a(c<? super Resource> cVar, Resource resource) {
            this.b = cVar;
            this.c = resource;
            lazySet(false);
        }

        public void a() {
            if (compareAndSet(false, true)) {
                try {
                    this.b.a(this.c);
                } finally {
                    this.c = null;
                    this.b = null;
                }
            }
        }

        public boolean b() {
            return get();
        }

        public void n_() {
            a();
        }
    }

    public an(n<Resource> nVar, o<? super Resource, ? extends d<? extends T>> oVar, c<? super Resource> cVar, boolean z) {
        this.a = nVar;
        this.b = oVar;
        this.c = cVar;
        this.d = z;
    }

    public void a(k<? super T> kVar) {
        b aVar;
        try {
            Object call = this.a.call();
            aVar = new a(this.c, call);
            kVar.a((l) aVar);
            d dVar = (d) this.b.a(call);
            if (this.d) {
                dVar = dVar.c(aVar);
            }
            dVar.a(e.a((k) kVar));
        } catch (Throwable th) {
            dji.thirdparty.f.c.b.a(th, (dji.thirdparty.f.e) kVar);
        }
    }

    private Throwable a(b bVar) {
        Throwable th = null;
        if (this.d) {
            try {
                bVar.a();
            } catch (Throwable th2) {
                th = th2;
            }
        }
        return th;
    }
}

package dji.thirdparty.f.e.a;

import dji.thirdparty.f.d;
import dji.thirdparty.f.d$g;
import dji.thirdparty.f.f;
import dji.thirdparty.f.k;
import dji.thirdparty.f.l;
import dji.thirdparty.f.m.e;

public final class cp<T> implements d$g<T, T> {
    private final d<? extends T> a;

    private static final class a<T> extends k<T> {
        private final dji.thirdparty.f.e.b.a a;
        private final k<? super T> b;

        a(k<? super T> kVar, dji.thirdparty.f.e.b.a aVar) {
            this.b = kVar;
            this.a = aVar;
        }

        public void a(f fVar) {
            this.a.a(fVar);
        }

        public void o_() {
            this.b.o_();
        }

        public void a(Throwable th) {
            this.b.a(th);
        }

        public void a_(T t) {
            this.b.a_(t);
            this.a.b(1);
        }
    }

    private static final class b<T> extends k<T> {
        private boolean a = true;
        private final k<? super T> b;
        private final e c;
        private final dji.thirdparty.f.e.b.a d;
        private final d<? extends T> e;

        b(k<? super T> kVar, e eVar, dji.thirdparty.f.e.b.a aVar, d<? extends T> dVar) {
            this.b = kVar;
            this.c = eVar;
            this.d = aVar;
            this.e = dVar;
        }

        public void a(f fVar) {
            this.d.a(fVar);
        }

        public void o_() {
            if (!this.a) {
                this.b.o_();
            } else if (!this.b.b()) {
                d();
            }
        }

        private void d() {
            Object aVar = new a(this.b, this.d);
            this.c.a(aVar);
            this.e.a(aVar);
        }

        public void a(Throwable th) {
            this.b.a(th);
        }

        public void a_(T t) {
            this.a = false;
            this.b.a_(t);
            this.d.b(1);
        }
    }

    public cp(d<? extends T> dVar) {
        this.a = dVar;
    }

    public k<? super T> a(k<? super T> kVar) {
        l eVar = new e();
        f aVar = new dji.thirdparty.f.e.b.a();
        Object bVar = new b(kVar, eVar, aVar, this.a);
        eVar.a(bVar);
        kVar.a(eVar);
        kVar.a(aVar);
        return bVar;
    }
}

package dji.thirdparty.f.l;

import dji.thirdparty.f.d$f;
import dji.thirdparty.f.g.c;
import dji.thirdparty.f.k;

public class e<T, R> extends f<T, R> {
    private final c<T> c;
    private final f<T, R> d;

    class AnonymousClass1 implements d$f<R> {
        final /* synthetic */ f a;

        AnonymousClass1(f fVar) {
            this.a = fVar;
        }

        public void a(k<? super R> kVar) {
            this.a.a(kVar);
        }
    }

    public e(f<T, R> fVar) {
        super(new AnonymousClass1(fVar));
        this.d = fVar;
        this.c = new c(fVar);
    }

    public void o_() {
        this.c.o_();
    }

    public void a(Throwable th) {
        this.c.a(th);
    }

    public void a_(T t) {
        this.c.a_(t);
    }

    public boolean J() {
        return this.d.J();
    }
}

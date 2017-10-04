package dji.thirdparty.f;

import dji.thirdparty.f.d.b;
import dji.thirdparty.f.m.c;

class b$19 implements b$c {
    final /* synthetic */ b a;
    final /* synthetic */ c b;
    final /* synthetic */ b c;

    b$19(b bVar, b bVar2, c cVar) {
        this.c = bVar;
        this.a = bVar2;
        this.b = cVar;
    }

    public void b() {
        try {
            this.a.a();
        } catch (Throwable th) {
            b.c.a(th);
            b.c(th);
        } finally {
            this.b.n_();
        }
    }

    public void a(Throwable th) {
        b.c.a(th);
        this.b.n_();
        b.c(th);
    }

    public void a(l lVar) {
        this.b.a(lVar);
    }
}

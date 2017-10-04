package dji.thirdparty.f;

import dji.thirdparty.f.c.a;
import dji.thirdparty.f.d.b;
import dji.thirdparty.f.m.c;
import java.util.Arrays;

class b$20 implements b$c {
    final /* synthetic */ b a;
    final /* synthetic */ c b;
    final /* synthetic */ dji.thirdparty.f.d.c c;
    final /* synthetic */ b d;

    b$20(b bVar, b bVar2, c cVar, dji.thirdparty.f.d.c cVar2) {
        this.d = bVar;
        this.a = bVar2;
        this.b = cVar;
        this.c = cVar2;
    }

    public void b() {
        try {
            this.a.a();
            this.b.n_();
        } catch (Throwable th) {
            a(th);
        }
    }

    public void a(Throwable th) {
        try {
            this.c.a(th);
        } catch (Throwable th2) {
            Throwable aVar = new a(Arrays.asList(new Throwable[]{th, th2}));
            b.c.a(aVar);
            b.c(aVar);
        } finally {
            this.b.n_();
        }
    }

    public void a(l lVar) {
        this.b.a(lVar);
    }
}

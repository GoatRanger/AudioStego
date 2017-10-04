package dji.thirdparty.f.j;

import dji.thirdparty.f.d.b;
import dji.thirdparty.f.g;
import dji.thirdparty.f.l;
import dji.thirdparty.f.m.f;
import java.util.concurrent.TimeUnit;

public final class c extends g {
    private static final c b = new c();

    private class a extends dji.thirdparty.f.g.a implements l {
        final dji.thirdparty.f.m.a a = new dji.thirdparty.f.m.a();
        final /* synthetic */ c b;

        a(c cVar) {
            this.b = cVar;
        }

        public l a(b bVar, long j, TimeUnit timeUnit) {
            return a(new f(bVar, this, this.b.b() + timeUnit.toMillis(j)));
        }

        public l a(b bVar) {
            bVar.a();
            return f.b();
        }

        public void n_() {
            this.a.n_();
        }

        public boolean b() {
            return this.a.b();
        }
    }

    static c c() {
        return b;
    }

    c() {
    }

    public dji.thirdparty.f.g.a a() {
        return new a(this);
    }
}

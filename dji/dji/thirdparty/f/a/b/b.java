package dji.thirdparty.f.a.b;

import android.os.Handler;
import dji.thirdparty.f.e.c.d;
import dji.thirdparty.f.g;
import dji.thirdparty.f.l;
import dji.thirdparty.f.m.f;
import java.util.concurrent.TimeUnit;

public final class b extends g {
    private final Handler b;

    static class a extends dji.thirdparty.f.g.a {
        private final Handler a;
        private final dji.thirdparty.f.m.b b = new dji.thirdparty.f.m.b();

        a(Handler handler) {
            this.a = handler;
        }

        public void n_() {
            this.b.n_();
        }

        public boolean b() {
            return this.b.b();
        }

        public l a(dji.thirdparty.f.d.b bVar, long j, TimeUnit timeUnit) {
            if (this.b.b()) {
                return f.b();
            }
            final l dVar = new d(dji.thirdparty.f.a.a.a.getInstance().b().a(bVar));
            dVar.a(this.b);
            this.b.a(dVar);
            this.a.postDelayed(dVar, timeUnit.toMillis(j));
            dVar.a(f.a(new dji.thirdparty.f.d.b(this) {
                final /* synthetic */ a b;

                public void a() {
                    this.b.a.removeCallbacks(dVar);
                }
            }));
            return dVar;
        }

        public l a(dji.thirdparty.f.d.b bVar) {
            return a(bVar, 0, TimeUnit.MILLISECONDS);
        }
    }

    public static b a(Handler handler) {
        if (handler != null) {
            return new b(handler);
        }
        throw new NullPointerException("handler == null");
    }

    b(Handler handler) {
        this.b = handler;
    }

    public dji.thirdparty.f.g.a a() {
        return new a(this.b);
    }
}

package dji.thirdparty.f.f;

import dji.thirdparty.f.b.a;
import dji.thirdparty.f.d;
import dji.thirdparty.f.d$f;
import dji.thirdparty.f.d.m;
import dji.thirdparty.f.e.a.ai;
import dji.thirdparty.f.e.a.t;
import dji.thirdparty.f.l;

public abstract class c<T> extends d<T> {
    public abstract void h(dji.thirdparty.f.d.c<? super l> cVar);

    protected c(d$f<T> dji_thirdparty_f_d_f_T) {
        super(dji_thirdparty_f_d_f_T);
    }

    public final l a() {
        final l[] lVarArr = new l[1];
        h(new dji.thirdparty.f.d.c<l>(this) {
            final /* synthetic */ c b;

            public void a(l lVar) {
                lVarArr[0] = lVar;
            }
        });
        return lVarArr[0];
    }

    public d<T> I() {
        return a(new ai(this));
    }

    @a
    public d<T> J() {
        return n(1);
    }

    @a
    public d<T> n(int i) {
        return a(i, m.a());
    }

    @a
    public d<T> a(int i, dji.thirdparty.f.d.c<? super l> cVar) {
        if (i > 0) {
            return a(new t(this, i, cVar));
        }
        h(cVar);
        return this;
    }
}

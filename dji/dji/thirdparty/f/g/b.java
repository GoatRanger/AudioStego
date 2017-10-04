package dji.thirdparty.f.g;

import dji.thirdparty.f.c.a;
import dji.thirdparty.f.c.h;
import dji.thirdparty.f.e;
import dji.thirdparty.f.e.d.i;
import dji.thirdparty.f.k;
import java.util.Arrays;

public class b<T> extends k<T> {
    boolean a = false;
    private final k<? super T> b;

    public b(k<? super T> kVar) {
        super(kVar);
        this.b = kVar;
    }

    public void o_() {
        h hVar;
        if (!this.a) {
            this.a = true;
            try {
                this.b.o_();
                try {
                    n_();
                } catch (Throwable th) {
                    i.a(th);
                    hVar = new h(th.getMessage(), th);
                }
            } catch (Throwable th2) {
                try {
                    n_();
                } catch (Throwable th3) {
                    i.a(th3);
                    hVar = new h(th3.getMessage(), th3);
                }
            }
        }
    }

    public void a(Throwable th) {
        dji.thirdparty.f.c.b.b(th);
        if (!this.a) {
            this.a = true;
            b(th);
        }
    }

    public void a_(T t) {
        try {
            if (!this.a) {
                this.b.a_(t);
            }
        } catch (Throwable th) {
            dji.thirdparty.f.c.b.a(th, (e) this);
        }
    }

    protected void b(Throwable th) {
        i.a(th);
        try {
            this.b.a(th);
            try {
                n_();
            } catch (Throwable e) {
                i.a(e);
                throw new dji.thirdparty.f.c.e(e);
            }
        } catch (Throwable th2) {
            i.a(th2);
            dji.thirdparty.f.c.e eVar = new dji.thirdparty.f.c.e("Error occurred when trying to propagate error to Observer.onError and during unsubscription.", new a(Arrays.asList(new Throwable[]{th, e, th2})));
        }
    }

    public k<? super T> d() {
        return this.b;
    }
}

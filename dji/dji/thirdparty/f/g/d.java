package dji.thirdparty.f.g;

import dji.thirdparty.f.e;
import dji.thirdparty.f.k;

public class d<T> extends k<T> {
    private final e<T> a;

    public d(k<? super T> kVar) {
        this(kVar, true);
    }

    public d(k<? super T> kVar, boolean z) {
        super(kVar, z);
        this.a = new c(kVar);
    }

    public void o_() {
        this.a.o_();
    }

    public void a(Throwable th) {
        this.a.a(th);
    }

    public void a_(T t) {
        this.a.a_(t);
    }
}

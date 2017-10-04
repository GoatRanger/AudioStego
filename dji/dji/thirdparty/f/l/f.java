package dji.thirdparty.f.l;

import dji.thirdparty.f.d;
import dji.thirdparty.f.d$f;
import dji.thirdparty.f.e;

public abstract class f<T, R> extends d<R> implements e<T> {
    public abstract boolean J();

    protected f(d$f<R> dji_thirdparty_f_d_f_R) {
        super(dji_thirdparty_f_d_f_R);
    }

    public final e<T, R> U() {
        if (getClass() == e.class) {
            return (e) this;
        }
        return new e(this);
    }
}

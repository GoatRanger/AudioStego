package dji.thirdparty.f.f;

import dji.thirdparty.f.d$f;
import dji.thirdparty.f.k;

public class d<K, T> extends dji.thirdparty.f.d<T> {
    private final K c;

    public static <K, T> d<K, T> a(K k, final dji.thirdparty.f.d<T> dVar) {
        return new d(k, new d$f<T>() {
            public void a(k<? super T> kVar) {
                dVar.a(kVar);
            }
        });
    }

    public static <K, T> d<K, T> a(K k, d$f<T> dji_thirdparty_f_d_f_T) {
        return new d(k, dji_thirdparty_f_d_f_T);
    }

    protected d(K k, d$f<T> dji_thirdparty_f_d_f_T) {
        super(dji_thirdparty_f_d_f_T);
        this.c = k;
    }

    public K I() {
        return this.c;
    }
}

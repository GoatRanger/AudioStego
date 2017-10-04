package dji.thirdparty.afinal.d.a;

import dji.thirdparty.afinal.b;

public class c<M, O> {
    M a;
    Class<M> b;
    Class<O> c;
    b d;
    O e;
    boolean f = false;
    private Object g;

    public c(M m, Class<M> cls, Class<O> cls2, b bVar) {
        this.a = m;
        this.b = cls;
        this.c = cls2;
        this.d = bVar;
    }

    public O a() {
        if (this.e == null && !this.f) {
            this.d.a(null, this.a, this.b, this.c);
            this.f = true;
        }
        return this.e;
    }

    public void a(O o) {
        this.e = o;
    }

    public Object b() {
        return this.g;
    }

    public void b(Object obj) {
        this.g = obj;
    }
}

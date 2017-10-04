package dji.thirdparty.afinal.d.a;

import dji.thirdparty.afinal.b;
import java.util.ArrayList;
import java.util.List;

public class d<O, M> {
    O a;
    Class<O> b;
    Class<M> c;
    b d;
    List<M> e;

    public d(O o, Class<O> cls, Class<M> cls2, b bVar) {
        this.a = o;
        this.b = cls;
        this.c = cls2;
        this.d = bVar;
    }

    public List<M> a() {
        if (this.e == null) {
            this.d.c(this.a, this.b, this.c);
        }
        if (this.e == null) {
            this.e = new ArrayList();
        }
        return this.e;
    }

    public void a(List<M> list) {
        this.e = list;
    }
}

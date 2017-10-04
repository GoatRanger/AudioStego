package dji.thirdparty.f.l;

import dji.thirdparty.f.b.a;
import dji.thirdparty.f.d$f;
import dji.thirdparty.f.d.c;
import dji.thirdparty.f.e.a.r;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public final class b<T> extends f<T, T> {
    private static final Object[] e = new Object[0];
    private final g<T> c;
    private final r<T> d = r.a();

    public static <T> b<T> I() {
        return a(null, false);
    }

    public static <T> b<T> i(T t) {
        return a(t, true);
    }

    private static <T> b<T> a(T t, boolean z) {
        final Object gVar = new g();
        if (z) {
            gVar.b(r.a().a((Object) t));
        }
        gVar.d = new c<b<T>>() {
            public void a(b<T> bVar) {
                bVar.b(gVar.a(), gVar.f);
            }
        };
        gVar.e = gVar.d;
        return new b(gVar, gVar);
    }

    protected b(d$f<T> dji_thirdparty_f_d_f_T, g<T> gVar) {
        super(dji_thirdparty_f_d_f_T);
        this.c = gVar;
    }

    public void o_() {
        if (this.c.a() == null || this.c.b) {
            Object b = this.d.b();
            for (b a : this.c.d(b)) {
                a.a(b, this.c.f);
            }
        }
    }

    public void a(Throwable th) {
        if (this.c.a() == null || this.c.b) {
            Object a = this.d.a(th);
            List list = null;
            for (b a2 : this.c.d(a)) {
                try {
                    a2.a(a, this.c.f);
                } catch (Throwable th2) {
                    if (list == null) {
                        list = new ArrayList();
                    }
                    list.add(th2);
                }
            }
            dji.thirdparty.f.c.b.a(list);
        }
    }

    public void a_(T t) {
        if (this.c.a() == null || this.c.b) {
            Object a = this.d.a((Object) t);
            for (b a2 : this.c.c(a)) {
                a2.a(a, this.c.f);
            }
        }
    }

    int K() {
        return this.c.b().length;
    }

    public boolean J() {
        return this.c.b().length > 0;
    }

    @a
    public boolean L() {
        return this.d.e(this.c.a());
    }

    @a
    public boolean M() {
        return this.d.c(this.c.a());
    }

    @a
    public boolean N() {
        return this.d.b(this.c.a());
    }

    @a
    public T O() {
        Object a = this.c.a();
        if (this.d.e(a)) {
            return this.d.g(a);
        }
        return null;
    }

    @a
    public Throwable P() {
        Object a = this.c.a();
        if (this.d.c(a)) {
            return this.d.h(a);
        }
        return null;
    }

    @a
    public T[] b(T[] tArr) {
        Object a = this.c.a();
        if (this.d.e(a)) {
            T[] tArr2;
            if (tArr.length == 0) {
                tArr2 = (Object[]) Array.newInstance(tArr.getClass().getComponentType(), 1);
            } else {
                tArr2 = tArr;
            }
            tArr2[0] = this.d.g(a);
            if (tArr2.length <= 1) {
                return tArr2;
            }
            tArr2[1] = null;
            return tArr2;
        }
        if (tArr.length > 0) {
            tArr[0] = null;
        }
        return tArr;
    }

    @a
    public Object[] Q() {
        Object[] b = b(e);
        if (b == e) {
            return new Object[0];
        }
        return b;
    }
}

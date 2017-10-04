package dji.thirdparty.f.e.a;

import dji.thirdparty.f.c.b;
import dji.thirdparty.f.d.o;
import dji.thirdparty.f.h;
import dji.thirdparty.f.h.a;
import dji.thirdparty.f.i;
import dji.thirdparty.f.l;

public class ds<T> implements a<T> {
    private final h<? extends T> a;
    private final o<Throwable, ? extends h<? extends T>> b;

    private ds(h<? extends T> hVar, o<Throwable, ? extends h<? extends T>> oVar) {
        if (hVar == null) {
            throw new NullPointerException("originalSingle must not be null");
        } else if (oVar == null) {
            throw new NullPointerException("resumeFunctionInCaseOfError must not be null");
        } else {
            this.a = hVar;
            this.b = oVar;
        }
    }

    public static <T> ds<T> a(h<? extends T> hVar, o<Throwable, ? extends h<? extends T>> oVar) {
        return new ds(hVar, oVar);
    }

    public static <T> ds<T> a(h<? extends T> hVar, final h<? extends T> hVar2) {
        if (hVar2 != null) {
            return new ds(hVar, new o<Throwable, h<? extends T>>() {
                public h<? extends T> a(Throwable th) {
                    return hVar2;
                }
            });
        }
        throw new NullPointerException("resumeSingleInCaseOfError must not be null");
    }

    public void a(final i<? super T> iVar) {
        i anonymousClass2 = new i<T>(this) {
            final /* synthetic */ ds b;

            public void a(T t) {
                iVar.a((Object) t);
            }

            public void a(Throwable th) {
                try {
                    ((h) this.b.b.a(th)).a(iVar);
                } catch (Throwable th2) {
                    b.a(th2, iVar);
                }
            }
        };
        iVar.a((l) anonymousClass2);
        this.a.a(anonymousClass2);
    }
}

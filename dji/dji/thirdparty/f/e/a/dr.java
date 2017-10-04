package dji.thirdparty.f.e.a;

import dji.thirdparty.f.c.b;
import dji.thirdparty.f.d.c;
import dji.thirdparty.f.d.n;
import dji.thirdparty.f.d.o;
import dji.thirdparty.f.h;
import dji.thirdparty.f.h.a;
import dji.thirdparty.f.i;
import dji.thirdparty.f.i.d;
import dji.thirdparty.f.l;
import java.util.Arrays;

public final class dr<T, Resource> implements a<T> {
    final n<Resource> a;
    final o<? super Resource, ? extends h<? extends T>> b;
    final c<? super Resource> c;
    final boolean d;

    public dr(n<Resource> nVar, o<? super Resource, ? extends h<? extends T>> oVar, c<? super Resource> cVar, boolean z) {
        this.a = nVar;
        this.b = oVar;
        this.c = cVar;
        this.d = z;
    }

    public void a(final i<? super T> iVar) {
        try {
            final Object call = this.a.call();
            try {
                h hVar = (h) this.b.a(call);
                if (hVar == null) {
                    a(iVar, call, new NullPointerException("The single"));
                    return;
                }
                i anonymousClass1 = new i<T>(this) {
                    final /* synthetic */ dr c;

                    public void a(T t) {
                        if (this.c.d) {
                            try {
                                this.c.c.a(call);
                            } catch (Throwable th) {
                                b.b(th);
                                iVar.a(th);
                                return;
                            }
                        }
                        iVar.a((Object) t);
                        if (!this.c.d) {
                            try {
                                this.c.c.a(call);
                            } catch (Throwable th2) {
                                b.b(th2);
                                d.getInstance().b().a(th2);
                            }
                        }
                    }

                    public void a(Throwable th) {
                        this.c.a(iVar, call, th);
                    }
                };
                iVar.a((l) anonymousClass1);
                hVar.a(anonymousClass1);
            } catch (Throwable th) {
                a(iVar, call, th);
            }
        } catch (Throwable th2) {
            b.b(th2);
            iVar.a(th2);
        }
    }

    void a(i<? super T> iVar, Resource resource, Throwable th) {
        b.b(th);
        if (this.d) {
            try {
                this.c.a(resource);
            } catch (Throwable th2) {
                b.b(th2);
                th = new dji.thirdparty.f.c.a(Arrays.asList(new Throwable[]{th, r1}));
            }
        }
        iVar.a(th);
        if (!this.d) {
            try {
                this.c.a(resource);
            } catch (Throwable th22) {
                b.b(th22);
                d.getInstance().b().a(th22);
            }
        }
    }
}

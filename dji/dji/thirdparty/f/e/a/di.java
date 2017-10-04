package dji.thirdparty.f.e.a;

import dji.thirdparty.f.d$g;
import dji.thirdparty.f.d.b;
import dji.thirdparty.f.g;
import dji.thirdparty.f.g.a;
import dji.thirdparty.f.k;
import dji.thirdparty.f.m.f;

public class di<T> implements d$g<T, T> {
    final g a;

    public di(g gVar) {
        this.a = gVar;
    }

    public k<? super T> a(final k<? super T> kVar) {
        final k<? super T> anonymousClass1 = new k<T>(this) {
            final /* synthetic */ di b;

            public void o_() {
                kVar.o_();
            }

            public void a(Throwable th) {
                kVar.a(th);
            }

            public void a_(T t) {
                kVar.a_(t);
            }
        };
        kVar.a(f.a(new b(this) {
            final /* synthetic */ di b;

            public void a() {
                final a a = this.b.a.a();
                a.a(new b(this) {
                    final /* synthetic */ AnonymousClass2 b;

                    public void a() {
                        anonymousClass1.n_();
                        a.n_();
                    }
                });
            }
        }));
        return anonymousClass1;
    }
}

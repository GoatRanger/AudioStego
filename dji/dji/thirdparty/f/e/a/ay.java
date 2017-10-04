package dji.thirdparty.f.e.a;

import dji.thirdparty.f.d$g;
import dji.thirdparty.f.d.b;
import dji.thirdparty.f.g;
import dji.thirdparty.f.k;
import dji.thirdparty.f.l;
import java.util.concurrent.TimeUnit;

public final class ay<T> implements d$g<T, T> {
    final long a;
    final TimeUnit b;
    final g c;

    public ay(long j, TimeUnit timeUnit, g gVar) {
        this.a = j;
        this.b = timeUnit;
        this.c = gVar;
    }

    public k<? super T> a(final k<? super T> kVar) {
        final l a = this.c.a();
        kVar.a(a);
        return new k<T>(this, kVar) {
            boolean a;
            final /* synthetic */ ay d;

            public void o_() {
                a.a(new b(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public void a() {
                        if (!this.a.a) {
                            this.a.a = true;
                            kVar.o_();
                        }
                    }
                }, this.d.a, this.d.b);
            }

            public void a(final Throwable th) {
                a.a(new b(this) {
                    final /* synthetic */ AnonymousClass1 b;

                    public void a() {
                        if (!this.b.a) {
                            this.b.a = true;
                            kVar.a(th);
                            a.n_();
                        }
                    }
                });
            }

            public void a_(final T t) {
                a.a(new b(this) {
                    final /* synthetic */ AnonymousClass1 b;

                    public void a() {
                        if (!this.b.a) {
                            kVar.a_(t);
                        }
                    }
                }, this.d.a, this.d.b);
            }
        };
    }
}

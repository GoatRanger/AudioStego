package dji.thirdparty.f.e.a;

import dji.thirdparty.f.d;
import dji.thirdparty.f.d$f;
import dji.thirdparty.f.d.b;
import dji.thirdparty.f.f;
import dji.thirdparty.f.g;
import dji.thirdparty.f.k;
import dji.thirdparty.f.l;

public final class cn<T> implements d$f<T> {
    final g a;
    final d<T> b;

    public cn(d<T> dVar, g gVar) {
        this.a = gVar;
        this.b = dVar;
    }

    public void a(final k<? super T> kVar) {
        final l a = this.a.a();
        kVar.a(a);
        a.a(new b(this) {
            final /* synthetic */ cn c;

            public void a() {
                final Thread currentThread = Thread.currentThread();
                this.c.b.a(new k<T>(this, kVar) {
                    final /* synthetic */ AnonymousClass1 b;

                    public void a_(T t) {
                        kVar.a_(t);
                    }

                    public void a(Throwable th) {
                        try {
                            kVar.a(th);
                        } finally {
                            a.n_();
                        }
                    }

                    public void o_() {
                        try {
                            kVar.o_();
                        } finally {
                            a.n_();
                        }
                    }

                    public void a(final f fVar) {
                        kVar.a(new f(this) {
                            final /* synthetic */ AnonymousClass1 b;

                            public void a(final long j) {
                                if (currentThread == Thread.currentThread()) {
                                    fVar.a(j);
                                } else {
                                    a.a(new b(this) {
                                        final /* synthetic */ AnonymousClass1 b;

                                        public void a() {
                                            fVar.a(j);
                                        }
                                    });
                                }
                            }
                        });
                    }
                });
            }
        });
    }
}

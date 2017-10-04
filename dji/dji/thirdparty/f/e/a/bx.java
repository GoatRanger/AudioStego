package dji.thirdparty.f.e.a;

import dji.thirdparty.f.c.b;
import dji.thirdparty.f.d;
import dji.thirdparty.f.d$g;
import dji.thirdparty.f.d.o;
import dji.thirdparty.f.e.b.a;
import dji.thirdparty.f.f;
import dji.thirdparty.f.k;
import dji.thirdparty.f.l;
import dji.thirdparty.f.m.e;

public final class bx<T> implements d$g<T, T> {
    final o<Throwable, ? extends d<? extends T>> a;

    public static <T> bx<T> a(final o<Throwable, ? extends T> oVar) {
        return new bx(new o<Throwable, d<? extends T>>() {
            public d<? extends T> a(Throwable th) {
                return d.b(oVar.a(th));
            }
        });
    }

    public static <T> bx<T> a(final d<? extends T> dVar) {
        return new bx(new o<Throwable, d<? extends T>>() {
            public d<? extends T> a(Throwable th) {
                return dVar;
            }
        });
    }

    public static <T> bx<T> b(final d<? extends T> dVar) {
        return new bx(new o<Throwable, d<? extends T>>() {
            public d<? extends T> a(Throwable th) {
                if (th instanceof Exception) {
                    return dVar;
                }
                return d.b(th);
            }
        });
    }

    public bx(o<Throwable, ? extends d<? extends T>> oVar) {
        this.a = oVar;
    }

    public k<? super T> a(final k<? super T> kVar) {
        final f aVar = new a();
        final l eVar = new e();
        Object anonymousClass4 = new k<T>(this) {
            long a;
            final /* synthetic */ bx e;
            private boolean f;

            public void o_() {
                if (!this.f) {
                    this.f = true;
                    kVar.o_();
                }
            }

            public void a(Throwable th) {
                if (this.f) {
                    b.b(th);
                    dji.thirdparty.f.i.d.getInstance().b().a(th);
                    return;
                }
                this.f = true;
                try {
                    n_();
                    Object anonymousClass1 = new k<T>(this) {
                        final /* synthetic */ AnonymousClass4 a;

                        {
                            this.a = r1;
                        }

                        public void a_(T t) {
                            kVar.a_(t);
                        }

                        public void a(Throwable th) {
                            kVar.a(th);
                        }

                        public void o_() {
                            kVar.o_();
                        }

                        public void a(f fVar) {
                            aVar.a(fVar);
                        }
                    };
                    eVar.a(anonymousClass1);
                    long j = this.a;
                    if (j != 0) {
                        aVar.b(j);
                    }
                    ((d) this.e.a.a(th)).a(anonymousClass1);
                } catch (Throwable th2) {
                    b.a(th2, kVar);
                }
            }

            public void a_(T t) {
                if (!this.f) {
                    this.a++;
                    kVar.a_(t);
                }
            }

            public void a(f fVar) {
                aVar.a(fVar);
            }
        };
        eVar.a(anonymousClass4);
        kVar.a(eVar);
        kVar.a(aVar);
        return anonymousClass4;
    }
}

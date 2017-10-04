package dji.thirdparty.f.e.a;

import com.here.odnp.posclient.pos.IPositioningSession;
import dji.thirdparty.f.c.b;
import dji.thirdparty.f.d$g;
import dji.thirdparty.f.d.n;
import dji.thirdparty.f.d.p;
import dji.thirdparty.f.e;
import dji.thirdparty.f.e.d.a.h;
import dji.thirdparty.f.e.d.b.ag;
import dji.thirdparty.f.e.d.b.an;
import dji.thirdparty.f.f;
import dji.thirdparty.f.k;
import dji.thirdparty.f.l;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicLong;

public final class cd<R, T> implements d$g<R, T> {
    private static final Object c = new Object();
    final p<R, ? super T, R> a;
    private final n<R> b;

    class AnonymousClass1 implements n<R> {
        final /* synthetic */ Object a;

        AnonymousClass1(Object obj) {
            this.a = obj;
        }

        public R call() {
            return this.a;
        }
    }

    static final class a<R> implements e<R>, f {
        final k<? super R> a;
        final Queue<Object> b;
        boolean c;
        boolean d;
        long e;
        final AtomicLong f;
        volatile f g;
        volatile boolean h;
        Throwable i;

        public a(R r, k<? super R> kVar) {
            Queue agVar;
            this.a = kVar;
            if (an.a()) {
                agVar = new ag();
            } else {
                agVar = new h();
            }
            this.b = agVar;
            agVar.offer(r.a().a((Object) r));
            this.f = new AtomicLong();
        }

        public void a_(R r) {
            this.b.offer(r.a().a((Object) r));
            b();
        }

        boolean a(boolean z, boolean z2, k<? super R> kVar) {
            if (kVar.b()) {
                return true;
            }
            if (z) {
                Throwable th = this.i;
                if (th != null) {
                    kVar.a(th);
                    return true;
                } else if (z2) {
                    kVar.o_();
                    return true;
                }
            }
            return false;
        }

        public void a(Throwable th) {
            this.i = th;
            this.h = true;
            b();
        }

        public void o_() {
            this.h = true;
            b();
        }

        public void a(long j) {
            if (j < 0) {
                throw new IllegalArgumentException("n >= required but it was " + j);
            } else if (j != 0) {
                a.a(this.f, j);
                f fVar = this.g;
                if (fVar == null) {
                    synchronized (this.f) {
                        fVar = this.g;
                        if (fVar == null) {
                            this.e = a.b(this.e, j);
                        }
                    }
                }
                if (fVar != null) {
                    fVar.a(j);
                }
                b();
            }
        }

        public void a(f fVar) {
            if (fVar == null) {
                throw new NullPointerException();
            }
            synchronized (this.f) {
                if (this.g != null) {
                    throw new IllegalStateException("Can't set more than one Producer!");
                }
                long j = this.e;
                if (j != IPositioningSession.NotSet) {
                    j--;
                }
                this.e = 0;
                this.g = fVar;
            }
            if (j > 0) {
                fVar.a(j);
            }
            b();
        }

        void b() {
            synchronized (this) {
                if (this.c) {
                    this.d = true;
                    return;
                }
                this.c = true;
                c();
            }
        }

        void c() {
            e eVar = this.a;
            Queue queue = this.b;
            r a = r.a();
            AtomicLong atomicLong = this.f;
            long j = atomicLong.get();
            while (true) {
                long j2;
                Object obj = j == IPositioningSession.NotSet ? 1 : null;
                if (!a(this.h, queue.isEmpty(), eVar)) {
                    long j3 = j;
                    j = 0;
                    while (j3 != 0) {
                        boolean z = this.h;
                        Object poll = queue.poll();
                        boolean z2 = poll == null;
                        if (!a(z, z2, eVar)) {
                            if (z2) {
                                break;
                            }
                            Object g = a.g(poll);
                            try {
                                eVar.a_(g);
                                j--;
                                j3--;
                            } catch (Throwable th) {
                                b.a(th, eVar, g);
                                return;
                            }
                        }
                        return;
                    }
                    if (j == 0 || obj != null) {
                        j2 = j3;
                    } else {
                        j2 = atomicLong.addAndGet(j);
                    }
                    synchronized (this) {
                        if (this.d) {
                            this.d = false;
                        } else {
                            this.c = false;
                            return;
                        }
                    }
                }
                return;
                j = j2;
            }
        }
    }

    public cd(R r, p<R, ? super T, R> pVar) {
        this(new AnonymousClass1(r), (p) pVar);
    }

    public cd(n<R> nVar, p<R, ? super T, R> pVar) {
        this.b = nVar;
        this.a = pVar;
    }

    public cd(p<R, ? super T, R> pVar) {
        this(c, (p) pVar);
    }

    public k<? super T> a(final k<? super R> kVar) {
        final Object call = this.b.call();
        if (call == c) {
            return new k<T>(this, kVar) {
                boolean a;
                R b;
                final /* synthetic */ cd d;

                public void a_(T t) {
                    if (this.a) {
                        try {
                            t = this.d.a.b(this.b, t);
                        } catch (Throwable th) {
                            b.a(th, kVar, t);
                            return;
                        }
                    }
                    this.a = true;
                    this.b = t;
                    kVar.a_(t);
                }

                public void a(Throwable th) {
                    kVar.a(th);
                }

                public void o_() {
                    kVar.o_();
                }
            };
        }
        final f aVar = new a(call, kVar);
        l anonymousClass3 = new k<T>(this) {
            final /* synthetic */ cd c;
            private R d = call;

            public void a_(T t) {
                try {
                    Object b = this.c.a.b(this.d, t);
                    this.d = b;
                    aVar.a_(b);
                } catch (Throwable th) {
                    b.a(th, this, t);
                }
            }

            public void a(Throwable th) {
                aVar.a(th);
            }

            public void o_() {
                aVar.o_();
            }

            public void a(f fVar) {
                aVar.a(fVar);
            }
        };
        kVar.a(anonymousClass3);
        kVar.a(aVar);
        return anonymousClass3;
    }
}

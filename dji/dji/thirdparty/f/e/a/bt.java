package dji.thirdparty.f.e.a;

import dji.thirdparty.f.c.c;
import dji.thirdparty.f.d$g;
import dji.thirdparty.f.d.b;
import dji.thirdparty.f.e.d.a.e;
import dji.thirdparty.f.e.d.b.an;
import dji.thirdparty.f.e.d.b.z;
import dji.thirdparty.f.e.d.j;
import dji.thirdparty.f.f;
import dji.thirdparty.f.g;
import dji.thirdparty.f.i.d;
import dji.thirdparty.f.k;
import dji.thirdparty.f.l;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicLong;

public final class bt<T> implements d$g<T, T> {
    private final g a;
    private final boolean b;

    private static final class a<T> extends k<T> implements b {
        final k<? super T> a;
        final dji.thirdparty.f.g.a b;
        final r<T> c;
        final boolean d;
        final Queue<Object> e;
        volatile boolean f;
        final AtomicLong g = new AtomicLong();
        final AtomicLong h = new AtomicLong();
        Throwable i;

        public a(g gVar, k<? super T> kVar, boolean z) {
            this.a = kVar;
            this.b = gVar.a();
            this.d = z;
            this.c = r.a();
            if (an.a()) {
                this.e = new z(j.c);
            } else {
                this.e = new e(j.c);
            }
        }

        void d() {
            k kVar = this.a;
            kVar.a(new f(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void a(long j) {
                    if (j > 0) {
                        a.a(this.a.g, j);
                        this.a.e();
                    }
                }
            });
            kVar.a(this.b);
            kVar.a((l) this);
        }

        public void c() {
            a((long) j.c);
        }

        public void a_(T t) {
            if (!b() && !this.f) {
                if (this.e.offer(this.c.a((Object) t))) {
                    e();
                } else {
                    a(new c());
                }
            }
        }

        public void o_() {
            if (!b() && !this.f) {
                this.f = true;
                e();
            }
        }

        public void a(Throwable th) {
            if (b() || this.f) {
                d.getInstance().b().a(th);
                return;
            }
            this.i = th;
            this.f = true;
            e();
        }

        protected void e() {
            if (this.h.getAndIncrement() == 0) {
                this.b.a(this);
            }
        }

        public void a() {
            long j = 0;
            long j2 = 1;
            Queue queue = this.e;
            k kVar = this.a;
            r rVar = this.c;
            do {
                long j3 = this.g.get();
                long j4 = 0;
                while (j3 != j4) {
                    boolean z = this.f;
                    Object poll = queue.poll();
                    boolean z2 = poll == null;
                    if (!a(z, z2, kVar, queue)) {
                        if (z2) {
                            break;
                        }
                        kVar.a_(rVar.g(poll));
                        j++;
                        j4 = 1 + j4;
                    } else {
                        return;
                    }
                }
                if (j3 != j4 || !a(this.f, queue.isEmpty(), kVar, queue)) {
                    if (j4 != 0) {
                        a.b(this.g, j4);
                    }
                    j2 = this.h.addAndGet(-j2);
                } else {
                    return;
                }
            } while (j2 != 0);
            if (j != 0) {
                a(j);
            }
        }

        boolean a(boolean z, boolean z2, k<? super T> kVar, Queue<Object> queue) {
            if (kVar.b()) {
                queue.clear();
                return true;
            }
            if (z) {
                if (!this.d) {
                    Throwable th = this.i;
                    if (th != null) {
                        queue.clear();
                        try {
                            kVar.a(th);
                            return true;
                        } finally {
                            this.b.n_();
                        }
                    } else if (z2) {
                        try {
                            kVar.o_();
                            return true;
                        } finally {
                            this.b.n_();
                        }
                    }
                } else if (z2) {
                    Throwable th2 = this.i;
                    if (th2 != null) {
                        try {
                            kVar.a(th2);
                        } catch (Throwable th3) {
                            this.b.n_();
                        }
                    } else {
                        kVar.o_();
                    }
                    this.b.n_();
                }
            }
            return false;
        }
    }

    public bt(g gVar, boolean z) {
        this.a = gVar;
        this.b = z;
    }

    public k<? super T> a(k<? super T> kVar) {
        if ((this.a instanceof dji.thirdparty.f.j.c) || (this.a instanceof dji.thirdparty.f.j.j)) {
            return kVar;
        }
        k aVar = new a(this.a, kVar, this.b);
        aVar.d();
        return aVar;
    }
}

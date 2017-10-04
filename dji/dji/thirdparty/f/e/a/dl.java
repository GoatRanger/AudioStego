package dji.thirdparty.f.e.a;

import com.here.odnp.posclient.pos.IPositioningSession;
import dji.thirdparty.f.d;
import dji.thirdparty.f.d$g;
import dji.thirdparty.f.e.d.a.g;
import dji.thirdparty.f.k;
import dji.thirdparty.f.l;
import dji.thirdparty.f.m.f;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public final class dl<T> implements d$g<d<T>, T> {
    final int a;
    final int b;

    static final class a<T> extends k<T> implements dji.thirdparty.f.d.b {
        final k<? super d<T>> a;
        final int b;
        final AtomicInteger c = new AtomicInteger(1);
        final l d = f.a((dji.thirdparty.f.d.b) this);
        int e;
        dji.thirdparty.f.l.f<T, T> f;

        public a(k<? super d<T>> kVar, int i) {
            this.a = kVar;
            this.b = i;
            a(this.d);
            a(0);
        }

        public void a_(T t) {
            int i = this.e;
            dji.thirdparty.f.l.f fVar = this.f;
            if (i == 0) {
                this.c.getAndIncrement();
                fVar = dv.a(this.b, this);
                this.f = fVar;
                this.a.a_(fVar);
            }
            i++;
            fVar.a_(t);
            if (i == this.b) {
                this.e = 0;
                this.f = null;
                fVar.o_();
                return;
            }
            this.e = i;
        }

        public void a(Throwable th) {
            dji.thirdparty.f.l.f fVar = this.f;
            if (fVar != null) {
                this.f = null;
                fVar.a(th);
            }
            this.a.a(th);
        }

        public void o_() {
            dji.thirdparty.f.l.f fVar = this.f;
            if (fVar != null) {
                this.f = null;
                fVar.o_();
            }
            this.a.o_();
        }

        dji.thirdparty.f.f d() {
            return new dji.thirdparty.f.f(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void a(long j) {
                    if (j < 0) {
                        throw new IllegalArgumentException("n >= 0 required but it was " + j);
                    } else if (j != 0) {
                        this.a.a(a.a((long) this.a.b, j));
                    }
                }
            };
        }

        public void a() {
            if (this.c.decrementAndGet() == 0) {
                n_();
            }
        }
    }

    static final class b<T> extends k<T> implements dji.thirdparty.f.d.b {
        final k<? super d<T>> a;
        final int b;
        final int c;
        final AtomicInteger d = new AtomicInteger(1);
        final l e = f.a((dji.thirdparty.f.d.b) this);
        final ArrayDeque<dji.thirdparty.f.l.f<T, T>> f = new ArrayDeque();
        final AtomicLong g = new AtomicLong();
        final AtomicInteger h = new AtomicInteger();
        final Queue<dji.thirdparty.f.l.f<T, T>> i;
        Throwable j;
        volatile boolean k;
        int l;
        int m;

        final class a extends AtomicBoolean implements dji.thirdparty.f.f {
            private static final long b = 4625807964358024108L;
            final /* synthetic */ b a;

            a(b bVar) {
                this.a = bVar;
            }

            public void a(long j) {
                if (j < 0) {
                    throw new IllegalArgumentException("n >= 0 required but it was " + j);
                } else if (j != 0) {
                    b bVar = this.a;
                    if (get() || !compareAndSet(false, true)) {
                        this.a.a(a.a((long) bVar.c, j));
                    } else {
                        bVar.a(a.b(a.a((long) bVar.c, j - 1), (long) bVar.b));
                    }
                    a.a(bVar.g, j);
                    bVar.e();
                }
            }
        }

        public b(k<? super d<T>> kVar, int i, int i2) {
            this.a = kVar;
            this.b = i;
            this.c = i2;
            a(this.e);
            a(0);
            this.i = new g(((i2 - 1) + i) / i2);
        }

        public void a_(T t) {
            int i = this.l;
            ArrayDeque arrayDeque = this.f;
            if (i == 0 && !this.a.b()) {
                this.d.getAndIncrement();
                dv a = dv.a(16, this);
                arrayDeque.offer(a);
                this.i.offer(a);
                e();
            }
            Iterator it = this.f.iterator();
            while (it.hasNext()) {
                ((dji.thirdparty.f.l.f) it.next()).a_(t);
            }
            int i2 = this.m + 1;
            if (i2 == this.b) {
                this.m = i2 - this.c;
                dji.thirdparty.f.l.f fVar = (dji.thirdparty.f.l.f) arrayDeque.poll();
                if (fVar != null) {
                    fVar.o_();
                }
            } else {
                this.m = i2;
            }
            i2 = i + 1;
            if (i2 == this.c) {
                this.l = 0;
            } else {
                this.l = i2;
            }
        }

        public void a(Throwable th) {
            Iterator it = this.f.iterator();
            while (it.hasNext()) {
                ((dji.thirdparty.f.l.f) it.next()).a(th);
            }
            this.f.clear();
            this.j = th;
            this.k = true;
            e();
        }

        public void o_() {
            Iterator it = this.f.iterator();
            while (it.hasNext()) {
                ((dji.thirdparty.f.l.f) it.next()).o_();
            }
            this.f.clear();
            this.k = true;
            e();
        }

        dji.thirdparty.f.f d() {
            return new a(this);
        }

        public void a() {
            if (this.d.decrementAndGet() == 0) {
                n_();
            }
        }

        void e() {
            AtomicInteger atomicInteger = this.h;
            if (atomicInteger.getAndIncrement() == 0) {
                k kVar = this.a;
                Queue queue = this.i;
                int i = 1;
                while (true) {
                    long j = this.g.get();
                    long j2 = 0;
                    while (j2 != j) {
                        boolean z = this.k;
                        dji.thirdparty.f.l.f fVar = (dji.thirdparty.f.l.f) queue.poll();
                        boolean z2 = fVar == null;
                        if (!a(z, z2, kVar, queue)) {
                            if (z2) {
                                break;
                            }
                            kVar.a_(fVar);
                            j2 = 1 + j2;
                        } else {
                            return;
                        }
                    }
                    if (j2 != j || !a(this.k, queue.isEmpty(), kVar, queue)) {
                        if (!(j2 == 0 || j == IPositioningSession.NotSet)) {
                            this.g.addAndGet(-j2);
                        }
                        int addAndGet = atomicInteger.addAndGet(-i);
                        if (addAndGet != 0) {
                            i = addAndGet;
                        } else {
                            return;
                        }
                    }
                    return;
                }
            }
        }

        boolean a(boolean z, boolean z2, k<? super dji.thirdparty.f.l.f<T, T>> kVar, Queue<dji.thirdparty.f.l.f<T, T>> queue) {
            if (kVar.b()) {
                queue.clear();
                return true;
            }
            if (z) {
                Throwable th = this.j;
                if (th != null) {
                    queue.clear();
                    kVar.a(th);
                    return true;
                } else if (z2) {
                    kVar.o_();
                    return true;
                }
            }
            return false;
        }
    }

    static final class c<T> extends k<T> implements dji.thirdparty.f.d.b {
        final k<? super d<T>> a;
        final int b;
        final int c;
        final AtomicInteger d = new AtomicInteger(1);
        final l e = f.a((dji.thirdparty.f.d.b) this);
        int f;
        dji.thirdparty.f.l.f<T, T> g;

        final class a extends AtomicBoolean implements dji.thirdparty.f.f {
            private static final long b = 4625807964358024108L;
            final /* synthetic */ c a;

            a(c cVar) {
                this.a = cVar;
            }

            public void a(long j) {
                if (j < 0) {
                    throw new IllegalArgumentException("n >= 0 required but it was " + j);
                } else if (j != 0) {
                    c cVar = this.a;
                    if (get() || !compareAndSet(false, true)) {
                        cVar.a(a.a(j, (long) cVar.c));
                    } else {
                        cVar.a(a.b(a.a(j, (long) cVar.b), a.a((long) (cVar.c - cVar.b), j - 1)));
                    }
                }
            }
        }

        public c(k<? super d<T>> kVar, int i, int i2) {
            this.a = kVar;
            this.b = i;
            this.c = i2;
            a(this.e);
            a(0);
        }

        public void a_(T t) {
            int i = this.f;
            dji.thirdparty.f.l.f fVar = this.g;
            if (i == 0) {
                this.d.getAndIncrement();
                fVar = dv.a(this.b, this);
                this.g = fVar;
                this.a.a_(fVar);
            }
            i++;
            if (fVar != null) {
                fVar.a_(t);
            }
            if (i == this.b) {
                this.f = i;
                this.g = null;
                fVar.o_();
            } else if (i == this.c) {
                this.f = 0;
            } else {
                this.f = i;
            }
        }

        public void a(Throwable th) {
            dji.thirdparty.f.l.f fVar = this.g;
            if (fVar != null) {
                this.g = null;
                fVar.a(th);
            }
            this.a.a(th);
        }

        public void o_() {
            dji.thirdparty.f.l.f fVar = this.g;
            if (fVar != null) {
                this.g = null;
                fVar.o_();
            }
            this.a.o_();
        }

        dji.thirdparty.f.f d() {
            return new a(this);
        }

        public void a() {
            if (this.d.decrementAndGet() == 0) {
                n_();
            }
        }
    }

    public dl(int i, int i2) {
        this.a = i;
        this.b = i2;
    }

    public k<? super T> a(k<? super d<T>> kVar) {
        if (this.b == this.a) {
            k aVar = new a(kVar, this.a);
            kVar.a(aVar.d);
            kVar.a(aVar.d());
            return aVar;
        } else if (this.b > this.a) {
            r0 = new c(kVar, this.a, this.b);
            kVar.a(r0.e);
            kVar.a(r0.d());
            return r0;
        } else {
            r0 = new b(kVar, this.a, this.b);
            kVar.a(r0.e);
            kVar.a(r0.d());
            return r0;
        }
    }
}

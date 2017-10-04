package dji.thirdparty.f.e.a;

import com.here.odnp.posclient.pos.IPositioningSession;
import dji.thirdparty.f.d$f;
import dji.thirdparty.f.d$g;
import dji.thirdparty.f.d.o;
import dji.thirdparty.f.e.d.j;
import dji.thirdparty.f.e.d.q;
import dji.thirdparty.f.f;
import dji.thirdparty.f.k;
import dji.thirdparty.f.l;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public final class bl<T, K, V> implements d$g<dji.thirdparty.f.f.d<K, V>, T> {
    final o<? super T, ? extends K> a;
    final o<? super T, ? extends V> b;
    final int c;
    final boolean d;

    public static final class a implements f {
        final b<?, ?, ?> a;

        public a(b<?, ?, ?> bVar) {
            this.a = bVar;
        }

        public void a(long j) {
            this.a.b(j);
        }
    }

    public static final class b<T, K, V> extends k<T> {
        static final Object i = new Object();
        static final AtomicIntegerFieldUpdater<b> l = AtomicIntegerFieldUpdater.newUpdater(b.class, "k");
        static final AtomicLongFieldUpdater<b> n = AtomicLongFieldUpdater.newUpdater(b.class, "m");
        static final AtomicIntegerFieldUpdater<b> p = AtomicIntegerFieldUpdater.newUpdater(b.class, "o");
        static final AtomicIntegerFieldUpdater<b> t = AtomicIntegerFieldUpdater.newUpdater(b.class, "s");
        final k<? super dji.thirdparty.f.f.d<K, V>> a;
        final o<? super T, ? extends K> b;
        final o<? super T, ? extends V> c;
        final int d;
        final boolean e;
        final Map<Object, c<K, V>> f = new ConcurrentHashMap();
        final Queue<dji.thirdparty.f.f.d<K, V>> g = new ConcurrentLinkedQueue();
        final a h;
        final dji.thirdparty.f.e.b.a j;
        volatile int k;
        volatile long m;
        volatile int o;
        Throwable q;
        volatile boolean r;
        volatile int s;

        public b(k<? super dji.thirdparty.f.f.d<K, V>> kVar, o<? super T, ? extends K> oVar, o<? super T, ? extends V> oVar2, int i, boolean z) {
            this.a = kVar;
            this.b = oVar;
            this.c = oVar2;
            this.d = i;
            this.e = z;
            p.lazySet(this, 1);
            this.j = new dji.thirdparty.f.e.b.a();
            this.j.a((long) i);
            this.h = new a(this);
        }

        public void a(f fVar) {
            this.j.a(fVar);
        }

        public void a_(T t) {
            if (!this.r) {
                Queue queue = this.g;
                k kVar = this.a;
                try {
                    Object a = this.b.a(t);
                    Object obj = a != null ? a : i;
                    c cVar = (c) this.f.get(obj);
                    if (cVar != null) {
                        int i = 1;
                    } else if (this.k == 0) {
                        cVar = c.a(a, this.d, this, this.e);
                        this.f.put(obj, cVar);
                        p.getAndIncrement(this);
                        obj = null;
                        queue.offer(cVar);
                        e();
                    } else {
                        return;
                    }
                    try {
                        cVar.a(this.c.a(t));
                        if (obj != null) {
                            this.j.a(1);
                        }
                    } catch (Throwable th) {
                        n_();
                        a(kVar, queue, th);
                    }
                } catch (Throwable th2) {
                    n_();
                    a(kVar, queue, th2);
                }
            }
        }

        public void a(Throwable th) {
            if (this.r) {
                dji.thirdparty.f.i.d.getInstance().b().a(th);
                return;
            }
            this.q = th;
            this.r = true;
            p.decrementAndGet(this);
            e();
        }

        public void o_() {
            if (!this.r) {
                this.r = true;
                p.decrementAndGet(this);
                e();
            }
        }

        public void b(long j) {
            if (j < 0) {
                throw new IllegalArgumentException("n >= 0 required but it was " + j);
            }
            a.a(n, (Object) this, j);
            e();
        }

        public void d() {
            if (l.compareAndSet(this, 0, 1) && p.decrementAndGet(this) == 0) {
                n_();
            }
        }

        public void b(K k) {
            if (k == null) {
                k = i;
            }
            if (this.f.remove(k) != null && p.decrementAndGet(this) == 0) {
                n_();
            }
        }

        void e() {
            if (t.getAndIncrement(this) == 0) {
                Queue queue = this.g;
                k kVar = this.a;
                int i = 1;
                while (!a(this.r, queue.isEmpty(), kVar, queue)) {
                    long j = this.m;
                    Object obj = j == IPositioningSession.NotSet ? 1 : null;
                    long j2 = 0;
                    while (j != 0) {
                        boolean z = this.r;
                        dji.thirdparty.f.f.d dVar = (dji.thirdparty.f.f.d) queue.poll();
                        boolean z2 = dVar == null;
                        if (!a(z, z2, kVar, queue)) {
                            if (z2) {
                                break;
                            }
                            kVar.a_(dVar);
                            j--;
                            j2--;
                        } else {
                            return;
                        }
                    }
                    if (j2 != 0) {
                        if (obj == null) {
                            n.addAndGet(this, j2);
                        }
                        this.j.a(-j2);
                    }
                    int addAndGet = t.addAndGet(this, -i);
                    if (addAndGet != 0) {
                        i = addAndGet;
                    } else {
                        return;
                    }
                }
            }
        }

        void a(k<? super dji.thirdparty.f.f.d<K, V>> kVar, Queue<?> queue, Throwable th) {
            queue.clear();
            List<c> arrayList = new ArrayList(this.f.values());
            this.f.clear();
            for (c a : arrayList) {
                a.a(th);
            }
            kVar.a(th);
        }

        boolean a(boolean z, boolean z2, k<? super dji.thirdparty.f.f.d<K, V>> kVar, Queue<?> queue) {
            if (z) {
                Throwable th = this.q;
                if (th != null) {
                    a(kVar, queue, th);
                    return true;
                } else if (z2) {
                    List<c> arrayList = new ArrayList(this.f.values());
                    this.f.clear();
                    for (c a : arrayList) {
                        a.a();
                    }
                    this.a.o_();
                    return true;
                }
            }
            return false;
        }
    }

    static final class c<K, T> extends dji.thirdparty.f.f.d<K, T> {
        final d<T, K> c;

        public static <T, K> c<K, T> a(K k, int i, b<?, K, T> bVar, boolean z) {
            return new c(k, new d(i, bVar, k, z));
        }

        protected c(K k, d<T, K> dVar) {
            super(k, dVar);
            this.c = dVar;
        }

        public void a(T t) {
            this.c.b(t);
        }

        public void a(Throwable th) {
            this.c.a(th);
        }

        public void a() {
            this.c.c();
        }
    }

    static final class d<T, K> extends AtomicInteger implements d$f<T>, f, l {
        static final AtomicLongFieldUpdater<d> f = AtomicLongFieldUpdater.newUpdater(d.class, "e");
        static final AtomicIntegerFieldUpdater<d> j = AtomicIntegerFieldUpdater.newUpdater(d.class, "i");
        static final AtomicReferenceFieldUpdater<d, k> l = AtomicReferenceFieldUpdater.newUpdater(d.class, k.class, "k");
        static final AtomicIntegerFieldUpdater<d> n = AtomicIntegerFieldUpdater.newUpdater(d.class, "m");
        private static final long o = -3852313036005250360L;
        final K a;
        final Queue<Object> b = new ConcurrentLinkedQueue();
        final b<?, K, T> c;
        final boolean d;
        volatile long e;
        volatile boolean g;
        Throwable h;
        volatile int i;
        volatile k<? super T> k;
        volatile int m;

        public d(int i, b<?, K, T> bVar, K k, boolean z) {
            this.c = bVar;
            this.a = k;
            this.d = z;
        }

        public void a(long j) {
            if (j < 0) {
                throw new IllegalArgumentException("n >= required but it was " + j);
            } else if (j != 0) {
                a.a(f, (Object) this, j);
                d();
            }
        }

        public boolean b() {
            return this.i != 0;
        }

        public void n_() {
            if (j.compareAndSet(this, 0, 1) && getAndIncrement() == 0) {
                this.c.b(this.a);
            }
        }

        public void a(k<? super T> kVar) {
            if (n.compareAndSet(this, 0, 1)) {
                kVar.a((l) this);
                kVar.a((f) this);
                l.lazySet(this, kVar);
                d();
                return;
            }
            kVar.a(new IllegalStateException("Only one Subscriber allowed!"));
        }

        public void b(T t) {
            if (t == null) {
                this.h = new NullPointerException();
                this.g = true;
            } else {
                this.b.offer(r.a().a((Object) t));
            }
            d();
        }

        public void a(Throwable th) {
            this.h = th;
            this.g = true;
            d();
        }

        public void c() {
            this.g = true;
            d();
        }

        void d() {
            if (getAndIncrement() == 0) {
                Queue queue = this.b;
                boolean z = this.d;
                k kVar = this.k;
                r a = r.a();
                k kVar2 = kVar;
                int i = 1;
                while (true) {
                    if (kVar2 != null) {
                        if (!a(this.g, queue.isEmpty(), kVar2, z)) {
                            long j = this.e;
                            Object obj = j == IPositioningSession.NotSet ? 1 : null;
                            long j2 = 0;
                            while (j != 0) {
                                boolean z2 = this.g;
                                Object poll = queue.poll();
                                boolean z3 = poll == null;
                                if (!a(z2, z3, kVar2, z)) {
                                    if (z3) {
                                        break;
                                    }
                                    kVar2.a_(a.g(poll));
                                    j--;
                                    j2--;
                                } else {
                                    return;
                                }
                            }
                            if (j2 != 0) {
                                if (obj == null) {
                                    f.addAndGet(this, j2);
                                }
                                this.c.j.a(-j2);
                            }
                        } else {
                            return;
                        }
                    }
                    int addAndGet = addAndGet(-i);
                    if (addAndGet == 0) {
                        return;
                    }
                    if (kVar2 == null) {
                        kVar2 = this.k;
                        i = addAndGet;
                    } else {
                        i = addAndGet;
                    }
                }
            }
        }

        boolean a(boolean z, boolean z2, k<? super T> kVar, boolean z3) {
            if (this.i != 0) {
                this.b.clear();
                this.c.b(this.a);
                return true;
            }
            if (z) {
                Throwable th;
                if (!z3) {
                    th = this.h;
                    if (th != null) {
                        this.b.clear();
                        kVar.a(th);
                        return true;
                    } else if (z2) {
                        kVar.o_();
                        return true;
                    }
                } else if (z2) {
                    th = this.h;
                    if (th != null) {
                        kVar.a(th);
                        return true;
                    }
                    kVar.o_();
                    return true;
                }
            }
            return false;
        }
    }

    public bl(o<? super T, ? extends K> oVar) {
        this(oVar, q.c(), j.c, false);
    }

    public bl(o<? super T, ? extends K> oVar, o<? super T, ? extends V> oVar2) {
        this(oVar, oVar2, j.c, false);
    }

    public bl(o<? super T, ? extends K> oVar, o<? super T, ? extends V> oVar2, int i, boolean z) {
        this.a = oVar;
        this.b = oVar2;
        this.c = i;
        this.d = z;
    }

    public k<? super T> a(k<? super dji.thirdparty.f.f.d<K, V>> kVar) {
        final k bVar = new b(kVar, this.a, this.b, this.c, this.d);
        kVar.a(dji.thirdparty.f.m.f.a(new dji.thirdparty.f.d.b(this) {
            final /* synthetic */ bl b;

            public void a() {
                bVar.d();
            }
        }));
        kVar.a(bVar.h);
        return bVar;
    }
}

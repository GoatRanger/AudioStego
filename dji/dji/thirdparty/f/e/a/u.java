package dji.thirdparty.f.e.a;

import com.here.odnp.posclient.pos.IPositioningSession;
import dji.thirdparty.f.d;
import dji.thirdparty.f.d$f;
import dji.thirdparty.f.d.x;
import dji.thirdparty.f.e.d.a.g;
import dji.thirdparty.f.e.d.j;
import dji.thirdparty.f.f;
import dji.thirdparty.f.k;
import dji.thirdparty.f.l;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public final class u<T, R> implements d$f<R> {
    final d<? extends T>[] a;
    final Iterable<? extends d<? extends T>> b;
    final x<? extends R> c;
    final int d;
    final boolean e;

    static final class a<T, R> extends k<T> {
        final b<T, R> a;
        final int b;
        final r<T> c = r.a();
        boolean d;

        public a(b<T, R> bVar, int i) {
            this.a = bVar;
            this.b = i;
            a((long) bVar.e);
        }

        public void a_(T t) {
            if (!this.d) {
                this.a.a(this.c.a((Object) t), this.b);
            }
        }

        public void a(Throwable th) {
            if (this.d) {
                dji.thirdparty.f.i.d.getInstance().b().a(th);
                return;
            }
            this.a.a(th);
            this.d = true;
            this.a.a(null, this.b);
        }

        public void o_() {
            if (!this.d) {
                this.d = true;
                this.a.a(null, this.b);
            }
        }

        public void b(long j) {
            a(j);
        }
    }

    static final class b<T, R> extends AtomicInteger implements f, l {
        static final Object o = new Object();
        private static final long p = 8567835998786448817L;
        final k<? super R> a;
        final x<? extends R> b;
        final int c;
        final a<T, R>[] d;
        final int e;
        final Object[] f;
        final g<Object> g;
        final boolean h;
        volatile boolean i;
        volatile boolean j;
        final AtomicLong k = new AtomicLong();
        final AtomicReference<Throwable> l = new AtomicReference();
        int m;
        int n;

        public b(k<? super R> kVar, x<? extends R> xVar, int i, int i2, boolean z) {
            this.a = kVar;
            this.b = xVar;
            this.c = i;
            this.e = i2;
            this.h = z;
            this.f = new Object[i];
            Arrays.fill(this.f, o);
            this.d = new a[i];
            this.g = new g(i2);
        }

        public void a(d<? extends T>[] dVarArr) {
            int i = 0;
            a[] aVarArr = this.d;
            int length = aVarArr.length;
            for (int i2 = 0; i2 < length; i2++) {
                aVarArr[i2] = new a(this, i2);
            }
            lazySet(0);
            this.a.a((l) this);
            this.a.a((f) this);
            while (i < length && !this.i) {
                dVarArr[i].b(aVarArr[i]);
                i++;
            }
        }

        public void a(long j) {
            if (j < 0) {
                throw new IllegalArgumentException("n >= required but it was " + j);
            } else if (j != 0) {
                a.a(this.k, j);
                c();
            }
        }

        public void n_() {
            if (!this.i) {
                this.i = true;
                if (getAndIncrement() == 0) {
                    a(this.g);
                }
            }
        }

        public boolean b() {
            return this.i;
        }

        void a(Queue<?> queue) {
            queue.clear();
            for (a n_ : this.d) {
                n_.n_();
            }
        }

        void a(Object obj, int i) {
            Object obj2;
            Object obj3 = null;
            Object obj4 = this.d[i];
            synchronized (this) {
                int i2;
                int length = this.f.length;
                Object obj5 = this.f[i];
                int i3 = this.m;
                if (obj5 == o) {
                    i3++;
                    this.m = i3;
                }
                int i4 = i3;
                i3 = this.n;
                if (obj == null) {
                    i3++;
                    this.n = i3;
                    i2 = i3;
                } else {
                    this.f[i] = obj4.c.g(obj);
                    i2 = i3;
                }
                if (i4 == length) {
                    obj2 = 1;
                } else {
                    obj2 = null;
                }
                if (i2 == length || (obj == null && obj5 == o)) {
                    obj3 = 1;
                }
                if (obj3 != null) {
                    this.j = true;
                } else if (obj != null && obj2 != null) {
                    this.g.a(obj4, this.f.clone());
                } else if (obj == null && this.l.get() != null && (obj5 == o || !this.h)) {
                    this.j = true;
                }
            }
            if (obj2 != null || obj == null) {
                c();
            } else {
                obj4.b(1);
            }
        }

        void c() {
            if (getAndIncrement() == 0) {
                Queue queue = this.g;
                k kVar = this.a;
                boolean z = this.h;
                AtomicLong atomicLong = this.k;
                int i = 1;
                while (!a(this.j, queue.isEmpty(), kVar, queue, z)) {
                    long j = atomicLong.get();
                    Object obj = j == IPositioningSession.NotSet ? 1 : null;
                    long j2 = j;
                    j = 0;
                    while (j2 != 0) {
                        boolean z2 = this.j;
                        a aVar = (a) queue.peek();
                        boolean z3 = aVar == null;
                        if (!a(z2, z3, kVar, queue, z)) {
                            if (z3) {
                                break;
                            }
                            queue.poll();
                            Object[] objArr = (Object[]) queue.poll();
                            if (objArr == null) {
                                this.i = true;
                                a(queue);
                                kVar.a(new IllegalStateException("Broken queue?! Sender received but not the array."));
                                return;
                            }
                            try {
                                kVar.a_(this.b.a(objArr));
                                aVar.b(1);
                                j2--;
                                j--;
                            } catch (Throwable th) {
                                this.i = true;
                                a(queue);
                                kVar.a(th);
                                return;
                            }
                        }
                        return;
                    }
                    if (j != 0 && obj == null) {
                        atomicLong.addAndGet(j);
                    }
                    int addAndGet = addAndGet(-i);
                    if (addAndGet != 0) {
                        i = addAndGet;
                    } else {
                        return;
                    }
                }
            }
        }

        boolean a(boolean z, boolean z2, k<?> kVar, Queue<?> queue, boolean z3) {
            if (this.i) {
                a((Queue) queue);
                return true;
            }
            if (z) {
                Throwable th;
                if (!z3) {
                    th = (Throwable) this.l.get();
                    if (th != null) {
                        a((Queue) queue);
                        kVar.a(th);
                        return true;
                    } else if (z2) {
                        kVar.o_();
                        return true;
                    }
                } else if (z2) {
                    th = (Throwable) this.l.get();
                    if (th != null) {
                        kVar.a(th);
                    } else {
                        kVar.o_();
                    }
                    return true;
                }
            }
            return false;
        }

        void a(Throwable th) {
            AtomicReference atomicReference = this.l;
            Throwable th2;
            Object aVar;
            do {
                th2 = (Throwable) atomicReference.get();
                if (th2 == null) {
                    Throwable th3 = th;
                } else if (th2 instanceof dji.thirdparty.f.c.a) {
                    Collection arrayList = new ArrayList(((dji.thirdparty.f.c.a) th2).a());
                    arrayList.add(th);
                    aVar = new dji.thirdparty.f.c.a(arrayList);
                } else {
                    aVar = new dji.thirdparty.f.c.a(Arrays.asList(new Throwable[]{th2, th}));
                }
            } while (!atomicReference.compareAndSet(th2, aVar));
        }
    }

    public u(Iterable<? extends d<? extends T>> iterable, x<? extends R> xVar) {
        this(null, iterable, xVar, j.c, false);
    }

    public u(d<? extends T>[] dVarArr, Iterable<? extends d<? extends T>> iterable, x<? extends R> xVar, int i, boolean z) {
        this.a = dVarArr;
        this.b = iterable;
        this.c = xVar;
        this.d = i;
        this.e = z;
    }

    public void a(k<? super R> kVar) {
        int length;
        d[] dVarArr;
        d[] dVarArr2 = this.a;
        if (dVarArr2 != null) {
            length = dVarArr2.length;
            dVarArr = dVarArr2;
        } else if (this.b instanceof List) {
            List list = (List) this.b;
            dVarArr2 = (d[]) list.toArray(new d[list.size()]);
            length = dVarArr2.length;
            dVarArr = dVarArr2;
        } else {
            Object obj = new d[8];
            int i = 0;
            Object obj2 = obj;
            for (d dVar : this.b) {
                Object obj3;
                if (i == obj2.length) {
                    obj3 = new d[((i >> 2) + i)];
                    System.arraycopy(obj2, 0, obj3, 0, i);
                } else {
                    obj3 = obj2;
                }
                length = i + 1;
                obj3[i] = dVar;
                i = length;
                obj2 = obj3;
            }
            Object obj4 = obj2;
            length = i;
        }
        if (length == 0) {
            kVar.o_();
            return;
        }
        new b(kVar, this.c, length, this.d, this.e).a(dVarArr);
    }
}

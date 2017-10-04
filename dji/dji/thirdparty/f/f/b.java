package dji.thirdparty.f.f;

import dji.thirdparty.f.d;
import dji.thirdparty.f.d.c;
import dji.thirdparty.f.d.m;
import dji.thirdparty.f.d.o;
import dji.thirdparty.f.e.a.e;
import dji.thirdparty.f.e.a.f;
import dji.thirdparty.f.e.a.r;
import dji.thirdparty.f.e.d.q;
import dji.thirdparty.f.k;
import dji.thirdparty.f.l;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicReference;

public final class b<T> {
    static final Object a = new Object();
    static final Object b = new Object();
    static final Object c = new Object();
    private final d<? extends T> d;

    private b(d<? extends T> dVar) {
        this.d = dVar;
    }

    public static <T> b<T> a(d<? extends T> dVar) {
        return new b(dVar);
    }

    public void a(final c<? super T> cVar) {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final AtomicReference atomicReference = new AtomicReference();
        dji.thirdparty.f.e.d.b.a(countDownLatch, this.d.b(new k<T>(this) {
            final /* synthetic */ b d;

            public void o_() {
                countDownLatch.countDown();
            }

            public void a(Throwable th) {
                atomicReference.set(th);
                countDownLatch.countDown();
            }

            public void a_(T t) {
                cVar.a(t);
            }
        }));
        if (atomicReference.get() == null) {
            return;
        }
        if (atomicReference.get() instanceof RuntimeException) {
            throw ((RuntimeException) atomicReference.get());
        }
        throw new RuntimeException((Throwable) atomicReference.get());
    }

    public Iterator<T> a() {
        return f.a(this.d);
    }

    public T b() {
        return b(this.d.n());
    }

    public T a(o<? super T, Boolean> oVar) {
        return b(this.d.m(oVar));
    }

    public T a(T t) {
        return b(this.d.r(q.c()).e(t));
    }

    public T a(T t, o<? super T, Boolean> oVar) {
        return b(this.d.l(oVar).r(q.c()).e(t));
    }

    public T c() {
        return b(this.d.q());
    }

    public T b(o<? super T, Boolean> oVar) {
        return b(this.d.q(oVar));
    }

    public T b(T t) {
        return b(this.d.r(q.c()).f(t));
    }

    public T b(T t, o<? super T, Boolean> oVar) {
        return b(this.d.l(oVar).r(q.c()).f(t));
    }

    public Iterable<T> c(T t) {
        return dji.thirdparty.f.e.a.c.a(this.d, t);
    }

    public Iterable<T> d() {
        return dji.thirdparty.f.e.a.d.a(this.d);
    }

    public Iterable<T> e() {
        return dji.thirdparty.f.e.a.b.a(this.d);
    }

    public T f() {
        return b(this.d.B());
    }

    public T c(o<? super T, Boolean> oVar) {
        return b(this.d.y(oVar));
    }

    public T d(T t) {
        return b(this.d.r(q.c()).g(t));
    }

    public T c(T t, o<? super T, Boolean> oVar) {
        return b(this.d.l(oVar).r(q.c()).g(t));
    }

    public Future<T> g() {
        return e.a(this.d);
    }

    public Iterable<T> h() {
        return new Iterable<T>(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public Iterator<T> iterator() {
                return this.a.a();
            }
        };
    }

    private T b(d<? extends T> dVar) {
        final AtomicReference atomicReference = new AtomicReference();
        final AtomicReference atomicReference2 = new AtomicReference();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        dji.thirdparty.f.e.d.b.a(countDownLatch, dVar.b(new k<T>(this) {
            final /* synthetic */ b d;

            public void o_() {
                countDownLatch.countDown();
            }

            public void a(Throwable th) {
                atomicReference2.set(th);
                countDownLatch.countDown();
            }

            public void a_(T t) {
                atomicReference.set(t);
            }
        }));
        if (atomicReference2.get() == null) {
            return atomicReference.get();
        }
        if (atomicReference2.get() instanceof RuntimeException) {
            throw ((RuntimeException) atomicReference2.get());
        }
        throw new RuntimeException((Throwable) atomicReference2.get());
    }

    @dji.thirdparty.f.b.b
    public void i() {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final Throwable[] thArr = new Throwable[]{null};
        dji.thirdparty.f.e.d.b.a(countDownLatch, this.d.b(new k<T>(this) {
            final /* synthetic */ b c;

            public void a_(T t) {
            }

            public void a(Throwable th) {
                thArr[0] = th;
                countDownLatch.countDown();
            }

            public void o_() {
                countDownLatch.countDown();
            }
        }));
        Throwable th = thArr[0];
        if (th == null) {
            return;
        }
        if (th instanceof RuntimeException) {
            throw ((RuntimeException) th);
        }
        throw new RuntimeException(th);
    }

    @dji.thirdparty.f.b.b
    public void a(dji.thirdparty.f.e<? super T> eVar) {
        final r a = r.a();
        final BlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
        l b = this.d.b(new k<T>(this) {
            final /* synthetic */ b c;

            public void a_(T t) {
                linkedBlockingQueue.offer(a.a((Object) t));
            }

            public void a(Throwable th) {
                linkedBlockingQueue.offer(a.a(th));
            }

            public void o_() {
                linkedBlockingQueue.offer(a.b());
            }
        });
        while (true) {
            try {
                Object poll = linkedBlockingQueue.poll();
                if (poll == null) {
                    poll = linkedBlockingQueue.take();
                }
                if (a.a(eVar, poll)) {
                    break;
                }
            } catch (Throwable e) {
                Thread.currentThread().interrupt();
                eVar.a(e);
            } finally {
                b.n_();
            }
        }
    }

    @dji.thirdparty.f.b.b
    public void a(k<? super T> kVar) {
        final r a = r.a();
        final BlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
        final dji.thirdparty.f.f[] fVarArr = new dji.thirdparty.f.f[]{null};
        l anonymousClass6 = new k<T>(this) {
            final /* synthetic */ b d;

            public void a_(T t) {
                linkedBlockingQueue.offer(a.a((Object) t));
            }

            public void a(Throwable th) {
                linkedBlockingQueue.offer(a.a(th));
            }

            public void o_() {
                linkedBlockingQueue.offer(a.b());
            }

            public void a(dji.thirdparty.f.f fVar) {
                fVarArr[0] = fVar;
                linkedBlockingQueue.offer(b.b);
            }

            public void c() {
                linkedBlockingQueue.offer(b.a);
            }
        };
        kVar.a(anonymousClass6);
        kVar.a(dji.thirdparty.f.m.f.a(new dji.thirdparty.f.d.b(this) {
            final /* synthetic */ b b;

            public void a() {
                linkedBlockingQueue.offer(b.c);
            }
        }));
        this.d.b(anonymousClass6);
        while (!kVar.b()) {
            try {
                Object poll = linkedBlockingQueue.poll();
                if (poll == null) {
                    poll = linkedBlockingQueue.take();
                }
                if (kVar.b() || poll == c) {
                    break;
                } else if (poll == a) {
                    kVar.c();
                } else if (poll == b) {
                    kVar.a(fVarArr[0]);
                } else if (a.a(kVar, poll)) {
                    return;
                }
            } catch (Throwable e) {
                Thread.currentThread().interrupt();
                kVar.a(e);
                return;
            } finally {
                anonymousClass6.n_();
            }
        }
        anonymousClass6.n_();
    }

    @dji.thirdparty.f.b.b
    public void b(c<? super T> cVar) {
        a(cVar, new c<Throwable>(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void a(Throwable th) {
                throw new dji.thirdparty.f.c.f(th);
            }
        }, m.a());
    }

    @dji.thirdparty.f.b.b
    public void a(c<? super T> cVar, c<? super Throwable> cVar2) {
        a(cVar, cVar2, m.a());
    }

    @dji.thirdparty.f.b.b
    public void a(final c<? super T> cVar, final c<? super Throwable> cVar2, final dji.thirdparty.f.d.b bVar) {
        a(new dji.thirdparty.f.e<T>(this) {
            final /* synthetic */ b d;

            public void a_(T t) {
                cVar.a(t);
            }

            public void a(Throwable th) {
                cVar2.a(th);
            }

            public void o_() {
                bVar.a();
            }
        });
    }
}

package dji.thirdparty.f;

import dji.thirdparty.f.d.c;
import dji.thirdparty.f.d.n;
import dji.thirdparty.f.e.a.i;
import dji.thirdparty.f.e.a.j;
import dji.thirdparty.f.e.a.k;
import dji.thirdparty.f.e.a.l;
import dji.thirdparty.f.e.a.m;
import dji.thirdparty.f.e.a.o;
import dji.thirdparty.f.e.a.p;
import dji.thirdparty.f.e.d.q;
import dji.thirdparty.f.i.a;
import dji.thirdparty.f.i.d;
import dji.thirdparty.f.j.e;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@dji.thirdparty.f.b.b
public class b {
    static final b a = a(new 1());
    static final b b = a(new 12());
    static final a c = d.getInstance().b();
    private final a d;

    public static b a(b... bVarArr) {
        a((Object) bVarArr);
        if (bVarArr.length == 0) {
            return a();
        }
        if (bVarArr.length == 1) {
            return bVarArr[0];
        }
        return a(new 23(bVarArr));
    }

    public static b a(Iterable<? extends b> iterable) {
        a((Object) iterable);
        return a(new 28(iterable));
    }

    public static b a() {
        return a;
    }

    public static b b(b... bVarArr) {
        a((Object) bVarArr);
        if (bVarArr.length == 0) {
            return a();
        }
        if (bVarArr.length == 1) {
            return bVarArr[0];
        }
        return a(new j(bVarArr));
    }

    public static b b(Iterable<? extends b> iterable) {
        a((Object) iterable);
        return a(new k(iterable));
    }

    public static b a(d<? extends b> dVar) {
        return a((d) dVar, 2);
    }

    public static b a(d<? extends b> dVar, int i) {
        a((Object) dVar);
        if (i >= 1) {
            return a(new i(dVar, i));
        }
        throw new IllegalArgumentException("prefetch > 0 required but it was " + i);
    }

    public static b a(a aVar) {
        NullPointerException e;
        a((Object) aVar);
        try {
            return new b(aVar);
        } catch (NullPointerException e2) {
            throw e2;
        } catch (Throwable th) {
            c.a(th);
            e2 = b(th);
        }
    }

    public static b a(n<? extends b> nVar) {
        a((Object) nVar);
        return a(new 29(nVar));
    }

    public static b b(n<? extends Throwable> nVar) {
        a((Object) nVar);
        return a(new 30(nVar));
    }

    public static b a(Throwable th) {
        a((Object) th);
        return a(new 31(th));
    }

    public static b a(dji.thirdparty.f.d.b bVar) {
        a((Object) bVar);
        return a(new 32(bVar));
    }

    public static b a(Callable<?> callable) {
        a((Object) callable);
        return a(new 33(callable));
    }

    public static b a(Future<?> future) {
        a((Object) future);
        return b(d.a((Future) future));
    }

    public static b b(d<?> dVar) {
        a((Object) dVar);
        return a(new 2(dVar));
    }

    public static b a(h<?> hVar) {
        a((Object) hVar);
        return a(new 3(hVar));
    }

    public static b c(b... bVarArr) {
        a((Object) bVarArr);
        if (bVarArr.length == 0) {
            return a();
        }
        if (bVarArr.length == 1) {
            return bVarArr[0];
        }
        return a(new m(bVarArr));
    }

    public static b c(Iterable<? extends b> iterable) {
        a((Object) iterable);
        return a(new p(iterable));
    }

    public static b c(d<? extends b> dVar) {
        return a((d) dVar, Integer.MAX_VALUE, false);
    }

    public static b b(d<? extends b> dVar, int i) {
        return a((d) dVar, i, false);
    }

    protected static b a(d<? extends b> dVar, int i, boolean z) {
        a((Object) dVar);
        if (i >= 1) {
            return a(new l(dVar, i, z));
        }
        throw new IllegalArgumentException("maxConcurrency > 0 required but it was " + i);
    }

    public static b d(b... bVarArr) {
        a((Object) bVarArr);
        return a(new dji.thirdparty.f.e.a.n(bVarArr));
    }

    public static b d(Iterable<? extends b> iterable) {
        a((Object) iterable);
        return a(new o(iterable));
    }

    public static b d(d<? extends b> dVar) {
        return a((d) dVar, Integer.MAX_VALUE, true);
    }

    public static b c(d<? extends b> dVar, int i) {
        return a((d) dVar, i, true);
    }

    public static b b() {
        return b;
    }

    static <T> T a(T t) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException();
    }

    public static b a(long j, TimeUnit timeUnit) {
        return a(j, timeUnit, e.d());
    }

    public static b a(long j, TimeUnit timeUnit, g gVar) {
        a((Object) timeUnit);
        a((Object) gVar);
        return a(new 4(gVar, j, timeUnit));
    }

    static NullPointerException b(Throwable th) {
        NullPointerException nullPointerException = new NullPointerException("Actually not, but can't pass out an exception otherwise...");
        nullPointerException.initCause(th);
        return nullPointerException;
    }

    public static <R> b a(n<R> nVar, dji.thirdparty.f.d.o<? super R, ? extends b> oVar, c<? super R> cVar) {
        return a((n) nVar, (dji.thirdparty.f.d.o) oVar, (c) cVar, true);
    }

    public static <R> b a(n<R> nVar, dji.thirdparty.f.d.o<? super R, ? extends b> oVar, c<? super R> cVar, boolean z) {
        a((Object) nVar);
        a((Object) oVar);
        a((Object) cVar);
        return a(new 5(nVar, oVar, cVar, z));
    }

    protected b(a aVar) {
        this.d = aVar;
    }

    public final b a(b bVar) {
        a((Object) bVar);
        return a(this, bVar);
    }

    public final void c() {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Throwable[] thArr = new Throwable[1];
        a(new 6(this, countDownLatch, thArr));
        if (countDownLatch.getCount() != 0) {
            try {
                countDownLatch.await();
                if (thArr[0] != null) {
                    dji.thirdparty.f.c.b.a(thArr[0]);
                }
            } catch (Throwable e) {
                throw dji.thirdparty.f.c.b.a(e);
            }
        } else if (thArr[0] != null) {
            dji.thirdparty.f.c.b.a(thArr[0]);
        }
    }

    public final boolean b(long j, TimeUnit timeUnit) {
        boolean z = true;
        a((Object) timeUnit);
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Throwable[] thArr = new Throwable[1];
        a(new 7(this, countDownLatch, thArr));
        if (countDownLatch.getCount() != 0) {
            try {
                z = countDownLatch.await(j, timeUnit);
                if (z && thArr[0] != null) {
                    dji.thirdparty.f.c.b.a(thArr[0]);
                }
            } catch (Throwable e) {
                throw dji.thirdparty.f.c.b.a(e);
            }
        } else if (thArr[0] != null) {
            dji.thirdparty.f.c.b.a(thArr[0]);
        }
        return z;
    }

    public final b a(d dVar) {
        return (b) e((dji.thirdparty.f.d.o) dVar);
    }

    public final <T> d<T> e(d<T> dVar) {
        a((Object) dVar);
        return dVar.k(i());
    }

    public final b b(b bVar) {
        a((Object) bVar);
        return b(this, bVar);
    }

    public final b c(long j, TimeUnit timeUnit) {
        return a(j, timeUnit, e.d(), false);
    }

    public final b b(long j, TimeUnit timeUnit, g gVar) {
        return a(j, timeUnit, gVar, false);
    }

    public final b a(long j, TimeUnit timeUnit, g gVar, boolean z) {
        a((Object) timeUnit);
        a((Object) gVar);
        return a(new 8(this, gVar, j, timeUnit, z));
    }

    @Deprecated
    public final b b(dji.thirdparty.f.d.b bVar) {
        return c(bVar);
    }

    public final b c(dji.thirdparty.f.d.b bVar) {
        return a(dji.thirdparty.f.d.m.a(), dji.thirdparty.f.d.m.a(), bVar, dji.thirdparty.f.d.m.a(), dji.thirdparty.f.d.m.a());
    }

    public final b d(dji.thirdparty.f.d.b bVar) {
        return a(dji.thirdparty.f.d.m.a(), dji.thirdparty.f.d.m.a(), dji.thirdparty.f.d.m.a(), dji.thirdparty.f.d.m.a(), bVar);
    }

    public final b a(c<? super Throwable> cVar) {
        return a(dji.thirdparty.f.d.m.a(), cVar, dji.thirdparty.f.d.m.a(), dji.thirdparty.f.d.m.a(), dji.thirdparty.f.d.m.a());
    }

    protected final b a(c<? super l> cVar, c<? super Throwable> cVar2, dji.thirdparty.f.d.b bVar, dji.thirdparty.f.d.b bVar2, dji.thirdparty.f.d.b bVar3) {
        a((Object) cVar);
        a((Object) cVar2);
        a((Object) bVar);
        a((Object) bVar2);
        a((Object) bVar3);
        return a(new 9(this, bVar, bVar2, cVar2, cVar, bVar3));
    }

    public final b b(c<? super l> cVar) {
        return a(cVar, dji.thirdparty.f.d.m.a(), dji.thirdparty.f.d.m.a(), dji.thirdparty.f.d.m.a(), dji.thirdparty.f.d.m.a());
    }

    public final b e(dji.thirdparty.f.d.b bVar) {
        return a(dji.thirdparty.f.d.m.a(), new 10(this, bVar), bVar, dji.thirdparty.f.d.m.a(), dji.thirdparty.f.d.m.a());
    }

    public final b c(b bVar) {
        return b(bVar);
    }

    public final <T> d<T> f(d<T> dVar) {
        return dVar.r(i());
    }

    public final b f(dji.thirdparty.f.d.b bVar) {
        return a(dji.thirdparty.f.d.m.a(), dji.thirdparty.f.d.m.a(), dji.thirdparty.f.d.m.a(), bVar, dji.thirdparty.f.d.m.a());
    }

    public final Throwable d() {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Throwable[] thArr = new Throwable[1];
        a(new 11(this, countDownLatch, thArr));
        if (countDownLatch.getCount() == 0) {
            return thArr[0];
        }
        try {
            countDownLatch.await();
            return thArr[0];
        } catch (Throwable e) {
            throw dji.thirdparty.f.c.b.a(e);
        }
    }

    public final Throwable d(long j, TimeUnit timeUnit) {
        a((Object) timeUnit);
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Throwable[] thArr = new Throwable[1];
        a(new 13(this, countDownLatch, thArr));
        if (countDownLatch.getCount() == 0) {
            return thArr[0];
        }
        try {
            if (countDownLatch.await(j, timeUnit)) {
                return thArr[0];
            }
            dji.thirdparty.f.c.b.a(new TimeoutException());
            return null;
        } catch (Throwable e) {
            throw dji.thirdparty.f.c.b.a(e);
        }
    }

    public final b a(b bVar) {
        a((Object) bVar);
        return a(new 14(this, bVar));
    }

    public final b d(b bVar) {
        a((Object) bVar);
        return c(this, bVar);
    }

    public final b a(g gVar) {
        a((Object) gVar);
        return a(new 15(this, gVar));
    }

    public final b e() {
        return a(q.a());
    }

    public final b a(dji.thirdparty.f.d.o<? super Throwable, Boolean> oVar) {
        a((Object) oVar);
        return a(new 16(this, oVar));
    }

    public final b b(dji.thirdparty.f.d.o<? super Throwable, ? extends b> oVar) {
        a((Object) oVar);
        return a(new 17(this, oVar));
    }

    public final b f() {
        return b(i().w());
    }

    public final b a(long j) {
        return b(i().b(j));
    }

    public final b c(dji.thirdparty.f.d.o<? super d<? extends Void>, ? extends d<?>> oVar) {
        a((Object) oVar);
        return b(i().v(oVar));
    }

    public final b g() {
        return b(i().y());
    }

    public final b a(dji.thirdparty.f.d.p<Integer, Throwable, Boolean> pVar) {
        return b(i().b((dji.thirdparty.f.d.p) pVar));
    }

    public final b b(long j) {
        return b(i().c(j));
    }

    public final b d(dji.thirdparty.f.d.o<? super d<? extends Throwable>, ? extends d<?>> oVar) {
        return b(i().x(oVar));
    }

    public final b e(b bVar) {
        a((Object) bVar);
        return b(bVar, this);
    }

    public final <T> d<T> g(d<T> dVar) {
        a((Object) dVar);
        return i().r((d) dVar);
    }

    public final l h() {
        l cVar = new dji.thirdparty.f.m.c();
        a(new 18(this, cVar));
        return cVar;
    }

    public final l g(dji.thirdparty.f.d.b bVar) {
        a((Object) bVar);
        l cVar = new dji.thirdparty.f.m.c();
        a(new 19(this, bVar, cVar));
        return cVar;
    }

    public final l a(c<? super Throwable> cVar, dji.thirdparty.f.d.b bVar) {
        a((Object) cVar);
        a((Object) bVar);
        l cVar2 = new dji.thirdparty.f.m.c();
        a(new 20(this, bVar, cVar2, cVar));
        return cVar2;
    }

    private static void d(Throwable th) {
        Thread currentThread = Thread.currentThread();
        currentThread.getUncaughtExceptionHandler().uncaughtException(currentThread, th);
    }

    public final void a(c cVar) {
        NullPointerException e;
        a((Object) cVar);
        try {
            this.d.a(cVar);
        } catch (NullPointerException e2) {
            throw e2;
        } catch (Throwable th) {
            c.a(th);
            e2 = b(th);
        }
    }

    public final <T> void a(k<T> kVar) {
        NullPointerException e;
        a((Object) kVar);
        if (kVar == null) {
            try {
                throw new NullPointerException("The RxJavaPlugins.onSubscribe returned a null Subscriber");
            } catch (NullPointerException e2) {
                throw e2;
            } catch (Throwable th) {
                c.a(th);
                e2 = b(th);
            }
        } else {
            a(new 21(this, kVar));
        }
    }

    public final b b(g gVar) {
        a((Object) gVar);
        return a(new 22(this, gVar));
    }

    public final b e(long j, TimeUnit timeUnit) {
        return b(j, timeUnit, e.d(), null);
    }

    public final b a(long j, TimeUnit timeUnit, b bVar) {
        a((Object) bVar);
        return b(j, timeUnit, e.d(), bVar);
    }

    public final b c(long j, TimeUnit timeUnit, g gVar) {
        return b(j, timeUnit, gVar, null);
    }

    public final b a(long j, TimeUnit timeUnit, g gVar, b bVar) {
        a((Object) bVar);
        return b(j, timeUnit, gVar, bVar);
    }

    public final b b(long j, TimeUnit timeUnit, g gVar, b bVar) {
        a((Object) timeUnit);
        a((Object) gVar);
        return a(new dji.thirdparty.f.e.a.q(this, j, timeUnit, gVar, bVar));
    }

    public final <U> U e(dji.thirdparty.f.d.o<? super b, U> oVar) {
        return oVar.a(this);
    }

    public final <T> d<T> i() {
        return d.a(new 24(this));
    }

    public final <T> h<T> c(n<? extends T> nVar) {
        a((Object) nVar);
        return h.a(new 25(this, nVar));
    }

    public final <T> h<T> b(T t) {
        a((Object) t);
        return c(new 26(this, t));
    }

    public final b c(g gVar) {
        a((Object) gVar);
        return a(new 27(this, gVar));
    }
}

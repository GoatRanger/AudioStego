package dji.thirdparty.b;

import dji.thirdparty.b.a.b.r;
import dji.thirdparty.b.a.c.b;
import dji.thirdparty.b.a.d;
import dji.thirdparty.b.a.i;
import dji.thirdparty.b.a.j;
import java.lang.ref.Reference;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class k {
    static final /* synthetic */ boolean c = (!k.class.desiredAssertionStatus());
    private static final Executor d = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue(), j.a("OkHttp ConnectionPool", true));
    final i a;
    boolean b;
    private final int e;
    private final long f;
    private final Runnable g;
    private final Deque<b> h;

    public k() {
        this(5, 5, TimeUnit.MINUTES);
    }

    public k(int i, long j, TimeUnit timeUnit) {
        this.g = new Runnable(this) {
            final /* synthetic */ k a;

            {
                this.a = r1;
            }

            public void run() {
                while (true) {
                    long a = this.a.a(System.nanoTime());
                    if (a != -1) {
                        if (a > 0) {
                            long j = a / 1000000;
                            a -= j * 1000000;
                            synchronized (this.a) {
                                try {
                                    this.a.wait(j, (int) a);
                                } catch (InterruptedException e) {
                                }
                            }
                        }
                    } else {
                        return;
                    }
                }
            }
        };
        this.h = new ArrayDeque();
        this.a = new i();
        this.e = i;
        this.f = timeUnit.toNanos(j);
        if (j <= 0) {
            throw new IllegalArgumentException("keepAliveDuration <= 0: " + j);
        }
    }

    public synchronized int a() {
        int i;
        i = 0;
        for (b bVar : this.h) {
            int i2;
            if (bVar.h.isEmpty()) {
                i2 = i + 1;
            } else {
                i2 = i;
            }
            i = i2;
        }
        return i;
    }

    public synchronized int b() {
        return this.h.size();
    }

    b a(a aVar, r rVar) {
        if (c || Thread.holdsLock(this)) {
            for (b bVar : this.h) {
                if (bVar.h.size() < bVar.g && aVar.equals(bVar.a().a) && !bVar.i) {
                    rVar.a(bVar);
                    return bVar;
                }
            }
            return null;
        }
        throw new AssertionError();
    }

    void a(b bVar) {
        if (c || Thread.holdsLock(this)) {
            if (!this.b) {
                this.b = true;
                d.execute(this.g);
            }
            this.h.add(bVar);
            return;
        }
        throw new AssertionError();
    }

    boolean b(b bVar) {
        if (!c && !Thread.holdsLock(this)) {
            throw new AssertionError();
        } else if (bVar.i || this.e == 0) {
            this.h.remove(bVar);
            return true;
        } else {
            notifyAll();
            return false;
        }
    }

    public void c() {
        List<b> arrayList = new ArrayList();
        synchronized (this) {
            Iterator it = this.h.iterator();
            while (it.hasNext()) {
                b bVar = (b) it.next();
                if (bVar.h.isEmpty()) {
                    bVar.i = true;
                    arrayList.add(bVar);
                    it.remove();
                }
            }
        }
        for (b bVar2 : arrayList) {
            j.a(bVar2.b());
        }
    }

    long a(long j) {
        b bVar = null;
        long j2 = Long.MIN_VALUE;
        synchronized (this) {
            long j3;
            int i = 0;
            int i2 = 0;
            for (b bVar2 : this.h) {
                if (a(bVar2, j) > 0) {
                    i2++;
                } else {
                    b bVar3;
                    int i3 = i + 1;
                    long j4 = j - bVar2.j;
                    if (j4 > j2) {
                        long j5 = j4;
                        bVar3 = bVar2;
                        j3 = j5;
                    } else {
                        bVar3 = bVar;
                        j3 = j2;
                    }
                    j2 = j3;
                    bVar = bVar3;
                    i = i3;
                }
            }
            if (j2 >= this.f || i > this.e) {
                this.h.remove(bVar);
                j.a(bVar.b());
                return 0;
            } else if (i > 0) {
                j3 = this.f - j2;
                return j3;
            } else if (i2 > 0) {
                j3 = this.f;
                return j3;
            } else {
                this.b = false;
                return -1;
            }
        }
    }

    private int a(b bVar, long j) {
        List list = bVar.h;
        int i = 0;
        while (i < list.size()) {
            if (((Reference) list.get(i)).get() != null) {
                i++;
            } else {
                d.a.warning("A connection to " + bVar.a().a().a() + " was leaked. Did you forget to close a response body?");
                list.remove(i);
                bVar.i = true;
                if (list.isEmpty()) {
                    bVar.j = j - this.f;
                    return 0;
                }
            }
        }
        return list.size();
    }
}

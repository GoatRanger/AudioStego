package dji.thirdparty.b;

import dji.thirdparty.b.a.j;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class p {
    private int a = 64;
    private int b = 5;
    private ExecutorService c;
    private final Deque<b> d = new ArrayDeque();
    private final Deque<b> e = new ArrayDeque();
    private final Deque<aa> f = new ArrayDeque();

    public p(ExecutorService executorService) {
        this.c = executorService;
    }

    public synchronized ExecutorService a() {
        if (this.c == null) {
            this.c = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue(), j.a("OkHttp Dispatcher", false));
        }
        return this.c;
    }

    public synchronized void a(int i) {
        if (i < 1) {
            throw new IllegalArgumentException("max < 1: " + i);
        }
        this.a = i;
        i();
    }

    public synchronized int b() {
        return this.a;
    }

    public synchronized void b(int i) {
        if (i < 1) {
            throw new IllegalArgumentException("max < 1: " + i);
        }
        this.b = i;
        i();
    }

    public synchronized int c() {
        return this.b;
    }

    synchronized void a(b bVar) {
        if (this.e.size() >= this.a || c(bVar) >= this.b) {
            this.d.add(bVar);
        } else {
            this.e.add(bVar);
            a().execute(bVar);
        }
    }

    public synchronized void d() {
        for (b d : this.d) {
            d.d();
        }
        for (b d2 : this.e) {
            d2.d();
        }
        for (aa c : this.f) {
            c.c();
        }
    }

    synchronized void b(b bVar) {
        if (this.e.remove(bVar)) {
            i();
        } else {
            throw new AssertionError("AsyncCall wasn't running!");
        }
    }

    private void i() {
        if (this.e.size() < this.a && !this.d.isEmpty()) {
            Iterator it = this.d.iterator();
            while (it.hasNext()) {
                b bVar = (b) it.next();
                if (c(bVar) < this.b) {
                    it.remove();
                    this.e.add(bVar);
                    a().execute(bVar);
                }
                if (this.e.size() >= this.a) {
                    return;
                }
            }
        }
    }

    private int c(b bVar) {
        int i = 0;
        for (b a : this.e) {
            int i2;
            if (a.a().equals(bVar.a())) {
                i2 = i + 1;
            } else {
                i2 = i;
            }
            i = i2;
        }
        return i;
    }

    synchronized void a(aa aaVar) {
        this.f.add(aaVar);
    }

    synchronized void a(e eVar) {
        if (!this.f.remove(eVar)) {
            throw new AssertionError("Call wasn't in-flight!");
        }
    }

    public synchronized List<e> e() {
        List arrayList;
        arrayList = new ArrayList();
        for (b e : this.d) {
            arrayList.add(e.e());
        }
        return Collections.unmodifiableList(arrayList);
    }

    public synchronized List<e> f() {
        List arrayList;
        arrayList = new ArrayList();
        arrayList.addAll(this.f);
        for (b e : this.e) {
            arrayList.add(e.e());
        }
        return Collections.unmodifiableList(arrayList);
    }

    public synchronized int g() {
        return this.d.size();
    }

    public synchronized int h() {
        return this.e.size() + this.f.size();
    }
}

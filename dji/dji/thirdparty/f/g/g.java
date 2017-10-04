package dji.thirdparty.f.g;

import dji.thirdparty.f.c;
import dji.thirdparty.f.c.a;
import dji.thirdparty.f.e;
import dji.thirdparty.f.k;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class g<T> extends k<T> {
    private static final e<Object> d = new e<Object>() {
        public void o_() {
        }

        public void a(Throwable th) {
        }

        public void a_(Object obj) {
        }
    };
    private final f<T> a;
    private final CountDownLatch b;
    private volatile Thread c;

    public g(long j) {
        this(d, j);
    }

    public g(e<T> eVar, long j) {
        this.b = new CountDownLatch(1);
        if (eVar == null) {
            throw new NullPointerException();
        }
        this.a = new f(eVar);
        if (j >= 0) {
            a(j);
        }
    }

    public g(k<T> kVar) {
        this(kVar, -1);
    }

    public g(e<T> eVar) {
        this(eVar, -1);
    }

    public g() {
        this(-1);
    }

    public static <T> g<T> d() {
        return new g();
    }

    public static <T> g<T> b(long j) {
        return new g(j);
    }

    public static <T> g<T> a(e<T> eVar, long j) {
        return new g(eVar, j);
    }

    public static <T> g<T> a(k<T> kVar) {
        return new g((k) kVar);
    }

    public static <T> g<T> a(e<T> eVar) {
        return new g((e) eVar);
    }

    public void o_() {
        try {
            this.c = Thread.currentThread();
            this.a.o_();
        } finally {
            this.b.countDown();
        }
    }

    public List<c<T>> e() {
        return this.a.b();
    }

    public void a(Throwable th) {
        try {
            this.c = Thread.currentThread();
            this.a.a(th);
        } finally {
            this.b.countDown();
        }
    }

    public List<Throwable> f() {
        return this.a.c();
    }

    public void a_(T t) {
        this.c = Thread.currentThread();
        this.a.a_(t);
    }

    public void c(long j) {
        a(j);
    }

    public List<T> g() {
        return this.a.d();
    }

    public void a(List<T> list) {
        this.a.a((List) list);
    }

    public void h() {
        this.a.f();
    }

    public void i() {
        if (!b()) {
            throw new AssertionError("Not unsubscribed.");
        }
    }

    public void j() {
        Collection f = f();
        if (f.size() > 0) {
            AssertionError assertionError = new AssertionError("Unexpected onError events: " + f().size());
            if (f.size() == 1) {
                assertionError.initCause((Throwable) f().get(0));
            } else {
                assertionError.initCause(new a(f));
            }
            throw assertionError;
        }
    }

    public void k() {
        try {
            this.b.await();
        } catch (Throwable e) {
            throw new RuntimeException("Interrupted", e);
        }
    }

    public void a(long j, TimeUnit timeUnit) {
        try {
            this.b.await(j, timeUnit);
        } catch (Throwable e) {
            throw new RuntimeException("Interrupted", e);
        }
    }

    public void b(long j, TimeUnit timeUnit) {
        try {
            if (!this.b.await(j, timeUnit)) {
                n_();
            }
        } catch (InterruptedException e) {
            n_();
        }
    }

    public Thread l() {
        return this.c;
    }

    public void m() {
        int size = this.a.b().size();
        if (size == 0) {
            throw new AssertionError("Not completed!");
        } else if (size > 1) {
            throw new AssertionError("Completed multiple times: " + size);
        }
    }

    public void n() {
        int size = this.a.b().size();
        if (size == 1) {
            throw new AssertionError("Completed!");
        } else if (size > 1) {
            throw new AssertionError("Completed multiple times: " + size);
        }
    }

    public void a(Class<? extends Throwable> cls) {
        Collection c = this.a.c();
        if (c.size() == 0) {
            throw new AssertionError("No errors");
        } else if (c.size() > 1) {
            r1 = new AssertionError("Multiple errors: " + c.size());
            r1.initCause(new a(c));
            throw r1;
        } else if (!cls.isInstance(c.get(0))) {
            r1 = new AssertionError("Exceptions differ; expected: " + cls + ", actual: " + c.get(0));
            r1.initCause((Throwable) c.get(0));
            throw r1;
        }
    }

    public void b(Throwable th) {
        Collection c = this.a.c();
        if (c.size() == 0) {
            throw new AssertionError("No errors");
        } else if (c.size() > 1) {
            r1 = new AssertionError("Multiple errors: " + c.size());
            r1.initCause(new a(c));
            throw r1;
        } else if (!th.equals(c.get(0))) {
            r1 = new AssertionError("Exceptions differ; expected: " + th + ", actual: " + c.get(0));
            r1.initCause((Throwable) c.get(0));
            throw r1;
        }
    }

    public void o() {
        Collection c = this.a.c();
        int size = this.a.b().size();
        if (c.size() <= 0 && size <= 0) {
            return;
        }
        if (c.isEmpty()) {
            throw new AssertionError("Found " + c.size() + " errors and " + size + " completion events instead of none");
        } else if (c.size() == 1) {
            r2 = new AssertionError("Found " + c.size() + " errors and " + size + " completion events instead of none");
            r2.initCause((Throwable) c.get(0));
            throw r2;
        } else {
            r2 = new AssertionError("Found " + c.size() + " errors and " + size + " completion events instead of none");
            r2.initCause(new a(c));
            throw r2;
        }
    }

    public void p() {
        int size = this.a.d().size();
        if (size > 0) {
            throw new AssertionError("No onNext events expected yet some received: " + size);
        }
    }

    public void a(int i) {
        int size = this.a.d().size();
        if (size != i) {
            throw new AssertionError("Number of onNext events differ; expected: " + i + ", actual: " + size);
        }
    }

    public void a(T... tArr) {
        a(Arrays.asList(tArr));
    }

    public void b(T t) {
        a(Collections.singletonList(t));
    }
}

package dji.thirdparty.f.e.a;

import dji.thirdparty.f.c;
import dji.thirdparty.f.d;
import dji.thirdparty.f.k;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReference;

public final class b {

    static final class a<T> extends k<c<? extends T>> implements Iterator<T> {
        final Semaphore a = new Semaphore(0);
        final AtomicReference<c<? extends T>> b = new AtomicReference();
        c<? extends T> c;

        a() {
        }

        public /* synthetic */ void a_(Object obj) {
            a((c) obj);
        }

        public void a(c<? extends T> cVar) {
            if ((this.b.getAndSet(cVar) == null ? 1 : null) != null) {
                this.a.release();
            }
        }

        public void a(Throwable th) {
        }

        public void o_() {
        }

        public boolean hasNext() {
            if (this.c == null || !this.c.g()) {
                if ((this.c == null || !this.c.h()) && this.c == null) {
                    try {
                        this.a.acquire();
                        this.c = (c) this.b.getAndSet(null);
                        if (this.c.g()) {
                            throw dji.thirdparty.f.c.b.a(this.c.b());
                        }
                    } catch (Throwable e) {
                        n_();
                        Thread.currentThread().interrupt();
                        this.c = c.a(e);
                        throw dji.thirdparty.f.c.b.a(e);
                    }
                }
                return !this.c.h();
            } else {
                throw dji.thirdparty.f.c.b.a(this.c.b());
            }
        }

        public T next() {
            if (hasNext() && this.c.i()) {
                T c = this.c.c();
                this.c = null;
                return c;
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            throw new UnsupportedOperationException("Read-only iterator.");
        }
    }

    private b() {
        throw new IllegalStateException("No instances!");
    }

    public static <T> Iterable<T> a(final d<? extends T> dVar) {
        return new Iterable<T>() {
            public Iterator<T> iterator() {
                Object aVar = new a();
                dVar.r().b(aVar);
                return aVar;
            }
        };
    }
}

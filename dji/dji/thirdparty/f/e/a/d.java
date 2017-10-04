package dji.thirdparty.f.e.a;

import dji.thirdparty.f.c;
import dji.thirdparty.f.k;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public final class d {

    static final class a<T> implements Iterator<T> {
        private final b<T> a;
        private final dji.thirdparty.f.d<? extends T> b;
        private T c;
        private boolean d = true;
        private boolean e = true;
        private Throwable f = null;
        private boolean g = false;

        a(dji.thirdparty.f.d<? extends T> dVar, b<T> bVar) {
            this.b = dVar;
            this.a = bVar;
        }

        public boolean hasNext() {
            if (this.f != null) {
                throw dji.thirdparty.f.c.b.a(this.f);
            } else if (!this.d) {
                return false;
            } else {
                if (this.e) {
                    return a();
                }
                return true;
            }
        }

        private boolean a() {
            try {
                if (!this.g) {
                    this.g = true;
                    this.a.a(1);
                    this.b.r().b(this.a);
                }
                c d = this.a.d();
                if (d.i()) {
                    this.e = false;
                    this.c = d.c();
                    return true;
                }
                this.d = false;
                if (d.h()) {
                    return false;
                }
                if (d.g()) {
                    this.f = d.b();
                    throw dji.thirdparty.f.c.b.a(this.f);
                }
                throw new IllegalStateException("Should not reach here");
            } catch (Throwable e) {
                this.a.n_();
                Thread.currentThread().interrupt();
                this.f = e;
                throw dji.thirdparty.f.c.b.a(this.f);
            }
        }

        public T next() {
            if (this.f != null) {
                throw dji.thirdparty.f.c.b.a(this.f);
            } else if (hasNext()) {
                this.e = true;
                return this.c;
            } else {
                throw new NoSuchElementException("No more elements");
            }
        }

        public void remove() {
            throw new UnsupportedOperationException("Read only iterator");
        }
    }

    private static class b<T> extends k<c<? extends T>> {
        final AtomicInteger a = new AtomicInteger();
        private final BlockingQueue<c<? extends T>> b = new ArrayBlockingQueue(1);

        public /* synthetic */ void a_(Object obj) {
            a((c) obj);
        }

        b() {
        }

        public void o_() {
        }

        public void a(Throwable th) {
        }

        public void a(c<? extends T> cVar) {
            if (this.a.getAndSet(0) == 1 || !cVar.i()) {
                while (!this.b.offer(cVar)) {
                    c<? extends T> cVar2 = (c) this.b.poll();
                    if (!(cVar2 == null || cVar2.i())) {
                        cVar = cVar2;
                    }
                }
            }
        }

        public c<? extends T> d() throws InterruptedException {
            a(1);
            return (c) this.b.take();
        }

        void a(int i) {
            this.a.set(i);
        }
    }

    private d() {
        throw new IllegalStateException("No instances!");
    }

    public static <T> Iterable<T> a(final dji.thirdparty.f.d<? extends T> dVar) {
        return new Iterable<T>() {
            public Iterator<T> iterator() {
                return new a(dVar, new b());
            }
        };
    }
}

package dji.thirdparty.f.e.a;

import dji.thirdparty.f.c;
import dji.thirdparty.f.c.b;
import dji.thirdparty.f.d;
import dji.thirdparty.f.e.d.j;
import dji.thirdparty.f.k;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public final class f {

    public static final class a<T> extends k<c<? extends T>> implements Iterator<T> {
        static final int a = ((j.c * 3) / 4);
        private final BlockingQueue<c<? extends T>> b = new LinkedBlockingQueue();
        private c<? extends T> c;
        private int d;

        public /* synthetic */ void a_(Object obj) {
            a((c) obj);
        }

        public void c() {
            a((long) j.c);
        }

        public void o_() {
        }

        public void a(Throwable th) {
            this.b.offer(c.a(th));
        }

        public void a(c<? extends T> cVar) {
            this.b.offer(cVar);
        }

        public boolean hasNext() {
            if (this.c == null) {
                this.c = d();
                this.d++;
                if (this.d >= a) {
                    a((long) this.d);
                    this.d = 0;
                }
            }
            if (this.c.g()) {
                throw b.a(this.c.b());
            } else if (this.c.h()) {
                return false;
            } else {
                return true;
            }
        }

        public T next() {
            if (hasNext()) {
                T c = this.c.c();
                this.c = null;
                return c;
            }
            throw new NoSuchElementException();
        }

        private c<? extends T> d() {
            try {
                c<? extends T> cVar = (c) this.b.poll();
                if (cVar == null) {
                    cVar = (c) this.b.take();
                }
                return cVar;
            } catch (Throwable e) {
                n_();
                throw b.a(e);
            }
        }

        public void remove() {
            throw new UnsupportedOperationException("Read-only iterator");
        }
    }

    private f() {
        throw new IllegalStateException("No instances!");
    }

    public static <T> Iterator<T> a(d<? extends T> dVar) {
        Object aVar = new a();
        dVar.r().b(aVar);
        return aVar;
    }
}

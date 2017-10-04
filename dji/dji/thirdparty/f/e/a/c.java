package dji.thirdparty.f.e.a;

import dji.thirdparty.f.c.b;
import dji.thirdparty.f.d;
import dji.thirdparty.f.k;
import java.util.Iterator;
import java.util.NoSuchElementException;

public final class c {

    private static final class a<T> extends k<T> {
        final r<T> a = r.a();
        volatile Object b;

        a(T t) {
            this.b = this.a.a((Object) t);
        }

        public void o_() {
            this.b = this.a.b();
        }

        public void a(Throwable th) {
            this.b = this.a.a(th);
        }

        public void a_(T t) {
            this.b = this.a.a((Object) t);
        }

        public Iterator<T> d() {
            return new Iterator<T>(this) {
                final /* synthetic */ a a;
                private Object b = null;

                {
                    this.a = r2;
                }

                public boolean hasNext() {
                    this.b = this.a.b;
                    return !this.a.a.b(this.b);
                }

                public T next() {
                    try {
                        if (this.b == null) {
                            Object obj = this.a.b;
                        }
                        if (this.a.a.b(this.b)) {
                            throw new NoSuchElementException();
                        } else if (this.a.a.c(this.b)) {
                            throw b.a(this.a.a.h(this.b));
                        } else {
                            T g = this.a.a.g(this.b);
                            this.b = null;
                            return g;
                        }
                    } finally {
                        this.b = null;
                    }
                }

                public void remove() {
                    throw new UnsupportedOperationException("Read only iterator");
                }
            };
        }
    }

    private c() {
        throw new IllegalStateException("No instances!");
    }

    public static <T> Iterable<T> a(final d<? extends T> dVar, final T t) {
        return new Iterable<T>() {
            public Iterator<T> iterator() {
                k aVar = new a(t);
                dVar.b(aVar);
                return aVar.d();
            }
        };
    }
}

package dji.thirdparty.f.e.a;

import dji.thirdparty.f.d$g;
import dji.thirdparty.f.f;
import dji.thirdparty.f.k;
import dji.thirdparty.f.l;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

public final class as<T> implements d$g<List<T>, T> {
    final int a;
    final int b;

    static final class a<T> extends k<T> {
        final k<? super List<T>> a;
        final int b;
        List<T> c;

        public a(k<? super List<T>> kVar, int i) {
            this.a = kVar;
            this.b = i;
            a(0);
        }

        public void a_(T t) {
            List list = this.c;
            if (list == null) {
                list = new ArrayList(this.b);
                this.c = list;
            }
            list.add(t);
            if (list.size() == this.b) {
                this.c = null;
                this.a.a_(list);
            }
        }

        public void a(Throwable th) {
            this.c = null;
            this.a.a(th);
        }

        public void o_() {
            List list = this.c;
            if (list != null) {
                this.a.a_(list);
            }
            this.a.o_();
        }

        f d() {
            return new f(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void a(long j) {
                    if (j < 0) {
                        throw new IllegalArgumentException("n >= required but it was " + j);
                    } else if (j != 0) {
                        this.a.a(a.a(j, (long) this.a.b));
                    }
                }
            };
        }
    }

    static final class b<T> extends k<T> {
        final k<? super List<T>> a;
        final int b;
        final int c;
        long d;
        final ArrayDeque<List<T>> e = new ArrayDeque();
        final AtomicLong f = new AtomicLong();
        long g;

        final class a extends AtomicBoolean implements f {
            private static final long b = -4015894850868853147L;
            final /* synthetic */ b a;

            a(b bVar) {
                this.a = bVar;
            }

            public void a(long j) {
                b bVar = this.a;
                if (a.a(bVar.f, j, bVar.e, bVar.a) && j != 0) {
                    if (get() || !compareAndSet(false, true)) {
                        bVar.a(a.a((long) bVar.c, j));
                    } else {
                        bVar.a(a.b(a.a((long) bVar.c, j - 1), (long) bVar.b));
                    }
                }
            }
        }

        public b(k<? super List<T>> kVar, int i, int i2) {
            this.a = kVar;
            this.b = i;
            this.c = i2;
            a(0);
        }

        public void a_(T t) {
            long j = this.d;
            if (j == 0) {
                this.e.offer(new ArrayList(this.b));
            }
            j++;
            if (j == ((long) this.c)) {
                this.d = 0;
            } else {
                this.d = j;
            }
            Iterator it = this.e.iterator();
            while (it.hasNext()) {
                ((List) it.next()).add(t);
            }
            List list = (List) this.e.peek();
            if (list != null && list.size() == this.b) {
                this.e.poll();
                this.g++;
                this.a.a_(list);
            }
        }

        public void a(Throwable th) {
            this.e.clear();
            this.a.a(th);
        }

        public void o_() {
            long j = this.g;
            if (j != 0) {
                if (j > this.f.get()) {
                    this.a.a(new dji.thirdparty.f.c.c("More produced than requested? " + j));
                    return;
                }
                this.f.addAndGet(-j);
            }
            a.a(this.f, this.e, this.a);
        }

        f d() {
            return new a(this);
        }
    }

    static final class c<T> extends k<T> {
        final k<? super List<T>> a;
        final int b;
        final int c;
        long d;
        List<T> e;

        final class a extends AtomicBoolean implements f {
            private static final long b = 3428177408082367154L;
            final /* synthetic */ c a;

            a(c cVar) {
                this.a = cVar;
            }

            public void a(long j) {
                if (j < 0) {
                    throw new IllegalArgumentException("n >= 0 required but it was " + j);
                } else if (j != 0) {
                    c cVar = this.a;
                    if (get() || !compareAndSet(false, true)) {
                        cVar.a(a.a(j, (long) cVar.c));
                    } else {
                        cVar.a(a.b(a.a(j, (long) cVar.b), a.a((long) (cVar.c - cVar.b), j - 1)));
                    }
                }
            }
        }

        public c(k<? super List<T>> kVar, int i, int i2) {
            this.a = kVar;
            this.b = i;
            this.c = i2;
            a(0);
        }

        public void a_(T t) {
            long j = this.d;
            List list = this.e;
            if (j == 0) {
                list = new ArrayList(this.b);
                this.e = list;
            }
            j++;
            if (j == ((long) this.c)) {
                this.d = 0;
            } else {
                this.d = j;
            }
            if (list != null) {
                list.add(t);
                if (list.size() == this.b) {
                    this.e = null;
                    this.a.a_(list);
                }
            }
        }

        public void a(Throwable th) {
            this.e = null;
            this.a.a(th);
        }

        public void o_() {
            List list = this.e;
            if (list != null) {
                this.e = null;
                this.a.a_(list);
            }
            this.a.o_();
        }

        f d() {
            return new a(this);
        }
    }

    public as(int i, int i2) {
        if (i <= 0) {
            throw new IllegalArgumentException("count must be greater than 0");
        } else if (i2 <= 0) {
            throw new IllegalArgumentException("skip must be greater than 0");
        } else {
            this.a = i;
            this.b = i2;
        }
    }

    public k<? super T> a(k<? super List<T>> kVar) {
        l aVar;
        if (this.b == this.a) {
            aVar = new a(kVar, this.a);
            kVar.a(aVar);
            kVar.a(aVar.d());
            return aVar;
        } else if (this.b > this.a) {
            aVar = new c(kVar, this.a, this.b);
            kVar.a(aVar);
            kVar.a(aVar.d());
            return aVar;
        } else {
            aVar = new b(kVar, this.a, this.b);
            kVar.a(aVar);
            kVar.a(aVar.d());
            return aVar;
        }
    }
}

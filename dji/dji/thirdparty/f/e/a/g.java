package dji.thirdparty.f.e.a;

import dji.thirdparty.f.d$f;
import dji.thirdparty.f.e;
import dji.thirdparty.f.k;
import dji.thirdparty.f.l.f;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicReference;

public final class g<T> extends f<T, T> {
    static final e d = new e() {
        public void o_() {
        }

        public void a(Throwable th) {
        }

        public void a_(Object obj) {
        }
    };
    final b<T> c;
    private boolean e = false;

    static final class a<T> implements d$f<T> {
        final b<T> a;

        public a(b<T> bVar) {
            this.a = bVar;
        }

        public void a(k<? super T> kVar) {
            Object obj = 1;
            if (this.a.a(null, kVar)) {
                kVar.a(dji.thirdparty.f.m.f.a(new dji.thirdparty.f.d.b(this) {
                    final /* synthetic */ a a;

                    {
                        this.a = r1;
                    }

                    public void a() {
                        this.a.a.set(g.d);
                    }
                }));
                synchronized (this.a.a) {
                    if (this.a.b) {
                        obj = null;
                    } else {
                        this.a.b = true;
                    }
                }
                if (obj != null) {
                    r a = r.a();
                    while (true) {
                        Object poll = this.a.c.poll();
                        if (poll != null) {
                            a.a((e) this.a.get(), poll);
                        } else {
                            synchronized (this.a.a) {
                                if (this.a.c.isEmpty()) {
                                    this.a.b = false;
                                    return;
                                }
                            }
                        }
                    }
                }
                return;
            }
            kVar.a(new IllegalStateException("Only one subscriber allowed!"));
        }
    }

    static final class b<T> extends AtomicReference<e<? super T>> {
        final Object a = new Object();
        boolean b = false;
        final ConcurrentLinkedQueue<Object> c = new ConcurrentLinkedQueue();
        final r<T> d = r.a();

        b() {
        }

        boolean a(e<? super T> eVar, e<? super T> eVar2) {
            return compareAndSet(eVar, eVar2);
        }
    }

    public static <T> g<T> I() {
        return new g(new b());
    }

    private g(b<T> bVar) {
        super(new a(bVar));
        this.c = bVar;
    }

    private void i(Object obj) {
        synchronized (this.c.a) {
            this.c.c.add(obj);
            if (!(this.c.get() == null || this.c.b)) {
                this.e = true;
                this.c.b = true;
            }
        }
        if (this.e) {
            while (true) {
                Object poll = this.c.c.poll();
                if (poll != null) {
                    this.c.d.a((e) this.c.get(), poll);
                } else {
                    return;
                }
            }
        }
    }

    public void o_() {
        if (this.e) {
            ((e) this.c.get()).o_();
        } else {
            i(this.c.d.b());
        }
    }

    public void a(Throwable th) {
        if (this.e) {
            ((e) this.c.get()).a(th);
        } else {
            i(this.c.d.a(th));
        }
    }

    public void a_(T t) {
        if (this.e) {
            ((e) this.c.get()).a_(t);
        } else {
            i(this.c.d.a((Object) t));
        }
    }

    public boolean J() {
        boolean z;
        synchronized (this.c.a) {
            z = this.c.get() != null;
        }
        return z;
    }
}

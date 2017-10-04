package dji.thirdparty.f.e.a;

import dji.thirdparty.f.d;
import dji.thirdparty.f.d$f;
import dji.thirdparty.f.d.o;
import dji.thirdparty.f.d.p;
import dji.thirdparty.f.e;
import dji.thirdparty.f.k;
import dji.thirdparty.f.l;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ad<T1, T2, D1, D2, R> implements d$f<R> {
    protected final d<T1> a;
    protected final d<T2> b;
    protected final o<? super T1, ? extends d<D1>> c;
    protected final o<? super T2, ? extends d<D2>> d;
    protected final p<? super T1, ? super d<T2>, ? extends R> e;

    final class a implements l {
        final dji.thirdparty.f.m.d a;
        final k<? super R> b;
        final dji.thirdparty.f.m.b c;
        final Object d = new Object();
        int e;
        int f;
        final Map<Integer, e<T2>> g = new HashMap();
        final Map<Integer, T2> h = new HashMap();
        boolean i;
        boolean j;
        final /* synthetic */ ad k;

        final class a extends k<D1> {
            final int a;
            boolean b = true;
            final /* synthetic */ a c;

            public a(a aVar, int i) {
                this.c = aVar;
                this.a = i;
            }

            public void o_() {
                if (this.b) {
                    e eVar;
                    this.b = false;
                    synchronized (this.c.d) {
                        eVar = (e) this.c.g.remove(Integer.valueOf(this.a));
                    }
                    if (eVar != null) {
                        eVar.o_();
                    }
                    this.c.c.b(this);
                }
            }

            public void a(Throwable th) {
                this.c.b(th);
            }

            public void a_(D1 d1) {
                o_();
            }
        }

        final class b extends k<T1> {
            final /* synthetic */ a a;

            b(a aVar) {
                this.a = aVar;
            }

            public void a_(T1 t1) {
                try {
                    int i;
                    Object I = dji.thirdparty.f.l.c.I();
                    e cVar = new dji.thirdparty.f.g.c(I);
                    synchronized (this.a.d) {
                        a aVar = this.a;
                        i = aVar.e;
                        aVar.e = i + 1;
                        this.a.g.put(Integer.valueOf(i), cVar);
                    }
                    dji.thirdparty.f.d a = dji.thirdparty.f.d.a(new b(I, this.a.a));
                    dji.thirdparty.f.d dVar = (dji.thirdparty.f.d) this.a.k.c.a(t1);
                    l aVar2 = new a(this.a, i);
                    this.a.c.a(aVar2);
                    dVar.a(aVar2);
                    I = this.a.k.e.b(t1, a);
                    synchronized (this.a.d) {
                        List<Object> arrayList = new ArrayList(this.a.h.values());
                    }
                    this.a.b.a_(I);
                    for (Object a_ : arrayList) {
                        cVar.a_(a_);
                    }
                } catch (Throwable th) {
                    dji.thirdparty.f.c.b.a(th, (e) this);
                }
            }

            public void o_() {
                List list = null;
                synchronized (this.a.d) {
                    this.a.i = true;
                    if (this.a.j) {
                        list = new ArrayList(this.a.g.values());
                        this.a.g.clear();
                        this.a.h.clear();
                    }
                }
                this.a.a(list);
            }

            public void a(Throwable th) {
                this.a.a(th);
            }
        }

        final class c extends k<D2> {
            final int a;
            boolean b = true;
            final /* synthetic */ a c;

            public c(a aVar, int i) {
                this.c = aVar;
                this.a = i;
            }

            public void o_() {
                if (this.b) {
                    this.b = false;
                    synchronized (this.c.d) {
                        this.c.h.remove(Integer.valueOf(this.a));
                    }
                    this.c.c.b(this);
                }
            }

            public void a(Throwable th) {
                this.c.b(th);
            }

            public void a_(D2 d2) {
                o_();
            }
        }

        final class d extends k<T2> {
            final /* synthetic */ a a;

            d(a aVar) {
                this.a = aVar;
            }

            public void a_(T2 t2) {
                try {
                    int i;
                    synchronized (this.a.d) {
                        a aVar = this.a;
                        i = aVar.f;
                        aVar.f = i + 1;
                        this.a.h.put(Integer.valueOf(i), t2);
                    }
                    dji.thirdparty.f.d dVar = (dji.thirdparty.f.d) this.a.k.d.a(t2);
                    l cVar = new c(this.a, i);
                    this.a.c.a(cVar);
                    dVar.a(cVar);
                    synchronized (this.a.d) {
                        List<e> arrayList = new ArrayList(this.a.g.values());
                    }
                    for (e a_ : arrayList) {
                        a_.a_(t2);
                    }
                } catch (Throwable th) {
                    dji.thirdparty.f.c.b.a(th, (e) this);
                }
            }

            public void o_() {
                List list = null;
                synchronized (this.a.d) {
                    this.a.j = true;
                    if (this.a.i) {
                        list = new ArrayList(this.a.g.values());
                        this.a.g.clear();
                        this.a.h.clear();
                    }
                }
                this.a.a(list);
            }

            public void a(Throwable th) {
                this.a.a(th);
            }
        }

        public a(ad adVar, k<? super R> kVar) {
            this.k = adVar;
            this.b = kVar;
            this.c = new dji.thirdparty.f.m.b();
            this.a = new dji.thirdparty.f.m.d(this.c);
        }

        public void c() {
            l bVar = new b(this);
            l dVar = new d(this);
            this.c.a(bVar);
            this.c.a(dVar);
            this.k.a.a(bVar);
            this.k.b.a(dVar);
        }

        public void n_() {
            this.a.n_();
        }

        public boolean b() {
            return this.a.b();
        }

        void a(Throwable th) {
            synchronized (this.d) {
                List<e> arrayList = new ArrayList(this.g.values());
                this.g.clear();
                this.h.clear();
            }
            for (e a : arrayList) {
                a.a(th);
            }
            this.b.a(th);
            this.a.n_();
        }

        void b(Throwable th) {
            synchronized (this.d) {
                this.g.clear();
                this.h.clear();
            }
            this.b.a(th);
            this.a.n_();
        }

        void a(List<e<T2>> list) {
            if (list != null) {
                for (e o_ : list) {
                    o_.o_();
                }
                this.b.o_();
                this.a.n_();
            }
        }
    }

    static final class b<T> implements d$f<T> {
        final dji.thirdparty.f.m.d a;
        final d<T> b;

        final class a extends k<T> {
            final k<? super T> a;
            final /* synthetic */ b b;
            private final l c;

            public a(b bVar, k<? super T> kVar, l lVar) {
                this.b = bVar;
                super(kVar);
                this.a = kVar;
                this.c = lVar;
            }

            public void a_(T t) {
                this.a.a_(t);
            }

            public void a(Throwable th) {
                this.a.a(th);
                this.c.n_();
            }

            public void o_() {
                this.a.o_();
                this.c.n_();
            }
        }

        public b(d<T> dVar, dji.thirdparty.f.m.d dVar2) {
            this.a = dVar2;
            this.b = dVar;
        }

        public void a(k<? super T> kVar) {
            l c = this.a.c();
            k aVar = new a(this, kVar, c);
            aVar.a(c);
            this.b.a(aVar);
        }
    }

    public ad(d<T1> dVar, d<T2> dVar2, o<? super T1, ? extends d<D1>> oVar, o<? super T2, ? extends d<D2>> oVar2, p<? super T1, ? super d<T2>, ? extends R> pVar) {
        this.a = dVar;
        this.b = dVar2;
        this.c = oVar;
        this.d = oVar2;
        this.e = pVar;
    }

    public void a(k<? super R> kVar) {
        l aVar = new a(this, new dji.thirdparty.f.g.d(kVar));
        kVar.a(aVar);
        aVar.c();
    }
}

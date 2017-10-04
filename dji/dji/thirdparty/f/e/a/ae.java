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
import java.util.Map.Entry;

public final class ae<TLeft, TRight, TLeftDuration, TRightDuration, R> implements d$f<R> {
    final d<TLeft> a;
    final d<TRight> b;
    final o<TLeft, d<TLeftDuration>> c;
    final o<TRight, d<TRightDuration>> d;
    final p<TLeft, TRight, R> e;

    final class a {
        final dji.thirdparty.f.m.b a;
        final k<? super R> b;
        final Object c = new Object();
        boolean d;
        int e;
        final Map<Integer, TLeft> f;
        boolean g;
        int h;
        final Map<Integer, TRight> i;
        final /* synthetic */ ae j;

        final class a extends k<TLeft> {
            final /* synthetic */ a a;

            final class a extends k<TLeftDuration> {
                final int a;
                boolean b = true;
                final /* synthetic */ a c;

                public a(a aVar, int i) {
                    this.c = aVar;
                    this.a = i;
                }

                public void a_(TLeftDuration tLeftDuration) {
                    o_();
                }

                public void a(Throwable th) {
                    this.c.a(th);
                }

                public void o_() {
                    if (this.b) {
                        this.b = false;
                        this.c.a(this.a, this);
                    }
                }
            }

            a(a aVar) {
                this.a = aVar;
            }

            protected void a(int i, l lVar) {
                Object obj = null;
                synchronized (this.a.c) {
                    if (this.a.f.remove(Integer.valueOf(i)) != null && this.a.f.isEmpty() && this.a.d) {
                        obj = 1;
                    }
                }
                if (obj != null) {
                    this.a.b.o_();
                    this.a.b.n_();
                    return;
                }
                this.a.a.b(lVar);
            }

            public void a_(TLeft tLeft) {
                synchronized (this.a.c) {
                    a aVar = this.a;
                    int i = aVar.e;
                    aVar.e = i + 1;
                    this.a.f.put(Integer.valueOf(i), tLeft);
                    int i2 = this.a.h;
                }
                try {
                    d dVar = (d) this.a.j.c.a(tLeft);
                    l aVar2 = new a(this, i);
                    this.a.a.a(aVar2);
                    dVar.a(aVar2);
                    List<Object> arrayList = new ArrayList();
                    synchronized (this.a.c) {
                        for (Entry entry : this.a.i.entrySet()) {
                            if (((Integer) entry.getKey()).intValue() < i2) {
                                arrayList.add(entry.getValue());
                            }
                        }
                    }
                    for (Object b : arrayList) {
                        this.a.b.a_(this.a.j.e.b(tLeft, b));
                    }
                } catch (Throwable th) {
                    dji.thirdparty.f.c.b.a(th, (e) this);
                }
            }

            public void a(Throwable th) {
                this.a.b.a(th);
                this.a.b.n_();
            }

            public void o_() {
                Object obj = null;
                synchronized (this.a.c) {
                    this.a.d = true;
                    if (this.a.g || this.a.f.isEmpty()) {
                        obj = 1;
                    }
                }
                if (obj != null) {
                    this.a.b.o_();
                    this.a.b.n_();
                    return;
                }
                this.a.a.b(this);
            }
        }

        final class b extends k<TRight> {
            final /* synthetic */ a a;

            final class a extends k<TRightDuration> {
                final int a;
                boolean b = true;
                final /* synthetic */ b c;

                public a(b bVar, int i) {
                    this.c = bVar;
                    this.a = i;
                }

                public void a_(TRightDuration tRightDuration) {
                    o_();
                }

                public void a(Throwable th) {
                    this.c.a(th);
                }

                public void o_() {
                    if (this.b) {
                        this.b = false;
                        this.c.a(this.a, this);
                    }
                }
            }

            b(a aVar) {
                this.a = aVar;
            }

            void a(int i, l lVar) {
                Object obj = null;
                synchronized (this.a.c) {
                    if (this.a.i.remove(Integer.valueOf(i)) != null && this.a.i.isEmpty() && this.a.g) {
                        obj = 1;
                    }
                }
                if (obj != null) {
                    this.a.b.o_();
                    this.a.b.n_();
                    return;
                }
                this.a.a.b(lVar);
            }

            public void a_(TRight tRight) {
                synchronized (this.a.c) {
                    a aVar = this.a;
                    int i = aVar.h;
                    aVar.h = i + 1;
                    this.a.i.put(Integer.valueOf(i), tRight);
                    int i2 = this.a.e;
                }
                this.a.a.a(new dji.thirdparty.f.m.e());
                try {
                    d dVar = (d) this.a.j.d.a(tRight);
                    l aVar2 = new a(this, i);
                    this.a.a.a(aVar2);
                    dVar.a(aVar2);
                    List<Object> arrayList = new ArrayList();
                    synchronized (this.a.c) {
                        for (Entry entry : this.a.f.entrySet()) {
                            if (((Integer) entry.getKey()).intValue() < i2) {
                                arrayList.add(entry.getValue());
                            }
                        }
                    }
                    for (Object b : arrayList) {
                        this.a.b.a_(this.a.j.e.b(b, tRight));
                    }
                } catch (Throwable th) {
                    dji.thirdparty.f.c.b.a(th, (e) this);
                }
            }

            public void a(Throwable th) {
                this.a.b.a(th);
                this.a.b.n_();
            }

            public void o_() {
                Object obj = null;
                synchronized (this.a.c) {
                    this.a.g = true;
                    if (this.a.d || this.a.i.isEmpty()) {
                        obj = 1;
                    }
                }
                if (obj != null) {
                    this.a.b.o_();
                    this.a.b.n_();
                    return;
                }
                this.a.a.b(this);
            }
        }

        public a(ae aeVar, k<? super R> kVar) {
            this.j = aeVar;
            this.b = kVar;
            this.a = new dji.thirdparty.f.m.b();
            this.f = new HashMap();
            this.i = new HashMap();
        }

        public void a() {
            this.b.a(this.a);
            l aVar = new a(this);
            l bVar = new b(this);
            this.a.a(aVar);
            this.a.a(bVar);
            this.j.a.a(aVar);
            this.j.b.a(bVar);
        }
    }

    public ae(d<TLeft> dVar, d<TRight> dVar2, o<TLeft, d<TLeftDuration>> oVar, o<TRight, d<TRightDuration>> oVar2, p<TLeft, TRight, R> pVar) {
        this.a = dVar;
        this.b = dVar2;
        this.c = oVar;
        this.d = oVar2;
        this.e = pVar;
    }

    public void a(k<? super R> kVar) {
        new a(this, new dji.thirdparty.f.g.d(kVar)).a();
    }
}

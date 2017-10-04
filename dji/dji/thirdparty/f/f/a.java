package dji.thirdparty.f.f;

import dji.thirdparty.f.d;
import dji.thirdparty.f.d$f;
import dji.thirdparty.f.d.n;
import dji.thirdparty.f.d.o;
import dji.thirdparty.f.d.q;
import dji.thirdparty.f.e;
import dji.thirdparty.f.e.a.g;
import dji.thirdparty.f.f;
import dji.thirdparty.f.k;
import dji.thirdparty.f.l;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

@dji.thirdparty.f.b.b
public abstract class a<S, T> implements d$f<T> {

    private static final class a<S, T> extends a<S, T> {
        private final n<? extends S> a;
        private final q<? super S, Long, ? super e<d<? extends T>>, ? extends S> b;
        private final dji.thirdparty.f.d.c<? super S> c;

        public /* bridge */ /* synthetic */ void a(Object obj) {
            super.a((k) obj);
        }

        a(n<? extends S> nVar, q<? super S, Long, ? super e<d<? extends T>>, ? extends S> qVar, dji.thirdparty.f.d.c<? super S> cVar) {
            this.a = nVar;
            this.b = qVar;
            this.c = cVar;
        }

        public a(n<? extends S> nVar, q<? super S, Long, ? super e<d<? extends T>>, ? extends S> qVar) {
            this(nVar, qVar, null);
        }

        public a(q<S, Long, e<d<? extends T>>, S> qVar, dji.thirdparty.f.d.c<? super S> cVar) {
            this(null, qVar, cVar);
        }

        public a(q<S, Long, e<d<? extends T>>, S> qVar) {
            this(null, qVar, null);
        }

        protected S a() {
            return this.a == null ? null : this.a.call();
        }

        protected S a(S s, long j, e<d<? extends T>> eVar) {
            return this.b.a(s, Long.valueOf(j), eVar);
        }

        protected void b(S s) {
            if (this.c != null) {
                this.c.a(s);
            }
        }
    }

    static final class b<S, T> implements e<d<? extends T>>, f, l {
        private static final AtomicIntegerFieldUpdater<b> g = AtomicIntegerFieldUpdater.newUpdater(b.class, "f");
        final dji.thirdparty.f.m.b a = new dji.thirdparty.f.m.b();
        boolean b;
        List<Long> c;
        f d;
        long e;
        private volatile int f;
        private final a<S, T> h;
        private final dji.thirdparty.f.g.c<d<? extends T>> i;
        private boolean j;
        private boolean k;
        private S l;
        private final c<d<T>> m;

        public /* synthetic */ void a_(Object obj) {
            a((d) obj);
        }

        public b(a<S, T> aVar, S s, c<d<T>> cVar) {
            this.h = aVar;
            this.i = new dji.thirdparty.f.g.c(this);
            this.l = s;
            this.m = cVar;
        }

        public void n_() {
            if (g.compareAndSet(this, 0, 1)) {
                synchronized (this) {
                    if (this.b) {
                        this.c = new ArrayList();
                        this.c.add(Long.valueOf(0));
                        return;
                    }
                    this.b = true;
                    c();
                }
            }
        }

        void a(f fVar) {
            if (this.d != null) {
                throw new IllegalStateException("setConcatProducer may be called at most once!");
            }
            this.d = fVar;
        }

        public boolean b() {
            return this.f != 0;
        }

        public void b(long j) {
            this.l = this.h.a(this.l, j, this.i);
        }

        void c() {
            this.a.n_();
            try {
                this.h.b(this.l);
            } catch (Throwable th) {
                b(th);
            }
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void a(long r8) {
            /*
            r7 = this;
            r4 = 0;
            r0 = 1;
            r1 = 0;
            r2 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1));
            if (r2 != 0) goto L_0x0009;
        L_0x0008:
            return;
        L_0x0009:
            r2 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1));
            if (r2 >= 0) goto L_0x0026;
        L_0x000d:
            r0 = new java.lang.IllegalStateException;
            r1 = new java.lang.StringBuilder;
            r1.<init>();
            r2 = "Request can't be negative! ";
            r1 = r1.append(r2);
            r1 = r1.append(r8);
            r1 = r1.toString();
            r0.<init>(r1);
            throw r0;
        L_0x0026:
            monitor-enter(r7);
            r2 = r7.b;	 Catch:{ all -> 0x005d }
            if (r2 == 0) goto L_0x0058;
        L_0x002b:
            r1 = r7.c;	 Catch:{ all -> 0x005d }
            if (r1 != 0) goto L_0x0036;
        L_0x002f:
            r1 = new java.util.ArrayList;	 Catch:{ all -> 0x005d }
            r1.<init>();	 Catch:{ all -> 0x005d }
            r7.c = r1;	 Catch:{ all -> 0x005d }
        L_0x0036:
            r2 = java.lang.Long.valueOf(r8);	 Catch:{ all -> 0x005d }
            r1.add(r2);	 Catch:{ all -> 0x005d }
        L_0x003d:
            monitor-exit(r7);	 Catch:{ all -> 0x005d }
            r1 = r7.d;
            r1.a(r8);
            if (r0 != 0) goto L_0x0008;
        L_0x0045:
            r0 = r7.d(r8);
            if (r0 != 0) goto L_0x0008;
        L_0x004b:
            monitor-enter(r7);
            r0 = r7.c;	 Catch:{ all -> 0x0055 }
            if (r0 != 0) goto L_0x0060;
        L_0x0050:
            r0 = 0;
            r7.b = r0;	 Catch:{ all -> 0x0055 }
            monitor-exit(r7);	 Catch:{ all -> 0x0055 }
            goto L_0x0008;
        L_0x0055:
            r0 = move-exception;
            monitor-exit(r7);	 Catch:{ all -> 0x0055 }
            throw r0;
        L_0x0058:
            r0 = 1;
            r7.b = r0;	 Catch:{ all -> 0x005d }
            r0 = r1;
            goto L_0x003d;
        L_0x005d:
            r0 = move-exception;
            monitor-exit(r7);	 Catch:{ all -> 0x005d }
            throw r0;
        L_0x0060:
            r1 = 0;
            r7.c = r1;	 Catch:{ all -> 0x0055 }
            monitor-exit(r7);	 Catch:{ all -> 0x0055 }
            r1 = r0.iterator();
        L_0x0068:
            r0 = r1.hasNext();
            if (r0 == 0) goto L_0x004b;
        L_0x006e:
            r0 = r1.next();
            r0 = (java.lang.Long) r0;
            r2 = r0.longValue();
            r0 = r7.d(r2);
            if (r0 == 0) goto L_0x0068;
        L_0x007e:
            goto L_0x0008;
            */
            throw new UnsupportedOperationException("Method not decompiled: dji.thirdparty.f.f.a.b.a(long):void");
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void c(long r6) {
            /*
            r5 = this;
            r2 = 0;
            r0 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1));
            if (r0 != 0) goto L_0x0007;
        L_0x0006:
            return;
        L_0x0007:
            r0 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1));
            if (r0 >= 0) goto L_0x0024;
        L_0x000b:
            r0 = new java.lang.IllegalStateException;
            r1 = new java.lang.StringBuilder;
            r1.<init>();
            r2 = "Request can't be negative! ";
            r1 = r1.append(r2);
            r1 = r1.append(r6);
            r1 = r1.toString();
            r0.<init>(r1);
            throw r0;
        L_0x0024:
            monitor-enter(r5);
            r0 = r5.b;	 Catch:{ all -> 0x003d }
            if (r0 == 0) goto L_0x0040;
        L_0x0029:
            r0 = r5.c;	 Catch:{ all -> 0x003d }
            if (r0 != 0) goto L_0x0034;
        L_0x002d:
            r0 = new java.util.ArrayList;	 Catch:{ all -> 0x003d }
            r0.<init>();	 Catch:{ all -> 0x003d }
            r5.c = r0;	 Catch:{ all -> 0x003d }
        L_0x0034:
            r1 = java.lang.Long.valueOf(r6);	 Catch:{ all -> 0x003d }
            r0.add(r1);	 Catch:{ all -> 0x003d }
            monitor-exit(r5);	 Catch:{ all -> 0x003d }
            goto L_0x0006;
        L_0x003d:
            r0 = move-exception;
            monitor-exit(r5);	 Catch:{ all -> 0x003d }
            throw r0;
        L_0x0040:
            r0 = 1;
            r5.b = r0;	 Catch:{ all -> 0x003d }
            monitor-exit(r5);	 Catch:{ all -> 0x003d }
            r0 = r5.d(r6);
            if (r0 != 0) goto L_0x0006;
        L_0x004a:
            monitor-enter(r5);
            r0 = r5.c;	 Catch:{ all -> 0x0054 }
            if (r0 != 0) goto L_0x0057;
        L_0x004f:
            r0 = 0;
            r5.b = r0;	 Catch:{ all -> 0x0054 }
            monitor-exit(r5);	 Catch:{ all -> 0x0054 }
            goto L_0x0006;
        L_0x0054:
            r0 = move-exception;
            monitor-exit(r5);	 Catch:{ all -> 0x0054 }
            throw r0;
        L_0x0057:
            r1 = 0;
            r5.c = r1;	 Catch:{ all -> 0x0054 }
            monitor-exit(r5);	 Catch:{ all -> 0x0054 }
            r1 = r0.iterator();
        L_0x005f:
            r0 = r1.hasNext();
            if (r0 == 0) goto L_0x004a;
        L_0x0065:
            r0 = r1.next();
            r0 = (java.lang.Long) r0;
            r2 = r0.longValue();
            r0 = r5.d(r2);
            if (r0 == 0) goto L_0x005f;
        L_0x0075:
            goto L_0x0006;
            */
            throw new UnsupportedOperationException("Method not decompiled: dji.thirdparty.f.f.a.b.c(long):void");
        }

        boolean d(long j) {
            if (b()) {
                c();
                return true;
            }
            try {
                this.k = false;
                this.e = j;
                b(j);
                if (this.j || b()) {
                    c();
                    return true;
                } else if (this.k) {
                    return false;
                } else {
                    b(new IllegalStateException("No events emitted!"));
                    return true;
                }
            } catch (Throwable th) {
                b(th);
                return true;
            }
        }

        private void b(Throwable th) {
            if (this.j) {
                dji.thirdparty.f.i.d.getInstance().b().a(th);
                return;
            }
            this.j = true;
            this.m.a(th);
            c();
        }

        public void o_() {
            if (this.j) {
                throw new IllegalStateException("Terminal event already emitted.");
            }
            this.j = true;
            this.m.o_();
        }

        public void a(Throwable th) {
            if (this.j) {
                throw new IllegalStateException("Terminal event already emitted.");
            }
            this.j = true;
            this.m.a(th);
        }

        public void a(d<? extends T> dVar) {
            if (this.k) {
                throw new IllegalStateException("onNext called multiple times!");
            }
            this.k = true;
            if (!this.j) {
                b((d) dVar);
            }
        }

        private void b(d<? extends T> dVar) {
            final g I = g.I();
            final long j = this.e;
            final l anonymousClass1 = new k<T>(this) {
                long a = j;
                final /* synthetic */ b d;

                public void a_(T t) {
                    this.a--;
                    I.a_(t);
                }

                public void a(Throwable th) {
                    I.a(th);
                }

                public void o_() {
                    I.o_();
                    long j = this.a;
                    if (j > 0) {
                        this.d.c(j);
                    }
                }
            };
            this.a.a(anonymousClass1);
            dVar.c(new dji.thirdparty.f.d.b(this) {
                final /* synthetic */ b b;

                public void a() {
                    this.b.a.b(anonymousClass1);
                }
            }).b(anonymousClass1);
            this.m.a_(I);
        }
    }

    static final class c<T> extends d<T> implements e<T> {
        private a<T> c;

        static final class a<T> implements d$f<T> {
            k<? super T> a;

            a() {
            }

            public void a(k<? super T> kVar) {
                synchronized (this) {
                    if (this.a == null) {
                        this.a = kVar;
                        return;
                    }
                    kVar.a(new IllegalStateException("There can be only one subscriber"));
                }
            }
        }

        public static <T> c<T> I() {
            return new c(new a());
        }

        protected c(a<T> aVar) {
            super(aVar);
            this.c = aVar;
        }

        public void o_() {
            this.c.a.o_();
        }

        public void a(Throwable th) {
            this.c.a.a(th);
        }

        public void a_(T t) {
            this.c.a.a_(t);
        }
    }

    protected abstract S a();

    protected abstract S a(S s, long j, e<d<? extends T>> eVar);

    protected void b(S s) {
    }

    @dji.thirdparty.f.b.b
    public static <S, T> a<S, T> a(n<? extends S> nVar, final dji.thirdparty.f.d.e<? super S, Long, ? super e<d<? extends T>>> eVar) {
        return new a((n) nVar, new q<S, Long, e<d<? extends T>>, S>() {
            public S a(S s, Long l, e<d<? extends T>> eVar) {
                eVar.a(s, l, eVar);
                return s;
            }
        });
    }

    @dji.thirdparty.f.b.b
    public static <S, T> a<S, T> a(n<? extends S> nVar, final dji.thirdparty.f.d.e<? super S, Long, ? super e<d<? extends T>>> eVar, dji.thirdparty.f.d.c<? super S> cVar) {
        return new a(nVar, new q<S, Long, e<d<? extends T>>, S>() {
            public S a(S s, Long l, e<d<? extends T>> eVar) {
                eVar.a(s, l, eVar);
                return s;
            }
        }, cVar);
    }

    @dji.thirdparty.f.b.b
    public static <S, T> a<S, T> a(n<? extends S> nVar, q<? super S, Long, ? super e<d<? extends T>>, ? extends S> qVar, dji.thirdparty.f.d.c<? super S> cVar) {
        return new a(nVar, qVar, cVar);
    }

    @dji.thirdparty.f.b.b
    public static <S, T> a<S, T> a(n<? extends S> nVar, q<? super S, Long, ? super e<d<? extends T>>, ? extends S> qVar) {
        return new a((n) nVar, (q) qVar);
    }

    @dji.thirdparty.f.b.b
    public static <T> a<Void, T> a(final dji.thirdparty.f.d.d<Long, ? super e<d<? extends T>>> dVar) {
        return new a(new q<Void, Long, e<d<? extends T>>, Void>() {
            public Void a(Void voidR, Long l, e<d<? extends T>> eVar) {
                dVar.a(l, eVar);
                return voidR;
            }
        });
    }

    @dji.thirdparty.f.b.b
    public static <T> a<Void, T> a(final dji.thirdparty.f.d.d<Long, ? super e<d<? extends T>>> dVar, final dji.thirdparty.f.d.b bVar) {
        return new a(new q<Void, Long, e<d<? extends T>>, Void>() {
            public Void a(Void voidR, Long l, e<d<? extends T>> eVar) {
                dVar.a(l, eVar);
                return null;
            }
        }, new dji.thirdparty.f.d.c<Void>() {
            public void a(Void voidR) {
                bVar.a();
            }
        });
    }

    public final void a(final k<? super T> kVar) {
        try {
            Object a = a();
            c I = c.I();
            final f bVar = new b(this, a, I);
            l anonymousClass6 = new k<T>(this) {
                final /* synthetic */ a c;

                public void a_(T t) {
                    kVar.a_(t);
                }

                public void a(Throwable th) {
                    kVar.a(th);
                }

                public void o_() {
                    kVar.o_();
                }

                public void a(f fVar) {
                    bVar.a(fVar);
                }
            };
            I.s().c(new o<d<T>, d<T>>(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public d<T> a(d<T> dVar) {
                    return dVar.s();
                }
            }).a(anonymousClass6);
            kVar.a(anonymousClass6);
            kVar.a((l) bVar);
            kVar.a(bVar);
        } catch (Throwable th) {
            kVar.a(th);
        }
    }
}

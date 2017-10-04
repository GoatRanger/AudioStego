package dji.thirdparty.f.e.a;

import dji.thirdparty.f.d;
import dji.thirdparty.f.d$f;
import dji.thirdparty.f.k;
import dji.thirdparty.f.m.f;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicReference;

public final class s<T> implements d$f<T> {
    final Iterable<? extends d<? extends T>> a;

    private static final class a<T> extends k<T> {
        private final k<? super T> a;
        private final b<T> b;
        private boolean c;

        a(long j, k<? super T> kVar, b<T> bVar) {
            this.a = kVar;
            this.b = bVar;
            a(j);
        }

        private void b(long j) {
            a(j);
        }

        public void a_(T t) {
            if (d()) {
                this.a.a_(t);
            }
        }

        public void o_() {
            if (d()) {
                this.a.o_();
            }
        }

        public void a(Throwable th) {
            if (d()) {
                this.a.a(th);
            }
        }

        private boolean d() {
            if (this.c) {
                return true;
            }
            if (this.b.a.get() == this) {
                this.c = true;
                return true;
            } else if (this.b.a.compareAndSet(null, this)) {
                this.b.a(this);
                this.c = true;
                return true;
            } else {
                this.b.a();
                return false;
            }
        }
    }

    private static class b<T> {
        final AtomicReference<a<T>> a;
        final Collection<a<T>> b;

        private b() {
            this.a = new AtomicReference();
            this.b = new ConcurrentLinkedQueue();
        }

        public void a() {
            a aVar = (a) this.a.get();
            if (aVar != null) {
                a(aVar);
            }
        }

        public void a(a<T> aVar) {
            for (a<T> aVar2 : this.b) {
                if (aVar2 != aVar) {
                    aVar2.n_();
                }
            }
            this.b.clear();
        }
    }

    public static <T> d$f<T> a(d<? extends T> dVar, d<? extends T> dVar2) {
        Iterable arrayList = new ArrayList();
        arrayList.add(dVar);
        arrayList.add(dVar2);
        return a(arrayList);
    }

    public static <T> d$f<T> a(d<? extends T> dVar, d<? extends T> dVar2, d<? extends T> dVar3) {
        Iterable arrayList = new ArrayList();
        arrayList.add(dVar);
        arrayList.add(dVar2);
        arrayList.add(dVar3);
        return a(arrayList);
    }

    public static <T> d$f<T> a(d<? extends T> dVar, d<? extends T> dVar2, d<? extends T> dVar3, d<? extends T> dVar4) {
        Iterable arrayList = new ArrayList();
        arrayList.add(dVar);
        arrayList.add(dVar2);
        arrayList.add(dVar3);
        arrayList.add(dVar4);
        return a(arrayList);
    }

    public static <T> d$f<T> a(d<? extends T> dVar, d<? extends T> dVar2, d<? extends T> dVar3, d<? extends T> dVar4, d<? extends T> dVar5) {
        Iterable arrayList = new ArrayList();
        arrayList.add(dVar);
        arrayList.add(dVar2);
        arrayList.add(dVar3);
        arrayList.add(dVar4);
        arrayList.add(dVar5);
        return a(arrayList);
    }

    public static <T> d$f<T> a(d<? extends T> dVar, d<? extends T> dVar2, d<? extends T> dVar3, d<? extends T> dVar4, d<? extends T> dVar5, d<? extends T> dVar6) {
        Iterable arrayList = new ArrayList();
        arrayList.add(dVar);
        arrayList.add(dVar2);
        arrayList.add(dVar3);
        arrayList.add(dVar4);
        arrayList.add(dVar5);
        arrayList.add(dVar6);
        return a(arrayList);
    }

    public static <T> d$f<T> a(d<? extends T> dVar, d<? extends T> dVar2, d<? extends T> dVar3, d<? extends T> dVar4, d<? extends T> dVar5, d<? extends T> dVar6, d<? extends T> dVar7) {
        Iterable arrayList = new ArrayList();
        arrayList.add(dVar);
        arrayList.add(dVar2);
        arrayList.add(dVar3);
        arrayList.add(dVar4);
        arrayList.add(dVar5);
        arrayList.add(dVar6);
        arrayList.add(dVar7);
        return a(arrayList);
    }

    public static <T> d$f<T> a(d<? extends T> dVar, d<? extends T> dVar2, d<? extends T> dVar3, d<? extends T> dVar4, d<? extends T> dVar5, d<? extends T> dVar6, d<? extends T> dVar7, d<? extends T> dVar8) {
        Iterable arrayList = new ArrayList();
        arrayList.add(dVar);
        arrayList.add(dVar2);
        arrayList.add(dVar3);
        arrayList.add(dVar4);
        arrayList.add(dVar5);
        arrayList.add(dVar6);
        arrayList.add(dVar7);
        arrayList.add(dVar8);
        return a(arrayList);
    }

    public static <T> d$f<T> a(d<? extends T> dVar, d<? extends T> dVar2, d<? extends T> dVar3, d<? extends T> dVar4, d<? extends T> dVar5, d<? extends T> dVar6, d<? extends T> dVar7, d<? extends T> dVar8, d<? extends T> dVar9) {
        Iterable arrayList = new ArrayList();
        arrayList.add(dVar);
        arrayList.add(dVar2);
        arrayList.add(dVar3);
        arrayList.add(dVar4);
        arrayList.add(dVar5);
        arrayList.add(dVar6);
        arrayList.add(dVar7);
        arrayList.add(dVar8);
        arrayList.add(dVar9);
        return a(arrayList);
    }

    public static <T> d$f<T> a(Iterable<? extends d<? extends T>> iterable) {
        return new s(iterable);
    }

    private s(Iterable<? extends d<? extends T>> iterable) {
        this.a = iterable;
    }

    public void a(k<? super T> kVar) {
        final b bVar = new b();
        final AtomicReference atomicReference = bVar.a;
        kVar.a(f.a(new dji.thirdparty.f.d.b(this) {
            final /* synthetic */ s c;

            public void a() {
                a aVar = (a) atomicReference.get();
                if (aVar != null) {
                    aVar.n_();
                }
                s.a(bVar.b);
            }
        }));
        for (d dVar : this.a) {
            if (kVar.b()) {
                break;
            }
            k aVar = new a(0, kVar, bVar);
            bVar.b.add(aVar);
            a aVar2 = (a) atomicReference.get();
            if (aVar2 != null) {
                bVar.a(aVar2);
                return;
            }
            dVar.a(aVar);
        }
        if (kVar.b()) {
            a(bVar.b);
        }
        kVar.a(new dji.thirdparty.f.f(this) {
            final /* synthetic */ s c;

            public void a(long j) {
                a aVar = (a) atomicReference.get();
                if (aVar != null) {
                    aVar.b(j);
                    return;
                }
                for (a aVar2 : bVar.b) {
                    if (!aVar2.b()) {
                        if (atomicReference.get() == aVar2) {
                            aVar2.b(j);
                            return;
                        }
                        aVar2.b(j);
                    }
                }
            }
        });
    }

    static <T> void a(Collection<a<T>> collection) {
        if (!collection.isEmpty()) {
            for (a n_ : collection) {
                n_.n_();
            }
            collection.clear();
        }
    }
}

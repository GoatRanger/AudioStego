package dji.thirdparty.f.e.a;

import dji.thirdparty.f.d.x;
import dji.thirdparty.f.h;
import dji.thirdparty.f.h.a;
import dji.thirdparty.f.i;
import dji.thirdparty.f.i.d;
import dji.thirdparty.f.l;
import dji.thirdparty.f.m.b;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class dt {
    public static <T, R> h<R> a(final h<? extends T>[] hVarArr, final x<? extends R> xVar) {
        return h.a(new a<R>() {
            public void a(i<? super R> iVar) {
                final AtomicInteger atomicInteger = new AtomicInteger(hVarArr.length);
                final AtomicBoolean atomicBoolean = new AtomicBoolean();
                final Object[] objArr = new Object[hVarArr.length];
                l bVar = new b();
                iVar.a(bVar);
                int i = 0;
                while (i < hVarArr.length && !bVar.b() && !atomicBoolean.get()) {
                    final i<? super R> iVar2 = iVar;
                    i anonymousClass1 = new i<T>(this) {
                        final /* synthetic */ AnonymousClass1 f;

                        public void a(T t) {
                            objArr[i] = t;
                            if (atomicInteger.decrementAndGet() == 0) {
                                try {
                                    iVar2.a(xVar.a(objArr));
                                } catch (Throwable th) {
                                    dji.thirdparty.f.c.b.b(th);
                                    a(th);
                                }
                            }
                        }

                        public void a(Throwable th) {
                            if (atomicBoolean.compareAndSet(false, true)) {
                                iVar2.a(th);
                            } else {
                                d.getInstance().b().a(th);
                            }
                        }
                    };
                    bVar.a((l) anonymousClass1);
                    if (!bVar.b() && !atomicBoolean.get()) {
                        hVarArr[i].a(anonymousClass1);
                        i++;
                    } else {
                        return;
                    }
                }
            }
        });
    }
}

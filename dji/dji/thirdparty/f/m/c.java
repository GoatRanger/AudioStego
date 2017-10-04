package dji.thirdparty.f.m;

import dji.thirdparty.f.l;
import java.util.concurrent.atomic.AtomicReference;

public final class c implements l {
    final AtomicReference<a> a = new AtomicReference(new a(false, f.a()));

    private static final class a {
        final boolean a;
        final l b;

        a(boolean z, l lVar) {
            this.a = z;
            this.b = lVar;
        }

        a a() {
            return new a(true, this.b);
        }

        a a(l lVar) {
            return new a(this.a, lVar);
        }
    }

    public boolean b() {
        return ((a) this.a.get()).a;
    }

    public void n_() {
        a aVar;
        AtomicReference atomicReference = this.a;
        do {
            aVar = (a) atomicReference.get();
            if (aVar.a) {
                return;
            }
        } while (!atomicReference.compareAndSet(aVar, aVar.a()));
        aVar.b.n_();
    }

    public void a(l lVar) {
        if (lVar == null) {
            throw new IllegalArgumentException("Subscription can not be null");
        }
        AtomicReference atomicReference = this.a;
        a aVar;
        do {
            aVar = (a) atomicReference.get();
            if (aVar.a) {
                lVar.n_();
                return;
            }
        } while (!atomicReference.compareAndSet(aVar, aVar.a(lVar)));
    }

    public l c() {
        return ((a) this.a.get()).b;
    }
}

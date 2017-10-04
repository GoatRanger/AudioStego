package dji.thirdparty.f.e.a;

import dji.thirdparty.f.d$g;
import dji.thirdparty.f.k;
import java.util.ArrayDeque;
import java.util.Deque;

public class ci<T> implements d$g<T, T> {
    final int a;

    public ci(int i) {
        if (i < 0) {
            throw new IndexOutOfBoundsException("count could not be negative");
        }
        this.a = i;
    }

    public k<? super T> a(final k<? super T> kVar) {
        return new k<T>(this, kVar) {
            final /* synthetic */ ci b;
            private final r<T> c = r.a();
            private final Deque<Object> d = new ArrayDeque();

            public void o_() {
                kVar.o_();
            }

            public void a(Throwable th) {
                kVar.a(th);
            }

            public void a_(T t) {
                if (this.b.a == 0) {
                    kVar.a_(t);
                    return;
                }
                if (this.d.size() == this.b.a) {
                    kVar.a_(this.c.g(this.d.removeFirst()));
                } else {
                    a(1);
                }
                this.d.offerLast(this.c.a((Object) t));
            }
        };
    }
}

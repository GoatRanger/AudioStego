package dji.thirdparty.f.e.a;

import dji.thirdparty.f.d$g;
import dji.thirdparty.f.g;
import dji.thirdparty.f.j.i;
import dji.thirdparty.f.k;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.TimeUnit;

public class cj<T> implements d$g<T, T> {
    final long a;
    final g b;

    public cj(long j, TimeUnit timeUnit, g gVar) {
        this.a = timeUnit.toMillis(j);
        this.b = gVar;
    }

    public k<? super T> a(final k<? super T> kVar) {
        return new k<T>(this, kVar) {
            final /* synthetic */ cj b;
            private Deque<i<T>> c = new ArrayDeque();

            private void b(long j) {
                long j2 = j - this.b.a;
                while (!this.c.isEmpty()) {
                    i iVar = (i) this.c.getFirst();
                    if (iVar.a() < j2) {
                        this.c.removeFirst();
                        kVar.a_(iVar.b());
                    } else {
                        return;
                    }
                }
            }

            public void a_(T t) {
                long b = this.b.b.b();
                b(b);
                this.c.offerLast(new i(b, t));
            }

            public void a(Throwable th) {
                kVar.a(th);
            }

            public void o_() {
                b(this.b.b.b());
                kVar.o_();
            }
        };
    }
}

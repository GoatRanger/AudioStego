package dji.thirdparty.f.e.a;

import com.here.odnp.posclient.pos.IPositioningSession;
import dji.thirdparty.f.d$g;
import dji.thirdparty.f.f;
import dji.thirdparty.f.g;
import dji.thirdparty.f.k;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.TimeUnit;

public final class ct<T> implements d$g<T, T> {
    final long a;
    final g b;
    final int c;

    public ct(long j, TimeUnit timeUnit, g gVar) {
        this.a = timeUnit.toMillis(j);
        this.b = gVar;
        this.c = -1;
    }

    public ct(int i, long j, TimeUnit timeUnit, g gVar) {
        if (i < 0) {
            throw new IndexOutOfBoundsException("count could not be negative");
        }
        this.a = timeUnit.toMillis(j);
        this.b = gVar;
        this.c = i;
    }

    public k<? super T> a(k<? super T> kVar) {
        final Deque arrayDeque = new ArrayDeque();
        final Deque arrayDeque2 = new ArrayDeque();
        final r a = r.a();
        final f duVar = new du(a, arrayDeque, kVar);
        kVar.a(duVar);
        final k<? super T> kVar2 = kVar;
        return new k<T>(this, kVar) {
            final /* synthetic */ ct f;

            protected void b(long j) {
                while (this.f.c >= 0 && arrayDeque.size() > this.f.c) {
                    arrayDeque2.pollFirst();
                    arrayDeque.pollFirst();
                }
                while (!arrayDeque.isEmpty() && ((Long) arrayDeque2.peekFirst()).longValue() < j - this.f.a) {
                    arrayDeque2.pollFirst();
                    arrayDeque.pollFirst();
                }
            }

            public void c() {
                a((long) IPositioningSession.NotSet);
            }

            public void a_(T t) {
                long b = this.f.b.b();
                arrayDeque2.add(Long.valueOf(b));
                arrayDeque.add(a.a((Object) t));
                b(b);
            }

            public void a(Throwable th) {
                arrayDeque2.clear();
                arrayDeque.clear();
                kVar2.a(th);
            }

            public void o_() {
                b(this.f.b.b());
                arrayDeque2.clear();
                arrayDeque.offer(a.b());
                duVar.a();
            }
        };
    }
}

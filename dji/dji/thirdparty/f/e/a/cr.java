package dji.thirdparty.f.e.a;

import com.here.odnp.posclient.pos.IPositioningSession;
import dji.thirdparty.f.d$g;
import dji.thirdparty.f.f;
import dji.thirdparty.f.k;
import java.util.ArrayDeque;
import java.util.Deque;

public final class cr<T> implements d$g<T, T> {
    final int a;

    public cr(int i) {
        if (i < 0) {
            throw new IndexOutOfBoundsException("count cannot be negative");
        }
        this.a = i;
    }

    public k<? super T> a(k<? super T> kVar) {
        final Deque arrayDeque = new ArrayDeque();
        final r a = r.a();
        final f duVar = new du(a, arrayDeque, kVar);
        kVar.a(duVar);
        final k<? super T> kVar2 = kVar;
        return new k<T>(this, kVar) {
            final /* synthetic */ cr e;

            public void c() {
                a((long) IPositioningSession.NotSet);
            }

            public void o_() {
                arrayDeque.offer(a.b());
                duVar.a();
            }

            public void a(Throwable th) {
                arrayDeque.clear();
                kVar2.a(th);
            }

            public void a_(T t) {
                if (this.e.a != 0) {
                    if (arrayDeque.size() == this.e.a) {
                        arrayDeque.removeFirst();
                    }
                    arrayDeque.offerLast(a.a((Object) t));
                }
            }
        };
    }
}

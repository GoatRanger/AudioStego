package dji.thirdparty.f.e.a;

import dji.thirdparty.f.d$g;
import dji.thirdparty.f.d.c;
import dji.thirdparty.f.f;
import dji.thirdparty.f.k;
import dji.thirdparty.f.l;

public class bf<T> implements d$g<T, T> {
    final c<Long> a;

    private static final class a<T> extends k<T> {
        private final k<? super T> a;

        a(k<? super T> kVar) {
            this.a = kVar;
            a(0);
        }

        private void b(long j) {
            a(j);
        }

        public void o_() {
            this.a.o_();
        }

        public void a(Throwable th) {
            this.a.a(th);
        }

        public void a_(T t) {
            this.a.a_(t);
        }
    }

    public bf(c<Long> cVar) {
        this.a = cVar;
    }

    public k<? super T> a(k<? super T> kVar) {
        final l aVar = new a(kVar);
        kVar.a(new f(this) {
            final /* synthetic */ bf b;

            public void a(long j) {
                this.b.a.a(Long.valueOf(j));
                aVar.b(j);
            }
        });
        kVar.a(aVar);
        return aVar;
    }
}

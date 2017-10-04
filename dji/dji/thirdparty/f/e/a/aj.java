package dji.thirdparty.f.e.a;

import dji.thirdparty.f.d;
import dji.thirdparty.f.h.a;
import dji.thirdparty.f.i;
import dji.thirdparty.f.k;
import dji.thirdparty.f.l;
import java.util.NoSuchElementException;

public class aj<T> implements a<T> {
    private final d<T> a;

    public aj(d<T> dVar) {
        this.a = dVar;
    }

    public void a(final i<? super T> iVar) {
        l anonymousClass1 = new k<T>(this) {
            final /* synthetic */ aj b;
            private boolean c = false;
            private boolean d = false;
            private T e = null;

            public void c() {
                a(2);
            }

            public void o_() {
                if (!this.c) {
                    if (this.d) {
                        iVar.a(this.e);
                    } else {
                        iVar.a(new NoSuchElementException("Observable emitted no items"));
                    }
                }
            }

            public void a(Throwable th) {
                iVar.a(th);
                n_();
            }

            public void a_(T t) {
                if (this.d) {
                    this.c = true;
                    iVar.a(new IllegalArgumentException("Observable emitted too many elements"));
                    n_();
                    return;
                }
                this.d = true;
                this.e = t;
            }
        };
        iVar.a(anonymousClass1);
        this.a.a(anonymousClass1);
    }

    public static <T> aj<T> a(d<T> dVar) {
        return new aj(dVar);
    }
}

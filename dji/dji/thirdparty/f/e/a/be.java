package dji.thirdparty.f.e.a;

import dji.thirdparty.f.c.a;
import dji.thirdparty.f.c.b;
import dji.thirdparty.f.d$g;
import dji.thirdparty.f.e;
import dji.thirdparty.f.k;
import java.util.Arrays;

public class be<T> implements d$g<T, T> {
    final e<? super T> a;

    public be(e<? super T> eVar) {
        this.a = eVar;
    }

    public k<? super T> a(final k<? super T> kVar) {
        return new k<T>(this, kVar) {
            final /* synthetic */ be b;
            private boolean c = false;

            public void o_() {
                if (!this.c) {
                    try {
                        this.b.a.o_();
                        this.c = true;
                        kVar.o_();
                    } catch (Throwable th) {
                        b.a(th, (e) this);
                    }
                }
            }

            public void a(Throwable th) {
                b.b(th);
                if (!this.c) {
                    this.c = true;
                    try {
                        this.b.a.a(th);
                        kVar.a(th);
                    } catch (Throwable th2) {
                        b.b(th2);
                        kVar.a(new a(Arrays.asList(new Throwable[]{th, th2})));
                    }
                }
            }

            public void a_(T t) {
                if (!this.c) {
                    try {
                        this.b.a.a_(t);
                        kVar.a_(t);
                    } catch (Throwable th) {
                        b.a(th, this, t);
                    }
                }
            }
        };
    }
}

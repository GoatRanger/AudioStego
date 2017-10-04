package dji.thirdparty.f.e.a;

import dji.thirdparty.f.c;
import dji.thirdparty.f.d$g;
import dji.thirdparty.f.k;

public final class ba<T> implements d$g<T, c<T>> {

    private static final class a {
        static final ba<Object> a = new ba();

        private a() {
        }
    }

    public static ba a() {
        return a.a;
    }

    ba() {
    }

    public k<? super c<T>> a(final k<? super T> kVar) {
        return new k<c<T>>(this, kVar) {
            boolean a;
            final /* synthetic */ ba c;

            public /* synthetic */ void a_(Object obj) {
                a((c) obj);
            }

            public void a(c<T> cVar) {
                switch (cVar.f()) {
                    case OnNext:
                        if (!this.a) {
                            kVar.a_(cVar.c());
                            return;
                        }
                        return;
                    case OnError:
                        a(cVar.b());
                        return;
                    case OnCompleted:
                        o_();
                        return;
                    default:
                        return;
                }
            }

            public void a(Throwable th) {
                if (!this.a) {
                    this.a = true;
                    kVar.a(th);
                }
            }

            public void o_() {
                if (!this.a) {
                    this.a = true;
                    kVar.o_();
                }
            }
        };
    }
}

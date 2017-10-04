package dji.thirdparty.f.e.a;

import dji.thirdparty.f.d$g;
import dji.thirdparty.f.f;
import dji.thirdparty.f.k;
import dji.thirdparty.f.l;
import java.util.concurrent.atomic.AtomicLong;

public final class cq<T> implements d$g<T, T> {
    final int a;

    public cq(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("limit >= 0 required but it was " + i);
        }
        this.a = i;
    }

    public k<? super T> a(final k<? super T> kVar) {
        l anonymousClass1 = new k<T>(this) {
            int a;
            boolean b;
            final /* synthetic */ cq d;

            public void o_() {
                if (!this.b) {
                    this.b = true;
                    kVar.o_();
                }
            }

            public void a(Throwable th) {
                if (!this.b) {
                    this.b = true;
                    try {
                        kVar.a(th);
                    } finally {
                        n_();
                    }
                }
            }

            public void a_(T t) {
                if (!b()) {
                    int i = this.a;
                    this.a = i + 1;
                    if (i < this.d.a) {
                        boolean z = this.a == this.d.a;
                        kVar.a_(t);
                        if (z && !this.b) {
                            this.b = true;
                            try {
                                kVar.o_();
                            } finally {
                                n_();
                            }
                        }
                    }
                }
            }

            public void a(final f fVar) {
                kVar.a(new f(this) {
                    final AtomicLong a = new AtomicLong(0);
                    final /* synthetic */ AnonymousClass1 c;

                    public void a(long j) {
                        if (j > 0 && !this.c.b) {
                            long min;
                            long j2;
                            do {
                                j2 = this.a.get();
                                min = Math.min(j, ((long) this.c.d.a) - j2);
                                if (min == 0) {
                                    return;
                                }
                            } while (!this.a.compareAndSet(j2, j2 + min));
                            fVar.a(min);
                        }
                    }
                });
            }
        };
        if (this.a == 0) {
            kVar.o_();
            anonymousClass1.n_();
        }
        kVar.a(anonymousClass1);
        return anonymousClass1;
    }
}

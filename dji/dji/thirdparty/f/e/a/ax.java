package dji.thirdparty.f.e.a;

import com.here.odnp.posclient.pos.IPositioningSession;
import dji.thirdparty.f.d$g;
import dji.thirdparty.f.d.b;
import dji.thirdparty.f.g;
import dji.thirdparty.f.g.d;
import dji.thirdparty.f.k;
import dji.thirdparty.f.l;
import dji.thirdparty.f.m.e;
import java.util.concurrent.TimeUnit;

public final class ax<T> implements d$g<T, T> {
    final long a;
    final TimeUnit b;
    final g c;

    static final class a<T> {
        int a;
        T b;
        boolean c;
        boolean d;
        boolean e;

        a() {
        }

        public synchronized int a(T t) {
            int i;
            this.b = t;
            this.c = true;
            i = this.a + 1;
            this.a = i;
            return i;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void a(int r3, dji.thirdparty.f.k<T> r4, dji.thirdparty.f.k<?> r5) {
            /*
            r2 = this;
            monitor-enter(r2);
            r0 = r2.e;	 Catch:{ all -> 0x002b }
            if (r0 != 0) goto L_0x000d;
        L_0x0005:
            r0 = r2.c;	 Catch:{ all -> 0x002b }
            if (r0 == 0) goto L_0x000d;
        L_0x0009:
            r0 = r2.a;	 Catch:{ all -> 0x002b }
            if (r3 == r0) goto L_0x000f;
        L_0x000d:
            monitor-exit(r2);	 Catch:{ all -> 0x002b }
        L_0x000e:
            return;
        L_0x000f:
            r0 = r2.b;	 Catch:{ all -> 0x002b }
            r1 = 0;
            r2.b = r1;	 Catch:{ all -> 0x002b }
            r1 = 0;
            r2.c = r1;	 Catch:{ all -> 0x002b }
            r1 = 1;
            r2.e = r1;	 Catch:{ all -> 0x002b }
            monitor-exit(r2);	 Catch:{ all -> 0x002b }
            r4.a_(r0);	 Catch:{ Throwable -> 0x002e }
            monitor-enter(r2);
            r0 = r2.d;	 Catch:{ all -> 0x0028 }
            if (r0 != 0) goto L_0x0033;
        L_0x0023:
            r0 = 0;
            r2.e = r0;	 Catch:{ all -> 0x0028 }
            monitor-exit(r2);	 Catch:{ all -> 0x0028 }
            goto L_0x000e;
        L_0x0028:
            r0 = move-exception;
            monitor-exit(r2);	 Catch:{ all -> 0x0028 }
            throw r0;
        L_0x002b:
            r0 = move-exception;
            monitor-exit(r2);	 Catch:{ all -> 0x002b }
            throw r0;
        L_0x002e:
            r1 = move-exception;
            dji.thirdparty.f.c.b.a(r1, r5, r0);
            goto L_0x000e;
        L_0x0033:
            monitor-exit(r2);	 Catch:{ all -> 0x0028 }
            r4.o_();
            goto L_0x000e;
            */
            throw new UnsupportedOperationException("Method not decompiled: dji.thirdparty.f.e.a.ax.a.a(int, dji.thirdparty.f.k, dji.thirdparty.f.k):void");
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void a(dji.thirdparty.f.k<T> r4, dji.thirdparty.f.k<?> r5) {
            /*
            r3 = this;
            monitor-enter(r3);
            r0 = r3.e;	 Catch:{ all -> 0x0021 }
            if (r0 == 0) goto L_0x000a;
        L_0x0005:
            r0 = 1;
            r3.d = r0;	 Catch:{ all -> 0x0021 }
            monitor-exit(r3);	 Catch:{ all -> 0x0021 }
        L_0x0009:
            return;
        L_0x000a:
            r0 = r3.b;	 Catch:{ all -> 0x0021 }
            r1 = r3.c;	 Catch:{ all -> 0x0021 }
            r2 = 0;
            r3.b = r2;	 Catch:{ all -> 0x0021 }
            r2 = 0;
            r3.c = r2;	 Catch:{ all -> 0x0021 }
            r2 = 1;
            r3.e = r2;	 Catch:{ all -> 0x0021 }
            monitor-exit(r3);	 Catch:{ all -> 0x0021 }
            if (r1 == 0) goto L_0x001d;
        L_0x001a:
            r4.a_(r0);	 Catch:{ Throwable -> 0x0024 }
        L_0x001d:
            r4.o_();
            goto L_0x0009;
        L_0x0021:
            r0 = move-exception;
            monitor-exit(r3);	 Catch:{ all -> 0x0021 }
            throw r0;
        L_0x0024:
            r1 = move-exception;
            dji.thirdparty.f.c.b.a(r1, r5, r0);
            goto L_0x0009;
            */
            throw new UnsupportedOperationException("Method not decompiled: dji.thirdparty.f.e.a.ax.a.a(dji.thirdparty.f.k, dji.thirdparty.f.k):void");
        }

        public synchronized void a() {
            this.a++;
            this.b = null;
            this.c = false;
        }
    }

    public ax(long j, TimeUnit timeUnit, g gVar) {
        this.a = j;
        this.b = timeUnit;
        this.c = gVar;
    }

    public k<? super T> a(k<? super T> kVar) {
        final Object a = this.c.a();
        final d dVar = new d(kVar);
        final Object eVar = new e();
        dVar.a((l) a);
        dVar.a((l) eVar);
        return new k<T>(this, kVar) {
            final a<T> a = new a();
            final k<?> b = this;
            final /* synthetic */ ax f;

            public void c() {
                a((long) IPositioningSession.NotSet);
            }

            public void a_(T t) {
                final int a = this.a.a(t);
                eVar.a(a.a(new b(this) {
                    final /* synthetic */ AnonymousClass1 b;

                    public void a() {
                        this.b.a.a(a, dVar, this.b.b);
                    }
                }, this.f.a, this.f.b));
            }

            public void a(Throwable th) {
                dVar.a(th);
                n_();
                this.a.a();
            }

            public void o_() {
                this.a.a(dVar, this);
            }
        };
    }
}

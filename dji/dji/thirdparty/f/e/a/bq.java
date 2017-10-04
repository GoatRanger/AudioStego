package dji.thirdparty.f.e.a;

import com.here.odnp.posclient.pos.IPositioningSession;
import dji.thirdparty.f.c;
import dji.thirdparty.f.d$g;
import dji.thirdparty.f.f;
import dji.thirdparty.f.i.d;
import dji.thirdparty.f.k;
import dji.thirdparty.f.l;
import java.util.concurrent.atomic.AtomicLong;

public final class bq<T> implements d$g<c<T>, T> {

    private static final class a {
        static final bq<Object> a = new bq();

        private a() {
        }
    }

    private static class b<T> extends k<T> {
        private final k<? super c<T>> a;
        private volatile c<T> b;
        private boolean c = false;
        private boolean d = false;
        private final AtomicLong e = new AtomicLong();

        b(k<? super c<T>> kVar) {
            this.a = kVar;
        }

        public void c() {
            a(0);
        }

        void b(long j) {
            a.a(this.e, j);
            a(j);
            e();
        }

        public void o_() {
            this.b = c.a();
            e();
        }

        public void a(Throwable th) {
            this.b = c.a(th);
            d.getInstance().b().a(th);
            e();
        }

        public void a_(T t) {
            this.a.a_(c.a((Object) t));
            d();
        }

        private void d() {
            AtomicLong atomicLong = this.e;
            long j;
            do {
                j = atomicLong.get();
                if (j == IPositioningSession.NotSet) {
                    return;
                }
            } while (!atomicLong.compareAndSet(j, j - 1));
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void e() {
            /*
            r6 = this;
            monitor-enter(r6);
            r0 = r6.c;	 Catch:{ all -> 0x0039 }
            if (r0 == 0) goto L_0x000a;
        L_0x0005:
            r0 = 1;
            r6.d = r0;	 Catch:{ all -> 0x0039 }
            monitor-exit(r6);	 Catch:{ all -> 0x0039 }
        L_0x0009:
            return;
        L_0x000a:
            monitor-exit(r6);	 Catch:{ all -> 0x0039 }
            r0 = r6.e;
        L_0x000d:
            r1 = r6.a;
            r1 = r1.b();
            if (r1 != 0) goto L_0x0009;
        L_0x0015:
            r1 = r6.b;
            if (r1 == 0) goto L_0x003c;
        L_0x0019:
            r2 = r0.get();
            r4 = 0;
            r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
            if (r2 <= 0) goto L_0x003c;
        L_0x0023:
            r0 = 0;
            r6.b = r0;
            r0 = r6.a;
            r0.a_(r1);
            r0 = r6.a;
            r0 = r0.b();
            if (r0 != 0) goto L_0x0009;
        L_0x0033:
            r0 = r6.a;
            r0.o_();
            goto L_0x0009;
        L_0x0039:
            r0 = move-exception;
            monitor-exit(r6);	 Catch:{ all -> 0x0039 }
            throw r0;
        L_0x003c:
            monitor-enter(r6);
            r1 = r6.d;	 Catch:{ all -> 0x0046 }
            if (r1 != 0) goto L_0x0049;
        L_0x0041:
            r0 = 0;
            r6.c = r0;	 Catch:{ all -> 0x0046 }
            monitor-exit(r6);	 Catch:{ all -> 0x0046 }
            goto L_0x0009;
        L_0x0046:
            r0 = move-exception;
            monitor-exit(r6);	 Catch:{ all -> 0x0046 }
            throw r0;
        L_0x0049:
            monitor-exit(r6);	 Catch:{ all -> 0x0046 }
            goto L_0x000d;
            */
            throw new UnsupportedOperationException("Method not decompiled: dji.thirdparty.f.e.a.bq.b.e():void");
        }
    }

    public static <T> bq<T> a() {
        return a.a;
    }

    bq() {
    }

    public k<? super T> a(k<? super c<T>> kVar) {
        final l bVar = new b(kVar);
        kVar.a(bVar);
        kVar.a(new f(this) {
            final /* synthetic */ bq b;

            public void a(long j) {
                if (j > 0) {
                    bVar.b(j);
                }
            }
        });
        return bVar;
    }
}

package dji.thirdparty.f.e.a;

import com.here.odnp.posclient.pos.IPositioningSession;
import dji.thirdparty.f.d$g;
import dji.thirdparty.f.e;
import dji.thirdparty.f.f;
import dji.thirdparty.f.k;
import dji.thirdparty.f.l;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public final class bw<T> implements d$g<T, T> {

    static final class a {
        static final bw<Object> a = new bw();

        a() {
        }
    }

    static final class b<T> extends AtomicLong implements e<T>, f, l {
        static final Object h = new Object();
        static final long i = -4611686018427387904L;
        private static final long j = -1364393685005146274L;
        final k<? super T> a;
        c<? super T> b;
        final AtomicReference<Object> c = new AtomicReference(h);
        Throwable d;
        volatile boolean e;
        boolean f;
        boolean g;

        public b(k<? super T> kVar) {
            this.a = kVar;
            lazySet(i);
        }

        public void a(long j) {
            if (j >= 0) {
                long j2;
                long j3;
                do {
                    j2 = get();
                    if (j2 != Long.MIN_VALUE) {
                        if (j2 == i) {
                            j3 = j;
                        } else {
                            j3 = j2 + j;
                            if (j3 < 0) {
                                j3 = IPositioningSession.NotSet;
                            }
                        }
                    } else {
                        return;
                    }
                } while (!compareAndSet(j2, j3));
                if (j2 == i) {
                    this.b.b(IPositioningSession.NotSet);
                }
                c();
            }
        }

        long b(long j) {
            long j2;
            long j3;
            do {
                j3 = get();
                if (j3 < 0) {
                    return j3;
                }
                j2 = j3 - j;
            } while (!compareAndSet(j3, j2));
            return j2;
        }

        public boolean b() {
            return get() == Long.MIN_VALUE;
        }

        public void n_() {
            if (get() >= 0) {
                getAndSet(Long.MIN_VALUE);
            }
        }

        public void a_(T t) {
            this.c.lazySet(t);
            c();
        }

        public void a(Throwable th) {
            this.d = th;
            this.e = true;
            c();
        }

        public void o_() {
            this.e = true;
            c();
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        void c() {
            /*
            r8 = this;
            r1 = 1;
            r2 = 0;
            monitor-enter(r8);
            r0 = r8.f;	 Catch:{ all -> 0x0028 }
            if (r0 == 0) goto L_0x000c;
        L_0x0007:
            r0 = 1;
            r8.g = r0;	 Catch:{ all -> 0x0028 }
            monitor-exit(r8);	 Catch:{ all -> 0x0028 }
        L_0x000b:
            return;
        L_0x000c:
            r0 = 1;
            r8.f = r0;	 Catch:{ all -> 0x0028 }
            r0 = 0;
            r8.g = r0;	 Catch:{ all -> 0x0028 }
            monitor-exit(r8);	 Catch:{ all -> 0x0028 }
        L_0x0013:
            r4 = r8.get();	 Catch:{ all -> 0x007c }
            r6 = -9223372036854775808;
            r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
            if (r0 != 0) goto L_0x002b;
        L_0x001d:
            if (r1 != 0) goto L_0x000b;
        L_0x001f:
            monitor-enter(r8);
            r0 = 0;
            r8.f = r0;	 Catch:{ all -> 0x0025 }
            monitor-exit(r8);	 Catch:{ all -> 0x0025 }
            goto L_0x000b;
        L_0x0025:
            r0 = move-exception;
            monitor-exit(r8);	 Catch:{ all -> 0x0025 }
            throw r0;
        L_0x0028:
            r0 = move-exception;
            monitor-exit(r8);	 Catch:{ all -> 0x0028 }
            throw r0;
        L_0x002b:
            r0 = r8.c;	 Catch:{ all -> 0x007c }
            r0 = r0.get();	 Catch:{ all -> 0x007c }
            r6 = 0;
            r3 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
            if (r3 <= 0) goto L_0x004e;
        L_0x0037:
            r3 = h;	 Catch:{ all -> 0x007c }
            if (r0 == r3) goto L_0x004e;
        L_0x003b:
            r3 = r8.a;	 Catch:{ all -> 0x007c }
            r3.a_(r0);	 Catch:{ all -> 0x007c }
            r3 = r8.c;	 Catch:{ all -> 0x007c }
            r4 = h;	 Catch:{ all -> 0x007c }
            r3.compareAndSet(r0, r4);	 Catch:{ all -> 0x007c }
            r4 = 1;
            r8.b(r4);	 Catch:{ all -> 0x007c }
            r0 = h;	 Catch:{ all -> 0x007c }
        L_0x004e:
            r3 = h;	 Catch:{ all -> 0x007c }
            if (r0 != r3) goto L_0x005f;
        L_0x0052:
            r0 = r8.e;	 Catch:{ all -> 0x007c }
            if (r0 == 0) goto L_0x005f;
        L_0x0056:
            r0 = r8.d;	 Catch:{ all -> 0x007c }
            if (r0 == 0) goto L_0x0076;
        L_0x005a:
            r3 = r8.a;	 Catch:{ all -> 0x007c }
            r3.a(r0);	 Catch:{ all -> 0x007c }
        L_0x005f:
            monitor-enter(r8);	 Catch:{ all -> 0x007c }
            r0 = r8.g;	 Catch:{ all -> 0x0083 }
            if (r0 != 0) goto L_0x007e;
        L_0x0064:
            r0 = 0;
            r8.f = r0;	 Catch:{ all -> 0x0083 }
            monitor-exit(r8);	 Catch:{ all -> 0x0069 }
            goto L_0x001d;
        L_0x0069:
            r0 = move-exception;
        L_0x006a:
            monitor-exit(r8);	 Catch:{ all -> 0x0069 }
            throw r0;	 Catch:{ all -> 0x006c }
        L_0x006c:
            r0 = move-exception;
            r2 = r1;
        L_0x006e:
            if (r2 != 0) goto L_0x0075;
        L_0x0070:
            monitor-enter(r8);
            r1 = 0;
            r8.f = r1;	 Catch:{ all -> 0x0086 }
            monitor-exit(r8);	 Catch:{ all -> 0x0086 }
        L_0x0075:
            throw r0;
        L_0x0076:
            r0 = r8.a;	 Catch:{ all -> 0x007c }
            r0.o_();	 Catch:{ all -> 0x007c }
            goto L_0x005f;
        L_0x007c:
            r0 = move-exception;
            goto L_0x006e;
        L_0x007e:
            r0 = 0;
            r8.g = r0;	 Catch:{ all -> 0x0083 }
            monitor-exit(r8);	 Catch:{ all -> 0x0083 }
            goto L_0x0013;
        L_0x0083:
            r0 = move-exception;
            r1 = r2;
            goto L_0x006a;
        L_0x0086:
            r0 = move-exception;
            monitor-exit(r8);	 Catch:{ all -> 0x0086 }
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: dji.thirdparty.f.e.a.bw.b.c():void");
        }
    }

    static final class c<T> extends k<T> {
        private final b<T> a;

        c(b<T> bVar) {
            this.a = bVar;
        }

        public void c() {
            a(0);
        }

        public void a_(T t) {
            this.a.a_(t);
        }

        public void a(Throwable th) {
            this.a.a(th);
        }

        public void o_() {
            this.a.o_();
        }

        void b(long j) {
            a(j);
        }
    }

    public static <T> bw<T> a() {
        return a.a;
    }

    public k<? super T> a(k<? super T> kVar) {
        f bVar = new b(kVar);
        l cVar = new c(bVar);
        bVar.b = cVar;
        kVar.a(cVar);
        kVar.a((l) bVar);
        kVar.a(bVar);
        return cVar;
    }
}

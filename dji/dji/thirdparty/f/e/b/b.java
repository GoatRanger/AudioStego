package dji.thirdparty.f.e.b;

import com.here.odnp.posclient.pos.IPositioningSession;
import dji.thirdparty.f.e;
import dji.thirdparty.f.e.a.a;
import dji.thirdparty.f.f;
import dji.thirdparty.f.k;
import java.util.List;

public final class b<T> implements e<T>, f {
    static final f j = new f() {
        public void a(long j) {
        }
    };
    final k<? super T> a;
    boolean b;
    List<T> c;
    f d;
    long e;
    long f;
    f g;
    Object h;
    volatile boolean i;

    public b(k<? super T> kVar) {
        this.a = kVar;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a_(T r5) {
        /*
        r4 = this;
        monitor-enter(r4);
        r0 = r4.b;	 Catch:{ all -> 0x0037 }
        if (r0 == 0) goto L_0x0016;
    L_0x0005:
        r0 = r4.c;	 Catch:{ all -> 0x0037 }
        if (r0 != 0) goto L_0x0011;
    L_0x0009:
        r0 = new java.util.ArrayList;	 Catch:{ all -> 0x0037 }
        r1 = 4;
        r0.<init>(r1);	 Catch:{ all -> 0x0037 }
        r4.c = r0;	 Catch:{ all -> 0x0037 }
    L_0x0011:
        r0.add(r5);	 Catch:{ all -> 0x0037 }
        monitor-exit(r4);	 Catch:{ all -> 0x0037 }
    L_0x0015:
        return;
    L_0x0016:
        monitor-exit(r4);	 Catch:{ all -> 0x0037 }
        r0 = r4.a;	 Catch:{ all -> 0x0030 }
        r0.a_(r5);	 Catch:{ all -> 0x0030 }
        r0 = r4.e;	 Catch:{ all -> 0x0030 }
        r2 = 9223372036854775807; // 0x7fffffffffffffff float:NaN double:NaN;
        r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r2 == 0) goto L_0x002c;
    L_0x0027:
        r2 = 1;
        r0 = r0 - r2;
        r4.e = r0;	 Catch:{ all -> 0x0030 }
    L_0x002c:
        r4.b();	 Catch:{ all -> 0x0030 }
        goto L_0x0015;
    L_0x0030:
        r0 = move-exception;
        monitor-enter(r4);
        r1 = 0;
        r4.b = r1;	 Catch:{ all -> 0x003a }
        monitor-exit(r4);	 Catch:{ all -> 0x003a }
        throw r0;
    L_0x0037:
        r0 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x0037 }
        throw r0;
    L_0x003a:
        r0 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x003a }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: dji.thirdparty.f.e.b.b.a_(java.lang.Object):void");
    }

    public void a(Throwable th) {
        boolean z;
        synchronized (this) {
            if (this.b) {
                this.h = th;
                z = false;
            } else {
                this.b = true;
                z = true;
            }
        }
        if (z) {
            this.a.a(th);
        } else {
            this.i = true;
        }
    }

    public void o_() {
        synchronized (this) {
            if (this.b) {
                this.h = Boolean.valueOf(true);
                return;
            }
            this.b = true;
            this.a.o_();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(long r8) {
        /*
        r7 = this;
        r4 = 0;
        r0 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1));
        if (r0 >= 0) goto L_0x000e;
    L_0x0006:
        r0 = new java.lang.IllegalArgumentException;
        r1 = "n >= 0 required";
        r0.<init>(r1);
        throw r0;
    L_0x000e:
        r0 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1));
        if (r0 != 0) goto L_0x0013;
    L_0x0012:
        return;
    L_0x0013:
        monitor-enter(r7);
        r0 = r7.b;	 Catch:{ all -> 0x001f }
        if (r0 == 0) goto L_0x0022;
    L_0x0018:
        r0 = r7.f;	 Catch:{ all -> 0x001f }
        r0 = r0 + r8;
        r7.f = r0;	 Catch:{ all -> 0x001f }
        monitor-exit(r7);	 Catch:{ all -> 0x001f }
        goto L_0x0012;
    L_0x001f:
        r0 = move-exception;
        monitor-exit(r7);	 Catch:{ all -> 0x001f }
        throw r0;
    L_0x0022:
        r0 = 1;
        r7.b = r0;	 Catch:{ all -> 0x001f }
        monitor-exit(r7);	 Catch:{ all -> 0x001f }
        r2 = r7.d;
        r0 = r7.e;	 Catch:{ all -> 0x003f }
        r0 = r0 + r8;
        r3 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1));
        if (r3 >= 0) goto L_0x0034;
    L_0x002f:
        r0 = 9223372036854775807; // 0x7fffffffffffffff float:NaN double:NaN;
    L_0x0034:
        r7.e = r0;	 Catch:{ all -> 0x003f }
        r7.b();	 Catch:{ all -> 0x003f }
        if (r2 == 0) goto L_0x0012;
    L_0x003b:
        r2.a(r8);
        goto L_0x0012;
    L_0x003f:
        r0 = move-exception;
        monitor-enter(r7);
        r1 = 0;
        r7.b = r1;	 Catch:{ all -> 0x0046 }
        monitor-exit(r7);	 Catch:{ all -> 0x0046 }
        throw r0;
    L_0x0046:
        r0 = move-exception;
        monitor-exit(r7);	 Catch:{ all -> 0x0046 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: dji.thirdparty.f.e.b.b.a(long):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(dji.thirdparty.f.f r5) {
        /*
        r4 = this;
        monitor-enter(r4);
        r0 = r4.b;	 Catch:{ all -> 0x0025 }
        if (r0 == 0) goto L_0x000e;
    L_0x0005:
        if (r5 == 0) goto L_0x000b;
    L_0x0007:
        r4.g = r5;	 Catch:{ all -> 0x0025 }
        monitor-exit(r4);	 Catch:{ all -> 0x0025 }
    L_0x000a:
        return;
    L_0x000b:
        r5 = j;	 Catch:{ all -> 0x0025 }
        goto L_0x0007;
    L_0x000e:
        r0 = 1;
        r4.b = r0;	 Catch:{ all -> 0x0025 }
        monitor-exit(r4);	 Catch:{ all -> 0x0025 }
        r4.d = r5;
        r0 = r4.e;
        r4.b();	 Catch:{ all -> 0x0028 }
        if (r5 == 0) goto L_0x000a;
    L_0x001b:
        r2 = 0;
        r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r2 == 0) goto L_0x000a;
    L_0x0021:
        r5.a(r0);
        goto L_0x000a;
    L_0x0025:
        r0 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x0025 }
        throw r0;
    L_0x0028:
        r0 = move-exception;
        monitor-enter(r4);
        r1 = 0;
        r4.b = r1;	 Catch:{ all -> 0x002f }
        monitor-exit(r4);	 Catch:{ all -> 0x002f }
        throw r0;
    L_0x002f:
        r0 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x002f }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: dji.thirdparty.f.e.b.b.a(dji.thirdparty.f.f):void");
    }

    void b() {
        e eVar = this.a;
        long j = 0;
        f fVar = null;
        while (true) {
            Object obj = null;
            synchronized (this) {
                long j2 = this.f;
                f fVar2 = this.g;
                Boolean bool = this.h;
                List list = this.c;
                if (j2 == 0 && fVar2 == null && list == null && bool == null) {
                    this.b = false;
                    obj = 1;
                } else {
                    this.f = 0;
                    this.g = null;
                    this.c = null;
                    this.h = null;
                }
            }
            if (obj != null) {
                break;
            }
            f fVar3;
            long b;
            obj = (list == null || list.isEmpty()) ? 1 : null;
            if (bool != null) {
                if (bool != Boolean.TRUE) {
                    eVar.a((Throwable) bool);
                    return;
                } else if (obj != null) {
                    eVar.o_();
                    return;
                }
            }
            long j3 = 0;
            if (list != null) {
                for (Object obj2 : list) {
                    if (!eVar.b()) {
                        if (this.i) {
                            continue;
                            break;
                        }
                        try {
                            eVar.a_(obj2);
                        } catch (Throwable th) {
                            dji.thirdparty.f.c.b.a(th, eVar, obj2);
                            return;
                        }
                    }
                    return;
                }
                j3 = 0 + ((long) list.size());
            }
            long j4 = this.e;
            if (j4 != IPositioningSession.NotSet) {
                if (j2 != 0) {
                    j4 += j2;
                    if (j4 < 0) {
                        j4 = IPositioningSession.NotSet;
                    }
                }
                if (j3 == 0 || j4 == IPositioningSession.NotSet) {
                    j3 = j4;
                } else {
                    j3 = j4 - j3;
                    if (j3 < 0) {
                        throw new IllegalStateException("More produced than requested");
                    }
                }
                this.e = j3;
            } else {
                j3 = j4;
            }
            if (fVar2 == null) {
                fVar3 = this.d;
                if (!(fVar3 == null || j2 == 0)) {
                    b = a.b(j, j2);
                }
                fVar3 = fVar;
                b = j;
            } else if (fVar2 == j) {
                this.d = null;
                fVar3 = fVar;
                b = j;
            } else {
                this.d = fVar2;
                if (j3 != 0) {
                    f fVar4 = fVar2;
                    b = a.b(j, j3);
                    fVar3 = fVar4;
                }
                fVar3 = fVar;
                b = j;
            }
            fVar = fVar3;
            j = b;
        }
        if (j != 0 && fVar != null) {
            fVar.a(j);
        }
    }
}

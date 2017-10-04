package dji.thirdparty.f.e.b;

import dji.thirdparty.f.f;

public final class a implements f {
    static final f g = new f() {
        public void a(long j) {
        }
    };
    long a;
    f b;
    boolean c;
    long d;
    long e;
    f f;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(long r6) {
        /*
        r5 = this;
        r2 = 0;
        r0 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1));
        if (r0 >= 0) goto L_0x000e;
    L_0x0006:
        r0 = new java.lang.IllegalArgumentException;
        r1 = "n >= 0 required";
        r0.<init>(r1);
        throw r0;
    L_0x000e:
        r0 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1));
        if (r0 != 0) goto L_0x0013;
    L_0x0012:
        return;
    L_0x0013:
        monitor-enter(r5);
        r0 = r5.c;	 Catch:{ all -> 0x001f }
        if (r0 == 0) goto L_0x0022;
    L_0x0018:
        r0 = r5.d;	 Catch:{ all -> 0x001f }
        r0 = r0 + r6;
        r5.d = r0;	 Catch:{ all -> 0x001f }
        monitor-exit(r5);	 Catch:{ all -> 0x001f }
        goto L_0x0012;
    L_0x001f:
        r0 = move-exception;
        monitor-exit(r5);	 Catch:{ all -> 0x001f }
        throw r0;
    L_0x0022:
        r0 = 1;
        r5.c = r0;	 Catch:{ all -> 0x001f }
        monitor-exit(r5);	 Catch:{ all -> 0x001f }
        r0 = r5.a;	 Catch:{ all -> 0x003f }
        r0 = r0 + r6;
        r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r2 >= 0) goto L_0x0032;
    L_0x002d:
        r0 = 9223372036854775807; // 0x7fffffffffffffff float:NaN double:NaN;
    L_0x0032:
        r5.a = r0;	 Catch:{ all -> 0x003f }
        r0 = r5.b;	 Catch:{ all -> 0x003f }
        if (r0 == 0) goto L_0x003b;
    L_0x0038:
        r0.a(r6);	 Catch:{ all -> 0x003f }
    L_0x003b:
        r5.a();	 Catch:{ all -> 0x003f }
        goto L_0x0012;
    L_0x003f:
        r0 = move-exception;
        monitor-enter(r5);
        r1 = 0;
        r5.c = r1;	 Catch:{ all -> 0x0046 }
        monitor-exit(r5);	 Catch:{ all -> 0x0046 }
        throw r0;
    L_0x0046:
        r0 = move-exception;
        monitor-exit(r5);	 Catch:{ all -> 0x0046 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: dji.thirdparty.f.e.b.a.a(long):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b(long r8) {
        /*
        r7 = this;
        r4 = 0;
        r0 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1));
        if (r0 > 0) goto L_0x000e;
    L_0x0006:
        r0 = new java.lang.IllegalArgumentException;
        r1 = "n > 0 required";
        r0.<init>(r1);
        throw r0;
    L_0x000e:
        monitor-enter(r7);
        r0 = r7.c;	 Catch:{ all -> 0x003d }
        if (r0 == 0) goto L_0x001a;
    L_0x0013:
        r0 = r7.e;	 Catch:{ all -> 0x003d }
        r0 = r0 + r8;
        r7.e = r0;	 Catch:{ all -> 0x003d }
        monitor-exit(r7);	 Catch:{ all -> 0x003d }
    L_0x0019:
        return;
    L_0x001a:
        r0 = 1;
        r7.c = r0;	 Catch:{ all -> 0x003d }
        monitor-exit(r7);	 Catch:{ all -> 0x003d }
        r0 = r7.a;	 Catch:{ all -> 0x0036 }
        r2 = 9223372036854775807; // 0x7fffffffffffffff float:NaN double:NaN;
        r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r2 == 0) goto L_0x0042;
    L_0x0029:
        r0 = r0 - r8;
        r2 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1));
        if (r2 >= 0) goto L_0x0040;
    L_0x002e:
        r0 = new java.lang.IllegalStateException;	 Catch:{ all -> 0x0036 }
        r1 = "more items arrived than were requested";
        r0.<init>(r1);	 Catch:{ all -> 0x0036 }
        throw r0;	 Catch:{ all -> 0x0036 }
    L_0x0036:
        r0 = move-exception;
        monitor-enter(r7);
        r1 = 0;
        r7.c = r1;	 Catch:{ all -> 0x0046 }
        monitor-exit(r7);	 Catch:{ all -> 0x0046 }
        throw r0;
    L_0x003d:
        r0 = move-exception;
        monitor-exit(r7);	 Catch:{ all -> 0x003d }
        throw r0;
    L_0x0040:
        r7.a = r0;	 Catch:{ all -> 0x0036 }
    L_0x0042:
        r7.a();	 Catch:{ all -> 0x0036 }
        goto L_0x0019;
    L_0x0046:
        r0 = move-exception;
        monitor-exit(r7);	 Catch:{ all -> 0x0046 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: dji.thirdparty.f.e.b.a.b(long):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(dji.thirdparty.f.f r3) {
        /*
        r2 = this;
        monitor-enter(r2);
        r0 = r2.c;	 Catch:{ all -> 0x0025 }
        if (r0 == 0) goto L_0x000d;
    L_0x0005:
        if (r3 != 0) goto L_0x0009;
    L_0x0007:
        r3 = g;	 Catch:{ all -> 0x0025 }
    L_0x0009:
        r2.f = r3;	 Catch:{ all -> 0x0025 }
        monitor-exit(r2);	 Catch:{ all -> 0x0025 }
    L_0x000c:
        return;
    L_0x000d:
        r0 = 1;
        r2.c = r0;	 Catch:{ all -> 0x0025 }
        monitor-exit(r2);	 Catch:{ all -> 0x0025 }
        r2.b = r3;	 Catch:{ all -> 0x001e }
        if (r3 == 0) goto L_0x001a;
    L_0x0015:
        r0 = r2.a;	 Catch:{ all -> 0x001e }
        r3.a(r0);	 Catch:{ all -> 0x001e }
    L_0x001a:
        r2.a();	 Catch:{ all -> 0x001e }
        goto L_0x000c;
    L_0x001e:
        r0 = move-exception;
        monitor-enter(r2);
        r1 = 0;
        r2.c = r1;	 Catch:{ all -> 0x0028 }
        monitor-exit(r2);	 Catch:{ all -> 0x0028 }
        throw r0;
    L_0x0025:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0025 }
        throw r0;
    L_0x0028:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0028 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: dji.thirdparty.f.e.b.a.a(dji.thirdparty.f.f):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a() {
        /*
        r13 = this;
        r12 = 0;
        r2 = 9223372036854775807; // 0x7fffffffffffffff float:NaN double:NaN;
        r10 = 0;
    L_0x0008:
        monitor-enter(r13);
        r4 = r13.d;	 Catch:{ all -> 0x0045 }
        r6 = r13.e;	 Catch:{ all -> 0x0045 }
        r8 = r13.f;	 Catch:{ all -> 0x0045 }
        r0 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1));
        if (r0 != 0) goto L_0x001e;
    L_0x0013:
        r0 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1));
        if (r0 != 0) goto L_0x001e;
    L_0x0017:
        if (r8 != 0) goto L_0x001e;
    L_0x0019:
        r0 = 0;
        r13.c = r0;	 Catch:{ all -> 0x0045 }
        monitor-exit(r13);	 Catch:{ all -> 0x0045 }
        return;
    L_0x001e:
        r0 = 0;
        r13.d = r0;	 Catch:{ all -> 0x0045 }
        r0 = 0;
        r13.e = r0;	 Catch:{ all -> 0x0045 }
        r0 = 0;
        r13.f = r0;	 Catch:{ all -> 0x0045 }
        monitor-exit(r13);	 Catch:{ all -> 0x0045 }
        r0 = r13.a;
        r9 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r9 == 0) goto L_0x003c;
    L_0x0030:
        r0 = r0 + r4;
        r9 = (r0 > r10 ? 1 : (r0 == r10 ? 0 : -1));
        if (r9 < 0) goto L_0x0039;
    L_0x0035:
        r9 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r9 != 0) goto L_0x0048;
    L_0x0039:
        r13.a = r2;
        r0 = r2;
    L_0x003c:
        if (r8 == 0) goto L_0x005e;
    L_0x003e:
        r4 = g;
        if (r8 != r4) goto L_0x0058;
    L_0x0042:
        r13.b = r12;
        goto L_0x0008;
    L_0x0045:
        r0 = move-exception;
        monitor-exit(r13);	 Catch:{ all -> 0x0045 }
        throw r0;
    L_0x0048:
        r0 = r0 - r6;
        r6 = (r0 > r10 ? 1 : (r0 == r10 ? 0 : -1));
        if (r6 >= 0) goto L_0x0055;
    L_0x004d:
        r0 = new java.lang.IllegalStateException;
        r1 = "more produced than requested";
        r0.<init>(r1);
        throw r0;
    L_0x0055:
        r13.a = r0;
        goto L_0x003c;
    L_0x0058:
        r13.b = r8;
        r8.a(r0);
        goto L_0x0008;
    L_0x005e:
        r0 = r13.b;
        if (r0 == 0) goto L_0x0008;
    L_0x0062:
        r1 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1));
        if (r1 == 0) goto L_0x0008;
    L_0x0066:
        r0.a(r4);
        goto L_0x0008;
        */
        throw new UnsupportedOperationException("Method not decompiled: dji.thirdparty.f.e.b.a.a():void");
    }
}

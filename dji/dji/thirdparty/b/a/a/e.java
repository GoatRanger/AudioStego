package dji.thirdparty.b.a.a;

import dji.thirdparty.c.v;
import dji.thirdparty.c.w;
import dji.thirdparty.c.x;
import java.io.EOFException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

public final class e {
    static final /* synthetic */ boolean d = (!e.class.desiredAssertionStatus());
    long a = 0;
    long b;
    final a c;
    private final int e;
    private final d f;
    private final List<f> g;
    private List<f> h;
    private final b i;
    private final c j = new c(this);
    private final c k = new c(this);
    private a l = null;

    final class a implements v {
        static final /* synthetic */ boolean a = (!e.class.desiredAssertionStatus());
        private static final long c = 16384;
        final /* synthetic */ e b;
        private final dji.thirdparty.c.c d = new dji.thirdparty.c.c();
        private boolean e;
        private boolean f;

        a(e eVar) {
            this.b = eVar;
        }

        public void a_(dji.thirdparty.c.c cVar, long j) throws IOException {
            if (a || !Thread.holdsLock(this.b)) {
                this.d.a_(cVar, j);
                while (this.d.b() >= 16384) {
                    a(false);
                }
                return;
            }
            throw new AssertionError();
        }

        private void a(boolean z) throws IOException {
            synchronized (this.b) {
                this.b.k.c();
                while (this.b.b <= 0 && !this.f && !this.e && this.b.l == null) {
                    try {
                        this.b.o();
                    } catch (Throwable th) {
                        this.b.k.b();
                    }
                }
                this.b.k.b();
                this.b.n();
                long min = Math.min(this.b.b, this.d.b());
                e eVar = this.b;
                eVar.b -= min;
            }
            this.b.k.c();
            try {
                d a = this.b.f;
                int b = this.b.e;
                boolean z2 = z && min == this.d.b();
                a.a(b, z2, this.d, min);
            } finally {
                this.b.k.b();
            }
        }

        public void flush() throws IOException {
            if (a || !Thread.holdsLock(this.b)) {
                synchronized (this.b) {
                    this.b.n();
                }
                while (this.d.b() > 0) {
                    a(false);
                    this.b.f.g();
                }
                return;
            }
            throw new AssertionError();
        }

        public x a() {
            return this.b.k;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void close() throws java.io.IOException {
            /*
            r6 = this;
            r4 = 0;
            r2 = 1;
            r0 = a;
            if (r0 != 0) goto L_0x0015;
        L_0x0007:
            r0 = r6.b;
            r0 = java.lang.Thread.holdsLock(r0);
            if (r0 == 0) goto L_0x0015;
        L_0x000f:
            r0 = new java.lang.AssertionError;
            r0.<init>();
            throw r0;
        L_0x0015:
            r1 = r6.b;
            monitor-enter(r1);
            r0 = r6.e;	 Catch:{ all -> 0x003f }
            if (r0 == 0) goto L_0x001e;
        L_0x001c:
            monitor-exit(r1);	 Catch:{ all -> 0x003f }
        L_0x001d:
            return;
        L_0x001e:
            monitor-exit(r1);	 Catch:{ all -> 0x003f }
            r0 = r6.b;
            r0 = r0.c;
            r0 = r0.f;
            if (r0 != 0) goto L_0x0052;
        L_0x0027:
            r0 = r6.d;
            r0 = r0.b();
            r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1));
            if (r0 <= 0) goto L_0x0042;
        L_0x0031:
            r0 = r6.d;
            r0 = r0.b();
            r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1));
            if (r0 <= 0) goto L_0x0052;
        L_0x003b:
            r6.a(r2);
            goto L_0x0031;
        L_0x003f:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x003f }
            throw r0;
        L_0x0042:
            r0 = r6.b;
            r0 = r0.f;
            r1 = r6.b;
            r1 = r1.e;
            r3 = 0;
            r0.a(r1, r2, r3, r4);
        L_0x0052:
            r1 = r6.b;
            monitor-enter(r1);
            r0 = 1;
            r6.e = r0;	 Catch:{ all -> 0x0068 }
            monitor-exit(r1);	 Catch:{ all -> 0x0068 }
            r0 = r6.b;
            r0 = r0.f;
            r0.g();
            r0 = r6.b;
            r0.m();
            goto L_0x001d;
        L_0x0068:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x0068 }
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: dji.thirdparty.b.a.a.e.a.close():void");
        }
    }

    private final class b implements w {
        static final /* synthetic */ boolean a = (!e.class.desiredAssertionStatus());
        final /* synthetic */ e b;
        private final dji.thirdparty.c.c c;
        private final dji.thirdparty.c.c d;
        private final long e;
        private boolean f;
        private boolean g;

        private b(e eVar, long j) {
            this.b = eVar;
            this.c = new dji.thirdparty.c.c();
            this.d = new dji.thirdparty.c.c();
            this.e = j;
        }

        public long a(dji.thirdparty.c.c cVar, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            }
            long j2;
            synchronized (this.b) {
                b();
                c();
                if (this.d.b() == 0) {
                    j2 = -1;
                } else {
                    j2 = this.d.a(cVar, Math.min(j, this.d.b()));
                    e eVar = this.b;
                    eVar.a += j2;
                    if (this.b.a >= ((long) (this.b.f.e.l(65536) / 2))) {
                        this.b.f.a(this.b.e, this.b.a);
                        this.b.a = 0;
                    }
                    synchronized (this.b.f) {
                        d a = this.b.f;
                        a.c += j2;
                        if (this.b.f.c >= ((long) (this.b.f.e.l(65536) / 2))) {
                            this.b.f.a(0, this.b.f.c);
                            this.b.f.c = 0;
                        }
                    }
                }
            }
            return j2;
        }

        private void b() throws IOException {
            this.b.j.c();
            while (this.d.b() == 0 && !this.g && !this.f && this.b.l == null) {
                try {
                    this.b.o();
                } catch (Throwable th) {
                    this.b.j.b();
                }
            }
            this.b.j.b();
        }

        void a(dji.thirdparty.c.e eVar, long j) throws IOException {
            if (a || !Thread.holdsLock(this.b)) {
                while (j > 0) {
                    boolean z;
                    Object obj;
                    synchronized (this.b) {
                        z = this.g;
                        obj = this.d.b() + j > this.e ? 1 : null;
                    }
                    if (obj != null) {
                        eVar.h(j);
                        this.b.b(a.FLOW_CONTROL_ERROR);
                        return;
                    } else if (z) {
                        eVar.h(j);
                        return;
                    } else {
                        long a = eVar.a(this.c, j);
                        if (a == -1) {
                            throw new EOFException();
                        }
                        j -= a;
                        synchronized (this.b) {
                            if (this.d.b() == 0) {
                                obj = 1;
                            } else {
                                obj = null;
                            }
                            this.d.a(this.c);
                            if (obj != null) {
                                this.b.notifyAll();
                            }
                        }
                    }
                }
                return;
            }
            throw new AssertionError();
        }

        public x a() {
            return this.b.j;
        }

        public void close() throws IOException {
            synchronized (this.b) {
                this.f = true;
                this.d.y();
                this.b.notifyAll();
            }
            this.b.m();
        }

        private void c() throws IOException {
            if (this.f) {
                throw new IOException("stream closed");
            } else if (this.b.l != null) {
                throw new IOException("stream was reset: " + this.b.l);
            }
        }
    }

    class c extends dji.thirdparty.c.a {
        final /* synthetic */ e a;

        c(e eVar) {
            this.a = eVar;
        }

        protected void a() {
            this.a.b(a.CANCEL);
        }

        protected IOException a(IOException iOException) {
            IOException socketTimeoutException = new SocketTimeoutException(com.alipay.sdk.c.a.f);
            if (iOException != null) {
                socketTimeoutException.initCause(iOException);
            }
            return socketTimeoutException;
        }

        public void b() throws IOException {
            if (j_()) {
                throw a(null);
            }
        }
    }

    e(int i, d dVar, boolean z, boolean z2, List<f> list) {
        if (dVar == null) {
            throw new NullPointerException("connection == null");
        } else if (list == null) {
            throw new NullPointerException("requestHeaders == null");
        } else {
            this.e = i;
            this.f = dVar;
            this.b = (long) dVar.f.l(65536);
            this.i = new b((long) dVar.e.l(65536));
            this.c = new a(this);
            this.i.g = z2;
            this.c.f = z;
            this.g = list;
        }
    }

    public int a() {
        return this.e;
    }

    public synchronized boolean b() {
        boolean z = false;
        synchronized (this) {
            if (this.l == null) {
                if (!(this.i.g || this.i.f) || (!(this.c.f || this.c.e) || this.h == null)) {
                    z = true;
                }
            }
        }
        return z;
    }

    public boolean c() {
        boolean z;
        if ((this.e & 1) == 1) {
            z = true;
        } else {
            z = false;
        }
        return this.f.b == z;
    }

    public d d() {
        return this.f;
    }

    public List<f> e() {
        return this.g;
    }

    public synchronized List<f> f() throws IOException {
        this.j.c();
        while (this.h == null && this.l == null) {
            try {
                o();
            } catch (Throwable th) {
                this.j.b();
            }
        }
        this.j.b();
        if (this.h != null) {
        } else {
            throw new IOException("stream was reset: " + this.l);
        }
        return this.h;
    }

    public synchronized a g() {
        return this.l;
    }

    public void a(List<f> list, boolean z) throws IOException {
        boolean z2 = true;
        if (d || !Thread.holdsLock(this)) {
            synchronized (this) {
                if (list == null) {
                    throw new NullPointerException("responseHeaders == null");
                } else if (this.h != null) {
                    throw new IllegalStateException("reply already sent");
                } else {
                    this.h = list;
                    if (z) {
                        z2 = false;
                    } else {
                        this.c.f = true;
                    }
                }
            }
            this.f.a(this.e, z2, (List) list);
            if (z2) {
                this.f.g();
                return;
            }
            return;
        }
        throw new AssertionError();
    }

    public x h() {
        return this.j;
    }

    public x i() {
        return this.k;
    }

    public w j() {
        return this.i;
    }

    public v k() {
        synchronized (this) {
            if (this.h != null || c()) {
            } else {
                throw new IllegalStateException("reply before requesting the sink");
            }
        }
        return this.c;
    }

    public void a(a aVar) throws IOException {
        if (d(aVar)) {
            this.f.b(this.e, aVar);
        }
    }

    public void b(a aVar) {
        if (d(aVar)) {
            this.f.a(this.e, aVar);
        }
    }

    private boolean d(a aVar) {
        if (d || !Thread.holdsLock(this)) {
            synchronized (this) {
                if (this.l != null) {
                    return false;
                } else if (this.i.g && this.c.f) {
                    return false;
                } else {
                    this.l = aVar;
                    notifyAll();
                    this.f.b(this.e);
                    return true;
                }
            }
        }
        throw new AssertionError();
    }

    void a(List<f> list, g gVar) {
        if (d || !Thread.holdsLock(this)) {
            a aVar = null;
            boolean z = true;
            synchronized (this) {
                if (this.h == null) {
                    if (gVar.c()) {
                        aVar = a.PROTOCOL_ERROR;
                    } else {
                        this.h = list;
                        z = b();
                        notifyAll();
                    }
                } else if (gVar.d()) {
                    aVar = a.STREAM_IN_USE;
                } else {
                    List arrayList = new ArrayList();
                    arrayList.addAll(this.h);
                    arrayList.addAll(list);
                    this.h = arrayList;
                }
            }
            if (aVar != null) {
                b(aVar);
                return;
            } else if (!z) {
                this.f.b(this.e);
                return;
            } else {
                return;
            }
        }
        throw new AssertionError();
    }

    void a(dji.thirdparty.c.e eVar, int i) throws IOException {
        if (d || !Thread.holdsLock(this)) {
            this.i.a(eVar, (long) i);
            return;
        }
        throw new AssertionError();
    }

    void l() {
        if (d || !Thread.holdsLock(this)) {
            boolean b;
            synchronized (this) {
                this.i.g = true;
                b = b();
                notifyAll();
            }
            if (!b) {
                this.f.b(this.e);
                return;
            }
            return;
        }
        throw new AssertionError();
    }

    synchronized void c(a aVar) {
        if (this.l == null) {
            this.l = aVar;
            notifyAll();
        }
    }

    private void m() throws IOException {
        if (d || !Thread.holdsLock(this)) {
            Object obj;
            boolean b;
            synchronized (this) {
                obj = (!this.i.g && this.i.f && (this.c.f || this.c.e)) ? 1 : null;
                b = b();
            }
            if (obj != null) {
                a(a.CANCEL);
                return;
            } else if (!b) {
                this.f.b(this.e);
                return;
            } else {
                return;
            }
        }
        throw new AssertionError();
    }

    void a(long j) {
        this.b += j;
        if (j > 0) {
            notifyAll();
        }
    }

    private void n() throws IOException {
        if (this.c.e) {
            throw new IOException("stream closed");
        } else if (this.c.f) {
            throw new IOException("stream finished");
        } else if (this.l != null) {
            throw new IOException("stream was reset: " + this.l);
        }
    }

    private void o() throws InterruptedIOException {
        try {
            wait();
        } catch (InterruptedException e) {
            throw new InterruptedIOException();
        }
    }
}

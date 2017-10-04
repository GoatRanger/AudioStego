package dji.thirdparty.b.a.a;

import com.here.odnp.posclient.pos.IPositioningSession;
import dji.thirdparty.b.a.f;
import dji.thirdparty.b.a.j;
import dji.thirdparty.b.z;
import dji.thirdparty.c.e;
import dji.thirdparty.c.p;
import java.io.Closeable;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class d implements Closeable {
    static final /* synthetic */ boolean k;
    private static final ExecutorService l = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue(), j.a("OkHttp FramedConnection", true));
    private static final int x = 16777216;
    final z a;
    final boolean b;
    long c;
    long d;
    n e;
    final n f;
    final p g;
    final Socket h;
    final c i;
    final c j;
    private final b m;
    private final Map<Integer, e> n;
    private final String o;
    private int p;
    private int q;
    private boolean r;
    private long s;
    private final ExecutorService t;
    private Map<Integer, l> u;
    private final m v;
    private int w;
    private boolean y;
    private final Set<Integer> z;

    public static class a {
        private Socket a;
        private String b;
        private e c;
        private dji.thirdparty.c.d d;
        private b e = b.a;
        private z f = z.SPDY_3;
        private m g = m.a;
        private boolean h;

        public a(boolean z) throws IOException {
            this.h = z;
        }

        public a a(Socket socket) throws IOException {
            return a(socket, ((InetSocketAddress) socket.getRemoteSocketAddress()).getHostName(), p.a(p.b(socket)), p.a(p.a(socket)));
        }

        public a a(Socket socket, String str, e eVar, dji.thirdparty.c.d dVar) {
            this.a = socket;
            this.b = str;
            this.c = eVar;
            this.d = dVar;
            return this;
        }

        public a a(b bVar) {
            this.e = bVar;
            return this;
        }

        public a a(z zVar) {
            this.f = zVar;
            return this;
        }

        public a a(m mVar) {
            this.g = mVar;
            return this;
        }

        public d a() throws IOException {
            return new d();
        }
    }

    public static abstract class b {
        public static final b a = new b() {
            public void a(e eVar) throws IOException {
                eVar.a(a.REFUSED_STREAM);
            }
        };

        public abstract void a(e eVar) throws IOException;

        public void a(d dVar) {
        }
    }

    class c extends f implements dji.thirdparty.b.a.a.b.a {
        final b a;
        final /* synthetic */ d c;

        private c(d dVar, b bVar) {
            this.c = dVar;
            super("OkHttp %s", dVar.o);
            this.a = bVar;
        }

        protected void f() {
            a aVar;
            Throwable th;
            a aVar2 = a.INTERNAL_ERROR;
            a aVar3 = a.INTERNAL_ERROR;
            try {
                if (!this.c.b) {
                    this.a.a();
                }
                do {
                } while (this.a.a(this));
                try {
                    this.c.a(a.NO_ERROR, a.CANCEL);
                } catch (IOException e) {
                }
                j.a(this.a);
            } catch (IOException e2) {
                aVar = a.PROTOCOL_ERROR;
                try {
                    this.c.a(aVar, a.PROTOCOL_ERROR);
                } catch (IOException e3) {
                }
                j.a(this.a);
            } catch (Throwable th2) {
                th = th2;
                this.c.a(aVar, aVar3);
                j.a(this.a);
                throw th;
            }
        }

        public void a(boolean z, int i, e eVar, int i2) throws IOException {
            if (this.c.d(i)) {
                this.c.a(i, eVar, i2, z);
                return;
            }
            e a = this.c.a(i);
            if (a == null) {
                this.c.a(i, a.INVALID_STREAM);
                eVar.h((long) i2);
                return;
            }
            a.a(eVar, i2);
            if (z) {
                a.l();
            }
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void a(boolean r9, boolean r10, int r11, int r12, java.util.List<dji.thirdparty.b.a.a.f> r13, dji.thirdparty.b.a.a.g r14) {
            /*
            r8 = this;
            r0 = r8.c;
            r0 = r0.d(r11);
            if (r0 == 0) goto L_0x000e;
        L_0x0008:
            r0 = r8.c;
            r0.b(r11, r13, r10);
        L_0x000d:
            return;
        L_0x000e:
            r6 = r8.c;
            monitor-enter(r6);
            r0 = r8.c;	 Catch:{ all -> 0x001b }
            r0 = r0.r;	 Catch:{ all -> 0x001b }
            if (r0 == 0) goto L_0x001e;
        L_0x0019:
            monitor-exit(r6);	 Catch:{ all -> 0x001b }
            goto L_0x000d;
        L_0x001b:
            r0 = move-exception;
            monitor-exit(r6);	 Catch:{ all -> 0x001b }
            throw r0;
        L_0x001e:
            r0 = r8.c;	 Catch:{ all -> 0x001b }
            r0 = r0.a(r11);	 Catch:{ all -> 0x001b }
            if (r0 != 0) goto L_0x008d;
        L_0x0026:
            r0 = r14.a();	 Catch:{ all -> 0x001b }
            if (r0 == 0) goto L_0x0035;
        L_0x002c:
            r0 = r8.c;	 Catch:{ all -> 0x001b }
            r1 = dji.thirdparty.b.a.a.a.INVALID_STREAM;	 Catch:{ all -> 0x001b }
            r0.a(r11, r1);	 Catch:{ all -> 0x001b }
            monitor-exit(r6);	 Catch:{ all -> 0x001b }
            goto L_0x000d;
        L_0x0035:
            r0 = r8.c;	 Catch:{ all -> 0x001b }
            r0 = r0.p;	 Catch:{ all -> 0x001b }
            if (r11 > r0) goto L_0x003f;
        L_0x003d:
            monitor-exit(r6);	 Catch:{ all -> 0x001b }
            goto L_0x000d;
        L_0x003f:
            r0 = r11 % 2;
            r1 = r8.c;	 Catch:{ all -> 0x001b }
            r1 = r1.q;	 Catch:{ all -> 0x001b }
            r1 = r1 % 2;
            if (r0 != r1) goto L_0x004d;
        L_0x004b:
            monitor-exit(r6);	 Catch:{ all -> 0x001b }
            goto L_0x000d;
        L_0x004d:
            r0 = new dji.thirdparty.b.a.a.e;	 Catch:{ all -> 0x001b }
            r2 = r8.c;	 Catch:{ all -> 0x001b }
            r1 = r11;
            r3 = r9;
            r4 = r10;
            r5 = r13;
            r0.<init>(r1, r2, r3, r4, r5);	 Catch:{ all -> 0x001b }
            r1 = r8.c;	 Catch:{ all -> 0x001b }
            r1.p = r11;	 Catch:{ all -> 0x001b }
            r1 = r8.c;	 Catch:{ all -> 0x001b }
            r1 = r1.n;	 Catch:{ all -> 0x001b }
            r2 = java.lang.Integer.valueOf(r11);	 Catch:{ all -> 0x001b }
            r1.put(r2, r0);	 Catch:{ all -> 0x001b }
            r1 = dji.thirdparty.b.a.a.d.l;	 Catch:{ all -> 0x001b }
            r2 = new dji.thirdparty.b.a.a.d$c$1;	 Catch:{ all -> 0x001b }
            r3 = "OkHttp %s stream %d";
            r4 = 2;
            r4 = new java.lang.Object[r4];	 Catch:{ all -> 0x001b }
            r5 = 0;
            r7 = r8.c;	 Catch:{ all -> 0x001b }
            r7 = r7.o;	 Catch:{ all -> 0x001b }
            r4[r5] = r7;	 Catch:{ all -> 0x001b }
            r5 = 1;
            r7 = java.lang.Integer.valueOf(r11);	 Catch:{ all -> 0x001b }
            r4[r5] = r7;	 Catch:{ all -> 0x001b }
            r2.<init>(r8, r3, r4, r0);	 Catch:{ all -> 0x001b }
            r1.execute(r2);	 Catch:{ all -> 0x001b }
            monitor-exit(r6);	 Catch:{ all -> 0x001b }
            goto L_0x000d;
        L_0x008d:
            monitor-exit(r6);	 Catch:{ all -> 0x001b }
            r1 = r14.b();
            if (r1 == 0) goto L_0x00a0;
        L_0x0094:
            r1 = dji.thirdparty.b.a.a.a.PROTOCOL_ERROR;
            r0.b(r1);
            r0 = r8.c;
            r0.b(r11);
            goto L_0x000d;
        L_0x00a0:
            r0.a(r13, r14);
            if (r10 == 0) goto L_0x000d;
        L_0x00a5:
            r0.l();
            goto L_0x000d;
            */
            throw new UnsupportedOperationException("Method not decompiled: dji.thirdparty.b.a.a.d.c.a(boolean, boolean, int, int, java.util.List, dji.thirdparty.b.a.a.g):void");
        }

        public void a(int i, a aVar) {
            if (this.c.d(i)) {
                this.c.c(i, aVar);
                return;
            }
            e b = this.c.b(i);
            if (b != null) {
                b.c(aVar);
            }
        }

        public void a(boolean z, n nVar) {
            e[] eVarArr;
            long j;
            synchronized (this.c) {
                int l = this.c.f.l(65536);
                if (z) {
                    this.c.f.a();
                }
                this.c.f.a(nVar);
                if (this.c.a() == z.HTTP_2) {
                    a(nVar);
                }
                int l2 = this.c.f.l(65536);
                if (l2 == -1 || l2 == l) {
                    eVarArr = null;
                    j = 0;
                } else {
                    long j2 = (long) (l2 - l);
                    if (!this.c.y) {
                        this.c.a(j2);
                        this.c.y = true;
                    }
                    if (this.c.n.isEmpty()) {
                        j = j2;
                        eVarArr = null;
                    } else {
                        j = j2;
                        eVarArr = (e[]) this.c.n.values().toArray(new e[this.c.n.size()]);
                    }
                }
                d.l.execute(new f(this, "OkHttp %s settings", this.c.o) {
                    final /* synthetic */ c a;

                    public void f() {
                        this.a.c.m.a(this.a.c);
                    }
                });
            }
            if (eVarArr != null && j != 0) {
                for (e eVar : eVarArr) {
                    synchronized (eVar) {
                        eVar.a(j);
                    }
                }
            }
        }

        private void a(final n nVar) {
            d.l.execute(new f(this, "OkHttp %s ACK Settings", new Object[]{this.c.o}) {
                final /* synthetic */ c c;

                public void f() {
                    try {
                        this.c.c.i.a(nVar);
                    } catch (IOException e) {
                    }
                }
            });
        }

        public void a() {
        }

        public void a(boolean z, int i, int i2) {
            if (z) {
                l c = this.c.c(i);
                if (c != null) {
                    c.b();
                    return;
                }
                return;
            }
            this.c.a(true, i, i2, null);
        }

        public void a(int i, a aVar, dji.thirdparty.c.f fVar) {
            if (fVar.j() > 0) {
            }
            synchronized (this.c) {
                e[] eVarArr = (e[]) this.c.n.values().toArray(new e[this.c.n.size()]);
                this.c.r = true;
            }
            for (e eVar : eVarArr) {
                if (eVar.a() > i && eVar.c()) {
                    eVar.c(a.REFUSED_STREAM);
                    this.c.b(eVar.a());
                }
            }
        }

        public void a(int i, long j) {
            if (i == 0) {
                synchronized (this.c) {
                    d dVar = this.c;
                    dVar.d += j;
                    this.c.notifyAll();
                }
                return;
            }
            e a = this.c.a(i);
            if (a != null) {
                synchronized (a) {
                    a.a(j);
                }
            }
        }

        public void a(int i, int i2, int i3, boolean z) {
        }

        public void a(int i, int i2, List<f> list) {
            this.c.a(i2, (List) list);
        }

        public void a(int i, String str, dji.thirdparty.c.f fVar, String str2, int i2, long j) {
        }
    }

    static {
        boolean z;
        if (d.class.desiredAssertionStatus()) {
            z = false;
        } else {
            z = true;
        }
        k = z;
    }

    private d(a aVar) throws IOException {
        int i = 2;
        this.n = new HashMap();
        this.s = System.nanoTime();
        this.c = 0;
        this.e = new n();
        this.f = new n();
        this.y = false;
        this.z = new LinkedHashSet();
        this.a = aVar.f;
        this.v = aVar.g;
        this.b = aVar.h;
        this.m = aVar.e;
        this.q = aVar.h ? 1 : 2;
        if (aVar.h && this.a == z.HTTP_2) {
            this.q += 2;
        }
        if (aVar.h) {
            i = 1;
        }
        this.w = i;
        if (aVar.h) {
            this.e.a(7, 0, 16777216);
        }
        this.o = aVar.b;
        if (this.a == z.HTTP_2) {
            this.g = new i();
            this.t = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), j.a(String.format("OkHttp %s Push Observer", new Object[]{this.o}), true));
            this.f.a(7, 0, 65535);
            this.f.a(5, 0, 16384);
        } else if (this.a == z.SPDY_3) {
            this.g = new o();
            this.t = null;
        } else {
            throw new AssertionError(this.a);
        }
        this.d = (long) this.f.l(65536);
        this.h = aVar.a;
        this.i = this.g.a(aVar.d, this.b);
        this.j = new c(this.g.a(aVar.c, this.b));
        new Thread(this.j).start();
    }

    public z a() {
        return this.a;
    }

    public synchronized int b() {
        return this.n.size();
    }

    synchronized e a(int i) {
        return (e) this.n.get(Integer.valueOf(i));
    }

    synchronized e b(int i) {
        e eVar;
        eVar = (e) this.n.remove(Integer.valueOf(i));
        if (eVar != null && this.n.isEmpty()) {
            a(true);
        }
        notifyAll();
        return eVar;
    }

    private synchronized void a(boolean z) {
        this.s = z ? System.nanoTime() : IPositioningSession.NotSet;
    }

    public synchronized boolean c() {
        return this.s != IPositioningSession.NotSet;
    }

    public synchronized int d() {
        return this.f.g(Integer.MAX_VALUE);
    }

    public synchronized long e() {
        return this.s;
    }

    public e a(int i, List<f> list, boolean z) throws IOException {
        if (this.b) {
            throw new IllegalStateException("Client cannot push requests.");
        } else if (this.a == z.HTTP_2) {
            return a(i, (List) list, z, false);
        } else {
            throw new IllegalStateException("protocol != HTTP_2");
        }
    }

    public e a(List<f> list, boolean z, boolean z2) throws IOException {
        return a(0, (List) list, z, z2);
    }

    private e a(int i, List<f> list, boolean z, boolean z2) throws IOException {
        e eVar;
        boolean z3 = true;
        boolean z4 = !z;
        if (z2) {
            z3 = false;
        }
        synchronized (this.i) {
            synchronized (this) {
                if (this.r) {
                    throw new IOException("shutdown");
                }
                int i2 = this.q;
                this.q += 2;
                eVar = new e(i2, this, z4, z3, list);
                if (eVar.b()) {
                    this.n.put(Integer.valueOf(i2), eVar);
                    a(false);
                }
            }
            if (i == 0) {
                this.i.a(z4, z3, i2, i, list);
            } else if (this.b) {
                throw new IllegalArgumentException("client streams shouldn't have associated stream IDs");
            } else {
                this.i.a(i, i2, (List) list);
            }
        }
        if (!z) {
            this.i.b();
        }
        return eVar;
    }

    void a(int i, boolean z, List<f> list) throws IOException {
        this.i.a(z, i, (List) list);
    }

    public void a(int i, boolean z, dji.thirdparty.c.c cVar, long j) throws IOException {
        if (j == 0) {
            this.i.a(z, i, cVar, 0);
            return;
        }
        while (j > 0) {
            int min;
            boolean z2;
            synchronized (this) {
                while (this.d <= 0) {
                    try {
                        if (this.n.containsKey(Integer.valueOf(i))) {
                            wait();
                        } else {
                            throw new IOException("stream closed");
                        }
                    } catch (InterruptedException e) {
                        throw new InterruptedIOException();
                    }
                }
                min = Math.min((int) Math.min(j, this.d), this.i.c());
                this.d -= (long) min;
            }
            j -= (long) min;
            c cVar2 = this.i;
            if (z && j == 0) {
                z2 = true;
            } else {
                z2 = false;
            }
            cVar2.a(z2, i, cVar, min);
        }
    }

    void a(long j) {
        this.d += j;
        if (j > 0) {
            notifyAll();
        }
    }

    void a(int i, a aVar) {
        final int i2 = i;
        final a aVar2 = aVar;
        l.submit(new f(this, "OkHttp %s stream %d", new Object[]{this.o, Integer.valueOf(i)}) {
            final /* synthetic */ d d;

            public void f() {
                try {
                    this.d.b(i2, aVar2);
                } catch (IOException e) {
                }
            }
        });
    }

    void b(int i, a aVar) throws IOException {
        this.i.a(i, aVar);
    }

    void a(int i, long j) {
        final int i2 = i;
        final long j2 = j;
        l.execute(new f(this, "OkHttp Window Update %s stream %d", new Object[]{this.o, Integer.valueOf(i)}) {
            final /* synthetic */ d d;

            public void f() {
                try {
                    this.d.i.a(i2, j2);
                } catch (IOException e) {
                }
            }
        });
    }

    public l f() throws IOException {
        int i;
        l lVar = new l();
        synchronized (this) {
            if (this.r) {
                throw new IOException("shutdown");
            }
            i = this.w;
            this.w += 2;
            if (this.u == null) {
                this.u = new HashMap();
            }
            this.u.put(Integer.valueOf(i), lVar);
        }
        b(false, i, 1330343787, lVar);
        return lVar;
    }

    private void a(boolean z, int i, int i2, l lVar) {
        final boolean z2 = z;
        final int i3 = i;
        final int i4 = i2;
        final l lVar2 = lVar;
        l.execute(new f(this, "OkHttp %s ping %08x%08x", new Object[]{this.o, Integer.valueOf(i), Integer.valueOf(i2)}) {
            final /* synthetic */ d f;

            public void f() {
                try {
                    this.f.b(z2, i3, i4, lVar2);
                } catch (IOException e) {
                }
            }
        });
    }

    private void b(boolean z, int i, int i2, l lVar) throws IOException {
        synchronized (this.i) {
            if (lVar != null) {
                lVar.a();
            }
            this.i.a(z, i, i2);
        }
    }

    private synchronized l c(int i) {
        return this.u != null ? (l) this.u.remove(Integer.valueOf(i)) : null;
    }

    public void g() throws IOException {
        this.i.b();
    }

    public void a(a aVar) throws IOException {
        synchronized (this.i) {
            synchronized (this) {
                if (this.r) {
                    return;
                }
                this.r = true;
                int i = this.p;
                this.i.a(i, aVar, j.a);
            }
        }
    }

    public void close() throws IOException {
        a(a.NO_ERROR, a.CANCEL);
    }

    private void a(a aVar, a aVar2) throws IOException {
        IOException iOException;
        IOException e;
        if (k || !Thread.holdsLock(this)) {
            e[] eVarArr;
            l[] lVarArr;
            try {
                a(aVar);
                iOException = null;
            } catch (IOException e2) {
                iOException = e2;
            }
            synchronized (this) {
                if (this.n.isEmpty()) {
                    eVarArr = null;
                } else {
                    e[] eVarArr2 = (e[]) this.n.values().toArray(new e[this.n.size()]);
                    this.n.clear();
                    a(false);
                    eVarArr = eVarArr2;
                }
                if (this.u != null) {
                    l[] lVarArr2 = (l[]) this.u.values().toArray(new l[this.u.size()]);
                    this.u = null;
                    lVarArr = lVarArr2;
                } else {
                    lVarArr = null;
                }
            }
            if (eVarArr != null) {
                e2 = iOException;
                for (e a : eVarArr) {
                    try {
                        a.a(aVar2);
                    } catch (IOException iOException2) {
                        if (e2 != null) {
                            e2 = iOException2;
                        }
                    }
                }
                iOException2 = e2;
            }
            if (lVarArr != null) {
                for (l c : lVarArr) {
                    c.c();
                }
            }
            try {
                this.i.close();
                e2 = iOException2;
            } catch (IOException e3) {
                e2 = e3;
                if (iOException2 != null) {
                    e2 = iOException2;
                }
            }
            try {
                this.h.close();
            } catch (IOException e4) {
                e2 = e4;
            }
            if (e2 != null) {
                throw e2;
            }
            return;
        }
        throw new AssertionError();
    }

    public void h() throws IOException {
        this.i.a();
        this.i.b(this.e);
        int l = this.e.l(65536);
        if (l != 65536) {
            this.i.a(0, (long) (l - 65536));
        }
    }

    public void a(n nVar) throws IOException {
        synchronized (this.i) {
            synchronized (this) {
                if (this.r) {
                    throw new IOException("shutdown");
                }
                this.e.a(nVar);
                this.i.b(nVar);
            }
        }
    }

    private boolean d(int i) {
        return this.a == z.HTTP_2 && i != 0 && (i & 1) == 0;
    }

    private void a(int i, List<f> list) {
        synchronized (this) {
            if (this.z.contains(Integer.valueOf(i))) {
                a(i, a.PROTOCOL_ERROR);
                return;
            }
            this.z.add(Integer.valueOf(i));
            final int i2 = i;
            final List<f> list2 = list;
            this.t.execute(new f(this, "OkHttp %s Push Request[%s]", new Object[]{this.o, Integer.valueOf(i)}) {
                final /* synthetic */ d d;

                public void f() {
                    if (this.d.v.a(i2, list2)) {
                        try {
                            this.d.i.a(i2, a.CANCEL);
                            synchronized (this.d) {
                                this.d.z.remove(Integer.valueOf(i2));
                            }
                        } catch (IOException e) {
                        }
                    }
                }
            });
        }
    }

    private void b(int i, List<f> list, boolean z) {
        final int i2 = i;
        final List<f> list2 = list;
        final boolean z2 = z;
        this.t.execute(new f(this, "OkHttp %s Push Headers[%s]", new Object[]{this.o, Integer.valueOf(i)}) {
            final /* synthetic */ d e;

            public void f() {
                boolean a = this.e.v.a(i2, list2, z2);
                if (a) {
                    try {
                        this.e.i.a(i2, a.CANCEL);
                    } catch (IOException e) {
                        return;
                    }
                }
                if (a || z2) {
                    synchronized (this.e) {
                        this.e.z.remove(Integer.valueOf(i2));
                    }
                }
            }
        });
    }

    private void a(int i, e eVar, int i2, boolean z) throws IOException {
        final dji.thirdparty.c.c cVar = new dji.thirdparty.c.c();
        eVar.a((long) i2);
        eVar.a(cVar, (long) i2);
        if (cVar.b() != ((long) i2)) {
            throw new IOException(cVar.b() + " != " + i2);
        }
        final int i3 = i;
        final int i4 = i2;
        final boolean z2 = z;
        this.t.execute(new f(this, "OkHttp %s Push Data[%s]", new Object[]{this.o, Integer.valueOf(i)}) {
            final /* synthetic */ d f;

            public void f() {
                try {
                    boolean a = this.f.v.a(i3, cVar, i4, z2);
                    if (a) {
                        this.f.i.a(i3, a.CANCEL);
                    }
                    if (a || z2) {
                        synchronized (this.f) {
                            this.f.z.remove(Integer.valueOf(i3));
                        }
                    }
                } catch (IOException e) {
                }
            }
        });
    }

    private void c(int i, a aVar) {
        final int i2 = i;
        final a aVar2 = aVar;
        this.t.execute(new f(this, "OkHttp %s Push Reset[%s]", new Object[]{this.o, Integer.valueOf(i)}) {
            final /* synthetic */ d d;

            public void f() {
                this.d.v.a(i2, aVar2);
                synchronized (this.d) {
                    this.d.z.remove(Integer.valueOf(i2));
                }
            }
        });
    }
}

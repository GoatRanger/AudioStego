package dji.thirdparty.b.a;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import dji.pilot.fpv.control.f;
import dji.thirdparty.c.d;
import dji.thirdparty.c.p;
import dji.thirdparty.c.v;
import dji.thirdparty.c.w;
import dji.thirdparty.c.x;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Flushable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.NoSuchElementException;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import lecho.lib.hellocharts.model.h;

public final class b implements Closeable, Flushable {
    private static final v F = new v() {
        public void a_(dji.thirdparty.c.c cVar, long j) throws IOException {
            cVar.h(j);
        }

        public void flush() throws IOException {
        }

        public x a() {
            return x.b;
        }

        public void close() throws IOException {
        }
    };
    static final String a = "journal";
    static final String b = "journal.tmp";
    static final String c = "journal.bkp";
    static final String d = "libcore.io.DiskLruCache";
    static final String e = "1";
    static final long f = -1;
    static final Pattern g = Pattern.compile("[a-z0-9_-]{1,120}");
    static final /* synthetic */ boolean h = (!b.class.desiredAssertionStatus());
    private static final String i = "CLEAN";
    private static final String j = "DIRTY";
    private static final String k = "REMOVE";
    private static final String l = "READ";
    private boolean A;
    private boolean B;
    private long C = 0;
    private final Executor D;
    private final Runnable E = new Runnable(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public void run() {
            int i = 1;
            synchronized (this.a) {
                if (this.a.z) {
                    i = 0;
                }
                if ((i | this.a.A) != 0) {
                    return;
                }
                try {
                    this.a.p();
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                } catch (IOException e2) {
                    this.a.B = true;
                }
                if (this.a.n()) {
                    this.a.m();
                    this.a.x = 0;
                }
            }
        }
    };
    private final dji.thirdparty.b.a.c.a m;
    private final File n;
    private final File o;
    private final File p;
    private final File q;
    private final int r;
    private long s;
    private final int t;
    private long u = 0;
    private d v;
    private final LinkedHashMap<String, b> w = new LinkedHashMap(0, h.l, true);
    private int x;
    private boolean y;
    private boolean z;

    public final class a {
        final /* synthetic */ b a;
        private final b b;
        private final boolean[] c;
        private boolean d;
        private boolean e;

        private a(b bVar, b bVar2) {
            this.a = bVar;
            this.b = bVar2;
            this.c = bVar2.f ? null : new boolean[bVar.t];
        }

        public w a(int i) throws IOException {
            w wVar = null;
            synchronized (this.a) {
                if (this.b.g != this) {
                    throw new IllegalStateException();
                } else if (this.b.f) {
                    try {
                        wVar = this.a.m.a(this.b.d[i]);
                    } catch (FileNotFoundException e) {
                    }
                }
            }
            return wVar;
        }

        public v b(int i) throws IOException {
            v anonymousClass1;
            synchronized (this.a) {
                if (this.b.g != this) {
                    throw new IllegalStateException();
                }
                if (!this.b.f) {
                    this.c[i] = true;
                }
                try {
                    anonymousClass1 = new c(this, this.a.m.b(this.b.e[i])) {
                        final /* synthetic */ a a;

                        protected void a(IOException iOException) {
                            synchronized (this.a.a) {
                                this.a.d = true;
                            }
                        }
                    };
                } catch (FileNotFoundException e) {
                    anonymousClass1 = b.F;
                }
            }
            return anonymousClass1;
        }

        public void a() throws IOException {
            synchronized (this.a) {
                if (this.d) {
                    this.a.a(this, false);
                    this.a.a(this.b);
                } else {
                    this.a.a(this, true);
                }
                this.e = true;
            }
        }

        public void b() throws IOException {
            synchronized (this.a) {
                this.a.a(this, false);
            }
        }

        public void c() {
            synchronized (this.a) {
                if (!this.e) {
                    try {
                        this.a.a(this, false);
                    } catch (IOException e) {
                    }
                }
            }
        }
    }

    private final class b {
        final /* synthetic */ b a;
        private final String b;
        private final long[] c;
        private final File[] d;
        private final File[] e;
        private boolean f;
        private a g;
        private long h;

        private b(b bVar, String str) {
            this.a = bVar;
            this.b = str;
            this.c = new long[bVar.t];
            this.d = new File[bVar.t];
            this.e = new File[bVar.t];
            StringBuilder append = new StringBuilder(str).append('.');
            int length = append.length();
            for (int i = 0; i < bVar.t; i++) {
                append.append(i);
                this.d[i] = new File(bVar.n, append.toString());
                append.append(f.b);
                this.e[i] = new File(bVar.n, append.toString());
                append.setLength(length);
            }
        }

        private void a(String[] strArr) throws IOException {
            if (strArr.length != this.a.t) {
                throw b(strArr);
            }
            int i = 0;
            while (i < strArr.length) {
                try {
                    this.c[i] = Long.parseLong(strArr[i]);
                    i++;
                } catch (NumberFormatException e) {
                    throw b(strArr);
                }
            }
        }

        void a(d dVar) throws IOException {
            for (long n : this.c) {
                dVar.m(32).n(n);
            }
        }

        private IOException b(String[] strArr) throws IOException {
            throw new IOException("unexpected journal line: " + Arrays.toString(strArr));
        }

        c a() {
            int i = 0;
            if (Thread.holdsLock(this.a)) {
                w[] wVarArr = new w[this.a.t];
                long[] jArr = (long[]) this.c.clone();
                int i2 = 0;
                while (i2 < this.a.t) {
                    try {
                        wVarArr[i2] = this.a.m.a(this.d[i2]);
                        i2++;
                    } catch (FileNotFoundException e) {
                        while (i < this.a.t && wVarArr[i] != null) {
                            j.a(wVarArr[i]);
                            i++;
                        }
                        return null;
                    }
                }
                return new c(this.b, this.h, wVarArr, jArr);
            }
            throw new AssertionError();
        }
    }

    public final class c implements Closeable {
        final /* synthetic */ b a;
        private final String b;
        private final long c;
        private final w[] d;
        private final long[] e;

        private c(b bVar, String str, long j, w[] wVarArr, long[] jArr) {
            this.a = bVar;
            this.b = str;
            this.c = j;
            this.d = wVarArr;
            this.e = jArr;
        }

        public String a() {
            return this.b;
        }

        public a b() throws IOException {
            return this.a.a(this.b, this.c);
        }

        public w a(int i) {
            return this.d[i];
        }

        public long b(int i) {
            return this.e[i];
        }

        public void close() {
            for (Closeable a : this.d) {
                j.a(a);
            }
        }
    }

    b(dji.thirdparty.b.a.c.a aVar, File file, int i, int i2, long j, Executor executor) {
        this.m = aVar;
        this.n = file;
        this.r = i;
        this.o = new File(file, a);
        this.p = new File(file, b);
        this.q = new File(file, c);
        this.t = i2;
        this.s = j;
        this.D = executor;
    }

    public synchronized void a() throws IOException {
        if (!h && !Thread.holdsLock(this)) {
            throw new AssertionError();
        } else if (!this.z) {
            if (this.m.e(this.q)) {
                if (this.m.e(this.o)) {
                    this.m.d(this.q);
                } else {
                    this.m.a(this.q, this.o);
                }
            }
            if (this.m.e(this.o)) {
                try {
                    j();
                    l();
                    this.z = true;
                } catch (IOException e) {
                    h.a().a("DiskLruCache " + this.n + " is corrupt: " + e.getMessage() + ", removing");
                    f();
                    this.A = false;
                }
            }
            m();
            this.z = true;
        }
    }

    public static b a(dji.thirdparty.b.a.c.a aVar, File file, int i, int i2, long j) {
        if (j <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        } else if (i2 <= 0) {
            throw new IllegalArgumentException("valueCount <= 0");
        } else {
            return new b(aVar, file, i, i2, j, new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), j.a("OkHttp DiskLruCache", true)));
        }
    }

    private void j() throws IOException {
        int i;
        Closeable a = p.a(this.m.a(this.o));
        try {
            String v = a.v();
            String v2 = a.v();
            String v3 = a.v();
            String v4 = a.v();
            String v5 = a.v();
            if (d.equals(v) && "1".equals(v2) && Integer.toString(this.r).equals(v3) && Integer.toString(this.t).equals(v4) && "".equals(v5)) {
                i = 0;
                while (true) {
                    d(a.v());
                    i++;
                }
            } else {
                throw new IOException("unexpected journal header: [" + v + ", " + v2 + ", " + v4 + ", " + v5 + dji.pilot.usercenter.protocol.d.H);
            }
        } catch (EOFException e) {
            this.x = i - this.w.size();
            if (a.g()) {
                this.v = k();
            } else {
                m();
            }
            j.a(a);
        } catch (Throwable th) {
            j.a(a);
        }
    }

    private d k() throws FileNotFoundException {
        return p.a(new c(this, this.m.c(this.o)) {
            static final /* synthetic */ boolean a = (!b.class.desiredAssertionStatus());
            final /* synthetic */ b b;

            protected void a(IOException iOException) {
                if (a || Thread.holdsLock(this.b)) {
                    this.b.y = true;
                    return;
                }
                throw new AssertionError();
            }
        });
    }

    private void d(String str) throws IOException {
        int indexOf = str.indexOf(32);
        if (indexOf == -1) {
            throw new IOException("unexpected journal line: " + str);
        }
        String str2;
        int i = indexOf + 1;
        int indexOf2 = str.indexOf(32, i);
        if (indexOf2 == -1) {
            String substring = str.substring(i);
            if (indexOf == k.length() && str.startsWith(k)) {
                this.w.remove(substring);
                return;
            }
            str2 = substring;
        } else {
            str2 = str.substring(i, indexOf2);
        }
        b bVar = (b) this.w.get(str2);
        if (bVar == null) {
            bVar = new b(str2);
            this.w.put(str2, bVar);
        }
        if (indexOf2 != -1 && indexOf == i.length() && str.startsWith(i)) {
            String[] split = str.substring(indexOf2 + 1).split(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            bVar.f = true;
            bVar.g = null;
            bVar.a(split);
        } else if (indexOf2 == -1 && indexOf == j.length() && str.startsWith(j)) {
            bVar.g = new a(bVar);
        } else if (indexOf2 != -1 || indexOf != l.length() || !str.startsWith(l)) {
            throw new IOException("unexpected journal line: " + str);
        }
    }

    private void l() throws IOException {
        this.m.d(this.p);
        Iterator it = this.w.values().iterator();
        while (it.hasNext()) {
            b bVar = (b) it.next();
            int i;
            if (bVar.g == null) {
                for (i = 0; i < this.t; i++) {
                    this.u += bVar.c[i];
                }
            } else {
                bVar.g = null;
                for (i = 0; i < this.t; i++) {
                    this.m.d(bVar.d[i]);
                    this.m.d(bVar.e[i]);
                }
                it.remove();
            }
        }
    }

    private synchronized void m() throws IOException {
        if (this.v != null) {
            this.v.close();
        }
        d a = p.a(this.m.b(this.p));
        try {
            a.b(d).m(10);
            a.b("1").m(10);
            a.n((long) this.r).m(10);
            a.n((long) this.t).m(10);
            a.m(10);
            for (b bVar : this.w.values()) {
                if (bVar.g != null) {
                    a.b(j).m(32);
                    a.b(bVar.b);
                    a.m(10);
                } else {
                    a.b(i).m(32);
                    a.b(bVar.b);
                    bVar.a(a);
                    a.m(10);
                }
            }
            a.close();
            if (this.m.e(this.o)) {
                this.m.a(this.o, this.q);
            }
            this.m.a(this.p, this.o);
            this.m.d(this.q);
            this.v = k();
            this.y = false;
        } catch (Throwable th) {
            a.close();
        }
    }

    public synchronized c a(String str) throws IOException {
        c cVar;
        a();
        o();
        e(str);
        b bVar = (b) this.w.get(str);
        if (bVar == null || !bVar.f) {
            cVar = null;
        } else {
            cVar = bVar.a();
            if (cVar == null) {
                cVar = null;
            } else {
                this.x++;
                this.v.b(l).m(32).b(str).m(10);
                if (n()) {
                    this.D.execute(this.E);
                }
            }
        }
        return cVar;
    }

    public a b(String str) throws IOException {
        return a(str, -1);
    }

    private synchronized a a(String str, long j) throws IOException {
        a aVar;
        a();
        o();
        e(str);
        b bVar = (b) this.w.get(str);
        if (j == -1 || (bVar != null && bVar.h == j)) {
            if (bVar != null) {
                if (bVar.g != null) {
                    aVar = null;
                }
            }
            if (this.B) {
                this.D.execute(this.E);
                aVar = null;
            } else {
                this.v.b(j).m(32).b(str).m(10);
                this.v.flush();
                if (this.y) {
                    aVar = null;
                } else {
                    b bVar2;
                    if (bVar == null) {
                        bVar = new b(str);
                        this.w.put(str, bVar);
                        bVar2 = bVar;
                    } else {
                        bVar2 = bVar;
                    }
                    aVar = new a(bVar2);
                    bVar2.g = aVar;
                }
            }
        } else {
            aVar = null;
        }
        return aVar;
    }

    public File b() {
        return this.n;
    }

    public synchronized long c() {
        return this.s;
    }

    public synchronized void a(long j) {
        this.s = j;
        if (this.z) {
            this.D.execute(this.E);
        }
    }

    public synchronized long d() throws IOException {
        a();
        return this.u;
    }

    private synchronized void a(a aVar, boolean z) throws IOException {
        int i = 0;
        synchronized (this) {
            b a = aVar.b;
            if (a.g != aVar) {
                throw new IllegalStateException();
            }
            if (z) {
                if (!a.f) {
                    int i2 = 0;
                    while (i2 < this.t) {
                        if (!aVar.c[i2]) {
                            aVar.b();
                            throw new IllegalStateException("Newly created entry didn't create value for index " + i2);
                        } else if (!this.m.e(a.e[i2])) {
                            aVar.b();
                            break;
                        } else {
                            i2++;
                        }
                    }
                }
            }
            while (i < this.t) {
                File file = a.e[i];
                if (!z) {
                    this.m.d(file);
                } else if (this.m.e(file)) {
                    File file2 = a.d[i];
                    this.m.a(file, file2);
                    long j = a.c[i];
                    long f = this.m.f(file2);
                    a.c[i] = f;
                    this.u = (this.u - j) + f;
                }
                i++;
            }
            this.x++;
            a.g = null;
            if ((a.f | z) != 0) {
                a.f = true;
                this.v.b(i).m(32);
                this.v.b(a.b);
                a.a(this.v);
                this.v.m(10);
                if (z) {
                    long j2 = this.C;
                    this.C = 1 + j2;
                    a.h = j2;
                }
            } else {
                this.w.remove(a.b);
                this.v.b(k).m(32);
                this.v.b(a.b);
                this.v.m(10);
            }
            this.v.flush();
            if (this.u > this.s || n()) {
                this.D.execute(this.E);
            }
        }
    }

    private boolean n() {
        return this.x >= 2000 && this.x >= this.w.size();
    }

    public synchronized boolean c(String str) throws IOException {
        boolean z;
        a();
        o();
        e(str);
        b bVar = (b) this.w.get(str);
        if (bVar == null) {
            z = false;
        } else {
            z = a(bVar);
            if (z && this.u <= this.s) {
                this.B = false;
            }
        }
        return z;
    }

    private boolean a(b bVar) throws IOException {
        if (bVar.g != null) {
            bVar.g.d = true;
        }
        for (int i = 0; i < this.t; i++) {
            this.m.d(bVar.d[i]);
            this.u -= bVar.c[i];
            bVar.c[i] = 0;
        }
        this.x++;
        this.v.b(k).m(32).b(bVar.b).m(10);
        this.w.remove(bVar.b);
        if (n()) {
            this.D.execute(this.E);
        }
        return true;
    }

    public synchronized boolean e() {
        return this.A;
    }

    private synchronized void o() {
        if (e()) {
            throw new IllegalStateException("cache is closed");
        }
    }

    public synchronized void flush() throws IOException {
        if (this.z) {
            o();
            p();
            this.v.flush();
        }
    }

    public synchronized void close() throws IOException {
        if (!this.z || this.A) {
            this.A = true;
        } else {
            for (b bVar : (b[]) this.w.values().toArray(new b[this.w.size()])) {
                if (bVar.g != null) {
                    bVar.g.b();
                }
            }
            p();
            this.v.close();
            this.v = null;
            this.A = true;
        }
    }

    private void p() throws IOException {
        while (this.u > this.s) {
            a((b) this.w.values().iterator().next());
        }
        this.B = false;
    }

    public void f() throws IOException {
        close();
        this.m.g(this.n);
    }

    public synchronized void g() throws IOException {
        synchronized (this) {
            a();
            for (b a : (b[]) this.w.values().toArray(new b[this.w.size()])) {
                a(a);
            }
            this.B = false;
        }
    }

    private void e(String str) {
        if (!g.matcher(str).matches()) {
            throw new IllegalArgumentException("keys must match regex [a-z0-9_-]{1,120}: \"" + str + "\"");
        }
    }

    public synchronized Iterator<c> h() throws IOException {
        a();
        return new Iterator<c>(this) {
            final Iterator<b> a = new ArrayList(this.d.w.values()).iterator();
            c b;
            c c;
            final /* synthetic */ b d;

            {
                this.d = r3;
            }

            public /* synthetic */ Object next() {
                return a();
            }

            public boolean hasNext() {
                if (this.b != null) {
                    return true;
                }
                synchronized (this.d) {
                    if (this.d.A) {
                        return false;
                    }
                    while (this.a.hasNext()) {
                        c a = ((b) this.a.next()).a();
                        if (a != null) {
                            this.b = a;
                            return true;
                        }
                    }
                    return false;
                }
            }

            public c a() {
                if (hasNext()) {
                    this.c = this.b;
                    this.b = null;
                    return this.c;
                }
                throw new NoSuchElementException();
            }

            public void remove() {
                if (this.c == null) {
                    throw new IllegalStateException("remove() before next()");
                }
                try {
                    this.d.c(this.c.b);
                } catch (IOException e) {
                } finally {
                    this.c = null;
                }
            }
        };
    }
}

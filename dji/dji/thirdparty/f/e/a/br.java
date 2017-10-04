package dji.thirdparty.f.e.a;

import com.here.odnp.posclient.pos.IPositioningSession;
import dji.thirdparty.f.c.g;
import dji.thirdparty.f.d$g;
import dji.thirdparty.f.e.d.a.i;
import dji.thirdparty.f.e.d.b.an;
import dji.thirdparty.f.e.d.b.p;
import dji.thirdparty.f.e.d.b.z;
import dji.thirdparty.f.e.d.j;
import dji.thirdparty.f.f;
import dji.thirdparty.f.k;
import dji.thirdparty.f.l;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;

public final class br<T> implements d$g<T, dji.thirdparty.f.d<? extends T>> {
    final boolean a;
    final int b;

    private static final class a {
        static final br<Object> a = new br(true, Integer.MAX_VALUE);

        private a() {
        }
    }

    private static final class b {
        static final br<Object> a = new br(false, Integer.MAX_VALUE);

        private b() {
        }
    }

    static final class c<T> extends k<T> {
        static final int f = (j.c / 4);
        final e<T> a;
        final long b;
        volatile boolean c;
        volatile j d;
        int e;

        public c(e<T> eVar, long j) {
            this.a = eVar;
            this.b = j;
        }

        public void c() {
            this.e = j.c;
            a((long) j.c);
        }

        public void a_(T t) {
            this.a.a(this, (Object) t);
        }

        public void a(Throwable th) {
            this.c = true;
            this.a.d().offer(th);
            this.a.f();
        }

        public void o_() {
            this.c = true;
            this.a.f();
        }

        public void b(long j) {
            int i = this.e - ((int) j);
            if (i > f) {
                this.e = i;
                return;
            }
            this.e = j.c;
            i = j.c - i;
            if (i > 0) {
                a((long) i);
            }
        }
    }

    static final class d<T> extends AtomicLong implements f {
        private static final long b = -1214379189873595503L;
        final e<T> a;

        public d(e<T> eVar) {
            this.a = eVar;
        }

        public void a(long j) {
            if (j > 0) {
                if (get() != IPositioningSession.NotSet) {
                    a.a((AtomicLong) this, j);
                    this.a.f();
                }
            } else if (j < 0) {
                throw new IllegalArgumentException("n >= 0 required");
            }
        }

        public long a(int i) {
            return addAndGet((long) (-i));
        }
    }

    static final class e<T> extends k<dji.thirdparty.f.d<? extends T>> {
        static final c<?>[] q = new c[0];
        final k<? super T> a;
        final boolean b;
        final int c;
        d<T> d;
        volatile Queue<Object> e;
        volatile dji.thirdparty.f.m.b f;
        volatile ConcurrentLinkedQueue<Throwable> g;
        final r<T> h = r.a();
        volatile boolean i;
        boolean j;
        boolean k;
        final Object l = new Object();
        volatile c<?>[] m = q;
        long n;
        long o;
        int p;
        final int r;
        int s;

        public /* synthetic */ void a_(Object obj) {
            a((dji.thirdparty.f.d) obj);
        }

        public e(k<? super T> kVar, boolean z, int i) {
            this.a = kVar;
            this.b = z;
            this.c = i;
            if (i == Integer.MAX_VALUE) {
                this.r = Integer.MAX_VALUE;
                a((long) IPositioningSession.NotSet);
                return;
            }
            this.r = Math.max(1, i >> 1);
            a((long) i);
        }

        Queue<Throwable> d() {
            Queue<Throwable> queue = this.g;
            if (queue == null) {
                synchronized (this) {
                    queue = this.g;
                    if (queue == null) {
                        queue = new ConcurrentLinkedQueue();
                        this.g = queue;
                    }
                }
            }
            return queue;
        }

        dji.thirdparty.f.m.b e() {
            dji.thirdparty.f.m.b bVar = this.f;
            if (bVar == null) {
                Object obj;
                synchronized (this) {
                    dji.thirdparty.f.m.b bVar2 = this.f;
                    if (bVar2 == null) {
                        bVar2 = new dji.thirdparty.f.m.b();
                        this.f = bVar2;
                        bVar = bVar2;
                        obj = 1;
                    } else {
                        bVar = bVar2;
                        obj = null;
                    }
                }
                if (obj != null) {
                    a((l) bVar);
                }
            }
            return bVar;
        }

        public void a(dji.thirdparty.f.d<? extends T> dVar) {
            if (dVar != null) {
                if (dVar instanceof dji.thirdparty.f.e.d.l) {
                    b(((dji.thirdparty.f.e.d.l) dVar).a());
                    return;
                }
                long j = this.n;
                this.n = 1 + j;
                c cVar = new c(this, j);
                a(cVar);
                dVar.a(cVar);
                f();
            }
        }

        private void i() {
            Collection arrayList = new ArrayList(this.g);
            if (arrayList.size() == 1) {
                this.a.a((Throwable) arrayList.get(0));
            } else {
                this.a.a(new dji.thirdparty.f.c.a(arrayList));
            }
        }

        public void a(Throwable th) {
            d().offer(th);
            this.i = true;
            f();
        }

        public void o_() {
            this.i = true;
            f();
        }

        void a(c<T> cVar) {
            e().a((l) cVar);
            synchronized (this.l) {
                Object obj = this.m;
                int length = obj.length;
                Object obj2 = new c[(length + 1)];
                System.arraycopy(obj, 0, obj2, 0, length);
                obj2[length] = cVar;
                this.m = obj2;
            }
        }

        void b(c<T> cVar) {
            int i = 0;
            j jVar = cVar.d;
            if (jVar != null) {
                jVar.e();
            }
            this.f.b(cVar);
            synchronized (this.l) {
                Object obj = this.m;
                int length = obj.length;
                while (i < length) {
                    if (cVar.equals(obj[i])) {
                        break;
                    }
                    i++;
                }
                i = -1;
                if (i < 0) {
                } else if (length == 1) {
                    this.m = q;
                } else {
                    Object obj2 = new c[(length - 1)];
                    System.arraycopy(obj, 0, obj2, 0, i);
                    System.arraycopy(obj, i + 1, obj2, i, (length - i) - 1);
                    this.m = obj2;
                }
            }
        }

        void a(c<T> cVar, T t) {
            Object obj = null;
            long j = this.d.get();
            if (j != 0) {
                synchronized (this) {
                    j = this.d.get();
                    if (!(this.j || j == 0)) {
                        this.j = true;
                        obj = 1;
                    }
                }
            }
            if (obj != null) {
                a(cVar, t, j);
            } else {
                b(cVar, t);
            }
        }

        protected void b(c<T> cVar, T t) {
            j jVar = cVar.d;
            if (jVar == null) {
                jVar = j.c();
                cVar.a((l) jVar);
                cVar.d = jVar;
            }
            try {
                jVar.a(this.h.a((Object) t));
                f();
            } catch (Throwable e) {
                cVar.n_();
                cVar.a(e);
            } catch (Throwable e2) {
                if (!cVar.b()) {
                    cVar.n_();
                    cVar.a(e2);
                }
            }
        }

        protected void a(c<T> cVar, T t, long j) {
            Throwable th;
            Object obj = null;
            try {
                this.a.a_(t);
            } catch (Throwable th2) {
                th = th2;
                obj = 1;
            }
            if (j != IPositioningSession.NotSet) {
                this.d.a(1);
            }
            cVar.b(1);
            synchronized (this) {
                if (this.k) {
                    this.k = false;
                    g();
                    return;
                }
                this.j = false;
                return;
            }
            if (obj == null) {
                synchronized (this) {
                    this.j = false;
                }
            }
            throw th;
        }

        public void b(long j) {
            a(j);
        }

        void b(T t) {
            Object obj = null;
            long j = this.d.get();
            if (j != 0) {
                synchronized (this) {
                    j = this.d.get();
                    if (!(this.j || j == 0)) {
                        this.j = true;
                        obj = 1;
                    }
                }
            }
            if (obj != null) {
                a((Object) t, j);
            } else {
                c(t);
            }
        }

        protected void c(T t) {
            Queue queue = this.e;
            if (queue == null) {
                int i = this.c;
                if (i == Integer.MAX_VALUE) {
                    queue = new i(j.c);
                } else if (!p.b(i)) {
                    queue = new dji.thirdparty.f.e.d.a.f(i);
                } else if (an.a()) {
                    queue = new z(i);
                } else {
                    queue = new dji.thirdparty.f.e.d.a.e(i);
                }
                this.e = queue;
            }
            if (queue.offer(t)) {
                f();
                return;
            }
            n_();
            a(g.a(new dji.thirdparty.f.c.c(), t));
        }

        protected void a(T t, long j) {
            Throwable th;
            Object obj = null;
            try {
                this.a.a_(t);
            } catch (Throwable th2) {
                th = th2;
                obj = 1;
            }
            if (j != IPositioningSession.NotSet) {
                this.d.a(1);
            }
            int i = this.s + 1;
            if (i == this.r) {
                this.s = 0;
                b((long) i);
            } else {
                this.s = i;
            }
            synchronized (this) {
                if (this.k) {
                    this.k = false;
                    g();
                    return;
                }
                this.j = false;
                return;
            }
            if (obj == null) {
                synchronized (this) {
                    this.j = false;
                }
            }
            throw th;
        }

        void f() {
            synchronized (this) {
                if (this.j) {
                    this.k = true;
                    return;
                }
                this.j = true;
                g();
            }
        }

        void g() {
            Object obj = null;
            k kVar = this.a;
            while (!h()) {
                Object obj2;
                long j;
                int i;
                boolean z;
                Queue queue;
                c[] cVarArr;
                int length;
                int i2;
                Queue queue2 = this.e;
                long j2 = this.d.get();
                if (j2 == IPositioningSession.NotSet) {
                    obj2 = 1;
                } else {
                    obj2 = null;
                }
                int i3 = 0;
                Object obj3;
                int i4;
                int i5;
                int i6;
                c cVar;
                Object obj4;
                int i7;
                j jVar;
                int i8;
                if (queue2 != null) {
                    do {
                        i4 = 0;
                        obj3 = null;
                        while (j2 > 0) {
                            obj3 = queue2.poll();
                            if (!h()) {
                                if (obj3 == null) {
                                    break;
                                }
                                try {
                                    try {
                                        kVar.a_(this.h.g(obj3));
                                    } catch (Throwable th) {
                                        if (this.b) {
                                            d().offer(th);
                                        } else {
                                            dji.thirdparty.f.c.b.b(th);
                                            n_();
                                            kVar.a(th);
                                            return;
                                        }
                                    }
                                    j2--;
                                    i4++;
                                    i3++;
                                } catch (Throwable th2) {
                                    if (obj == null) {
                                        synchronized (this) {
                                            this.j = false;
                                        }
                                    }
                                }
                            } else {
                                return;
                            }
                        }
                        if (i4 > 0) {
                            if (obj2 != null) {
                                j2 = IPositioningSession.NotSet;
                            } else {
                                j2 = this.d.a(i4);
                            }
                        }
                        if (j2 != 0) {
                        }
                    } while (obj3 != null);
                    j = j2;
                    i = i3;
                    z = this.i;
                    queue = this.e;
                    cVarArr = this.m;
                    length = cVarArr.length;
                    if (z || !((queue == null || queue.isEmpty()) && length == 0)) {
                        if (length <= 0) {
                            long j3 = this.o;
                            i3 = this.p;
                            if (length <= i3 || cVarArr[i3].b != j3) {
                                if (length <= i3) {
                                    i3 = 0;
                                }
                                for (i2 = 0; i2 < length && cVarArr[i3].b != j3; i2++) {
                                    i3++;
                                    if (i3 == length) {
                                        i3 = 0;
                                    }
                                }
                                this.p = i3;
                                this.o = cVarArr[i3].b;
                            }
                            i5 = i3;
                            i4 = i;
                            j2 = j;
                            i6 = 0;
                            obj3 = null;
                            while (i6 < length) {
                                if (!h()) {
                                    cVar = cVarArr[i5];
                                    obj4 = null;
                                    do {
                                        i7 = 0;
                                        while (j2 > 0) {
                                            if (!h()) {
                                                jVar = cVar.d;
                                                if (jVar != null) {
                                                    obj4 = jVar.k();
                                                    if (obj4 != null) {
                                                        break;
                                                    }
                                                    try {
                                                        kVar.a_(this.h.g(obj4));
                                                        i7++;
                                                        j2--;
                                                    } catch (Throwable th3) {
                                                        obj = 1;
                                                        dji.thirdparty.f.c.b.b(th3);
                                                        kVar.a(th3);
                                                        return;
                                                    } finally {
                                                        n_();
                                                    }
                                                } else {
                                                    break;
                                                }
                                            }
                                            return;
                                        }
                                        if (i7 > 0) {
                                            if (obj2 != null) {
                                                j2 = this.d.a(i7);
                                            } else {
                                                j2 = IPositioningSession.NotSet;
                                            }
                                            cVar.b((long) i7);
                                        }
                                        if (j2 == 0) {
                                            break;
                                        }
                                    } while (obj4 != null);
                                    z = cVar.c;
                                    j jVar2 = cVar.d;
                                    if (z && (jVar2 == null || jVar2.j())) {
                                        b(cVar);
                                        if (!h()) {
                                            i4++;
                                            obj3 = 1;
                                        } else {
                                            return;
                                        }
                                    }
                                    if (j2 == 0) {
                                        obj4 = obj3;
                                        i8 = i4;
                                        break;
                                    }
                                    i3 = i5 + 1;
                                    if (i3 == length) {
                                        i3 = 0;
                                    }
                                    i6++;
                                    i5 = i3;
                                } else {
                                    return;
                                }
                            }
                            obj4 = obj3;
                            i8 = i4;
                            this.p = i5;
                            this.o = cVarArr[i5].b;
                        } else {
                            obj4 = null;
                            i8 = i;
                        }
                        if (i8 > 0) {
                            a((long) i8);
                        }
                        if (obj4 == null) {
                            synchronized (this) {
                                if (this.k) {
                                    this.j = false;
                                    return;
                                }
                                this.k = false;
                            }
                        }
                    } else {
                        Queue queue3 = this.g;
                        if (queue3 == null || queue3.isEmpty()) {
                            kVar.o_();
                            return;
                        } else {
                            i();
                            return;
                        }
                    }
                }
                j = j2;
                i = i3;
                z = this.i;
                queue = this.e;
                cVarArr = this.m;
                length = cVarArr.length;
                if (z) {
                }
                if (length <= 0) {
                    obj4 = null;
                    i8 = i;
                } else {
                    long j32 = this.o;
                    i3 = this.p;
                    if (length <= i3) {
                        i3 = 0;
                    }
                    for (i2 = 0; i2 < length; i2++) {
                        i3++;
                        if (i3 == length) {
                            i3 = 0;
                        }
                    }
                    this.p = i3;
                    this.o = cVarArr[i3].b;
                    i5 = i3;
                    i4 = i;
                    j2 = j;
                    i6 = 0;
                    obj3 = null;
                    while (i6 < length) {
                        if (!h()) {
                            cVar = cVarArr[i5];
                            obj4 = null;
                            do {
                                i7 = 0;
                                while (j2 > 0) {
                                    if (!h()) {
                                        jVar = cVar.d;
                                        if (jVar != null) {
                                            break;
                                        }
                                        obj4 = jVar.k();
                                        if (obj4 != null) {
                                            break;
                                        }
                                        kVar.a_(this.h.g(obj4));
                                        i7++;
                                        j2--;
                                    } else {
                                        return;
                                    }
                                }
                                if (i7 > 0) {
                                    if (obj2 != null) {
                                        j2 = IPositioningSession.NotSet;
                                    } else {
                                        j2 = this.d.a(i7);
                                    }
                                    cVar.b((long) i7);
                                }
                                if (j2 == 0) {
                                    break;
                                }
                                break;
                            } while (obj4 != null);
                            z = cVar.c;
                            j jVar22 = cVar.d;
                            b(cVar);
                            if (!h()) {
                                i4++;
                                obj3 = 1;
                                if (j2 == 0) {
                                    obj4 = obj3;
                                    i8 = i4;
                                    break;
                                }
                                i3 = i5 + 1;
                                if (i3 == length) {
                                    i3 = 0;
                                }
                                i6++;
                                i5 = i3;
                            } else {
                                return;
                            }
                        }
                        return;
                    }
                    obj4 = obj3;
                    i8 = i4;
                    this.p = i5;
                    this.o = cVarArr[i5].b;
                }
                if (i8 > 0) {
                    a((long) i8);
                }
                if (obj4 == null) {
                    synchronized (this) {
                        if (this.k) {
                            this.k = false;
                        } else {
                            this.j = false;
                            return;
                        }
                    }
                }
            }
        }

        boolean h() {
            if (this.a.b()) {
                return true;
            }
            Queue queue = this.g;
            if (this.b || queue == null || queue.isEmpty()) {
                return false;
            }
            try {
                i();
                return true;
            } finally {
                n_();
            }
        }
    }

    public static <T> br<T> a(boolean z) {
        if (z) {
            return a.a;
        }
        return b.a;
    }

    public static <T> br<T> a(boolean z, int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("maxConcurrent > 0 required but it was " + i);
        } else if (i == Integer.MAX_VALUE) {
            return a(z);
        } else {
            return new br(z, i);
        }
    }

    br(boolean z, int i) {
        this.a = z;
        this.b = i;
    }

    public k<dji.thirdparty.f.d<? extends T>> a(k<? super T> kVar) {
        l eVar = new e(kVar, this.a, this.b);
        f dVar = new d(eVar);
        eVar.d = dVar;
        kVar.a(eVar);
        kVar.a(dVar);
        return eVar;
    }
}

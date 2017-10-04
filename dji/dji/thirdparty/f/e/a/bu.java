package dji.thirdparty.f.e.a;

import com.here.odnp.posclient.pos.IPositioningSession;
import dji.thirdparty.f.a.d;
import dji.thirdparty.f.d$g;
import dji.thirdparty.f.f;
import dji.thirdparty.f.k;
import dji.thirdparty.f.l;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

public class bu<T> implements d$g<T, T> {
    private final Long a;
    private final dji.thirdparty.f.d.b b;
    private final d c;

    private static final class a<T> extends k<T> implements dji.thirdparty.f.e.d.a.a {
        private final ConcurrentLinkedQueue<Object> a = new ConcurrentLinkedQueue();
        private final Long b;
        private final AtomicLong c;
        private final k<? super T> d;
        private final AtomicBoolean e = new AtomicBoolean(false);
        private final dji.thirdparty.f.e.d.a f;
        private final r<T> g = r.a();
        private final dji.thirdparty.f.d.b h;
        private final d i;

        public a(k<? super T> kVar, Long l, dji.thirdparty.f.d.b bVar, d dVar) {
            this.d = kVar;
            this.b = l;
            this.c = l != null ? new AtomicLong(l.longValue()) : null;
            this.h = bVar;
            this.f = new dji.thirdparty.f.e.d.a(this);
            this.i = dVar;
        }

        public void c() {
            a((long) IPositioningSession.NotSet);
        }

        public void o_() {
            if (!this.e.get()) {
                this.f.c();
            }
        }

        public void a(Throwable th) {
            if (!this.e.get()) {
                this.f.b(th);
            }
        }

        public void a_(T t) {
            if (g()) {
                this.a.offer(this.g.a((Object) t));
                this.f.d();
            }
        }

        public boolean b(Object obj) {
            return this.g.a(this.d, obj);
        }

        public void b(Throwable th) {
            if (th != null) {
                this.d.a(th);
            } else {
                this.d.o_();
            }
        }

        public Object d() {
            return this.a.peek();
        }

        public Object e() {
            Object poll = this.a.poll();
            if (!(this.c == null || poll == null)) {
                this.c.incrementAndGet();
            }
            return poll;
        }

        private boolean g() {
            if (this.c == null) {
                return true;
            }
            long j;
            do {
                j = this.c.get();
                if (j <= 0) {
                    boolean z;
                    try {
                        z = this.i.a() && e() != null;
                    } catch (Throwable e) {
                        if (this.e.compareAndSet(false, true)) {
                            n_();
                            this.d.a(e);
                        }
                        z = false;
                    }
                    if (this.h != null) {
                        try {
                            this.h.a();
                        } catch (Throwable e2) {
                            dji.thirdparty.f.c.b.b(e2);
                            this.f.b(e2);
                            return false;
                        }
                    }
                    if (!z) {
                        return false;
                    }
                }
            } while (!this.c.compareAndSet(j, j - 1));
            return true;
        }

        protected f f() {
            return this.f;
        }
    }

    private static class b {
        static final bu<?> a = new bu();

        private b() {
        }
    }

    public static <T> bu<T> a() {
        return b.a;
    }

    bu() {
        this.a = null;
        this.b = null;
        this.c = dji.thirdparty.f.a.a;
    }

    public bu(long j) {
        this(j, null, dji.thirdparty.f.a.a);
    }

    public bu(long j, dji.thirdparty.f.d.b bVar) {
        this(j, bVar, dji.thirdparty.f.a.a);
    }

    public bu(long j, dji.thirdparty.f.d.b bVar, d dVar) {
        if (j <= 0) {
            throw new IllegalArgumentException("Buffer capacity must be > 0");
        } else if (dVar == null) {
            throw new NullPointerException("The BackpressureOverflow strategy must not be null");
        } else {
            this.a = Long.valueOf(j);
            this.b = bVar;
            this.c = dVar;
        }
    }

    public k<? super T> a(k<? super T> kVar) {
        l aVar = new a(kVar, this.a, this.b, this.c);
        kVar.a(aVar);
        kVar.a(aVar.f());
        return aVar;
    }
}

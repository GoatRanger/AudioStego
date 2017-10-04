package dji.thirdparty.f.e.a;

import com.here.odnp.posclient.pos.IPositioningSession;
import dji.thirdparty.f.b;
import dji.thirdparty.f.b$a;
import dji.thirdparty.f.b$c;
import dji.thirdparty.f.d;
import dji.thirdparty.f.k;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public final class l implements b$a {
    final d<? extends b> a;
    final int b;
    final boolean c;

    static final class a extends k<b> {
        static final AtomicReferenceFieldUpdater<a, Queue> g = AtomicReferenceFieldUpdater.newUpdater(a.class, Queue.class, "f");
        static final AtomicIntegerFieldUpdater<a> i = AtomicIntegerFieldUpdater.newUpdater(a.class, "h");
        final b$c a;
        final dji.thirdparty.f.m.b b = new dji.thirdparty.f.m.b();
        final int c;
        final boolean d;
        volatile boolean e;
        volatile Queue<Throwable> f;
        volatile int h;
        final AtomicInteger j = new AtomicInteger(1);

        public /* synthetic */ void a_(Object obj) {
            a((b) obj);
        }

        public a(b$c dji_thirdparty_f_b_c, int i, boolean z) {
            this.a = dji_thirdparty_f_b_c;
            this.c = i;
            this.d = z;
            if (i == Integer.MAX_VALUE) {
                a((long) IPositioningSession.NotSet);
            } else {
                a((long) i);
            }
        }

        Queue<Throwable> d() {
            Queue<Throwable> queue = this.f;
            if (queue != null) {
                return queue;
            }
            queue = new ConcurrentLinkedQueue();
            return !g.compareAndSet(this, null, queue) ? this.f : queue;
        }

        public void a(b bVar) {
            if (!this.e) {
                this.j.getAndIncrement();
                bVar.a(new b$c(this) {
                    dji.thirdparty.f.l a;
                    boolean b;
                    final /* synthetic */ a c;

                    {
                        this.c = r1;
                    }

                    public void a(dji.thirdparty.f.l lVar) {
                        this.a = lVar;
                        this.c.b.a(lVar);
                    }

                    public void a(Throwable th) {
                        if (this.b) {
                            dji.thirdparty.f.i.d.getInstance().b().a(th);
                            return;
                        }
                        this.b = true;
                        this.c.b.b(this.a);
                        this.c.d().offer(th);
                        this.c.e();
                        if (this.c.d && !this.c.e) {
                            this.c.a(1);
                        }
                    }

                    public void b() {
                        if (!this.b) {
                            this.b = true;
                            this.c.b.b(this.a);
                            this.c.e();
                            if (!this.c.e) {
                                this.c.a(1);
                            }
                        }
                    }
                });
            }
        }

        public void a(Throwable th) {
            if (this.e) {
                dji.thirdparty.f.i.d.getInstance().b().a(th);
                return;
            }
            d().offer(th);
            this.e = true;
            e();
        }

        public void o_() {
            if (!this.e) {
                this.e = true;
                e();
            }
        }

        void e() {
            Queue queue;
            Throwable a;
            if (this.j.decrementAndGet() == 0) {
                queue = this.f;
                if (queue == null || queue.isEmpty()) {
                    this.a.b();
                    return;
                }
                a = l.a(queue);
                if (i.compareAndSet(this, 0, 1)) {
                    this.a.a(a);
                } else {
                    dji.thirdparty.f.i.d.getInstance().b().a(a);
                }
            } else if (!this.d) {
                queue = this.f;
                if (queue != null && !queue.isEmpty()) {
                    a = l.a(queue);
                    if (i.compareAndSet(this, 0, 1)) {
                        this.a.a(a);
                    } else {
                        dji.thirdparty.f.i.d.getInstance().b().a(a);
                    }
                }
            }
        }
    }

    public l(d<? extends b> dVar, int i, boolean z) {
        this.a = dVar;
        this.b = i;
        this.c = z;
    }

    public void a(b$c dji_thirdparty_f_b_c) {
        dji.thirdparty.f.l aVar = new a(dji_thirdparty_f_b_c, this.b, this.c);
        dji_thirdparty_f_b_c.a(aVar);
        this.a.b(aVar);
    }

    public static Throwable a(Queue<Throwable> queue) {
        Collection arrayList = new ArrayList();
        while (true) {
            Throwable th = (Throwable) queue.poll();
            if (th == null) {
                break;
            }
            arrayList.add(th);
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        if (arrayList.size() == 1) {
            return (Throwable) arrayList.get(0);
        }
        return new dji.thirdparty.f.c.a(arrayList);
    }
}

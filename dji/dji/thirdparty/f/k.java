package dji.thirdparty.f;

import com.here.odnp.posclient.pos.IPositioningSession;
import dji.thirdparty.f.e.d.n;

public abstract class k<T> implements e<T>, l {
    private static final Long a = Long.valueOf(Long.MIN_VALUE);
    private final n b;
    private final k<?> c;
    private f d;
    private long e;

    protected k() {
        this(null, false);
    }

    protected k(k<?> kVar) {
        this(kVar, true);
    }

    protected k(k<?> kVar, boolean z) {
        this.e = a.longValue();
        this.c = kVar;
        n nVar = (!z || kVar == null) ? new n() : kVar.b;
        this.b = nVar;
    }

    public final void a(l lVar) {
        this.b.a(lVar);
    }

    public final void n_() {
        this.b.n_();
    }

    public final boolean b() {
        return this.b.b();
    }

    public void c() {
    }

    protected final void a(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("number requested cannot be negative: " + j);
        }
        synchronized (this) {
            if (this.d != null) {
                f fVar = this.d;
                fVar.a(j);
                return;
            }
            b(j);
        }
    }

    private void b(long j) {
        if (this.e == a.longValue()) {
            this.e = j;
            return;
        }
        long j2 = this.e + j;
        if (j2 < 0) {
            this.e = IPositioningSession.NotSet;
        } else {
            this.e = j2;
        }
    }

    public void a(f fVar) {
        Object obj = null;
        synchronized (this) {
            long j = this.e;
            this.d = fVar;
            if (this.c != null && j == a.longValue()) {
                obj = 1;
            }
        }
        if (obj != null) {
            this.c.a(this.d);
        } else if (j == a.longValue()) {
            this.d.a(IPositioningSession.NotSet);
        } else {
            this.d.a(j);
        }
    }
}

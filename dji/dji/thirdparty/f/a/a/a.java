package dji.thirdparty.f.a.a;

import java.util.concurrent.atomic.AtomicReference;

public final class a {
    private static final a a = new a();
    private final AtomicReference<b> b = new AtomicReference();

    public static a getInstance() {
        return a;
    }

    a() {
    }

    @dji.thirdparty.f.b.a
    public void a() {
        this.b.set(null);
    }

    public b b() {
        if (this.b.get() == null) {
            this.b.compareAndSet(null, b.a());
        }
        return (b) this.b.get();
    }

    public void a(b bVar) {
        if (!this.b.compareAndSet(null, bVar)) {
            throw new IllegalStateException("Another strategy was already registered: " + this.b.get());
        }
    }
}

package dji.thirdparty.f.j;

import dji.thirdparty.f.d.b;
import dji.thirdparty.f.g.a;

class f implements b {
    private final b a;
    private final a b;
    private final long c;

    public f(b bVar, a aVar, long j) {
        this.a = bVar;
        this.b = aVar;
        this.c = j;
    }

    public void a() {
        if (!this.b.b()) {
            if (this.c > this.b.a()) {
                long a = this.c - this.b.a();
                if (a > 0) {
                    try {
                        Thread.sleep(a);
                    } catch (Throwable e) {
                        Thread.currentThread().interrupt();
                        throw new RuntimeException(e);
                    }
                }
            }
            if (!this.b.b()) {
                this.a.a();
            }
        }
    }
}

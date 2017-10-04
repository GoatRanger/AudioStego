package dji.thirdparty.b.a;

import dji.thirdparty.b.af;
import java.util.LinkedHashSet;
import java.util.Set;

public final class i {
    private final Set<af> a = new LinkedHashSet();

    public synchronized void a(af afVar) {
        this.a.add(afVar);
    }

    public synchronized void b(af afVar) {
        this.a.remove(afVar);
    }

    public synchronized boolean c(af afVar) {
        return this.a.contains(afVar);
    }

    public synchronized int a() {
        return this.a.size();
    }
}

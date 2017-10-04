package dji.sdksharedlib.hardware.abstractions.a.a;

import dji.thirdparty.f.d.n;

class e$6 implements n<Boolean> {
    final /* synthetic */ e a;

    e$6(e eVar) {
        this.a = eVar;
    }

    public /* synthetic */ Object call() {
        return a();
    }

    public synchronized Boolean a() {
        Boolean valueOf;
        boolean z = true;
        synchronized (this) {
            boolean z2;
            e eVar = this.a;
            if (this.a.b.isEmpty()) {
                z2 = false;
            } else {
                z2 = true;
            }
            eVar.c = z2;
            if (this.a.b.isEmpty()) {
                z = false;
            }
            valueOf = Boolean.valueOf(z);
        }
        return valueOf;
    }
}

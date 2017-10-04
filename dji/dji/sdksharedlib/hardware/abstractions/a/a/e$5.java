package dji.sdksharedlib.hardware.abstractions.a.a;

import dji.thirdparty.f.d.n;

class e$5 implements n<Boolean> {
    final /* synthetic */ e a;

    e$5(e eVar) {
        this.a = eVar;
    }

    public /* synthetic */ Object call() {
        return a();
    }

    public synchronized Boolean a() {
        this.a.c = true;
        return Boolean.valueOf(true);
    }
}

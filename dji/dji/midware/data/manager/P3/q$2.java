package dji.midware.data.manager.P3;

import dji.midware.data.a.a.a;

class q$2 implements Runnable {
    final /* synthetic */ a a;
    final /* synthetic */ Class b;
    final /* synthetic */ q c;

    q$2(q qVar, a aVar, Class cls) {
        this.c = qVar;
        this.a = aVar;
        this.b = cls;
    }

    public void run() {
        for (q$a a : q.a(this.c)) {
            a.a(this.a, this.b.getSimpleName());
        }
    }
}

package dji.midware.data.manager.P3;

import dji.midware.data.a.a.a;
import dji.midware.data.a.a.b;
import dji.midware.data.a.a.c;

class q$1 implements Runnable {
    final /* synthetic */ a a;
    final /* synthetic */ String b;
    final /* synthetic */ q c;

    q$1(q qVar, a aVar, String str) {
        this.c = qVar;
        this.a = aVar;
        this.b = str;
    }

    public void run() {
        for (q$a dji_midware_data_manager_P3_q_a : q.a(this.c)) {
            if (this.a instanceof c) {
                dji_midware_data_manager_P3_q_a.a((c) this.a, this.b);
            } else if (this.a instanceof b) {
                dji_midware_data_manager_P3_q_a.a((b) this.a, this.b);
            }
        }
    }
}

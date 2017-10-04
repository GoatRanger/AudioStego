package dji.midware.data.manager.P3;

import com.dji.frame.c.f;
import dji.midware.R;

class d$1 implements Runnable {
    final /* synthetic */ d a;

    d$1(d dVar) {
        this.a = dVar;
    }

    public void run() {
        d.a(this.a, f.a(d.a(), R.raw.flyc_param_infos));
    }
}

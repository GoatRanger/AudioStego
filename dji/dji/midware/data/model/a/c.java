package dji.midware.data.model.a;

import dji.midware.data.manager.P3.n;
import dji.midware.data.manager.P3.o;

public abstract class c extends n {
    public c(boolean z) {
        super(z);
    }

    public void onEventBackgroundThread(o oVar) {
        if (oVar == o.ConnectLose) {
            clear();
        }
    }
}

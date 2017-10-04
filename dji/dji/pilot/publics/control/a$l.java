package dji.pilot.publics.control;

import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.model.P3.DataCommonGetVersion;
import java.util.Iterator;

class a$l extends Thread {
    final /* synthetic */ a a;
    private a$i b;
    private boolean c;

    private a$l(a aVar) {
        this.a = aVar;
    }

    private a$l a(a$i dji_pilot_publics_control_a_i, boolean z) {
        this.b = dji_pilot_publics_control_a_i;
        this.c = z;
        return this;
    }

    public void run() {
        if (ServiceManager.getInstance().isConnected()) {
            a.a(this.a, 0);
            a.b(this.a, 0);
            Iterator it = a.k(this.a).iterator();
            while (it.hasNext()) {
                DataCommonGetVersion dataCommonGetVersion = (DataCommonGetVersion) it.next();
                boolean l;
                if (DeviceType.isRemote(dataCommonGetVersion.getDeviceType()) && !ServiceManager.getInstance().isRemoteOK()) {
                    if (this.c) {
                        l = a.l(this.a);
                    } else {
                        l = a.m(this.a);
                    }
                    if (!l) {
                        return;
                    }
                } else if (a.a(this.a, dataCommonGetVersion)) {
                    this.b.a(dataCommonGetVersion).run();
                } else {
                    if (this.c) {
                        l = a.l(this.a);
                    } else {
                        l = a.m(this.a);
                    }
                    if (!l) {
                        return;
                    }
                }
            }
            return;
        }
        a.b(this.a, false);
    }
}

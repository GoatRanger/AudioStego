package dji.sdksharedlib.hardware.abstractions.d;

import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataSimulatorConnectHeartPacket;
import dji.midware.e.d;
import java.util.TimerTask;

class d$a extends TimerTask {
    final /* synthetic */ d a;

    private d$a(d dVar) {
        this.a = dVar;
    }

    public void run() {
        if (d.a(this.a) == d$b.Running || d.a(this.a) == d$b.Starting || d.a(this.a) == d$b.ResponseReceived) {
            int i = 0;
            if (d.a(this.a) == d$b.Starting) {
                i = 1;
            }
            DataSimulatorConnectHeartPacket.getInstance().a(i).start(new d(this) {
                final /* synthetic */ d$a a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                }

                public void onFailure(a aVar) {
                }
            });
        }
    }
}

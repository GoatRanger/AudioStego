package dji.midware.data.model.P3;

import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.c;
import java.util.Timer;
import java.util.TimerTask;

public class DataCameraSetRecord extends n implements c {
    private static DataCameraSetRecord a = null;
    private Timer b;
    private TYPE c;

    public enum TYPE {
        STOP(0),
        START(1),
        PAUSE(2),
        RESUME(3),
        OTHER(7);
        
        private int f;

        private TYPE(int i) {
            this.f = i;
        }

        public int a() {
            return this.f;
        }

        public boolean a(int i) {
            return this.f == i;
        }
    }

    public static synchronized DataCameraSetRecord getInstance() {
        DataCameraSetRecord dataCameraSetRecord;
        synchronized (DataCameraSetRecord.class) {
            if (a == null) {
                a = new DataCameraSetRecord();
            }
            dataCameraSetRecord = a;
        }
        return dataCameraSetRecord;
    }

    public DataCameraSetRecord a(TYPE type) {
        this.c = type;
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[1];
        this._sendData[0] = (byte) this.c.a();
    }

    public void start(long j) {
        final dji.midware.data.a.a.c cVar = new dji.midware.data.a.a.c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.CAMERA.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.c.a();
        cVar.n = dji.midware.data.config.P3.b.a.b.a();
        cVar.p = getSendData();
        if (this.b == null) {
            this.b = new Timer();
        } else {
            stop();
        }
        this.b.schedule(new TimerTask(this) {
            final /* synthetic */ DataCameraSetRecord b;

            public void run() {
                this.b.start(cVar);
            }
        }, 0, j);
    }

    public void stop() {
        if (this.b != null) {
            this.b.cancel();
            this.b = null;
        }
    }
}

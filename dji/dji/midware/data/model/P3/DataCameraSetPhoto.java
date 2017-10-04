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

public class DataCameraSetPhoto extends n implements c {
    private static DataCameraSetPhoto a = null;
    private Timer b;
    private TYPE c;

    public enum TYPE {
        STOP(0),
        SINGLE(1),
        HDR(2),
        FULLVIEW(3),
        BURST(4),
        AEB(5),
        TIME(6),
        APP_FULLVIEW(7),
        OTHER(10);
        
        private int j;

        private TYPE(int i) {
            this.j = i;
        }

        public int a() {
            return this.j;
        }

        public boolean a(int i) {
            return this.j == i;
        }

        public static TYPE find(int i) {
            TYPE type = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2].a(i)) {
                    return values()[i2];
                }
            }
            return type;
        }
    }

    public static synchronized DataCameraSetPhoto getInstance() {
        DataCameraSetPhoto dataCameraSetPhoto;
        synchronized (DataCameraSetPhoto.class) {
            if (a == null) {
                a = new DataCameraSetPhoto();
            }
            dataCameraSetPhoto = a;
        }
        return dataCameraSetPhoto;
    }

    public DataCameraSetPhoto a(TYPE type) {
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
        cVar.n = dji.midware.data.config.P3.b.a.a.a();
        cVar.p = getSendData();
        if (this.b == null) {
            this.b = new Timer();
        } else {
            stop();
        }
        this.b.schedule(new TimerTask(this) {
            final /* synthetic */ DataCameraSetPhoto b;

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

package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataCameraSetFileIndexMode extends n implements e {
    private static DataCameraSetFileIndexMode a = null;
    private FileIndexMode b = FileIndexMode.RESET;

    public enum FileIndexMode {
        RESET(0),
        CONTINUOUS(1);
        
        private int c;

        private FileIndexMode(int i) {
            this.c = i;
        }

        public int a() {
            return this.c;
        }

        public boolean a(int i) {
            return this.c == i;
        }

        public static FileIndexMode find(int i) {
            FileIndexMode fileIndexMode = RESET;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2].a(i)) {
                    return values()[i2];
                }
            }
            return fileIndexMode;
        }
    }

    public static synchronized DataCameraSetFileIndexMode getInstance() {
        DataCameraSetFileIndexMode dataCameraSetFileIndexMode;
        synchronized (DataCameraSetFileIndexMode.class) {
            if (a == null) {
                a = new DataCameraSetFileIndexMode();
            }
            dataCameraSetFileIndexMode = a;
        }
        return dataCameraSetFileIndexMode;
    }

    public void a(FileIndexMode fileIndexMode) {
        this.b = fileIndexMode;
    }

    protected void doPack() {
        this._sendData = new byte[1];
        this._sendData[0] = (byte) (this.b != null ? this.b.a() : FileIndexMode.RESET.a());
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.CAMERA.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.c.a();
        cVar.n = dji.midware.data.config.P3.b.a.ao.a();
        start(cVar, dVar);
    }
}

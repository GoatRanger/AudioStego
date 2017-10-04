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

public class DataCameraGetUsbStatus extends n implements e {
    private static DataCameraGetUsbStatus instance = null;

    public enum USBSTATUS {
        DISCONNECT(0),
        CONNECT(1);
        
        private int value;

        private USBSTATUS(int i) {
            this.value = i;
        }

        public int value() {
            return this.value;
        }
    }

    public static synchronized DataCameraGetUsbStatus getInstance() {
        DataCameraGetUsbStatus dataCameraGetUsbStatus;
        synchronized (DataCameraGetUsbStatus.class) {
            if (instance == null) {
                instance = new DataCameraGetUsbStatus();
            }
            dataCameraGetUsbStatus = instance;
        }
        return dataCameraGetUsbStatus;
    }

    public USBSTATUS getUSBStatus() {
        return (((Integer) get(1, 1, Integer.class)).intValue() & 1) == 1 ? USBSTATUS.CONNECT : USBSTATUS.DISCONNECT;
    }

    protected void doPack() {
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.CAMERA.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.c.a();
        cVar.n = dji.midware.data.config.P3.b.a.f.a();
        start(cVar, dVar);
    }
}

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

public class DataCameraGetFileParams extends n implements e {
    private static DataCameraGetFileParams instance = null;
    private ParamsType paramsType;

    public enum ParamsType {
        DCF(0),
        CLIP(1);
        
        private int value;

        private ParamsType(int i) {
            this.value = i;
        }

        public int value() {
            return this.value;
        }
    }

    public static synchronized DataCameraGetFileParams getInstance() {
        DataCameraGetFileParams dataCameraGetFileParams;
        synchronized (DataCameraGetFileParams.class) {
            if (instance == null) {
                instance = new DataCameraGetFileParams();
            }
            dataCameraGetFileParams = instance;
        }
        return dataCameraGetFileParams;
    }

    public DataCameraGetFileParams setType(ParamsType paramsType) {
        this.paramsType = paramsType;
        return this;
    }

    public int getMaxFolderNum() {
        return ((Integer) get(0, 2, Integer.class)).intValue();
    }

    public int getFolderId() {
        return ((Integer) get(2, 2, Integer.class)).intValue();
    }

    public int getMaxFileNum() {
        return ((Integer) get(4, 2, Integer.class)).intValue();
    }

    protected void doPack() {
        this._sendData = new byte[1];
        this._sendData[0] = (byte) this.paramsType.value();
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.CAMERA.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.c.a();
        cVar.n = dji.midware.data.config.P3.b.a.bc.a();
        start(cVar, dVar);
    }
}

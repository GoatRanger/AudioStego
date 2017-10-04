package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;
import dji.thirdparty.g.b.a.a;

public class DataCameraRequestSendFiles extends n implements e {
    private static DataCameraRequestSendFiles instance = null;
    private FILE_SELECT_MODE mode = FILE_SELECT_MODE.CURRENT;

    public enum Error {
        FileNotFound(34),
        INVALID_CMD(a.fw_),
        OTHER(100);
        
        private int data;

        private Error(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static Error find(int i) {
            Error error = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return error;
        }
    }

    public enum FILE_SELECT_MODE {
        CURRENT(0),
        NEXT(1),
        OTHER(100);
        
        private int data;

        private FILE_SELECT_MODE(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static FILE_SELECT_MODE find(int i) {
            FILE_SELECT_MODE file_select_mode = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return file_select_mode;
        }
    }

    public static synchronized DataCameraRequestSendFiles getInstance() {
        DataCameraRequestSendFiles dataCameraRequestSendFiles;
        synchronized (DataCameraRequestSendFiles.class) {
            if (instance == null) {
                instance = new DataCameraRequestSendFiles();
            }
            dataCameraRequestSendFiles = instance;
        }
        return dataCameraRequestSendFiles;
    }

    public DataCameraRequestSendFiles setMode(FILE_SELECT_MODE file_select_mode) {
        this.mode = file_select_mode;
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[1];
        this._sendData[0] = (byte) this.mode.value();
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.CAMERA.value();
        cVar.j = q.a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.a.a();
        cVar.n = dji.midware.data.config.P3.d.a.l.a();
        start(cVar, dVar);
    }
}

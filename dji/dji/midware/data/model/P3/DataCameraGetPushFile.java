package dji.midware.data.model.P3;

import dji.midware.data.a.b.b;
import dji.midware.data.b.a.a;
import dji.midware.data.manager.P3.n;
import dji.midware.data.model.d.c;
import dji.midware.data.model.d.d;
import dji.midware.data.model.d.e;
import dji.midware.data.model.d.f;

public class DataCameraGetPushFile extends n {
    private static DataCameraGetPushFile instance = null;

    public static synchronized DataCameraGetPushFile getInstance() {
        DataCameraGetPushFile dataCameraGetPushFile;
        synchronized (DataCameraGetPushFile.class) {
            if (instance == null) {
                instance = new DataCameraGetPushFile();
            }
            dataCameraGetPushFile = instance;
        }
        return dataCameraGetPushFile;
    }

    public void setPushRecData(byte[] bArr) {
        b bVar = new b(bArr);
        if (bVar.g == a.sessionId()) {
            switch (dji.midware.data.config.a.a.b.find(bVar.d)) {
                case PUSH:
                    e.getInstance().a(bVar);
                    return;
                case ABORT:
                    dji.midware.data.model.d.b.getInstance().a(bVar);
                    return;
                case DATA:
                    switch (dji.midware.data.config.a.a.a.find(bVar.c)) {
                        case a:
                            d.getInstance().a(bVar);
                            return;
                        case File:
                            c.getInstance().a(bVar);
                            return;
                        case Stream:
                            f.getInstance().a(bVar);
                            return;
                        default:
                            return;
                    }
                default:
                    return;
            }
        }
    }

    protected void doPack() {
    }
}

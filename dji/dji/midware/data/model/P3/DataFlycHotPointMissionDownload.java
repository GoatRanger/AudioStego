package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.g;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.data.model.P3.DataFlycStartHotPointMissionWithInfo.CAMERA_DIR;
import dji.midware.data.model.P3.DataFlycStartHotPointMissionWithInfo.ROTATION_DIR;
import dji.midware.data.model.P3.DataFlycStartHotPointMissionWithInfo.TO_START_POINT_MODE;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataFlycHotPointMissionDownload extends n implements e {
    private static DataFlycHotPointMissionDownload instance = null;

    public static synchronized DataFlycHotPointMissionDownload getInstance() {
        DataFlycHotPointMissionDownload dataFlycHotPointMissionDownload;
        synchronized (DataFlycHotPointMissionDownload.class) {
            if (instance == null) {
                instance = new DataFlycHotPointMissionDownload();
            }
            dataFlycHotPointMissionDownload = instance;
        }
        return dataFlycHotPointMissionDownload;
    }

    public int getResult() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    public int getVersion() {
        return ((Integer) get(1, 1, Integer.class)).intValue();
    }

    public double getHotPointLatitude() {
        return ((Double) get(2, 8, Double.class)).doubleValue();
    }

    public double getHotPointLongitude() {
        return ((Double) get(10, 8, Double.class)).doubleValue();
    }

    public double getHotPointAttitude() {
        return ((Double) get(18, 8, Double.class)).doubleValue();
    }

    public double getHotPointRadius() {
        return ((Double) get(26, 8, Double.class)).doubleValue();
    }

    public float getHotPointAngleSpeed() {
        return ((Float) get(34, 4, Float.class)).floatValue();
    }

    public ROTATION_DIR getHotPointClockWise() {
        return ROTATION_DIR.find(((Integer) get(38, 1, Integer.class)).intValue());
    }

    public TO_START_POINT_MODE getHotPointToStartPointMode() {
        return TO_START_POINT_MODE.find(((Integer) get(39, 1, Integer.class)).intValue());
    }

    public CAMERA_DIR getHotPointCameraDir() {
        return CAMERA_DIR.find(((Integer) get(40, 1, Integer.class)).intValue());
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.FLYC.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.d.a();
        cVar.n = g.a.aq.a();
        cVar.p = getSendData();
        start(cVar, dVar);
    }

    protected void doPack() {
        this._sendData = null;
    }
}

package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.g;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.data.model.P3.DataFlycUploadWayPointMissionMsg.ACTION_ON_RC_LOST;
import dji.midware.data.model.P3.DataFlycUploadWayPointMissionMsg.FINISH_ACTION;
import dji.midware.data.model.P3.DataFlycUploadWayPointMissionMsg.GIMBAL_PITCH_MODE;
import dji.midware.data.model.P3.DataFlycUploadWayPointMissionMsg.TRACE_MODE;
import dji.midware.data.model.P3.DataFlycUploadWayPointMissionMsg.YAW_MODE;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataFlycDownloadWayPointMissionMsg extends n implements e {
    private static DataFlycDownloadWayPointMissionMsg instance = null;

    public static synchronized DataFlycDownloadWayPointMissionMsg getInstance() {
        DataFlycDownloadWayPointMissionMsg dataFlycDownloadWayPointMissionMsg;
        synchronized (DataFlycDownloadWayPointMissionMsg.class) {
            if (instance == null) {
                instance = new DataFlycDownloadWayPointMissionMsg();
            }
            dataFlycDownloadWayPointMissionMsg = instance;
        }
        return dataFlycDownloadWayPointMissionMsg;
    }

    public int getResult() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    public int getWayPointCount() {
        return ((Integer) get(1, 1, Integer.class)).intValue();
    }

    public float getCmdSpeed() {
        return ((Float) get(2, 4, Float.class)).floatValue();
    }

    public float getIdleSpeed() {
        return ((Float) get(6, 4, Float.class)).floatValue();
    }

    public FINISH_ACTION getFinishAction() {
        return FINISH_ACTION.find(((Integer) get(10, 1, Integer.class)).intValue());
    }

    public int getRepeatNum() {
        return ((Integer) get(11, 1, Integer.class)).intValue();
    }

    public YAW_MODE getYawMode() {
        return YAW_MODE.find(((Integer) get(12, 1, Integer.class)).intValue());
    }

    public TRACE_MODE getTraceMode() {
        return TRACE_MODE.find(((Integer) get(13, 1, Integer.class)).intValue());
    }

    public ACTION_ON_RC_LOST getActionOnRCLost() {
        return ACTION_ON_RC_LOST.find(((Integer) get(14, 1, Integer.class)).intValue());
    }

    public GIMBAL_PITCH_MODE getGimbalPitchMode() {
        return GIMBAL_PITCH_MODE.find(((Integer) get(15, 1, Integer.class)).intValue());
    }

    public double getHPLat() {
        return ((Double) get(16, 8, Double.class)).doubleValue();
    }

    public double getHPLng() {
        return ((Double) get(24, 8, Double.class)).doubleValue();
    }

    public float getHPHeight() {
        return ((Float) get(32, 4, Float.class)).floatValue();
    }

    public int getGotoFirstFlag() {
        return ((Integer) get(36, 1, Integer.class)).intValue();
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.FLYC.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.d.a();
        cVar.n = g.a.ac.a();
        cVar.p = getSendData();
        start(cVar, dVar);
    }

    protected void doPack() {
        this._sendData = new byte[1];
        this._sendData[0] = (byte) 0;
    }
}

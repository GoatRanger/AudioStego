package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.k;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataRcGetFDRcCalibrationState extends n implements e {
    private static DataRcGetFDRcCalibrationState instance;

    public static synchronized DataRcGetFDRcCalibrationState getInstance() {
        DataRcGetFDRcCalibrationState dataRcGetFDRcCalibrationState;
        synchronized (DataRcGetFDRcCalibrationState.class) {
            if (instance == null) {
                instance = new DataRcGetFDRcCalibrationState();
            }
            dataRcGetFDRcCalibrationState = instance;
        }
        return dataRcGetFDRcCalibrationState;
    }

    public int getSegmentNumber() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    public int getASegmentFilledUpState() {
        return ((Integer) get(1, 4, Integer.class)).intValue();
    }

    public int getBSegmentFilledUpState() {
        return ((Integer) get(5, 4, Integer.class)).intValue();
    }

    public int getCSegmentFilledUpState() {
        return ((Integer) get(9, 4, Integer.class)).intValue();
    }

    public int getDSegmentFilledUpState() {
        return ((Integer) get(13, 4, Integer.class)).intValue();
    }

    public int getESegmentFilledUpState() {
        return ((Integer) get(17, 4, Integer.class)).intValue();
    }

    public int getFSegmentFilledUpState() {
        return ((Integer) get(21, 4, Integer.class)).intValue();
    }

    public int getGSegmentFilledUpState() {
        return ((Integer) get(25, 4, Integer.class)).intValue();
    }

    public int getHSegmentFilledUpState() {
        return ((Integer) get(29, 4, Integer.class)).intValue();
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.OSD.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.g.a();
        cVar.n = k.a.GetFDRcCalibrationStatue.b();
        cVar.p = getSendData();
        start(cVar, dVar);
    }

    protected void doPack() {
        this._sendData = new byte[1];
        this._sendData[0] = (byte) 0;
    }
}

package dji.pilot.fpv.model;

import android.util.Log;
import dji.midware.data.manager.P3.n;
import dji.midware.data.model.P3.DataEyeGetPushAvoidanceParam;
import dji.midware.data.model.P3.DataEyeGetPushException;
import dji.midware.data.model.P3.DataEyeGetPushFrontAvoidance;
import dji.midware.data.model.P3.DataEyeGetPushFrontAvoidance.SensorType;
import dji.midware.data.model.P3.DataFlycGetPushAvoidParam;
import dji.midware.util.c;
import java.util.Arrays;
import java.util.HashMap;

public class l extends n {
    public static final SensorType[] a = new SensorType[]{SensorType.Front, SensorType.Back, SensorType.Right, SensorType.Left, SensorType.Top, SensorType.Bottom};
    public static HashMap<SensorType, DataEyeGetPushFrontAvoidance> b = new HashMap(6);
    public static DataFlycGetPushAvoidParam c = new DataFlycGetPushAvoidParam();
    public static DataEyeGetPushAvoidanceParam d = new DataEyeGetPushAvoidanceParam();
    public static DataEyeGetPushException e = new DataEyeGetPushException();
    private HashMap<SensorType, Boolean> f = new HashMap(6);
    private boolean g = false;
    private boolean h = false;
    private boolean i = false;

    public enum a {
        RADAR_FRONT(0),
        RADAR_BACK(1),
        RADAR_RIGHT(2),
        RADAR_LEFT(3),
        RADAR_TOP(4),
        RADAR_BOTTOM(5),
        RADAR_RESERVE_1(6),
        RADAR_RESERVE_2(7),
        MC_AVOID_STATUS(8),
        GUIDANCE_DETECT(9),
        WARN_STATUS(10),
        END(254),
        OTHER(253);
        
        private int n;

        private a(int i) {
            this.n = i;
        }

        public byte a() {
            return (byte) this.n;
        }

        public boolean a(int i) {
            return this.n == i;
        }

        public static a find(int i) {
            a aVar = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2].a(i)) {
                    return values()[i2];
                }
            }
            return aVar;
        }
    }

    static {
        for (Object put : a) {
            b.put(put, new DataEyeGetPushFrontAvoidance(false));
        }
    }

    public l(boolean z) {
        super(z);
    }

    public void setRecData(byte[] bArr) {
        super.setRecData(bArr);
    }

    public byte[] getRecData() {
        return b();
    }

    private int a() {
        int i = 0;
        SensorType[] sensorTypeArr = a;
        int i2 = 0;
        while (i < sensorTypeArr.length) {
            i2 += a(sensorTypeArr[i]);
            i++;
        }
        return ((a(DataFlycGetPushAvoidParam.getInstance(), c) + i2) + a(DataEyeGetPushAvoidanceParam.getInstance(), d)) + a(DataEyeGetPushException.getInstance(), e);
    }

    private int a(SensorType sensorType) {
        DataEyeGetPushFrontAvoidance a = dji.logic.g.a.getInstance().a(sensorType);
        if (a == null || a.getRecData() == null || Arrays.equals(a.getRecData(), ((DataEyeGetPushFrontAvoidance) b.get(sensorType)).getRecData())) {
            this.f.put(sensorType, Boolean.valueOf(false));
            return 0;
        }
        ((DataEyeGetPushFrontAvoidance) b.get(sensorType)).setRecData(a.getRecData());
        this.f.put(sensorType, Boolean.valueOf(true));
        return a.getRecDataLen() + 3;
    }

    private int a(n nVar, n nVar2) {
        if (nVar == null || nVar.getRecData() == null || Arrays.equals(nVar.getRecData(), nVar2.getRecData())) {
            return 0;
        }
        nVar2.setRecData(nVar.getRecData());
        if (nVar instanceof DataFlycGetPushAvoidParam) {
            this.g = true;
        }
        if (nVar instanceof DataEyeGetPushAvoidanceParam) {
            this.h = true;
        }
        if (nVar instanceof DataEyeGetPushException) {
            this.i = true;
        }
        return nVar.getRecDataLen() + 3;
    }

    private byte[] b() {
        int a = a();
        byte[] bArr = new byte[a];
        SensorType[] sensorTypeArr = a;
        int length = sensorTypeArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int length2;
            SensorType sensorType = sensorTypeArr[i];
            if (((Boolean) this.f.get(sensorType)).booleanValue()) {
                byte[] recData = ((DataEyeGetPushFrontAvoidance) b.get(sensorType)).getRecData();
                bArr[i2] = (byte) sensorType.value();
                i2++;
                bArr[i2] = (byte) recData.length;
                i2++;
                c.a(recData, bArr, i2);
                length2 = recData.length + i2;
                bArr[length2] = a.END.a();
                length2++;
            } else {
                length2 = i2;
            }
            i++;
            i2 = length2;
        }
        if (this.g) {
            recData = c.getRecData();
            bArr[i2] = a.MC_AVOID_STATUS.a();
            i2++;
            bArr[i2] = (byte) recData.length;
            i2++;
            c.a(recData, bArr, i2);
            length2 = recData.length + i2;
            bArr[length2] = a.END.a();
            i2 = length2 + 1;
        }
        if (this.h) {
            recData = d.getRecData();
            bArr[i2] = a.GUIDANCE_DETECT.a();
            i2++;
            bArr[i2] = (byte) recData.length;
            i2++;
            c.a(recData, bArr, i2);
            length2 = recData.length + i2;
            bArr[length2] = a.END.a();
            i2 = length2 + 1;
        }
        if (this.i) {
            recData = e.getRecData();
            bArr[i2] = a.WARN_STATUS.a();
            i2++;
            bArr[i2] = (byte) recData.length;
            i2++;
            c.a(recData, bArr, i2);
            length2 = recData.length + i2;
            bArr[length2] = a.END.a();
            i2 = length2 + 1;
        }
        Log.d("FLightRecordVisionGroup", "vision group length:" + a + "  end index:" + i2);
        return bArr;
    }

    protected void doPack() {
    }
}

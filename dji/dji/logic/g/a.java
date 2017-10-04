package dji.logic.g;

import dji.midware.data.manager.P3.o;
import dji.midware.data.model.P3.DataEyeGetPushFrontAvoidance;
import dji.midware.data.model.P3.DataEyeGetPushFrontAvoidance.SensorType;
import dji.thirdparty.a.c;
import java.util.Arrays;
import java.util.HashMap;

public class a {
    private final HashMap<SensorType, b> a;
    private final HashMap<SensorType, DataEyeGetPushFrontAvoidance> b;

    public static a getInstance() {
        return a.a();
    }

    public void a(DataEyeGetPushFrontAvoidance dataEyeGetPushFrontAvoidance) {
        if (dataEyeGetPushFrontAvoidance != null && dataEyeGetPushFrontAvoidance.getRecData() != null) {
            synchronized (this.b) {
                SensorType sensorType = dataEyeGetPushFrontAvoidance.getSensorType();
                DataEyeGetPushFrontAvoidance dataEyeGetPushFrontAvoidance2 = (DataEyeGetPushFrontAvoidance) this.b.get(sensorType);
                if (dataEyeGetPushFrontAvoidance2 == null) {
                    dataEyeGetPushFrontAvoidance2 = new DataEyeGetPushFrontAvoidance(false);
                    this.b.put(sensorType, dataEyeGetPushFrontAvoidance2);
                }
                if (!Arrays.equals(dataEyeGetPushFrontAvoidance2.getRecData(), dataEyeGetPushFrontAvoidance.getRecData())) {
                    dataEyeGetPushFrontAvoidance2.setRecData(dataEyeGetPushFrontAvoidance.getRecData());
                    c.a().e(this.a.get(sensorType));
                }
            }
        }
    }

    public HashMap<SensorType, DataEyeGetPushFrontAvoidance> a() {
        HashMap<SensorType, DataEyeGetPushFrontAvoidance> hashMap;
        synchronized (this.b) {
            hashMap = this.b;
        }
        return hashMap;
    }

    public DataEyeGetPushFrontAvoidance a(SensorType sensorType) {
        DataEyeGetPushFrontAvoidance dataEyeGetPushFrontAvoidance;
        synchronized (this.b) {
            dataEyeGetPushFrontAvoidance = (DataEyeGetPushFrontAvoidance) this.b.get(sensorType);
        }
        return dataEyeGetPushFrontAvoidance;
    }

    private void b() {
        synchronized (this.b) {
            this.b.clear();
        }
    }

    public void onEventBackgroundThread(o oVar) {
        if (o.ConnectLose == oVar) {
            b();
        }
    }

    private a() {
        this.a = new HashMap(6);
        this.b = new HashMap(6);
        this.a.put(SensorType.Front, b.a);
        this.a.put(SensorType.Back, b.b);
        this.a.put(SensorType.Left, b.c);
        this.a.put(SensorType.Right, b.d);
        this.a.put(SensorType.Top, b.e);
        this.a.put(SensorType.Bottom, b.f);
        c.a().a((Object) this);
    }
}

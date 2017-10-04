package dji.midware.data.model.P3;

import dji.midware.data.model.a.a;
import java.util.ArrayList;

public class DataCameraGetPushTimelapseParms extends a {
    private static DataCameraGetPushTimelapseParms instance = null;

    public class TimelapsePushPointInfo {
        public int duration = 0;
        public int interval = 0;
        public int pitch = 0;
        public int roll = 0;
        public int yaw = 0;
    }

    public static synchronized DataCameraGetPushTimelapseParms getInstance() {
        DataCameraGetPushTimelapseParms dataCameraGetPushTimelapseParms;
        synchronized (DataCameraGetPushTimelapseParms.class) {
            if (instance == null) {
                instance = new DataCameraGetPushTimelapseParms();
            }
            dataCameraGetPushTimelapseParms = instance;
        }
        return dataCameraGetPushTimelapseParms;
    }

    public int getControlMode() {
        return ((Integer) get(0, 1, Integer.class)).intValue() & 3;
    }

    public int getGimbalPointCount() {
        return ((Integer) get(0, 1, Integer.class)).intValue() >> 2;
    }

    public ArrayList<TimelapsePushPointInfo> getGimbalInfoPoints() {
        ArrayList<TimelapsePushPointInfo> arrayList = new ArrayList();
        for (int i = 1; i <= getGimbalPointCount(); i++) {
            TimelapsePushPointInfo timelapsePushPointInfo = new TimelapsePushPointInfo();
            timelapsePushPointInfo.interval = getInterval(i);
            timelapsePushPointInfo.duration = getDuration(i);
            timelapsePushPointInfo.yaw = getYaw(i);
            timelapsePushPointInfo.roll = getRoll(i);
            timelapsePushPointInfo.pitch = getPitch(i);
            arrayList.add(timelapsePushPointInfo);
        }
        return arrayList;
    }

    public int getInterval(int i) {
        return ((Integer) get(((i - 1) * 12) + 1, 2, Integer.class)).intValue();
    }

    public int getDuration(int i) {
        return ((Integer) get(((i - 1) * 12) + 3, 4, Integer.class)).intValue();
    }

    public int getTotalDuration() {
        int i = 0;
        for (int i2 = 1; i2 <= getGimbalPointCount(); i2++) {
            i += getDuration(i2);
        }
        return i;
    }

    public int getYaw(int i) {
        return ((Integer) get(((i - 1) * 12) + 7, 2, Integer.class)).intValue();
    }

    public int getRoll(int i) {
        return ((Integer) get(((i - 1) * 12) + 9, 2, Integer.class)).intValue();
    }

    public int getPitch(int i) {
        return ((Integer) get(((i - 1) * 12) + 11, 2, Integer.class)).intValue();
    }

    protected void doPack() {
    }
}

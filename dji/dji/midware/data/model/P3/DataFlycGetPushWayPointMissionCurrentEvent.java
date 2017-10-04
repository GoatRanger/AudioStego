package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;

public class DataFlycGetPushWayPointMissionCurrentEvent extends n {
    private static DataFlycGetPushWayPointMissionCurrentEvent instance = null;

    public static synchronized DataFlycGetPushWayPointMissionCurrentEvent getInstance() {
        DataFlycGetPushWayPointMissionCurrentEvent dataFlycGetPushWayPointMissionCurrentEvent;
        synchronized (DataFlycGetPushWayPointMissionCurrentEvent.class) {
            if (instance == null) {
                instance = new DataFlycGetPushWayPointMissionCurrentEvent();
            }
            dataFlycGetPushWayPointMissionCurrentEvent = instance;
        }
        return dataFlycGetPushWayPointMissionCurrentEvent;
    }

    protected void doPack() {
    }

    protected boolean isChanged(byte[] bArr) {
        return true;
    }

    public int getEventType() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    public int getUploadIncidentIsValid() {
        return ((Integer) get(1, 1, Integer.class)).intValue();
    }

    public int getUploadIncidentEstimatedTime() {
        return ((Integer) get(2, 2, Integer.class)).intValue();
    }

    public int getUploadIncidentReserved() {
        return ((Integer) get(4, 2, Integer.class)).intValue();
    }

    public int getFinishIncidentIsRepeat() {
        return ((Integer) get(1, 1, Integer.class)).intValue();
    }

    public int getFinishIncidentResrved() {
        return ((Integer) get(2, 2, Integer.class)).intValue();
    }

    public int getReachIncidentWayPointIndex() {
        return ((Integer) get(1, 1, Integer.class)).intValue();
    }

    public int getReachIncidentCurrentStatus() {
        return ((Integer) get(2, 1, Integer.class)).intValue();
    }

    public int getReachIncidentReserved() {
        return ((Integer) get(3, 1, Integer.class)).intValue();
    }
}

package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;

public class DataFlycGetPushWayPointMissionInfo extends n {
    private static DataFlycGetPushWayPointMissionInfo instance = null;

    public enum RunningStatus {
        NotRunning(0),
        Running(1),
        Paused(2);
        
        private int data;

        private RunningStatus(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static RunningStatus find(int i) {
            RunningStatus runningStatus = NotRunning;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return runningStatus;
        }
    }

    public static synchronized DataFlycGetPushWayPointMissionInfo getInstance() {
        DataFlycGetPushWayPointMissionInfo dataFlycGetPushWayPointMissionInfo;
        synchronized (DataFlycGetPushWayPointMissionInfo.class) {
            if (instance == null) {
                instance = new DataFlycGetPushWayPointMissionInfo();
            }
            dataFlycGetPushWayPointMissionInfo = instance;
        }
        return dataFlycGetPushWayPointMissionInfo;
    }

    protected boolean isChanged(byte[] bArr) {
        return true;
    }

    protected void doPack() {
    }

    public int getMissionType() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    public int getReserved() {
        return ((Integer) get(1, 3, Integer.class)).intValue();
    }

    public int getTargetWayPoint() {
        return ((Integer) get(1, 1, Integer.class)).intValue();
    }

    public int getCurrentStatus() {
        return ((Integer) get(2, 1, Integer.class)).intValue();
    }

    public int getErrorNotification() {
        return ((Integer) get(3, 1, Integer.class)).intValue();
    }

    public int getWayPointStatus() {
        return ((Integer) get(4, 1, Integer.class)).intValue();
    }

    public RunningStatus getRunningStatus() {
        return RunningStatus.find(((Integer) get(4, 1, Integer.class)).intValue());
    }

    public int getHotPointMissionStatus() {
        return ((Integer) get(1, 1, Integer.class)).intValue();
    }

    public int getHotPointRadius() {
        return ((Integer) get(2, 2, Integer.class)).intValue();
    }

    public int getHotPointReason() {
        return ((Integer) get(4, 1, Integer.class)).intValue();
    }

    public int getHotPointSpeed() {
        return ((Integer) get(5, 1, Integer.class)).intValue();
    }

    public int getFollowMeStatus() {
        return ((Integer) get(1, 1, Integer.class)).intValue() & 15;
    }

    public int getFollowMeGpsLevel() {
        return (((Integer) get(1, 1, Integer.class)).intValue() >> 4) & 15;
    }

    public int getFollowMeDistance() {
        return ((Integer) get(2, 2, Integer.class)).intValue();
    }

    public int getFollowMeReason() {
        return ((Integer) get(4, 1, Integer.class)).intValue();
    }

    public int getMissionStatus() {
        return ((Integer) get(1, 1, Integer.class)).intValue() & 3;
    }

    public boolean isPositionValid() {
        return (((Integer) get(1, 1, Integer.class)).intValue() & 4) == 4;
    }

    public int getLimitedHeight() {
        return ((Integer) get(2, 2, Integer.class)).intValue();
    }

    public int getCurrentHeight() {
        return ((Integer) get(4, 2, Integer.class)).intValue();
    }

    public int isTrackingEnabled() {
        return ((Integer) get(6, 1, Integer.class)).intValue();
    }
}

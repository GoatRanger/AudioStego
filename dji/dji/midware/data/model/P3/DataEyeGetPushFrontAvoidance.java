package dji.midware.data.model.P3;

import dji.logic.g.a;
import dji.midware.data.manager.P3.n;
import dji.thirdparty.afinal.c.c;

public class DataEyeGetPushFrontAvoidance extends n {
    private static DataEyeGetPushFrontAvoidance instance = null;
    private int[] mCacheData = null;
    private int[] mCacheLevels = null;

    public enum SensorType {
        Front(0),
        Back(1),
        Right(2),
        Left(3),
        Top(4),
        Bottom(5),
        OTHER(100);
        
        private final int data;

        private SensorType(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static SensorType find(int i) {
            SensorType sensorType = Front;
            for (SensorType sensorType2 : values()) {
                if (sensorType2._equals(i)) {
                    return sensorType2;
                }
            }
            return sensorType;
        }
    }

    public static synchronized DataEyeGetPushFrontAvoidance getInstance() {
        DataEyeGetPushFrontAvoidance dataEyeGetPushFrontAvoidance;
        synchronized (DataEyeGetPushFrontAvoidance.class) {
            if (instance == null) {
                instance = new DataEyeGetPushFrontAvoidance(true);
            }
            dataEyeGetPushFrontAvoidance = instance;
        }
        return dataEyeGetPushFrontAvoidance;
    }

    public DataEyeGetPushFrontAvoidance(boolean z) {
        super(z);
    }

    protected void setPushRecData(byte[] bArr) {
        a.getInstance().a(this);
        super.setPushRecData(bArr);
    }

    protected boolean isWantPush() {
        return super.isWantPush();
    }

    public SensorType getSensorType() {
        return SensorType.find((((Integer) get(0, 1, Integer.class)).intValue() & dji.thirdparty.g.b.a.a.fw_) >> 5);
    }

    public int getObserveCount() {
        return ((Integer) get(0, 1, Integer.class)).intValue() & 31;
    }

    public int[] getObserveValues() {
        int observeCount = getObserveCount();
        if (observeCount == 0) {
            return null;
        }
        if (this.mCacheData == null || observeCount != this.mCacheData.length) {
            this.mCacheData = new int[observeCount];
        }
        c.b(this.mCacheData, Integer.MAX_VALUE);
        int i = 0;
        while (i < observeCount && (i * 3) + 3 <= this._recData.length) {
            this.mCacheData[i] = ((Integer) get((i * 3) + 1, 2, Integer.class)).intValue();
            i++;
        }
        return this.mCacheData;
    }

    public int[] getObserveLevels() {
        int observeCount = getObserveCount();
        if (observeCount == 0) {
            return null;
        }
        if (this.mCacheLevels == null || observeCount != this.mCacheLevels.length) {
            this.mCacheLevels = new int[observeCount];
        }
        c.b(this.mCacheLevels, Integer.MAX_VALUE);
        int i = 0;
        while (i < observeCount && (i * 3) + 4 <= this._recData.length) {
            this.mCacheLevels[i] = ((Integer) get((i * 3) + 3, 1, Integer.class)).intValue();
            i++;
        }
        return this.mCacheLevels;
    }

    protected void doPack() {
    }
}

package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;

public class DataEyeGetPushFlatCheck extends n {
    private static DataEyeGetPushFlatCheck instance = null;

    public enum FlatStatus {
        None(0),
        Calculating(1),
        SafeForLanding(2),
        UnsafeToHover(3),
        WaterSurfaceToHover(4),
        EnterCheckArea(10),
        UnderExposure(-1),
        DriftMuchWhenLanding(-2),
        MoveStickWhenCalculating(-3),
        TooLow(-4),
        TooHigh(-5),
        BadResult(-10),
        OTHER(100);
        
        private final int data;

        private FlatStatus(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static FlatStatus find(int i) {
            FlatStatus flatStatus = None;
            for (FlatStatus flatStatus2 : values()) {
                if (flatStatus2._equals(i)) {
                    return flatStatus2;
                }
            }
            return flatStatus;
        }
    }

    public static synchronized DataEyeGetPushFlatCheck getInstance() {
        DataEyeGetPushFlatCheck dataEyeGetPushFlatCheck;
        synchronized (DataEyeGetPushFlatCheck.class) {
            if (instance == null) {
                instance = new DataEyeGetPushFlatCheck();
            }
            dataEyeGetPushFlatCheck = instance;
        }
        return dataEyeGetPushFlatCheck;
    }

    public int getTinkCount() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    public FlatStatus getFlatStatus() {
        if (this._recData == null || this._recData.length <= 1) {
            return FlatStatus.None;
        }
        return FlatStatus.find(this._recData[1]);
    }

    protected void doPack() {
    }
}

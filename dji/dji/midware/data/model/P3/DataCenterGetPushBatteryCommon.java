package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;

public class DataCenterGetPushBatteryCommon extends n {
    private static final int FLAG_DAY = 31;
    private static final int FLAG_MONTH = 480;
    private static final int FLAG_YEAR = 65024;
    private static DataCenterGetPushBatteryCommon instance = null;
    private final int[] mPartVoltages;
    private final int[] mProductDate;

    public enum ConnStatus {
        NORMAL(0),
        INVALID(1),
        EXCEPTION(2),
        OTHER(100);
        
        private int mData;

        private ConnStatus(int i) {
            this.mData = 0;
            this.mData = i;
        }

        public int value() {
            return this.mData;
        }

        private boolean belongsTo(int i) {
            return this.mData == i;
        }

        public static ConnStatus ofData(int i) {
            for (ConnStatus connStatus : values()) {
                if (connStatus.belongsTo(i)) {
                    return connStatus;
                }
            }
            return OTHER;
        }
    }

    public static synchronized DataCenterGetPushBatteryCommon getInstance() {
        DataCenterGetPushBatteryCommon dataCenterGetPushBatteryCommon;
        synchronized (DataCenterGetPushBatteryCommon.class) {
            if (instance == null) {
                instance = new DataCenterGetPushBatteryCommon();
            }
            dataCenterGetPushBatteryCommon = instance;
        }
        return dataCenterGetPushBatteryCommon;
    }

    public DataCenterGetPushBatteryCommon() {
        this.mPartVoltages = new int[6];
        this.mProductDate = new int[]{2013, 1, 1};
    }

    public DataCenterGetPushBatteryCommon(boolean z) {
        super(z);
        this.mPartVoltages = new int[6];
        this.mProductDate = new int[]{2013, 1, 1};
    }

    public int getRelativeCapacity() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    public int getCurrentPV() {
        return ((Integer) get(1, 2, Integer.class)).intValue();
    }

    public int getCurrentCapacity() {
        return ((Integer) get(3, 2, Integer.class)).intValue();
    }

    public int getFullCapacity() {
        return ((Integer) get(5, 2, Integer.class)).intValue();
    }

    public int getLife() {
        return ((Integer) get(7, 1, Integer.class)).intValue();
    }

    public int getLoopNum() {
        return ((Integer) get(8, 2, Integer.class)).intValue();
    }

    public int getErrorType() {
        return ((Integer) get(10, 4, Integer.class)).intValue();
    }

    public int getCurrent() {
        return ((Integer) get(14, 2, Integer.class)).intValue();
    }

    public int[] getPartVoltages() {
        int length = this.mPartVoltages.length;
        int i = 0;
        int i2 = 16;
        while (i < length) {
            this.mPartVoltages[i] = ((Integer) get(i2, 2, Integer.class)).intValue();
            i++;
            i2 += 2;
        }
        return this.mPartVoltages;
    }

    public int getSerialNo() {
        return ((Integer) get(28, 2, Integer.class)).intValue();
    }

    public int[] getProductDate() {
        int intValue = ((Integer) get(30, 2, Integer.class)).intValue();
        this.mProductDate[0] = ((FLAG_YEAR & intValue) >>> 9) + 1980;
        this.mProductDate[1] = (intValue & FLAG_MONTH) >>> 5;
        this.mProductDate[2] = intValue & 31;
        return this.mProductDate;
    }

    public int getTemperature() {
        return ((Integer) get(32, 2, Integer.class)).intValue();
    }

    public ConnStatus getConnStatus() {
        return ConnStatus.ofData(((Integer) get(34, 1, Integer.class)).intValue());
    }

    public int totalStudyCycle() {
        return ((Integer) get(35, 2, Integer.class)).intValue();
    }

    public int lastStudyCycle() {
        if (this._recData.length < 38) {
            return getLoopNum();
        }
        return ((Integer) get(37, 2, Integer.class)).intValue();
    }

    public boolean isNeedStudy() {
        return getLoopNum() - lastStudyCycle() > 15;
    }

    public boolean isBatteryOnCharge() {
        return ((Integer) get(39, 2, Integer.class)).intValue() == 1;
    }

    protected void doPack() {
    }
}

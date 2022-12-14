package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;

public class DataBatteryGetPushCheckStatus extends n {
    private static DataBatteryGetPushCheckStatus instance = null;

    public static synchronized DataBatteryGetPushCheckStatus getInstance() {
        DataBatteryGetPushCheckStatus dataBatteryGetPushCheckStatus;
        synchronized (DataBatteryGetPushCheckStatus.class) {
            if (instance == null) {
                instance = new DataBatteryGetPushCheckStatus();
            }
            dataBatteryGetPushCheckStatus = instance;
        }
        return dataBatteryGetPushCheckStatus;
    }

    public boolean isOK() {
        return getFirstDischargeStatus() || getSecondDischargeStatus() || getFirstOverheatStatus() || getSecondOverheatStatus() || getFirstLowheatStatus() || getSecondLowheatStatus();
    }

    public boolean getFirstDischargeStatus() {
        return (((Integer) get(0, 4, Integer.class)).intValue() & 1) == 1;
    }

    public boolean getSecondDischargeStatus() {
        return ((((Integer) get(0, 4, Integer.class)).intValue() >> 1) & 1) == 1;
    }

    public boolean getFirstOverheatStatus() {
        return ((((Integer) get(0, 4, Integer.class)).intValue() >> 2) & 1) == 1;
    }

    public boolean getSecondOverheatStatus() {
        return ((((Integer) get(0, 4, Integer.class)).intValue() >> 3) & 1) == 1;
    }

    public boolean getFirstLowheatStatus() {
        return ((((Integer) get(0, 4, Integer.class)).intValue() >> 4) & 1) == 1;
    }

    public boolean getSecondLowheatStatus() {
        return ((((Integer) get(0, 4, Integer.class)).intValue() >> 5) & 1) == 1;
    }

    public boolean getDischargeShortCircuit() {
        return ((((Integer) get(0, 4, Integer.class)).intValue() >> 6) & 1) == 1;
    }

    public boolean getCustomDischarge() {
        return ((((Integer) get(0, 4, Integer.class)).intValue() >> 17) & 1) == 1;
    }

    public short getUnderVoltageBatteryCellIndex() {
        return (short) ((((Integer) get(0, 4, Integer.class)).intValue() >> 7) & 7);
    }

    public short getDamagedBatteryCellIndex() {
        return (short) ((((Integer) get(0, 4, Integer.class)).intValue() >> 10) & 7);
    }

    protected void doPack() {
    }
}

package dji.common.util;

import dji.midware.data.model.P3.DataCenterGetPushBatteryCommon.ConnStatus;

public class HistoryInfo {
    private static final int FLAG_DISCHARGE = 131072;
    private static final long FLAG_DISCHARGE_NEW = 2097152;
    private static final int FLAG_FIRSTLEVEL_CURRENT = 1;
    private static final long FLAG_FIRSTLEVEL_CURRENT_NEW = 1;
    private static final int FLAG_FIRSTLEVEL_LOWTEMP = 16;
    private static final long FLAG_FIRSTLEVEL_LOWTEMP_NEW = 16;
    private static final int FLAG_FIRSTLEVEL_OVERTEMP = 4;
    private static final long FLAG_FIRSTLEVEL_OVERTEMP_NEW = 4;
    private static final int FLAG_INVALID = 7168;
    private static final long FLAG_INVALID_NEW = 126976;
    private static final int FLAG_REPLACE = 57344;
    private static final int FLAG_SECONDLEVEL_CURRENT = 2;
    private static final long FLAG_SECONDLEVEL_CURRENT_NEW = 2;
    private static final int FLAG_SECONDLEVEL_LOWTEMP = 32;
    private static final long FLAG_SECONDLEVEL_LOWTEMP_NEW = 32;
    private static final int FLAG_SECONDLEVEL_OVERTEMP = 8;
    private static final long FLAG_SECONDLEVEL_OVERTEMP_NEW = 8;
    private static final int FLAG_SHORT_CIRCUIT = 64;
    private static final long FLAG_SHORT_CIRCUIT_NEW = 64;
    private static final int FLAG_UNDER_VOLTAGE = 896;
    private static final long FLAG_UNDER_VOLTAGE_NEW = 3968;
    private static final int FLAG_WATCHDOG_RESET = 65536;
    private static final long FLAG_WATCHDOG_RESET_NEW = 1048576;
    public static final byte VALUE_ALREADY_REPLACE = (byte) 1;
    public static final byte VALUE_NO_REPLACE = (byte) 0;
    private ConnStatus mConnStatus = ConnStatus.NORMAL;
    private boolean mDischargeFlag = false;
    private boolean mFirstLevelCurrent = false;
    private boolean mFirstLevelLowTemp = false;
    private boolean mFirstLevelOverTemp = false;
    private byte mInvalidNum = (byte) 0;
    private int mOriginalError = 0;
    private long mOriginalNewError = 0;
    private byte mReplaceFlag = (byte) 0;
    private boolean mSecondLevelCurrent = false;
    private boolean mSecondLevelLowTemp = false;
    private boolean mSecondLevelOverTemp = false;
    private boolean mShortCircuit = false;
    private byte mUnderVoltageNum = (byte) 0;
    private boolean mWatchDogReset = false;

    public boolean hasError() {
        return hasFirstLevelCurrent() || hasSecondLevelCurrent() || hasFirstLevelOverTemp() || hasSecondLevelOverTemp() || hasFirstLevelLowTemp() || hasSecondLevelLowTemp() || hasShortCircuit() || getUnderVoltageNum() != (byte) 0 || getInvalidNum() != (byte) 0 || hasDischargeSelf() || this.mConnStatus != ConnStatus.NORMAL;
    }

    public boolean hasConnError() {
        return this.mConnStatus != ConnStatus.NORMAL;
    }

    public ConnStatus getConnStatus() {
        return this.mConnStatus;
    }

    public boolean hasFirstLevelCurrent() {
        return this.mFirstLevelCurrent;
    }

    public boolean hasSecondLevelCurrent() {
        return this.mSecondLevelCurrent;
    }

    public boolean hasFirstLevelOverTemp() {
        return this.mFirstLevelOverTemp;
    }

    public boolean hasSecondLevelOverTemp() {
        return this.mSecondLevelOverTemp;
    }

    public boolean hasFirstLevelLowTemp() {
        return this.mFirstLevelLowTemp;
    }

    public boolean hasSecondLevelLowTemp() {
        return this.mSecondLevelLowTemp;
    }

    public boolean hasShortCircuit() {
        return this.mShortCircuit;
    }

    public byte getUnderVoltageNum() {
        return this.mUnderVoltageNum;
    }

    public byte getInvalidNum() {
        return this.mInvalidNum;
    }

    public byte getReplaceFlag() {
        return this.mReplaceFlag;
    }

    public boolean hasWatchDogReset() {
        return this.mWatchDogReset;
    }

    public boolean hasDischargeSelf() {
        return this.mDischargeFlag;
    }

    public void parseSimple(int i) {
        resetSimple();
        this.mOriginalError = i;
        this.mUnderVoltageNum = (byte) ((i & FLAG_UNDER_VOLTAGE) >>> 7);
        this.mInvalidNum = (byte) ((i & FLAG_INVALID) >>> 10);
    }

    public void parse(int i) {
        boolean z;
        boolean z2 = true;
        reset();
        this.mOriginalError = i;
        this.mFirstLevelCurrent = (i & 1) != 0;
        if (((i & 2) >>> 1) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.mSecondLevelCurrent = z;
        if (((i & 4) >>> 2) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.mFirstLevelOverTemp = z;
        if (((i & 8) >>> 3) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.mSecondLevelOverTemp = z;
        if (((i & 16) >>> 4) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.mFirstLevelLowTemp = z;
        if (((i & 32) >>> 5) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.mSecondLevelLowTemp = z;
        if (((i & 64) >>> 6) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.mShortCircuit = z;
        this.mUnderVoltageNum = (byte) ((i & FLAG_UNDER_VOLTAGE) >>> 7);
        this.mInvalidNum = (byte) ((i & FLAG_INVALID) >>> 10);
        this.mReplaceFlag = (byte) ((FLAG_REPLACE & i) >>> 13);
        if (((65536 & i) >>> 16) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.mWatchDogReset = z;
        if (((131072 & i) >>> 17) == 0) {
            z2 = false;
        }
        this.mDischargeFlag = z2;
    }

    public void updateConnStatus(ConnStatus connStatus) {
        this.mConnStatus = connStatus;
    }

    public void reset() {
        this.mFirstLevelCurrent = false;
        this.mSecondLevelCurrent = false;
        this.mFirstLevelOverTemp = false;
        this.mSecondLevelOverTemp = false;
        this.mFirstLevelLowTemp = false;
        this.mSecondLevelLowTemp = false;
        this.mShortCircuit = false;
        this.mUnderVoltageNum = (byte) 0;
        this.mInvalidNum = (byte) 0;
        this.mReplaceFlag = (byte) 0;
        this.mWatchDogReset = false;
        this.mDischargeFlag = false;
    }

    public void resetSimple() {
        this.mUnderVoltageNum = (byte) 0;
        this.mInvalidNum = (byte) 0;
    }

    public boolean equals(Object obj) {
        boolean equals = super.equals(obj);
        if (equals || !(obj instanceof HistoryInfo)) {
            return equals;
        }
        if (this.mOriginalError == ((HistoryInfo) obj).mOriginalError) {
            return true;
        }
        return equals;
    }

    public int hashCode() {
        return this.mOriginalError;
    }

    public String toString() {
        return Integer.toBinaryString(this.mOriginalError);
    }

    public void parse(long j) {
        boolean z;
        boolean z2 = true;
        reset();
        this.mOriginalNewError = j;
        this.mFirstLevelCurrent = (1 & j) != 0;
        if ((2 & j) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.mSecondLevelCurrent = z;
        if ((4 & j) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.mFirstLevelOverTemp = z;
        if ((8 & j) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.mSecondLevelOverTemp = z;
        if ((16 & j) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.mFirstLevelLowTemp = z;
        if ((32 & j) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.mSecondLevelLowTemp = z;
        if ((64 & j) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.mShortCircuit = z;
        this.mUnderVoltageNum = (byte) ((int) ((FLAG_UNDER_VOLTAGE_NEW & j) >> 7));
        this.mInvalidNum = (byte) ((int) ((FLAG_INVALID_NEW & j) >> 12));
        this.mReplaceFlag = (byte) 0;
        if ((1048576 & j) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.mWatchDogReset = z;
        if ((FLAG_DISCHARGE_NEW & j) == 0) {
            z2 = false;
        }
        this.mDischargeFlag = z2;
    }
}
